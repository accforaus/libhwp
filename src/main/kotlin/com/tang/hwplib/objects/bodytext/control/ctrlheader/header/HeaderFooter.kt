package com.tang.hwplib.objects.bodytext.control.ctrlheader.header

/**
 * 머리말/꼬리말이 적용될 범위 (페이지 종류)
 *
 * @author accforaus
 *
 * @property [value] [Byte], 머리말/꼬리말이 적용될 범위 (페이지 종류)값
 */
enum class HWPHeaderFooterApplyPage(v: Byte) {
    /**
     * 양쪽
     */
    BothPage(0.toByte()),
    /**
     * 짝수 쪽민
     */
    EvenPage(1.toByte()),
    /**
     * 홀수 쪽만
     */
    OddPage(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPHeaderFooterApplyPage] enum 값
         */
        fun valueOf(v: Byte) : HWPHeaderFooterApplyPage {
            for (hfap in values())
                if (hfap.value == v)
                    return hfap
            return BothPage
        }
    }
}