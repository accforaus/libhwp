package com.tang.hwplib.builder.bodytext.paragraph.control.gso

import com.tang.hwplib.builder.bodytext.paragraph.control.HWPCtrlDataBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.HWPGsoControlBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.ctrlheader.HWPCtrlHeaderGsoBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPCaptionBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPShapeComponentNormalBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPTextBoxBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.control.gso.HWPControlObjectLinkLine
import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControlType
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentLineForObjectLinkLine

class HWPControlObjectLinkLineBuilder : HWPGsoControlBuilder() {
    private val control : HWPControlObjectLinkLine = HWPControlObjectLinkLine.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderGsoBuilder) : HWPControlObjectLinkLineBuilder = this.apply {
        control.header = headerBuilder.build().apply {
            ctrlId = HWPControlType.Gso.ctrlId
        }
    }

    fun setShapeComponent(shapeComponentBuilder: HWPShapeComponentNormalBuilder) : HWPControlObjectLinkLineBuilder = this.apply {
        control.shapeComponent = shapeComponentBuilder.build()
    }

    fun setShapeComponentObjectLinkLine(shapeComponentBuilder: HWPShapeComponentLineForObjectLinkLineBuilder) : HWPControlObjectLinkLineBuilder = this.apply {
        control.shapeComponentObjectLinkLine = shapeComponentBuilder.build()
    }

    fun setCaption(captionBuilder: HWPCaptionBuilder) : HWPControlObjectLinkLineBuilder = this.apply {
        control.caption = captionBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlObjectLinkLineBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlObjectLinkLine = control.apply {
        setGsoId(HWPGsoControlType.ObjectLinkLine.id)
    }
}

class HWPShapeComponentLineForObjectLinkLineBuilder : HWPBuilder<HWPShapeComponentLineForObjectLinkLine> {
    private val shapeComponent : HWPShapeComponentLineForObjectLinkLine = HWPShapeComponentLineForObjectLinkLine.build()

    fun setStartX(startX: Int) : HWPShapeComponentLineForObjectLinkLineBuilder = this.apply {
        shapeComponent.startX = startX
    }

    fun setStartY(startY: Int) : HWPShapeComponentLineForObjectLinkLineBuilder = this.apply {
        shapeComponent.startY = startY
    }

    fun setEndX(endX: Int) : HWPShapeComponentLineForObjectLinkLineBuilder = this.apply {
        shapeComponent.endX = endX
    }

    fun setEndY(endY: Int) : HWPShapeComponentLineForObjectLinkLineBuilder = this.apply {
        shapeComponent.endY = endY
    }

    fun setUnknown(unknown: ByteArray) : HWPShapeComponentLineForObjectLinkLineBuilder = this.apply {
        shapeComponent.unknown = unknown
    }

    override fun build(): HWPShapeComponentLineForObjectLinkLine = shapeComponent
}