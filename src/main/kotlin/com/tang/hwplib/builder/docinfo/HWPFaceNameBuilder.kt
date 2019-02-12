package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.HWPFaceName
import com.tang.hwplib.objects.docinfo.facename.HWPFaceNameEnum

internal fun buildEmptyFaceName(docInfo: HWPDocInfo) {
    docInfo.run {
        fun addFaceNames() {
            fun addDotum(faceName: HWPFaceName) {
                faceName.run {
                    property.value = 33
                    name = "함초롬돋움"
                    baseFontName = "HCR Dotum"
                }
            }
            fun addBatang(faceName: HWPFaceName) {
                faceName.run {
                    property.value = 33
                    name = "함초롬바탕"
                    baseFontName = "HCR Batang"
                }
            }
            addDotum(addNewFaceName(HWPFaceNameEnum.HANGUL))
            addBatang(addNewFaceName(HWPFaceNameEnum.HANGUL))
            addDotum(addNewFaceName(HWPFaceNameEnum.LATIN))
            addBatang(addNewFaceName(HWPFaceNameEnum.LATIN))
            addDotum(addNewFaceName(HWPFaceNameEnum.HANJA))
            addBatang(addNewFaceName(HWPFaceNameEnum.HANJA))
            addDotum(addNewFaceName(HWPFaceNameEnum.JAPANESE))
            addBatang(addNewFaceName(HWPFaceNameEnum.JAPANESE))
            addDotum(addNewFaceName(HWPFaceNameEnum.ETC))
            addBatang(addNewFaceName(HWPFaceNameEnum.ETC))
            addDotum(addNewFaceName(HWPFaceNameEnum.SYMBOL))
            addBatang(addNewFaceName(HWPFaceNameEnum.SYMBOL))
            addDotum(addNewFaceName(HWPFaceNameEnum.USER))
            addBatang(addNewFaceName(HWPFaceNameEnum.USER))
        }
        addFaceNames()
    }
}