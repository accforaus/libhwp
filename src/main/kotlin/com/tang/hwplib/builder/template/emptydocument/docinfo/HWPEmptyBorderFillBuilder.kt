package com.tang.hwplib.builder.template.emptydocument.docinfo

import com.tang.hwplib.builder.docinfo.HWPBorderFillBuilder
import com.tang.hwplib.builder.docinfo.HWPBorderFillListBuilder
import com.tang.hwplib.builder.docinfo.borderfill.HWPBorderFillPropertyBuilder
import com.tang.hwplib.builder.docinfo.borderfill.HWPEachBorderBuilder
import com.tang.hwplib.builder.docinfo.borderfill.fillinfo.HWPFillInfoBuilder
import com.tang.hwplib.builder.docinfo.borderfill.fillinfo.HWPFillTypeBuilder
import com.tang.hwplib.builder.docinfo.borderfill.fillinfo.HWPPatternFillBuilder
import com.tang.hwplib.builder.etc.Color4ByteBuilder
import com.tang.hwplib.builder.etc.HWPDocInfoBuilderType
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPBorderFill
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPatternType

class HWPEmptyBorderFillBuilder {
    private fun getBuilder(docInfo: HWPDocInfo) : HWPBorderFillBuilder = docInfo.builderFactory(HWPDocInfoBuilderType.BorderFill) as HWPBorderFillBuilder

    fun build(docInfo: HWPDocInfo) {
        getBuilder(docInfo).setProperty(HWPBorderFillPropertyBuilder().setValue(0)).setDiagonalSort(HWPBorderType.Dash)
                .build()
        getBuilder(docInfo).setProperty(HWPBorderFillPropertyBuilder().setValue(0))
                .setDiagonalSort(HWPBorderType.Dash)
                .setFillInfo(HWPFillInfoBuilder(docInfo).setFillType(HWPFillTypeBuilder().setValue(1))
                                .setPatternFill(HWPPatternFillBuilder()
                                        .setBackColor(Color4ByteBuilder().setValue(-1))
                                        .setPatternColor(Color4ByteBuilder().setValue(-16777216))
                                        .setPatternType(HWPPatternType.None)))
                .build()
    }
}