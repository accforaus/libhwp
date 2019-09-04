package com.tang.hwplib.tools.control.equation.extensions.engine

import com.tang.hwplib.tools.control.equation.extensions.utils.*
import com.tang.hwplib.tools.control.equation.extensions.utils.needToWrap

/**
 * Wrap sub and sup
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.wrapSubSup() : StringBuilder {
    var builder = this
    var startPos = 0

    while (true) {
        startPos = builder.indexOf("^", startPos)

        if (startPos == -1) break


        val parameterResult = builder.getParameterRange(startPos + 1)
        val start = parameterResult.from
        val end = parameterResult.to
        builder = parameterResult.builder

        if (!parameterResult.result) {
            builder = builder.insert(end, "}")
            builder = builder.insert(start, "{")
        }

        startPos = start
    }

    startPos = 0

    while (true) {
        startPos = builder.indexOf("_", startPos)

        if (startPos == -1) break

        var startWithPar = false
        var n = startPos + 1

        n = builder.findNotSpace(n)

        if (builder[n].`is`('{')) {
            startWithPar = true
            var parLevel = 1
            while (true) {
                ++n

                when (builder[n]) {
                    '{' -> ++parLevel
                    '}' -> --parLevel
                }

                if (parLevel == 0) break
            }
        } else {
            while (n < builder.length) {
                if (builder[n].`is`('$')) break
                if (builder[n].`is`('{') || builder[n].`is`('}') ||
                        builder[n].`is`(' ') || builder[n].`is`('^') || builder[n].`is`('_'))
                    break
                n++
            }
        }

        n = builder.findNotSpace(n)

        if (builder[n].`is`('_')) {
            val parameterResult = builder.getParameterRange(n + 1)
            val start = parameterResult.from
            val end = parameterResult.to
            builder = parameterResult.builder
            builder = builder.insert(end, "}")
            builder = builder.insert(startPos + 1, "{")
        } else {
            if (!startWithPar) {
                builder = builder.insert(n, "}")
                builder = builder.insert(startPos + 1, "{")
            }
        }

        ++startPos
    }

    return builder
}

/**
 * Wrap Sqrt data
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.wrapSqrt() : StringBuilder {
    var builder = this

    var startPos = builder.length

    while (true) {
        startPos = builder.lastIndexOf("\\sqrt", startPos - 1)
        if (startPos == -1) break

        val parameterResult = builder.getParameterRange(startPos + 6)
        var start = parameterResult.from
        var end = parameterResult.to
        val p1 = parameterResult.result
        builder = parameterResult.builder

        var n = end

        n = builder.findNotSpace(n)

        if (builder[n].`is`('o') && builder[n + 1].`is`('f')) {
            builder[n] = ' '
            builder[n + 1] = ' '

            val parameterResult2 = builder.getParameterRange(n + 2)
            val nextStart = parameterResult.from
            var nextEnd = parameterResult.to
            builder = parameterResult.builder
            val p2 = parameterResult2.result

            if (!p2) {
                var smartResult = builder.smartInsert(nextEnd, "}")
                builder = smartResult.builder

                if (smartResult.result != 0) ++nextEnd

                smartResult = builder.smartInsert(nextStart, "{")
                builder = smartResult.builder

                if (smartResult.result != 0) ++nextEnd
            }

            if (!p1) {
                var smartResult = builder.smartInsert(nextEnd, "]")
                builder = smartResult.builder

                if (smartResult.result != 0) ++nextEnd

                smartResult = builder.smartInsert(nextStart, "[")
                builder = smartResult.builder

                if (smartResult.result != 0) ++nextEnd
            } else {
                start = builder.indexOf(start) { it.`is`('{') }
                builder[start] = '['
                builder[end - 1] = ']'
            }

            builder = builder.smartInsert(nextEnd, "}").builder
        } else {
            if (!p1) {
                var smartResult = builder.smartInsert(end, "}", end)
                builder = smartResult.builder
                end = smartResult.insertedPos!!

                smartResult = builder.smartInsert(start, "{")
                builder = smartResult.builder

                if (smartResult.result != 0) ++end
            }
            builder = builder.smartInsert(end, "}").builder
        }
        builder = builder.smartInsert(startPos, "{").builder
    }

    return builder
}

/**
 * Wrap another funcs
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.wrapAnother() : StringBuilder {
    var builder = this
    val size = needToWrap.size
    val positions = arrayListOf<Pair<Int, Int>>()

    for (index in 0 until size) {
        var pos = 0

        while (true) {
            pos = builder.indexOf(needToWrap[index], pos)
            if (pos == -1) break

            positions.push(Pair(pos, needToWrap[index].length))

            pos += needToWrap[index].length
        }
    }

    positions.sortBy { it.first }

    for (position in positions.asReversed()) {
        val parameterResult = builder.getParameterRange(position.first + position.second)
        val start = parameterResult.from
        var end = parameterResult.to
        builder = parameterResult.builder

        if (!parameterResult.result) {
            var smartResult = builder.smartInsert(end, "}")
            builder = smartResult.builder

            if (smartResult.result != 0) ++end

            smartResult = builder.smartInsert(start, "{")
            builder = smartResult.builder

            if (smartResult.result != 0) ++end
        }

        builder = builder.smartInsert(end, "}").builder
        builder = builder.smartInsert(position.first, "{").builder
    }

    return builder
}