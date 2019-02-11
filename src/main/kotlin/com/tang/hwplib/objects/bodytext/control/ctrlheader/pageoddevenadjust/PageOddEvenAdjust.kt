package com.tang.hwplib.objects.bodytext.control.ctrlheader.pageoddevenadjust

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

enum class HWPOddEvenDivision(v: Byte) {
    /**
     * 양 족
     */
    Both(0.toByte()),
    /**
     * 짝수 쪽
     */
    Even(1.toByte()),
    /**
     * 홀수 쪽
     */
    Odd(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPOddEvenDivision] enum 값
         */
        fun valueOf(v: Byte) : HWPOddEvenDivision {
            for (oed in values())
                if (oed.value == v)
                    return oed
            return Both
        }
    }
}

/**
 * 홀/짝수 조정 문단 리스트 헤더 속성을 나타내는 객체
 * 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 홀/짝수 조정 문단 리스트 헤더 속성 값
 */
class PageOddEvenAdjustHeaderProperty {
    var value: Long = 0

    /**
     * 홀/짝수 구분을 반환하는 함수
     * bit 0-1
     *
     * @return [HWPOddEvenDivision] 홀/짝수 구분 반환
     */
    fun getOddEvenDivision(): HWPOddEvenDivision = HWPOddEvenDivision.valueOf(get(value, 0, 1).toByte())

    /**
     * 홀/짝수 구분을 설정하는 함수
     * bit 0-1
     *
     * @param [oddEvenDivision] [HWPOddEvenDivision] 홀/짝수 구분값
     */
    fun setOddEvenDivision(oddEvenDivision: HWPOddEvenDivision) {
        value = set(value, 0, 1, oddEvenDivision.value.toInt())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [PageOddEvenAdjustHeaderProperty] 생성된 객체 반환
         */
        fun build(oddEvenDivision: HWPOddEvenDivision = HWPOddEvenDivision.Both)
                : PageOddEvenAdjustHeaderProperty = PageOddEvenAdjustHeaderProperty().apply {
            setOddEvenDivision(oddEvenDivision)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [PageOddEvenAdjustHeaderProperty] 생성된 객체 반환
         */
        fun build(value: Long = 0) : PageOddEvenAdjustHeaderProperty = PageOddEvenAdjustHeaderProperty().apply {
            this.value = value
        }
    }
}