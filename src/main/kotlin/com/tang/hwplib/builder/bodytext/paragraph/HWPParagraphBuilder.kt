package com.tang.hwplib.builder.bodytext.paragraph

import com.tang.hwplib.builder.bodytext.paragraph.control.buildEmptyColumnDefine
import com.tang.hwplib.builder.bodytext.paragraph.control.buildEmptySectionDefine
import com.tang.hwplib.objects.bodytext.control.HWPControl
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph

internal fun buildEmptyParagraph() : HWPParagraph {
    val paragraph: HWPParagraph = HWPParagraph()
    paragraph.run {
        header = buildEmptyParaHeader()
        paraCharShape = buildEmptyParaCharShape()
        lineSeg = buildEmptyParaLineSeg()
        text = buildEmptyParaText()
        controlList = arrayListOf(buildEmptySectionDefine(), buildEmptyColumnDefine())
    }
    return paragraph
}