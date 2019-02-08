package com.tang.hwplib.objects.docinfo.parashape

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 문단 모양 속성1을 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 문단 모양 속성1의 값을 가진 데이터
 */
class HWPParaShapeProperty1 {
    var value: Long = 0

    /**
     * 줄 간격 종류를 반환하는 함수
     * 한글 2007 이하 버전에서 사용
     * bit 0-1
     *
     * @return [HWPLineSpaceSort] 줄 간격 종류 반환
     */
    fun getLineSpaceSort() : HWPLineSpaceSort = HWPLineSpaceSort.valueOf(get(value, 0, 1).toByte())

    /**
     * 줄 간격 종류를 설정하는 함수
     * 한글 2007 이하 버전에서 사용
     * bit 0-1
     *
     * @param [lineSpaceSort] [HWPLineSpaceSort], 줄 간격 종류값을 가진 데이터
     */
    fun setLineSpaceSort(lineSpaceSort: HWPLineSpaceSort) {
        value = set(value, 0, 1, lineSpaceSort.value.toInt())
    }

    /**
     * 정렬 방식을 반환하는 함수
     * bit 2-4
     *
     * @return [HWPAlignment] 정렬 방식 반환
     */
    fun getAlignment() : HWPAlignment = HWPAlignment.valueOf(get(value, 2, 4).toByte())

    /**
     * 정렬 방식을 설정하는 함수
     * bit 2-4
     *
     * @param [alignment] [HWPAlignment], 정렬 방식값을 가진 데이터
     */
    fun setAlignment(alignment: HWPAlignment) {
        value = set(value, 2, 4, alignment.value.toInt())
    }

    /**
     * 줄 나눔 기준 영어 단위를 반환하는 함수
     * bit 5-6
     *
     * @return [HWPLineDivideForEnglish] 줄 나눔 기준 영어 단위 반환
     */
    fun getLineDivideForEnglish() : HWPLineDivideForEnglish = HWPLineDivideForEnglish.valueOf(get(value, 5, 6).toByte())

    /**
     * 줄 나눔 기준 영어 단위를 설정하는 함수
     * bit 5-6
     *
     * @param [lineDivideForEnglish] [HWPLineDivideForEnglish], 줄 나눔 기준 영어 단위값을 가진 데이터
     */
    fun setLineDivideForEnglish(lineDivideForEnglish: HWPLineDivideForEnglish) {
        value = set(value, 5, 6, lineDivideForEnglish.value.toInt())
    }

    /**
     * 줄 나눔 기준 한글 단위를 반환하는 함수
     * bit 7
     *
     * @return [HWPLineDivideForHangul] 줄 나눔 기준 한글 단위 반환
     */
    fun getLineDivideForHangul() : HWPLineDivideForHangul = if (get(value, 7)) HWPLineDivideForHangul.ByLetter else HWPLineDivideForHangul.ByWord

    /**
     * 줄 나눔 기준 한글 단위를 설정하는 함수
     * bit 7
     *
     * @param [lineDivideForHangul] [HWPLineDivideForHangul], 줄 나눔 기준 한글 단위값을 가진 데이터
     */
    fun setLineDivideForHangul(lineDivideForHangul: HWPLineDivideForHangul) {
        if (lineDivideForHangul == HWPLineDivideForHangul.ByLetter)
            value = set(value, 7, true)
        else
            value = set(value, 7, false)
    }

    /**
     * 편집 용지의 줄 격자 사용 여부를 반환하는 함수
     * bit 8
     *
     * @return [Boolean] 편집 용지의 줄 격자 사용 여부 반환
     */
    fun isUseGrid() : Boolean = get(value, 8)

    /**
     * 편집 용지의 줄 격자 사용 여부를 설정하는 함수
     * bit 8
     *
     * @param [useGrid] [Boolean], 편집 용지의 줄 격자 사용 여부값을 가진 데이터
     */
    fun setUseGrid(useGrid: Boolean) {
        value = set(value, 8, useGrid)
    }

    /**
     * 공백 최소값을 반환하는 함수
     * 0% - 75%
     * bit 9-15
     *
     * @return [Byte] 공백 최소값 반환
     */
    fun getMinimumSpace() : Byte = get(value, 9, 15).toByte()

    /**
     * 공백 최소값을 설정하는 함수
     * 0% - 75%
     * bit 9-15
     *
     * @param [minimumSpace] [Byte], 공백 최소값을 가진 데이터
     */
    fun setMinimumSpace(minimumSpace: Byte) {
        value = set(value, 9, 15, minimumSpace.toInt())
    }

