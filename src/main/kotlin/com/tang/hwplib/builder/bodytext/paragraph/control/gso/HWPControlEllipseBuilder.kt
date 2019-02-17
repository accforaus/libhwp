package com.tang.hwplib.builder.bodytext.paragraph.control.gso

import com.tang.hwplib.builder.bodytext.paragraph.control.HWPCtrlDataBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.ctrlheader.HWPCtrlHeaderGsoBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPCaptionBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPShapeComponentNormalBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPTextBoxBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPGsoControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.control.gso.HWPControlEllipse
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentEllipse
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.ellipse.HWPShapeComponentEllipseProperty

class HWPControlEllipseBuilder : HWPGsoControlBuilder<HWPControlEllipse> {
    private val control : HWPControlEllipse = HWPControlEllipse.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderGsoBuilder) : HWPControlEllipseBuilder = this.apply {
        control.header = headerBuilder.build().apply {
            ctrlId = HWPControlType.Gso.ctrlId
        }
    }

    fun setShapeComponent(shapeComponentBuilder: HWPShapeComponentNormalBuilder) : HWPControlEllipseBuilder = this.apply {
        control.shapeComponent = shapeComponentBuilder.build()
    }

    fun setShapeComponentEllipse(shapeComponentEllipseBuilder: HWPShapeComponentEllipseBuilder) : HWPControlEllipseBuilder = this.apply {
        control.shapeComponentEllipse = shapeComponentEllipseBuilder.build()
    }

    fun setCaption(captionBuilder: HWPCaptionBuilder) : HWPControlEllipseBuilder = this.apply {
        control.caption = captionBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlEllipseBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    fun setTextBox(textBoxBuilder: HWPTextBoxBuilder) : HWPControlEllipseBuilder = this.apply {
        control.textBox = textBoxBuilder.build()
    }

    override fun build(): HWPControlEllipse = control
}

class HWPShapeComponentEllipseBuilder : HWPBuilder<HWPShapeComponentEllipse> {
    private val shapeComponent : HWPShapeComponentEllipse = HWPShapeComponentEllipse.build()

    fun setProperty(propertyBuilder: HWPShapeComponentEllipsePropertyBuilder) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.property = propertyBuilder.build()
    }

    fun setCenterX(centerX: Int) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.centerX = centerX
    }

    fun setCenterY(centerY: Int) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.centerY = centerY
    }

    fun setAxis1X(axis1X: Int) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.axis1X = axis1X
    }

    fun setAxis1Y(axis1Y: Int) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.axis1Y = axis1Y
    }

    fun setAxis2X(axis2X: Int) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.axis2X = axis2X
    }

    fun setAxis2Y(axis2Y: Int) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.axis2Y = axis2Y
    }

    fun setStartX(startX: Int) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.startX = startX
    }

    fun setStartY(startY: Int) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.startY = startY
    }

    fun setEndX(endX: Int) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.endX = endX
    }

    fun setEndY(endY: Int) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.endY = endY
    }

    fun setStartX2(startX2: Int) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.startX2 = startX2
    }

    fun setStartY2(startY2: Int) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.startY2 = startY2
    }

    fun setEndX2(endX2: Int) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.endX2 = endX2
    }

    fun setEndY2(endY2: Int) : HWPShapeComponentEllipseBuilder = this.apply {
        shapeComponent.endY2 = endY2
    }

    override fun build(): HWPShapeComponentEllipse = shapeComponent
}

class HWPShapeComponentEllipsePropertyBuilder : HWPBuilder<HWPShapeComponentEllipseProperty> {
    private val property: HWPShapeComponentEllipseProperty = HWPShapeComponentEllipseProperty.build()

    fun setValue(value: Long) : HWPShapeComponentEllipsePropertyBuilder = this.apply {
        property.value = value
    }

    fun setRecalculateIntervalWhenChangingArc(recalculateIntervalWhenChangingArc: Boolean) : HWPShapeComponentEllipsePropertyBuilder = this.apply {
        property.setRecalculateIntervalWhenChangingArc(recalculateIntervalWhenChangingArc)
    }

    fun setChangeArc(changeArc: Boolean) : HWPShapeComponentEllipsePropertyBuilder = this.apply {
        property.setChangeArc(changeArc)
    }

    fun setArcSort(arcSort: Short) : HWPShapeComponentEllipsePropertyBuilder = this.apply {
        property.setArcSort(arcSort)
    }

    override fun build(): HWPShapeComponentEllipseProperty = property
}