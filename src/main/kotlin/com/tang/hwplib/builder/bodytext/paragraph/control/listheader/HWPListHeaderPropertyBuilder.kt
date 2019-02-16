package com.tang.hwplib.builder.bodytext.paragraph.control.listheader

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.ctrlheader.sectiondefine.HWPTextDirection
import com.tang.hwplib.objects.bodytext.control.gso.listheader.HWPListHeaderProperty
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPLineChange
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPTextVerticalAlignment

class HWPListHeaderPropertyBuilder : HWPBuilder<HWPListHeaderProperty> {
    private val header: HWPListHeaderProperty = HWPListHeaderProperty.build()

    fun setTextDirection(textDirection: HWPTextDirection) : HWPListHeaderPropertyBuilder = this.apply {
        header.setTextDirection(textDirection)
    }

    fun setLineChange(lineChange: HWPLineChange) : HWPListHeaderPropertyBuilder = this.apply {
        header.setLineChange(lineChange)
    }

    fun setTextVerticalAlignment(textVerticalAlignment: HWPTextVerticalAlignment) : HWPListHeaderPropertyBuilder = this.apply {
        header.setTextVerticalAlignment(textVerticalAlignment)
    }

    override fun build(): HWPListHeaderProperty = header
}