package com.tang.hwplib.objects.bodytext.paragraph.header

import com.sun.org.apache.xpath.internal.operations.Bool
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * HWPControl Mask를 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], HWPControl Mask값을 가진 데이터
 */
class HWPControlMask {
    var value: Long = 0

    /**
     * 문단이 구역/단 정의 컨트롤을 가졌는지 여부를 반환하는 함수
     * bit 2
     *
     * @return [Boolean] 문단이 구역/단 정의 컨트롤을 가졌는지 여부
     */
    fun hasSectColDef() : Boolean = get(value, 2)

    /**
     * 문단이 구역/단 정의 컨트롤을 가졌는지 여부를 설정하는 함수
     * bit 2
     *
     * @param [hasSectColDef] [Boolean], 문단이 구역/단 정의 컨트롤을 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setHasSectColDef(hasSectColDef: Boolean) {
        value = set(value, 2, hasSectColDef)
    }

    /**
     * 필드 시작 컨트롤을 가졌는지 여부를 반환하는 함수
     * bit 3
     *
     * @return [Boolean] 필드 시작 컨트롤을 가졌는지 여부
     */
    fun hasFieldStart() : Boolean = get(value, 3)

    /**
     * 필드 시작 컨트롤을 가졌는지 여부를 설정하는 함수
     * bit 3
     *
     * @param [fieldStart] [Boolean], 필드 시작 컨트롤을 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setFieldStart(fieldStart: Boolean) {
        value = set(value, 3, fieldStart)
    }

    /**
     * 필드 끝 컨트롤을 가졌는지 여부를 반환하는 함수
     * bit 4
     *
     * @return [Boolean] 필드 끝 컨트롤을 가졌는지 여부
     */
    fun hasFieldEnd() : Boolean = get(value, 4)

    /**
     * 필드 끝 컨트롤을 가졌는지 여부를 설정하는 함수
     * bit 4
     *
     * @param [fieldEnd] [Boolean], 필드 끝 컨트롤을 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setFieldEnd(fieldEnd: Boolean) {
        value = set(value, 4, fieldEnd)
    }

    /**
     * Title Mark를 가졌는지 여부를 반환하는 함수
     * bit 8
     *
     * @return [Boolean] Title Mark를 가졌는지 여부
     */
    fun hasTitleMark() : Boolean = get(value, 8)

    /**
     * Title Mark를 가졌는지 여부를 설정하는 함수
     * bit 8
     *
     * @param [titleMark] [Boolean], Title Mark를 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setTitleMark(titleMark: Boolean) {
        value = set(value, 8, titleMark)
    }

    /**
     * 탭을 가졌는지 여부를 반환하는 함수
     * bit 9
     *
     * @return [Boolean] 탭을 가졌는지 여부
     */
    fun hasTab() : Boolean = get(value, 9)

    /**
     * 탭을 가졌는지 여부를 설정하는 함수
     * bit 9
     *
     * @param [tab] [Boolean], 탭을 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setTab(tab: Boolean) {
        value = set(value, 9, tab)
    }

    /**
     * 강제 줄 나눔을 가졌는지 여부를 반환하는 함수
     * bit 10
     *
     * @return [Boolean] 강제 줄 나눔을 가졌는지 여부
     */
    fun hasLineBreak() : Boolean = get(value, 10)

    /**
     * 강제 줄 나눔을 가졌는지 여부를 설정하는 함수
     * bit 10
     *
     * @param [lineBreak] [Boolean], 강제 줄 나눔을 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setHasLineBreak(lineBreak: Boolean) {
        value = set(value, 10, lineBreak)
    }

    /**
     * 그리기 객체 또는 표 객체를 가졌는지 여부를 반환하는 함수
     * bit 11
     *
     * @return [Boolean] 그리기 객체 또는 표 객체를 가졌는지 여부
     */
    fun hasGsoTable() : Boolean = get(value, 11)

