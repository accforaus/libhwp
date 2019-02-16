package com.tang.hwplib.builder.bodytext.paragraph.header

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.paragraph.header.HWPDivideSort

class HWPDivideSortBuilder : HWPBuilder<HWPDivideSort> {
    private val divideSort: HWPDivideSort = HWPDivideSort.build()

    fun setDivideMultiColumn(divideMultiColumn: Boolean) : HWPDivideSortBuilder = this.apply {
        divideSort.setDivideMultiColumn(divideMultiColumn)
    }

    fun setDivideSection(divideSection: Boolean) : HWPDivideSortBuilder = this.apply {
        divideSort.setDivideSection(divideSection)
    }

    fun setDividePage(dividePage: Boolean) : HWPDivideSortBuilder = this.apply {
        divideSort.setDividePage(dividePage)
    }

    fun setDivideColumn(divideColumn: Boolean) : HWPDivideSortBuilder = this.apply {
        divideSort.setDivideColumn(divideColumn)
    }

    override fun build(): HWPDivideSort = divideSort
}