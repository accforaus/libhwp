package com.tang.hwplib.objects.docinfo.charshape

/**
 * 강조점 종류
 * bit 21-24
 */
enum class HWPEmphasisSort(v: Byte) {
    /**
     * 없음
     */
    None(0.toByte()),
    /**
     * 검정 동그라미 강조점
     */
    Circle(1.toByte()),
    /**
     * 속 빈 동그라미 강조점
     */
    EmptyCircle(2.toByte()),
    /**
     * ˇ
     */
    Type3(3.toByte()),
    /**
     * ~
     */
    Type4(4.toByte()),
    /**
     * .
     */
    Type5(5.toByte()),
    /**
     * :
     */
    Type6(6.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPEmphasisSort] enum 값
         */
        fun valueOf(v: Byte) : HWPEmphasisSort {
            for (es in values())
                if (es.value == v)
                    return es
            return None
        }
    }
}

/**
 * 외곽선 종류
 * bit 8-10
 *
 * @author accforaus
 *
 * @property [value] [Byte], 외곽선 종류 값을 가진 데이터
 */
enum class HWPOuterLineSort(v: Byte) {
    /**
     * 없음
     */
    None(0.toByte()),
    /**
     * 실선
     */
    Solid(1.toByte()),
    /**
     * 점선
     */
    Dot(2.toByte()),
    /**
     * 굵은 실선(두꺼운 선)
     */
    BoldSolid(3.toByte()),
    /**
     * 파선(긴 점선)
     */
    Dash(4.toByte()),
    /**
     * 일점 쇄선
     */
    DashDot(5.toByte()),
    /**
     * 이점 쇄선
     */
    DashDotDot(6.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPOuterLineSort] enum 값
         */
        fun valueOf(v: Byte) : HWPOuterLineSort {
            for (ols in values())
                if (ols.value == v)
                    return ols
            return None
        }
    }
}

/**
 * 그림자 종류
 * bit 11-12
 *
 * @author accforaus
 *
 * @property [value] [Byte], 그림자 종류 값을 가진 데이터
 */
enum class HWPShadowSort(v: Byte) {
    /**
     * 없음
     */
    None(0.toByte()),
    /**
     * 비연속
     */
    Discontinuous(1.toByte()),
    /**
     * 연속
     */
    Continuous(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPShadowSort] enum 값
         */
        fun valueOf(v: Byte) : HWPShadowSort {
            for (ss in values())
                if (ss.value == v)
                    return ss
            return None
        }
    }
}

/**
 * 밑줄 종류
 * bit 2-3
 *
 * @author accforaus
 *
 * @property [value] [Byte], 밑줄 종류 값을 가진 데이터
 */
enum class HWPUnderLineSort(v: Byte) {
    /**
     * 없음
     */
    None(0.toByte()),
    /**
     * 글자 아래
     */
    Bottom(1.toByte()),
    /**
     * 글자 위
     */
    Top(3.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPUnderLineSort] enum 값
         */
        fun valueOf(v: Byte) : HWPUnderLineSort {
            for (uls in values())
                if (uls.value == v)
                    return uls
            return None
        }
    }
}