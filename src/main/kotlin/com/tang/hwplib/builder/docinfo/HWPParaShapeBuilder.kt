package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.bodytext.paragraph.lineseg.HWPParaLineSegBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.docinfo.parashape.HWPParaShapeProperty1Builder
import com.tang.hwplib.builder.docinfo.parashape.HWPParaShapeProperty2Builder
import com.tang.hwplib.builder.docinfo.parashape.HWPParaShapeProperty3Builder
import com.tang.hwplib.objects.docinfo.HWPParaShape
import com.tang.hwplib.objects.docinfo.parashape.HWPParaShapeProperty1
import com.tang.hwplib.objects.docinfo.parashape.HWPParaShapeProperty2
import com.tang.hwplib.objects.docinfo.parashape.HWPParaShapeProperty3

class HWPParaShapeBuilder : HWPBuilder<HWPParaShape> {
    private val paraShape: HWPParaShape = HWPParaShape.build()

    fun setProperty1(property1Builder: HWPParaShapeProperty1Builder) : HWPParaShapeBuilder = this.apply {
        paraShape.property1 = property1Builder.build()
    }

    fun setLeftMargin(leftMargin: Int) : HWPParaShapeBuilder = this.apply {
        paraShape.leftMargin = leftMargin
    }

    fun setRightMargin(rightMargin: Int) : HWPParaShapeBuilder = this.apply {
        paraShape.rightMargin = rightMargin
    }

    fun setIndent(indent: Int) : HWPParaShapeBuilder = this.apply {
        paraShape.indent = indent
    }

    fun setTopParaShape(topParaShape: Int) : HWPParaShapeBuilder = this.apply {
        paraShape.topParaSpace = topParaShape
    }

    fun setBottomParaShape(bottomParaShape: Int) : HWPParaShapeBuilder = this.apply {
        paraShape.bottomParaSpace = bottomParaShape
    }

    fun setLineSpace(lineSpace: Int) : HWPParaShapeBuilder = this.apply {
        paraShape.lineSpace = lineSpace
    }

    fun setTabDefID(tabDefID: Int) : HWPParaShapeBuilder = this.apply {
        paraShape.tabDefId = tabDefID
    }

    fun setParaHeadID(paraHeadID: Int) : HWPParaShapeBuilder = this.apply {
        paraShape.paraHeadId = paraHeadID
    }

    fun setBorderFillID(borderFillID: Int) : HWPParaShapeBuilder = this.apply {
        paraShape.borderFillId = borderFillID
    }

    fun setLeftBorderSpace(leftBorderSpace: Short) : HWPParaShapeBuilder = this.apply {
        paraShape.leftBorderSpace = leftBorderSpace
    }

    fun setRightBorderSpace(rightBorderSpace: Short) : HWPParaShapeBuilder = this.apply {
        paraShape.rightBorderSpace = rightBorderSpace
    }

    fun setTopBorderSpace(topBorderSpace: Short) : HWPParaShapeBuilder = this.apply {
        paraShape.topBorderSpace = topBorderSpace
    }

    fun setBottomBorderSpace(bottomBorderSpace: Short) : HWPParaShapeBuilder = this.apply {
        paraShape.bottomBorderSpace = bottomBorderSpace
    }

    fun setProperty2(property2Builder: HWPParaShapeProperty2Builder) : HWPParaShapeBuilder = this.apply {
        paraShape.property2 = property2Builder.build()
    }

    fun setProperty3(property3Builder: HWPParaShapeProperty3Builder) : HWPParaShapeBuilder = this.apply {
        paraShape.property3 = property3Builder.build()
    }

    fun setLineSpace2(lineSpace2: Long) : HWPParaShapeBuilder = this.apply {
        paraShape.lineSpace2 = lineSpace2
    }

    override fun build(): HWPParaShape = paraShape
}

class HWPParaShapeListBuilder : HWPBuilder<ArrayList<HWPParaShape>> {
    private val paraShapeList : ArrayList<HWPParaShape> = ArrayList()

    fun addParaShape(paraShapeBuilder: HWPParaShapeBuilder) : HWPParaShapeListBuilder = this.apply {
        paraShapeList.add(paraShapeBuilder.build())
    }

    override fun build(): ArrayList<HWPParaShape> = paraShapeList
}