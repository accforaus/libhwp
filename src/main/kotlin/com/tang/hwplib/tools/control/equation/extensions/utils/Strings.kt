package com.tang.hwplib.tools.control.equation.extensions.utils


/**
 * [str]의 문자열에서 [length] 만큼의 문자열의 위치를 반환하는 확장 함수
 * @receiver StringBuilder
 * @param str String
 * @param fromIndex Int
 * @param length Int
 * @return Int
 */
fun StringBuilder.indexOf(str: String, fromIndex: Int, length: Int) = this.indexOf(str.take(length), fromIndex)

/**
 * 빈 문자열을 확인하는 확장 함수
 * @receiver StringBuilder
 * @return Boolean
 */
fun StringBuilder.isWhiteSpace() = this.length == 1 && this[0] == ' '

/**
 * 문자가 알파벳인지 확인하는 확장 함수
 * @receiver Char
 * @return Boolean
 */
fun Char.isAlphaBet() = this in 'a'..'z' || this in 'A'..'Z'

/**
 * 해당 문자가 [OPENPARTMP] 여부 확인 확장 함수
 * @receiver Char
 * @return Boolean
 */
fun Char.isOPENPARTMP() = this.`is`(OPENPARTMP)

/**
 * 해당 문자가 [CLOSEPARTMP] 여부 확인 확장 함수
 * @receiver Char
 * @return Boolean
 */
fun Char.isCLOSEPARTMP() = this.`is`(CLOSEPARTMP)

/**
 * 해당 문자가 같은 문자인지 여부 확인 확장 함수
 * @receiver Char
 * @param char Char
 * @return Boolean
 */
fun Char.`is`(char: Char) = this == char

/**
 * check character is upper case
 * @receiver Char
 * @return Boolean
 */
fun Char.isUpper() = this in 'A'..'Z'

/**
 * check character is lower case
 * @receiver Char
 * @return Boolean
 */
fun Char.isLower() = this in 'a'..'z'

/**
 * 해당 위치의 문자가 빈공간인지 확인하는 함수
 * @receiver StringBuilder
 * @param position Int
 * @return Int
 */
fun StringBuilder.findNotSpace(position: Int) : Int {
    var result = position
    while (result < this.length) {
        if (this[result].isWhitespace() || this[result].isCLOSEPARTMP())
            result++
        else
            break
    }
    return result
}

/**
 * 해다 위치의 문자가 빈공간인지 거꾸로 확인하는 확장 함수
 * @receiver StringBuilder
 * @param position Int
 * @return Int
 */
fun StringBuilder.findNotSpaceBack(position: Int) : Int {
    var result = position
    while (result >= 0) {
        if (this[result].isWhitespace() || this[result].isCLOSEPARTMP())
            result--
        else
            break
    }
    return result
}

/**
 * Find white space index from start
 * downTo is option for for loop
 * @receiver StringBuilder
 * @param start Int
 * @param downTo Boolean
 * @return Int
 */
fun StringBuilder.indexOfWhiteSpace(start: Int, downTo: Boolean = false) : Int {
    var result = 0
    when (downTo) {
        true -> {
            for (i in start downTo 0) {
                if (this[i].isWhitespace()) {
                    result = i
                    break
                }
            }
        }
        else -> {
            for (i in start until length) {
                if (this[i].isWhitespace()) {
                    result = i
                    break
                }
            }
        }
    }
    return result
}

/**
 * SmartInsertResult class for result smartInsert()
 * @property builder StringBuilder
 * @property insertedPos Int?
 * @property result Int
 * @constructor
 */
data class SmartInsertResult(
        val builder: StringBuilder,
        val insertedPos: Int? = null,
        val result: Int
)

/**
 * Insert [p] string to fit tex.
 * @receiver StringBuilder
 * @param pos Int
 * @param p String
 * @param insertedPos Int?
 * @return SmartInsertResult
 */
