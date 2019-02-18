package com.tang.hwplib.builder.etc

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.HWPRecordHeader

class HWPRecordHeaderBuilder : HWPBuilder<HWPRecordHeader> {
    private val recordHeader: HWPRecordHeader = HWPRecordHeader.build()

    fun setTagID(tagID: Short) : HWPRecordHeaderBuilder = this.apply {
        recordHeader.tagId = tagID
    }

    fun setLevel(level: Short) : HWPRecordHeaderBuilder = this.apply {
        recordHeader.level = level
    }

    fun setSize(size: Long) : HWPRecordHeaderBuilder = this.apply {
        recordHeader.size = size
    }

    override fun build(): HWPRecordHeader = recordHeader
}