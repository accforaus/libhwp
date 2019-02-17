package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlPageNumberPosition
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderPageNumberPosition
import com.tang.hwplib.objects.bodytext.control.ctrlheader.pagenumberposition.HWPNumberPosition
import com.tang.hwplib.objects.bodytext.control.ctrlheader.pagenumberposition.PageNumberPositionHeaderProperty
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPNumberShape

class HWPControlPageNumberPositionBuilder : HWPControlBuilder<HWPControlPageNumberPosition> {
    private val control : HWPControlPageNumberPosition = HWPControlPageNumberPosition.build()

    override fun build(): HWPControlPageNumberPosition = control
}

class HWPCtrlHeaderPageNumberPositionBuilder : HWPBuilder<HWPCtrlHeaderPageNumberPosition> {
    private val header : HWPCtrlHeaderPageNumberPosition = HWPCtrlHeaderPageNumberPosition.build()

    fun setProperty(propertyBuilder: PageNumberPositionHeaderPropertyBuilder) : HWPCtrlHeaderPageNumberPositionBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    fun setNumber(number: Int) : HWPCtrlHeaderPageNumberPositionBuilder = this.apply {
        header.number = number
    }

    fun setUserSymbol(userSymbol: String) : HWPCtrlHeaderPageNumberPositionBuilder = this.apply {
        header.userSymbol = userSymbol
    }

    fun setBeforeDecorationLetter(beforeDecorationLetter: String) : HWPCtrlHeaderPageNumberPositionBuilder = this.apply {
        header.beforeDecorationLetter = beforeDecorationLetter
    }

    fun setAfterDecorationLetter(afterDecorationLetter: String) : HWPCtrlHeaderPageNumberPositionBuilder = this.apply {
        header.afterDecorationLetter = afterDecorationLetter
    }

    override fun build(): HWPCtrlHeaderPageNumberPosition = header
}

class PageNumberPositionHeaderPropertyBuilder : HWPBuilder<PageNumberPositionHeaderProperty> {
    private val property: PageNumberPositionHeaderProperty = PageNumberPositionHeaderProperty.build()

    fun setValue(value: Long) : PageNumberPositionHeaderPropertyBuilder = this.apply {
        property.value = value
    }

    fun setNumberShape(numberShape: HWPNumberShape) : PageNumberPositionHeaderPropertyBuilder = this.apply {
        property.setNumberShape(numberShape)
    }

    fun setNumberPosition(numberPosition: HWPNumberPosition) : PageNumberPositionHeaderPropertyBuilder = this.apply {
        property.setNumberPosition(numberPosition)
    }

    override fun build(): PageNumberPositionHeaderProperty = property
}