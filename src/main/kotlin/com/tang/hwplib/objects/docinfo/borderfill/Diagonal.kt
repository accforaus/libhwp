package com.tang.hwplib.objects.docinfo.borderfill

/**
 * 대각선 종류
 * UINT8 -unsigned 1 byte
 *
 * @author accforaus
 *
 * @property [value] [Byte], 대각선 종류의 값을 가진 데이터
 */
enum class HWPDiagonalSort(v: Byte) {
    /**
     * Slash
     */
    Slash(0.toByte()),
    /**
     * BackSlash
     */
    BackSlash(1.toByte()),
    /**
     * CrookedSlash
     */
    CrookedSlash(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPDiagonalSort] enum 값
         */
        fun valueOf(v: Byte) : HWPDiagonalSort = values().find { it.value == v } ?: Slash
    }
}

/**
 * Slash 대각선 모양
 * 시계 바향으로 각각의 대각선 유무를 나타냄
 * bit 2-4
 *
 * @author accforaus
 *
 * @property [value] [Byte], Slash 대각선 모양의 값을 가진 데이터
 */
enum class HWPSlashDiagonalShape(v: Byte) {
    /**
     * none
     * bit 000
     */
    None(0.toByte()),
    /**
     * slash
     * bit 010
     */
    Slash(2.toByte()),
    /**
     * LeftTop --> Bottom Edge
     * bit 011
     */
    LeftTopToBottomEdge(3.toByte()),
    /**
     * LeftTop --> Right Edge
     * bit 110
     */
    LeftTopToRightEdge(6.toByte()),
    /**
     * LeftTop --> Bottom & Right Edge
     * bit 111
     */
    LeftToBottomRightEdge(7.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPSlashDiagonalShape] enum 값
         */
        fun valueOf(v: Byte) : HWPSlashDiagonalShape = values().find { it.value == v } ?: None
    }
}

/**
 * BackSlash 대각선 모양
 * 반시계 방향으로 각각의 대각선 유무를 나타냄
 * bit 5-7
 *
 * @author accforaus
 *
 * @property [value] [Byte], BackSlash 대각선 모양의 값을 가진 데이터
 */
enum class HWPBackSlashDiagonalShape(v: Byte) {
    /**
     * none
     * bit 000
     */
    None(0.toByte()),
    /**
     * / back slash
     * bit 010
     */
    BackSlash(2.toByte()),
    /**
     * RightTop --> Bottom Edge
     * bit 011
     */
    RightTopToBottomEdge(3.toByte()),
    /**
     * RightTop --> Left Edge
     * bit 110
     */
    RightTopToLeftEdge(6.toByte()),
    /**
     * RightTop --> Bottom & Left Edge
     * bit 111
     */
    RightTopToBottomLeftEdge(7.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPBackSlashDiagonalShape] enum 값
         */
        fun valueOf(v: Byte) : HWPBackSlashDiagonalShape = values().find { it.value == v } ?: None
    }
}