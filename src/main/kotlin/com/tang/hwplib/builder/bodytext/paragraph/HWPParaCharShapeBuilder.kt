package com.tang.hwplib.builder.bodytext.paragraph

import com.tang.hwplib.objects.bodytext.paragraph.charshape.HWPCharPositionShapeIdPair
import com.tang.hwplib.objects.bodytext.paragraph.charshape.HWPParaCharShape

internal fun buildEmptyParaCharShape() : HWPParaCharShape = HWPParaCharShape.build(
        positionShapeIDPairGenerator = {
            val positionShapeIdPair: ArrayList<HWPCharPositionShapeIdPair> = ArrayList()
            positionShapeIdPair.add(HWPCharPositionShapeIdPair.build(0,0))
            positionShapeIdPair
        }
)