    /**
     * 그리기 객체 또는 표 객체를 가졌는지 여부를 설정하는 함수
     * bit 11
     *
     * @param [gsoTable] [Boolean], 그리기 객체 또는 표 객체를 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setGsoTable(gsoTable: Boolean) {
        value = set(value, 11, gsoTable)
    }

    /**
     * 문단 나누기를 가졌는지 여부를 반환하는 함수
     * bit 13
     *
     * @return [Boolean] 문단 나누기를 가졌는지 여부
     */
    fun hasParaBreak() : Boolean = get(value, 13)

    /**
     * 문단 나누기를 가졌는지 여부를 설정하는 함수
     * bit 13
     *
     * @param [paraBreak] [Boolean], 문단 나누기를 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setParaBreak(paraBreak: Boolean) {
        value = set(value, 13, paraBreak)
    }

    /**
     * 숨은 설명을 가졌는지 여부를 반환하는 함수
     * bit 15
     *
     * @return [Boolean] 숨은 설명을 가졌는지 여부
     */
    fun hasHiddenComment() : Boolean = get(value, 15)

    /**
     * 숨은 설명을 가졌는지 여부를 설정하는 함수
     * bit 15
     *
     * @param [hiddenComment] [Boolean], 숨은 설명을 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setHiddenComment(hiddenComment: Boolean) {
        value = set(value, 15, hiddenComment)
    }

    /**
     * 머리말 또는 꼬리말을 가졌는지 여부를 반환하는 함수
     * bit 16
     *
     * @return [Boolean] 머리말 또는 꼬리말을 가졌는지 여부
     */
    fun hasHeaderFooter() : Boolean = get(value, 16)

    /**
     * 머리말 또는 꼬리말을 가졌는지 여부를 설정하는 함수
     * bit 16
     *
     * @param [headerFooter] [Boolean], 머리말 또는 꼬리말을 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setHeaderFooter(headerFooter: Boolean) {
        value = set(value, 16, headerFooter)
    }

    /**
     * 각주 또는 미주를 가졌는지 여부를 반환하는 함수
     * bit 17
     *
     * @return [Boolean] 각주 또는 미주를 가졌는지 여부
     */
    fun hasFootnoteEndnote() : Boolean = get(value, 17)

    /**
     * 각주 또는 미주를 가졌는지 여부를 설정하는 함수
     * bit 17
     *
     * @param [footnoteEndnote] [Boolean], 각주 또는 미주를 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setFootnoteEndnote(footnoteEndnote: Boolean) {
        value = set(value, 17, footnoteEndnote)
    }

    /**
     * 자동 번호를 가졌는지 여부를 반환하는 함수
     * bit 18
     *
     * @return [Boolean] 자동 번호를 가졌는지 여부
     */
    fun hasAutoNumber() : Boolean = get(value, 18)

    /**
     * 자동 번호를 가졌는지 여부를 설정하는 함수
     * bit 18
     *
     * @param [autoNumber] [Boolean], 자동 번호를 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setAutoNumber(autoNumber: Boolean) {
        value = set(value, 18, autoNumber)
    }

    /**
     * 페이지 컨트롤을 가졌는지 여부를 반환하는 함수
     * bit 21
     *
     * @return [Boolean] 페이지 컨트롤을 가졌는지 여부
     */
    fun hasPageControl() : Boolean = get(value, 21)

    /**
     * 페이지 컨트롤을 가졌는지 여부를 설정하는 함수
     * bit 21
     *
     * @param [pageControl] [Boolean], 페이지 컨트롤을 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setPageControl(pageControl: Boolean) {
        value = set(value, 21, pageControl)
    }

    /**
     * 책갈피/찾아보기 표시를 가졌는지 여부를 반환하는 함수
     * bit 22
     *
     * @return [Boolean] 책갈피/찾아보기 표시를 가졌는지 여부
     */
    fun hasBookmark() : Boolean = get(value, 22)

    /**
     * 책갈피/찾아보기 표시를 가졌는지 여부를 설정하는 함수
     * bit 22
     *
     * @param [bookmark] [Boolean], 책갈피/찾아보기 표시를 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setBookmark(bookmark: Boolean) {
        value = set(value, 22, bookmark)
    }

    /**
     * 덧말/글자 겹침을 가졌는지 여부를 반환하는 함수
     * bit 23
     *
     * @return [Boolean] 덧말/글자 겹침을 가졌는지 여부
     */
    fun hasAdditionalTextOverlappingLetter() : Boolean = get(value, 23)

