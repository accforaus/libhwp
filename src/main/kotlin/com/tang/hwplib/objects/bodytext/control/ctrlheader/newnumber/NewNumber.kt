package com.tang.hwplib.objects.bodytext.control.ctrlheader.newnumber

import com.tang.hwplib.objects.bodytext.control.ctrlheader.autonumber.HWPNumberSort
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 새 번호 지정 문단 리스트 헤더 속성을 나타내는 객체
 * 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 새 번호 지정 문단 리스트 헤더 속성 값
 */
class HWPNewNumberHeaderProperty {
    var value: Long = 0
        set(newValue) {
            field = newValue
            _numberSort = getNumberSort()
        }
    private var _numberSort: HWPNumberSort = HWPNumberSort.Page
    /**
     * 번호 종류를 반환하는 함수
     * bit 0-3
     *
     * @return [HWPNumberSort] 번호 종류 반환
     */
    fun getNumberSort() : HWPNumberSort = HWPNumberSort.valueOf(get(value, 0, 3).toByte())

    /**
     * 번호 종류를 설정하는 함수
     * bit 0-3
     *
     * @param [numberSort] [HWPNumberSort] 번호 종류값
     */
    fun setNumberSort(numberSort: HWPNumberSort) {
        value = set(value, 0, 3, numberSort.value.toInt())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPNewNumberHeaderProperty] 생성된 객체 반환
         */
        fun build(numberSort: HWPNumberSort = HWPNumberSort.Page) : HWPNewNumberHeaderProperty = HWPNewNumberHeaderProperty().apply {
            setNumberSort(numberSort)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPNewNumberHeaderProperty] 생성된 객체 반환
         */
        fun build(value: Long = 0) : HWPNewNumberHeaderProperty = HWPNewNumberHeaderProperty().apply {
            this.value = value
        }
    }
}