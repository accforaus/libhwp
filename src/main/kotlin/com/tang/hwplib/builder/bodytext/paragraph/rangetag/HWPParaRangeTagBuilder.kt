package com.tang.hwplib.builder.bodytext.paragraph.rangetag

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.paragraph.rangetag.HWPParaRangeTag

class HWPParaRangeTagBuilder : HWPBuilder<HWPParaRangeTag> {
    private val rangeTag: HWPParaRangeTag = HWPParaRangeTag.build()

    fun addParaRangeTagItem(rangeTagItemBuilder: HWPRangeTagItemBuilder) : HWPParaRangeTagBuilder = this.apply {
        rangeTag.rangeTagItemList.add(rangeTagItemBuilder.build())
    }

    override fun build(): HWPParaRangeTag = rangeTag
}