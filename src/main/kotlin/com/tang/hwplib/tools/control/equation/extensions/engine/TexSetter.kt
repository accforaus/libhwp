package com.tang.hwplib.tools.control.equation.extensions.engine

import com.tang.hwplib.tools.control.equation.extensions.utils.OPENPARTMP
import com.tang.hwplib.tools.control.equation.extensions.engine.search.bSearchTexFunc
import com.tang.hwplib.tools.control.equation.extensions.utils.*

/**
 * Set RM Bound
 * @receiver StringBuilder
 * @param from Int
 * @param to Int
 * @return StringBuilder
 */
fun StringBuilder.setRmBound(from: Int, to: Int) : StringBuilder {
    var builder = this
    val isRm = arrayListOf(false)
    val types = arrayListOf<Int>()

    var index = 0

    while (index < builder.length) {
        when (builder[index]) {
            '{' -> {
                types.push(0)
                if (isRm.isNotEmpty()) isRm.push(isRm.last())
                else isRm.push(false)
            }
            '}' -> {
                types.push(0)
                if (isRm.isNotEmpty())
                    isRm.pop()
            }
            '\\' -> {
                val min = index

                types.push(0)
                ++index

                while (true) {
                    if (builder[index].isAlphaBet()) {
                        types.push(0)
                        ++index
                    } else {
                        if (builder[index].isCLOSEPARTMP()) {
                            types.push(0)
                            ++index
                        }
                        break
                    }
                }

                val searchStr = StringBuilder(builder.slice(IntRange(min, index - 1)).toString())
                val n = searchStr.bSearchTexFunc()

                if (!n) {
                    for (j in min + 1 until index) {
                        types[j] = 2
                    }
                    builder[min] = OPENPARTMP
                    isRm[isRm.lastIndex] = true
                } else {
                    if (builder[min + 1].`is`('x') && builder[min + 1].`is`('r') && builder[min + 1].`is`('m')) {
                        if (isRm.isNotEmpty())
                            isRm[isRm.lastIndex] = true
                        else isRm.push(true)

                        builder[min] = ' '
                        builder[min + 1] = ' '
                        builder[min + 2] = ' '
                        builder[min + 3] = ' '
                        builder[min + 4] = ' '
                    } else if (builder[min + 1].`is`('i') && builder[min + 2].`is`('t')) {
                        if (isRm.isNotEmpty())
                            isRm[isRm.lastIndex] = false
                        else isRm.push(false)

                        builder[min] = ' '
                        builder[min + 1] = ' '
                        builder[min + 2] = ' '
                        builder[min + 3] = ' '
                    }
                }

                --index
            }
            in 'a'..'z', in 'A'..'Z' -> {
                if (isRm.isNotEmpty() && isRm.last()) types.push(2)
                else types.push(1)
            }
            else -> types.push(0)
        }

        index++
    }

    val vecovlPos = arrayListOf<Int>()

    var pos = 0

    while (true) {
        pos = builder.indexOf("\\ovl", pos)
        if (pos == -1) break
        vecovlPos.push(pos)
        pos += 4
    }

    pos = 0

    while (true) {
        pos = builder.indexOf("\\vec", pos)
        if (pos == -1) break
        vecovlPos.push(pos)
        pos += 4
    }

    vecovlPos.sort()

    for (n in vecovlPos.asReversed()) {
        val parameterResult = builder.getParameterRange(n + 5)
        val from = parameterResult.from
        val to = parameterResult.to
        builder = parameterResult.builder

        if (!parameterResult.result) {
            var smartResult = builder.smartInsert(to, "}")
            builder = smartResult.builder

            if (smartResult.result != 0) types.add(types.first() + to, 0)

            smartResult = builder.smartInsert(from, "{")
            builder = smartResult.builder

            if (smartResult.result != 0) types.add(types.first() + from, 0)
        }

        var j = from
        while (j < to) {
            if (types[j] == 1) break
            j++
        }

        if (j == to) {
            j = from
            while (j < to) {
                types[j] = 0
                j++
            }

            if (builder[n + 1].`is`('o')) {
                builder[n + 3] = 'r'
            } else {
                builder[n + 2] = 'r'
                builder[n + 3] = 'm'
            }
        }
    }

    index = types.size - 1
    while (index >= 0) {
        if (types[index] == 2) {
            val end = index + 1

            while (true) {
                if (types[index] == 2 || (types[index] != 1 && (builder[index].`is`(' ') || builder[index].`is`('_') || builder[index].`is`('^'))))
                    --index
                else
                    break
            }
            ++index

            builder = builder.smartInsert(end, "}").builder
            builder = builder.smartInsert(index, "\\mrm{").builder
        }
        index--
    }

    return builder
}


