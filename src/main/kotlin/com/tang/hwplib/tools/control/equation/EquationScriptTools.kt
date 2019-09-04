package com.tang.hwplib.tools.control.equation

import com.tang.hwplib.objects.HWPDocument
import com.tang.hwplib.objects.bodytext.control.*
import com.tang.hwplib.objects.bodytext.control.equation.HWPEQEdit
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphList
import com.tang.hwplib.tools.control.equation.extensions.engine.toTex
import com.tang.hwplib.tools.control.equation.extensions.utils.initVariables
import java.util.*
import kotlin.collections.ArrayList

/**
 * HWP Equation text to normal string inHWPEQEdit
 * @receiver HWPEQEdit
 * @return String
 */
fun HWPEQEdit.toNormal() = buildString {
    if (script == null) return ""
    var result = this@toNormal.script!!
    val regex = Regex("""^[\n\r]+\.|[\r\n]+$""")
    if (result.contains("from")) result = result.split("from")[0]
    append(result.replace(regex, "").trim())
}

/**
 * Get Equation List in HWPDocument
 * @receiver HWPDocument
 * @return ArrayList<String>
 */
fun HWPDocument.getEquationList() : ArrayList<String> {
    val result = arrayListOf<String>()
    bodyText.sectionList[0].paragraphList.forEach { paragraph ->
        paragraph.controlList?.forEach { control -> getEquation(control, result) }
    }
    return result
}

/**
 * Get equation To Tex List
 * @receiver HWPDocument
 * @return List<String>
 */
fun HWPDocument.equationToTex() : List<String> {
    initVariables()
    return getEquationList().map { StringBuilder(it).toTex().toString() }
}

/**
 * Get Equation in each controls
 * @param control HWPControl
 * @param equations ArrayList<String>
 */
private fun getEquation(control: HWPControl, equations: ArrayList<String>) = when (control) {
    is HWPControlEquation -> equations.addUnit(control.eqEdit.toNormal())
    is HWPControlEndNote -> getEquation(control.paragraphList, equations)
    is HWPControlFootnote -> getEquation(control.paragraphList, equations)
    is HWPControlHeader -> getEquation(control.paragraphList, equations)
    is HWPControlFooter -> getEquation(control.paragraphList, equations)
    is HWPControlSectionDefine -> for (b in control.batangPageInfoList) getEquation(b.paragraphList, equations)
    is HWPControlTable -> {
        for (row in control.rowList)
            for (cell in row.cellList)
                getEquation(cell.paragraphList, equations)

    }
    else -> {}
}

/**
 * Get Equation in paragraph list
 * @param paragraphList HWPParagraphList
 * @param equations ArrayList<String>
 */
private fun getEquation(paragraphList: HWPParagraphList, equations: ArrayList<String>) {
    paragraphList.paragraphList.forEach { paragraph ->
        paragraph.controlList?.forEach { control -> getEquation(control, equations) }
    }
}

/**
 * Add ArrayList in any elements like [add] but it return [Unit] not [Boolean]
 * @receiver ArrayList<T>
 * @param element T
 * @return Unit
 */
private fun <T> ArrayList<T>.addUnit(element: T) : Unit { this.add(element) }