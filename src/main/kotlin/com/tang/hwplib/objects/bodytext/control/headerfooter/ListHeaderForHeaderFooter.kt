package com.tang.hwplib.objects.bodytext.control.headerfooter

import com.tang.hwplib.objects.bodytext.control.gso.listheader.HWPListHeaderProperty

/**
 * 머리말/꼬리말 문단 리스트 헤더를 나타내는 객체
 * 14 bytes
 *
 * @author accforaus
 *
 * @property [paraCount] [Int], 문단 수 (INT16 - signed 2 bytes)
 * @property [property] [HWPListHeaderProperty], 문단 리스트 헤더 속성
 * @property [textWidth] [Long], 텍스트의 최대 길이 (=개체의 폭) (HWPUNIT - unsigned 4 bytes)
 * @property [textHeight] [Long], 텍스트의 최대 높이 (=개체의 높이) (HWPUNIT - unsigned 4 bytes)
 */
class ListHeaderForHeaderFooter {
    var paraCount: Int = 0
    var property: HWPListHeaderProperty = HWPListHeaderProperty()
    var textWidth: Long = 0
    var textHeight: Long = 0
}