package com.tang.hwplib.builder.bodytext.paragraph.header

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.paragraph.header.HWPControlMask
import com.tang.hwplib.objects.bodytext.paragraph.header.HWPDivideSort
import com.tang.hwplib.objects.bodytext.paragraph.header.HWPParaHeader

class HWPParaHeadBuilder : HWPBuilder<HWPParaHeader> {
    private val header: HWPParaHeader = HWPParaHeader.build()

    fun setLastInList(lastInList: Boolean) : HWPParaHeadBuilder = this.apply {
        header.lastInList = lastInList
    }

    fun setCharacterCount(characterCount: Long) : HWPParaHeadBuilder = this.apply {
        header.characterCount= characterCount
    }

    fun setParaShapeID(paraShapeID: Int) : HWPParaHeadBuilder = this.apply {
        header.paraShapeId = paraShapeID
    }

    fun setStyleID(styleID: Short) : HWPParaHeadBuilder = this.apply {
        header.styleId = styleID
    }

    fun setDivideSort(divideSortBuilder: HWPDivideSortBuilder) : HWPParaHeadBuilder = this.apply {
        header.divideSort = divideSortBuilder.build()
    }

    fun setCharShapeCount(charShapeCount: Int) : HWPParaHeadBuilder = this.apply {
        header.charShapeCount = charShapeCount
    }

    fun setRangeTagCount(rangeTagCount: Int) : HWPParaHeadBuilder = this.apply {
        header.rangeTagCount = rangeTagCount
    }

    fun setLineAlignCount(lineAlignCount: Int) : HWPParaHeadBuilder = this.apply {
        header.lineAlignCount = lineAlignCount
    }

    fun setIsMergedByTrack(isMergedByTrack: Int) : HWPParaHeadBuilder = this.apply {
        header.isMergedByTrack = isMergedByTrack
    }

    override fun build(): HWPParaHeader = header
}