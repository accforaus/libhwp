package com.tang.hwplib.builder.docinfo.charshape

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.objects.docinfo.charshape.*

class HWPCharShapePropertyBuilder : HWPBuilder<HWPCharShapeProperty> {
    private val property: HWPCharShapeProperty = HWPCharShapeProperty.build()

    fun setValue(value: Long) : HWPCharShapePropertyBuilder = this.apply {
        property.value = value
    }

    fun setItalic(italic: Boolean) : HWPCharShapePropertyBuilder = this.apply {
        property.setItalic(italic)
    }

    fun setBold(bold: Boolean) : HWPCharShapePropertyBuilder = this.apply {
        property.setBold(bold)
    }

    fun setUnderLineSort(underLineSort: HWPUnderLineSort) : HWPCharShapePropertyBuilder = this.apply {
        property.setUnderLineSort(underLineSort)
    }

    fun setUnderLineShape(underLineShape: HWPBorderType) : HWPCharShapePropertyBuilder = this.apply {
        property.setUnderLineShape(underLineShape)
    }

    fun setOuterLineSort(outerLineSort: HWPOuterLineSort) : HWPCharShapePropertyBuilder = this.apply {
        property.setOuterLineSort(outerLineSort)
    }

    fun setShadowSort(shadowSort: HWPShadowSort) : HWPCharShapePropertyBuilder = this.apply {
        property.setShadowSort(shadowSort)
    }

    fun setEmboss(emboss: Boolean) : HWPCharShapePropertyBuilder = this.apply {
        property.setEmboss(emboss)
    }

    fun setEngrave(engrave: Boolean) : HWPCharShapePropertyBuilder = this.apply {
        property.setEngrave(engrave)
    }

    fun setSuperScript(superScript: Boolean) : HWPCharShapePropertyBuilder = this.apply {
        property.setSuperScript(superScript)
    }

    fun setSubScript(subScript: Boolean) : HWPCharShapePropertyBuilder = this.apply {
        property.setSubScript(subScript)
    }

    fun setStrikeLine(strikeLine: Boolean) : HWPCharShapePropertyBuilder = this.apply {
        property.setStrikeLine(strikeLine)
    }

    fun setEmphasisSort(emphasisSort: HWPEmphasisSort) : HWPCharShapePropertyBuilder = this.apply {
        property.setEmphasisSort(emphasisSort)
    }

    fun setUsingSpaceAppropriateForFont(usingSpaceAppropriateForFont: Boolean) : HWPCharShapePropertyBuilder = this.apply {
        property.setUsingSpaceAppropriateForFont(usingSpaceAppropriateForFont)
    }

    fun setStrikeLineShape(strikeLineShape: HWPBorderType) : HWPCharShapePropertyBuilder = this.apply {
        property.setStrikeLineShape(strikeLineShape)
    }

    fun setKerning(kerning: Boolean) : HWPCharShapePropertyBuilder = this.apply {
        property.setKerning(kerning)
    }

    override fun build(): HWPCharShapeProperty = property
}