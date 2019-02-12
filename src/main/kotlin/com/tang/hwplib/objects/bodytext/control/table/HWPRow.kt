package com.tang.hwplib.objects.bodytext.control.table

import com.tang.hwplib.annotation.ID
import com.tang.hwplib.annotation.IDTypes
import com.tang.hwplib.annotation.LinkID
import com.tang.hwplib.objects.docinfo.HWPBorderFill

/**
 *
 */
class HWPRow {
    var cellList: ArrayList<HWPCell> = ArrayList()

    /**
     * 셀을 추가하고 반환하는 함수
     *
     * @return [HWPCell] 생성된 객체 반환
     */
    fun addNewCell() : HWPCell = HWPCell().apply { cellList.add(this) }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPRow] 복사된 객체 반환
     */
    fun copy() : HWPRow = HWPRow().also {
        for (cell in this.cellList) it.cellList.add(cell.copy())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPRow] 생성된 객체 반환
         */
        fun build(
                cellGenerator: () -> ArrayList<HWPCell> = {ArrayList()}
        ) : HWPRow = HWPRow().apply {
            this.cellList = cellGenerator()
        }
    }
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
@LinkID class HWPZoneInfo {
    var startColumn: Int = 0
    var startRow: Int = 0
    var endColumn: Int = 0
    var endRow: Int = 0
    @ID(IDTypes.BorderFill)
    var borderFillId: Int = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPZoneInfo] 복사된 객체 반환
     */
    fun copy() : HWPZoneInfo = HWPZoneInfo().also {
        it.startColumn = this.startColumn
        it.startRow = this.startRow
        it.endColumn = this.endColumn
        it.endRow = this.endRow
        it.borderFillId = this.borderFillId
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPZoneInfo] 생성된 객체 반환
         */
        fun build(startColumn: Int = 0,
                  startRow: Int = 0,
                  endColumn: Int = 0,
                  endRow: Int = 0,
                  borderFillId: Int = 0)
                : HWPZoneInfo = HWPZoneInfo().apply {
            this.startColumn = startColumn
            this.startRow = startRow
            this.endColumn = endColumn
            this.endRow = endRow
            this.borderFillId = borderFillId
        }
    }
}