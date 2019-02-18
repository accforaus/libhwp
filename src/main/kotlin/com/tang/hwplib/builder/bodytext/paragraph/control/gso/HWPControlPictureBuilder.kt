package com.tang.hwplib.builder.bodytext.paragraph.control.gso

import com.tang.hwplib.builder.bodytext.paragraph.control.HWPCtrlDataBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.HWPGsoControlBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.ctrlheader.HWPCtrlHeaderGsoBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPCaptionBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPLineInfoPropertyBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPShapeComponentNormalBuilder
import com.tang.hwplib.builder.docinfo.borderfill.fillinfo.HWPPictureInfoBuilder
import com.tang.hwplib.builder.etc.Color4ByteBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.control.gso.HWPControlPicture
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentPicture
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.picture.*

class HWPControlPictureBuilder : HWPGsoControlBuilder() {
    private val control : HWPControlPicture = HWPControlPicture.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderGsoBuilder) : HWPControlPictureBuilder = this.apply {
        control.header = headerBuilder.build().apply {
            ctrlId = HWPControlType.Gso.ctrlId
        }
    }

    fun setShapeComponent(shapeComponentBuilder: HWPShapeComponentNormalBuilder) : HWPControlPictureBuilder = this.apply {
        control.shapeComponent = shapeComponentBuilder.build()
    }

    fun setShapeComponentPicture(shapeComponentPictureBuilder: HWPShapeComponentPictureBuilder) : HWPControlPictureBuilder = this.apply {
        control.shapeComponentPicture = shapeComponentPictureBuilder.build()
    }

    fun setCaption(captionBuilder: HWPCaptionBuilder) : HWPControlPictureBuilder = this.apply {
        control.caption = captionBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlPictureBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlPicture = control
}

class HWPShapeComponentPictureBuilder : HWPBuilder<HWPShapeComponentPicture> {
    private val shapeComponent : HWPShapeComponentPicture = HWPShapeComponentPicture.build()

    fun setBorderColor(colorBuilder: Color4ByteBuilder) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.borderColor = colorBuilder.build()
    }

    fun setBorderThickness(borderThickness: Int) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.borderThickness = borderThickness
    }

    fun setBorderProperty(borderPropertyBuilder: HWPLineInfoPropertyBuilder) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.borderProperty = borderPropertyBuilder.build()
    }

    fun setLeftTop(positionBuilder: HWPPositionXYBuilder) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.leftTop = positionBuilder.build()
    }

    fun setRightTop(positionBuilder: HWPPositionXYBuilder) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.rightTop = positionBuilder.build()
    }

    fun setLeftBottom(positionBuilder: HWPPositionXYBuilder) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.leftBottom = positionBuilder.build()
    }

    fun setRightBottom(positionBuilder: HWPPositionXYBuilder) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.rightBottom = positionBuilder.build()
    }

    fun setLeftAfterCutting(leftAfterCutting: Int) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.leftAfterCutting = leftAfterCutting
    }

    fun setTopAfterCutting(topAfterCutting: Int) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.topAfterCutting = topAfterCutting
    }

    fun setRightAfterCutting(rightAfterCutting: Int) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.rightAfterCutting = rightAfterCutting
    }

    fun setBottomAfterCutting(bottomAfterCutting: Int) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.bottomAfterCutting = bottomAfterCutting
    }

    fun setInnerMargin(innerMarginBuilder: HWPInnerMarginBuilder) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.innerMargin = innerMarginBuilder.build()
    }

    fun setPictureInfo(pictureInfoBuilder: HWPPictureInfoBuilder) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.pictureInfo = pictureInfoBuilder.build()
    }

    fun setBorderTransparency(borderTransparency: Short) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.borderTransparency = borderTransparency
    }

    fun setPictureEffect(pictureEffectBuilder: HWPPictureEffectBuilder) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.pictureEffect = pictureEffectBuilder.build()
    }

    fun setImageWidth(imageWidth: Long) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.imageWidth = imageWidth
    }

    fun setImageHeight(imageHeight: Long) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.imageHeight = imageHeight
    }

    fun setImageTransparency(imageTransparency: Byte) : HWPShapeComponentPictureBuilder = this.apply {
        shapeComponent.imageTransparency = imageTransparency
    }

    override fun build(): HWPShapeComponentPicture = shapeComponent
}

class HWPPictureEffectBuilder : HWPBuilder<HWPPictureEffect> {
    private val picture : HWPPictureEffect = HWPPictureEffect.build()

    fun setProperty(propertyBuilder: HWPPictureEffectPropertyBuilder) : HWPPictureEffectBuilder = this.apply {
        picture.property = propertyBuilder.build()
    }

    fun setShadowEffect(shadowEffectBuilder: HWPShadowEffectBuilder) : HWPPictureEffectBuilder = this.apply {
        picture.shadowEffect = shadowEffectBuilder.build()
    }