/**
 * Set Case Bound
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.setCaseBound() : StringBuilder {
    var builder = this

    var startPos = 0

    while (true) {
        startPos = builder.indexOf("cases", startPos)
        if (startPos == -1) break

        val parStart = builder.indexOf("{", startPos)
        val len = parStart - startPos
        var parLevel = 1

        var i = parStart
        var andNum = 0
        var sharpNum = 0

        while (i < builder.length) {
            when (builder[i]) {
                '{' -> ++parLevel
                '}' -> --parLevel
                '#' -> {
                    if (parLevel == 1) {
                        if (!builder[i + 1].`is`('\n')) {
                            val smartResult = builder.smartInsert(i + 1, "\n")
                            builder = smartResult.builder
                            i += smartResult.result
                        }
                        ++sharpNum
                    }
                }
                '&' -> {
                    if (parLevel == 1 && sharpNum == 0) ++andNum
                }
            }
            if (parLevel == 0) break
            i++
        }

        if (andNum <= 1) {
            if (i == builder.length) {
                builder = builder.deleteCharAt(builder.length - 1)
                builder = builder.insert(i - 1, "\n\\end{cases}").append('$')
            } else builder = builder.insert(i, "\n\\end{cases}")
            builder = builder.insert(startPos, len + 1, "{\\begin{cases}\n")
            startPos = builder.indexOf("\\end", startPos) + 13
        } else {
            val tBuilder = StringBuilder("{\\left\\{ \\beginttabuu{")
            for (j in 0 until andNum) tBuilder.append('c')
            tBuilder.append('}')

            builder = builder.insert(i, "\\end{tabu} \\right.\n")
            builder = builder.insert(startPos, len + 1, tBuilder.toString())
            startPos = builder.indexOf("\\end", startPos + tBuilder.length) + 18
        }
    }

    return builder
}

/**
 * Set Matrix Bound
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.setMatrixBound() : StringBuilder {
    var builder = this

    var startPos = 0

    while (true) {
        startPos = builder.indexOf("matrix", startPos)
        if (startPos == -1) break

        val parStart = builder.indexOf("{", startPos)
        val len = parStart - startPos
        var parLevel = 1

        val typeBuilder = StringBuilder()

        if (builder[startPos - 1].`is`('b') || builder[startPos - 1].`is`('d') || builder[startPos - 1].`is`('p')) {}
            //typeBuilder.append(builder[startPos - 1])
        typeBuilder.append("pmatrix")

        var i = parStart + 1

        while (i < builder.length) {
            when (builder[i]) {
                '{' -> ++parLevel
                '}' -> --parLevel
            }
            if (parLevel == 0) break
            i++
        }
        builder = builder.insert(i, "\\end{$typeBuilder}")
        builder = if (!builder[0].`is`('m'))
            builder.insert(startPos - 1, len + 2, "{\\begin{$typeBuilder}")
        else
            builder.insert(startPos, len + 1, "{\\begin{$typeBuilder}")
        startPos = builder.indexOf("\\end", startPos) + 13
    }

    return builder
}

/**
 * Set LR Parameters
 * @receiver StringBuilder
 * @return Pair<StringBuilder, Boolean>
 */
