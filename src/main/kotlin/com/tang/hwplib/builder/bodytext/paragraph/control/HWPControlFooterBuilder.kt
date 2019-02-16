package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.bodytext.paragraph.HWPParagraphListBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.listheader.HWPListHeaderPropertyBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlFooter
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderFooter
import com.tang.hwplib.objects.bodytext.control.ctrlheader.header.HWPHeaderFooterApplyPage
import com.tang.hwplib.objects.bodytext.control.headerfooter.ListHeaderForHeaderFooter

class HWPControlFooterBuilder : HWPControlBuilder<HWPControlFooter> {
    private val control : HWPControlFooter = HWPControlFooter.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderFooterBuilder) : HWPControlFooterBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    fun setListHeader(listHeaderBuilder: ListHeaderForHeaderFooterBuilder) : HWPControlFooterBuilder = this.apply {
        control.listHeader = listHeaderBuilder.build()
    }

    fun setParagraphList(paragraphListBuilder: HWPParagraphListBuilder) : HWPControlFooterBuilder = this.apply {
        control.paragraphList = paragraphListBuilder.build()
    }

    override fun build(): HWPControlFooter = control
}

class HWPCtrlHeaderFooterBuilder : HWPBuilder<HWPCtrlHeaderFooter> {
    private val header : HWPCtrlHeaderFooter = HWPCtrlHeaderFooter.build()

    fun setApplyPage(applyPage: HWPHeaderFooterApplyPage) : HWPCtrlHeaderFooterBuilder = this.apply {
        header.applyPage = applyPage
    }

    fun setCreateIndex(createIndex: Int) : HWPCtrlHeaderFooterBuilder = this.apply {
        header.createIndex = createIndex
    }

    override fun build(): HWPCtrlHeaderFooter = header
}

class ListHeaderForHeaderFooterBuilder : HWPBuilder<ListHeaderForHeaderFooter> {
    private val header : ListHeaderForHeaderFooter = ListHeaderForHeaderFooter.build()

    fun setParaCount(paraCount: Int) : ListHeaderForHeaderFooterBuilder = this.apply {
        header.paraCount = paraCount
    }

    fun setProperty(propertyBuilder: HWPListHeaderPropertyBuilder) : ListHeaderForHeaderFooterBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    fun setTextWidth(textWidth: Long) : ListHeaderForHeaderFooterBuilder = this.apply {
        header.textWidth = textWidth
    }

    fun setTextHeight(textHeight: Long) : ListHeaderForHeaderFooterBuilder = this.apply {
        header.textHeight = textHeight
    }

    override fun build(): ListHeaderForHeaderFooter = header
}