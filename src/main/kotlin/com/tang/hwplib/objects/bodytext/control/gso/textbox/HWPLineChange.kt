package com.tang.hwplib.objects.bodytext.control.gso.textbox

/**
 * 문단의 줄바꿈
 * bit 3-4
 *
 * @author accforaus
 *
 * @property [value] [Byte], 문단의 줄바꿈 값
 */
enum class HWPLineChange(v: Byte) {
    /**
     * 일반적인 줄바꿈
     */
    Normal(0.toByte()),
    /**
     * 자간을 조종하여 한 줄을 유지
     */
    KeepOneLineByAdjustWordSpace(1.toByte()),
    /**
     * 내요에 따라 폭이 늘어남
     */
    IncreaseWidthByContent(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPLineChange] enum 값
         */
        fun valueOf(v: Byte) : HWPLineChange {
            for (lc in values())
                if (lc.value == v)
                    return lc
            return Normal
        }
    }
}