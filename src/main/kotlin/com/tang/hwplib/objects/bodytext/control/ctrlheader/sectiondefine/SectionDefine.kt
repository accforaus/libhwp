package com.tang.hwplib.objects.bodytext.control.ctrlheader.sectiondefine

import com.tang.hwplib.objects.bodytext.HWPSection
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 텍스트 방향
 * 
 * @author accforaus
 * 
 * @property [value] [Byte], 텍스트 방향 값
 */
enum class HWPTextDirection(v: Byte) {
    /**
     * 가로
     */
    Horizontal(0.toByte()),
    /**
     * 세로
     */
    VerticalWithEnglishLayDown(1.toByte()),
    /**
     * 세로
     */
    VerticalWithEnglishStanding(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPTextDirection] enum 값
         */
        fun valueOf(v: Byte) : HWPTextDirection {
            for (td in values())
                if (td.value == v)
                    return td
            return Horizontal
        }
    }
}

/**
 * 구역 정의 헤더 속성을 나타내는 객체
 * UINT32 - unsigned 4 bytes
 * 
 * @author accforaus
 * 
 * @property [value] [Long], 구역 정의 헤더 속성 값
 */
class HWPSectionDefineHeaderProperty {
    var value: Long = 0
        set(newValue) {
            field = newValue
            _isHideHeader = isHideHeader()
            _isHideFooter = isHideFooter()
            _isHideBatangPage = isHideBatangPage()
            _isHideBackground = isHideBackground()
            _isHideBorder = isHideBorder()
            _isHidePageNumberPosition = isHidePageNumberPosition()
            _isDisplayBackgroundAtFirstPageOfSection = isDisplayBackgroundAtFirstPageOfSection()
            _isDisplayBorderAtFirstPageOfSection = isDisplayBorderAtFirstPageOfSection()
            _textDirection = getTextDirection()
            _isHideEmptyLine = isHideEmptyLine()
            _isApplyPageNumberByDivideSection = isApplyPageNumberByDivideSection()
            _isApplyWongoji = isApplyWongoji()
        }
    private var _isHideHeader: Boolean = false
    private var _isHideFooter: Boolean = false
    private var _isHideBatangPage: Boolean = false
    private var _isHideBorder: Boolean = false
    private var _isHideBackground: Boolean = false
    private var _isHidePageNumberPosition: Boolean = false
    private var _isDisplayBorderAtFirstPageOfSection: Boolean = false
    private var _isDisplayBackgroundAtFirstPageOfSection: Boolean = false
    private var _textDirection: HWPTextDirection = HWPTextDirection.Horizontal
    private var _isHideEmptyLine: Boolean = false
    private var _isApplyPageNumberByDivideSection: Boolean = false
    private var _isApplyWongoji: Boolean = false

    /**
     * 머리말을 감출지 여부를 반환하는 함수
     * bit 0
     * 
     * @return [Boolean] 머리말을 감출지 여부 반환
     */
    fun isHideHeader() : Boolean = get(value, 0)

    /**
     * 머리말을 감출지 여부를 설정하는 함수
     * bit 0
     *
     * @param [hideHeader] [Boolean] 머리말을 감출지 여부값
     */
    fun setHideHeader(hideHeader: Boolean) {
        value = set(value, 0, hideHeader)
    }

    /**
     * 꼬리말을 감출지 여부를 반환하는 함수
     * bit 1
     *
     * @return [Boolean] 꼬리말을 감출지 여부 반환
     */
    fun isHideFooter() : Boolean = get(value, 1)

    /**
     * 꼬리말을 감출지 여부를 설정하는 함수
     * bit 1
     *
     * @param [hideFooter] [Boolean] 꼬리말을 감출지 여부값
     */
    fun setHideFooter(hideFooter: Boolean) {
        value = set(value, 1, hideFooter)
    }
    
    /**
     * 바탕쪽을 감출지 여부를 반환하는 함수
     * bit 2
     *
     * @return [Boolean] 바탕쪽을 감출지 여부 반환
     */
    fun isHideBatangPage() : Boolean = get(value, 2)

