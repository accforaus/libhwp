package com.tang.hwplib.objects.docinfo.facename

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 글꼴 속성을 나타내는 객체
 * BYTE - unsigned 1 byte
 *
 * @author accforaus
 *
 * @property [value] [Short], 글꼴 속성 정보를 가지고 있는 데이터
 */
class HWPFaceNameProperty {
    var value: Short = 0

    /**
     * 대체 글꼴 존재 여부를 반환하는 함수 (0x80)
     *
     * @return [Boolean], 대체 글꼴 존재 여부를 반환
     */
    fun hasSubstituteFont() : Boolean = get(value, 7)

    /**
     * 대채 글꼴 존재 여부를 설정하는 함수 (0x80)
     *
     * @param [substituteFont] [Boolean], 대체 글꼴에 대한 참/거짓 값
     */
    fun setSubstituteFont(substituteFont: Boolean) {
        value = set(value, 7, substituteFont)
    }

    /**
     * 글꼴 유형 정보 존재 여부를 반환하는 함수 (0x40)
     *
     * @return [Boolean], 글꼴 유형 정보 존재 여부 반한
     */
    fun hasFontInfo() : Boolean = get(value, 6)

    /**
     * 글꼴 유형 존재 여부를 설정하는 함수 (0x40)
     *
     * @param [fontInfo] [Boolean], 글꼴 유형 존재에 대한 참/거짓 값
    */
    fun setFontInfo(fontInfo: Boolean) {
        value = set(value, 6, fontInfo)
    }

    /**
     * 기본 글꼴 존재 여부를 반환하는 함수 (0x20)
     *
     * @return [Boolean], 기본 글꼴 존재 여부 반환
     */
    fun hasBaseFont() : Boolean = get(value, 5)

    /**
     * 기본 글꼴 존재 여부를 설정하는 함수 (0x20)
     *
     * @param [baseFont] [Boolean], 기본 글꼴 존재에 대한 참/거짓 값
     */
    fun setBaseFont(baseFont: Boolean) {
        value = set(value, 5, baseFont)
    }
}