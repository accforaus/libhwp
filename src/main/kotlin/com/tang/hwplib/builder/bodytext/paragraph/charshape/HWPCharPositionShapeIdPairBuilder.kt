package com.tang.hwplib.builder.bodytext.paragraph.charshape

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.paragraph.charshape.HWPCharPositionShapeIdPair

class HWPCharPositionShapeIdPairBuilder : HWPBuilder<HWPCharPositionShapeIdPair> {
    private val charPositionShapeIDPair: HWPCharPositionShapeIdPair = HWPCharPositionShapeIdPair.build()

    fun setCharPosition(position: Long) : HWPCharPositionShapeIdPairBuilder = this.apply {
        charPositionShapeIDPair.position = position
    }

    fun setCharShapeID(charShapeID: Long) : HWPCharPositionShapeIdPairBuilder = this.apply {
        charPositionShapeIDPair.shapeId = charShapeID
    }

    override fun build(): HWPCharPositionShapeIdPair = charPositionShapeIDPair
}

