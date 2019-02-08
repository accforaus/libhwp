package com.tang.hwplib.objects.docinfo.parashape

/**
 * 줄 간격 종류
 * bit 0-4
 *
 * @author accforaus
 *
 * @property [value] [Byte], 줄 간격 종류값을 가진 데이터
 */
enum class HWPLineSpaceSort(v: Byte) {
    /**
     * 글자에 따라
     */
    RatioForLetter(0.toByte()),
    /**
     * 고정값
     */
    FixedValue(1.toByte()),
    /**
     * 여백만 지정
     */
    OnlyMargin(2.toByte()),
    /**
     * 최소
     */
    Minimum(3.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPLineSpaceSort] enum 값
         */
        fun valueOf(v: Byte) : HWPLineSpaceSort {
            for (lss in values())
                if (lss.value == v)
                    return lss
            return RatioForLetter
        }
    }
}