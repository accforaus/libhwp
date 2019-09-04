package com.tang.hwplib.tools.control.equation.extensions.engine

import com.tang.hwplib.tools.control.equation.extensions.utils.*

/**
 * Check double sub
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.checkDoubleSub() : StringBuilder {
    var builder = this

    for (doubleSubSup in doubleSubSupList) {
        var startPos = 0
        while (true) {
            startPos = builder.indexOf(doubleSubSup, startPos)
            if (startPos == -1) break

            var findSub = false
            var findSup = false
            var insertPar = false
            var n = startPos + doubleSubSup.length
            n = builder.findNotSpace(n)

            if (builder[n].`is`('_')) {
                findSub = true
                ++n
            } else if (builder[n].`is`('^')) {
                findSup = true
                ++n
            } else {
                startPos = n
                continue
            }

            n = builder.findNotSpace(n)
            n = builder.findParMatch(n)
            n = builder.findNotSpace(n)

            if (builder[n].`is`('_')) {
                if (!findSub) {
                    findSub = true
                    ++n
                } else insertPar = true
            } else if (builder[n].`is`('^')) {
                if (!findSup) {
                    findSup = true
                    ++n
                } else insertPar = true
            } else {
                startPos = n
                continue
            }

            if (!insertPar) {
                n = builder.findNotSpace(n)
                n = builder.findParMatch(n)
                n = builder.findNotSpace(n)

                if (builder[n].`is`('_') || builder[n].`is`('^')) insertPar = true
            }

            if (insertPar) {
                builder = builder.smartInsert(n, "{}").builder
            }
            startPos = n
        }
    }
    return builder
}

/**
 * Check small parameter
 * @receiver StringBuilder
 * @return StringBuilder
 */
fun StringBuilder.checkSmallPar() : StringBuilder {
    var builder = this

    var start: Int
    var end = -1
    var prevStart = -1

    while (true) {
        end = builder.indexOf("\\right", end + 1)
        if (end == -1) break

        start = builder.lastIndexOf("\\left", end - 1)
        if (start == prevStart) continue

        prevStart = start

        var i = start + 5

        while (i < end) {
            val p = builder[i]
            if (p.`is`('\\')) {
                if (builder.indexOf("\\dfrac", i) == i || builder.indexOf("\\begin", i) == i) break
            } else {
                if (p.`is`('^') || p.`is`('_')) break
            }
            i++
        }
        if (i == end) {
            var parChar = end + 6
            parChar = builder.findNotSpace(parChar)

            if (builder[parChar].`is`('.')) {
                builder = builder.erase(end, parChar - end + 1)
                end -= parChar - end + 1
            } else {
                builder = builder.erase(end, 6)
                end -= 6
            }

            parChar = start + 5
            parChar = builder.findNotSpace(parChar)

            builder = if (builder[parChar].`is`('.'))
                builder.erase(start, parChar - start + 1)
            else
                builder.erase(start, 5)
        } else {
            end += 6
        }
    }
    return builder
}

