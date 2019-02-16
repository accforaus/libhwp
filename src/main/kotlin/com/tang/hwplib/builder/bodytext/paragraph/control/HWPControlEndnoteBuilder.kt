package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.bodytext.paragraph.HWPParagraphListBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.listheader.HWPListHeaderPropertyBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlEndNote
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderEndnote
import com.tang.hwplib.objects.bodytext.control.footnoteendnote.ListHeaderForFootnoteEndnote
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPNumberShape

class HWPControlEndnoteBuilder : HWPControlBuilder<HWPControlEndNote> {
    private val control : HWPControlEndNote = HWPControlEndNote.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderEndnoteBuilder) : HWPControlEndnoteBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    fun setListHeader(listHeaderBuilder: ListHeaderForFootnoteEndnoteBuilder) : HWPControlEndnoteBuilder = this.apply {
        control.listHeader = listHeaderBuilder.build()
    }

    fun setParagraphList(paragraphListBuilder: HWPParagraphListBuilder) : HWPControlEndnoteBuilder = this.apply {
        control.paragraphList = paragraphListBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlEndnoteBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlEndNote = control
}

class HWPCtrlHeaderEndnoteBuilder : HWPBuilder<HWPCtrlHeaderEndnote> {
    private val header : HWPCtrlHeaderEndnote = HWPCtrlHeaderEndnote.build()

    fun setNumber(number: Long) : HWPCtrlHeaderEndnoteBuilder = this.apply {
        header.number = number
    }

    fun setBeforDecorationLetter(beforeDecorationLetter: String) : HWPCtrlHeaderEndnoteBuilder = this.apply {
        header.beforeDecorationLetter = beforeDecorationLetter
    }

    fun setAfterDecorationLetter(afterDecorationLetter: String) : HWPCtrlHeaderEndnoteBuilder = this.apply {
        header.afterDecorationLetter = afterDecorationLetter
    }

    fun setNumberShape(numberShape: HWPNumberShape) : HWPCtrlHeaderEndnoteBuilder = this.apply {
        header.numberShape = numberShape
    }

    override fun build(): HWPCtrlHeaderEndnote = header
}

class ListHeaderForFootnoteEndnoteBuilder : HWPBuilder<ListHeaderForFootnoteEndnote> {
    private val header : ListHeaderForFootnoteEndnote = ListHeaderForFootnoteEndnote.build()

    fun setParaCount(paraCount: Int) : ListHeaderForFootnoteEndnoteBuilder = this.apply {
        header.paraCount = paraCount
    }

    fun setProperty(propertyBuilder: HWPListHeaderPropertyBuilder) : ListHeaderForFootnoteEndnoteBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    override fun build(): ListHeaderForFootnoteEndnote = header
}