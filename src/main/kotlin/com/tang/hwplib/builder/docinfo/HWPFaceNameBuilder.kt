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