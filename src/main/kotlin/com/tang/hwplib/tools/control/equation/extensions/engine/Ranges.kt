package com.tang.hwplib.tools.control.equation.extensions.engine

import com.tang.hwplib.tools.control.equation.extensions.utils.*

/**
 * Find index from start range
 * @receiver StringBuilder
 * @param start Int
 * @return Int
 */
fun StringBuilder.findRangeStart(start: Int) : Int {
    var parLevel = 0
    var index = start
    while (index >= 0) {
        if (!this[index].`is`('$')) {
            if (this[index].`is`('{') && !this[index - 1].`is`('\\')) ++parLevel
            else if (this[index].`is`('}') && !this[index - 1].`is`('\\')) --parLevel
            if (parLevel > 0) break
        }
        index--
    }
    return index + 1
}

/**
 * Find index from end range
 * @receiver StringBuilder
 * @param start Int
 * @return Int
 */
fun StringBuilder.findRangeEnd(start: Int) : Int {
    var parLevel = 0
    var index = start

    while (index < this.length) {
        if (!this[index].`is`('$')) {
            if (this[index].`is`('{') && !this[index - 1].`is`('\\')) ++parLevel
            else if (this[index].`is`('}') && !this[index - 1].`is`('\\')) --parLevel
            if (parLevel < 0) break
        }
        index++
    }
    return index
}

/**
 * Class for Parameter Range result
 * @property builder StringBuilder
 * @property result Boolean
 * @property from Int
 * @property to Int
 * @constructor
 */
data class ParameterResult(
        val builder: StringBuilder,
        val result: Boolean,
        val from: Int,
        val to: Int
)


/**
 * Get parameter range from start
 * @receiver StringBuilder
 * @param start Int
 * @return ParameterResult
 */
fun StringBuilder.getParameterRange(start: Int) : ParameterResult {
    var n = start
    var builder = this
    var res = 0
    val from = n
    var to = 0

    val positions = arrayListOf(n)

    while (positions.isNotEmpty()) {
        n = positions.pop()

        n = builder.findNotSpace(n)

        if (builder[n].`is`('{')) {
            var parLevel = 1

            while (true) {
                ++n
                when (builder[n]) {
                    '{' -> ++parLevel
                    '}' -> --parLevel
                    '$' -> {
                        if (parLevel != 0) {
                            for (i in 0 until parLevel) {
                                builder = builder.insert(n, '}')
                                parLevel = 0
                                break
                            }
                        }
                    }
                }

                if (parLevel == 0) break
            }
            ++n
        } else {
            if (builder[n].isOPENPARTMP()) builder[n++] = ' '
            while (n < builder.length) {
                if (builder[n].`is`('$')) break
                if (builder[n].`is`('{') || builder[n].`is`('}') ||
                        builder[n].`is`(' ') || builder[n].`is`('^') ||
                        builder[n].`is`('_') || builder[n].`is`('`')) break

                ++n
            }
            ++res
        }

        var temp = n
        temp = builder.findNotSpace(temp)

        if (builder[temp].`is`('^') || builder[temp].`is`('_')) {
            positions.push(temp + 1)
            ++res
        }
    }

    to = n

    return ParameterResult(builder, res == 0, from, to)
}

/**
 * Get parameter back range from start
 * @receiver StringBuilder
 * @param start Int
 * @return ParameterResult
 */
fun StringBuilder.getParameterRangeBack(start: Int) : ParameterResult {
    var n = start
    var res = 0
    val builder = this
    val to = n
    var from = 0

    --n

    val positions = arrayListOf(n)

    while (positions.isNotEmpty()) {
        n = positions.pop()

        n = builder.findNotSpaceBack(n)

        if (builder[n].`is`('}')) {
            var parLevel = -1
            --n

            while (n != 1) {
                when (builder[n]) {
                    '{' -> ++parLevel
                    '}' -> --parLevel
                }

                if (parLevel == 0) break
                --n
            }
        } else {
            while (n != 0) {
                if (builder[n].`is`('{') || builder[n].`is`('}') ||
                        builder[n].`is`(' ') || builder[n].`is`('^') ||
                        builder[n].`is`('_') || builder[n].`is`('`')) break
                --n
            }

            ++n
            ++res
        }

        var temp = n - 1
        temp = builder.findNotSpaceBack(temp)

        if (builder[temp].`is`('^') || builder[temp].`is`('_')) {
            positions.push(temp - 1)
            ++res
        }
    }

    from = n

    return ParameterResult(builder, res == 0, from, to)
}