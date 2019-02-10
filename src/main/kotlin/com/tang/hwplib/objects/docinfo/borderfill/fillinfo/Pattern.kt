package com.tang.hwplib.objects.docinfo.borderfill.fillinfo

import com.tang.hwplib.objects.etc.Color4Byte

/**
 * 채우기 무늬 종류
 * INT32 - signed 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Byte], 채우기 무늬 종류 값을 가진 데이터
 */
enum class HWPPatternType(v: Byte) {
    /**
     * none
     */
    None((-1).toByte()),
    /**
     * - - - -
     */
    HorizontalLine(0.toByte()),
    /**
     * |||||
     */
    VerticalLine(1.toByte()),
    /**
     * \\\\\
     */
    BackDiagonalLine(2.toByte()),
    /**
     * /////
     */
    FrontDiagonalLine(3.toByte()),
    /**
     * +++++
     */
    CrossLine(4.toByte()),
    /**
     * xxxxx
     */
    CrossDiagonalLine(5.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPPatternType] enum 값
         */
        fun valueOf(v: Byte) : HWPPatternType {
            for (pt in values())
                if (pt.value == v)
                    return pt
            return None
        }
    }
}

/**
 * 단색을 나타내는 객체
 * 단색 채우기 (type & 0x0000001 != 0)
 *
 * @author accforaus
 *
 * @property [backColor] [Color4Byte], 배경색 (COLORREF - unsigned 4 bytes)
 * @property [patternColor] [Color4Byte], 무늬색 (COLORREF - unsigned 4 bytes)
 * @property [patternType] [HWPPatternType], 무늬 종류 (INT32 - signed 4 bytes)
 */
class HWPPatternFill {
    var backColor: Color4Byte = Color4Byte()
    var patternColor: Color4Byte = Color4Byte()
    var patternType: HWPPatternType = HWPPatternType.None

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPPatternFill] 복사된 객체 반환
     */
    fun copy() : HWPPatternFill = HWPPatternFill().also {
        it.backColor.value = this.backColor.value
        it.patternColor.value = this.patternColor.value
        it.patternType = HWPPatternType.valueOf(this.patternType.value)
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPPatternFill] 생성된 객체 반환
         */
        fun build(backColor: Color4Byte = Color4Byte.build(),
                  patternColor: Color4Byte = Color4Byte.build(),
                  patternType: HWPPatternType = HWPPatternType.None): HWPPatternFill = HWPPatternFill().apply {
            this.backColor = backColor
            this.patternColor = patternColor
            this.patternType = patternType
        }
    }
}