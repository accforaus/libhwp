package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlIndexMark
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderIndexMark

class HWPControlIndexMarkBuilder : HWPControlBuilder<HWPControlIndexMark> {
    private val control : HWPControlIndexMark = HWPControlIndexMark.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderIndexMarkBuilder) : HWPControlIndexMarkBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlIndexMarkBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlIndexMark = control
}

class HWPCtrlHeaderIndexMarkBuilder : HWPBuilder<HWPCtrlHeaderIndexMark> {
    private val header : HWPCtrlHeaderIndexMark = HWPCtrlHeaderIndexMark.build()

    fun setKeyword1(keyword1: String) : HWPCtrlHeaderIndexMarkBuilder = this.apply {
        header.keyword1 = keyword1
    }

    fun setKeyword2(keyword2: String) : HWPCtrlHeaderIndexMarkBuilder = this.apply {
        header.keyword2 = keyword2
    }

    override fun build(): HWPCtrlHeaderIndexMark = header
}