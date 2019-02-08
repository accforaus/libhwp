package com.tang.hwplib.objects.bodytext.control.gso.textbox

import com.tang.hwplib.objects.bodytext.control.gso.listheader.HWPListHeaderProperty
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphList
import com.tang.hwplib.objects.etc.LIST_HEADER

/**
 * 글상자 문단 리스트 헤더를 나타내는 객체
 * Tag ID: HWPTAG_LIST_HEADER [LIST_HEADER]
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [paraCount] [Int] 문단 수 문단 수 (INT16 - signed 2 bytes)
 * @property [property] [ListHeaderForHWPTextBox], 그리기 개체 글상자용 텍스트 속성 (BYTE stream - unsigned 12 bytes)
 * @property [leftMargin] [Int], 셀 왼쪽 여백 (HWPUNIT16 - unsigned 2 bytes)
 * @property [rightMargin] [Int], 셀 오른쪽 여백 (HWPUNIT16 - unsigned 2 bytes)
 * @property [topMargin] [Int], 셀 위쪽 여백 (HWPUNIT16 - unsigned 2 bytes)
 * @property [bottomMargin] [Int], 셀 아래쪽 여백 (HWPUNIT16 - unsigned 2 bytes)
 * @property [textWidth] [Long], 텍스트의 최대 길이 (=개체의 폭) (HWPUNIT - unsigned 4 bytes)
 * @property [editableAtFormMode] [Boolean], 양식 모드에서 편집 가능 (BYTE - unsigned 1 bytes)
 * @property [fieldName] [String], 필드 이름 (WCHAR array[n])
 */
class ListHeaderForHWPTextBox {
    var paraCount: Int = 0
    var property: HWPListHeaderProperty = HWPListHeaderProperty()
    var leftMargin: Int = 0
    var rightMargin: Int = 0
    var topMargin: Int = 0
    var bottomMargin: Int = 0
    var textWidth: Long = 0
    var editableAtFormMode: Boolean = false
    var fieldName: String? = null
}

/**
 * 그리기 객체 글상자용 텍스트 정보를 나타내는 객체
 *
 * @author accforaus
 *
 * @property [listHeader] [ListHeaderForHWPTextBox], 그리기 개체 글상자용 텍스트 속성 (BYTE stream - unsigned 12 bytes)
 * @property [paragraphList] [HWPParagraphList], 문단 리스트 (BYTE stream)
 */
class HWPTextBox {
    var listHeader: ListHeaderForHWPTextBox = ListHeaderForHWPTextBox()
    var paragraphList: HWPParagraphList = HWPParagraphList()
}