package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlBookmark
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderBookmark

class HWPControlBookmarkBuilder : HWPControlBuilder<HWPControlBookmark> {
    private val control : HWPControlBookmark = HWPControlBookmark.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderBookmarkBuilder) : HWPControlBookmarkBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    override fun build(): HWPControlBookmark = control
}

class HWPCtrlHeaderBookmarkBuilder : HWPBuilder<HWPCtrlHeaderBookmark> {
    private val header : HWPCtrlHeaderBookmark = HWPCtrlHeaderBookmark.build()

    override fun build(): HWPCtrlHeaderBookmark = header
}