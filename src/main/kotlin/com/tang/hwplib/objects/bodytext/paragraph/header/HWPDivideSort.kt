package com.tang.hwplib.objects.bodytext.paragraph.header

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 단 나누기 종류를 나타내는 객체
 * UINT8 - unsigned 1 byte
 *
 * @author accforaus
 *
 * @property [value] [Byte], 단 나누기 종류를 가진 데이터
 */
class HWPDivideSort {
    var value: Short = 0

    /**
     * 구역 나누기 여부를 반환하는 함수
     * bit 0 (0x01)
     *
     * @return [Boolean] 구역 나누기 여부 반환
     */
    fun isDivideSection(): Boolean = get(value, 0)

    /**
     * 구역 나누기 여부를 설정하는 함수
     * bit 0 (0x01)
     *
     * @param [divideSection] [Boolean], 구역 나누기 여부값을 가진 데이터
     */
    fun setDivideSection(divideSection: Boolean) {
        value = set(value, 0, divideSection)
    }

    /**
     * 다단 나누기를 반환하는 함수
     * bit 1 (0x02)
     *
     * @return [Boolean] 다단 나누기 반환
     */
    fun isDivideMultiColumn() : Boolean = get(value, 1)

    /**
     * 다단 나누기를 설정하는 함수
     * bit 1 (0x02)
     *
     * @param [divideMultiColumn] [Boolean], 다단 나누기값을 가진 데이터
     */
    fun setDivideMultiColumn(divideMultiColumn: Boolean) {
        value = set(value, 1, divideMultiColumn)
    }

    /**
     * 쪽 나누기를 반환하는 함수
     * bit 2 (0x04)
     *
     * @return [Boolean] 쪽 나누기 반환
     */
    fun isDividePage() : Boolean = get(value, 2)

    /**
     * 쪽 나누기를 설정하는 함수
     * bit 2 (0x04)
     *
     * @param [dividePage] [Boolean], 쪽 나누기값을 가진 데이터
     */
    fun setDividePage(dividePage: Boolean) {
        value = set(value, 2, dividePage)
    }

    /**
     * 단 나누기를 반환하는 함수
     * bit 3 (0x08)
     *
     * @return [Boolean] 단 나누기 반환
     */
    fun isDivideColumn() : Boolean = get(value, 3)

    /**
     * 단 나누기를 설정하는 함수
     * bit 3 (0x08)
     *
     * @param [divideColumn] [Boolean], 단 나누기값을 가진 데이터
     */
    fun setDivideColumn(divideColumn: Boolean) {
        value = set(value, 3, divideColumn)
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPDivideSort] 생성된 객체 반환
         */
        fun build(isDivideSection: Boolean = false,
                  isDivideMultiColumn: Boolean = false,
                  isDividePage: Boolean = false,
                  isDivideColumn: Boolean = false)
                : HWPDivideSort = HWPDivideSort().apply {
            setDivideSection(isDivideSection)
            setDivideMultiColumn(isDivideMultiColumn)
            setDividePage(isDividePage)
            setDivideColumn(isDivideColumn)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPDivideSort] 생성된 객체 반환
         */
        fun build(value: Short = 0): HWPDivideSort = HWPDivideSort().apply {
            this.value = value
        }
    }
}