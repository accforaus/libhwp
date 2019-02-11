package com.tang.hwplib.objects.bodytext.control.table

import com.tang.hwplib.objects.bodytext.control.ctrlheader.sectiondefine.HWPTextDirection
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPLineChange
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPTextVerticalAlignment
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphList
import com.tang.hwplib.objects.docinfo.HWPBorderFill
import com.tang.hwplib.objects.etc.LIST_HEADER
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 셀 문단 리스트 헤더 속성을 나타내는 객체
 *
 * @author accforaus
 *
 * @property [value] [Long], 셀 문단 리스트 헤더 속성값
 */
class ListHeaderPropertyForCell {
    var value: Long = 0

    /**
     * 텍스트 방향을 반환하는 함수
     * bit 0-2
     *
     * @return [HWPTextDirection] 텍스트 방향 반환
     */
    fun getTextDirection() : HWPTextDirection = HWPTextDirection.valueOf(get(value, 0, 2).toByte())

    /**
     * 텍스트 방향을 설정하는 함수
     * bit 0-2
     *
     * @param [textDirection] [HWPTextDirection] 텍스트 방향 값
     */
    fun setTextDirection(textDirection: HWPTextDirection) {
        value = set(value, 0, 2, textDirection.value.toInt())
    }

    /**
     * 문단의 줄바꿈을 반환하는 함수
     * bit 3-4
     *
     * @return [HWPLineChange] 문단의 줄바꿈 반환
     */
    fun getLineChange() : HWPLineChange = HWPLineChange.valueOf(get(value, 3, 4).toByte())

    /**
     * 문단의 줄바꿈을 설정하는 함수
     * bit 3-4
     *
     * @param [lineChange] [HWPLineChange] 문단의 줄바꿈값
     */
    fun setLineChange(lineChange: HWPLineChange) {
        value = set(value, 3, 4, lineChange.value.toInt())
    }

    /**
     * 세로 정렬을 반환하는 함수
     * bit 5-6
     *
     * @return [HWPTextVerticalAlignment] 세로 정렬 반환
     */
    fun getTextVerticalAlignment() : HWPTextVerticalAlignment = HWPTextVerticalAlignment.valueOf(get(value, 5,6).toByte())

    /**
     * 세로 정렬을 설정하는 함수
     * bit 5-6
     *
     * @param [textVerticalAlignment] [HWPTextVerticalAlignment] 세로 정렬값
     */
    fun setTextVerticalAlignment(textVerticalAlignment: HWPTextVerticalAlignment) {
        value = set(value, 5, 6, textVerticalAlignment.value.toInt())
    }

    /**
     * 셀 보호 여부를 반환하는 함수
     * bit 17
     *
     * @return [Boolean] 셀 보호 여부 반환
     */
    fun isProtectCell() : Boolean = get(value, 17)

    /**
     * 셀 보호 여부를 설정하는 함수
     * bit 17
     *
     * @param [protectCell] [Boolean] 셀 보호 여부값
     */
    fun setProtectCell(protectCell: Boolean) {
        value = set(value, 17, protectCell)
    }

    /**
     * 폼에서 수정가능 여부를 반환하는 함수
     * bit 19
     *
     * @return [Boolean] 폼에서 수정가능 여부 반환
     */
    fun isEditableAtFormMode() : Boolean = get(value, 19)

    /**
     * 폼에서 수정가능 여부를 설정하는 함수
     * bit 19
     *
     * @param [editableAtFormMode] [Boolean] 폼에서 수정가능 여부값
     */
    fun setEditableAtFormMode(editableAtFormMode: Boolean) {
        value = set(value, 19, editableAtFormMode)
    }

    companion object {
        fun build(textDirection: HWPTextDirection = HWPTextDirection.Horizontal,
                  lineChange: HWPLineChange = HWPLineChange.Normal,
                  textVerticalAlignment: HWPTextVerticalAlignment = HWPTextVerticalAlignment.Top,
                  protectCell: Boolean = false, editableAtFormMode: Boolean = false)
                : ListHeaderPropertyForCell = ListHeaderPropertyForCell().apply {
            setTextDirection(textDirection)
            setLineChange(lineChange)
            setTextVerticalAlignment(textVerticalAlignment)
            setProtectCell(protectCell)
            setEditableAtFormMode(editableAtFormMode)
        }

        fun build(value: Long = 0) : ListHeaderPropertyForCell = ListHeaderPropertyForCell().apply {
            this.value = value
        }
    }
}

