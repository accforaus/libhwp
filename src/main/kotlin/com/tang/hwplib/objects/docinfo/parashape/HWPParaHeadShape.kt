package com.tang.hwplib.objects.docinfo.parashape

/**
 * 문단 머리 종류
 * bit 23-24
 *
 * @author accforaus
 *
 * @property [value] [Byte], 문단 머리 종류값을 가진 데이터
 */
enum class HWPParaHeadShape(v: Byte) {
    /**
     * 없음
     */
    None(0.toByte()),
    /**
     * 개요
     */
    Outline(1.toByte()),
    /**
     * 번호
     */
    Numbering(2.toByte()),
    /**
     * 글머리표 (bullet)
     */
    Bullet(3.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPParaHeadShape] enum 값
         */
        fun valueOf(v: Byte) : HWPParaHeadShape {
            for (phs in values())
                if (phs.value == v)
                    return phs
            return None
        }
    }
}