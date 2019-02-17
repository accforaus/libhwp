package com.tang.hwplib.builder.bodytext.paragraph.control.gso

import com.tang.hwplib.builder.bodytext.paragraph.control.HWPCtrlDataBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.ctrlheader.HWPCtrlHeaderGsoBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPCaptionBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPShapeComponentNormalBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPTextBoxBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPGsoControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.control.gso.HWPControlArc
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentArc
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.arc.HWPArcBorder

class HWPControlArcBuilder : HWPGsoControlBuilder<HWPControlArc> {
    private val control: HWPControlArc = HWPControlArc.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderGsoBuilder) : HWPControlArcBuilder = this.apply {
        control.header = headerBuilder.build().apply {
            ctrlId = HWPControlType.Gso.ctrlId
        }
    }

    fun setShapeComponent(shapeComponentBuilder: HWPShapeComponentNormalBuilder) : HWPControlArcBuilder = this.apply {
        control.shapeComponent = shapeComponentBuilder.build()
    }

    fun setShapeComponentArc(shapeComponentArcBuilder: HWPShapeComponentArcBuilder) : HWPControlArcBuilder = this.apply {
        control.shapeComponentArc = shapeComponentArcBuilder.build()
    }

    fun setCaption(captionBuilder: HWPCaptionBuilder) : HWPControlArcBuilder = this.apply {
        control.caption = captionBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlArcBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    fun setTextBox(textBoxBuilder: HWPTextBoxBuilder) : HWPControlArcBuilder = this.apply {
        control.textBox = textBoxBuilder.build()
    }

    override fun build(): HWPControlArc = control
}

class HWPShapeComponentArcBuilder : HWPBuilder<HWPShapeComponentArc> {
    private val shapeComponent : HWPShapeComponentArc = HWPShapeComponentArc.build()

    fun setArcBorder(arcBorder: HWPArcBorder) : HWPShapeComponentArcBuilder = this.apply {
        shapeComponent.arcBorder = arcBorder
    }

    fun setCenterX(centerX: Int) : HWPShapeComponentArcBuilder = this.apply {
        shapeComponent.centerX = centerX
    }

    fun setCenterY(centerY: Int) : HWPShapeComponentArcBuilder = this.apply {
        shapeComponent.centerY = centerY
    }

    fun setAxis1X(axis1X: Int) : HWPShapeComponentArcBuilder = this.apply {
        shapeComponent.axis1X = axis1X
    }

    fun setAxis1Y(axis1Y: Int) : HWPShapeComponentArcBuilder = this.apply {
        shapeComponent.axis1Y = axis1Y
    }

    fun setAxis2X(axis2X: Int) : HWPShapeComponentArcBuilder = this.apply {
        shapeComponent.axis2X = axis2X
    }

    fun setAxis2Y(axis2Y: Int) : HWPShapeComponentArcBuilder = this.apply {
        shapeComponent.axis2Y = axis2Y
    }

    override fun build(): HWPShapeComponentArc = shapeComponent
}