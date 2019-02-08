package com.tang.hwplib.objects.docinfo.charshape

import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 언어별 글자 위치를 나타내는 객체
 * -100% ~ 100%
 * INT8 array[7] - signed 7 bytes
 *
 * @author accforaus
 *
 * @property [array] [ByteArray], 언어별 글자 위치값을 가진 byte 배열
 */
class HWPCharOffsets {
    var array : ByteArray = ByteArray(7)

    /**
     * 한글 위치값을 가져오는 함수
     *
     * @return [Byte] 한글 위치값 반환
     */
    fun getHangul() : Byte = array[0]

    /**
     * 한글 위치값을 설정하는 함수
     *
     * @param [charOffset] [Byte], 한글 위치값을 가진 데이터
     */
    fun setHangul(charOffset: Byte) {
        array[0] = charOffset
    }

    /**
     * 영어 위치값을 가져오는 함수
     *
     * @return [Byte] 영어 위치값 반환
     */
    fun getLatin() : Byte = array[1]

    /**
     * 영어 위치값을 설정하는 함수
     *
     * @param [charOffset] [Byte], 영어 위치값을 가진 데이터
     */
    fun setLatin(charOffset: Byte) {
        array[1] = charOffset
    }

    /**
     * 한자 위치값을 가져오는 함수
     *
     * @return [Byte] 한자 위치값 반환
     */
    fun getHanja() : Byte = array[2]

    /**
     * 한자 위치값을 설정하는 함수
     *
     * @param [charOffset] [Byte] 한자 위치값을 가진 데이터
     */
    fun setHanja(charOffset: Byte) {
        array[2] = charOffset
    }

    /**
     * 일본어 위치값을 가져오는 함수
     *
     * @return [Byte] 일본어 위치값 반환
     */
    fun getJapanese() : Byte = array[3]

    /**
     * 일본어 위치값을 설정하는 함수
     *
     * @param [charOffset] [Byte], 일본어 위치값을 가진 데이터
     */
    fun setJapanese(charOffset: Byte) {
        array[3] = charOffset
    }

    /**
     * 기타 위치값을 가져오는 함수
     *
     * @return [Byte] 기타 위치값 반환
     */
    fun getOther() : Byte = array[4]

    /**
     * 기타 위치값을 설정하는 함수
     *
     * @param [charOffset] [Byte], 기타 위치값을 가진 데이터
     */
    fun setOther(charOffset: Byte) {
        array[4] = charOffset
    }

    /**
     * 기호 위치값을 가져오는 함수
     *
     * @return [Byte] 기호 위치값 반환
     */
    fun getSymbol() : Byte = array[5]

    /**
     * 기호 위치값을 설정하는 함수
     *
     * @param [charOffset] [Byte], 기호 위치값을 가진 데이터
     */
    fun setSymbol(charOffset: Byte) {
        array[5] = charOffset
    }

    /**
     * 사용자 위치값을 가져오는 함수
     *
     * @return [Byte] 사용자 위치값 반환
     */
    fun getUser() : Byte = array[6]

    /**
     * 사용자 위치값을 설정하는 함수
     *
     * @param [charOffset] [Byte], 사용자 위치값을 가진 데이터
     */
    fun setUser(charOffset: Byte) {
        array[6] = charOffset
    }

    /**
     * 위치값들을 같은 값으로 설정하는 함수
     *
     * @param [charOffset] [Byte], 위치값을 가진 데이터
     */
    fun setForAll(charOffset: Byte) {
        for (index in 0 until array.size) array[index] = charOffset
    }
}

/**
 * 글자 모양 속성을 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 글자 모양 속성 값을 가진 데이터
 */
class HWPCharShapeProperty {
    var value: Long = 0

    /**
     * 기울임 여부를 반환하는 함수
     * bit 0
     *
     * @return [Boolean] 기울임 여부 반환
     */
    fun isItalic() : Boolean = get(value, 0)

