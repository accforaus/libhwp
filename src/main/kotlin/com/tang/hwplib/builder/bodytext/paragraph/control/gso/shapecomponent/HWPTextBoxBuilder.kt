package com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent

import com.tang.hwplib.builder.bodytext.paragraph.HWPParagraphListBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.listheader.HWPListHeaderPropertyBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPTextBox
import com.tang.hwplib.objects.bodytext.control.gso.textbox.ListHeaderForHWPTextBox

class HWPTextBoxBuilder : HWPBuilder<HWPTextBox> {
    private val text : HWPTextBox = HWPTextBox.build()

    fun setListHeader(listHeaderBuilder: ListHeaderForHWPTextBoxBuilder) : HWPTextBoxBuilder = this.apply {
        text.listHeader = listHeaderBuilder.build()
    }

    fun setParagraphList(paragraphListBuilder: HWPParagraphListBuilder) : HWPTextBoxBuilder = this.apply {
        text.paragraphList = paragraphListBuilder.build()
    }

    override fun build(): HWPTextBox = text
}

class ListHeaderForHWPTextBoxBuilder : HWPBuilder<ListHeaderForHWPTextBox> {
    private val header : ListHeaderForHWPTextBox = ListHeaderForHWPTextBox.build()

    fun setParaCount(paraCount: Int) : ListHeaderForHWPTextBoxBuilder = this.apply {
        header.paraCount = paraCount
    }

    fun setProperty(propertyBuilder: HWPListHeaderPropertyBuilder) : ListHeaderForHWPTextBoxBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    fun setLeftMargin(leftMargin: Int) : ListHeaderForHWPTextBoxBuilder = this.apply {
        header.leftMargin = leftMargin
    }

    fun setRightMargin(rightMargin: Int) : ListHeaderForHWPTextBoxBuilder = this.apply {
        header.rightMargin = rightMargin
    }

    fun setTopMargin(topMargin: Int) : ListHeaderForHWPTextBoxBuilder = this.apply {
        header.topMargin = topMargin
    }

    fun setBottomMargin(bottomMargin: Int) : ListHeaderForHWPTextBoxBuilder = this.apply {
        header.bottomMargin = bottomMargin
    }

    fun setTextWidth(textWidth: Long) : ListHeaderForHWPTextBoxBuilder = this.apply {
        header.textWidth = textWidth
    }

    fun setEditbleAtFormMode(editableAtFormMode: Boolean) : ListHeaderForHWPTextBoxBuilder = this.apply {
        header.editableAtFormMode = editableAtFormMode
    }

    fun setFieldName(fieldName: String) : ListHeaderForHWPTextBoxBuilder = this.apply {
        header.fieldName = fieldName
    }

    override fun build(): ListHeaderForHWPTextBox = header
}