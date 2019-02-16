package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.etc.Color4ByteBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlColumnDefine
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderColumnDefine
import com.tang.hwplib.objects.bodytext.control.ctrlheader.columndefine.HWPColumnDefineHeaderProperty
import com.tang.hwplib.objects.bodytext.control.ctrlheader.columndefine.HWPColumnDirection
import com.tang.hwplib.objects.bodytext.control.ctrlheader.columndefine.HWPColumnInfo
import com.tang.hwplib.objects.bodytext.control.ctrlheader.columndefine.HWPColumnSort
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderThickness
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.objects.etc.Color4Byte

class HWPControlColumnDefineBuilder : HWPControlBuilder<HWPControlColumnDefine> {
    private val control : HWPControlColumnDefine = HWPControlColumnDefine.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderColumnDefineBuilder) : HWPControlColumnDefineBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlColumnDefineBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlColumnDefine = control
}

class HWPCtrlHeaderColumnDefineBuilder : HWPBuilder<HWPCtrlHeaderColumnDefine> {
    private val header : HWPCtrlHeaderColumnDefine = HWPCtrlHeaderColumnDefine.build()

    fun setProperty(propertyBuilder: HWPColumnDefineHeaderPropertyBuilder) : HWPCtrlHeaderColumnDefineBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    fun setGapBetweenColumn(gapBetweenColumn: Int) : HWPCtrlHeaderColumnDefineBuilder = this.apply {
        header.gapBetweenColumn = gapBetweenColumn
    }

    fun setProperty2(property2: Int) : HWPCtrlHeaderColumnDefineBuilder = this.apply {
        header.property2 = property2
    }

    fun setColumnInfoList(columnInfoListBuilder: HWPColumnInfoListBuilder) : HWPCtrlHeaderColumnDefineBuilder = this.apply {
        header.columnInfoList = columnInfoListBuilder.build()
    }

    fun setDivideLineSort(divideLineSort: HWPBorderType) : HWPCtrlHeaderColumnDefineBuilder = this.apply {
        header.divideLineSort = divideLineSort
    }

    fun setDivideLineThickness(divideLineThickness: HWPBorderThickness) : HWPCtrlHeaderColumnDefineBuilder = this.apply {
        header.divideLineThickness = divideLineThickness
    }

    fun setDivideLineColor(colorBuilder: Color4ByteBuilder) : HWPCtrlHeaderColumnDefineBuilder = this.apply {
        header.divideLineColor = colorBuilder.build()
    }

    override fun build(): HWPCtrlHeaderColumnDefine = header
}

class HWPColumnDefineHeaderPropertyBuilder : HWPBuilder<HWPColumnDefineHeaderProperty> {
    private val property: HWPColumnDefineHeaderProperty = HWPColumnDefineHeaderProperty.build()

    fun setColumnSort(columnSort: HWPColumnSort) : HWPColumnDefineHeaderPropertyBuilder = this.apply {
        property.setColumnSort(columnSort)
    }

    fun setColumnCount(columnCount: Short) : HWPColumnDefineHeaderPropertyBuilder = this.apply {
        property.setColumnCount(columnCount)
    }

    fun setColumnDirection(columnDirection: HWPColumnDirection) : HWPColumnDefineHeaderPropertyBuilder = this.apply {
        property.setColumnDirection(columnDirection)
    }

    fun setSameWidth(sameWidth: Boolean) : HWPColumnDefineHeaderPropertyBuilder = this.apply {
        property.setSameWidth(sameWidth)
    }

    override fun build(): HWPColumnDefineHeaderProperty = property
}

class HWPColumnInfoListBuilder : HWPBuilder<ArrayList<HWPColumnInfo>> {
    private val columnInfoList: ArrayList<HWPColumnInfo> = ArrayList()

    fun addColumnInfo(columnInfoBuilder: HWPColumnInfoBuilder) : HWPColumnInfoListBuilder = this.apply {
        columnInfoList.add(columnInfoBuilder.build())
    }

    override fun build(): ArrayList<HWPColumnInfo> = columnInfoList
}

class HWPColumnInfoBuilder : HWPBuilder<HWPColumnInfo> {
    private val columnInfo : HWPColumnInfo = HWPColumnInfo.build()

    fun setWidth(width: Int) : HWPColumnInfoBuilder = this.apply {
        columnInfo.width = width
    }

    fun setGap(gap: Int) : HWPColumnInfoBuilder = this.apply {
        columnInfo.gap = gap
    }

    override fun build(): HWPColumnInfo = columnInfo
}

internal fun buildEmptyColumnDefine() : HWPControlColumnDefine = HWPControlColumnDefine.build().apply {
    header = HWPCtrlHeaderColumnDefine.build(
            property = HWPColumnDefineHeaderProperty.build(4100),
            divideLineSort = HWPBorderType.Solid,
            divideLineThickness = HWPBorderThickness.MM0_1
    )
    setCtrlData(null)
}