package com.tang.hwplib.tools.control.equation.extensions.utils


class FuncList {
    lateinit var hwp: String
    lateinit var tex: String

    fun compareTo(other: FuncList): Boolean {
        val n = hwp.compareTo(other.hwp, true)
        return if (n != 0) n < 0 else hwp < other.hwp
    }
}

internal var spcSize = spcHwp.size
internal var funcSize = funcHwp.size
internal var spcNextSize = spcHwpNext.size
internal var spcUniCodeSize = hwpUnicodeSpc.size
internal var remainSize = funcRemain.size
internal var hwpParOutSize = parameterWrap.size
internal var oneParSize = oneParameter.size
internal var twoParSize = twoParameter.size


internal val texFuncSorted = arrayListOf<String>()
internal val hwpParameterOutKeyword = arrayListOf<String>()
internal val funcSorted = arrayListOf<FuncList>()
internal val funcRemainSorted = arrayListOf<FuncList>()
internal lateinit var funcSorted_t: String
internal lateinit var funcRemainSorted_t: String
internal val func = arrayListOf<FuncList>()

fun initVariables() {
    texFuncSorted.clear()
    hwpParameterOutKeyword.clear()
    funcSorted.clear()
    funcRemainSorted.clear()
    funcSorted_t = ""
    funcRemainSorted_t = ""
    func.clear()

    for (index in 0 until funcSize) {
        funcTex[index] = funcTex[index].append(CLOSEPARTMP)
        funcSorted.push(FuncList().apply {
            hwp = funcHwp[index]
            tex = funcTex[index]
        })

        if (!funcTex[index][0].isAlphaBet()) {
            texFuncSorted.push(funcTex[index])

            val n = texFuncSorted.last().indexOf(" ")
            if (n != -1) {
                texFuncSorted[texFuncSorted.lastIndex] = texFuncSorted.last().remove(n, 1)
            }
        }
    }

    texFuncSorted.push("\\circ")

    texFuncSorted.sortWith(Comparator { s, s2 -> s.compareTo(s2, true) })

    for (index in 0 until hwpParOutSize) {
        hwpParameterOutKeyword.push(parameterWrap[index])
    }

    hwpParameterOutKeyword.sortWith(Comparator { o1, o2 -> o1.compareTo(o2, true) })

    for (index in 0 until  remainSize) {
        funcRemainSorted.push(FuncList().apply {
            hwp = funcRemain[index]
            tex = funcRemainNext[index]
        })
    }

    funcSorted.sortWith(Comparator { o1, o2 -> o1.hwp.compareTo(o2.hwp, true) })
    funcRemainSorted.sortWith(Comparator { o1, o2 -> o1.hwp.compareTo(o2.hwp, true) })
}