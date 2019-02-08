package com.tang.hwplib.objects.docinfo.parashape

/**
 * 정렬 방식
 * bit 2-4
 *
 * @author accforaus
 *
 */
enum class HWPAlignment(v: Byte) {
    /**
     * 양쪽 정렬
     */
    Justify(0.toByte()),
    /**
     * 왼쪽 정렬
     */
    Left(1.toByte()),
    /**
     * 오른쪽 정렬
     */
    Right(2.toByte()),
    /**
     * 가운데 정렬
     */
    Center(3.toByte()),
    /**
     * 배분 정렬
     */
    Distribute(4.toByte()),
    /**
     * 나눔 정렬
     */
    Divide(5.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPAlignment] enum 값
         */
        fun valueOf(v: Byte) : HWPAlignment {
            for (a in values())
                if (a.value == v)
                    return a
            return Justify
        }
    }
}

/**
 * 세로 정렬
 * bit 20-21
 *
 * @author accforaus
 *
 * @property [value] [Byte], 세로 정렬 값을 가진 데이터
 */
enum class HWPVerticalAlignment(v: Byte) {
    /**
     * 글꼴 기준
     */
    ByFont(0.toByte()),
    /**
     * 위쪽
     */
    Top(1.toByte()),
    /**
     * 가운데
     */
    Center(2.toByte()),
    /**
     * 아래쪽
     */
    Bottom(3.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPVerticalAlignment] enum 값
         */
        fun valueOf(v: Byte) : HWPVerticalAlignment {
            for (va in values())
                if (va.value == v)
                    return va
            return ByFont
        }
    }
}