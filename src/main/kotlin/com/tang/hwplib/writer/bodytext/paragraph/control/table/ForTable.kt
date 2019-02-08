package com.tang.hwplib.writer.bodytext.paragraph.control.table

import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterSet
import com.tang.hwplib.objects.bodytext.control.table.HWPCell
import com.tang.hwplib.objects.bodytext.control.table.HWPTable
import com.tang.hwplib.objects.etc.LIST_HEADER
import com.tang.hwplib.objects.etc.TABLE
import com.tang.hwplib.writer.util.StreamWriter
import com.tang.hwplib.writer.bodytext.paragraph.control.bookmark.ForParameterSet
import com.tang.hwplib.writer.bodytext.paragraph.forParagraphList

/**
 * 셀 속성 [HWPCell]을 쓰는 함수
 *
 * @author accforaus
 *
 * @param [cell] [HWPCell], 셀 속성 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forCell(cell: HWPCell, sw: StreamWriter) {
    /**
     * 셀 속성의 전체 크기를 반환하는 함수
     *
     * @param [psFieldName] [HWPParameterSet], 파라미터 필드 이름
     * @return [Int] 셀 속성의 전체 크기 반환
     */
    fun getSize(psFieldName: HWPParameterSet?): Int {
        var size: Int = 0
        size += 38
        if (psFieldName != null) {
            size += 1
            size += ForParameterSet.getSize(psFieldName)
        } else size += 1
        size += 8
        return size
    }
    cell.listHeader.run {
        val psFieldName: HWPParameterSet? = HWPParameterSet.createForFieldName(fieldName)
        sw.writeRecordHeader(LIST_HEADER.toInt(), getSize(psFieldName))

        sw.writeInt32(paraCount)
        sw.writeUInt32(property.value)
        sw.writeUInt16(colIndex)
        sw.writeUInt16(rowIndex)
        sw.writeUInt16(colSpan)
        sw.writeUInt16(rowSpan)
        sw.writeUInt32(width)
        sw.writeUInt32(height)
        sw.writeUInt16(leftMargin)
        sw.writeUInt16(rightMargin)
        sw.writeUInt16(topMargin)
        sw.writeUInt16(bottomMargin)
        sw.writeUInt16(borderFillId)
        sw.writeUInt32(textWidth)
        if (psFieldName != null) {
            val flag: Short= 0xff
            sw.writeUInt8(flag)
            ForParameterSet.write(psFieldName, sw)
        } else {
            val flag: Short = 0x0
            sw.writeUInt8(flag)
        }
        sw.writeZero(8)
    }
    forParagraphList(cell.paragraphList, sw)
}

/**
 * 표 개체 속성 [HWPTable]을 쓰는 함수
 *
 * @author accforaus
 *
 * @param [table] [HWPTable], 표 개체 속성 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forTable(table: HWPTable, sw: StreamWriter) {
    fun getSize(): Int {
        var size: Int = 0
        size += 18
        size += 2 * table.rowCount
        size += 2
        if (sw.version.isOver(5,0,1,0)) {
            size += 2
            size += 10 * table.zoneInfoList.size
        }
        return size
    }

    sw.writeRecordHeader(TABLE.toInt(), getSize())

    table.run {
        sw.writeUInt32(property.value)
        sw.writeUInt16(rowCount)
        sw.writeUInt16(columnCount)
        sw.writeUInt16(cellSpacing)
        sw.writeUInt16(leftInnerMargin)
        sw.writeUInt16(rightInnerMargin)
        sw.writeUInt16(topInnerMargin)
        sw.writeUInt16(bottomInnerMargin)

        for (index in 0 until rowCount) sw.writeUInt16(cellCountOfRowList[index])
        sw.writeUInt16(borderFillId)

        if (sw.version.isOver(5,0,1,0)) {
            sw.writeUInt16(zoneInfoList.size)
            for (zi in zoneInfoList) {
                sw.writeUInt16(zi.startColumn)
                sw.writeUInt16(zi.startRow)
                sw.writeUInt16(zi.endColumn)
                sw.writeUInt16(zi.endRow)
                sw.writeUInt16(zi.borderFillId)
            }
        }
    }
}