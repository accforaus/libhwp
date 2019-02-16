package com.tang.hwplib.builder.docinfo

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

internal fun buildEmptyCharShapes() : ArrayList<HWPCharShape> {
    val charShapes: ArrayList<HWPCharShape> = ArrayList()

    charShapes.run {
        add(HWPCharShape.build(
                faceNameIds = HWPFaceNameIds.build(1),
                ratios = HWPRatios.build(100),
                charSpaces = HWPCharSpaces.build(0),
                relativeSizes = HWPRelativeSizes.build(100),
                charOffsets = HWPCharOffsets.build(0),
                baseSize = 1000,
                property = HWPCharShapeProperty.build(0),
                shadowGap1 = 10,
                shadowGap2 = 10,
                charColor = Color4Byte.build(value = 0),
                underLineColor = Color4Byte.build(value = 0),
                shadeColor = Color4Byte.build(value = -1),
                shadowColor = Color4Byte.build(value = 11711154),
                borderFillId = 2,
                strikeLineColor = Color4Byte.build(value = 0)
        ))
        add(HWPCharShape.build(
                faceNameIds = HWPFaceNameIds.build(0),
                ratios = HWPRatios.build(100),
                charSpaces = HWPCharSpaces.build(0),
                relativeSizes = HWPRelativeSizes.build(100),
                charOffsets = HWPCharOffsets.build(0),
                baseSize = 1000,
                property = HWPCharShapeProperty.build(0),
                shadowGap1 = 10,
                shadowGap2 = 10,
                charColor = Color4Byte.build(value = 0),
                underLineColor = Color4Byte.build(value = 0),
                shadeColor = Color4Byte.build(value = -1),
                shadowColor = Color4Byte.build(value = 11711154),
                borderFillId = 2,
                strikeLineColor = Color4Byte.build(value = 0)
        ))
        add(HWPCharShape.build(
                faceNameIds = HWPFaceNameIds.build(0),
                ratios = HWPRatios.build(100),
                charSpaces = HWPCharSpaces.build(0),
                relativeSizes = HWPRelativeSizes.build(100),
                charOffsets = HWPCharOffsets.build(0),
                baseSize = 900,
                property = HWPCharShapeProperty.build(0),
                shadowGap1 = 10,
                shadowGap2 = 10,
                charColor = Color4Byte.build(value = 0),
                underLineColor = Color4Byte.build(value = 0),
                shadeColor = Color4Byte.build(value = -1),
                shadowColor = Color4Byte.build(value = 11711154),
                borderFillId = 2,
                strikeLineColor = Color4Byte.build(value = 0)
        ))
        add(HWPCharShape.build(
                faceNameIds = HWPFaceNameIds.build(1),
                ratios = HWPRatios.build(100),
                charSpaces = HWPCharSpaces.build(0),
                relativeSizes = HWPRelativeSizes.build(100),
                charOffsets = HWPCharOffsets.build(0),
                baseSize = 900,
                property = HWPCharShapeProperty.build(0),
                shadowGap1 = 10,
                shadowGap2 = 10,
                charColor = Color4Byte.build(value = 0),
                underLineColor = Color4Byte.build(value = 0),
                shadeColor = Color4Byte.build(value = -1),
                shadowColor = Color4Byte.build(value = 11711154),
                borderFillId = 2,
                strikeLineColor = Color4Byte.build(value = 0)
        ))
        add(HWPCharShape.build(
                faceNameIds = HWPFaceNameIds.build(0),
                ratios = HWPRatios.build(100),
                charSpaces = HWPCharSpaces.build(-5),
                relativeSizes = HWPRelativeSizes.build(100),
                charOffsets = HWPCharOffsets.build(0),
                baseSize = 900,
                property = HWPCharShapeProperty.build(0),
                shadowGap1 = 10,
                shadowGap2 = 10,
                charColor = Color4Byte.build(value = 0),
                underLineColor = Color4Byte.build(value = 0),
                shadeColor = Color4Byte.build(value = -1),
                shadowColor = Color4Byte.build(value = 11711154),
                borderFillId = 2,
                strikeLineColor = Color4Byte.build(value = 0)
        ))
        add(HWPCharShape.build(
                faceNameIds = HWPFaceNameIds.build(0),
                ratios = HWPRatios.build(100),
                charSpaces = HWPCharSpaces.build(0),
                relativeSizes = HWPRelativeSizes.build(100),
                charOffsets = HWPCharOffsets.build(0),
                baseSize = 1600,
                property = HWPCharShapeProperty.build(0),
                shadowGap1 = 10,
                shadowGap2 = 10,
                charColor = Color4Byte.build(value = 11891758),
                underLineColor = Color4Byte.build(value = 0),
                shadeColor = Color4Byte.build(value = -1),
                shadowColor = Color4Byte.build(value = 11711154),
                borderFillId = 2,
                strikeLineColor = Color4Byte.build(value = 0)
        ))
        add(HWPCharShape.build(
                faceNameIds = HWPFaceNameIds.build(0),
                ratios = HWPRatios.build(100),
                charSpaces = HWPCharSpaces.build(0),
                relativeSizes = HWPRelativeSizes.build(100),
                charOffsets = HWPCharOffsets.build(0),
                baseSize = 1100,
                property = HWPCharShapeProperty.build(0),
                shadowGap1 = 10,
                shadowGap2 = 10,
                charColor = Color4Byte.build(value = 0),
                underLineColor = Color4Byte.build(value = 0),
                shadeColor = Color4Byte.build(value = -1),
                shadowColor = Color4Byte.build(value = 11711154),
                borderFillId = 2,
                strikeLineColor = Color4Byte.build(value = 0)
        ))
    }

    return charShapes
}