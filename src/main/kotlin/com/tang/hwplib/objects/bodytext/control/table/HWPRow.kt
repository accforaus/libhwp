package com.tang.hwplib.objects.bodytext.control.table

import com.tang.hwplib.objects.docinfo.HWPBorderFill

/**
 *
 */
class HWPRow {
    var cellList: ArrayList<HWPCell> = ArrayList()

    fun addNewCell() : HWPCell = HWPCell().apply { cellList.add(this) }
}

/**
 * 영역 속성을 나타내는 객체
 * 10 bytes
 *
 * @author accforaus
 *
 * @property [startColumn] [Int], 시작 열 주소 (UINT16 - unsigned 2 bytes)
 * @property [startRow] [Int], 시작 행 주소 (UINT16 - unsigned 2 bytes)
 * @property [endColumn] [Int], 끝 열 주소 (UINT16 - unsigned 2 bytes)
 * @property [endRow] [Int], 끝 행 주소 (UINT16 - unsigned 2 bytes)
 * @property [borderFillId] [Int], 테두리 채우기[HWPBorderFill] ID
 */
class HWPZoneInfo {
    var startColumn: Int = 0
    var startRow: Int = 0
    var endColumn: Int = 0
    var endRow: Int = 0
    var borderFillId: Int = 0
}