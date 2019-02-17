package com.tang.hwplib.builder.docinfo.parashape

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.parashape.*

class HWPParaShapeProperty1Builder : HWPBuilder<HWPParaShapeProperty1> {
    private val property: HWPParaShapeProperty1 = HWPParaShapeProperty1.build()

    fun setValue(value: Long) : HWPParaShapeProperty1Builder = this.apply {
        property.value = value
    }

    fun setLineSpaceSort(lineSpaceSort: HWPLineSpaceSort) : HWPParaShapeProperty1Builder = this.apply {
        property.setLineSpaceSort(lineSpaceSort)
    }

    fun setALignment(alignment: HWPAlignment) : HWPParaShapeProperty1Builder = this.apply {
        property.setAlignment(alignment)
    }

    fun setLineDivideForEnglish(lineDivideForEnglish: HWPLineDivideForEnglish) : HWPParaShapeProperty1Builder = this.apply {
        property.setLineDivideForEnglish(lineDivideForEnglish)
    }

    fun setLineDivideForHangul(lineDivideForHangul: HWPLineDivideForHangul) : HWPParaShapeProperty1Builder = this.apply {
        property.setLineDivideForHangul(lineDivideForHangul)
    }

    fun setUseGrid(useGrid: Boolean) : HWPParaShapeProperty1Builder = this.apply {
        property.setUseGrid(useGrid)
    }

    fun setMinimumSpace(minimumSpace: Byte) : HWPParaShapeProperty1Builder = this.apply {
        property.setMinimumSpace(minimumSpace)
    }

    fun setProtectLonger(protectLonger: Boolean) : HWPParaShapeProperty1Builder = this.apply {
        property.setProtectLonger(protectLonger)
    }

    fun setTogetherNextPara(togetherNextPara: Boolean) : HWPParaShapeProperty1Builder = this.apply {
        property.setTogetherNextPara(togetherNextPara)
    }

    fun setProtectPara(protectPara: Boolean) : HWPParaShapeProperty1Builder = this.apply {
        property.setProtectPara(protectPara)
    }

    fun setSplitPageBeforePara(splitPageBeforePara: Boolean) : HWPParaShapeProperty1Builder = this.apply {
        property.setSplitPageBeforePage(splitPageBeforePara)
    }

    fun setVerticalAlignment(verticalAlignment: HWPVerticalAlignment) : HWPParaShapeProperty1Builder = this.apply {
        property.setVerticalAlignment(verticalAlignment)
    }

    fun setLineHeightForFont(lineHeightForFont: Boolean) : HWPParaShapeProperty1Builder = this.apply {
        property.setLineHeightForFont(lineHeightForFont)
    }

    fun setParaHeadShape(paraHeadShape: HWPParaHeadShape) : HWPParaShapeProperty1Builder = this.apply {
        property.setParaHeadShape(paraHeadShape)
    }

    fun setParaLevel(paraLevel: Byte) : HWPParaShapeProperty1Builder = this.apply {
        property.setParaLevel(paraLevel)
    }

    fun setLinkBorder(linkBorder: Boolean) : HWPParaShapeProperty1Builder = this.apply {
        property.setLinkBorder(linkBorder)
    }

    fun setIgnoreParaMargin(ignoreParaMargin: Boolean) : HWPParaShapeProperty1Builder = this.apply {
        property.setIgnoreParaMargin(ignoreParaMargin)
    }

    fun setParaTailShape(paraTailShape: Boolean) : HWPParaShapeProperty1Builder = this.apply {
        property.setParaTailShape(paraTailShape)
    }

    override fun build(): HWPParaShapeProperty1 = property
}

class HWPParaShapeProperty2Builder : HWPBuilder<HWPParaShapeProperty2> {
    private val property: HWPParaShapeProperty2 = HWPParaShapeProperty2.build()

    fun setValue(value: Long) : HWPParaShapeProperty2Builder = this.apply {
        property.value = value
    }

    fun setInputSingleLine(inputSingleLine: Boolean) : HWPParaShapeProperty2Builder = this.apply {
        property.setInputSingleLine(inputSingleLine)
    }

    fun setAutoAdjustGapHangulEnglish(autoAdjustGapHangulEnglish: Boolean) : HWPParaShapeProperty2Builder = this.apply {
        property.setAutoAdjustGapHangulEnglish(autoAdjustGapHangulEnglish)
    }

    fun setAutoAdjustGapHangulNumber(autoAdjustHangulNumber: Boolean) : HWPParaShapeProperty2Builder = this.apply {
        property.setAutoAdjustGapHangulNumber(autoAdjustHangulNumber)
    }

    override fun build(): HWPParaShapeProperty2 = property
}

class HWPParaShapeProperty3Builder : HWPBuilder<HWPParaShapeProperty3> {
    private val property: HWPParaShapeProperty3 = HWPParaShapeProperty3.build(0)

    fun setValue(value: Long) : HWPParaShapeProperty3Builder = this.apply {
        property.value = value
    }

    fun setLineSpaceSort(lineSpaceSort: HWPLineSpaceSort) : HWPParaShapeProperty3Builder = this.apply {
        property.setLineSpaceSort(lineSpaceSort)
    }

    override fun build(): HWPParaShapeProperty3 = property
}