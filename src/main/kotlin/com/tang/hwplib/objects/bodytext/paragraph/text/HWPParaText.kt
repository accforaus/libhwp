package com.tang.hwplib.objects.bodytext.paragraph.text

import com.tang.hwplib.objects.etc.PARA_TEXT

/**
 * 문단의 텍스트를 나타내는 객체
 * Tag ID: HWPTAG_PARA_TEXT [PARA_TEXT]
 * 가변 길이
 *
 * @property [charList] [HWPChar], 문자만큼의 텍스트를 가진 리스트
 *
 * 문단은 최소 하나의 문자 Shape buffer가 존재하며, 첫 번째 pos가 반드시 0이어야 한다
 * 텍스트 문자 Shape 레코드를 글자 모양 정보 수 (Character Spaces)만큼 읽는다
 */
class HWPParaText {
    var charList: ArrayList<HWPChar> = ArrayList()

    /**
     * 일반 문자 객체를 생성하고 생성된 객체를 반환하는 함수
     *
     * @return [HWPCharNormal] 생성된 일반 문자 객체
     */
    fun addNewNormalChar() : HWPCharNormal = HWPCharNormal().apply { charList.add(this) }

    /**
     * 컨트롤 문자 객체를 생성하고 생성된 객체를 반환하는 함수
     *
     * @return [HWPCharControlChar] 생성된 컨트롤 문자 객체
     */
    fun addNewCharControlChar() : HWPCharControlChar = HWPCharControlChar().apply { charList.add(this) }

    /**
     * 인라인 문자 객체를 생성하고 생성된 객체를 반환하는 함수
     *
     * @return [HWPCharControlInline] 생성된 인라인 문자 객체
     */
    fun addNewInlineControlChar() : HWPCharControlInline = HWPCharControlInline().apply { charList.add(this) }

    /**
     * 확장 문자 객체를 생성하고 생성된 객체를 반환하는 함수
     *
     * @return [HWPCharControlExtend] 생성된 확장 문자 객체
     */
    fun addNewExtendControlChar() : HWPCharControlExtend = HWPCharControlExtend().apply { charList.add(this) }

    /**
     * 확장 문자 객체 인덱스를 이용하여 확장 문자 객체 인덱스를 반환하는 함수
     *
     * @param [extendCharIndex] [Int], 확장 문자 객체 인덱스
     * @return [Int] 확장 문자 객체 인덱스 (존재하지 않으면 -1을 반환)
     */
    fun getCharIndexFromExtendCharIndex(extendCharIndex: Int) : Int = Any().run {
        var extendCharIndex2: Int = 0
        for ((index, value) in charList.withIndex()) {
            if (value.getType() == HWPCharType.ControlExtend)
                if (extendCharIndex == extendCharIndex2)
                    return index
            extendCharIndex2++
        }
        return -1
    }

    /**
     * 인라인 문자 객체의 인덱스와 code를 이용하여 인라인 문자 객체의 인덱스를 반환하는 함수
     *
     * @param [startIndex] [Int], 인라인 문자 객체 인덱스
     * @param [charCode] [Short], 비교할 code
     * @return [Int] 인라인 문자 객체 인덱스 (존재하지 않으면 -1을 반환)
     */
    fun getInlineCharIndex(startIndex: Int, charCode: Short) : Int = Any().run {
        for ((index, value) in charList.withIndex()) {
            if (index >= startIndex) {
                val ch: HWPChar = value
                if (ch.getType() == HWPCharType.ControlInline && ch.code == charCode)
                    return index
            }
        }
        return -1
    }

    /**
     * 일반 문자열을 반환하는 함수
     *
     * @param [startIndex] [Int], 시작 인덱스
     * @param [endIndex] [Int], 끝 인덱스
     * @return [String] [startIndex]부터 [endIndex] 까지 이루어진 문자열 반환
     */
    fun getNormalString(startIndex: Int, endIndex: Int) : String? = StringBuilder().run {
        if (startIndex == endIndex) return ""
        if (startIndex > endIndex) return null
        for (index in startIndex..endIndex) {
            val ch: HWPChar = charList[index]
            if (ch is HWPCharNormal) this.append(ch.getCh())
        }
        return this.toString()
    }

