package com.tang.hwplib.builder.bodytext.paragraph.charshape

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.paragraph.charshape.HWPCharPositionShapeIdPair
import com.tang.hwplib.objects.bodytext.paragraph.charshape.HWPParaCharShape

class HWPParaCharShapeBuilder : HWPBuilder<HWPParaCharShape> {
    private val paraCharShape: HWPParaCharShape = HWPParaCharShape.build()

    fun addParaCharShape(position: Long, charShapeID: Long) : HWPParaCharShapeBuilder = this.apply {
        paraCharShape.positionShapeIdPairList.add(
                HWPCharPositionShapeIdPairBuilder()
                        .setCharPosition(position)
                        .setCharShapeID(charShapeID).build())
    }

    override fun build(): HWPParaCharShape = paraCharShape
}