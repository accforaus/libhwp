package com.tang.hwplib.objects.bodytext.paragraph.linesegment

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 레이아웃 세그먼트 태그를 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 레이아웃 세그먼트 태그값을 가진 데이터
 */
class HWPLineSegItemTag {
    var value: Long = 0

    /**
     * 페이지의 첫 줄인지 여부를 반환하는 함수
     * bit 0
     *
     * @return [Boolean] 페이지의 첫 줄인지 여부를 반환
     */
    fun isFirstLineAtPage() : Boolean = get(value, 0)

    /**
     * 페이지의 첫 줄인지 여부를 설정하는 함수
     * bit 0
     *
     * @param [firstLineAtPage] [Boolean], 페이지의 첫 줄인지 여부의 참/거짓 값을 가진 데이터
     */
    fun setFirstLineAtPage(firstLineAtPage: Boolean) {
        value = set(value, 0, firstLineAtPage)
    }

    /**
     * 컬럼의 첫 줄인지 여부를 반환하는 함수
     * bit 1
     *
     * @return [Boolean] 컬럼의 첫 줄인지 여부를 반환
     */
    fun isFirstLineAtColumn() : Boolean = get(value, 1)

    /**
     * 컬럼의 첫 줄인지 여부를 설정하는 함수
     * bit 1
     *
     * @param [firstLineAtColumn] [Boolean], 컬럼의 첫 줄인지 여부의 참/거짓 값을 가진 데이터
     */
    fun setFirstLineAtColumn(firstLineAtColumn: Boolean) {
        value = set(value, 1, firstLineAtColumn)
    }

    /**
     * 테스트가 배열되지 않은 빈 세그먼트인지 여부를 반환하는 함수
     * bit 16
     *
     * @return [Boolean] 테스트가 배열되지 않은 빈 세그먼트인지 여부를 반환
     */
    fun isEmptySegment() : Boolean = get(value, 16)

    /**
     * 테스트가 배열되지 않은 빈 세그먼트인지 여부를 설정하는 함수
     * bit 16
     *
     * @param [emptySegment] [Boolean], 테스트가 배열되지 않은 빈 세그먼트인지 여부의 참/거짓 값을 가진 데이터
     */
    fun setEmptySegment(emptySegment: Boolean) {
        value = set(value, 16, emptySegment)
    }

    /**
     * 줄의 첫 세그먼트인지 여부를 설정하는 함수
     * bit 17
     *
     * @param [firstLineAtPage] [Boolean], 줄의 첫 세그먼트인지 여부의 참/거짓 값을 가진 데이터
     */

    fun isFirstSegmentAtLine() : Boolean = get(value, 17)

    /**
     * 줄의 첫 세그먼트인지 여부를 설정하는 함수
     * bit 17
     *
     * @param [firstSegmentAtLine] [Boolean], 줄의 첫 세그먼트인지 여부의 참/거짓 값을 가진 데이터
     */
    fun setFirstSegmentAtLine(firstSegmentAtLine: Boolean) {
        value = set(value, 17, firstSegmentAtLine)
    }

    /**
     * 줄의 마지막 세그먼트 인지 여부를 반환하는 함수
     * bit 18
     *
     * @return [Boolean] 줄의 마지막 세그먼트 인지 여부를 반환
     */
    fun isLastSegmentAtLine() : Boolean = get(value, 18)

    /**
     * 줄의 마지막 세그먼트 인지 여부를 설정하는 함수
     * bit 18
     *
     * @param [lastSegmentAtLine] [Boolean], 줄의 마지막 세그먼트 인지 여부의 참/거짓 값을 가진 데이터
     */
    fun setLastSegmentAtLine(lastSegmentAtLine: Boolean) {
        value = set(value, 18, lastSegmentAtLine)
    }

    /**
     * 줄의 마지막에 auto-hyphenation이 수행되었는지 여부를 반환하는 함수
     * bit 19
     *
     * @return [Boolean] 줄의 마지막에 auto-hyphenation이 수행되었는지 여부를 반환
     */
    fun isAutoHyphenation() : Boolean = get(value, 19)

    /**
     * 줄의 마지막에 auto-hyphenation이 수행되었는지 여부를 설정하는 함수
     * bit 19
     *
     * @param [autoHyphenation] [Boolean], 줄의 마지막에 auto-hyphenation이 수행되었는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setAutoHyphenation(autoHyphenation: Boolean) {
        value = set(value, 19, autoHyphenation)
    }

    /**
     * indentaion 적용 여부를 반환하는 함수
     * bit 20
     *
     * @return [Boolean] indentaion 적용 여부를 반환
     */
    fun isAdjustIndentation() : Boolean = get(value, 20)

    /**
     * indentaion 적용 여부를 설정하는 함수
     * bit 20
     *
     * @param [adjustIndentation] [Boolean], indentaion 적용 여부의 참/거짓 값을 가진 데이터
     */
    fun setAdjustIndentation(adjustIndentation: Boolean) {
        value = set(value, 20, adjustIndentation)
    }

    /**
     * 문단 머리 모양 적용 여부를 반환하는 함수
     * bit 21
     *
     * @return [Boolean] 문단 머리 모양 적용 여부를 반환
     */
    fun isAdjustBullet() : Boolean = get(value, 21)

