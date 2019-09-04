package com.tang.hwplib.tools.control.equation.extensions.engine.search

import com.tang.hwplib.tools.control.equation.extensions.utils.FuncList
import com.tang.hwplib.tools.control.equation.extensions.utils.compareTo
import com.tang.hwplib.tools.control.equation.extensions.utils.isLower
import com.tang.hwplib.tools.control.equation.extensions.utils.isUpper
import com.tang.hwplib.tools.control.equation.extensions.utils.texFuncSorted

/**
 * Binary Search for Tex Functions
 * @receiver StringBuilder
 * @return Boolean
 */
fun StringBuilder.bSearchTexFunc() : Boolean {
    var upper = texFuncSorted.size - 1
    var mid = 0
    var lower = 0

    while (true) {
        mid = (upper + lower) / 2
        val res = texFuncSorted[mid].compareTo(this.toString(), true)

        when {
            res == 0 -> return true
            res > 0 -> upper = mid - 1
            else -> lower = mid + 1
        }

        if (upper < lower) return false
    }
}

/**
 * Binary Search for find Alphabet
 * @receiver StringBuilder
 * @param start Int
 * @param ar ArrayList<FuncList>
 * @param num Int
 * @return Int
 */
fun StringBuilder.bSearchAlphabet(start: Int, ar: ArrayList<FuncList>, num: Int) : Int {
    var upper = num - 1
    var lower = 0
    var mid = 0
    val builder = this

    val src = StringBuilder(builder.drop(start).toString())

    while (true) {
        mid = (upper + lower) / 2

        var midVal = ar[mid].hwp

        var overlapNum: Int
        val resultOfSub = src.compareTo(midVal, midVal.length)
        overlapNum = resultOfSub.first
        var findRes = resultOfSub.second
        var direction = 0

        if (findRes == 0 || findRes != 0 && overlapNum >= 2) {
            val m = mid

            for (index in m + 1 until num) {
                val nextVal = ar[index].hwp
                var oNum = 0
                val findNextResult = src.compareTo(nextVal, nextVal.length)
                oNum = findNextResult.first
                val findNext = findNextResult.second

                if (oNum >= overlapNum) {
                    if (findNext == 0) {
                        findRes = 0
                        direction = 1
                        mid = index
                        midVal = nextVal
                    }
                } else if (oNum < overlapNum) break
            }

            if (findRes != 0) {
                for (index in m - 1 downTo 0) {
                    val prevVal = ar[index].hwp
                    var oNum = 0
                    val findPrevResult = src.compareTo(prevVal, prevVal.length)
                    oNum = findPrevResult.first
                    val findPrev = findPrevResult.second

                    if (findPrev == 0) {
                        findRes = 0
                        direction = -1
                        mid = index
                        midVal = prevVal
                    } else if (oNum < 2) break
                }
            }
        }

        when {
            findRes > 0 -> lower = mid + 1
            findRes < 0 -> upper = mid - 1
            else -> {
                if (direction != 0) {
                    val firstLatterMid = midVal[0]
                    val firstLatterSrc = builder[start]

                    if (firstLatterMid.isLower() && firstLatterSrc.isLower() ||
                            firstLatterMid.isUpper() || firstLatterSrc.isUpper()) return mid

                    val compVal = ar[mid - direction].hwp

                    if (midVal.compareTo(compVal, true) == 0) {
                        return if (direction < 0 && firstLatterSrc.isLower() ||
                                direction > 0 && firstLatterSrc.isUpper()) mid - direction
                        else
                            mid
                    }
                }
                return mid
            }
        }

        if (upper < lower) return -1
    }
}

/**
 * Binary Search for Out of Parameter
 * @receiver StringBuilder
 * @param start Int
 * @param ar ArrayList<String>
 * @return Int
 */
fun StringBuilder.bSearchOutParameter(start: Int, ar: ArrayList<String>) : Int {
    var upper = ar.size - 1
    var lower = 0
    var mid: Int

    val src = StringBuilder(this.drop(start).toString())

    while (true) {
        mid = (upper + lower) / 2

        var midVal = ar[mid]

        var overlapNum: Int
        val findCompareResult = src.compareTo(midVal, midVal.length)
        overlapNum = findCompareResult.first
        var findRes = findCompareResult.second
        var direction = 0

        if (findRes == 0 || findRes != 0 && overlapNum >= 2) {
            val tempMid = mid

            for (index in tempMid + 1 until ar.size) {
                val nextVal = ar[index]
                var oNum: Int
                val findNextResult = src.compareTo(nextVal, nextVal.length)
                oNum = findNextResult.first
                val findNext = findNextResult.second

                if (oNum >= overlapNum) {
                    if (findNext == 0) {
                        findRes = 0
                        direction = 1
                        mid = index
                        midVal = nextVal
                    }
                } else if (oNum < overlapNum) break
            }

            if (findRes != 0) {
                for (index in tempMid - 1 downTo 0) {
                    val prevVal = ar[index]
                    var oNum: Int
                    val findPrevResult = src.compareTo(prevVal, prevVal.length)
                    oNum = findPrevResult.first
                    val findPrev = findPrevResult.second

                    if (findPrev == 0) {
                        findRes = 0
                        direction = -1
                        mid = index
                        midVal = prevVal
                    } else if (oNum < 2) break
                }
            }
        }

        when {
            findRes > 0 -> lower = mid + 1
            findRes < 0 -> upper = mid - 1
            else -> {
                if (direction != 0) {
                    val firstLatterMid = midVal[0]
                    val firstLatterSrc = this[start]

                    if (firstLatterMid.isUpper() && firstLatterSrc.isUpper() ||
                            firstLatterMid.isLower() && firstLatterSrc.isLower()) return mid

                    val compVal = ar[mid - direction]

                    if (midVal.compareTo(compVal, true) == 0) {
                        return if (direction < 0 && firstLatterSrc.isLower() ||
                                direction > 0 && firstLatterSrc.isUpper()) mid - direction
                        else mid
                    }
                }
                return mid
            }
        }
        if (upper < lower) return -1
    }
}