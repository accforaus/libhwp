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

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [ListHeaderForHeaderFooter] 복사된 객체 반환
     */
    fun copy() : ListHeaderForHeaderFooter = ListHeaderForHeaderFooter().also {
        it.paraCount = this.paraCount
        it.property.value = this.property.value
        it.textWidth = this.textWidth
        it.textHeight = this.textHeight
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [ListHeaderForHeaderFooter] 생성된 객체 반환
         */
        fun build(paraCount: Int = 0,
                  property: HWPListHeaderProperty = HWPListHeaderProperty.build(),
                  textWidth: Long = 0, textHeight: Long = 0)
                : ListHeaderForHeaderFooter = ListHeaderForHeaderFooter().apply {
            this.paraCount = paraCount
            this.property = property
            this.textWidth = textWidth
            this.textHeight = textHeight
        }
    }
}