package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.docinfo.facename.HWPFaceNamePropertyBuilder
import com.tang.hwplib.builder.docinfo.facename.HWPFontTypeInfoBuilder
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.HWPFaceName
import com.tang.hwplib.objects.docinfo.facename.HWPFaceNameEnum
import com.tang.hwplib.objects.docinfo.facename.HWPFontType

class HWPFaceNameBuilder : HWPBuilder<HWPFaceName> {
    private val faceName: HWPFaceName = HWPFaceName.build()

    fun setProperty(propertyBuilder: HWPFaceNamePropertyBuilder) : HWPFaceNameBuilder = this.apply {
        faceName.property = propertyBuilder.build()
    }

    fun setName(name: String?) : HWPFaceNameBuilder = this.apply {
        faceName.name = name
    }

    fun setSubstituteFontType(substituteFontType: HWPFontType?) : HWPFaceNameBuilder = this.apply {
        faceName.substituteFontType = substituteFontType
    }

    fun setSubstituteFontName(substituteFontName: String?) : HWPFaceNameBuilder = this.apply {
        faceName.substituteFontName = substituteFontName
    }

    fun setFontTypeInfo(fontTypeInfoBuilder: HWPFontTypeInfoBuilder) : HWPFaceNameBuilder = this.apply {
        faceName.fontTypeInfo = fontTypeInfoBuilder.build()
    }

    fun setBaseFontName(baseFontName: String?) : HWPFaceNameBuilder = this.apply {
        faceName.baseFontName = baseFontName
    }

    override fun build(): HWPFaceName = faceName
}

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