    /**
     * 덧말/글자 겹침을 가졌는지 여부를 설정하는 함수
     * bit 23
     *
     * @param [additionalOverlappingLetter] [Boolean], 덧말/글자 겹침을 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setAdditionalTextOverlappingLetter(additionalOverlappingLetter: Boolean) {
        value = set(value, 23, additionalOverlappingLetter)
    }

    /**
     * 하이픈을 가졌는지 여부를 반환하는 함수
     * bit 24
     *
     * @return [Boolean] 하이픈을 가졌는지 여부
     */
    fun hasHyphen() : Boolean = get(value, 24)

    /**
     * 하이픈을 가졌는지 여부를 설정하는 함수
     * bit 24
     *
     * @param [hyphen] [Boolean], 하이픈을 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setHyphen(hyphen: Boolean) {
        value = set(value, 24, hyphen)
    }

    /**
     * 묶음 빈칸을 가졌는지 여부를 반환하는 함수
     * bit 30
     *
     * @return [Boolean] 묶음 빈칸을 가졌는지 여부
     */
    fun hasBundleBlank() : Boolean = get(value, 30)

    /**
     * 묶음 빈칸을 가졌는지 여부를 설정하는 함수
     * bit 30
     *
     * @param [bundleBlank] [Boolean], 묶음 빈칸을 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setBundleBlank(bundleBlank: Boolean) {
        value = set(value, 30, bundleBlank)
    }

    /**
     * 고정 폭 빈칸을 가졌는지 여부를 반환하는 함수
     * bit 31
     *
     * @return [Boolean] 고정 폭 빈칸을 가졌는지 여부
     */
    fun hasFixWidthBlank() : Boolean = get(value, 31)

    /**
     * 고정 폭 빈칸을 가졌는지 여부를 설정하는 함수
     * bit 31
     *
     * @param [fixWidthBlank] [Boolean], 고정 폭 빈칸을 가졌는지 여부의 참/거짓 값을 가진 데이터
     */
    fun setFixWidthBlank(fixWidthBlank: Boolean) {
        value = set(value, 31, fixWidthBlank)
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlMask] 생성된 객체 반환
         */
        fun build(hasSectColDef: Boolean = false,
                  hasFieldStart: Boolean = false,
                  hasFieldEnd: Boolean = false,
                  hasTitleMark: Boolean = false,
                  hasTab: Boolean = false,
                  hasLineBreak: Boolean = false,
                  hasGsoTable: Boolean = false,
                  hasParaBreak: Boolean = false,
                  hasHiddenComment: Boolean = false,
                  hasHeaderFooter: Boolean = false,
                  hasFootnoteEndnote: Boolean = false,
                  hasAutoNumber: Boolean = false,
                  hasPageControl: Boolean = false,
                  hasBookmark: Boolean = false,
                  hasAdditionalTextOverlappingLetter: Boolean = false,
                  hasHyphen: Boolean = false,
                  hasBundleBlank: Boolean = false,
                  hasFixWidthBlank: Boolean = false)
                : HWPControlMask = HWPControlMask().apply {
            setHasSectColDef(hasSectColDef)
            setFieldStart(hasFieldStart)
            setFieldEnd(hasFieldEnd)
            setTitleMark(hasTitleMark)
            setTab(hasTab)
            setHasLineBreak(hasLineBreak)
            setGsoTable(hasGsoTable)
            setParaBreak(hasParaBreak)
            setHiddenComment(hasHiddenComment)
            setHeaderFooter(hasHeaderFooter)
            setFootnoteEndnote(hasFootnoteEndnote)
            setAutoNumber(hasAutoNumber)
            setPageControl(hasPageControl)
            setBookmark(hasBookmark)
            setAdditionalTextOverlappingLetter(hasAdditionalTextOverlappingLetter)
            setHyphen(hasHyphen)
            setBundleBlank(hasBundleBlank)
            setFixWidthBlank(hasFixWidthBlank)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlMask] 생성된 객체 반환
         */
        fun build(value: Long = 0) : HWPControlMask = HWPControlMask().apply {
            this.value = value
        }
    }
}