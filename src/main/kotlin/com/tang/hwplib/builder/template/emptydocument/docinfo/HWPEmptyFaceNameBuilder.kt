package com.tang.hwplib.builder.template.emptydocument.docinfo

import com.tang.hwplib.builder.docinfo.HWPFaceNameBuilder
import com.tang.hwplib.builder.docinfo.facename.HWPFaceNamePropertyBuilder
import com.tang.hwplib.builder.etc.HWPDocInfoBuilderType
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.HWPFaceName
import com.tang.hwplib.objects.docinfo.facename.HWPFaceNameEnum

class HWPEmptyFaceNameBuilder(val docInfo: HWPDocInfo) {
    private fun getBuilder() : HWPFaceNameBuilder = docInfo.builderFactory(HWPDocInfoBuilderType.All) as HWPFaceNameBuilder
    fun build() {
        fun addDotum(builder: HWPFaceNameBuilder) {
            builder.setProperty(HWPFaceNamePropertyBuilder().setValue(33))
                    .setName("함초롬돋움")
                    .setBaseFontName("HCR Dotum").build()
        }
        fun addBatang(builder: HWPFaceNameBuilder) {
            builder.setProperty(HWPFaceNamePropertyBuilder().setValue(33))
                    .setName("함초롱바탕")
                    .setBaseFontName("HCR Batang").build()
        }

        addDotum(getBuilder())
        addBatang(getBuilder())
    }
}