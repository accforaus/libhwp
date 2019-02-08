package com.tang.hwplib.objects.bodytext.control.footnoteendnote

import com.tang.hwplib.objects.bodytext.control.gso.listheader.HWPListHeaderProperty

/**
 * 각주/미주 문단 리스트 헤더를 나타내는 객체
 *
 * @author accforaus
 *
 * @property [paraCount] [Int], 문단 수 (INT16 - signed 2 bytes)
 * @property [property] [HWPListHeaderProperty], 속성 속성 (BYTE stream)
 */
class ListHeaderForFootnoteEndnote {
    var paraCount: Int = 0
    var property: HWPListHeaderProperty = HWPListHeaderProperty()
}