package com.tang.hwplib.objects.docinfo.borderfill

import com.tang.hwplib.objects.etc.Color4Byte
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 테두리선 종류
 * UINT8 - unsigned 1 byte
 *
 * @author accforaus
 *
 * @property [value] [Byte], 테두리선 종류를 가진 데이터
 */
enum class HWPBorderType(v: Byte) {
    /**
     * 실선
     */
    Solid(0.toByte()),
    /**
     * 긴 점선
     */
    Dash(1.toByte()),
    /**
     * 점선
     */
    Dot(2.toByte()),
    /**
     * -.-.-.-.-
     */
    DashDot(3.toByte()),
    /**
     * -..-..-..-..
     */
    DashDotDot(4.toByte()),
    /**
     * Dash보다 긴 선분의 반복
     */
    LongDash(5.toByte()),
    /**
     * Dot보다 큰 동그라미의 반복
     */
    CircleDot(6.toByte()),
    /**
     * 2중선
     */
    Double(7.toByte()),
    /**
     * 가는선 + 굵은선 2중선
     */
    ThickThick(8.toByte()),
    /**
     * 굵은선 + 가는선 2중선
     */
    ThickThin(9.toByte()),
    /**
     * 가는선 + 굵은선 + 가는선 3중선
     */
    ThinThickThin(10.toByte()),
    /**
     * 물결
     */
    Wae(11.toByte()),
    /**
     * 물결 2중선
     */
    DoubleWave(12.toByte()),
    /**
     * 두꺼운 3D
     */
    Thick3D(13.toByte()),
    /**
     * 두꺼운 3D (광원 반대)
     */
    Thick3DReverseLighting(14.toByte()),
    /**
     * 3D 단선
     */
    Solid3D(15.toByte()),
    /**
     * 3D 단선 (광원 반대)
     */
    Solid3DReverseLighting(16.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPBorderType] enum 값
         */
        fun valueOf(v: Byte) : HWPBorderType {
            for (bt in values())
                if (bt.value == v)
                    return bt
            return Solid
        }
    }
}

/**
 * 테두리선 굵기
 * UINT8 - unsigned 1 byte
 *
 * @author accforaus
 *
 * @property [value] [Byte], 테두리선 굵기를 가진 데이터
 */
enum class HWPBorderThickness(v: Byte) {
    /**
     * 0.1 mm
     */
    MM0_1(0.toByte()),
    /**
     * 0.12 mm
     */
    MM0_12(1.toByte()),
    /**
     * 0.15 mm
     */
    MM0_15(2.toByte()),
    /**
     * 0.2 mm
     */
    MM0_2(3.toByte()),
    /**
     * 0.25 mm
     */
    MM0_25(4.toByte()),
    /**
     * 0.3 mm
     */
    MM0_3(5.toByte()),
    /**
     * 0.4 mm
     */
    MM0_4(6.toByte()),
    /**
     * 0.5 mm
     */
    MM0_5(7.toByte()),
    /**
     * 0.6 mm
     */
    MM0_6(8.toByte()),
    /**
     * 0.7 mm
     */
    MM0_7(9.toByte()),
    /**
     * 1.0 mm
     */
    MM1_0(10.toByte()),
    /**
     * 1.5 mm
     */
    MM1_5(11.toByte()),
    /**
     * 2.0 mm
     */
    MM2_0(12.toByte()),
    /**
     * 3.0 mm
     */
    MM3_0(13.toByte()),
    /**
     * 4.0 mm
     */
    MM4_0(14.toByte()),
    /**
     * 5.0 mm
     */
    MM5_0(15.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPBorderThickness] enum 값
         */
        fun valueOf(v: Byte) : HWPBorderThickness {
            for (bt in values())
                if (bt.value == v)
                    return bt
            return MM0_1
        }
    }
}

/**
 * 각 방향의 테두리 속성을 나타내는 객체 (6 bytes)
 *
 * @author accforaus
 *
 * @property [type] [HWPBorderType], 테두리선 종류 (UINT8 - unsigned 1 byte)
 * @property [thickness] [HWPBorderThickness], 테두리선 굵기 (UINT8 - unsigned 1 byte)
 * @property [color] [Color4Byte], 테두리선 색상 (COLORREF - unsigned 4 bytes)
 */
class HWPEachBorder {
    var type: HWPBorderType = HWPBorderType.Solid
    var thickness: HWPBorderThickness = HWPBorderThickness.MM0_1
    var color: Color4Byte = Color4Byte()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPEachBorder] 복사된 객체 반환
     */
    fun copy(): HWPEachBorder = HWPEachBorder().also {
        it.type = HWPBorderType.valueOf(this.type.value)
        it.thickness = HWPBorderThickness.valueOf(this.thickness.value)
        it.color.value = this.color.value
    }
}

/**
 * 테두리/배경 속성을 나타내는 객체
 *
 * @author accforaus
 *
 * @property [value] [Int], 테두리/배경속성을 가진데이터
 */
class HWPBorderFillProperty {
    var value: Int = 0

    /**
     * 3D효과의 유무를 반환하는 함수
     * bit 0
     *
     * @return [Boolean] 3D효과의 유무를 반환
     */
    fun is3DEffect() : Boolean = get(value, 0)

    /**
     * 3D효과의 유무를 설정하는 함수
     * bit 0
     *
     * @param [_3DEffect] [Boolean], 3D효과의 참/거짓 값을 가진 데이터
     */
    fun set3DEffect(_3DEffect: Boolean) {
        value = set(value, 0, _3DEffect)
    }

    /**
     * 그림자 효과 유무를 반환하는 함수
     * bit 1
     *
     * @return [Boolean] 그림자 효과 유무를 반환
     */
    fun isShadowEffect() : Boolean = get(value, 1)

