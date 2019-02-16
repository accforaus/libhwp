package com.tang.hwplib.builder.docinfo.numbering

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.numbering.HWPExtendNumbering
import com.tang.hwplib.objects.docinfo.numbering.HWPLevelNumbering

class HWPLevelNumberingBuilder : HWPBuilder<HWPLevelNumbering> {
    private val levelNumbering: HWPLevelNumbering = HWPLevelNumbering.build()

    fun setParagraphHeadInfo(paragraphHeadInfoBuilder: HWPParagraphHeadInfoBuilder) : HWPLevelNumberingBuilder = this.apply {
        levelNumbering.paragraphHeadInfo = paragraphHeadInfoBuilder.build()
    }

    fun setNumberFormat(numberFormat: String?) : HWPLevelNumberingBuilder = this.apply {
        levelNumbering.numberFormat = numberFormat
    }

    override fun build(): HWPLevelNumbering = levelNumbering
}

class HWPExtendNumberingBuilder : HWPBuilder<HWPExtendNumbering> {
    private val extendNumbering: HWPExtendNumbering = HWPExtendNumbering.build()

    fun setNumberFormat(numberFormat: String?) : HWPExtendNumberingBuilder = this.apply {
        extendNumbering.numberFormat = numberFormat
    }

    override fun build(): HWPExtendNumbering = extendNumbering
}