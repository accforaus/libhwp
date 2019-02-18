package com.tang.hwplib.builder.bodytext.paragraph.control.gso

import com.tang.hwplib.builder.bodytext.paragraph.control.HWPCtrlDataBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.HWPGsoControlBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.ctrlheader.HWPCtrlHeaderGsoBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPCaptionBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPShapeComponentNormalBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPTextBoxBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.control.gso.HWPControlRectangle
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentRectangle

class HWPControlRectangleBuilder : HWPGsoControlBuilder() {
    private val control : HWPControlRectangle = HWPControlRectangle.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderGsoBuilder) : HWPControlRectangleBuilder = this.apply {
        control.header = headerBuilder.build().apply {
            ctrlId = HWPControlType.Gso.ctrlId
        }
    }

    fun setShapeComponent(shapeComponentBuilder: HWPShapeComponentNormalBuilder) : HWPControlRectangleBuilder = this.apply {
        control.shapeComponent = shapeComponentBuilder.build()
    }

    fun setShapeComponentRectangle(shapeComponentRectangleBuilder: HWPShapeComponentRectangleBuilder) : HWPControlRectangleBuilder = this.apply {
        control.shapeComponentRectangle = shapeComponentRectangleBuilder.build()
    }

    fun setCaption(captionBuilder: HWPCaptionBuilder) : HWPControlRectangleBuilder = this.apply {
        control.caption = captionBuilder.build()
    }

    fun setTextBox(textBoxBuilder: HWPTextBoxBuilder) : HWPControlRectangleBuilder = this.apply {
        control.textBox = textBoxBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlRectangleBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlRectangle = control
}

class HWPShapeComponentRectangleBuilder : HWPBuilder<HWPShapeComponentRectangle> {
    private val shapeComponent : HWPShapeComponentRectangle = HWPShapeComponentRectangle.build()

    fun setRoundRate(roundRate: Byte) : HWPShapeComponentRectangleBuilder = this.apply {
        shapeComponent.roundRate = roundRate
    }

    fun setX1(x1: Int) : HWPShapeComponentRectangleBuilder = this.apply {
        shapeComponent.x1 = x1
    }

    fun setY1(y1: Int) : HWPShapeComponentRectangleBuilder = this.apply {
        shapeComponent.y1 = y1
    }

    fun setX2(x2: Int) : HWPShapeComponentRectangleBuilder = this.apply {
        shapeComponent.x2 = x2
    }

    fun setY2(y2: Int) : HWPShapeComponentRectangleBuilder = this.apply {
        shapeComponent.y2 = y2
    }

    fun setX3(x3: Int) : HWPShapeComponentRectangleBuilder = this.apply {
        shapeComponent.x3 = x3
    }

    fun setY3(y3: Int) : HWPShapeComponentRectangleBuilder = this.apply {
        shapeComponent.y3 = y3
    }

    fun setX4(x4: Int) : HWPShapeComponentRectangleBuilder = this.apply {
        shapeComponent.x4 = x4
    }

    fun setY4(y4: Int) : HWPShapeComponentRectangleBuilder = this.apply {
        shapeComponent.y4 = y4
    }

    override fun build(): HWPShapeComponentRectangle = shapeComponent
}