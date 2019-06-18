package com.tang.hwplib.tools.textextractor

import com.tang.hwplib.objects.HWPDocument
import com.tang.hwplib.objects.bodytext.control.*
import com.tang.hwplib.objects.bodytext.control.gso.*
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPTextBox
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphList
import java.lang.StringBuilder

fun HWPDocument.getNormalString(extractType: HWPTextExtractType) : String = when (extractType) {
    HWPTextExtractType.All -> extractAll(this)
    HWPTextExtractType.Paragraph -> extractOnlyParagraph(this)
}

private fun extractOnlyParagraph(hwp: HWPDocument) : String = StringBuilder().apply {
    for (section in hwp.bodyText.sectionList) {
        for (paragraph in section.paragraphList)
            this.append(paragraph.getNormalString()).append("\n")
    }
}.toString()

private fun extractAll(hwp: HWPDocument) : String {
    fun extractInParagraphList(paragraphList: HWPParagraphList) : String {
        val innerBuilder = StringBuilder()
        for (paragraph in paragraphList) {
            innerBuilder.append(paragraph.getNormalString()).append("\n")
        }
        return innerBuilder.toString()
    }

    fun extractInTextBox(textBox: HWPTextBox?) : String = StringBuilder().apply {
        textBox?.let { append(extractInParagraphList(it.paragraphList)) } ?: append("")
    }.append("\n").toString()

    fun extractInGsoControl(control: HWPGsoControl) : String {
        val builder = StringBuilder()

        when (control) {
            is HWPControlEllipse -> builder.append(extractInTextBox(control.textBox))
            is HWPControlArc -> builder.append(extractInTextBox(control.textBox))
            is HWPControlPolygon -> builder.append(extractInTextBox(control.textBox))
            is HWPControlCurve -> builder.append(extractInTextBox(control.textBox))
            is HWPControlRectangle-> builder.append(extractInTextBox(control.textBox))
            is HWPControlContainer -> {
                for (child in control.childControlList)
                    builder.append(extractInGsoControl(child))
            }
        }
        return builder.toString()
    }

    fun extractInControl(controlList: ArrayList<HWPControl>) : String {
        val builder = StringBuilder()
        for (control in controlList) {
            when (control) {
                is HWPControlTable -> {
                    for (row in control.rowList) {
                        for (cell in row.cellList)
                            builder.append(extractInParagraphList(cell.paragraphList))
                    }
                }
                is HWPGsoControl -> builder.append(extractInGsoControl(control)).append("\n")
                is HWPControlEquation -> builder.append(control.eqEdit.script).append("\n")
                is HWPControlHeader -> builder.append(extractInParagraphList(control.paragraphList)).append("\n")
                is HWPControlFooter -> builder.append(extractInParagraphList(control.paragraphList)).append("\n")
                is HWPControlFootnote -> builder.append(extractInParagraphList(control.paragraphList)).append("\n")
                is HWPControlEndNote -> builder.append(extractInParagraphList(control.paragraphList)).append("\n")
                is HWPControlAdditionalText -> {
                    builder.append(control.getHeader().mainText)
                            .append("\n")
                            .append(control.getHeader().subText)
                            .append("\n")
                }
                is HWPControlHiddenComment -> builder.append(extractInParagraphList(control.paragraphList)).append("\n")
            }
        }
        return builder.toString()
    }

    val builder = StringBuilder()

    for (section in hwp.bodyText.sectionList) {
        for (paragraph in section.paragraphList) {
            builder.append(paragraph.getNormalString()).append("\n")
            paragraph.controlList?.run {
                builder.append(extractInControl(this))
            }
        }
    }

    return builder.toString()
}