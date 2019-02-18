package com.tang.hwplib.builder.template.emptydocument.docinfo

import com.tang.hwplib.builder.docinfo.HWPBorderFillBuilder
import com.tang.hwplib.builder.docinfo.HWPBorderFillListBuilder
import com.tang.hwplib.builder.docinfo.borderfill.HWPBorderFillPropertyBuilder
import com.tang.hwplib.builder.docinfo.borderfill.HWPEachBorderBuilder
import com.tang.hwplib.builder.docinfo.borderfill.fillinfo.HWPFillInfoBuilder
import com.tang.hwplib.builder.docinfo.borderfill.fillinfo.HWPFillTypeBuilder
import com.tang.hwplib.builder.docinfo.borderfill.fillinfo.HWPPatternFillBuilder
import com.tang.hwplib.builder.etc.Color4ByteBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPBorderFill
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPatternType

class HWPEmptyBorderFillBuilder : HWPBuilder<ArrayList<HWPBorderFill>> {
    private fun buildBorderFills(): HWPBorderFillListBuilder = HWPBorderFillListBuilder()
            .addBorderFill(
            HWPBorderFillBuilder().setProperty(HWPBorderFillPropertyBuilder().setValue(0))
                    .setDiagonalSort(HWPBorderType.Dash))
            .addBorderFill(
            HWPBorderFillBuilder().setProperty(HWPBorderFillPropertyBuilder().setValue(0))
                    .setDiagonalSort(HWPBorderType.Dash)
                    .setFillInfo(
                            HWPFillInfoBuilder().setFillType(HWPFillTypeBuilder().setValue(1))
                                    .setPatternFill(HWPPatternFillBuilder()
                                            .setBackColor(Color4ByteBuilder().setValue(-1))
                                            .setPatternColor(Color4ByteBuilder().setValue(-16777216))
                                            .setPatternType(HWPPatternType.None))
                    )
    )

    override fun build(): ArrayList<HWPBorderFill> = buildBorderFills().build()
}