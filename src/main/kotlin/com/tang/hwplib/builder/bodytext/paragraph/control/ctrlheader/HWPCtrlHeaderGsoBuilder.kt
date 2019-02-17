package com.tang.hwplib.builder.bodytext.paragraph.control.ctrlheader

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderGso
import com.tang.hwplib.objects.bodytext.control.ctrlheader.gso.*

class HWPCtrlHeaderGsoBuilder : HWPBuilder<HWPCtrlHeaderGso> {
    private val header : HWPCtrlHeaderGso = HWPCtrlHeaderGso.build()

    fun setProperty(propertyBuilder: HWPGsoHeaderPropertyBuilder) : HWPCtrlHeaderGsoBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    fun setYOffset(yOffset: Long) : HWPCtrlHeaderGsoBuilder = this.apply {
        header.yOffset = yOffset
    }

    fun setXOffset(xOffset: Long) : HWPCtrlHeaderGsoBuilder = this.apply {
        header.xOffset = xOffset
    }

    fun setWidth(width: Long) : HWPCtrlHeaderGsoBuilder = this.apply {
        header.width = width
    }

    fun setHeight(height: Long) : HWPCtrlHeaderGsoBuilder = this.apply {
        header.height = height
    }

    fun setZOrder(zOrder: Int) : HWPCtrlHeaderGsoBuilder = this.apply {
        header.zOrder = zOrder
    }

    fun setOuterMarginLeft(outerMarginLeft: Int) : HWPCtrlHeaderGsoBuilder = this.apply {
        header.outerMarginLeft = outerMarginLeft
    }

    fun setOuterMarginRight(outerMarginRight: Int) : HWPCtrlHeaderGsoBuilder = this.apply {
        header.outerMarginRight = outerMarginRight
    }

    fun setOuterMarginTop(outerMarginTop: Int) : HWPCtrlHeaderGsoBuilder = this.apply {
        header.outerMarginTop = outerMarginTop
    }

    fun setOuterMarginBottom(outerMarginBottom: Int) : HWPCtrlHeaderGsoBuilder = this.apply {
        header.outerMarginBottom = outerMarginBottom
    }

    fun setPreventPageDivide(preventPageDivide: Boolean) : HWPCtrlHeaderGsoBuilder = this.apply {
        header.preventPageDivide = preventPageDivide
    }

    fun setExplanation(explanation: String) : HWPCtrlHeaderGsoBuilder = this.apply {
        header.explanation = explanation
    }

    override fun build(): HWPCtrlHeaderGso = header
}

class HWPGsoHeaderPropertyBuilder : HWPBuilder<HWPGsoHeaderProperty> {
    private val property : HWPGsoHeaderProperty = HWPGsoHeaderProperty.build()

    fun setValue(value: Long) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.value = value
    }

    fun setLikeWord(likeWord: Boolean) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.setLikeWord(likeWord)
    }

    fun setApplyLineSpace(applyLineSpace: Boolean) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.setApplyLineSpace(applyLineSpace)
    }

    fun setVertRelTo(vertRelTo: HWPVertRelTo) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.setVertRelTo(vertRelTo)
    }

    fun setVertRelativeArrange(vertRelativeArrange: HWPRelativeArrange) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.setVertRelativeArrange(vertRelativeArrange)
    }

    fun setHorzRelTo(horzRelTo: HWPHorzRelTo) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.setHorzRelTo(horzRelTo)
    }

    fun setHorzRelativeArrange(horzRelativeArrange: HWPRelativeArrange) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.setHorzRelativeArrange(horzRelativeArrange)
    }

    fun setVertRelToParaLimit(vertRelToParaLimit: Boolean) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.setVertRelToParaLimit(vertRelToParaLimit)
    }

    fun setAllowOverlap(allowOverlap: Boolean) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.setAllowOverlap(allowOverlap)
    }

    fun setWidthCriterion(widthCriterion: HWPWidthCriterion) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.setWidthCriterion(widthCriterion)
    }

    fun setHeightCriterion(heightCriterion: HWPHeightCriterion) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.setHeightCriterion(heightCriterion)
    }

    fun setProtectSize(protectSize: Boolean) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.setProtectSize(protectSize)
    }

    fun setTextFlowMethod(textFlowMethod: HWPTextFlowMethod) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.setTextFlowMethod(textFlowMethod)
    }

    fun setTextHorzArrange(textHorzArrange: HWPTextHorzArrange) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.setTextHorzArrange(textHorzArrange)
    }

    fun setObjectNumberSort(objectNumberSort: HWPObjectNumberSort) : HWPGsoHeaderPropertyBuilder = this.apply {
        property.setObjectNumberSort(objectNumberSort)
    }

    override fun build(): HWPGsoHeaderProperty = property
}