    fun setNeonEffect(neonEffectBuilder: HWPNeonEffectBuilder) : HWPPictureEffectBuilder = this.apply {
        picture.neonEffect = neonEffectBuilder.build()
    }

    fun setSoftEdgeEffect(softEdgeEffectBuilder: HWPSoftEdgeEffectBuilder) : HWPPictureEffectBuilder = this.apply {
        picture.softEdgeEffect = softEdgeEffectBuilder.build()
    }

    fun setReflectionEffect(reflectionEffectBuilder: HWPReflectionEffectBuilder) : HWPPictureEffectBuilder = this.apply {
        picture.reflectionEffect = reflectionEffectBuilder.build()
    }

    override fun build(): HWPPictureEffect = picture
}

class HWPPictureEffectPropertyBuilder : HWPBuilder<HWPPictureEffectProperty> {
    private val property : HWPPictureEffectProperty = HWPPictureEffectProperty.build()

    fun setValue(value: Long) : HWPPictureEffectPropertyBuilder = this.apply {
        property.value = value
    }

    fun setShadowEffect(shadowEffect: Boolean) : HWPPictureEffectPropertyBuilder = this.apply {
        property.setShadowEffect(shadowEffect)
    }

    fun setNeonEffect(neonEffect: Boolean) : HWPPictureEffectPropertyBuilder = this.apply {
        property.setNeonEffect(neonEffect)
    }

    fun setSoftBorderEffect(softBorderEffect: Boolean) : HWPPictureEffectPropertyBuilder = this.apply {
        property.setSoftBorderEffect(softBorderEffect)
    }

    fun setReflectionEffect(reflectionEffect: Boolean) : HWPPictureEffectPropertyBuilder = this.apply {
        property.setReflectionEffect(reflectionEffect)
    }

    override fun build(): HWPPictureEffectProperty = property
}

class HWPSoftEdgeEffectBuilder : HWPBuilder<HWPSoftEdgeEffect> {
    private val softEdge: HWPSoftEdgeEffect = HWPSoftEdgeEffect.build()

    fun setRadius(radius: Float) : HWPSoftEdgeEffectBuilder = this.apply {
        softEdge.radius = radius
    }

    override fun build(): HWPSoftEdgeEffect = softEdge
}

class HWPShadowEffectBuilder : HWPBuilder<HWPShadowEffect> {
    private val shadow: HWPShadowEffect = HWPShadowEffect.build()

    fun setStyle(style: Int) : HWPShadowEffectBuilder = this.apply {
        shadow.style = style
    }

    fun setTransparency(transparency: Float) : HWPShadowEffectBuilder = this.apply {
        shadow.transparency = transparency
    }

    fun setCloudy(cloudy: Float) : HWPShadowEffectBuilder = this.apply {
        shadow.cloudy = cloudy
    }

    fun setDirection(direction: Float) : HWPShadowEffectBuilder = this.apply {
        shadow.direction = direction
    }

    fun setDistance(distance: Float) : HWPShadowEffectBuilder = this.apply {
        shadow.distance = distance
    }

    fun setSort(sort: Int) : HWPShadowEffectBuilder = this.apply {
        shadow.sort = sort
    }

    fun setTiltAngleX(tiltAngleX: Float) : HWPShadowEffectBuilder = this.apply {
        shadow.tiltAngleX = tiltAngleX
    }

    fun setTiltAngleY(tiltAngleY: Float) : HWPShadowEffectBuilder = this.apply {
        shadow.tiltAngleY = tiltAngleY
    }

    fun setZoomRateX(zoomRateX: Float) : HWPShadowEffectBuilder = this.apply {
        shadow.zoomRateX = zoomRateX
    }

    fun setZoomRateY(zoomRateY: Float) : HWPShadowEffectBuilder = this.apply {
        shadow.zoomRateY = zoomRateY
    }

    fun setRotateWithShape(rotateWithShape: Int) : HWPShadowEffectBuilder = this.apply {
        shadow.rotateWithShape = rotateWithShape
    }

    fun setColor(colorWithEffectBuilder: HWPColorWithEffectBuilder) : HWPShadowEffectBuilder = this.apply {
        shadow.color = colorWithEffectBuilder.build()
    }

    override fun build(): HWPShadowEffect = shadow
}

class HWPReflectionEffectBuilder : HWPBuilder<HWPReflectionEffect> {
    private val reflection : HWPReflectionEffect = HWPReflectionEffect.build()

    fun setStyle(style: Int) : HWPReflectionEffectBuilder = this.apply {
        reflection.style = style
    }

    fun setRadius(radius: Float) : HWPReflectionEffectBuilder = this.apply {
        reflection.radius = radius
    }

    fun setDirection(direction: Float) : HWPReflectionEffectBuilder = this.apply {
        reflection.direction = direction
    }

