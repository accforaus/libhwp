package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlAdditionalText
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderAdditionalText
import com.tang.hwplib.objects.bodytext.control.ctrlheader.additionaltext.HWPAdditionalTextPosition
import com.tang.hwplib.objects.docinfo.parashape.HWPAlignment

class HWPControlAdditionalTextBuilder : HWPControlBuilder<HWPControlAdditionalText> {
    private val control : HWPControlAdditionalText = HWPControlAdditionalText.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderAdditionalTextBuilder) : HWPControlAdditionalTextBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlAdditionalTextBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlAdditionalText = control
}

class HWPCtrlHeaderAdditionalTextBuilder : HWPBuilder<HWPCtrlHeaderAdditionalText> {
    private val header: HWPCtrlHeaderAdditionalText = HWPCtrlHeaderAdditionalText.build()

    fun setMainText(mainText: String?) : HWPCtrlHeaderAdditionalTextBuilder = this.apply {
        header.mainText = mainText
    }

    fun setSubText(subText: String?) : HWPCtrlHeaderAdditionalTextBuilder = this.apply {
        header.subText = subText
    }

    fun setPosition(position: HWPAdditionalTextPosition) : HWPCtrlHeaderAdditionalTextBuilder = this.apply {
        header.position = position
    }

    fun setFsizeratio(fsizeratio: Long) : HWPCtrlHeaderAdditionalTextBuilder = this.apply {
        header.fsizeratio = fsizeratio
    }

    fun setOption(option: Long) : HWPCtrlHeaderAdditionalTextBuilder = this.apply {
        header.option = option
    }

    fun setStyleID(styleID: Long) : HWPCtrlHeaderAdditionalTextBuilder = this.apply {
        header.styleId = styleID
    }

    fun setAlignment(alignment: HWPAlignment?) : HWPCtrlHeaderAdditionalTextBuilder = this.apply {
        header.alignment = alignment
    }

    override fun build(): HWPCtrlHeaderAdditionalText = header
}