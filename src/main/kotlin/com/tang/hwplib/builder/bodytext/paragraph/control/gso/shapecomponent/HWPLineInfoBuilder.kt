package com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent

import com.tang.hwplib.builder.etc.Color4ByteBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.line.*

class HWPLineInfoBuilder : HWPBuilder<HWPLineInfo> {
    private val lineInfo : HWPLineInfo = HWPLineInfo.build()

    fun setColor(colorBuilder: Color4ByteBuilder) : HWPLineInfoBuilder = this.apply {
        lineInfo.color = colorBuilder.build()
    }

    fun setThickness(thickness: Int) : HWPLineInfoBuilder = this.apply {
        lineInfo.thickness = thickness
    }

    fun setProperty(propertyBuilder: HWPLineInfoPropertyBuilder) : HWPLineInfoBuilder = this.apply {
        lineInfo.property = propertyBuilder.build()
    }

    fun setOutlineStyle(outlineStype: HWPOutlineStyle) : HWPLineInfoBuilder = this.apply {
        lineInfo.outlineStyle = outlineStype
    }

    override fun build(): HWPLineInfo = lineInfo
}

class HWPLineInfoPropertyBuilder : HWPBuilder<HWPLineInfoProperty> {
    private val property: HWPLineInfoProperty = HWPLineInfoProperty.build()

    fun setLineType(lineType: HWPLineType) : HWPLineInfoPropertyBuilder = this.apply {
        property.setLineType(lineType)
    }

    fun setLineEndShape(lineEndShape: HWPLineEndShape) : HWPLineInfoPropertyBuilder = this.apply {
        property.setLineEndShape(lineEndShape)
    }

    fun setStartArrowShape(startArrowShape: HWPLineArrowShape) : HWPLineInfoPropertyBuilder = this.apply {
        property.setStartArrowShape(startArrowShape)
    }

    fun setEndArrowShape(endArrowShape: HWPLineArrowShape) : HWPLineInfoPropertyBuilder = this.apply {
        property.setEndArrowShape(endArrowShape)
    }

    fun setStartArrowSize(startArrowSize: HWPLineArrowSize) : HWPLineInfoPropertyBuilder = this.apply {
        property.setStartArrowSize(startArrowSize)
    }

    fun setEndArrowSize(endArrowSize: HWPLineArrowSize) : HWPLineInfoPropertyBuilder = this.apply {
        property.setEndArrowSize(endArrowSize)
    }

    fun setFillStartArrow(fillStartArrow: Boolean) : HWPLineInfoPropertyBuilder = this.apply {
        property.setFillStartArrow(fillStartArrow)
    }

    fun setFillEndArrow(fillEndArrow: Boolean) : HWPLineInfoPropertyBuilder = this.apply {
        property.setFillEndArrow(fillEndArrow)
    }

    override fun build(): HWPLineInfoProperty = property
}