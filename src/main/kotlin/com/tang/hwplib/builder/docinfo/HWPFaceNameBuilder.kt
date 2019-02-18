package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.docinfo.facename.HWPFaceNamePropertyBuilder
import com.tang.hwplib.builder.docinfo.facename.HWPFontTypeInfoBuilder
import com.tang.hwplib.copyto.docinfo.IDMappingTypes
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.HWPFaceName
import com.tang.hwplib.objects.docinfo.facename.HWPFaceNameEnum
import com.tang.hwplib.objects.docinfo.facename.HWPFontType
import com.tang.hwplib.util.exceptions.HWPBuildException
import kotlin.math.acos

class HWPFaceNameBuilder(private val docInfo: HWPDocInfo, private val type: HWPFaceNameEnum) : HWPDocInfoBuilder() {
    class HWPFaceNameBuildException(msg: String) : HWPBuildException(msg)

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

    private fun verify() : Boolean {
        faceName.run {
            if (property.hasBaseFont())
                if (baseFontName == null) throw HWPFaceNameBuildException("Property has base font: BaseName must be not null")
            if (property.hasSubstituteFont()) {
                if (substituteFontType == null) throw HWPFaceNameBuildException("Property has substitute font name: SubstituteFontType must be not null")
                if (substituteFontName == null) throw HWPFaceNameBuildException("Property has substitute font name: SubstituteFontName must be not null")
            }
        }
        return true
    }

    fun proceed() : Int = build().run {
        when (type) {
            HWPFaceNameEnum.HANGUL -> return docInfo.hangulFaceNameList.size - 1
            HWPFaceNameEnum.ENGLISH -> return docInfo.englishFaceNameList.size - 1
            HWPFaceNameEnum.HANJA -> return docInfo.hanjaFaceNameList.size - 1
            HWPFaceNameEnum.JAPANESE -> return docInfo.japaneseFaceNameList.size - 1
            HWPFaceNameEnum.ETC -> return docInfo.etcFaceNameList.size - 1
            HWPFaceNameEnum.SYMBOL -> return docInfo.symbolFaceNameList.size - 1
            HWPFaceNameEnum.USER -> return docInfo.userFaceNameList.size - 1
            else -> return docInfo.hanjaFaceNameList.size - 1
        }
    }

    override fun build(): HWPFaceName = faceName.also { face ->
        if(verify()) {
            docInfo.run {
                type?.let {
                    when (it) {
                        HWPFaceNameEnum.HANGUL -> {
                            this.hangulFaceNameList.add(face)
                            this.updateIDMappings(IDMappingTypes.HANGUL_FACENAME)
                        }
                        HWPFaceNameEnum.ENGLISH -> {
                            this.englishFaceNameList.add(face)
                            this.updateIDMappings(IDMappingTypes.ENGLISH_FACENAME)
                        }
                        HWPFaceNameEnum.HANJA -> {
                            this.hanjaFaceNameList.add(face)
                            this.updateIDMappings(IDMappingTypes.HANJA_FACENAME)
                        }
                        HWPFaceNameEnum.JAPANESE -> {
                            this.japaneseFaceNameList.add(face)
                            this.updateIDMappings(IDMappingTypes.JAPANESE_FACENAME)
                        }
                        HWPFaceNameEnum.ETC -> {
                            this.etcFaceNameList.add(face)
                            this.updateIDMappings(IDMappingTypes.ETC_FACENAME)
                        }
                        HWPFaceNameEnum.SYMBOL -> {
                            this.symbolFaceNameList.add(face)
                            this.updateIDMappings(IDMappingTypes.SYMBOL_FACENAME)
                        }
                        HWPFaceNameEnum.USER -> {
                            this.userFaceNameList.add(face)
                            this.updateIDMappings(IDMappingTypes.USER_FACENAME)
                        }
                        HWPFaceNameEnum.ALL -> {
                            this.hangulFaceNameList.add(face)
                            this.updateIDMappings(IDMappingTypes.HANGUL_FACENAME)
                            this.englishFaceNameList.add(face)
                            this.updateIDMappings(IDMappingTypes.ENGLISH_FACENAME)
                            this.hanjaFaceNameList.add(face)
                            this.updateIDMappings(IDMappingTypes.HANJA_FACENAME)
                            this.japaneseFaceNameList.add(face)
                            this.updateIDMappings(IDMappingTypes.JAPANESE_FACENAME)
                            this.etcFaceNameList.add(face)
                            this.updateIDMappings(IDMappingTypes.ETC_FACENAME)
                            this.symbolFaceNameList.add(face)
                            this.updateIDMappings(IDMappingTypes.SYMBOL_FACENAME)
                            this.userFaceNameList.add(face)
                            this.updateIDMappings(IDMappingTypes.USER_FACENAME)
                        }
                    }
                }
            }
        }
    }
}