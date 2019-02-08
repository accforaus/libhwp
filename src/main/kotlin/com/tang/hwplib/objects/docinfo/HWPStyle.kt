package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.objects.docinfo.style.HWPStyleProperty
import com.tang.hwplib.objects.etc.STYLE
/**
 * 스타일(문단 스타일)을 나타내는 객체
 * Tag ID: HWPTAG_STYLE [STYLE]
 * 가변 길이
 * @author accforaus
 *
 * @property [hangulName] [String], 로컬 스타일 이름. 한글 윈도우에서는 한글 스타일 이름 (WCHAR array[2] - unsigned 4 bytes)
 * @property [englishName] [String], 영문 스타일 이름 (WCHAR array[2] - unsigned 4 bytes)
 * @property [property] [HWPStyleProperty], 속성 (BYTE - unsigned 1 byte)
 * @property [nextStyleId] [Short], 다음 스타일[HWPStyle] 아이디 참조값 (BYTE - unsigned 1 byte)
 * @property [languageId] [Short], 언어 아이디 (INT16 - signed 2 bytes)
 * @property [paraShapeId] [Int], 문단 모양[HWPParaShape] 참조값(문단 모양의 아이디 속성) 스타일의 종류가 문단인 경우 반드시 지정해야 한다. (UINT16 - unsigned 2 bytes)
 * @property [charShapeId] [Int], 글자 모양[HWPCharShape] 참조값(글자 모양의 아이디 속성) 스타일의 종류가 글자인 경우 반드시 지정해야 한다. (UINT16 - unsigned 2 bytes)
 */
class HWPStyle {
    var hangulName: String? = null
    var englishName: String? = null
    var property: HWPStyleProperty = HWPStyleProperty()
    var nextStyleId: Short = 0
    var languageId: Short = 0
    var paraShapeId: Int = 0
    var charShapeId: Int = 0
}