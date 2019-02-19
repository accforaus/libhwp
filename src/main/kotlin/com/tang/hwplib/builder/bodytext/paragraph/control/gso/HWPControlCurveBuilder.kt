package com.tang.hwplib.builder.bodytext.paragraph.control.gso

import com.tang.hwplib.builder.bodytext.paragraph.control.HWPCtrlDataBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.HWPGsoControlBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.ctrlheader.HWPCtrlHeaderGsoBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPCaptionBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPShapeComponentNormalBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPTextBoxBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.control.gso.HWPControlCurve
import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControlType
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentCurve
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.curve.HWPCurveSegmentType
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.polygon.HWPPositionXY

class HWPControlCurveBuilder : HWPGsoControlBuilder() {
    private val control : HWPControlCurve = HWPControlCurve.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderGsoBuilder) : HWPControlCurveBuilder = this.apply {
        control.header = headerBuilder.build().apply {
            ctrlId = HWPControlType.Gso.ctrlId
        }
    }

    fun setShapeComponent(shapeComponentBuilder: HWPShapeComponentNormalBuilder) : HWPControlCurveBuilder = this.apply {
        control.shapeComponent = shapeComponentBuilder.build()
    }

    fun setShapeComponentCurve(shapeComponentCurveBuilder: HWPShapeComponentCurveBuilder) : HWPControlCurveBuilder = this.apply {
        control.shapeComponentCurve = shapeComponentCurveBuilder.build()
    }

    fun setCaption(captionBuilder: HWPCaptionBuilder) : HWPControlCurveBuilder = this.apply {
        control.caption = captionBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlCurveBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    fun setTextBox(textBoxBuilder: HWPTextBoxBuilder) : HWPControlCurveBuilder = this.apply {
        control.textBox = textBoxBuilder.build()
    }

    override fun build(): HWPControlCurve = control.apply {
        setGsoId(HWPGsoControlType.Curve.id)
    }
}

class HWPShapeComponentCurveBuilder : HWPBuilder<HWPShapeComponentCurve> {
    private val shapeComponent : HWPShapeComponentCurve = HWPShapeComponentCurve.build()

    fun setPositionList(positionXYListBuilder: HWPPositionXYListBuilder) : HWPShapeComponentCurveBuilder = this.apply {
        shapeComponent.positionList = positionXYListBuilder.build()
    }

    fun setSegmentTypeList(segmentTypeListBuilder: HWPCurveSegmentTypeListBuilder) : HWPShapeComponentCurveBuilder = this.apply {
        shapeComponent.segmentTypeList = segmentTypeListBuilder.build()
    }

    override fun build(): HWPShapeComponentCurve = shapeComponent
}

class HWPCurveSegmentTypeListBuilder : HWPBuilder<ArrayList<HWPCurveSegmentType>> {
    private val segmentList : ArrayList<HWPCurveSegmentType> = ArrayList()

    fun addCurveSegmentType(segmentType: HWPCurveSegmentType) : HWPCurveSegmentTypeListBuilder = this.apply {
        segmentList.add(segmentType)
    }

    override fun build(): ArrayList<HWPCurveSegmentType> = segmentList
}

class HWPPositionXYListBuilder : HWPBuilder<ArrayList<HWPPositionXY>> {
    private val positionList : ArrayList<HWPPositionXY> = ArrayList()

    fun addPosition(positionBuilder: HWPPositionXYBuilder) : HWPPositionXYListBuilder = this.apply {
        positionList.add(positionBuilder.build())
    }

    override fun build(): ArrayList<HWPPositionXY> = positionList
}

class HWPPositionXYBuilder : HWPBuilder<HWPPositionXY> {
    private val position : HWPPositionXY = HWPPositionXY.build()

    fun setX(x: Long) : HWPPositionXYBuilder = this.apply {
        position.x = x
    }

    fun setY(y: Long) : HWPPositionXYBuilder = this.apply {
        position.y = y
    }

    override fun build(): HWPPositionXY = position
}