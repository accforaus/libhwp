package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.objects.bodytext.control.HWPControl
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph
import com.tang.hwplib.objects.bodytext.paragraph.text.HWPParaText

class HWPControlBuildFactory(private val paragraph: HWPParagraph) {
    private val controlList : ArrayList<HWPControl> = ArrayList()

    fun addControl(controlBuilder: HWPControlBuilder) : HWPControlBuildFactory = this.apply {
        controlList.add(controlBuilder.build())
    }

    fun build() : HWPControlBuildFactory = this.apply {
        fun addExtendChar(text: HWPParaText) {
            for (control in controlList)
                text.addExtendCharByControl(control)
        }
        if (paragraph.controlList == null) {
            paragraph.controlList = controlList
            if (paragraph.text == null)
                paragraph.text = HWPParaText()
            addExtendChar(paragraph.text!!)
        }
        else {
            for (control in controlList)
                paragraph.controlList?.add(control)
            addExtendChar(paragraph.text!!)
        }
    }
}