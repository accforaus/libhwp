package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.docinfo.style.HWPStylePropertyBuilder
import com.tang.hwplib.copyto.docinfo.IDMappingTypes
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.HWPStyle
import com.tang.hwplib.objects.docinfo.style.HWPStyleProperty
import com.tang.hwplib.objects.docinfo.style.HWPStyleSort
import com.tang.hwplib.util.exceptions.HWPBuildException

class HWPStyleBuilder(private val docInfo : HWPDocInfo) : HWPDocInfoBuilder() {
    private val style: HWPStyle = HWPStyle.build()

    fun setHangulName(hangulName: String?) : HWPStyleBuilder = this.apply {
        style.hangulName = hangulName
    }

    fun setEnglishName(englishName: String?) : HWPStyleBuilder = this.apply {
        style.englishName = englishName
    }

    fun setProperty(propertyBuilder: HWPStylePropertyBuilder) : HWPStyleBuilder = this.apply {
        style.property = propertyBuilder.build()
    }

    fun setNextStyleID(nextStyleID: Short) : HWPStyleBuilder = this.apply {
        style.nextStyleId = nextStyleID
    }

    fun setLanguageID(languageID: Short) : HWPStyleBuilder = this.apply {
        style.languageId = languageID
    }

    fun setParaShapeId(paraShapeID: Int) : HWPStyleBuilder = this.apply {
        style.paraShapeId = paraShapeID
    }

    fun setParaShapeId(paraShapeBuilder: HWPParaShapeBuilder) : HWPStyleBuilder = this.apply {
        style.paraShapeId = paraShapeBuilder.proceed()
    }

    fun setCharShapeID(charShapeID: Int) : HWPStyleBuilder = this.apply {
        style.charShapeId = charShapeID
    }

    fun setCharShapeID(charShapeBuilder: HWPCharShapeBuilder) : HWPStyleBuilder = this.apply {
        style.charShapeId = charShapeBuilder.proceed()
    }

    fun proceed() : Int = build().run {
        docInfo.styleList.size - 1
    }

    fun verify() : Boolean {
        style.run {
            when (property.getStyleSort()) {
                HWPStyleSort.ParaStyle -> {
                    if (paraShapeId < 0)
                        throw HWPBuildException("Style Sort ${HWPStyleSort.ParaStyle}: Style must have paragraph shape ID")
                }
                HWPStyleSort.CharStyle -> {
                    if (charShapeId < 0)
                        throw HWPBuildException("Style Sort ${HWPStyleSort.CharStyle}: Style must have character shape ID")
                }
            }
        }
        return true
    }

    override fun build(): HWPStyle = style.apply {
        if (docInfo.styleList.size != 0)
            style.nextStyleId = (docInfo.styleList.size - 1).toShort()
        docInfo.styleList.add(this)
        docInfo.updateIDMappings(IDMappingTypes.STYLE)
    }
}

class HWPStyleListBuilder : HWPBuilder<ArrayList<HWPStyle>> {
    private val styleList : ArrayList<HWPStyle> = ArrayList()

    fun addStyle(styleBuilder: HWPStyleBuilder) : HWPStyleListBuilder = this.apply {
        styleList.add(styleBuilder.build())
    }

    override fun build(): ArrayList<HWPStyle> = styleList
}