    /**
     * 기울임 여부를 설정하는 함수
     *
     * @param [italic] [Boolean], 기울임 여부의 참/거짓 값을 가진 데이터
     */
    fun setItalic(italic: Boolean) {
        value = set(value, 0, italic)
    }

    /**
     * 진하게 여부를 반환하는 함수
     * bit 1
     *
     * @return [Boolean] 진하게 여부 반환
     */
    fun isBold() : Boolean = get(value, 1)

    /**
     * 진하게 여부를 설정하는 함수
     * bit 1
     *
     * @param [bold] [Boolean], 진하게 여부의 참/거짓 값을 가진 데이터
     */
    fun setBold(bold: Boolean) {
        value = set(value, 1, bold)
    }

    /**
     * 밑줄 종류를 반환하는 함수
     * bit 2-3
     *
     * @return [HWPUnderLineSort] 밑줄 종류를 반환
     */
    fun getUnderLineSort() : HWPUnderLineSort = HWPUnderLineSort.valueOf(get(value, 2, 3).toByte())

    /**
     * 밑줄 종류를 설정하는 함수
     * bit 2-3
     *
     * @param [underLineSort] [HWPUnderLineSort], 밑줄 종류 값을 가진 데이터
     */
    fun setUnderLineSort(underLineSort: HWPUnderLineSort) {
        value = set(value, 2, 3, underLineSort.value.toInt())
    }

    /**
     * 밑줄 모양를 반환하는 함수
     * bit 4-7
     *
     * @return [HWPUnderLineSort] 밑줄 모양를 반환
     */
    fun getUnderLineShape() : HWPBorderType = HWPBorderType.valueOf(get(value, 4, 7).toByte())

    /**
     * 밑줄 모양룰 설정하는 함수
     * bit 4-7
     *
     * @param [underLineShape] [HWPBorderType], 밑줄 모양 값을 가진 데이터
     */
    fun setUnserLineShape(underLineShape: HWPBorderType) {
        value = set(value, 4, 7, underLineShape.value.toInt())
    }

    /**
     * 외곽선 종류를 반환하는 함수
     * bit 8-10
     *
     * @return [HWPUnderLineSort] 외곽선 종류를 반환
     */
    fun getOutterLineSort() : HWPOuterLineSort = HWPOuterLineSort.valueOf(get(value, 8, 10).toByte())

    /**
     * 외곽선 종류룰 설정하는 함수
     * bit 8-10
     *
     * @param [outterLineSort] [HWPBorderType], 외곽선 종류 값을 가진 데이터
     */
    fun setOutterLineSort(outterLineSort: HWPOuterLineSort) {
        value = set(value, 8, 10, outterLineSort.value.toInt())
    }

    /**
     * 그림자 종류를 반환하는 함수
     * bit 11-12
     *
     * @return [HWPUnderLineSort] 그림자 종류를 반환
     */
    fun getShadowSort() : HWPShadowSort = HWPShadowSort.valueOf(get(value, 11, 12).toByte())

    /**
     * 그림자 종류룰 설정하는 함수
     * bit 11-12
     *
     * @param [shadowSort] [HWPBorderType], 그림자 종류 값을 가진 데이터
     */
    fun setShadowSort(shadowSort: HWPShadowSort) {
        value = set(value, 11, 12, shadowSort.value.toInt())
    }

    /**
     * 양각 여부를 반환하는 함수
     * bit 13
     *
     * @return [Boolean] 양각 여부 반환
     */
    fun isEmboss() : Boolean = get(value, 13)

    /**
     * 양각 여부를 설정하는 함수
     * bit 13
     *
     * @param [emboss] [Boolean], 양각 여부의 참/거짓 값을 가진 데이터
     */
    fun setEmboss(emboss: Boolean) {
        value = set(value, 13, emboss)
    }

    /**
     * 음각 여부를 반환하는 함수
     * bit 14
     *
     * @return [Boolean] 음각 여부 반환
     */
    fun isEngrave() : Boolean = get(value, 14)

