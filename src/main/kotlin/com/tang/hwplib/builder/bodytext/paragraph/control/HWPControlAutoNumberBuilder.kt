package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlAutoNumber
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderAutoNumber
import com.tang.hwplib.objects.bodytext.control.ctrlheader.autonumber.HWPAutoNumberHeaderProperty
import com.tang.hwplib.objects.bodytext.control.ctrlheader.autonumber.HWPNumberSort
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPNumberShape

class HWPControlAutoNumberBuilder : HWPControlBuilder<HWPControlAutoNumber> {
    private val control : HWPControlAutoNumber = HWPControlAutoNumber.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderAutoNumberBuilder) : HWPControlAutoNumberBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlAutoNumberBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlAutoNumber = control
}

class HWPCtrlHeaderAutoNumberBuilder : HWPBuilder<HWPCtrlHeaderAutoNumber> {
    private val header : HWPCtrlHeaderAutoNumber = HWPCtrlHeaderAutoNumber.build()

    fun setProperty(propertyBuilder: HWPAutoNumberHeaderPropertyBuilder) : HWPCtrlHeaderAutoNumberBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    fun setNumber(number: Int) : HWPCtrlHeaderAutoNumberBuilder = this.apply {
        header.number = number
    }

    fun setUserSymbol(userSymbol: String?) : HWPCtrlHeaderAutoNumberBuilder = this.apply {
        header.userSymbol = userSymbol
    }

    fun setBeforeDecorationLetter(beforeDecorationLetter: String?) : HWPCtrlHeaderAutoNumberBuilder = this.apply {
        header.beforeDecorationLetter = beforeDecorationLetter
    }

    fun setAfterDecorationLetter(afterDecorationLetter: String?) : HWPCtrlHeaderAutoNumberBuilder = this.apply {
        header.afterDecorationLetter = afterDecorationLetter
    }

    override fun build(): HWPCtrlHeaderAutoNumber = header
}

class HWPAutoNumberHeaderPropertyBuilder : HWPBuilder<HWPAutoNumberHeaderProperty> {
    private val property : HWPAutoNumberHeaderProperty = HWPAutoNumberHeaderProperty.build()

    fun setNumberSort(numberSort: HWPNumberSort) : HWPAutoNumberHeaderPropertyBuilder = this.apply {
        property.setNumberSort(numberSort)
    }

    fun setNumberShape(numberShape: HWPNumberShape) : HWPAutoNumberHeaderPropertyBuilder = this.apply {
        property.setNumberShape(numberShape)
    }

    fun setSuperScript(superScript: Boolean) : HWPAutoNumberHeaderPropertyBuilder = this.apply {
        property.setSuperScript(superScript)
    }

    override fun build(): HWPAutoNumberHeaderProperty = property
}