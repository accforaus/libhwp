package com.tang.hwplib.objects.bodytext.control.sectiondefine

import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderThickness
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.objects.etc.Color4Byte
import com.tang.hwplib.objects.etc.FOOTNOTE_SHAPE
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 한 페이지 내에서 각주를 다단에 위치 시킬 방법
 *
 * @author accforaus
 *
 * @property [value] [Byte], 한 페이지 내에서 각주를 다단에 위치 시킬 방법값
 */
enum class HWPFootnoteDisplayMethod(v: Byte) {
    /**
     * 각 단마다 따로 배열
     */
    EachColumn(0.toByte()),
    /**
     * 통단으로 배열
     */
    AllColumn(1.toByte()),
    /**
     * 가장 오른쪽 단에 배열
     */
    RightColumn(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPFootnoteDisplayMethod] enum 값
         */
        fun valueOf(v: Byte) : HWPFootnoteDisplayMethod {
            for (fdm in values())
                if (fdm.value == v)
                    return fdm
            return EachColumn
        }
    }
}

/**
 * 한 페이지 내에서 미주를 다단에 위치 시킬 방법
 *
 * @author accforaus
 *
 * @property [value] [Byte], 한 페이지 내에서 미주를 다단에 위치 시킬 방법값
 */
enum class HWPEndnoteDisplayMethod(v: Byte) {
    /**
     * 문사의 마지막
     */
    DocumentEnd(0.toByte()),
    /**
     * 구역의 마지막
     */
    SectionEnd(1.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPEndnoteDisplayMethod] enum 값
         */
        fun valueOf(v: Byte) : HWPEndnoteDisplayMethod {
            for (edm in values())
                if (edm.value == v)
                    return edm
            return DocumentEnd
        }
    }
}

/**
 * 각주/미주 모양의 속성을 나타내는 객체
 *  UINT32 - unsigned 4 bytes
 *
 *  @author accforaus
 *
 *  @property [value] [Long], 각주/미주 모양의 속성값
 */
class HWPFootnoteShapeProperty {
    var value: Long = 0

    /**
     * 번호모양을 반환하는 함수
     * bit 0-7
     *
     * @return [HWPNumberShape] 번호모양 반환
     */
    fun getNumberShape() : HWPNumberShape = HWPNumberShape.valueOf(get(value, 0, 7).toShort())

    /**
     * 번호모양을 설정하는 함수
     * bit 0-7
     *
     * @param [numberShape] [HWPNumberShape] 번호모양값
     */
    fun setNumberShape(numberShape: HWPNumberShape) {
        value = set(value, 0, 7, numberShape.value.toInt())
    }

    /**
     * 한 페이지 내에서 각주를 다단에 위치시킬 방법을 반환하는 함수
     * bit 8-9
     *
     * @return [HWPFootnoteDisplayMethod] 한 페이지 내에서 각주를 다단에 위치시킬 방법 반환
     */
    fun getFootnoteDisplayMethod() : HWPFootnoteDisplayMethod = HWPFootnoteDisplayMethod.valueOf(get(value, 8, 9).toByte())

    /**
     * 한 페이지 내에서 각주를 다단에 위치시킬 방법을 설정하는 함수
     * bit 8-9
     *
     * @param [footnoteDisplayMethod] [HWPFootnoteDisplayMethod] 한 페이지 내에서 각주를 다단에 위치시킬 방법 값
     */
    fun setFootnoteDisplayMethod(footnoteDisplayMethod: HWPFootnoteDisplayMethod) {
        value = set(value, 8, 9, footnoteDisplayMethod.value.toInt())
    }

    /**
     * 한 페이지 내에서 미주를 다단에 위치시킬 방법을 반환하는 함수
     * bit 8-9
     *
     * @return [HWPEndnoteDisplayMethod] 한 페이지 내에서 각주를 다단에 위치시킬 방법 반환
     */
    fun getEndnoteDisplayMethod() : HWPEndnoteDisplayMethod = HWPEndnoteDisplayMethod.valueOf(get(value, 8, 9).toByte())

    /**
     * 한 페이지 내에서 미주를 다단에 위치시킬 방법을 반환하는 함수
     * bit 8-9
     *
     * @param [endnoteDisplayMethod] [HWPEndnoteDisplayMethod] 한 페이지 내에서 미주를 다단에 위치시킬 방법값
     */
    fun setEndnoteDisplayMethod(endnoteDisplayMethod: HWPEndnoteDisplayMethod) {
        value = set(value, 8, 9, endnoteDisplayMethod.value.toInt())
    }

    /**
     * numbering을 반환하는 함수
     * bit  10-11
     *
     * @return [HWPNumberingMethod] numbering 반환
     */
    fun getNumberingMethod() : HWPNumberingMethod = HWPNumberingMethod.valueOf(get(value, 10, 11).toByte())

