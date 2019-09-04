package com.tang.hwplib.tools.control.equation.extensions.engine

import com.tang.hwplib.tools.control.equation.extensions.utils.*
import com.tang.hwplib.tools.control.equation.extensions.utils.oneParSize
import com.tang.hwplib.tools.control.equation.extensions.utils.oneParameter
import com.tang.hwplib.tools.control.equation.extensions.utils.twoParSize
import com.tang.hwplib.tools.control.equation.extensions.utils.twoParameter

/**
 * Delete Over Used Par function
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.deleteOverUsedPar() : StringBuilder {
    val builder = this
    var parLev = 0
    val positions = arrayListOf<Int>()
    val parNums = arrayListOf<Int>()

    for (index in 1 until builder.length) {
        if (builder[index].`is`('{') && !builder[index - 1].`is`('\\')) {
            positions.push(index)
            if (parLev >= parNums.size || parNums[parLev] == 0) parNums.push(0)
            ++parLev
        } else if (builder[index].`is`('}') && !builder[index - 1].`is`('\\')) {
            var deletePar = true

            val start = positions[--parLev]
            val end = index
            positions.pop()

            if (parNums[parLev] == 0) {
                parNums.pop()

                var j = start - 1
                j = builder.indexOf(j, true) { it.`is`(' ') }
                if (builder[j].`is`(']')) {
                    var bigParLev = 1
                    var midParLev = 0
                    --j
                    while (j >= 0) {
                        when (builder[j]) {
                            '[' -> --bigParLev
                            ']' -> ++bigParLev
                            '{' -> if(!builder[j - 1].`is`('\\')) --midParLev
                            '}' -> if(!builder[j - 1].`is`('\\')) ++midParLev
                        }
                        if (bigParLev == 0 || midParLev < 0) break
                        j--
                    }

                    if (j == -1) deletePar = false
                    else {
                        --j
                        j = builder.indexOf(j, true) { it.`is`(' ') }
                    }
                }
                if (deletePar && builder[j].isAlphaBet()) {
                    --j
                    j = builder.indexOf(j, true) { it.isAlphaBet() }

                    if (builder[j].`is`('\\')) {
                        for (k in 0 until oneParSize) {
                            if (builder.indexOf(oneParameter[k], j) == j) {
                                deletePar = false
                                break
                            }
                        }

                        for (k in 0 until twoParSize) {
                            if (builder.indexOf(twoParameter[k], j) == j) {
                                parNums.push(1)
                                deletePar = false
                                break
                            }
                        }
                    }
                } else if (deletePar && (builder[j].`is`('_') || builder[j].`is`('^'))) {
                    var k = start + 1
                    k = indexOf(k) { it.`is`(' ') }
                    if (k != end) deletePar = false
                    else builder[j] = ' '
                }
            }
            else {
                if (--parNums[parLev] == 0) parNums.pop()
                deletePar = false
            }

            if (deletePar) {
                builder[start] = ' '
                builder[end] = ' '
            }
        }
    }

    return builder
}

/**
 * delete multiple space
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.deleteMultipleSpace() : StringBuilder {
    var builder = this

    var index = builder.length - 1

    while (index > 0) {
        if (builder[index].`is`(' ') && builder[index - 1].`is`(' ')) {
            var j = index - 1
            j = builder.indexOf(j, true) { it.`is`(' ') }
            builder = builder.erase(j + 2, index - j - 1)
            index = j
        }
        index--
    }

    return builder
}

