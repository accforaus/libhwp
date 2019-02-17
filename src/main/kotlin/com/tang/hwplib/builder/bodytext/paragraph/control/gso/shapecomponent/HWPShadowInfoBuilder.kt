package com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent

import com.tang.hwplib.builder.etc.Color4ByteBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.shadow.HWPShadowInfo
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.shadow.HWPShadowType

class HWPShadowInfoBuilder : HWPBuilder<HWPShadowInfo> {
    private val shadowInfo: HWPShadowInfo = HWPShadowInfo.build()

    fun setType(type: HWPShadowType) : HWPShadowInfoBuilder = this.apply {
        shadowInfo.type = type
    }

    fun setColor(colorBuilder: Color4ByteBuilder) : HWPShadowInfoBuilder = this.apply {
        shadowInfo.color = colorBuilder.build()
    }

    fun setOffsetX(offsetX: Int) : HWPShadowInfoBuilder = this.apply {
        shadowInfo.offsetX = offsetX
    }

    fun setOffsetY(offsetY: Int) : HWPShadowInfoBuilder = this.apply {
        shadowInfo.offsetY = offsetY
    }

    fun setTransparent(transparent: Short) : HWPShadowInfoBuilder = this.apply {
        shadowInfo.transparent = transparent
    }

    override fun build(): HWPShadowInfo = shadowInfo
}