package com.tang.hwplib.builder.docinfo.facename

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.facename.HWPFontTypeInfo

class HWPFontTypeInfoBuilder : HWPBuilder<HWPFontTypeInfo> {
    private val fontTypeInfo: HWPFontTypeInfo = HWPFontTypeInfo.build()

    fun setFontType(fontType: Short) : HWPFontTypeInfoBuilder = this.apply {
        fontTypeInfo.fontType = fontType
    }

    fun setSerifType(serifType: Short) : HWPFontTypeInfoBuilder = this.apply {
        fontTypeInfo.serifType = serifType
    }

    fun setThickness(thickness: Short) : HWPFontTypeInfoBuilder = this.apply {
        fontTypeInfo.thickness = thickness
    }

    fun setRatio(ratio: Short) : HWPFontTypeInfoBuilder = this.apply {
        fontTypeInfo.ratio = ratio
    }

    fun setContrast(contrast: Short) : HWPFontTypeInfoBuilder = this.apply {
        fontTypeInfo.contrast = contrast
    }

    fun setStrokeDeviation(strokeDeviation: Short) : HWPFontTypeInfoBuilder = this.apply {
        fontTypeInfo.strokeDeviation = strokeDeviation
    }

    fun setCharacterStrokeType(characterStrokeType: Short) : HWPFontTypeInfoBuilder = this.apply {
        fontTypeInfo.characterStrokeType = characterStrokeType
    }

    fun setCharacterShape(characterShape: Short) : HWPFontTypeInfoBuilder = this.apply {
        fontTypeInfo.characterShape = characterShape
    }

    fun setMiddleLine(middleLine: Short) : HWPFontTypeInfoBuilder = this.apply {
        fontTypeInfo.middleLine = middleLine
    }

    fun setXHeight(xHeight: Short) : HWPFontTypeInfoBuilder = this.apply {
        fontTypeInfo.xHeight = xHeight
    }

    override fun build(): HWPFontTypeInfo = fontTypeInfo
}