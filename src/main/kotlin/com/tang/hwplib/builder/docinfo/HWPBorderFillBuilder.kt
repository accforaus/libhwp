package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.docinfo.borderfill.HWPBorderFillPropertyBuilder
import com.tang.hwplib.builder.docinfo.borderfill.HWPEachBorderBuilder
import com.tang.hwplib.builder.docinfo.borderfill.fillinfo.HWPFillInfoBuilder
import com.tang.hwplib.builder.etc.Color4ByteBuilder
import com.tang.hwplib.copyto.docinfo.IDMappingTypes
import com.tang.hwplib.objects.docinfo.HWPBorderFill
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderFillProperty
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderThickness
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.objects.docinfo.borderfill.HWPEachBorder
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPFillInfo
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPFillType
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPatternFill
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPatternType
import com.tang.hwplib.objects.etc.Color4Byte

class HWPBorderFillBuilder(private val docInfo: HWPDocInfo) : HWPDocInfoBuilder() {
    private val borderFill: HWPBorderFill = HWPBorderFill.build()

    fun setProperty(propertyBuilder: HWPBorderFillPropertyBuilder) : HWPBorderFillBuilder = this.apply {
        borderFill.property = propertyBuilder.build()
    }

    fun setLeftBorder(borderBuilder: HWPEachBorderBuilder) : HWPBorderFillBuilder = this.apply {
        borderFill.leftBorder = borderBuilder.build()
    }

    fun setRightBorder(borderBuilder: HWPEachBorderBuilder) : HWPBorderFillBuilder = this.apply {
        borderFill.rightBorder = borderBuilder.build()
    }

    fun setTopBorder(borderBuilder: HWPEachBorderBuilder) : HWPBorderFillBuilder = this.apply {
        borderFill.topBorder = borderBuilder.build()
    }

    fun setBottomBorder(borderBuilder: HWPEachBorderBuilder) : HWPBorderFillBuilder = this.apply {
        borderFill.bottomBorder = borderBuilder.build()
    }

    fun setDiagonalSort(diagonalSort: HWPBorderType) : HWPBorderFillBuilder = this.apply {
        borderFill.diagonalSort = diagonalSort
    }

    fun setDiagonalThickness(diagonalThickness: HWPBorderThickness) : HWPBorderFillBuilder = this.apply {
        borderFill.diagonalThickness = diagonalThickness
    }

    fun setDiagonalColor(colorBuilder: Color4ByteBuilder) : HWPBorderFillBuilder = this.apply {
        borderFill.diagonalColor = colorBuilder.build()
    }

    fun setFillInfo(fillInfoBuilder: HWPFillInfoBuilder) : HWPBorderFillBuilder = this.apply {
        borderFill.fillInfo = fillInfoBuilder.build()
    }

    fun proceed() : Int = build().run {
        docInfo.borderFillList.size
    }

    override fun build(): HWPBorderFill = borderFill.apply {
        docInfo.borderFillList.add(this)
        docInfo.updateIDMappings(IDMappingTypes.BORDERFILL)
    }
}

class HWPBorderFillListBuilder : HWPBuilder<ArrayList<HWPBorderFill>> {
    private val borderFillList: ArrayList<HWPBorderFill> = ArrayList()

    fun addBorderFill(borderFillBuilder: HWPBorderFillBuilder) : HWPBorderFillListBuilder = this.apply {
        borderFillList.add(borderFillBuilder.build())
    }

    override fun build(): ArrayList<HWPBorderFill> = borderFillList
}