    /**
     * 음각 여부를 설정하는 함수
     * bit 14
     *
     * @param [engrave] [Boolean], 음각 여부의 참/거짓 값을 가진 데이터
     */
    fun setEngrave(engrave: Boolean) {
        value = set(value, 14, engrave)
    }

    /**
     * 위 첨자 여부를 반환하는 함수
     * bit 15
     *
     * @return [Boolean] 위 첨자 여부 반환
     */
    fun isSuperScript() : Boolean = get(value, 15)

    /**
     * 위 첨자 여부를 설정하는 함수
     * bit 15
     *
     * @param [superScript] [Boolean], 위 첨자 여부의 참/거짓 값을 가진 데이터
     */
    fun setSuperScript(superScript: Boolean) {
        value = set(value, 15, superScript)
    }

    /**
     * 아래 첨자 여부를 반환하는 함수
     * bit 16
     *
     * @return [Boolean] 아래 첨자 여부 반환
     */
    fun isSubScript() : Boolean = get(value, 16)

    /**
     * 아래 첨자 여부를 설정하는 함수
     * bit 16
     * @param [subScript] [Boolean], 아래 첨자 여부의 참/거짓 값을 가진 데이터
     */
    fun setSubScript(subScript: Boolean) {
        value = set(value, 16, subScript)
    }

    /**
     * 취소선 여부를 반환하는 함수
     * bit 18-20
     *
     * @return [Boolean] 취소선 여부 반환
     */
    fun isStrikeLine() : Boolean = get(value, 18) or get(value, 19) or get(value, 20)

    /**
     * 취소선 여부를 설정하는 함수
     * bit 18-20
     *
     * @param [strikeLine] [Boolean], 취소선 여부의 참/거짓 값을 가진 데이터
     */
    fun setStrikeLine(strikeLine: Boolean) {
        value = set(value, 18, strikeLine)
        value = set(value, 19, strikeLine)
        value = set(value, 20, strikeLine)
    }

    /**
     * 강조점 종류를 반환하는 함수
     * bit 21-24
     *
     * @return [HWPUnderLineSort] 강조점 종류를 반환
     */
    fun getEmphasisSort() : HWPEmphasisSort = HWPEmphasisSort.valueOf(get(value, 21, 24).toByte())

    /**
     * 강조점 종류룰 설정하는 함수
     * bit 21-24
     *
     * @param [emphasisSort] [HWPBorderType], 강조점 종류 값을 가진 데이터
     */
    fun setEmpasisSort(emphasisSort: HWPEmphasisSort) {
        value = set(value, 21, 24, emphasisSort.value.toInt())
    }

    /**
     * 글꼴에 어울리는 빈칸 사용 여부 반환 함수
     *  bit 25
     *
     * @return [Boolean] 글꼴에 어울리는 빈칸 사용 여부 반환
     */
    fun isUsingSpaceAppropriateForFont() : Boolean = get(value, 25)

    /**
     * 글꼴에 어울리는 빈칸 사용 여부 설정 함수
     * bit 25
     *
     * @param [usingSpaceAppropriateForFont] [Boolean], 글꼴에 어울리는 빈칸 사용 여부의 참/값을 가진 데이터
     */
    fun setUsingSpaceAppropriateForFont(usingSpaceAppropriateForFont: Boolean) {
        value = set(value, 25, usingSpaceAppropriateForFont)
    }

    /**
     * 취소선 모양를 반환하는 함수
     * bit 26-29
     *
     * @return [HWPUnderLineSort] 취소선 모양를 반환
     */
    fun getStrikeLineShape() : HWPBorderType = HWPBorderType.valueOf(get(value, 26, 29).toByte())

    /**
     * 취소선 모양룰 설정하는 함수
     * bit 26-29
     *
     * @param [strikeLineShape] [HWPBorderType], 취소선 모양 값을 가진 데이터
     */
    fun setStrikeLineShape(strikeLineShape: HWPBorderType) {
        value = set(value, 26, 29, strikeLineShape.value.toInt())
    }

