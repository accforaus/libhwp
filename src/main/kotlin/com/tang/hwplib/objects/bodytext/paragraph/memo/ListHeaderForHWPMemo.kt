package com.tang.hwplib.objects.bodytext.paragraph.memo

import com.tang.hwplib.objects.bodytext.control.gso.listheader.HWPListHeaderProperty

/**
 * 메모를 위한 문단 리스트 헤더를 나타내는 객체
 *
 * @author accforaus
 *
 * @property [paraCount] [Int], 문단 개수
 * @property [property] [HWPListHeaderProperty], 리스트 헤더의 속성 객체
 * @property [textWidth] [Long], 텍스트 영역의 폭
 * @property [textHeight] [Long], 텍스트 영역의 높이
*/
class ListHeaderForHWPMemo {
    var paraCount: Int = 0
    var property: HWPListHeaderProperty = HWPListHeaderProperty()
    var textWidth: Long = 0
    var textHeight: Long = 0
}