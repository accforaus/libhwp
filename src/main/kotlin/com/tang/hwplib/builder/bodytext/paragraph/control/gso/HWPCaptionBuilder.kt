package com.tang.hwplib.builder.bodytext.paragraph.control.gso

import com.tang.hwplib.builder.bodytext.paragraph.HWPParagraphListBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.listheader.HWPListHeaderPropertyBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.gso.caption.HWPCaption
import com.tang.hwplib.objects.bodytext.control.gso.caption.HWPCaptionDirection
import com.tang.hwplib.objects.bodytext.control.gso.caption.ListHeaderCaptionProperty
import com.tang.hwplib.objects.bodytext.control.gso.caption.ListHeaderForHWPCaption

class HWPCaptionBuilder : HWPBuilder<HWPCaption> {
    private val caption : HWPCaption = HWPCaption.build()

    fun setListHeaderForCaption(listHeaderForCaptionBuilder: ListHeaderForHWPCaptionBuilder) : HWPCaptionBuilder = this.apply {
        caption.listHeaderForCaption = listHeaderForCaptionBuilder.build()
    }

    fun setParagraphList(paragraphListBuilder: HWPParagraphListBuilder) : HWPCaptionBuilder = this.apply {
        caption.paragraphList = paragraphListBuilder.build()
    }

    override fun build(): HWPCaption = caption
}

class ListHeaderForHWPCaptionBuilder : HWPBuilder<ListHeaderForHWPCaption> {
    private val header : ListHeaderForHWPCaption = ListHeaderForHWPCaption.build()

    fun setParaCount(paraCount: Int) : ListHeaderForHWPCaptionBuilder = this.apply {
        header.paraCount = paraCount
    }

    fun setProperty(propertyBuilder: HWPListHeaderPropertyBuilder) : ListHeaderForHWPCaptionBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    fun setCaptionProperty(captionPropertyBuilder: ListHeaderCaptionPropertyBuilder) : ListHeaderForHWPCaptionBuilder = this.apply {
        header.captionProperty = captionPropertyBuilder.build()
    }

    fun setCaptionWidth(captionWidth: Long) : ListHeaderForHWPCaptionBuilder = this.apply {
        header.captionWidth = captionWidth
    }

    fun setSpaceBetweenCaptionAndFrame(spaceBetweenCaptionAndFrame: Int) : ListHeaderForHWPCaptionBuilder = this.apply {
        header.spaceBetweenCaptionAndFrame = spaceBetweenCaptionAndFrame
    }

    fun setTextWidth(textWidth: Long) : ListHeaderForHWPCaptionBuilder = this.apply {
        header.textWidth = textWidth
    }

    override fun build(): ListHeaderForHWPCaption = header
}

class ListHeaderCaptionPropertyBuilder : HWPBuilder<ListHeaderCaptionProperty> {
    private val property : ListHeaderCaptionProperty = ListHeaderCaptionProperty.build()

    fun setDirection(direction : HWPCaptionDirection) : ListHeaderCaptionPropertyBuilder = this.apply {
        property.setDirection(direction)
    }

    fun setIncludeMargin(includeMargin: Boolean) : ListHeaderCaptionPropertyBuilder = this.apply {
        property.setIncludeMargin(includeMargin)
    }

    override fun build(): ListHeaderCaptionProperty = property
}