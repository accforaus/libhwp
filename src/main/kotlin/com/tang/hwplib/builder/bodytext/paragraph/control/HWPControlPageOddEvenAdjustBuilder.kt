package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlPageOddEvenAdjust
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderPageOddEvenAdjust
import com.tang.hwplib.objects.bodytext.control.ctrlheader.pageoddevenadjust.HWPOddEvenDivision
import com.tang.hwplib.objects.bodytext.control.ctrlheader.pageoddevenadjust.PageOddEvenAdjustHeaderProperty

class HWPControlPageOddEvenAdjustBuilder : HWPControlBuilder() {
    private val control : HWPControlPageOddEvenAdjust = HWPControlPageOddEvenAdjust.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderPageOddEvenAdjustBuilder) : HWPControlPageOddEvenAdjustBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlPageOddEvenAdjustBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlPageOddEvenAdjust = control
}

class HWPCtrlHeaderPageOddEvenAdjustBuilder : HWPBuilder<HWPCtrlHeaderPageOddEvenAdjust> {
    private val header: HWPCtrlHeaderPageOddEvenAdjust = HWPCtrlHeaderPageOddEvenAdjust.build()

    fun setProperty(propertyBuilder: HWPPageOddEvenAdjustHeaderPropertyBuilder) : HWPCtrlHeaderPageOddEvenAdjustBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    override fun build(): HWPCtrlHeaderPageOddEvenAdjust = header
}

class HWPPageOddEvenAdjustHeaderPropertyBuilder : HWPBuilder<PageOddEvenAdjustHeaderProperty> {
    private val property: PageOddEvenAdjustHeaderProperty = PageOddEvenAdjustHeaderProperty.build(0)

    fun setValue(value: Long) : HWPPageOddEvenAdjustHeaderPropertyBuilder = this.apply {
        property.value = value
    }

    fun setOddEvenDivision(oddEvenDivision: HWPOddEvenDivision) : HWPPageOddEvenAdjustHeaderPropertyBuilder = this.apply {
        property.setOddEvenDivision(oddEvenDivision)
    }

    override fun build(): PageOddEvenAdjustHeaderProperty = property
}