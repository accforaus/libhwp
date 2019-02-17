package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlNewNumber
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderNewNumber
import com.tang.hwplib.objects.bodytext.control.ctrlheader.autonumber.HWPNumberSort
import com.tang.hwplib.objects.bodytext.control.ctrlheader.newnumber.HWPNewNumberHeaderProperty

class HWPControlNewNumberBuilder : HWPControlBuilder<HWPControlNewNumber> {
    private val control : HWPControlNewNumber = HWPControlNewNumber.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderNewNumberBuilder) : HWPControlNewNumberBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlNewNumberBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlNewNumber = control
}

class HWPCtrlHeaderNewNumberBuilder : HWPBuilder<HWPCtrlHeaderNewNumber> {
    private val header : HWPCtrlHeaderNewNumber = HWPCtrlHeaderNewNumber.build()

    fun setProperty(propertyBuilder: HWPNewNumberPropertyBuilder) : HWPCtrlHeaderNewNumberBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    fun setNumber(number: Int) : HWPCtrlHeaderNewNumberBuilder = this.apply {
        header.number = number
    }

    override fun build(): HWPCtrlHeaderNewNumber = header
}

class HWPNewNumberPropertyBuilder : HWPBuilder<HWPNewNumberHeaderProperty> {
    private val property : HWPNewNumberHeaderProperty = HWPNewNumberHeaderProperty.build(0)

    fun setValue(value: Long) : HWPNewNumberPropertyBuilder = this.apply {
        property.value = value
    }

    fun setNumberSort(numberSort: HWPNumberSort) : HWPNewNumberPropertyBuilder = this.apply {
        property.setNumberSort(numberSort)
    }

    override fun build(): HWPNewNumberHeaderProperty = property
}