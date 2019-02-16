package com.tang.hwplib.builder.bodytext.paragraph.rangetag

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.paragraph.rangetag.HWPRangeTagItem

class HWPRangeTagItemBuilder : HWPBuilder<HWPRangeTagItem> {
    private val rangeTagItem: HWPRangeTagItem = HWPRangeTagItem.build()

    fun setRangeStart(rangeStart: Long) : HWPRangeTagItemBuilder = this.apply {
        rangeTagItem.rangeStart = rangeStart
    }

    fun setRangeEnd(rangeEnd: Long) : HWPRangeTagItemBuilder = this.apply {
        rangeTagItem.rangeEnd = rangeEnd
    }

    fun setSort(sort: Short) : HWPRangeTagItemBuilder = this.apply {
        rangeTagItem.sort = sort
    }

    fun setData(data: ByteArray?) : HWPRangeTagItemBuilder = this.apply {
        rangeTagItem.data = data
    }

    override fun build(): HWPRangeTagItem = rangeTagItem
}