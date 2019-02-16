package com.tang.hwplib.builder.docinfo.style

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.style.HWPStyleProperty
import com.tang.hwplib.objects.docinfo.style.HWPStyleSort

class HWPStylePropertyBuilder : HWPBuilder<HWPStyleProperty> {
    private val property: HWPStyleProperty = HWPStyleProperty.build(0)

    fun setStyleSort(styleSort: HWPStyleSort) : HWPStylePropertyBuilder = this.apply {
        property.setStyleSort(styleSort)
    }

    override fun build(): HWPStyleProperty = property
}