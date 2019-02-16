package com.tang.hwplib.builder.docinfo.numbering

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.numbering.HWPParagraphAlignment
import com.tang.hwplib.objects.docinfo.numbering.HWPParagraphHeadInfo
import com.tang.hwplib.objects.docinfo.numbering.HWPParagraphHeadInfoProperty
import com.tang.hwplib.objects.docinfo.numbering.HWPValueType

class HWPParagraphHeadInfoPropertyBuilder : HWPBuilder<HWPParagraphHeadInfoProperty> {
    private val property: HWPParagraphHeadInfoProperty = HWPParagraphHeadInfoProperty.build()

    fun setParagraphAlignment(paragraphAlignment: HWPParagraphAlignment) : HWPParagraphHeadInfoPropertyBuilder = this.apply {
        property.setParagraphAlignment(paragraphAlignment)
    }

    fun setFollowStringWidth(followStringWith: Boolean) : HWPParagraphHeadInfoPropertyBuilder = this.apply {
        property.setFollowStringWidth(followStringWith)
    }

    fun setAutoIndent(autoIndent: Boolean) : HWPParagraphHeadInfoPropertyBuilder = this.apply {
        property.setAutoIndent(autoIndent)
    }

    fun setValueTypeForDistanceFromBody(valueTypeForDistanceFromBody: HWPValueType) : HWPParagraphHeadInfoPropertyBuilder = this.apply {
        property.setValueTypeForDistanceFromBody(valueTypeForDistanceFromBody)
    }

    override fun build(): HWPParagraphHeadInfoProperty = property
}

class HWPParagraphHeadInfoBuilder : HWPBuilder<HWPParagraphHeadInfo> {
    private val paragraphHeadInfo: HWPParagraphHeadInfo = HWPParagraphHeadInfo.build()

    fun setProperty(propertyBuilder: HWPParagraphHeadInfoPropertyBuilder) : HWPParagraphHeadInfoBuilder = this.apply {
        paragraphHeadInfo.property = propertyBuilder.build()
    }

    fun setCorrectionValueForWidth(correctionValueForWidth: Short) : HWPParagraphHeadInfoBuilder = this.apply {
        paragraphHeadInfo.correctionValueForWidth = correctionValueForWidth
    }

    fun setDistanceFromBody(distanceFromBody: Short) : HWPParagraphHeadInfoBuilder = this.apply {
        paragraphHeadInfo.distanceFromBody = distanceFromBody
    }

    fun setCharShapeID(charShapeID: Long) : HWPParagraphHeadInfoBuilder = this.apply {
        paragraphHeadInfo.charShapeID = charShapeID
    }

    override fun build(): HWPParagraphHeadInfo = paragraphHeadInfo
}