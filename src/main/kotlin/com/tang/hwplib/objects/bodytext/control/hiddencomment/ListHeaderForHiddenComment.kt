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

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [ListHeaderForHiddenComment] 복사된 객체 반환
     */
    fun copy() : ListHeaderForHiddenComment = ListHeaderForHiddenComment().also {
        it.paraCount = this.paraCount
        it.property.value = this.property.value
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [ListHeaderForHiddenComment] 생성된 객체 반환
         */
        fun build(paraCount: Int = 0,
                  property: HWPListHeaderProperty = HWPListHeaderProperty.build())
                : ListHeaderForHiddenComment = ListHeaderForHiddenComment().apply {
            this.paraCount = paraCount
            this.property = property
        }
    }
}