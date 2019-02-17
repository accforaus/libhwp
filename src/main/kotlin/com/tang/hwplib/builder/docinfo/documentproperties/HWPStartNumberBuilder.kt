package com.tang.hwplib.builder.docinfo.documentproperties

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.documentproperties.HWPStartNumber

class HWPStartNumberBuilder : HWPBuilder<HWPStartNumber> {
    private val startNumber : HWPStartNumber = HWPStartNumber.build()

    fun setPage(page: Int) : HWPStartNumberBuilder = this.apply {
        startNumber.page = page
    }

    fun setFootnote(footnote: Int) : HWPStartNumberBuilder = this.apply {
        startNumber.footnote = footnote
    }

    fun setEndnote(endnote: Int) : HWPStartNumberBuilder = this.apply {
        startNumber.endnote = endnote
    }

    fun setPicture(picture: Int) : HWPStartNumberBuilder = this.apply {
        startNumber.picture = picture
    }

    fun setTable(table: Int) : HWPStartNumberBuilder = this.apply {
        startNumber.table = table
    }

    fun setEquation(equation: Int) : HWPStartNumberBuilder = this.apply {
        startNumber.equation = equation
    }

    override fun build(): HWPStartNumber = startNumber
}