    /**
     * 일반 문자열을 반환하는 함수
     *
     * @param [startIndex] [Int], 시작 인덱스
     * @return [String] [startIndex]부터 마지막 인덱스 까지 이루어진 문자열 반환
     */
    fun getNormalString(startIndex: Int): String? = getNormalString(startIndex, charList.size - 1)

    /**
     * 문자열을 추가하는 함수
     *
     * @param [str] [String], 추가할 문자열
     */
    fun addString(str: String) {
        val len: Int = str.length
        for (index in 0..len)
            addNewNormalChar().run { this.code = str.codePointAt(index).toShort() }
        processEndOfParagraph()
    }

    /**
     * 섹션 정의 확장 문자 객체를 추가하는 함수
     */
    fun addExtendCharForSectionDefine() {
        addNewExtendControlChar().run {
            val addition: ByteArray = ByteArray(12)
            addition[3] = 's'.toByte()
            addition[2] = 'e'.toByte()
            addition[1] = 'c'.toByte()
            addition[0] = 'd'.toByte()
            this.code = 0x0002.toShort()
            this.addition = addition
        }
        processEndOfParagraph()
    }

    /**
     * 문단끝을 조절하는 함수
     */
    private fun processEndOfParagraph() {
        for (ch in charList) {
            if (ch.code == 13.toShort()) {
                charList.remove(ch)
                break
            }
        }
        addNewNormalChar().run { this.code = 0x0d.toShort() }
    }

    /**
     * 단 정의 확장 문자 객체를 추가하는 함수
     */
    fun addExtendCharForColumnDefine() {
        addNewExtendControlChar().run {
            val addition: ByteArray = ByteArray(12)
            addition[3] = 'c'.toByte()
            addition[2] = 'o'.toByte()
            addition[1] = 'l'.toByte()
            addition[0] = 'd'.toByte()
            this.code = 0x0002.toShort()
            this.addition = addition
        }
        processEndOfParagraph()
    }

    /**
     * 테이블 확장 문자 객체를 추가하는 함수
     */
    fun addExtendCharForTable() {
        addNewExtendControlChar().run {
            val addition: ByteArray = ByteArray(12)
            addition[3] = 't'.toByte()
            addition[2] = 'b'.toByte()
            addition[1] = 'l'.toByte()
            addition[0] = ' '.toByte()
            this.code = 0x000b.toShort()
            this.addition = addition
        }
        processEndOfParagraph()
    }

    /**
     * GSO 확장 문자 객체를 추가하는 함수
     */
    fun addExtendCharForGSO() {
        addNewExtendControlChar().run {
            val addition: ByteArray = ByteArray(12)
            addition[3] = 'g'.toByte()
            addition[2] = 's'.toByte()
            addition[1] = 'o'.toByte()
            addition[0] = ' '.toByte()
            this.code = 0x000b.toShort()
            this.addition = addition
        }
        processEndOfParagraph()
    }

    /**
     * 하이퍼링크 시작 필드 확장 문자 객체를 추가하는 함수
     */
    fun addExtendCharForHyperlinkStart() {
        addNewExtendControlChar().run {
            val addition: ByteArray = ByteArray(12)
            addition[3] = '%'.toByte()
            addition[2] = 'h'.toByte()
            addition[1] = 'l'.toByte()
            addition[0] = 'k'.toByte()
            this.code = 0x0003.toShort()
            this.addition = addition
        }
        processEndOfParagraph()
    }

    /**
     * 하이퍼링크 끝 필드 확장 문자를 추가하는 함수
     */
    fun addExtendCharForHyperlinkEnd() {
        addNewExtendControlChar().run {
            val addition: ByteArray = ByteArray(12)
            addition[2] = 'h'.toByte()
            addition[1] = 'l'.toByte()
            addition[0] = 'k'.toByte()
            this.code = 0x0004.toShort()
            this.addition = addition
        }
        processEndOfParagraph()
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPParaText] 복사된 객체 반환
     */
    fun copy() : HWPParaText = HWPParaText().also {
        for (char in this.charList) it.charList.add(char.copy())
    }
}