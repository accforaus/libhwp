package com.tang.hwplib.objects.docinfo.numbering

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
}