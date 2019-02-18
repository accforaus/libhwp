package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.bodytext.paragraph.HWPParagraphListBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlFootnote
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderFootnote
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPNumberShape

class HWPControlFootnoteBuilder : HWPControlBuilder() {
    private val control : HWPControlFootnote = HWPControlFootnote.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderFootnoteBuilder) : HWPControlFootnoteBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    fun setListHeader(listHeaderBuilder: ListHeaderForFootnoteEndnoteBuilder) : HWPControlFootnoteBuilder = this.apply {
        control.listHeader = listHeaderBuilder.build()
    }

    fun setParagraphList(paragraphListBuilder: HWPParagraphListBuilder) : HWPControlFootnoteBuilder = this.apply {
        control.paragraphList = paragraphListBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlFootnoteBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlFootnote = control
}

class HWPCtrlHeaderFootnoteBuilder : HWPBuilder<HWPCtrlHeaderFootnote> {
    private val header : HWPCtrlHeaderFootnote = HWPCtrlHeaderFootnote.build()

    fun setNumber(number: Long) : HWPCtrlHeaderFootnoteBuilder = this.apply {
        header.number = number
    }

    fun setBeforDecorationLetter(beforeDecorationLetter: String) : HWPCtrlHeaderFootnoteBuilder = this.apply {
        header.beforeDecorationLetter = beforeDecorationLetter
    }

    fun setAfterDecorationLetter(afterDecorationLetter: String) : HWPCtrlHeaderFootnoteBuilder = this.apply {
        header.afterDecorationLetter = afterDecorationLetter
    }

    fun setNumberShape(numberShape: HWPNumberShape) : HWPCtrlHeaderFootnoteBuilder = this.apply {
        header.numberShape = numberShape
    }

    override fun build(): HWPCtrlHeaderFootnote = header
}
