package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.objects.docinfo.HWPBorderFill
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderFillProperty
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.objects.docinfo.borderfill.HWPEachBorder
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPFillInfo
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPFillType
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPatternFill
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPatternType
import com.tang.hwplib.objects.etc.Color4Byte

internal fun buildEmptyBorderFill() : ArrayList<HWPBorderFill> {
    val borderFillList: ArrayList<HWPBorderFill> = ArrayList()

    borderFillList.run {
        add(HWPBorderFill.build(
                property = HWPBorderFillProperty.build(0),
                leftBorder = HWPEachBorder.build(),
                rightBorder = HWPEachBorder.build(),
                topBorder = HWPEachBorder.build(),
                bottomBorder = HWPEachBorder.build(),
                diagonalSort = HWPBorderType.Dash))
        add(HWPBorderFill.build(
                property = HWPBorderFillProperty.build(0),
                diagonalSort = HWPBorderType.Dash,
                fillInfo = HWPFillInfo.build(
                        type = HWPFillType.build(1),
                        patternFill = HWPPatternFill.build(
                                backColor = Color4Byte.build(value = -1),
                                patternColor = Color4Byte.build(value = -16777216),
                                patternType = HWPPatternType.None
                        )
                )
        ))
    }

    return borderFillList
}