fun StringBuilder.setLRPar() : Pair<StringBuilder, Boolean> {
    var builder = this

    var n = 0

    while (true) {
        n = builder.indexOf("\\left", n + 1)
        if (n == -1) break

        n += 5

        n = builder.findNotSpace(n)

        if (builder[n].`is`('{') || builder[n].`is`('}')) {
            builder = builder.smartInsert(n, "\\").builder
        } else {
            if (builder[n].`is`('(') || builder[n].`is`('|') ||
                    builder[n].`is`('[') || builder[n].`is`('.') ||
                    builder[n].`is`('<') || builder[n].`is`(')') ||
                    builder[n].`is`(']') || builder[n].`is`('>') ||
                    builder[n].`is`('\\')) {}
            else builder = builder.smartInsert(n, ".").builder
        }
    }

    n = 0

    while (true) {
        n = builder.indexOf("\\right", n + 1)

        if (n == -1) break

        n += 6
        n = builder.findNotSpace(n)

        if (builder[n].`is`('}') || builder[n].`is`('{')) {
            builder = builder.smartInsert(n, "\\").builder
        } else {
            if (builder[n].`is`(')') || builder[n].`is`('|') ||
                    builder[n].`is`(']') || builder[n].`is`('.') ||
                    builder[n].`is`('>') || builder[n].`is`('(') ||
                    builder[n].`is`('[') || builder[n].`is`('<') ||
                    builder[n].`is`('\\')) {}
            else builder = builder.smartInsert(n, ".").builder
        }
    }

    var parLevel = 0

    for (i  in 1 until builder.length) {
        when (builder[i]) {
            '{' -> if (!builder[i - 1].`is`('\\')) ++parLevel
            '}' -> if (!builder[i - 1].`is`('\\')) --parLevel
        }
    }

    if (parLevel > 0) {
        for (i in 0 until parLevel)
            builder = builder.smartInsert(builder.length - 1, "}").builder
    } else if (parLevel < 0) {
        for (i in 0 until -parLevel) {
            builder = builder.smartInsert(1, "{").builder
        }
    }

    var insertedNum = 0

    val LRLevs = arrayListOf(Pair(0, 1))

    var i = 1

    while (i < builder.length) {
        if (builder[i].`is`('$')) break
        if (builder[i].`is`('{') && !builder[i - 1].`is`('\\'))
            LRLevs.push(Pair(0, i + 1))
        else if (builder[i].`is`('}') && !builder[i - 1].`is`('\\')) {
            if (LRLevs.last().first < 0) {
                for (j in LRLevs.last().first until 0) {
                    val smartResult = builder.smartInsert(LRLevs.last().second, "\\left. ")
                    builder = smartResult.builder
                    i += smartResult.result
                    ++insertedNum
                }
            } else if (LRLevs.last().first > 0) {
                for (j in LRLevs.last().first downTo 1) {
                    val smartResult = builder.smartInsert(LRLevs.last().second, "\\right. ")
                    builder = smartResult.builder
                    i += smartResult.result
                    ++insertedNum
                }
            }

            LRLevs.pop()
        } else if (builder[i].`is`('\\')) {
            if (i < builder.length - 5) {
                if (builder[i + 1].`is`('l') && builder[i + 2].`is`('e') && builder[i + 3].`is`('f') && builder[i + 4].`is`('t')) {
                    val value = LRLevs.last()
                    LRLevs[LRLevs.lastIndex] = Pair(value.first + 1, value.second)
                    i += 5
                } else if (builder[i + 1].`is`('r') && builder[i + 2].`is`('i') && builder[i + 3].`is`('g') && builder[i + 4].`is`('h') && builder[i + 5].`is`('t')) {
                    val value = LRLevs.last()
                    LRLevs[LRLevs.lastIndex] = Pair(value.first - 1, value.second)
                    i += 6
                }
            }
        }
        i++
        if (i == builder.length) break
    }

    if (LRLevs[0].first > 0) {
        ++insertedNum
        for (j in LRLevs.last().first downTo 1) {
            builder = builder.smartInsert(i, "\\right. ").builder
        }
    } else if (LRLevs[0].first < 0) {
        ++insertedNum
        for (j in LRLevs.last().first until 0) {
            builder = builder.smartInsert(1, "\\left. ").builder
        }
    }

    return Pair(builder, insertedNum != 0)
}