    /**
     * 바탕쪽을 감출지 여부를 설정하는 함수
     * bit 2
     *
     * @param [hideBatangPage] [Boolean] 바탕쪽을 감출지 여부값
     */
    fun setHideBatangPage(hideBatangPage: Boolean) {
        value = set(value, 2, hideBatangPage)
    }

    /**
     * 테두리을 감출지 여부를 반환하는 함수
     * bit 3
     *
     * @return [Boolean] 테두리을 감출지 여부 반환
     */
    fun isHideBorder() : Boolean = get(value, 3)

    /**
     * 테두리을 감출지 여부를 설정하는 함수
     * bit 3
     *
     * @param [hideBorder] [Boolean] 테두리을 감출지 여부값
     */
    fun setHideBorder(hideBorder: Boolean) {
        value = set(value, 3, hideBorder)
    }

    /**
     * 배경을 감출지 여부를 반환하는 함수
     * bit 4
     *
     * @return [Boolean] 배경을 감출지 여부 반환
     */
    fun isHideBackground() : Boolean = get(value, 4)

    /**
     * 배경을 감출지 여부를 설정하는 함수
     * bit 4
     *
     * @param [hideBackground] [Boolean] 배경을 감출지 여부값
     */
    fun setHideBackground(hideBackground: Boolean) {
        value = set(value, 4, hideBackground)
    }

    /**
     * 쪽 번호 위치를 감출지 여부를 반환하는 함수
     * bit 5
     *
     * @return [Boolean] 쪽 번호 위치를 감출지 여부 반환
     */
    fun isHidePageNumberPosition() : Boolean = get(value, 5)

    /**
     * 쪽 번호 위치를 감출지 여부를 설정하는 함수
     * bit 5
     *
     * @param [hidePageNumberPosition] [Boolean] 쪽 번호 위치를 감출지 여부값
     */
    fun setHidePageNumberPosition(hidePageNumberPosition: Boolean) {
        value = set(value, 5, hidePageNumberPosition)
    }

    /**
     * 구역의 첫 쪽에만 테두리 표시 여부를 반환하는 함수
     * bit 8
     *
     * @return [Boolean] 구역의 첫 쪽에만 테두리 표시 여부 반환
     */
    fun isDisplayBorderAtFirstPageOfSection() : Boolean = get(value, 8)

    /**
     * 구역의 첫 쪽에만 테두리 표시 여부를 설정하는 함수
     * bit 8
     *
     * @param [displayBorderAtFirstPageOfSection] [Boolean] 구역의 첫 쪽에만 테두리 표시 여부값
     */
    fun setDisplayBorderAtFirstPageOfSection(displayBorderAtFirstPageOfSection: Boolean) {
        value = set(value, 8, displayBorderAtFirstPageOfSection)
    }

    /**
     * 구역의 첫 쪽에만 배경 표시 여부를 반환하는 함수
     * bit 9
     *
     * @return [Boolean] 구역의 첫 쪽에만 배경 표시 여부 반환
     */
    fun isDisplayBackgroundAtFirstPageOfSection() : Boolean = get(value, 9)

    /**
     * 구역의 첫 쪽에만 배경 표시 여부를 설정하는 함수
     * bit 9
     *
     * @param [displayBackgroundAtFirstPageOfSection] [Boolean] 구역의 첫 쪽에만 배경 표시 여부값
     */
    fun setDisplayBackgroundAtFirstPageOfSection(displayBackgroundAtFirstPageOfSection: Boolean) {
        value = set(value, 9, displayBackgroundAtFirstPageOfSection)
    }

    /**
     * 텍스트 방향을 반환하는 함수
     * bit 16-18
     *
     * @return [HWPTextDirection] 텍스트 방향 반환
     */
    fun getTextDirection() : HWPTextDirection = HWPTextDirection.valueOf(get(value, 16, 18).toByte())

