package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlField
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderField
import com.tang.hwplib.objects.bodytext.control.ctrlheader.field.HWPFieldHeaderProperty

class HWPControlFieldBuilder : HWPControlBuilder() {
    private val control : HWPControlField = HWPControlField.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderFieldBuilder) : HWPControlFieldBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlFieldBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlField = control
}

class HWPCtrlHeaderFieldBuilder : HWPBuilder<HWPCtrlHeaderField> {
    private val header : HWPCtrlHeaderField = HWPCtrlHeaderField.build()

    fun setProperty(propertyBuilder: HWPFieldHeaderPropertyBuilder) : HWPCtrlHeaderFieldBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    fun setEtcProperty(etcProperty: Short) : HWPCtrlHeaderFieldBuilder = this.apply {
        header.etcProperty = etcProperty
    }

    fun setCommand(command: String) : HWPCtrlHeaderFieldBuilder = this.apply {
        header.command = command
    }

    fun setMemoIndex(memoIndex: Int) : HWPCtrlHeaderFieldBuilder = this.apply {
        header.memoIndex = memoIndex
    }

    override fun build(): HWPCtrlHeaderField = header
}

class HWPFieldHeaderPropertyBuilder : HWPBuilder<HWPFieldHeaderProperty> {
    private val property : HWPFieldHeaderProperty = HWPFieldHeaderProperty.build()

    fun setValue(value: Long) : HWPFieldHeaderPropertyBuilder = this.apply {
        property.value = value
    }

    fun setModifyInReadOnlyState(modifyInReadOnlyState: Boolean) : HWPFieldHeaderPropertyBuilder = this.apply {
        property.setModifyInReadOnlyState(modifyInReadOnlyState)
    }

    fun setUpdateTextPropertyAtUpdatingHyperlinkNotOpened(updateTextPropertyAtUpdatingHyperlinkNotOpened: Boolean) : HWPFieldHeaderPropertyBuilder = this.apply {
        property.setUpdateTextPropertyAtUpdatingHyperlinkNotOpened(updateTextPropertyAtUpdatingHyperlinkNotOpened)
    }

    fun setUpdateTextPropertyAtUpdatingHyperlinkOpened(updateTextPropertyAtUpdatingHyperlinkOpened: Boolean) : HWPFieldHeaderPropertyBuilder = this.apply {
        property.setUpdateTextPropertyAtUpdatingHyperlinkOpened(updateTextPropertyAtUpdatingHyperlinkOpened)
    }

    fun setUpdateTextPropertyAtUpdatingHyperlinkCreating(updateTextPropertyAtUpdatingHyperlinkCreating: Boolean) : HWPFieldHeaderPropertyBuilder = this.apply {
        property.setUpdateTextPropertyAtUpdatingHyperlinkCreating(updateTextPropertyAtUpdatingHyperlinkCreating)
    }

    fun setModifiedContent(modifiedContent: Boolean) : HWPFieldHeaderPropertyBuilder = this.apply {
        property.setModifiedContent(modifiedContent)
    }

    override fun build(): HWPFieldHeaderProperty = property
}