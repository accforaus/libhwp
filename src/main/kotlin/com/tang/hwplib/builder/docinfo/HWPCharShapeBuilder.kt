package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.bodytext.paragraph.charshape.HWPParaCharShapeBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.docinfo.charshape.*
import com.tang.hwplib.builder.etc.Color4ByteBuilder
import com.tang.hwplib.objects.docinfo.HWPCharShape
import com.tang.hwplib.objects.docinfo.charshape.*
import com.tang.hwplib.objects.etc.Color4Byte

class HWPCharShapeBuilder : HWPBuilder<HWPCharShape> {
    private val charShape: HWPCharShape = HWPCharShape.build()

    fun setFaceNameIDs(faceNameIdsBuilder: HWPFaceNameIDBuilder) : HWPCharShapeBuilder = this.apply {
        charShape.faceNameIds = faceNameIdsBuilder.build()
    }

    fun setRatios(ratiosBuilder: HWPRatiosBuilder) : HWPCharShapeBuilder = this.apply {
        charShape.ratios = ratiosBuilder.build()
    }

    fun setCharSpaces(charSpaceBuilder: HWPCharSpaceBuilder) : HWPCharShapeBuilder = this.apply {
        charShape.charSpaces = charSpaceBuilder.build()
    }

    fun setRelativeSizes(relativeSizes: HWPRelativeSizeBuilder) : HWPCharShapeBuilder = this.apply {
        charShape.relativeSizes = relativeSizes.build()
    }

    fun setCharOffsets(charOffsetBuilder: HWPCharOffsetsBuilder) : HWPCharShapeBuilder = this.apply {
        charShape.charOffsets = charOffsetBuilder.build()
    }

    fun setBaseSize(baseSize: Int) : HWPCharShapeBuilder = this.apply {
        charShape.baseSize = baseSize
    }

    fun setProperty(propertyBuilder: HWPCharShapePropertyBuilder) : HWPCharShapeBuilder = this.apply {
        charShape.property = propertyBuilder.build()
    }

    fun setShadowGap1(shadowGap1: Byte) : HWPCharShapeBuilder = this.apply {
        charShape.shadowGap1 = shadowGap1
    }

    fun setShadowGap2(shadowGap2: Byte) : HWPCharShapeBuilder = this.apply {
        charShape.shadowGap2 = shadowGap2
    }

    fun setCharColor(colorBuilder: Color4ByteBuilder) : HWPCharShapeBuilder = this.apply {
        charShape.charColor = colorBuilder.build()
    }

    fun setUnderLineColor(colorBuilder: Color4ByteBuilder) : HWPCharShapeBuilder = this.apply {
        charShape.underLineColor = colorBuilder.build()
    }

    fun setShadeColor(colorBuilder: Color4ByteBuilder) : HWPCharShapeBuilder = this.apply {
        charShape.shadeColor = colorBuilder.build()
    }

    fun setShadowColor(colorBuilder: Color4ByteBuilder) : HWPCharShapeBuilder = this.apply {
        charShape.shadowColor = colorBuilder.build()
    }

    fun setBorderFillID(borderFillID: Int) : HWPCharShapeBuilder = this.apply {
        charShape.borderFillId = borderFillID
    }

    fun setStrikeLineColor(colorBuilder: Color4ByteBuilder) : HWPCharShapeBuilder = this.apply {
        charShape.strikeLineColor = colorBuilder.build()
    }

    override fun build(): HWPCharShape = charShape
}

class HWPCharShapeListBuilder : HWPBuilder<ArrayList<HWPCharShape>> {
    private val charShapeList: ArrayList<HWPCharShape> = ArrayList()

    fun addCharShape(charShapeBuilder: HWPCharShapeBuilder) : HWPCharShapeListBuilder = this.apply {
        charShapeList.add(charShapeBuilder.build())
    }

    override fun build(): ArrayList<HWPCharShape> = charShapeList
}