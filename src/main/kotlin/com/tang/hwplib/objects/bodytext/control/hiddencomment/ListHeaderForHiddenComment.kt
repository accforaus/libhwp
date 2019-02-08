package com.tang.hwplib.objects.bodytext.control.hiddencomment

import com.tang.hwplib.objects.bodytext.control.gso.listheader.HWPListHeaderProperty

/**
 * 검추기 문단 리스트 헤더를 나타내는 객체
 * 2 bytes
 *
 * @author accforaus
 *
 * @property [paraCount] [Int], 문단 수 (INT16 - signed 2 bytes)
 * @property [property] [HWPListHeaderProperty], 문단 리스트 헤더 속성
 */
class ListHeaderForHiddenComment {
    var paraCount: Int = 0
    var property: HWPListHeaderProperty = HWPListHeaderProperty()
}