    fun setDistance(distance: Float) : HWPReflectionEffectBuilder = this.apply {
        reflection.distance = distance
    }

    fun setTiltAngleX(tiltAngleX: Float) : HWPReflectionEffectBuilder = this.apply {
        reflection.tiltAngleX = tiltAngleX
    }

    fun setTiltAngleY(tiltAngleY: Float) : HWPReflectionEffectBuilder = this.apply {
        reflection.tiltAngleY = tiltAngleY
    }

    fun setZoomRateX(zoomRateX: Float) : HWPReflectionEffectBuilder = this.apply {
        reflection.zoomRateX = zoomRateX
    }

    fun setZoomRateY(zoomRateY: Float) : HWPReflectionEffectBuilder = this.apply {
        reflection.zoomRateY = zoomRateY
    }

    fun setRotationStyle(rotationStyle: Int) : HWPReflectionEffectBuilder = this.apply {
        reflection.rotationStyle = rotationStyle
    }

    fun setStartPosition(startPosition: Float) : HWPReflectionEffectBuilder = this.apply {
        reflection.startPosition = startPosition
    }

    fun setStartTransparency(startTransparency: Float) : HWPReflectionEffectBuilder = this.apply {
        reflection.startTransparency = startTransparency
    }

    fun setEndTransparency(endTransparency: Float) : HWPReflectionEffectBuilder = this.apply {
        reflection.endTransparency = endTransparency
    }

    fun setEndPosition(endPosition: Float) : HWPReflectionEffectBuilder = this.apply {
        reflection.endPosition = endPosition
    }

    fun setOffsetDirection(offsetDirection: Float) : HWPReflectionEffectBuilder = this.apply {
        reflection.offsetDirection = offsetDirection
    }

    override fun build(): HWPReflectionEffect = reflection
}

class HWPNeonEffectBuilder : HWPBuilder<HWPNeonEffect> {
    private val neonEffect : HWPNeonEffect = HWPNeonEffect.build()

    fun setTransparency(transparency: Float) : HWPNeonEffectBuilder = this.apply {
        neonEffect.transparency = transparency
    }

    fun setRadius(radius: Float) : HWPNeonEffectBuilder = this.apply {
        neonEffect.radius = radius
    }

    fun setColor(colorWithEffectBuilder: HWPColorWithEffectBuilder) : HWPNeonEffectBuilder = this.apply {
        neonEffect.color = colorWithEffectBuilder.build()
    }

    override fun build(): HWPNeonEffect = neonEffect
}

class HWPInnerMarginBuilder : HWPBuilder<HWPInnerMargin> {
    private val innerMargin: HWPInnerMargin = HWPInnerMargin.build()

    fun setLeft(left: Int) : HWPInnerMarginBuilder = this.apply {
        innerMargin.left = left
    }

    fun setRight(right: Int) : HWPInnerMarginBuilder = this.apply {
        innerMargin.right = right
    }

    fun setTop(top: Int) : HWPInnerMarginBuilder = this.apply {
        innerMargin.top = top
    }

    fun setBottom(bottom: Int) : HWPInnerMarginBuilder = this.apply {
        innerMargin.bottom = bottom
    }

    override fun build(): HWPInnerMargin = innerMargin
}

class HWPColorWithEffectBuilder : HWPBuilder<HWPColorWithEffect> {
    private val colorWithEffect: HWPColorWithEffect = HWPColorWithEffect.build()

    fun setType(type: Int) : HWPColorWithEffectBuilder = this.apply {
        colorWithEffect.type = type
    }

    fun setColor(color: ByteArray) : HWPColorWithEffectBuilder = this.apply {
        colorWithEffect.color = color
    }

    fun setColorEffectList(colorEffectListBuilder: HWPColorEffectListBuilder) : HWPColorWithEffectBuilder = this.apply {
        colorWithEffect.colorEffectList = colorEffectListBuilder.build()
    }

    override fun build(): HWPColorWithEffect = colorWithEffect
}

class HWPColorEffectListBuilder : HWPBuilder<ArrayList<HWPColorEffect>> {
    private val colorList : ArrayList<HWPColorEffect> = ArrayList()

    fun addColor(colorEffectBuilder: HWPColorEffectBuilder) : HWPColorEffectListBuilder = this.apply {
        colorList.add(colorEffectBuilder.build())
    }

    override fun build(): ArrayList<HWPColorEffect> = colorList
}

class HWPColorEffectBuilder : HWPBuilder<HWPColorEffect> {
    private val colorEffect: HWPColorEffect = HWPColorEffect.build()

    fun setSort(sort: Int) : HWPColorEffectBuilder = this.apply {
        colorEffect.sort = sort
    }

    fun setValue(value: Float) : HWPColorEffectBuilder = this.apply {
        colorEffect.value = value
    }

    override fun build(): HWPColorEffect = colorEffect
}