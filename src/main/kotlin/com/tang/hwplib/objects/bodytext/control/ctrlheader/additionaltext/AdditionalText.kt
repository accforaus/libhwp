package com.tang.hwplib.objects.bodytext.control.ctrlheader.additionaltext

/**
 * 덧말의 위치
 */
enum class HWPAdditionalTextPosition(v: Byte) {
    /**
     * 위
     */
    Top(0.toByte()),
    /**
     * 아래
     */
    Bottom(1.toByte()),
    /**
     * 가운데
     */
    Center(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPAdditionalTextPosition] enum 값
         */
        fun valueOf(v: Byte) : HWPAdditionalTextPosition {
            for (atp in values())
                if (atp.value == v)
                    return atp
            return Top
        }
    }
}