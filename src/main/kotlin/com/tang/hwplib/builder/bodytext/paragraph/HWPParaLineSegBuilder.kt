package com.tang.hwplib.builder.bodytext.paragraph

import com.tang.hwplib.objects.bodytext.paragraph.linesegment.HWPLineSegItem
import com.tang.hwplib.objects.bodytext.paragraph.linesegment.HWPLineSegItemTag
import com.tang.hwplib.objects.bodytext.paragraph.linesegment.HWPParaLineSeg

internal fun buildEmptyParaLineSeg() : HWPParaLineSeg = HWPParaLineSeg.build(
        lineSegItemGenerator = {
            val lineSeg: ArrayList<HWPLineSegItem> = ArrayList()
            lineSeg.add(HWPLineSegItem.build(
                    lineHeight = 1000, textPartHeight = 1000,
                    distanceBaseLineToLineVerticalPosition = 850,
                    lineSpace = 600, segmentWidth = 42520,
                    tag = HWPLineSegItemTag.build(393216)
            ))
            lineSeg
        }
)