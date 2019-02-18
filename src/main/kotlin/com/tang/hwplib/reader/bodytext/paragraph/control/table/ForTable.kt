package com.tang.hwplib.reader.bodytext.paragraph.control.table

import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterSet
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterType
import com.tang.hwplib.objects.bodytext.control.table.HWPCell
import com.tang.hwplib.objects.bodytext.control.table.HWPTable
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.etc.LIST_HEADER
import com.tang.hwplib.reader.bodytext.paragraph.control.bookmark.ForParameterSet
import com.tang.hwplib.reader.bodytext.paragraph.forParagraphList
import com.tang.hwplib.util.exceptions.HWPReadException
import com.tang.hwplib.reader.util.StreamReader

/**
 * 셀 속성 [HWPCell]을 읽는 함수
 *
 * @author accforaus
 *
 * @throws [HWPReadException]
 *
 * @param [cell] [HWPCell], 빈 셀 속성 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forCell(cell: HWPCell, sr: StreamReader, docInfo : HWPDocInfo) {
    if (!sr.isImmediatelyAfterReadingHeader())
        sr.readRecordHeader()
    if (sr.header.tagId == LIST_HEADER) {
        cell.listHeader.run {
            paraCount = sr.readInt32()
            property.value = sr.readUInt32()
            colIndex = sr.readUInt16()
            rowIndex = sr.readUInt16()
            colSpan = sr.readUInt16()
            rowSpan = sr.readUInt16()
            width = sr.readHwpUnit()
            height = sr.readHwpUnit()
            leftMargin = sr.readUInt16()
            rightMargin = sr.readUInt16()
            topMargin = sr.readUInt16()
            bottomMargin = sr.readUInt16()
            borderFillId = sr.readUInt16()
            textWidth = sr.readUInt32()
            if (sr.header.size > sr.readAfterHeader) {
                if (sr.readUInt8() == 0xff.toShort()) {
                    val ps: HWPParameterSet = HWPParameterSet()
                    ForParameterSet.read(ps, sr)

                    if (ps.id == 0x21b) {
                        for (pi in ps.parameterItemList) {
                            if (pi.id == 0x4000.toLong() && pi.type == HWPParameterType.String)
                                fieldName = pi.value_BSTR
                        }
                    }
                }
                sr.skipToEndRecord()
            }
        }
    } else throw HWPReadException("cell's list header does not exist")
    forParagraphList(cell.paragraphList, sr, docInfo)
}

/**
 * 표 개체 속성 [HWPTable]을 읽는 함수
 *
 * @author accforaus
 *
 * @param [table] [HWPTable], 빈 표 개체 속성 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forTable(table: HWPTable, sr: StreamReader) {
    table.run {
        property.value = sr.readUInt32()
        rowCount = sr.readUInt16()
        columnCount = sr.readUInt16()
        cellSpacing = sr.readUInt16()
        leftInnerMargin = sr.readUInt16()
        rightInnerMargin = sr.readUInt16()
        topInnerMargin = sr.readUInt16()
        bottomInnerMargin = sr.readUInt16()
        for (index in 0 until rowCount) addCellCountOfRow(sr.readUInt16())
        borderFillId = sr.readUInt16()
        if (sr.fileVersion!!.isOver(5, 0, 1, 0)) {
            for (index in 0 until sr.readUInt16()) {
                addNewZoneInfo().also {
                    it.startColumn = sr.readUInt16()
                    it.startRow = sr.readUInt16()
                    it.endColumn = sr.readUInt16()
                    it.endRow = sr.readUInt16()
                    it.borderFillId = sr.readUInt16()
                }
            }
        }
    }
}