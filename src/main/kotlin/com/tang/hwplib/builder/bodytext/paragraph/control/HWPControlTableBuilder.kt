package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.bodytext.paragraph.HWPParagraphListBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.ctrlheader.HWPCtrlHeaderGsoBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPCaptionBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlTable
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.control.ctrlheader.sectiondefine.HWPTextDirection
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPLineChange
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPTextVerticalAlignment
import com.tang.hwplib.objects.bodytext.control.table.*

class HWPControlTableBuilder : HWPControlBuilder<HWPControlTable> {
    private val control : HWPControlTable = HWPControlTable.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderGsoBuilder) : HWPControlTableBuilder = this.apply {
        control.header = headerBuilder.build().apply {
            ctrlId = HWPControlType.Table.ctrlId
        }
    }

    fun setTable(tableBuilder: HWPTableBuilder) : HWPControlTableBuilder = this.apply {
        control.table = tableBuilder.build()
    }

    fun setRowList(rowListBuilder: HWPRowListBuilder) : HWPControlTableBuilder = this.apply {
        control.rowList = rowListBuilder.build()
    }

    fun setCaption(captionBuilder: HWPCaptionBuilder) : HWPControlTableBuilder = this.apply {
        control.caption = captionBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlTableBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlTable = control
}

class HWPTableBuilder : HWPBuilder<HWPTable> {
    private val table: HWPTable = HWPTable.build()

    fun setProperty(propertyBuilder: TablePropertyBuilder) : HWPTableBuilder = this.apply {
        table.property = propertyBuilder.build()
    }

    fun setRowCount(rowCount: Int) : HWPTableBuilder = this.apply {
        table.rowCount = rowCount
    }

    fun setColumnCount(columnCount: Int) : HWPTableBuilder = this.apply {
        table.columnCount = columnCount
    }

    fun setCellSpacing(cellSpacing: Int) : HWPTableBuilder =  this.apply {
        table.cellSpacing = cellSpacing
    }

    fun setLeftInnerMargin(leftInnerMargin: Int) : HWPTableBuilder = this.apply {
        table.leftInnerMargin = leftInnerMargin
    }

    fun setRightInnerMargin(rightInnerMargin: Int) : HWPTableBuilder = this.apply {
        table.rightInnerMargin = rightInnerMargin
    }

    fun setTopInnerMargin(topInnerMargin: Int) : HWPTableBuilder =  this.apply {
        table.topInnerMargin = topInnerMargin
    }

    fun setBottomInnerMargin(bottomInnerMargin: Int) : HWPTableBuilder = this.apply {
        table.bottomInnerMargin = bottomInnerMargin
    }

    fun setCellCountOfRowList(cellCountBuilder: HWPCellCountOfRowListBuilder) : HWPTableBuilder = this.apply {
        table.cellCountOfRowList = cellCountBuilder.build()
    }

    fun setBorderFillID(borderFillID: Int) : HWPTableBuilder = this.apply {
        table.borderFillId = borderFillID
    }

    fun setZoneInfoList(zoneInfoListBuilder: HWPZoneInfoListBuilder) : HWPTableBuilder = this.apply {
        table.zoneInfoList = zoneInfoListBuilder.build()
    }

    override fun build(): HWPTable = table
}

class HWPCellCountOfRowListBuilder : HWPBuilder<ArrayList<Int>> {
    private val cellCountList: ArrayList<Int> = ArrayList()

    fun addCellCount(cellCount: Int) : HWPCellCountOfRowListBuilder = this.apply {
        cellCountList.add(cellCount)
    }

    override fun build(): ArrayList<Int> = cellCountList
}

class TablePropertyBuilder : HWPBuilder<TableProperty> {
    private val property : TableProperty = TableProperty.build()

    fun setValue(value: Long) : TablePropertyBuilder = this.apply {
        property.value = value
    }

    fun setDivideAtPageBoundray(divideAtPageBoundary: DivideAtPageBoundary) : TablePropertyBuilder = this.apply {
        property.setDivideAtPageBoundary(divideAtPageBoundary)
    }

    fun setAutoRepeatTitleRow(autoRepeatTitleRow: Boolean) : TablePropertyBuilder = this.apply {
        property.setAutoRepeatTitleRow(autoRepeatTitleRow)
    }

    override fun build(): TableProperty = property
}

class HWPRowListBuilder : HWPBuilder<ArrayList<HWPRow>> {
    private val rowList : ArrayList<HWPRow> = ArrayList()

    fun addRow(rowBuilder: HWPRowBuilder) : HWPRowListBuilder = this.apply {
        rowList.add(rowBuilder.build())
    }

    override fun build(): ArrayList<HWPRow> = rowList
}

class HWPRowBuilder : HWPBuilder<HWPRow> {
    private val row: HWPRow = HWPRow.build()

    fun setCellList(cellListBuilder: HWPCellListBuilder) : HWPRowBuilder = this.apply {
        row.cellList = cellListBuilder.build()
    }

    override fun build(): HWPRow = row
}

class HWPZoneInfoListBuilder : HWPBuilder<ArrayList<HWPZoneInfo>> {
    private val zoneInfoList: ArrayList<HWPZoneInfo> = ArrayList()

    fun addZoneInfo(zoneInfoBuilder: HWPZoneInfoBuilder) : HWPZoneInfoListBuilder = this.apply {
        zoneInfoList.add(zoneInfoBuilder.build())
    }

    override fun build(): ArrayList<HWPZoneInfo> = zoneInfoList
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

    fun setValue(value: Long) : ListHeaderPropertyForCellBuilder = this.apply {
        property.value = value
    }

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