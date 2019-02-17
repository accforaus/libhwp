package com.tang.hwplib.builder.docinfo.facename

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.facename.HWPFaceNameProperty

class HWPFaceNamePropertyBuilder : HWPBuilder<HWPFaceNameProperty> {
    private val property: HWPFaceNameProperty = HWPFaceNameProperty.build()

    fun setValue(value: Short) : HWPFaceNamePropertyBuilder = this.apply {
        property.value = value
    }

    fun setSubstituteFont(substituteFont: Boolean) : HWPFaceNamePropertyBuilder = this.apply {
        property.setSubstituteFont(substituteFont)
    }

    fun setFontInfo(fontInfo: Boolean) : HWPFaceNamePropertyBuilder = this.apply {
        property.setFontInfo(fontInfo)
    }

    fun setBaseFont(baseFont: Boolean) : HWPFaceNamePropertyBuilder = this.apply {
        property.setBaseFont(baseFont)
    }

    override fun build(): HWPFaceNameProperty = property
}