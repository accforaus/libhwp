package com.tang.hwplib.objects.bodytext.control.ctrlheader.pagehide

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 감추기 속성을 나타내는 객체
 * 2 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 감추기 속성 값
 */
class HWPPageHideHeaderProperty {
    var value: Long = 0
        set(newValue) {
            field = newValue
            _isHideHeader = isHideHeader()
            _isHideFooter = isHideFooter()
            _isHideBatangPage = isHideBatangPage()
            _isHideBorder = isHideBorder()
            _isHidePageNumber = isHidePageNumber()
        }
    private var _isHideHeader: Boolean = false
    private var _isHideFooter: Boolean = false
    private var _isHideBatangPage: Boolean = false
    private var _isHideBorder: Boolean = false
    private var _isHidePageNumber: Boolean = false

    /**
     * 머리말 감춤 여부를 반환하는 함수
     * bit 0
     *
     * @return [Boolean] 머리말 감춤 여부 반환
     */
    fun isHideHeader() : Boolean = get(value, 0)

    /**
     * 머리말 감춤 여부를 설정하는 함수
     * bit 0
     *
     * @param [hideHeader] [Boolean] 머리말 감춤 여부 값
     */
    fun setHideHeader(hideHeader: Boolean) {
        value = set(value, 0, hideHeader)
    }

    /**
     * 꼬리말 감춤 여부를 반환하는 함수
     * bit 1
     *
     * @return [Boolean] 꼬리말 감춤 여부 반환
     */
    fun isHideFooter() : Boolean = get(value, 1)

    /**
     * 꼬리말 감춤 여부를 설정하는 함수
     * bit 1
     *
     * @param [hideFooter] [Boolean] 꼬리말 감춤 여부 값
     */
    fun setHideFooter(hideFooter: Boolean) {
        value = set(value, 1, hideFooter)
    }

    /**
     * 바탕쪽 감춤 여부를 반환하는 함수
     * bit 2
     *
     * @return [Boolean] 바탕쪽 감춤 여부 반환
     */
    fun isHideBatangPage() : Boolean = get(value, 2)

    /**
     * 바탕쪽 감춤 여부를 설정하는 함수
     * bit2
     *
     * @param [hideBatangPage] [Boolean] 바탕쪽 감춤 여부 값
     */
    fun setHideBatangPage(hideBatangPage: Boolean) {
        value = set(value, 2, hideBatangPage)
    }

    /**
     * 테두리 감춤 여부를 반환하는 함수
     * bit 3
     *
     * @return [Boolean] 테두리 감춤 여부 반환
     */
    fun isHideBorder() : Boolean = get(value, 3)

    /**
     * 테두리 감춤 여부를 설정하는 함수
     * bit 3
     *
     * @param [hideBorder] [Boolean] 테두리 감춤 여부 값
     */
    fun setHideBorder(hideBorder: Boolean) {
        value = set(value, 3, hideBorder)
    }

    /**
     * 쪽 번호 위치 감춤 여부를 반환하는 함수
     * bit 4
     *
     * @return [Boolean] 쪽 번호 위치 감춤 여부 반환
     */
    fun isHidePageNumber() : Boolean = get(value, 4)

    /**
     * 쪽 번호 위치 감춤 여부를 설정하는 함수
     * bit 4
     *
     * @param [hidePageNumder] [Boolean] 쪽 번호 위치 감춤 여부 값
     */
    fun setHidePageNumber(hidePageNumder: Boolean) {
        value = set(value, 4, hidePageNumder)
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPPageHideHeaderProperty] 생성된 객체 반환
         */
        fun build(hideHeader: Boolean = false,
                  hideFooter: Boolean = false,
                  hideBatangPage: Boolean = false,
                  hideBorder: Boolean = false,
                  hidePageNumder: Boolean = false)
                : HWPPageHideHeaderProperty = HWPPageHideHeaderProperty().apply {
            setHideHeader(hideHeader)
            setHideFooter(hideFooter)
            setHideBatangPage(hideBatangPage)
            setHideBorder(hideBorder)
            setHidePageNumber(hidePageNumder)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPPageHideHeaderProperty] 생성된 객체 반환
         */
        fun build(value: Long = 0) : HWPPageHideHeaderProperty = HWPPageHideHeaderProperty().apply {
            this.value = value
        }
    }
}