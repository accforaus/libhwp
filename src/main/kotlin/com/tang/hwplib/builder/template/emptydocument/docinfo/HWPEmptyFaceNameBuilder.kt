package com.tang.hwplib.builder.template.emptydocument.docinfo

import com.tang.hwplib.builder.docinfo.HWPFaceNameBuilder
import com.tang.hwplib.builder.docinfo.facename.HWPFaceNamePropertyBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.HWPFaceName
import com.tang.hwplib.objects.docinfo.facename.HWPFaceNameEnum

class HWPEmptyFaceNameBuilder(val docInfo: HWPDocInfo) {
    fun build() {
        fun addDotum() : HWPFaceNameBuilder = HWPFaceNameBuilder()
                .setProperty(HWPFaceNamePropertyBuilder().setValue(33))
                .setName("함초롬돋움")
                .setBaseFontName("HCR Dotum")
        fun addBatang() : HWPFaceNameBuilder = HWPFaceNameBuilder()
                .setProperty(HWPFaceNamePropertyBuilder().setValue(33))
                .setName("함초롱바탕")
                .setBaseFontName("HCR Batang")

        docInfo.run {
            hangulFaceNameList = arrayListOf(addDotum().build(), addBatang().build())
            englishFaceNameList = arrayListOf(addDotum().build(), addBatang().build())
            hanjaFaceNameList = arrayListOf(addDotum().build(), addBatang().build())
            japaneseFaceNameList = arrayListOf(addDotum().build(), addBatang().build())
            etcFaceNameList = arrayListOf(addDotum().build(), addBatang().build())
            symbolFaceNameList = arrayListOf(addDotum().build(), addBatang().build())
            userFaceNameList = arrayListOf(addDotum().build(), addBatang().build())
        }
    }
}