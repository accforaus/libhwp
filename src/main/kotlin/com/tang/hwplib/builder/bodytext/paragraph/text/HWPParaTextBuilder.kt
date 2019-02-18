package com.tang.hwplib.builder.bodytext.paragraph.text

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControl
import com.tang.hwplib.objects.bodytext.control.HWPControlColumnDefine
import com.tang.hwplib.objects.bodytext.control.HWPControlSectionDefine
import com.tang.hwplib.objects.bodytext.paragraph.text.HWPParaText

class HWPParaTextBuilder : HWPBuilder<HWPParaText> {
    private val text: HWPParaText = HWPParaText()

    fun addExtendChar(control: HWPControl) : HWPParaTextBuilder = this.apply {
        text.addExtendCharByControl(control)
    }

    override fun build(): HWPParaText = text
}