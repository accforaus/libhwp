package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlPageHide
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderPageHide
import com.tang.hwplib.objects.bodytext.control.ctrlheader.pagehide.HWPPageHideHeaderProperty

class HWPControlPageHideBuilder : HWPControlBuilder() {
    private val control : HWPControlPageHide = HWPControlPageHide.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderPageHideBuilder) : HWPControlPageHideBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlPageHideBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlPageHide = control
}

class HWPCtrlHeaderPageHideBuilder : HWPBuilder<HWPCtrlHeaderPageHide> {
    private val header : HWPCtrlHeaderPageHide = HWPCtrlHeaderPageHide.build()

    fun setProperty(propertyBuilder: HWPPageHideHeaderPropertyBuilder) : HWPCtrlHeaderPageHideBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    override fun build(): HWPCtrlHeaderPageHide = header
}

class HWPPageHideHeaderPropertyBuilder : HWPBuilder<HWPPageHideHeaderProperty> {
    private val property: HWPPageHideHeaderProperty = HWPPageHideHeaderProperty.build()

    fun setValue(value: Long) : HWPPageHideHeaderPropertyBuilder = this.apply {
        property.value = value
    }

    fun setHideHeader(hideHeader: Boolean) : HWPPageHideHeaderPropertyBuilder = this.apply {
        property.setHideHeader(hideHeader)
    }

    fun setHideFooter(hideFooter: Boolean) : HWPPageHideHeaderPropertyBuilder = this.apply {
        property.setHideFooter(hideFooter)
    }

    fun setHideBatangPage(hideBatangPage: Boolean) : HWPPageHideHeaderPropertyBuilder = this.apply {
        property.setHideBatangPage(hideBatangPage)
    }

    fun setHideBorder(hideBorder: Boolean) : HWPPageHideHeaderPropertyBuilder = this.apply {
        property.setHideBorder(hideBorder)
    }

    fun setHidePageNumber(hidePageNumber: Boolean) : HWPPageHideHeaderPropertyBuilder = this.apply {
        property.setHidePageNumber(hidePageNumber)
    }

    override fun build(): HWPPageHideHeaderProperty = property
}