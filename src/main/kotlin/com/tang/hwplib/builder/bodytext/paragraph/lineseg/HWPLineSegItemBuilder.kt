package com.tang.hwplib.builder.bodytext.paragraph.lineseg

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.paragraph.linesegment.HWPLineSegItem
import com.tang.hwplib.objects.bodytext.paragraph.linesegment.HWPLineSegItemTag

class HWPLineSegItemBuilder : HWPBuilder<HWPLineSegItem> {
    private val lineSegItem: HWPLineSegItem = HWPLineSegItem.build()

    fun setTextStartPosition(textStartPosition: Long) : HWPLineSegItemBuilder = this.apply {
        lineSegItem.textStartPosition = textStartPosition
    }

    fun setLineVerticalPosition(lineVerticalPosition: Int) : HWPLineSegItemBuilder = this.apply {
        lineSegItem.lineVerticalPosition = lineVerticalPosition
    }

    fun setTextPartHeight(textPartHeight: Int) : HWPLineSegItemBuilder = this.apply {
        lineSegItem.textPartHeight = textPartHeight
    }

    fun setDistanceBaseLineToLineVerticalPosition(distanceBaseLineToLineVerticalPosition: Int) : HWPLineSegItemBuilder = this.apply {
        lineSegItem.distanceBaseLineToLineVerticalPosition = distanceBaseLineToLineVerticalPosition
    }

    fun setLineSpace(lineSpace: Int) : HWPLineSegItemBuilder = this.apply {
        lineSegItem.lineSpace = lineSpace
    }

    fun setStartPositionFromColumn(startPositionFromColumn: Int) : HWPLineSegItemBuilder = this.apply {
        lineSegItem.startPositionFromColumn = startPositionFromColumn
    }

    fun setSegmentWidth(segmentWidth: Int) : HWPLineSegItemBuilder = this.apply {
        lineSegItem.segmentWidth = segmentWidth
    }

    fun setTag(tagBuilder: HWPLineSegItemTagBuilder) : HWPLineSegItemBuilder = this.apply {
        lineSegItem.tag = tagBuilder.build()
    }

    override fun build(): HWPLineSegItem = lineSegItem
}

class HWPLineSegItemTagBuilder: HWPBuilder<HWPLineSegItemTag> {
    private val lineSegItemTag: HWPLineSegItemTag = HWPLineSegItemTag.build()

    fun setValue(value: Long) : HWPLineSegItemTagBuilder = this.apply {
        lineSegItemTag.value = value
    }

    fun setFirstLineAtPage(firstLineAtPage: Boolean) : HWPLineSegItemTagBuilder = this.apply {
        lineSegItemTag.setFirstLineAtPage(firstLineAtPage)
    }

    fun setFirstLineAtColumn(firstLineAtColumn: Boolean) : HWPLineSegItemTagBuilder = this.apply {
        lineSegItemTag.setFirstLineAtColumn(firstLineAtColumn)
    }

    fun setEmptySegment(emptySegment: Boolean) : HWPLineSegItemTagBuilder = this.apply {
        lineSegItemTag.setEmptySegment(emptySegment)
    }

    fun setFirstSegmentAtLine(firstSegmentAtLine: Boolean) : HWPLineSegItemTagBuilder = this.apply {
        lineSegItemTag.setFirstSegmentAtLine(firstSegmentAtLine)
    }

    fun setLastSegmentAtLine(lastSegmentAtLine: Boolean) : HWPLineSegItemTagBuilder = this.apply {
        lineSegItemTag.setLastSegmentAtLine(lastSegmentAtLine)
    }

    fun setAutoHyphenation(autoHyphenation: Boolean) : HWPLineSegItemTagBuilder = this.apply {
        lineSegItemTag.setAutoHyphenation(autoHyphenation)
    }

    fun setAdjustIndentation(adjustIndentation: Boolean) : HWPLineSegItemTagBuilder = this.apply {
        lineSegItemTag.setAdjustIndentation(adjustIndentation)
    }

    fun setAdjustBullet(adjustBullet: Boolean) : HWPLineSegItemTagBuilder = this.apply {
        lineSegItemTag.setAdjustBullet(adjustBullet)
    }

    fun setBit31(bit31: Boolean) : HWPLineSegItemTagBuilder = this.apply {
        lineSegItemTag.setBit31(bit31)
    }

    override fun build(): HWPLineSegItemTag = lineSegItemTag
}