    /**
     * numbering을 설정하는 함수
     * bit  10-11
     *
     * @param [numberingMethod] [HWPNumberingMethod] numbering 값
     */
    fun setNumberingMethod(numberingMethod: HWPNumberingMethod) {
        value = set(value, 10, 11, numberingMethod.value.toInt())
    }

    /**
     * 각주 내용 중 번호 코드의 모양을 위 첨자 형식으로 할지 여부를 반환하는 함수
     * bit 12
     *
     * @return [Boolean] 각주 내용 중 번호 코드의 모양을 위 첨자 형식으로 할지 여부 반환
     */
    fun isDisplayWithSupscript() : Boolean = get(value, 12)

    /**
     * 각주 내용 중 번호 코드의 모양을 위 첨자 형식으로 할지 여부를 설정하는 함수
     * bit 12
     *
     * @param [displayWithSupscript] [Boolean] 각주 내용 중 번호 코드의 모양을 위 첨자 형식으로 할지 여부값
     */
    fun setDisplayWithSupscript(displayWithSupscript: Boolean) {
        value = set(value, 12, displayWithSupscript)
    }

    /**
     * 텍스트에 이어 바로 출력할지 여부를 반환하는 함수
     * bit 13
     *
     * @return [Boolean] 텍스트에 이어 바로 출력할지 여부 반환
     */
    fun isContinueFromText() : Boolean = get(value, 13)

    /**
     * 텍스트에 이어 바로 출력할지 여부를 설정하는 함수
     * bit 13
     *
     * @param [continueFromText] [Boolean] 텍스트에 이어 바로 출력할지 여부값
     */
    fun setContinueFromText(continueFromText: Boolean) {
        value = set(value, 13, continueFromText)
    }
}

/**
 * 각주/미주 모양을 나타내는 객체
 * Tag ID: HWPTAG_FOOTNOTE_SHAPE [FOOTNOTE_SHAPE]
 * 26 bytes
 *
 * @author accforaus
 *
 * @property [property] [HWPFootnoteShapeProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [userSymbol] [String], 사용자 기호 (WCHAR - unsigned 2 bytes)
 * @property [beforeDecorativeLetter] [String], 앞 장식 문자 (WCHAR - unsigned 2 bytes)
 * @property [afterDecorativeLetter] [String], 뒤 장식 문자 (WCHAR - unsigned 2 bytes)
 * @property [startNumber] [Int], 시작 번호 (UINT16 - unsigned 2 bytes)
 * @property [divideLineLength] [Long], 구분선 길이 (HWPUNIT16 - unsigned 4 bytes)
 * @property [divideLineTopMargin] [Int], 구분선 위 여백 (HWPUNIT16 - unsigned 2 bytes)
 * @property [divideLineBottomMargin] [Int], 구분선 아래 여백 (HWPUNIT16 - unsigned 2 bytes)
 * @property [betweenNotesMargin] [Int], 주석 사이 여백 (HWPUNIT16 -unsigned 2 bytes)
 * @property [divideLineSort] [HWPBorderType], 구분선 종류 (UINT8 - unsigned 1 byte)
 * @property [divideLineThickness] [HWPBorderThickness], 구분선 굵기 (UINT8 - unsigned 1 byte)
 * @property [divideLineColor] [Color4Byte], 구분선 색상 (COLORREF - unsigned 4 bytes)
 */
class HWPFootEndNoteShape {
    var property: HWPFootnoteShapeProperty = HWPFootnoteShapeProperty()
    var userSymbol: String? = null
    var beforeDecorativeLetter: String? = null
    var afterDecorativeLetter: String? = null
    var startNumber: Int = 0
    var divideLineLength: Long = 0
    var divideLineTopMargin: Int = 0
    var divideLineBottomMargin: Int = 0
    var betweenNotesMargin: Int = 0
    var divideLineSort: HWPBorderType = HWPBorderType.Solid
    var divideLineThickness: HWPBorderThickness = HWPBorderThickness.MM0_1
    var divideLineColor: Color4Byte = Color4Byte()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPFootEndNoteShape] 복사된 객체 반환
     */
    fun copy() : HWPFootEndNoteShape = HWPFootEndNoteShape().also {
        it.property.value = this.property.value
        it.userSymbol = this.userSymbol
        it.beforeDecorativeLetter = this.beforeDecorativeLetter
        it.afterDecorativeLetter = this.afterDecorativeLetter
        it.startNumber = this.startNumber
        it.divideLineLength = this.divideLineLength
        it.divideLineTopMargin = this.divideLineTopMargin
        it.divideLineBottomMargin = this.divideLineBottomMargin
        it.betweenNotesMargin = this.betweenNotesMargin
        it.divideLineSort = HWPBorderType.valueOf(this.divideLineSort.value)
        it.divideLineThickness = HWPBorderThickness.valueOf(this.divideLineThickness.value)
        it.divideLineColor.value = this.divideLineColor.value
    }
}