    /**
     * 그림자 효과 유무를 설정하는 함수
     * bit 1
     *
     * @param [shadowEffect] [Boolean], 그림자 효과의 참/값을 가진 데이터
     */
    fun setShadowEffect(shadowEffect: Boolean) {
        value = set(value, 1, shadowEffect)
    }

    /**
     * Slash 대각선 모양을 반환하는 함수
     * 시계 방향으로 각각의 대각선 유무를 나타냄
     * bit 2-4
     *
     * @return [HWPSlashDiagonalShape] Slash 대각선 모양을 반환
     */
    fun getSlashDiagonalShape() : HWPSlashDiagonalShape = HWPSlashDiagonalShape.valueOf(get(value, 2, 4).toByte())

    /**
     * Slash 대각선 모양을 설정하는 함수
     * 시계 방향으로 각각의 대각선 유무를 나타냄
     * bit 2-4
     *
     * @param [diagonalShape] [HWPSlashDiagonalShape], Slash 대각선 모양의 값을 가진 데이터
     */
    fun setSlashDiagonalShape(diagonalShape: HWPSlashDiagonalShape) {
        value = set(value, 2, 4, diagonalShape.value.toInt())
    }

    /**
     * BackSlash 대각선 모양을 반환하는 함수
     * 반시계 방향으로 각각의 대각선 유무를 나타냄
     * bit 5-7
     *
     * @return [HWPBackSlashDiagonalShape] BackSlash 대각선 모양을 반환
     */
    fun getBackSlashDiagonalShape() : HWPBackSlashDiagonalShape = HWPBackSlashDiagonalShape.valueOf(get(value, 5, 7).toByte())

    /**
     * BackSlash 대각선 모양을 설정하는 함수
     * 반시계 방향으로 각각의 대각선 유무를 나타냄
     * bit 5-7
     *
     * @param [diagonalShape] [HWPBackSlashDiagonalShape], BackSlash 대각선 모양의 값을 가진 데이터
     */
    fun setBackSlashDiagonalShape(diagonalShape: HWPBackSlashDiagonalShape) {
        value = set(value, 5, 7, diagonalShape.value.toInt())
    }

    /**
     * Slash 대각선 꺽은선 유무를 반환하는 함수
     * bit 8-9
     *
     * @return [Boolean] Slash 대각선 꺽은선 유무를 반환
     */
    fun isBrokenSlashDiagonal() : Boolean = get(value, 8) || get(value, 9)

    /**
     * Slash 대각선 꺽은선 유무를 설정하는 함수
     * bit 8-9
     *
     * @param [brokenSlashDiagonal] [Boolean], Slash 대각선 꺽은선의 참/거짓 값을 가진 데이터
     */
    fun setBrokenSlashDiagonal(brokenSlashDiagonal: Boolean) {
        value = set(value, 8, brokenSlashDiagonal)
        value = set(value, 9,brokenSlashDiagonal)
    }

    /**
     * BackSlash 대각선 꺽은선 유무를 반환하는 함수
     * bit 10
     *
     * @return [Boolean] BackSlash 대각선 꺽은선 유무를 반환
     */
    fun isBrokenBackSlashDiagonal(): Boolean = get(value, 10)

    /**
     * BackSlash 대각선 꺽은선 유무를 설정하는 함수
     * bit 10
     *
     * @param [brokenBackSlashDiagonal] [Boolean], BackSlash 대각선 꺽은선의 참/거짓 값을 가진 데이터
     */
    fun setBrokenBackSlashDiagonal(brokenBackSlashDiagonal: Boolean) {
        value = set(value, 10, brokenBackSlashDiagonal)
    }


    /**
     * Slash 대각선 모양 180도 회전 여부를 반환하는 함수
     * bit 11
     *
     * @return [Boolean] Slash 대각선 모양 180도 회전 여부를 반환
     */
    fun isRotateSlashDiagonal180() : Boolean = get(value, 11)

    /**
     * Slash 대각선 모양 180도 회전 여부를 설정하는 함수
     * bit 11
     *
     * @param [rotateSlashDiagonal] [Boolean] Slash 대각선 모양 180도 회전의 참/거짓 값을 가진 데이터
     */
    fun setRotateSlashDiagonal180(rotateSlashDiagonal: Boolean) {
        value = set(value, 11, rotateSlashDiagonal)
    }

    /**
     * BackSlash 대각선 모양 180도 회전 여부를 반환하는 함수
     * bit 12
     *
     * @return [Boolean] BackSlash 대각선 모양 180도 회전 여부 반환
     */
    fun isRotateBackSlashDiagonal180() : Boolean = get(value, 12)

    /**
     * BackSlash 대각선 모양 180도 회전 여부를 설정하는 함수
     * bit 12
     *
     * @param [rotateBackSlashDiagonal] [Boolean], BackSlash 대각선 모양 180도 회전의 참/거짓 값을 가진 데이터
     */
    fun setRotateBackSlashDiagonal180(rotateBackSlashDiagonal: Boolean) {
        value = set(value, 12, rotateBackSlashDiagonal)
    }

    /**
     * 중심선 유무를 반환하는 함수
     * bit 13
     *
     * @return [Boolean] 중심선 유무 반환
     */
    fun hasCenterLine() : Boolean = get(value, 13)

    /**
     * 중심선 유무를 설정하는 함수
     * bit 13
     *
     * @param [hasCenterLine] [Boolean], 중심선 유무의 참/거짓 값을 가진 데이터
     */
    fun setHasCenterLine(hasCenterLine: Boolean) {
        value = set(value, 13, hasCenterLine)
    }
}