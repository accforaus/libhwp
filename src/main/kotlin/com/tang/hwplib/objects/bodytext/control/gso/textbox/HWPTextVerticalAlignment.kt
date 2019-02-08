package com.tang.hwplib.objects.bodytext.control.gso.textbox

/**
 * 세로 정렬
 * bit 5-6
 *
 * @author accforaus
 *
 * @property [value] [Byte], 세로 정렬 값
 */
enum class HWPTextVerticalAlignment(v: Byte) {
    /**
     * top
     */
    Top(0.toByte()),
    /**
     * center
     */
    Center(1.toByte()),
    /**
     * bottom
     */
    Bottom(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPTextVerticalAlignment] enum 값
         */
        fun valueOf(v: Byte) : HWPTextVerticalAlignment {
            for (tva in values())
                if (tva.value == v)
                    return tva
            return Top
        }
    }
}