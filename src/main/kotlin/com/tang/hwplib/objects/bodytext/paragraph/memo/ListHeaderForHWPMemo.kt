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

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [ListHeaderForHWPMemo] 복사된 객체 반환
     */
    fun copy() : ListHeaderForHWPMemo = ListHeaderForHWPMemo().also {
        it.paraCount = this.paraCount
        it.property.value = this.property.value
        it.textWidth = this.textWidth
        it.textHeight = this.textHeight
    }
}