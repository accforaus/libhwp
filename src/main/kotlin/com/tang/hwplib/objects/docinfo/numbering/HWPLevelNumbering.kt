package com.tang.hwplib.objects.docinfo.numbering

import com.tang.hwplib.util.extension.nullEquals

/**
 * 번호 형식을 나타내는 객체
 * 2 bytes
 *
 * @author accforaus
 * @property [paragraphHeadInfo] [HWPParagraphHeadInfo], 문단 머리 정보 (BYTE stream - unsigned 8 bytes)
 * @property [numberFormat] [String], 번호 형식
 */
class HWPLevelNumbering {
    var paragraphHeadInfo: HWPParagraphHeadInfo = HWPParagraphHeadInfo()
    var numberFormat: String? = null

    override fun equals(other: Any?): Boolean = (other as HWPLevelNumbering).let {
        return paragraphHeadInfo == it.paragraphHeadInfo
                && nullEquals(numberFormat, it.numberFormat)
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPLevelNumbering] 복사된 객체 반환
     */
    fun copy() : HWPLevelNumbering = HWPLevelNumbering().also {
        it.paragraphHeadInfo = this.paragraphHeadInfo.copy()
        it.numberFormat = this.numberFormat
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPLevelNumbering] 생성된 객체 반환
         */
        fun build(paragraphHeadInfo: HWPParagraphHeadInfo = HWPParagraphHeadInfo.build(),
                  numberFormat: String? = null)
                : HWPLevelNumbering = HWPLevelNumbering().apply {
            this.paragraphHeadInfo = paragraphHeadInfo
            this.numberFormat = numberFormat
        }
    }
}

/**
 * 확장 번호 형식을 나타내는 객체
 * 4 bytes
 *
 * @author accforaus
 *
 * @property [numberFormat] [String], 확장 번호 형식
 */
class HWPExtendNumbering {
    var numberFormat: String? = null

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPExtendNumbering] 복사된 객체 반환
     */
    fun copy() : HWPExtendNumbering = HWPExtendNumbering().also {
        it.numberFormat = this.numberFormat
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPExtendNumbering] 생성된 객체 반환
         */
        fun build(numberFormat: String? = null)
                : HWPExtendNumbering = HWPExtendNumbering().apply {
            this.numberFormat = numberFormat
        }
    }

    override fun equals(other: Any?): Boolean = nullEquals(numberFormat, (other as HWPExtendNumbering).numberFormat)
}