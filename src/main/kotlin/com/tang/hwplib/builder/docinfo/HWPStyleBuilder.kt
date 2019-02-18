package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.docinfo.style.HWPStylePropertyBuilder
import com.tang.hwplib.objects.docinfo.HWPStyle
import com.tang.hwplib.objects.docinfo.style.HWPStyleProperty
import com.tang.hwplib.objects.docinfo.style.HWPStyleSort
import com.tang.hwplib.util.exceptions.HWPBuildException

class HWPStyleBuilder : HWPBuilder<HWPStyle> {
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

    fun setCharShapeID(charShapeID: Int) : HWPStyleBuilder = this.apply {
        style.charShapeId = charShapeID
    }

    override fun build(): HWPStyle {
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
        return style
    }
}

class HWPStyleListBuilder : HWPBuilder<ArrayList<HWPStyle>> {
    private val styleList : ArrayList<HWPStyle> = ArrayList()

    fun addStyle(styleBuilder: HWPStyleBuilder) : HWPStyleListBuilder = this.apply {
        styleList.add(styleBuilder.build())
    }

    override fun build(): ArrayList<HWPStyle> = styleList
}