    /**
     * 문단 머리 모양 적용 여부를 설정하는 함수
     * bit 21
     *
     * @param [adjustBullet] [Boolean], 문단 머리 모양 적용 여부의 참/거짓 값을 가진 데이터
     */
    fun setAdjustBullet(adjustBullet: Boolean) {
        value = set(value, 21, adjustBullet)
    }

    /**
     * 구현상의 편의를 위한 property를 반환하는 함수
     * bit 31
     *
     * @return [Boolean] 구현상의 편의를 위한 property를 반환
     */
    fun getBit31() : Boolean = get(value, 31)

    /**
     * 구현상의 편의를 위한 property를 설정하는 함수
     * bit 31
     *
     * @param [bit31] [Boolean], 구현상의 편의를 위한 property 값을 가진 데이터
     */
    fun setBit31(bit31: Boolean) {
        value = set(value, 31, bit31)
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPLineSegItemTag] 생성된 객체 반환
         */
        fun build(isFirstLineAtPage: Boolean = false,
                  isFirstLineAtColumn: Boolean = false,
                  isEmptySegment: Boolean = false,
                  isFirstSegmentAtLine: Boolean = false,
                  isLastSegmentAtLine: Boolean = false,
                  isAutoHyphenation: Boolean = false,
                  isAdjustIndentation: Boolean = false,
                  isAdjustBullet: Boolean = false,
                  bit31: Boolean = false)
                : HWPLineSegItemTag = HWPLineSegItemTag().apply {
            setFirstLineAtPage(isFirstLineAtPage)
            setFirstLineAtColumn(isFirstLineAtColumn)
            setEmptySegment(isEmptySegment)
            setFirstSegmentAtLine(isFirstSegmentAtLine)
            setLastSegmentAtLine(isLastSegmentAtLine)
            setAutoHyphenation(isAutoHyphenation)
            setAdjustIndentation(isAdjustIndentation)
            setAdjustBullet(isAdjustBullet)
            setBit31(bit31)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPLineSegItemTag] 생성된 객체 반환
         */
        fun build(value: Long = 0) : HWPLineSegItemTag = HWPLineSegItemTag().apply {
            this.value = value
        }
    }
}

/**
 * 레이아웃 세그먼트 아이템을 나타내는 객체
 * 36 bytes
 *
 * @author accforaus
 *
 * @property [textStartPosition] [Long], 텍스트의 시작 위치 (UINT32 - unsigned 4 bytes)
 * @property [lineVerticalPosition] [Int], 줄의 세로 위치 (INT32 - signed 4 bytes)
 * @property [lineHeight] [Int], 줄의 높이 (INT32 - signed 4 bytes)
 * @property [textPartHeight] [Int], 텍스트 부분의 높이 (INT32 - signed 4 bytes)
 * @property [distanceBaseLineToLineVerticalPosition] [Int], 줄의 세로 위치에서 베이스라인까지 거리 (INT32 - signed 4 bytes)
 * @property [lineSpace] [Int], 줄 간격 (INT32 - signed 4 bytes)
 * @property [startPositionFromColumn] [Int], 칼럼에서 시작 위치 (INT32 - signed 4 bytes)
 * @property [segmentWidth] [Int], 세그먼트의 폭
 * @property [tag] [HWPLineSegItemTag], 태그 (UINT32 - unsigned 4 bytes)
 */
class HWPLineSegItem {
    var textStartPosition: Long = 0
    var lineVerticalPosition: Int = 0
    var lineHeight: Int = 0
    var textPartHeight: Int = 0
    var distanceBaseLineToLineVerticalPosition: Int = 0
    var lineSpace: Int = 0
    var startPositionFromColumn: Int = 0
    var segmentWidth: Int = 0
    var tag: HWPLineSegItemTag = HWPLineSegItemTag()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPLineSegItem] 복사된 객체 반환
     */
    fun copy() : HWPLineSegItem = HWPLineSegItem().also {
        it.textStartPosition = this.textStartPosition
        it.lineVerticalPosition = this.lineVerticalPosition
        it.lineHeight = this.lineHeight
        it.textPartHeight = this.textPartHeight
        it.distanceBaseLineToLineVerticalPosition = this.distanceBaseLineToLineVerticalPosition
        it.lineSpace = this.lineSpace
        it.startPositionFromColumn = this.startPositionFromColumn
        it.segmentWidth = this.segmentWidth
        it.tag.value = this.tag.value
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPLineSegItem] 생성된 객체 반환
         */
        fun build(textStartPosition: Long = 0,
                  lineVerticalPosition: Int = 0,
                  lineHeight: Int = 0,
                  textPartHeight: Int = 0,
                  distanceBaseLineToLineVerticalPosition: Int = 0,
                  lineSpace: Int = 0,
                  startPositionFromColumn: Int = 0,
                  segmentWidth: Int = 0,
                  tag: HWPLineSegItemTag = HWPLineSegItemTag.build())
                : HWPLineSegItem = HWPLineSegItem().apply {
            this.textStartPosition = textStartPosition
            this.lineVerticalPosition = lineVerticalPosition
            this.lineHeight = lineHeight
            this.textPartHeight = textPartHeight
            this.distanceBaseLineToLineVerticalPosition = distanceBaseLineToLineVerticalPosition
            this.lineSpace = lineSpace
            this.startPositionFromColumn = startPositionFromColumn
            this.segmentWidth = segmentWidth
            this.tag = tag
        }
    }
}