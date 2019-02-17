package com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent

import com.tang.hwplib.builder.docinfo.borderfill.fillinfo.HWPFillInfoBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponent
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentContainer
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentNormal

open class HWPShapeComponentBuilder : HWPBuilder<HWPShapeComponent> {
    protected val shapeComponent : HWPShapeComponent = HWPShapeComponent.build()

    fun setGsoID(gsoID: Long) : HWPShapeComponentBuilder = this.apply {
        shapeComponent.gsoId = gsoID
    }

    fun setOffsetX(offsetX: Int) : HWPShapeComponentBuilder = this.apply {
        shapeComponent.offsetX = offsetX
    }

    fun setOffsetY(offsetY: Int) : HWPShapeComponentBuilder = this.apply {
        shapeComponent.offsetY = offsetY
    }

    fun setGroupingCount(groupingCount: Int) : HWPShapeComponentBuilder = this.apply {
        shapeComponent.groupingCount = groupingCount
    }

    fun setLocalFileVersion(localFileVersion: Int) : HWPShapeComponentBuilder = this.apply {
        shapeComponent.localFileVersion = localFileVersion
    }

    fun setWidthAtCreate(widthAtCreate: Long) : HWPShapeComponentBuilder = this.apply {
        shapeComponent.widthAtCreate = widthAtCreate
    }

    fun setHeightAtCreate(heightAtCreate: Long) : HWPShapeComponentBuilder = this.apply {
        shapeComponent.heightAtCreate = heightAtCreate
    }

    fun setWidthAtCurrent(widthWidthAtCurrent: Long) : HWPShapeComponentBuilder = this.apply {
        shapeComponent.widthAtCurrent = widthWidthAtCurrent
    }

    fun setHeightAtCurrent(heightAtCurrent: Long) : HWPShapeComponentBuilder = this.apply {
        shapeComponent.heightAtCurrent = heightAtCurrent
    }

    fun setProperty(property: Long) : HWPShapeComponentBuilder = this.apply {
        shapeComponent.property = property
    }

    fun setRotateAngle(rotateAngle: Int) : HWPShapeComponentBuilder = this.apply {
        shapeComponent.rotateAngle = rotateAngle
    }

    fun setRotateXCenter(rotateXCenter: Int) : HWPShapeComponentBuilder = this.apply {
        shapeComponent.rotateXCenter = rotateXCenter
    }

    fun setRotateYCenter(rotateYCenter: Int) : HWPShapeComponentBuilder = this.apply {
        shapeComponent.rotateYCenter = rotateYCenter
    }

    fun setRenderingInfo(renderInfoBuilder: HWPRenderingInfoBuilder) : HWPShapeComponentBuilder = this.apply {
        shapeComponent.renderingInfo = renderInfoBuilder.build()
    }

    override fun build(): HWPShapeComponent = shapeComponent
}

class HWPShapeComponentNormalBuilder : HWPShapeComponentBuilder() {
    fun setLineInfo(lineInfoBuilder: HWPLineInfoBuilder) : HWPShapeComponentNormalBuilder = this.apply {
        (shapeComponent as HWPShapeComponentNormal).lineInfo = lineInfoBuilder.build()
    }

    fun setFillInfo(fillInfoBuilder: HWPFillInfoBuilder) : HWPShapeComponentNormalBuilder = this.apply {
        (shapeComponent as HWPShapeComponentNormal).fillInfo = fillInfoBuilder.build()
    }

    fun setShadowInfo(shadowInfoBuilder: HWPShadowInfoBuilder) : HWPShapeComponentNormalBuilder = this.apply {
        (shapeComponent as HWPShapeComponentNormal).shadowInfo = shadowInfoBuilder.build()
    }

    override fun build(): HWPShapeComponentNormal = shapeComponent as HWPShapeComponentNormal
}

class HWPShapeComponentContainerBuilder : HWPShapeComponentBuilder() {
    fun setChildControlIDList(childControlIDBuilder: ChildControlIDBuilder) : HWPShapeComponentContainerBuilder = this.apply {
        (shapeComponent as HWPShapeComponentContainer).childControlIdList = childControlIDBuilder.build()
    }

    override fun build(): HWPShapeComponentContainer = shapeComponent as HWPShapeComponentContainer
}

class ChildControlIDBuilder : HWPBuilder<ArrayList<Long>> {
    private val childControlIDList : ArrayList<Long> = ArrayList()

    fun addChildControlID(childControlID: Long) : ChildControlIDBuilder = this.apply {
        childControlIDList.add(childControlID)
    }

    override fun build(): ArrayList<Long> = childControlIDList
}