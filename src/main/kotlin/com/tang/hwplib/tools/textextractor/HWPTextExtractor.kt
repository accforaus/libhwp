package com.tang.hwplib.tools.textextractor

import com.tang.hwplib.objects.HWPDocument
import com.tang.hwplib.objects.bodytext.control.HWPControl
import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControl
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphList
import com.tang.hwplib.objects.bodytext.paragraph.text.HWPCharNormal
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.HWPParaShape
import com.tang.hwplib.objects.docinfo.parashape.HWPParaHeadShape

fun extractText(hwp: HWPDocument) : String = StringBuilder().run {
    for (section in hwp.bodyText.sectionList) {
        for (paragraph in section.paragraphList) {
            append(extractParagraph(paragraph, hwp.docInfo))
        }
    }
    toString()
}

fun extractParagraph(paragraph: HWPParagraph, docInfo: HWPDocInfo) : String {
    fun extractText() : String = StringBuilder().let {
        paragraph.text?.run {
            for (char in this.charList) {
                when (char) {
                    is HWPCharNormal -> {
                        it.append(char.char)
                    }
                }
            }
        }
        it.toString()
    }
    val builder: StringBuilder = StringBuilder()
    val paraShapeID: Int = paragraph.header.paraShapeId
    val paraShape: HWPParaShape = docInfo.paraShapeList[paraShapeID]
    val paraHeadShape: HWPParaHeadShape = paraShape.property1.getParaHeadShape()
    when (paraHeadShape) {
        HWPParaHeadShape.Bullet -> {
            val bulletIndex: Int = paraShape.paraHeadId
            builder.append(docInfo.bulletList[bulletIndex].bulletChar)
        }
        HWPParaHeadShape.Numbering -> {
            val numberingIndex: Int = paraShape.paraHeadId
        }
        HWPParaHeadShape.Outline, HWPParaHeadShape.None -> {

        }
    }
    builder.append("\n").append(extractText())
    return builder.toString()
}

fun extractParagraphList(paragraphList: HWPParagraphList, docInfo: HWPDocInfo) : String {
    val builder: StringBuilder = StringBuilder()
    for (paragraph in paragraphList)
        builder.append(extractParagraph(paragraph, docInfo))
    return builder.toString()
}

fun extractControl(control: HWPControl) : String {
    return String()
}

fun extractGsoControl(control: HWPGsoControl) : String {
    when (control) {

    }
    return String()
}