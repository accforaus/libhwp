package com.tang.hwplib.builder.bodytext.paragraph.control.gso

import com.tang.hwplib.builder.bodytext.paragraph.control.HWPCtrlDataBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.HWPGsoControlBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.ctrlheader.HWPCtrlHeaderGsoBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPCaptionBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPShapeComponentNormalBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPTextBoxBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.control.gso.HWPControlPolygon
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentPolygon

class HWPControlPolygonBuilder : HWPGsoControlBuilder() {
    private val control : HWPControlPolygon = HWPControlPolygon.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderGsoBuilder) : HWPControlPolygonBuilder = this.apply {
        control.header = headerBuilder.build().apply {
            ctrlId = HWPControlType.Gso.ctrlId
        }
    }

    fun setShapeComponent(shapeComponentBuilder: HWPShapeComponentNormalBuilder) : HWPControlPolygonBuilder = this.apply {
        control.shapeComponent = shapeComponentBuilder.build()
    }

    fun setShapeComponentPolygon(shapeComponentPolygonBuilder: HWPShapeComponentPolygonBuilder) : HWPControlPolygonBuilder = this.apply {
        control.shapeComponentPolygon = shapeComponentPolygonBuilder.build()
    }

    fun setCaption(captionBuilder: HWPCaptionBuilder) : HWPControlPolygonBuilder = this.apply {
        control.caption = captionBuilder.build()
    }

    fun setTextBox(textBoxBuilder: HWPTextBoxBuilder) : HWPControlPolygonBuilder = this.apply {
        control.textBox = textBoxBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlPolygonBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlPolygon = control
}

class HWPShapeComponentPolygonBuilder : HWPBuilder<HWPShapeComponentPolygon> {
    private val shapeComponent: HWPShapeComponentPolygon = HWPShapeComponentPolygon.build()

    fun setPositionList(positionListBuilder: HWPPositionXYListBuilder) : HWPShapeComponentPolygonBuilder = this.apply {
        shapeComponent.positionList = positionListBuilder.build()
    }

    override fun build(): HWPShapeComponentPolygon = shapeComponent
}