    /**
     * Kerning 여부를 반환하는 함수
     * bit 30
     *
     * @return [Boolean] Kerning 여부 반환
     */
    fun isKerning() : Boolean = get(value, 30)

    /**
     * Kerning 여부를 설정하는 함수
     * bit 30
     *
     * @param [kerning] [Boolean], Kerning 여부의 참/거짓 값을 가진 데이터
     */
    fun setKerning(kerning: Boolean) {
        value = set(value, 30, kerning)
    }
}

/**
 * 언어별 자간을 나타내는 객체
 * -50% ~ 50%
 * INT8 array[7] - signed 7 bytes
 *
 * @author accforaus
 *
 * @property [array] [ByteArray], 언어별 자간값을 가진 byte 배열
 */
class HWPCharSpaces {
    var array: ByteArray = ByteArray(7)

    /**
     * 한글 자간값을 가져오는 함수
     *
     * @return [Byte] 한글 자간값 반환
     */
    fun getHangul() : Byte = array[0]

    /**
     * 한글 자간값을 설정하는 함수
     *
     * @param [charOffset] [Byte], 한글 자간값을 가진 데이터
     */
    fun setHangul(charOffset: Byte) {
        array[0] = charOffset
    }

    /**
     * 영어 자간값을 가져오는 함수
     *
     * @return [Byte] 영어 자간값 반환
     */
    fun getLatin() : Byte = array[1]

    /**
     * 영어 자간값을 설정하는 함수
     *
     * @param [charOffset] [Byte], 영어 자간값을 가진 데이터
     */
    fun setLatin(charOffset: Byte) {
        array[1] = charOffset
    }

    /**
     * 한자 자간값을 가져오는 함수
     *
     * @return [Byte] 한자 자간값 반환
     */
    fun getHanja() : Byte = array[2]

    /**
     * 한자 자간값을 설정하는 함수
     *
     * @param [charOffset] [Byte], 한자 자간값을 가진 데이터
     */
    fun setHanja(charOffset: Byte) {
        array[2] = charOffset
    }

    /**
     * 일본어 자간값을 가져오는 함수
     *
     * @return [Byte] 일본어 자간값 반환
     */
    fun getJapanese() : Byte = array[3]

    /**
     * 일본어 자간값을 설정하는 함수
     *
     * @param [charOffset] [Byte], 일본어 자간값을 가진 데이터
     */
    fun setJapanese(charOffset: Byte) {
        array[3] = charOffset
    }

    /**
     * 기타 자간값을 가져오는 함수
     *
     * @return [Byte] 기타 자간값 반환
     */
    fun getOther() : Byte = array[4]

    /**
     * 기타 자간값을 설정하는 함수
     *
     * @param [charOffset] [Byte], 기타 자간값을 가진 데이터
     */
    fun setOther(charOffset: Byte) {
        array[4] = charOffset
    }

    /**
     * 기호 자간값을 가져오는 함수
     *
     * @return [Byte] 기호 자간값 반환
     */
    fun getSymbol() : Byte = array[5]

    /**
     * 기호 자간값을 설정하는 함수
     *
     * @param [charOffset] [Byte], 기호 자간값을 가진 데이터
     */
    fun setSymbol(charOffset: Byte) {
        array[5] = charOffset
    }

    /**
     * 사용자 자간값을 가져오는 함수
     *
     * @return [Byte] 사용자 자간값 반환
     */
    fun getUser() : Byte = array[6]

    /**
     * 사용자 자간값을 설정하는 함수
     *
     * @param [charOffset] [Byte], 사용자 자간값을 가진 데이터
     */
    fun setUser(charOffset: Byte) {
        array[6] = charOffset
    }

    /**
     * 자간값들을 같은 값으로 설정하는 함수
     *
     * @param [charOffset] [Byte], 자간값을 가진 데이터
     */
    fun setForAll(charOffset: Byte) {
        for (index in 0 until array.size) array[index] = charOffset
    }
}