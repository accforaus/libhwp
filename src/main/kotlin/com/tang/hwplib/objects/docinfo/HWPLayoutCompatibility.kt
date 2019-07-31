package com.tang.hwplib.objects.docinfo
import com.tang.hwplib.objects.etc.LAYOUT_COMPATIBILITY
/**
 * 레이아웃 호환성을 나타내는 객체
 * Tag ID: HWPTAG_LAYOUT_COMPATIBILITY [LAYOUT_COMPATIBILITY]
 * 20 bytes
 *
 * @author accforaus
 *
 * @property [letterLevelFormat] [Long], 글자 단위 서식 (UINT32 - unsigned 4 bytes)
 * @property [paragraphLevelFormat] [Long], 문단 단위 서식 (UINT32 - unsigned 4 bytes)
 * @property [sectionLevelFormat] [Long], 구역 단위 서식 (UINT32 - unsigned 4 bytes)
 * @property [objectLevelFormat] [Long], 개체 단위 서식 (UINT32 - unsigned 4 bytes)
 * @property [fieldLevelFormat] [Long], 필드 단위 서식 (UINT32 - unsigned 4 bytes)
 */
class HWPLayoutCompatibility : HWPDocInfoElement() {
    var letterLevelFormat: Long = 0
    var paragraphLevelFormat: Long = 0
    var sectionLevelFormat: Long = 0
    var objectLevelFormat: Long = 0
    var fieldLevelFormat: Long = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPLayoutCompatibility] 복사된 객체 반환
     */
    override fun copy() : HWPLayoutCompatibility = HWPLayoutCompatibility().also {
        it.letterLevelFormat = this.letterLevelFormat
        it.paragraphLevelFormat = this.paragraphLevelFormat
        it.sectionLevelFormat = this.sectionLevelFormat
        it.objectLevelFormat = this.objectLevelFormat
        it.fieldLevelFormat = this.fieldLevelFormat
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPLayoutCompatibility] 생성된 객체 반환
         */
        fun build(letterLevelFormat: Long = 0,
                  paragraphLevelFormat: Long = 0,
                  sectionLevelFormat: Long = 0,
                  objectLevelFormat: Long = 0,
                  fieldLevelFormat: Long = 0)
                : HWPLayoutCompatibility = HWPLayoutCompatibility().apply {
            this.letterLevelFormat = letterLevelFormat
            this.paragraphLevelFormat = paragraphLevelFormat
            this.sectionLevelFormat = sectionLevelFormat
            this.objectLevelFormat = objectLevelFormat
            this.fieldLevelFormat = fieldLevelFormat
        }
    }

    override fun equals(other: Any?): Boolean = (other as HWPLayoutCompatibility).let {
        return letterLevelFormat == it.letterLevelFormat
                && paragraphLevelFormat == it.paragraphLevelFormat
                && sectionLevelFormat == it.sectionLevelFormat
                && objectLevelFormat == it.objectLevelFormat
                && fieldLevelFormat == it.fieldLevelFormat
    }
}