    /**
     * 외톨이줄 보호 여부를 반환하는 함수
     * bit 16
     *
     * @return [Boolean] 외톨이줄 보호 여부 반환
     */
    fun isProtectLonger() : Boolean = get(value, 16)

    /**
     * 외톨이줄 보호 여부를 설정하는 함수
     * bit 16
     *
     * @param [protectLonger] [Boolean], 외톨이줄 보호 여부값을 가진 데이터
     */
    fun setProtectLonger(protectLonger: Boolean) {
        value = set(value, 16, protectLonger)
    }

    /**
     * 다음 문단과 함께 여부를 반환하는 함수
     * bit 17
     *
     * @return [Boolean] 다음 문단과 함께 여부 반환
     */
    fun isTogetherNextPara() : Boolean = get(value, 17)

    /**
     * 다음 문단과 함께 여부를 설정하는 함수
     * bit 17
     *
     * @param [togetherNextPara] [Boolean], 다음 문단과 함께 여부값을 가진 데이터
     */
    fun setTogetherNextPara(togetherNextPara: Boolean) {
        value = set(value, 17, togetherNextPara)
    }

    /**
     * 문단 보호 여부를 반환하는 함수
     * bit 18
     *
     * @return [Boolean] 문단 보호 여부 반환
     */
    fun isProtectPara() : Boolean = get(value, 18)

    /**
     * 문단 보호 여부를 설정하는 함수
     * bit 18
     *
     * @param [protectPara] [Boolean], 문단 보호 여부값을 가진 데이터
     */
    fun setProtectPara(protectPara: Boolean) {
        value = set(value, 18, protectPara)
    }

    /**
     * 문단 앞에서 항상 쪽 나눔 여부를 반환하는 함수
     * bit 19
     *
     * @return [Boolean] 문단 앞에서 항상 쪽 나눔 여부 반환
     */
    fun isSplitPageBeforePara() : Boolean = get(value, 19)

    /**
     * 문단 앞에서 항상 쪽 나눔 여부를 설정하는 함수
     * bit 19
     *
     * @param [splitPageBeforePage] [Boolean], 문단 앞에서 항상 쪽 나눔 여부값을 가진 데이터
     */
    fun setSplitPageBeforePage(splitPageBeforePage: Boolean) {
        value = set(value, 19, splitPageBeforePage)
    }

    /**
     * 세로 정렬을 반환하는 함수
     * bit 20-21
     *
     * @return [HWPVerticalAlignment] 세로 정렬 반환
     */
    fun getVerticalAlignment() : HWPVerticalAlignment = HWPVerticalAlignment.valueOf(get(value, 20, 21).toByte())

    /**
     * 세로 정렬을 설정하는 함수
     * bit 20-21
     *
     * @param [verticalAlignment] [HWPVerticalAlignment], 세로 정렬값을 가진 데이터
     */
    fun setVerticalAlignment(verticalAlignment: HWPVerticalAlignment) {
        value = set(value, 20, 21, verticalAlignment.value.toInt())
    }

    /**
     * 글꼴에 어울리는 줄 높이 여부를 반환하는 함수
     * bit 22
     *
     * @return [Boolean] 글꼴에 어울리는 줄 높이 여부 반환
     */
    fun isLineHeightForFont() : Boolean = get(value, 22)

    /**
     * 글꼴에 어울리는 줄 높이 여부를 설정하는 함수
     * bit 22
     *
     * @param [lineHeightForFont] [Boolean], 글꼴에 어울리는 줄 높이 여부값을 가진 데이터
     */
    fun setLineHeightForFont(lineHeightForFont: Boolean) {
        value = set(value, 22, lineHeightForFont)
    }

    /**
     * 문단 머리 모양 종류를 반환하는 함수
     * bit 23-24
     *
     * @return [HWPParaHeadShape] 문단 머리 모양 종류 반환
     */
    fun getParaHeadShape() : HWPParaHeadShape = HWPParaHeadShape.valueOf(get(value, 23, 24).toByte())

    /**
     * 문단 머리 모양 종류를 설정하는 함수
     * bit 23-24
     *
     * @param [paraHeadShape] [HWPParaHeadShape], 문단 머리 모양 종류값을 가진 데이터
     */
    fun setParaHeadShape(paraHeadShape: HWPParaHeadShape) {
        value = set(value, 23, 24, paraHeadShape.value.toInt())
    }

    fun getParaLevel() : Byte = get(value, 25, 27).toByte()
    fun setParaLevel(paraLevel: Byte) {
        value = set(value, 25, 27, paraLevel.toInt())
    }

    /**
     * 문단 테두리 연결 여부를 반환하는 함수
     * bit 28
     *
     * @return [Boolean] 문단 테두리 연결 여부 반환
     */
    fun isLinkBorder() : Boolean = get(value, 28)

