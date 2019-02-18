package com.tang.hwplib.builder.bodytext.paragraph.lineseg

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.paragraph.linesegment.HWPLineSegItem
import com.tang.hwplib.objects.bodytext.paragraph.linesegment.HWPLineSegItemTag
import com.tang.hwplib.objects.bodytext.paragraph.linesegment.HWPParaLineSeg

class HWPParaLineSegBuilder : HWPBuilder<HWPParaLineSeg> {
    private var lineSeg: HWPParaLineSeg = HWPParaLineSeg.build()

    fun addLineSegItem(lineSegItemBuilder: HWPLineSegItemBuilder) : HWPParaLineSegBuilder = this.apply {
        lineSeg.lineSegItemList.add(lineSegItemBuilder.build())
    }

    override fun build(): HWPParaLineSeg = lineSeg
}