package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.bodytext.paragraph.HWPParagraphListBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.ctrlheader.sectiondefine.HWPTextDirection
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPLineChange
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPTextVerticalAlignment
import com.tang.hwplib.objects.bodytext.control.table.*

class HWPRowBuilder : HWPBuilder<HWPRow> {
    private val row: HWPRow = HWPRow.build()

    fun setCellList(cellListBuilder: HWPCellListBuilder) : HWPRowBuilder = this.apply {
        row.cellList = cellListBuilder.build()
    }

    override fun build(): HWPRow = row
}


class HWPZoneInfoBuilder : HWPBuilder<HWPZoneInfo> {
    private val zoneInfo : HWPZoneInfo = HWPZoneInfo.build()

    fun setStartColumn(startColumn: Int) : HWPZoneInfoBuilder = this.apply {
        zoneInfo.startColumn = startColumn
    }

    fun setStartRow(startRow: Int) : HWPZoneInfoBuilder = this.apply {
        zoneInfo.startRow = startRow
    }

    fun setEndColumn(endColumn: Int) : HWPZoneInfoBuilder = this.apply {
        zoneInfo.endColumn = endColumn
    }

    fun setEndRow(endRow: Int) : HWPZoneInfoBuilder = this.apply {
        zoneInfo.endRow = endRow
    }

    fun setBorderFillID(borderFillID: Int) : HWPZoneInfoBuilder = this.apply {
        zoneInfo.borderFillId = borderFillID
    }

    override fun build(): HWPZoneInfo = zoneInfo
}

class HWPCellListBuilder : HWPBuilder<ArrayList<HWPCell>> {
    private val cellList : ArrayList<HWPCell> = ArrayList()

    fun addCell(cellBuilder: HWPCellBuilder) : HWPCellListBuilder = this.apply {
        cellList.add(cellBuilder.build())
    }

    override fun build(): ArrayList<HWPCell> = cellList
}

class HWPCellBuilder : HWPBuilder<HWPCell> {
    private val cell : HWPCell = HWPCell.build()

    fun setListHeader(listHeaderBuilder: ListHeaderForCellBuilder) : HWPCellBuilder = this.apply {
        cell.listHeader = listHeaderBuilder.build()
    }

    fun setParagraphList(paragraphListBuilder: HWPParagraphListBuilder) : HWPCellBuilder = this.apply {
        cell.paragraphList = paragraphListBuilder.build()
    }

    override fun build(): HWPCell = cell
}

class ListHeaderForCellBuilder : HWPBuilder<ListHeaderForCell> {
    private val header : ListHeaderForCell = ListHeaderForCell.build()

    fun setParaCount(paraCount: Int) : ListHeaderForCellBuilder = this.apply {
        header.paraCount = paraCount
    }

    fun setProperty(propertyBuilder: ListHeaderPropertyForCellBuilder) : ListHeaderForCellBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    fun setColIndex(colIndex: Int) : ListHeaderForCellBuilder = this.apply {
        header.colIndex = colIndex
    }

    fun setRowIndex(rowIndex: Int) : ListHeaderForCellBuilder = this.apply {
        header.rowIndex = rowIndex
    }

    fun setColSpan(colSpan: Int) : ListHeaderForCellBuilder = this.apply {
        header.colSpan = colSpan
    }

    fun setRowSpan(rowSpan: Int) : ListHeaderForCellBuilder = this.apply {
        header.rowSpan = rowSpan
    }

    fun setWidth(width: Long) : ListHeaderForCellBuilder = this.apply {
        header.width = width
    }

    fun setHeight(height: Long) : ListHeaderForCellBuilder = this.apply {
        header.height = height
    }

    fun setLeftMargin(leftMargin: Int) : ListHeaderForCellBuilder = this.apply {
        header.leftMargin = leftMargin
    }

    fun setRightMargin(rightMargin: Int) : ListHeaderForCellBuilder = this.apply {
        header.rightMargin = rightMargin
    }

    fun setTopMargin(topMargin: Int) : ListHeaderForCellBuilder = this.apply {
        header.topMargin = topMargin
    }

    fun setBottomMargin(bottomMargin: Int) : ListHeaderForCellBuilder = this.apply {
        header.bottomMargin = bottomMargin
    }

    fun setBorderFillID(borderFillID: Int) : ListHeaderForCellBuilder = this.apply {
        header.borderFillId = borderFillID
    }

    fun setTextWidth(textWidth: Long) : ListHeaderForCellBuilder = this.apply {
        header.textWidth = textWidth
    }

    fun setFieldName(fieldName: String) : ListHeaderForCellBuilder = this.apply {
        header.fieldName = fieldName
    }

    override fun build(): ListHeaderForCell = header
}

class ListHeaderPropertyForCellBuilder : HWPBuilder<ListHeaderPropertyForCell> {
    private val property: ListHeaderPropertyForCell = ListHeaderPropertyForCell.build()

    fun setTextDirection(textDirection: HWPTextDirection) : ListHeaderPropertyForCellBuilder = this.apply {
        property.setTextDirection(textDirection)
    }

    fun setLineChange(lineChange: HWPLineChange) : ListHeaderPropertyForCellBuilder = this.apply {
        property.setLineChange(lineChange)
    }

    fun setTextVerticalAlignment(textVerticalAlignment: HWPTextVerticalAlignment) : ListHeaderPropertyForCellBuilder = this.apply {
        property.setTextVerticalAlignment(textVerticalAlignment)
    }

    fun setProtectCell(protectCell: Boolean) : ListHeaderPropertyForCellBuilder = this.apply {
        property.setProtectCell(protectCell)
    }

    fun setEditableAtFormMode(editableAtFormMode: Boolean) : ListHeaderPropertyForCellBuilder = this.apply {
        property.setEditableAtFormMode(editableAtFormMode)
    }

    override fun build(): ListHeaderPropertyForCell = property
}