package com.tang.hwplib.objects.bodytext.control.sectiondefine

/**
 * 번호 모양
 * 0-16은 범용
 * 0x80, 0x81 은 각주/미주 전용
 *
 * @author accforaus
 *
 * @property [value] [Short], 번호 모양 값
 */
enum class HWPNumberShape(v: Short) {
    /**
     * 1, 2, 3
     */
    Type0(0.toShort()),
    /**
     * 동그라미 쳐진 1, 2, 3
     */
    Type1(1.toShort()),
    /**
     * I II, III
     */
    Type2(2.toShort()),
    /**
     * i, ii, iii
     */
    Type3(3.toShort()),
    /**
     * A, B, C
     */
    Type4(4.toShort()),
    /**
     * a, b, c
     */
    Type5(5.toShort()),
    /**
     * 동그라미 쳐진 A, B, C
     */
    Type6(6.toShort()),
    /**
     * 동그라미 쳐진 a, b, c
     */
    Type7(7.toShort()),
    /**
     * 가, 나, 다
     */
    Type8(8.toShort()),
    /**
     * 동그라미 쳐진 가, 나, 다
     */
    Type9(9.toShort()),
    /**
     * ㄱ, ㄴ, ㄷ
     */
    Type10(10.toShort()),
    /**
     * 동그라미 쳐진 ㄱ, ㄴ, ㄷ
     */
    Type11(11.toShort()),
    /**
     * 일, 이, 삼
     */
    Type12(12.toShort()),
    /**
     * 一, 二, 三
     */
    Type13(13.toShort()),
    /**
     * 동그라미 쳐진 一, 二, 三
     */
    Type14(14.toShort()),
    /**
     * 갑, 을, 병, 정, 무, 기, 경, 신, 임, 계
     */
    Type15(15.toShort()),
    /**
     * 甲, 乙, 丙, 丁, 戊, 己 庚, 辛, 壬, 癸
     */
    Type16(16.toShort()),
    /**
     * 4가지 문자가 차례로 반복
     */
    Type128(0x80.toShort()),
    /**
     * 사용자 지정 문자 반복
     */
    Type129(0x81.toShort());

    var value: Short = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPNumberShape] enum 값
         */
        fun valueOf(v: Short) : HWPNumberShape {
            for (ns in values())
                if (ns.value == v)
                    return ns
            return Type0
        }
    }
}

/**
 * numbering
 *
 * @author accforaus
 *
 * @property [value] [Byte], numbering value
 */
enum class HWPNumberingMethod(v: Byte) {
    /**
     * 앞 구역에 이어서
     */
    Continue(0.toByte()),
    /**
     * 현재 구역부터 새로 시작
     */
    Restart(1.toByte()),
    /**
     * 쪽마다 새로 시작 (각주 전용)
     */
    RestartAtEachPage(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPNumberingMethod] enum 값
         */
        fun valueOf(v: Byte) : HWPNumberingMethod {
            for (nm in values())
                if (nm.value == v)
                    return nm
            return Continue
        }
    }
}