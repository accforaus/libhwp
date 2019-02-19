package com.tang.hwplib.builder.bodytext.paragraph.control.gso

import com.tang.hwplib.builder.bodytext.paragraph.control.HWPCtrlDataBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.HWPGsoControlBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.ctrlheader.HWPCtrlHeaderGsoBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPCaptionBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPShapeComponentNormalBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPTextBoxBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.control.gso.HWPControlLine
import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControlType
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentLine

class HWPControlLineBuilder : HWPGsoControlBuilder() {
    private val control : HWPControlLine = HWPControlLine.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderGsoBuilder) : HWPControlLineBuilder = this.apply {
        control.header = headerBuilder.build().apply {
            ctrlId = HWPControlType.Gso.ctrlId
        }
    }

    fun setShapeComponent(shapeComponentBuilder: HWPShapeComponentNormalBuilder) : HWPControlLineBuilder = this.apply {
        control.shapeComponent = shapeComponentBuilder.build()
    }

    fun setShapeComponentLine(shapeComponentLineBuilder: HWPShapeComponentLineBuilder) : HWPControlLineBuilder = this.apply {
        control.shapeComponentLine = shapeComponentLineBuilder.build()
    }

    fun setCaption(captionBuilder: HWPCaptionBuilder) : HWPControlLineBuilder = this.apply {
        control.caption = captionBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlLineBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlLine = control.apply {
        setGsoId(HWPGsoControlType.Line.id)
    }
}

class HWPShapeComponentLineBuilder : HWPBuilder<HWPShapeComponentLine> {
    private val shapeComponent : HWPShapeComponentLine = HWPShapeComponentLine.build()

    fun setStartX(startX: Int) : HWPShapeComponentLineBuilder = this.apply {
        shapeComponent.startX = startX
    }

    fun setStartY(startY: Int) : HWPShapeComponentLineBuilder = this.apply {
        shapeComponent.startY = startY
    }

    fun setEndX(endX: Int) : HWPShapeComponentLineBuilder = this.apply {
        shapeComponent.endX = endX
    }

    fun setEndY(endY: Int) : HWPShapeComponentLineBuilder = this.apply {
        shapeComponent.endY = endY
    }

    fun setStartedRightOrBottom(startedRightOrBottom: Boolean) : HWPShapeComponentLineBuilder = this.apply {
        shapeComponent.startedRightOrBottom = startedRightOrBottom
    }

    override fun build(): HWPShapeComponentLine = shapeComponent
}