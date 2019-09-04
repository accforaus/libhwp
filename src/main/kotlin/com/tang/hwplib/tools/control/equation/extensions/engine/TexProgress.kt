package com.tang.hwplib.tools.control.equation.extensions.engine

import com.tang.hwplib.tools.control.equation.extensions.engine.search.bSearchAlphabet
import com.tang.hwplib.tools.control.equation.extensions.engine.search.bSearchOutParameter
import com.tang.hwplib.tools.control.equation.extensions.utils.*

/**
 * Progress Deleqalign
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.deleqalign() : StringBuilder {
    var builder = this
    var start = builder.indexOf("eqalign")
    if (start == -1) return builder

    var end: Int

    val eqStart = arrayListOf(0)
    val eqMid = arrayListOf(0)
    val eqEnd = arrayListOf(0)

    val searchBack = arrayListOf(false)
    val searchNext = arrayListOf(false)
    val isMatrix = arrayListOf(false)
    val sharpNum = arrayListOf(0)

    var i = 1

    while (true) {
        end = start + 7

        end = builder.indexOf(end) { !it.`is`('{') }

        eqStart.push(start)
        eqMid.push(end)
        sharpNum.push(0)

        var parLevel = 0

        while (true) {
            ++end
            when (builder[end]) {
                '{' -> ++parLevel
                '}' -> --parLevel
                '#' -> if (parLevel == 0) ++sharpNum[i]
            }
            if (parLevel == -1) break
        }

        eqEnd.push(end)
        start = end + 1
        searchBack.push(true)
        searchNext.push(true)
        isMatrix.push(true)

        start = builder.indexOf("eqalign", start)

        if (start == -1) break
        ++i
    }

    val len = builder.length - 1
    eqStart.push(len)
    eqMid.push(len)
    eqEnd.push(len)
    searchBack.push(true)
    searchNext.push(false)
    isMatrix.push(false)
    sharpNum.push(0)

    start = 1
    end = 1

    i = 1

    while (i < eqStart.size - 1) {
        var parLevel = 0
        var beginLevel: Int
        var hasLineBreak = false

        var j = 0
        var k: Int
        var andNum = 0

        if (searchBack[i]) {
            parLevel = 0
            beginLevel = 0

            k = eqStart[i] - 1
            while (k >= eqEnd[i - 1] + 1) {
                when (builder[k]) {
                    '{' -> {
                        ++parLevel
                        if (parLevel == 0) {
                            if (builder.indexOf("cases}", k + 1, 6) == k + 1 ||
                                    builder.indexOf("matrix}", k + 2, 7) == k + 2 ||
                                    builder.indexOf("tabu}", k + 1, 5) == k + 1) {
                                if (builder.indexOf("\\begin", k - 6, 6) == k - 6) {
                                    k -= 6
                                    ++beginLevel
                                } else if (builder.indexOf("\\end", k - 4, 4) == k - 4) {
                                    k -= 4
                                    --beginLevel
                                    isMatrix[i] = false
                                    searchNext[i] = false
                                }
                            } else if (builder[k + 1].`is`('c')) {
                                var a = k + 2
                                a = builder.indexOf(a) { !it.`is`('c') }

                                if (builder[a].`is`('}') && k >= 12) {
                                    if (builder.indexOf("\\begin{tabu}", k - 12, 12) != k - 12) {
                                        isMatrix[i] = false
                                        searchNext[i] = false
                                    }
                                } else {
                                    isMatrix[i] = false
                                    searchNext[i] = false
                                }
                            } else {
                                isMatrix[i] = false
                                searchNext[i] = false
                            }
                        } else if (parLevel > 0) {
                            isMatrix[i] = false
                            searchNext[i] = false
                        }
                    }
                    '}' -> --parLevel
                    '&' -> {
                        if (beginLevel == 0) {
                            isMatrix[i] = false
                            searchNext[i] = false
                        }
                    }
                    '#' -> if (beginLevel == 0) hasLineBreak = true
                    ' ', '\n', '\r' -> {}
                    else -> {
                        if (parLevel == 0) {
                            isMatrix[i] = false
                            searchNext[i] = false
                        }
                    }
                }
                if (parLevel == 1 || beginLevel == 1 || hasLineBreak || !searchNext[i]) break
                k--
            }
        }

        hasLineBreak = false

        if (searchNext[i] && isMatrix[i]) {
            parLevel = 0
            beginLevel = 0
            j = eqEnd[i] + 1
            while (j < eqStart[i + 1]) {
                when (builder[j]) {
                    '{' -> {
                        ++parLevel
                        isMatrix[i] = false
                    }
                    '}' -> {
                        --parLevel
                        if (parLevel < 0) isMatrix[i] = false
                    }
                    '&' -> {
                        if (beginLevel == 0) {
                            isMatrix[i] = true
                            ++andNum
                        }
                    }
                    '#' -> if (beginLevel == 0) hasLineBreak = true
                    '\\' -> {
                        if (builder.indexOf("end", j + 1, 3) == j + 1) {
                            j += 3
                            --beginLevel
                        } else if (builder.indexOf("begin", j + 1, 5) == j + 1) {
                            ++beginLevel
                            j += 5
                        }
                    }
                    else -> if (beginLevel == 0) isMatrix[i] = false
                }
                if (parLevel == -1 || beginLevel == -1 || hasLineBreak || !isMatrix[i]) break
                j++
            }
        }

        if (j == eqStart[i + 1] && andNum == 1)
            searchBack[i + 1] = false

        if (isMatrix[i]) {
            ++end

            if (searchBack[i + 1]) {
                val arr = arrayListOf(arrayListOf<StringBuilder>())
                val tmp = StringBuilder()
                for (a in start..end) {
                    arr.push(arrayListOf())
                    var startPos = eqMid[a] + 1
                    var b = startPos
                    while (b < eqEnd[a]) {
                        when (builder[b]) {
                            '{' -> ++parLevel
                            '}' -> --parLevel
                            '#' -> {
                                if (parLevel == 0) {
                                    arr[a - start].push(StringBuilder(builder.slice(IntRange(startPos, b - startPos)).toString()))
                                    startPos = b + 2
                                }
                            }
                        }
                        b++
                    }
                    arr[a - start].push(StringBuilder(builder.slice(IntRange(startPos, b - startPos)).toString()))
                }

                for (a in 0 until sharpNum[i] + 1) {
                    for (b in 0 until arr.size) {
                        var k = arr[b][a].indexOf('\n')
                        if (k != -1)
                            arr[b][a] = arr[b][a].delete(k, 1)
                        k = arr[b][a].indexOf('\r')
                        if (k != -1)
                            arr[b][a] = arr[b][a].delete(k, 1)
                        tmp.append(arr[b][a].toString())
                        if (b != arr.size - 1) {
                            tmp.append(" & ")
                        }
                    }

                    if (a != sharpNum[i])
                        tmp.append(" # \n")
                }

                builder = builder.insert(eqStart[start], eqEnd[end - 1] - eqStart[start] + 1, tmp.toString())

                val d = tmp.length - (eqEnd[end - 1] - eqStart[start] + 1)
                eqEnd[i] = eqStart[start] + tmp.length

                for (a in i + 1 until eqStart.size) {
                    eqStart[a] += d
                    eqMid[a] += d
                    eqEnd[a] += d
                }

                start = i + 1
                end = i + 1
            }
        } else {
            builder = builder.insert(eqEnd[i], 1, "\\end{aligned}")
            val alignedIndex = builder.lastIndexOf("aligned")
            val casesIndex = builder.lastIndexOf("cases")
            if (alignedIndex != -1 && casesIndex != -1 && alignedIndex > casesIndex) {
                builder = builder.insert(alignedIndex, 7, "cases")
                builder = builder.insert(casesIndex, 5, "aligned")
            }
            builder = builder.insert(eqStart[i], eqMid[i] - eqStart[i] + 1, "\\begin{aligned}")
            val d = 28 - (eqMid[i] - eqStart[i] + 2)
            eqEnd[i] += d - 1
            for (a in i + 1 until eqStart.size) {
                eqStart[a] += d
                eqMid[a] += d
                eqEnd[a] += d
            }
            start = i + 1
            end = i + 1
        }
        i++
    }

    return builder
}

/**
 * Add space after left right parameter
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.addSpaceAfterLRPar() : StringBuilder {
    var builder = this

    var pos = 0

    while (true) {
        pos = builder.indexOf("\\left", pos)
        if (pos == -1) break

        pos += 5
        pos = builder.findNotSpace(pos)

        if (!builder[pos + 1].`is`(' ')) {
            if (!builder[pos].`is`('\\')) {
                builder = builder.insert(++pos, ' ')
            }
        }
    }

    pos = 0

    while (true) {
        pos = builder.indexOf("\\right", pos)

        if (pos == -1) break

        if (!builder[pos - 1].`is`(' '))
            builder = builder.insert(pos, ' ')
        pos += 7
    }

    return builder
}

/**
 * Over to Frac
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.overToFrac() : StringBuilder {
    var builder = this

    var pos = 0

    while (true) {
        pos = builder.indexOf("\\kfrac", pos)

        if (pos == -1) break

        var rangeResult = builder.getParameterRange(pos + 6)
        builder = rangeResult.builder
        var frontParStart = rangeResult.from
        var frontParEnd = rangeResult.to
        val f = rangeResult.result

        rangeResult = builder.getParameterRangeBack(pos)
        var backParStart = rangeResult.from
        var backParEnd = rangeResult.to
        builder = rangeResult.builder
        val b = rangeResult.result

        if (!f) {
            var smartResult = builder.smartInsert(frontParEnd, "}", frontParEnd)
            frontParEnd = smartResult.insertedPos!!
            builder = smartResult.builder
            ++frontParEnd

            smartResult = builder.smartInsert(frontParStart, "{", frontParStart)
            frontParStart = smartResult.insertedPos!!
            builder = smartResult.builder

            if (smartResult.result != 0) ++frontParEnd
        }

        if (!b) {
            var smartResult = builder.smartInsert(backParEnd, "}", backParEnd)
            backParEnd = smartResult.insertedPos!!
            builder = smartResult.builder

            if (smartResult.result != 0) {
                ++frontParEnd
                ++frontParStart
            }

            smartResult = builder.smartInsert(backParStart, "{", backParStart)
            backParStart = smartResult.insertedPos!!
            builder = smartResult.builder

            if (smartResult.result != 0) {
                ++frontParEnd
                ++frontParStart
                ++backParEnd
            }

            ++backParEnd
        }

        for (i in 0 until backParEnd - backParStart) {
            builder[frontParStart - 1 - i] = builder[backParEnd - 1 - i]
        }

        builder[backParStart] = '\\'
        builder[backParStart + 1] = 'd'
        builder[backParStart + 2] = 'f'
        builder[backParStart + 3] = 'r'
        builder[backParStart + 4] = 'a'
        builder[backParStart + 5] = 'c'

        for (i in backParStart + 6 until builder.length) {
            if (builder[i].`is`('{')) break
            else builder[i] = ' '
        }

        builder = builder.smartInsert(frontParEnd, "}").builder
        builder = builder.smartInsert(backParStart, "{").builder

        pos = backParStart + 5
    }

    return builder
}

/**
 * Auto Align
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.autoAlign() : StringBuilder {
    var builder = this

    val startCasePos = arrayListOf<Int>()
    val endCasePos = arrayListOf<Int>()
    val startMatrixPos = arrayListOf<Int>()
    val endMatrixPos = arrayListOf<Int>()
    val startAlignedPos = arrayListOf<Int>()
    val endAlignedPos = arrayListOf<Int>()

    var scp = 0
    var smp = 0
    var sap = 0
    var andPos = -1
    var newLnPos = -1
    var cNum = 0
    var mNum = 0
    var aNum = 0

    while (true) {
        scp = builder.indexOf("\\begin{cases}", scp) + 13

        if (scp - 13 != -1) {
            startCasePos.push(scp)
            scp = builder.indexOf("\\end{cases}", scp)
            endCasePos.push(scp)
            scp += 11
        } else break
    }

    while (true) {
        smp = builder.indexOf("matrix", smp) + 7

        if (smp - 7 != -1) {
            startMatrixPos.push(smp)
            smp = builder.indexOf("matrix", smp) - 6
            endMatrixPos.push(smp)
            smp += 7
        } else break
    }

    while (true) {
        sap = builder.indexOf("\\begin{aligned}", sap) + 15

        if (sap - 15 != -1) {
            startAlignedPos.push(sap)
            sap = builder.indexOf("\\end{aligned}", sap)
            endAlignedPos.push(sap)
            sap += 13
        } else break
    }

    cNum = startCasePos.size
    mNum = startMatrixPos.size
    aNum = startAlignedPos.size

    while (true) {
        andPos = builder.indexOf("&", andPos + 1)
        newLnPos = builder.indexOf("#", newLnPos + 1)
        if (andPos == -1 && newLnPos == -1) break

        var i = 0
        while (i < cNum) {
            if (andPos in startCasePos[i]..endCasePos[i] ||
                    newLnPos in startCasePos[i]..endCasePos[i]) break
            i++
        }

        var j = 0
        while (j < mNum) {
            if (andPos in startMatrixPos[j]..endMatrixPos[j] ||
                    newLnPos in startMatrixPos[j]..endMatrixPos[j]) break
            j++
        }

        var k = 0
        while (k < aNum) {
            if (andPos in startAlignedPos[k]..endAlignedPos[k] ||
                    newLnPos in startAlignedPos[k]..endAlignedPos[k]) break
            k++
        }

        if (i == cNum && j == mNum && k == aNum) {
            if (andPos in 0 until newLnPos)
                builder = builder.insert(andPos, "\n")
            builder = builder.insert(builder.indexOf("$") + 1, 0, "\\begin{align*}\n")
            builder = builder.insert(builder.lastIndexOf("$"), 0, "\n\\end{align*}")
            break
        }
    }
    return builder
}

/**
 * Change Piles
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.changePile() : StringBuilder {
    var builder = this

    var start = builder.indexOf("\\pile")
    if (start == -1) return builder

    while (true) {
        val mid = builder.indexOf("{", start + 5)
        var end = mid + 1
        var parLevel = 0

        while (end < builder.length) {
            when (builder[end]) {
                '{' -> ++parLevel
                '}' -> --parLevel
            }
            if (parLevel == -1) break
            end++
        }

        builder = builder.insert(end, 1, "\\end{aligned}")
        builder = builder.insert(start, mid - start + 1, "\\begin{aligned}")
        start = builder.indexOf("\\pile", start + 15)
        if (start == -1) break
    }

    return builder
}

/**
 * Equation in HWP to Tex
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.toTex() : StringBuilder {
    var builder = this

    builder = builder.insert(0, '$')
    builder.append('$')

    var errorProbability = false

    for (i in builder.length - 1 downTo 1) {
        if (!builder[i - 1].isAlphaBet() && builder[i - 1].isAlphaBet()) {
            if (!builder[i - 1].`is`(' ') && builder.bSearchOutParameter(i, hwpParameterOutKeyword) != -1) {
                builder = builder.insert(i, " ")
            }
        }
    }

    builder = builder.wrapSubSup()

    for (i in builder.indices) {
        if (builder[i].isLower() && builder[i + 1].isUpper())
            builder = builder.insert(i + 1, " ")
    }

    var startPos = 0

    for (i in 0 until spcSize) {
        startPos = 0
        val hwpStrLen = spcHwp[i].length

        while (true) {
            val n = builder.indexOf(spcHwp[i], startPos)
            if (n != -1) {
                builder = builder.insert(n, hwpStrLen, spcTex[i])
                startPos = n + spcTex[i].length
            } else break
        }
    }

    var i = 0
    while (i < builder.length) {
        if (!builder[i].isAlphaBet()) {
            i++
            continue
        }
        val n = builder.bSearchAlphabet(i, funcSorted, funcSize)

        if (n != -1) {
            val hwpSize = funcSorted[n].hwp.length
            val texSize = funcSorted[n].tex.length
            builder = builder.insert(i, hwpSize, funcSorted[n].tex)
            i += texSize - 1
        }
        i++
    }

    builder = builder.setRmBound(0, builder.length)
    builder = builder.setCaseBound()
    builder = builder.setMatrixBound()
    val result = builder.setLRPar()
    builder = result.first
    errorProbability = result.second

    builder = builder.addSpaceAfterLRPar()
    builder = builder.wrapAnother()
    builder = builder.wrapSqrt()
    builder = builder.overToFrac()

    for (index in builder.indices) {
        if (builder[index].isCLOSEPARTMP() || builder[index].isOPENPARTMP() || builder[index].`is`('`'))
            builder[index] = ' '
    }

    builder = builder.checkSmallPar()
    builder = builder.deleqalign()
    builder = builder.changePile()

    builder = builder.autoAlign()
    builder = builder.deleteOverUsedPar()
    builder = builder.checkDoubleSub()
    builder = builder.deleteMultipleSpace()

    for (index in 0 until spcNextSize) {
        startPos = 0
        val hwpStrLen = spcHwpNext[index].length

        while (true) {
            val n = builder.indexOf(spcHwpNext[index], startPos)
            if (n != -1) {
                builder = builder.insert(n, hwpStrLen, spcTexNext[index])
                startPos = n + spcTexNext[index].length
            } else break
        }
    }

    for (index in 0 until spcUniCodeSize) {
        var j = 0
        while (j < builder.length) {
            var k = 0
            while (k < hwpUnicodeSpc[index].size) {
                if (!builder[j + k].`is`(hwpUnicodeSpc[index][k])) break
                k++
            }
            if (k > hwpUnicodeSpc[index].lastIndex) {
                builder = builder.insert(j, hwpUnicodeSpcLen[index], texUnicodeSpc[index])
                j += texUnicodeSpcLen[i] - 1
            }
            if (builder.length - j <= hwpUnicodeSpcLen[index]) break
            j++
        }
    }

    for (index in 0 until remainSize) {
        startPos = 0
        val hwpStrLen = funcRemain[index].length
        while (true) {
            val n = builder.indexOf(funcRemain[index], startPos)
            if (n != -1) {
                val replace = funcRemainNext[index]
                builder = builder.insert(n, hwpStrLen, funcRemainNext[index])
                startPos = n
            } else break
        }
    }

    if (errorProbability) builder.append("****")

    builder = builder.deleteCharAt(0)
    return builder.deleteCharAt(builder.lastIndex)
}