    /**
     * 문단 테두리 연결 여부를 설정하는 함수
     * bit 28
     *
     * @param [linkBorder] [Boolean], 문단 테두리 연결 여부값을 가진 데이터
     */
    fun setLinkBorder(linkBorder: Boolean) {
        value = set(value, 28, linkBorder)
    }

    /**
     * 문단 여백 무시 여부를 반환하는 함수
     * bit 29
     *
     * @return [Boolean] 문단 여백 무시 여부 반환
     */
    fun isIgnoreParaMargin() : Boolean = get(value, 29)

    /**
     * 문단 여백 무시 여부를 설정하는 함수
     * bit 29
     *
     * @param [ignoreParaMargin] [Boolean], 문단 여백 무시 여부값을 가진 데이터
     */
    fun setIgnoreParaMargin(ignoreParaMargin: Boolean) {
        value = set(value, 29, ignoreParaMargin)
    }

    /**
     * 문단 꼬리 모양을 반환하는 함수
     * bit 30
     *
     * @return [Boolean] 문단 꼬리 모양 반환
     */
    fun getParaTailShape() : Boolean = get(value, 30)

    /**
     * 문단 꼬리 모양을 설정하는 함수
     * bit 30
     *
     * @param [paraTailShape] [Boolean], 문단 꼬리 모양값을 가진 데이터
     */
    fun setParaTailShape(paraTailShape: Boolean) {
        value = set(value, 30, paraTailShape)
    }
}

/**
 * 문단 모양 속성2를 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 문단 모양 속성2 값을 가진 데이터
 */
class HWPParaShapeProperty2 {
    var value: Long = 0

    /**
     * 한 줄로 입력 여부를 반환하는 함수
     * bit 0-1
     *
     * @return [Boolean] 한 줄로 입력 여부를 반환
     */
    fun isInputSingleLine() : Boolean = get(value, 0)

    /**
     * 한 줄로 입력 여부를 설정하는 함수
     * bit 0-1
     *
     * @param [inputSingleLine] [Boolean] 한 줄로 입력 여부 값을 가진 데이터
     */
    fun setInputSingleLine(inputSingleLine: Boolean) {
        value = set(value, 0, inputSingleLine)
    }

    /**
     * 한글과 영어 간격을 자동 조절 여부를 반환하는 함수
     * bit 4
     *
     * @return [Boolean] 한글과 영어 간격을 자동 조절 여부를 반환
     */
    fun isAutoAdjustGapHangulEnglish() : Boolean = get(value, 4)

    /**
     * 한글과 영어 간격을 자동 조절 여부를 설정하는 함수
     * bit 4
     *
     * @param [autoAdjustGapHangulEnglish] [Boolean] 한글과 영어 간격을 자동 조절 여부 값을 가진 데이터
     */
    fun setAutoAdjustGapHangulEnglish(autoAdjustGapHangulEnglish: Boolean) {
        value = set(value, 4, autoAdjustGapHangulEnglish)
    }

    /**
     * 한글과 숫자 간격을 자동 조절 여부를 반환하는 함수
     * bit 5
     *
     * @return [Boolean] 한글과 숫자 간격을 자동 조절 여부를 반환
     */
    fun isAutoAdjustGapHangulNumber() : Boolean = get(value, 5)

    /**
     * 한글과 숫자 간격을 자동 조절 여부를 설정하는 함수
     * bit 5
     *
     * @param [autoAdjustGapHangulNumber] [Boolean] 한글과 숫자 간격을 자동 조절 여부 값을 가진 데이터
     */
    fun setAutoAdjustGapHangulNumber(autoAdjustGapHangulNumber: Boolean) {
        value = set(value, 5, autoAdjustGapHangulNumber)
    }
}

/**
 * 문단 모양 속성3를 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 문단 모양 속성3 값을 가진 데이터
 */
class HWPParaShapeProperty3 {
    var value: Long = 0

    /**
     * 줄 간격을 반환하는 함수
     * bit 0-4
     *
     * @return [HWPLineSpaceSort] 줄 간격 반환
     */
    fun getLineSpaceSort() : HWPLineSpaceSort = HWPLineSpaceSort.valueOf(get(value, 0, 4).toByte())

    /**
     * 줄 간격을 설정하는 함수
     * bit 0-4
     *
     * @param [lineSpaceSort] [HWPLineSpaceSort], 줄 간격값을 가진 데이터
     */
    fun setLineSpaceSort(lineSpaceSort: HWPLineSpaceSort) {
        value = set(value, 0, 4, lineSpaceSort.value.toInt())
    }
}
