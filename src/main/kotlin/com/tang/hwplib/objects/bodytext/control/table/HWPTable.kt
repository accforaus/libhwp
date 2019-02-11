package com.tang.hwplib.objects.bodytext.control.table

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set
import com.tang.hwplib.objects.docinfo.HWPBorderFill
/**
 * 쪽 경계에서 나눔 종류
 *
 * @author accforaus
 *
 * @property [value] [Byte], 쪽 경계에서 나눔 종류값
 */
enum class DivideAtPageBoundary(v: Byte) {
    /**
     * 나누지 않음
     */
    NoDivide(0.toByte()),
    /**
     * 셀 단위로 나눔
     */
    DivideByCell(1.toByte()),
    /**
     * 나눔
     */
    Divide(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [DivideAtPageBoundary] enum 값
         */
        fun valueOf(v: Byte) : DivideAtPageBoundary {
            for (dapb in values())
                if (dapb.value == v)
                    return dapb
            return NoDivide
        }
    }
}

/**
 * 표 개체 속성의 속성을 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 표 개체 속성값
 */
class TableProperty {
    var value: Long = 0

    /**
     * 쪽 경계에서 나눔 종류를 반환하는 함수
     * bit 0-1
     *
     * @return [DivideAtPageBoundary] 쪽 경계에서 나눔 종류 반환
     */
    fun getDivideAtPageBoundary() : DivideAtPageBoundary = DivideAtPageBoundary.valueOf(get(value, 0, 1).toByte())

    /**
     * 쪽 경계에서 나눔 종류를 반환하는 함수
     * bit 0-1
     *
     * @param [divideAtPageBoundary] [DivideAtPageBoundary] 쪽 경계에서 나눔 종류값
     */
    fun setDivideAtPageBoundary(divideAtPageBoundary: DivideAtPageBoundary) {
        value = set(value, 0, 1, divideAtPageBoundary.value.toInt())
    }

    /**
     * 제목 줄 자동 반목 여부를 반환하는 함수
     * bit 2
     *
     * @return [Boolean] 제목 줄 자동 반목 여부 반환
     */
    fun isAutoRepeatTitleRow() : Boolean = get(value, 2)

    /**
     * 제목 줄 자동 반목 여부를 반환하는 함수
     * bit 2
     *
     * @param [autoRepeatTitleRow] [Boolean] 제목 줄 자동 반복 여부값
     */
    fun setAutoRepeatTitleRow(autoRepeatTitleRow: Boolean) {
        value = set(value, 2, autoRepeatTitleRow)
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [TableProperty] 생성된 객체 반환
         */
        fun build(divideAtPageBoundary: DivideAtPageBoundary = DivideAtPageBoundary.NoDivide,
                  autoRepeatTitleRow: Boolean = false)
                : TableProperty = TableProperty().apply {
            setDivideAtPageBoundary(divideAtPageBoundary)
            setAutoRepeatTitleRow(autoRepeatTitleRow)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [TableProperty] 생성된 객체 반환
         */
        fun build(value: Long = 0) : TableProperty = TableProperty().apply {
            this.value = value
        }
    }
}

/**
 * 표 개체 속성을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [property] [TableProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [rowCount] [Int], RowCount (UINT16 - unsigned 2 bytes)
 * @property [columnCount] [Int], nCols (UINT16 - unsigned 2 bytes)
 * @property [cellSpacing] [Int], CellSpacing (HWPUNIT16 - unsigned 2 bytes)
 * @property [leftInnerMargin] [Int], 왼쪽 여백 정보 (HWPUNIT16 - unsigned 2 bytes)
 * @property [rightInnerMargin] [Int], 오른쪽 여백 정보 (HWPUINT16 - unsigned 2 bytes)
 * @property [topInnerMargin] [Int], 위쪽 여백 정보 (HWPUNIT16 - unsigned 2 bytes)
 * @property [bottomInnerMargin] [Int], 아래쪽 여백 정보 (HWPUNIT16 - unsigned 2 bytes)
 * @property [cellCountOfRowList] [ArrayList], HWPRow Size (BYTE stream - 2 x unsigned 1 byte)
 * @property [borderFillId] [Int], Border Fill[HWPBorderFill] ID
 * @property [zoneInfoList] [HWPZoneInfo], 영역 속성 [>=5.0.1.0]
 */
class HWPTable {
    var property: TableProperty = TableProperty()
    var rowCount: Int = 0
    var columnCount: Int = 0
    var cellSpacing: Int = 0
    var leftInnerMargin: Int = 0
    var rightInnerMargin: Int = 0
    var topInnerMargin: Int = 0
    var bottomInnerMargin: Int = 0
    var cellCountOfRowList: ArrayList<Int> = ArrayList()
    var borderFillId: Int = 0
    var zoneInfoList: ArrayList<HWPZoneInfo> = ArrayList()

    /**
     * 새로운 HWPRow Count를 추가하는 함수
     *
     * @param [cellCountOfRow] [Int], 추가될 HWPRow Count
     */
    fun addCellCountOfRow(cellCountOfRow: Int) {
        cellCountOfRowList.add(cellCountOfRow)
    }

    /**
     * 새로운 영역 속성을 추가하고 반환하는 함수
     *
     * @return [HWPZoneInfo] 생성된 영역 속성 반환
     */
    fun addNewZoneInfo() : HWPZoneInfo = HWPZoneInfo().apply { zoneInfoList.add(this) }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPTable] 복사된 객체 반환
     */
    fun copy() : HWPTable = HWPTable().also {
        it.property.value = this.property.value
        it.rowCount = this.rowCount
        it.columnCount = this.columnCount
        it.cellSpacing = this.cellSpacing
        it.leftInnerMargin = this.leftInnerMargin
        it.rightInnerMargin = this.rightInnerMargin
        it.topInnerMargin = this.topInnerMargin
        it.bottomInnerMargin = this.bottomInnerMargin
        for (int in this.cellCountOfRowList) it.cellCountOfRowList.add(int)
        it.borderFillId = this.borderFillId
        for (zoneInfo in this.zoneInfoList) it.zoneInfoList.add(zoneInfo.copy())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPTable] 생성된 객체 반환
         */
        fun build(property: TableProperty = TableProperty.build(),
                  rowCount: Int = 0, columnCount: Int = 0,
                  cellSpacing: Int = 0, leftInnerMargin: Int = 0,
                  rightInnerMargin: Int = 0, topInnerMargin: Int = 0,
                  bottomInnerMargin: Int = 0, borderFillId: Int = 0,
                  cellCountOfRowGenerator: () -> ArrayList<Int> = {ArrayList()},
                  zoneInfoGenerator: () -> ArrayList<HWPZoneInfo> = {ArrayList()})
                : HWPTable = HWPTable().apply {
            this.property = property
            this.rowCount = rowCount
            this.columnCount = columnCount
            this.cellSpacing = cellSpacing
            this.leftInnerMargin = leftInnerMargin
            this.rightInnerMargin = rightInnerMargin
            this.topInnerMargin = topInnerMargin
            this.bottomInnerMargin = bottomInnerMargin
            this.cellCountOfRowList = cellCountOfRowGenerator()
            this.borderFillId = borderFillId
            this.zoneInfoList = zoneInfoGenerator()
        }
    }
}