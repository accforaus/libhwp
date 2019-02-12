package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.objects.docinfo.HWPCharShape
import com.tang.hwplib.objects.docinfo.charshape.*
import com.tang.hwplib.objects.etc.Color4Byte

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