/**
 * 셀 문단 리스트 헤더를 나타내는 객체
 * Tag ID: HWPTAG_LIST_HEADER [LIST_HEADER]
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [paraCount] [Int] 문단 수 문단 수 (INT16 - signed 2 bytes)
 * @property [property] [ListHeaderForCell], 속성 (BYTE stream - unsigned 26 bytes)
 * @property [colIndex] [Int], 셀 주소(Column, 맨 왼쪽 셀이 0부터 시작하여 1씩 증가) (UINT16 - unsigned 2 bytes)
 * @property [rowIndex] [Int], 셀 주소(HWPRow, 맨 위쪽 셀이 0부터 시작하여 1씩 증가) (UINT16 - unsigned 2 bytes)
 * @property [colSpan] [Int], 열의 병합 개수 (UINT16 - unsigned 2 bytes)
 * @property [rowSpan] [Int], 행의 병합 개수 (UINT16 - unsigned 2 bytes)
 * @property [width] [Long], 셀의 폭 (HWPUNIT - unsigned 4 bytes)
 * @property [height] [Long], 셀의 높이 (HWPUNIT - unsigned 4 bytes)
 * @property [leftMargin] [Int], 셀 왼쪽 여백 (HWPUNIT16 - unsigned 2 bytes)
 * @property [rightMargin] [Int], 셀 오른쪽 여백 (HWPUNIT16 - unsigned 2 bytes)
 * @property [topMargin] [Int], 셀 위쪽 여백 (HWPUNIT16 - unsigned 2 bytes)
 * @property [bottomMargin] [Int], 셀 아래쪽 여백 (HWPUNIT16 - unsigned 2 bytes)
 * @property [borderFillId] [Int], 테두리/배경[HWPBorderFill] ID
 * @property [textWidth] [Long], 텍스트의 최대 길이 (=개체의 폭) (HWPUNIT - unsigned 4 bytes)
 * @property [fieldName] [String], 필드 이름 (WCHAR array[n])
 */
class ListHeaderForCell {
    var paraCount: Int = 0
    var property: ListHeaderPropertyForCell = ListHeaderPropertyForCell()
    var colIndex: Int = 0
    var rowIndex: Int = 0
    var colSpan: Int = 0
    var rowSpan: Int = 0
    var width: Long = 0
    var height: Long = 0
    var leftMargin: Int = 0
    var rightMargin: Int = 0
    var topMargin: Int = 0
    var bottomMargin: Int = 0
    var borderFillId: Int = 0
    var textWidth: Long = 0
    var fieldName: String? = null

    fun copy() : ListHeaderForCell = ListHeaderForCell().also {
        it.paraCount = this.paraCount
        it.property.value = this.property.value
        it.colIndex = this.colIndex
        it.rowIndex = this.rowIndex
        it.colSpan = this.colSpan
        it.rowSpan = this.rowSpan
        it.width = this.width
        it.height = this.height
        it.leftMargin = this.leftMargin
        it.rightMargin = this.rightMargin
        it.topMargin = this.topMargin
        it.bottomMargin = this.bottomMargin
        it.borderFillId = this.borderFillId
        it.textWidth = this.textWidth
        it.fieldName = this.fieldName
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [ListHeaderForCell] 생성된 객체 반환
         */
        fun build(paraCount: Int = 0,
                  property: ListHeaderPropertyForCell = ListHeaderPropertyForCell.build(),
                  colIndex: Int = 0, rowIndex: Int = 0, colSpan: Int = 0, rowSpan: Int = 0,
                  width: Long = 0, height: Long = 0, leftMargin: Int = 0, rightMargin: Int = 0,
                  topMargin: Int = 0, bottomMargin: Int = 0, borderFillId: Int = 0,
                  textWidth: Long = 0, fieldName: String? = null)
                : ListHeaderForCell = ListHeaderForCell().apply {
            this.paraCount = paraCount
            this.property = property
            this.colIndex = colIndex
            this.rowIndex = rowIndex
            this.colSpan = colSpan
            this.rowSpan = rowSpan
            this.width = width
            this.height = height
            this.leftMargin = leftMargin
            this.rightMargin = rightMargin
            this.topMargin = topMargin
            this.bottomMargin = bottomMargin
            this.borderFillId = borderFillId
            this.textWidth = textWidth
            this.fieldName = fieldName
        }
    }
}

/**
 * 셀 리스트를 나타내는 객체
 *
 * @author accforaus
 *
 * @property [listHeader] [ListHeaderForCell], 셀 문단 리스트 헤더 (BYTE stream - n bytes)
 * @property [paragraphList] [HWPParagraphList], 문단 리스트
 */
class HWPCell {
    var listHeader: ListHeaderForCell = ListHeaderForCell()
    var paragraphList: HWPParagraphList = HWPParagraphList()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCell] 복사된 객체 반환
     */
    fun copy() : HWPCell = HWPCell().also {
        it.listHeader = this.listHeader.copy()
        it.paragraphList = this.paragraphList.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPCell] 생성된 객체 반환
         */
        fun build(listHeader: ListHeaderForCell = ListHeaderForCell.build(),
                  paragraphList: HWPParagraphList = HWPParagraphList.build())
                : HWPCell = HWPCell().apply {
            this.listHeader = listHeader
            this.paragraphList = paragraphList
        }
    }
}