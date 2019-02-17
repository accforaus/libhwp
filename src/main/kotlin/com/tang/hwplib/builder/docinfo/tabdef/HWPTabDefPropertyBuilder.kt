package com.tang.hwplib.builder.docinfo.tabdef

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.tabdef.HWPTabDefProperty

class HWPTabDefPropertyBuilder : HWPBuilder<HWPTabDefProperty> {
    private val property: HWPTabDefProperty = HWPTabDefProperty.build()

    fun setValue(value: Long) : HWPTabDefPropertyBuilder = this.apply {
        property.value = value
    }

    fun setAutoTabAtParagraphLeftEnd(autoTabAtParagraphLeftEnd: Boolean) : HWPTabDefPropertyBuilder = this.apply {
        property.setAutoTabAtParagraphLeftEnd(autoTabAtParagraphLeftEnd)
    }

    fun setAutoTabAtParagraphRightEnd(autoTabAtParagraphRightEnd: Boolean) : HWPTabDefPropertyBuilder = this.apply {
        property.setAutoTabAtParagraphRightEnd(autoTabAtParagraphRightEnd)
    }

    override fun build(): HWPTabDefProperty = property
}