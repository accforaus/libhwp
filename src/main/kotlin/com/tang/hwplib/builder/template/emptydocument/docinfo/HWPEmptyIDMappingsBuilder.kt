package com.tang.hwplib.builder.template.emptydocument.docinfo

import com.tang.hwplib.builder.docinfo.HWPIDMappingsBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPIDMappings

class HWPEmptyIDMappingsBuilder : HWPBuilder<HWPIDMappings> {
    override fun build(): HWPIDMappings = HWPIDMappingsBuilder()
            .setHangulFaceNameCount(2)
            .setHanjaFaceNameCount(2)
            .setEnglishFaceNameCount(2)
            .setJapaneseFaceNameCount(2)
            .setEtcFaceNameCount(2)
            .setSymbolFaceNameCount(2)
            .setUserFaceNameCount(2)
            .setBorderFillCount(2)
            .setCharShapeCount(7)
            .setTabDefCount(3)
            .setNumberingCount(1)
            .setParaShapeCount(16)
            .setStyleCount(18)
            .build()
}