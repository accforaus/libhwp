package com.tang.hwplib.objects.bodytext.paragraph.text

import com.tang.hwplib.objects.bodytext.control.*
import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControl
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
     * 문단끝을 조절하는 함수
     */
    private fun processEndOfParagraph() {
        for (ch in charList) {
            if (ch.code == 13.toShort()) {
                charList.remove(ch)
                break
            }
        }
        addNewCharControlChar().run { this.code = 0x0d.toShort() }
    }

    fun addExtendCharByControl(control: HWPControl) {
        when (control) {
            is HWPControlSectionDefine, is HWPControlColumnDefine -> {
                addNewExtendControlChar().run {
                    this.addition = HWPControl.getAddition(control)
                    this.code = HWPControlExtendType.SectionColumnDefine.value
                }
            }
            is HWPControlField -> {
                addNewExtendControlChar().run {
                    this.addition = HWPControl.getFieldStartAddition(control)
                    this.code = HWPControlExtendType.FieldStart.value
                }
            }
            is HWPGsoControl -> {
                addNewExtendControlChar().run {
                    val addition: ByteArray = ByteArray(12)
                    addition[3] = 'g'.toByte()
                    addition[2] = 's'.toByte()
                    addition[1] = 'o'.toByte()
                    addition[0] = ' '.toByte()
                    this.addition = addition
                    this.code = HWPControlExtendType.GsoControl.value
                }
            }
            is HWPControlHiddenComment -> {
                addNewExtendControlChar().run {
                    this.addition = HWPControl.getAddition(control)
                    this.code = HWPControlExtendType.HiddenComment.value
                }
            }
            is HWPControlHeader, is HWPControlFooter -> {
                addNewExtendControlChar().run {
                    this.addition = HWPControl.getAddition(control)
                    this.code = HWPControlExtendType.HeaderFooter.value
                }
            }
            is HWPControlFootnote, is HWPControlEndNote -> {
                addNewExtendControlChar().run {
                    this.addition = HWPControl.getAddition(control)
                    this.code = HWPControlExtendType.FootEndNote.value
                }
            }
            is HWPControlAutoNumber -> {
                addNewExtendControlChar().run {
                    this.addition = HWPControl.getAddition(control)
                    this.code = HWPControlExtendType.AutoNumber.value
                }
            }
            is HWPControlPageHide, is HWPControlPageOddEvenAdjust, is HWPControlPageNumberPosition -> {
                addNewExtendControlChar().run {
                    this.addition = HWPControl.getAddition(control)
                    this.code = HWPControlExtendType.PageControl.value
                }
            }
            is HWPControlBookmark, is HWPControlIndexMark -> {
                addNewExtendControlChar().run {
                    this.addition = HWPControl.getAddition(control)
                    this.code = HWPControlExtendType.BookIndexMark.value
                }
            }
            is HWPControlAdditionalText, is HWPControlOverlappingLetter -> {
                addNewExtendControlChar().run {
                    this.addition = HWPControl.getAddition(control)
                    this.code = HWPControlExtendType.AdditionalOverlappingText.value
                }
            }
        }
        processEndOfParagraph()
    }

    fun addControlCharByType(type: HWPControlCharType) {
        if (type.value != 0.toShort()) {
            addNewCharControlChar().run {
                this.code = type.value
            }
        }
    }

    fun addInlineCharByType(type: HWPControlInlineType) {
        if (type.value != 0.toShort()) {
            addNewInlineControlChar().run {
                this.code = type.value
            }
        }
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