fun StringBuilder.smartInsert(pos: Int, p: String, insertedPos: Int? = null) : SmartInsertResult {
    val min = this.indexOf(pos - 1, true) { it.`is`(' ') } + 1
    val max = this.indexOf(pos) { it.`is`(' ') }
    var tempInsertedPos: Int? = null

    val len = p.length

    if (max - min >= len) {
        for (index in 0 until len) {
            this[max - len + index] = p[index]
        }
        insertedPos?.let { tempInsertedPos = max - len }

        return SmartInsertResult(this, tempInsertedPos, 0)
    } else {
        for (index in 0 until max - min) {
            this[min + index] = p[index]
        }

        insertedPos?.let { tempInsertedPos = min }
        return SmartInsertResult(
                this.insert(max, p.drop(max - min)),
                tempInsertedPos,
                len - max + min
        )
    }
}

/**
 * Compare to end range
 * @receiver StringBuilder
 * @param str String
 * @param end Int
 * @return Pair<Int, Int>
 */
fun StringBuilder.compareTo(str: String, end: Int) : Pair<Int, Int> {
    var overlapNum = 0
    for (index in 0 until end) {
        val compare1 = this[index].toLowerCase()
        val compare2 = str[index].toLowerCase()

        if (compare1 != compare2) {
            overlapNum = index
            return Pair(overlapNum, compare1.compareTo(compare2))
        }
    }
    overlapNum = end
    return Pair(overlapNum, 0)
}

/**
 * Find parameter match index from start
 * @receiver StringBuilder
 * @param start Int
 * @return Int
 */
fun StringBuilder.findParMatch(start: Int) : Int {
    var parLevel = 1
    var result = 0

    for (index in start + 1 until length) {
        when (this[index]) {
            '{' -> ++parLevel
            '}' -> --parLevel
        }
        if (parLevel == 0) {
            result = index + 1
        }
    }

    return result
}

/**
 * Find index by [f]
 * @receiver StringBuilder
 * @param f Function0<Boolean>
 * @param start Int
 * @param downTo Boolean
 * @return Int
 */
fun StringBuilder.indexOf(start: Int, downTo: Boolean = false, f: (Char) -> Boolean) : Int {
    var result = 0
    if (downTo) {
        for (i in start downTo 0) {
            if (f(this[i])) continue
            else {
                result = i
                break
            }

        }
    } else {
        for (i in start until this.length) {
            if (f(this[i])) continue
            else {
                result = i
                break
            }
        }
    }
    return result
}

/**
 * Insert String value with replace count
 * @receiver StringBuilder
 * @param start Int
 * @param replaceCount Int
 * @param str String
 * @return StringBuilder
 */
fun StringBuilder.insert(start: Int, replaceCount: Int, str: String) : StringBuilder {
    val builder = this
    val result = StringBuilder()

    require(start >= 0 && start <= this.length) { "Start must greater than 0, (start: $start)" }

    if (start == 0) {
        result.append(str)
        if (replaceCount <= this.length)
            result.append(this.drop(replaceCount))
    }
    else if (start in 1 until this.length) {
        val temp = StringBuilder()
        for (count in this.indices) {
            if (count >= start) temp.append(builder[count])
            else result.append(builder[count])
        }

        if (result.lastIndex == this.lastIndex)
            result.append(str)
        else {
            result.append(str)
            if (start + replaceCount < this.length) {
                result.append(temp.drop(replaceCount))
            }
        }
    } else result.append(this).append(str)

    return result
}

/**
 * append element to string instance
 * @receiver String
 * @param element T
 * @return String
 */
fun <T> String.append(element: T) : String = StringBuilder(this).append(element).toString()

fun String.remove(index: Int, count: Int) : String {
    val result = StringBuilder(this)
    for (c in 0 until count)
        result.deleteCharAt(index)
    return result.toString()
}

/**
 * Erase specific index with count
 * @receiver StringBuilder
 * @param index Int
 * @param count Int
 * @return StringBuilder
 */
fun StringBuilder.erase(index: Int, count: Int) : StringBuilder {
    var result = this
    for (c in 0 until count) {
        result = result.deleteCharAt(index)
    }
    return result
}