package com.tang.hwplib.builder.bodytext.paragraph.text

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlColumnDefine
import com.tang.hwplib.objects.bodytext.control.HWPControlSectionDefine
import com.tang.hwplib.objects.bodytext.paragraph.text.HWPParaText

class HWPParaTextBuilder : HWPBuilder<HWPParaText> {
    private val text: HWPParaText = HWPParaText()

    override fun build(): HWPParaText = text
}

internal fun buildEmptyParaText() : HWPParaText = HWPParaText().apply {
    addExtendCharByControl(HWPControlSectionDefine())
    addExtendCharByControl(HWPControlColumnDefine())
}