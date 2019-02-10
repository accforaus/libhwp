package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.objects.docinfo.documentproperties.HWPCaretPosition
import com.tang.hwplib.objects.docinfo.documentproperties.HWPStartNumber
import com.tang.hwplib.objects.etc.DOCUMENT_PROPERTIES
/**
 * 문서 속성을 나타내는 객체 (26 bytes)
 * Tag ID: HWPTAG_DOCUMENT_PROPERTIES [DOCUMENT_PROPERTIES]
 *
 * @author accforaus
 *
 * @property [sectionCount] [Int], 구역 개수 UINT16 (unsigned 2 bytes)
 * @property [startNumber] [HWPStartNumber], 문서 내 각종 시작번호에 대한 정보 (12 bytes)
 * @property [caretPosition] [HWPCaretPosition], 문서 내 캐럿의 위치 정보 (12 bytes)
 */
class HWPDocumentProperties {
    var sectionCount: Int = 0
    var startNumber: HWPStartNumber = HWPStartNumber()
    var caretPosition: HWPCaretPosition = HWPCaretPosition()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPDocumentProperties] 복사된 객체 반환
     */
    fun copy(): HWPDocumentProperties = HWPDocumentProperties().also {
        it.sectionCount = this.sectionCount
        it.startNumber = this.startNumber.copy()
        it.caretPosition = this.caretPosition.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPDocumentProperties] 생성된 객체 반환
         */
        fun build(sectionCount: Int = 0,
                  startNumber: HWPStartNumber = HWPStartNumber.build(),
                  caretPosition: HWPCaretPosition = HWPCaretPosition.build())
                : HWPDocumentProperties = HWPDocumentProperties().apply {
            this.sectionCount = sectionCount
            this.startNumber = startNumber
            this.caretPosition = caretPosition
        }
    }
}