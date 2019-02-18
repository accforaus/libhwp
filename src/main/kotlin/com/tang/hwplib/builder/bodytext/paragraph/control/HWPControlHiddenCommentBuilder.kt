package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.bodytext.paragraph.HWPParagraphListBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.listheader.HWPListHeaderPropertyBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlHiddenComment
import com.tang.hwplib.objects.bodytext.control.hiddencomment.ListHeaderForHiddenComment

class HWPControlHiddenCommentBuilder : HWPControlBuilder() {
    private val control : HWPControlHiddenComment = HWPControlHiddenComment.build()

    fun setListHeader(listHeaderBuilder: ListHeaderForHiddenCommentBuilder) : HWPControlHiddenCommentBuilder = this.apply {
        control.listHeader = listHeaderBuilder.build()
    }

    fun setParagraphList(paragraphListBuilder: HWPParagraphListBuilder) : HWPControlHiddenCommentBuilder = this.apply {
        control.paragraphList = paragraphListBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlHiddenCommentBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlHiddenComment = control
}

class ListHeaderForHiddenCommentBuilder : HWPBuilder<ListHeaderForHiddenComment> {
    private val header : ListHeaderForHiddenComment = ListHeaderForHiddenComment.build()

    fun setParaCount(paraCount: Int) : ListHeaderForHiddenCommentBuilder = this.apply {
        header.paraCount = paraCount
    }

    fun setProperty(propertyBuilder: HWPListHeaderPropertyBuilder) : ListHeaderForHiddenCommentBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    override fun build(): ListHeaderForHiddenComment = header
}