package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPDocInfoElement

open class HWPDocInfoBuilder : HWPBuilder<HWPDocInfoElement> {
    override fun build(): HWPDocInfoElement = HWPDocInfoElement()
}