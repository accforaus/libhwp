package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.bodytext.paragraph.HWPParagraphListBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlHeader
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderHeader
import com.tang.hwplib.objects.bodytext.control.ctrlheader.header.HWPHeaderFooterApplyPage

class HWPControlHeaderBuilder : HWPControlBuilder<HWPControlHeader> {
    private val control : HWPControlHeader = HWPControlHeader.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderHeaderBuilder) : HWPControlHeaderBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    fun setListHeader(listHeaderBuilder: ListHeaderForHeaderFooterBuilder) : HWPControlHeaderBuilder = this.apply {
        control.listHeader = listHeaderBuilder.build()
    }

    fun setParagraphList(paragraphListBuilder: HWPParagraphListBuilder) : HWPControlHeaderBuilder = this.apply {
        control.paragraphList = paragraphListBuilder.build()
    }

    override fun build(): HWPControlHeader = control
}

class HWPCtrlHeaderHeaderBuilder : HWPBuilder<HWPCtrlHeaderHeader> {
    private val header : HWPCtrlHeaderHeader = HWPCtrlHeaderHeader.build()

    fun setApplyPage(applyPage: HWPHeaderFooterApplyPage) : HWPCtrlHeaderHeaderBuilder = this.apply {
        header.applyPage = applyPage
    }

    fun setCreateIndex(createIndex: Int) : HWPCtrlHeaderHeaderBuilder = this.apply {
        header.createIndex = createIndex
    }

    override fun build(): HWPCtrlHeaderHeader = header
}