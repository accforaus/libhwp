package com.tang.hwplib.builder.bodytext.paragraph.control.gso

import com.tang.hwplib.builder.bodytext.paragraph.control.HWPCtrlDataBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.HWPGsoControlBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.ctrlheader.HWPCtrlHeaderGsoBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPCaptionBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPLineInfoPropertyBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPShapeComponentNormalBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPTextBoxBuilder
import com.tang.hwplib.builder.etc.Color4ByteBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.control.gso.HWPControlOLE
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentOLE
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.ole.DVASPECT
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.ole.HWPObjectSort
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.ole.HWPShapeComponentOLEProperty

class HWPControlOLEBuilder : HWPGsoControlBuilder() {
    private val control : HWPControlOLE = HWPControlOLE.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderGsoBuilder) : HWPControlOLEBuilder = this.apply {
        control.header = headerBuilder.build().apply {
            ctrlId = HWPControlType.Gso.ctrlId
        }
    }

    fun setShapeComponent(shapeComponentBuilder: HWPShapeComponentNormalBuilder) : HWPControlOLEBuilder = this.apply {
        control.shapeComponent = shapeComponentBuilder.build()
    }

    fun setShapeComponentOLE(shapeComponentOLEBuilder: HWPShapeComponentOLEBuilder) : HWPControlOLEBuilder = this.apply {
        control.shapeComponentOLE = shapeComponentOLEBuilder.build()
    }

    fun setCaption(captionBuilder: HWPCaptionBuilder) : HWPControlOLEBuilder = this.apply {
        control.caption = captionBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlOLEBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlOLE = control
}

class HWPShapeComponentOLEBuilder : HWPBuilder<HWPShapeComponentOLE> {
    private val shapeComponent: HWPShapeComponentOLE = HWPShapeComponentOLE.build()

    fun setProperty(propertyBuilder: HWPShapeComponentOLEPropertyBuilder) : HWPShapeComponentOLEBuilder = this.apply {
        shapeComponent.property = propertyBuilder.build()
    }

    fun setExtentWidth(extentWidth: Int) : HWPShapeComponentOLEBuilder = this.apply {
        shapeComponent.extentWidth = extentWidth
    }

    fun setExtentHeight(extentHeight: Int) : HWPShapeComponentOLEBuilder = this.apply {
        shapeComponent.extentHeight = extentHeight
    }

    fun setBinDataID(binDataID: Int) : HWPShapeComponentOLEBuilder = this.apply {
        shapeComponent.binDataId = binDataID
    }

    fun setBorderColor(colorBuilder: Color4ByteBuilder) : HWPShapeComponentOLEBuilder = this.apply {
        shapeComponent.borderColor = colorBuilder.build()
    }

    fun setBorderThickness(borderThickness: Int) : HWPShapeComponentOLEBuilder = this.apply {
        shapeComponent.borderThickness = borderThickness
    }

    fun setBorderProperty(borderPropertyBuilder: HWPLineInfoPropertyBuilder) : HWPShapeComponentOLEBuilder = this.apply {
        shapeComponent.borderProperty = borderPropertyBuilder.build()
    }

    override fun build(): HWPShapeComponentOLE = shapeComponent
}

class HWPShapeComponentOLEPropertyBuilder : HWPBuilder<HWPShapeComponentOLEProperty> {
    private val property : HWPShapeComponentOLEProperty = HWPShapeComponentOLEProperty.build()

    fun setValue(value: Long) : HWPShapeComponentOLEPropertyBuilder = this.apply {
        property.value = value
    }

    fun setDVASPECT(dvaspect: DVASPECT) : HWPShapeComponentOLEPropertyBuilder = this.apply {
        property.setDVASPECT(dvaspect)
    }

    fun setMoniker(moniker: Boolean) : HWPShapeComponentOLEPropertyBuilder = this.apply {
        property.setMoniker(moniker)
    }

    fun setBaseLine(baseLine: Byte) : HWPShapeComponentOLEPropertyBuilder = this.apply {
        property.setBaseLine(baseLine)
    }

    fun setObjectSort(objectSort: HWPObjectSort) : HWPShapeComponentOLEPropertyBuilder = this.apply {
        property.setObjectSort(objectSort)
    }

    override fun build(): HWPShapeComponentOLEProperty = property
}