    /**
     * 텍스트 방향을 설정하는 함수
     * bit 16-18
     *
     * @param [textDirection] [HWPTextDirection] 텍스트 방향값
     */
    fun setTextDirection(textDirection: HWPTextDirection) {
        value = set(value, 16, 18, textDirection.value.toInt())
    }

    /**
     * 빈 줄 감춤 여부를 반환하는 함수
     * bit 19
     *
     * @return [Boolean] 빈 줄 감춤 여부 반환
     */
    fun isHideEmptyLine() : Boolean = get(value, 19)

    /**
     * 빈 줄 감춤 여부를 설정하는 함수
     * bit 19
     *
     * @param [hideEmptyLine] [Boolean] 빈 줄 감춤 여부값
     */
    fun setHideEmptyLine(hideEmptyLine: Boolean) {
        value = set(value, 19, hideEmptyLine)
    }

    /**
     * 구역 나눔으로 새 페이지가 생길 때의 페이지 번호 적용할지 여부를 반환하는 함수
     * bit 20-21
     *
     * @return [Boolean] 구역 나눔으로 새 페이지가 생길 때의 페이지 번호 적용할지 여부 반환
     */
    fun isApplyPageNumberByDivideSection() : Boolean = get(value, 20) or get(value, 21)

    /**
     * 구역 나눔으로 새 페이지가 생길 때의 페이지 번호 적용할지 여부를 설정하는 함수
     * bit 20-21
     *
     * @param [applyPageNumberByDivideSection] [Boolean] 구역 나눔으로 새 페이지가 생길 때의 페이지 번호 적용할지 여부값
     */
    fun setApplyPageNumberByDivideSection(applyPageNumberByDivideSection: Boolean) {
        value = set(value, 20, applyPageNumberByDivideSection)
        value = set(value, 21, applyPageNumberByDivideSection)
    }

    /**
     * 원고지 정서법 적용 여부를 반환하는 함수
     * bit 22
     *
     * @return [Boolean] 원고지 정서법 적용 여부 반환
     */
    fun isApplyWongoji() : Boolean = get(value, 22)

    /**
     * 원고지 정서법 적용 여부를 설정하는 함수
     * bit 22
     *
     * @param [applyWongoji] [Boolean] 원고지 정서법 적용 여부값
     */
    fun setApplyWongoji(applyWongoji: Boolean) {
        value = set(value, 22, applyWongoji)
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPSectionDefineHeaderProperty] 생성된 객체 반환
         */
        fun build(hideHeader: Boolean = false,
                  hideFooter: Boolean = false,
                  hideBatangPage: Boolean = false,
                  hideBorder: Boolean = false,
                  hideBackground: Boolean = false,
                  hidePageNumberPosition: Boolean = false,
                  displayBorderAtFirstPageOfSection: Boolean = false,
                  displayBackgroundAtFirstPageOfSection: Boolean,
                  textDirection: HWPTextDirection = HWPTextDirection.Horizontal,
                  hideEmptyLine: Boolean = false,
                  applyPageNumberByDivideSection: Boolean = false,
                  applyWongoji: Boolean = false)
                : HWPSectionDefineHeaderProperty = HWPSectionDefineHeaderProperty().apply {
            setHideHeader(hideHeader)
            setHideFooter(hideFooter)
            setHideBatangPage(hideBatangPage)
            setHideBorder(hideBorder)
            setHideBackground(hideBackground)
            setHidePageNumberPosition(hidePageNumberPosition)
            setDisplayBorderAtFirstPageOfSection(displayBorderAtFirstPageOfSection)
            setDisplayBackgroundAtFirstPageOfSection(displayBackgroundAtFirstPageOfSection)
            setTextDirection(textDirection)
            setHideEmptyLine(hideEmptyLine)
            setApplyPageNumberByDivideSection(applyPageNumberByDivideSection)
            setApplyWongoji(applyWongoji)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPSectionDefineHeaderProperty] 생성된 객체 반환
         */
        fun build(value: Long = 0) : HWPSectionDefineHeaderProperty = HWPSectionDefineHeaderProperty().apply {
            this.value = value
        }
    }
}