package com.tang.hwplib.objects.docinfo.borderfill.fillinfo

import com.tang.hwplib.objects.etc.Color4Byte

/**
 * 그러데이션 유형
 * INT16 - signed 2 bytes
 *
 * @author accforaus
 *
 * @property [value] [Byte], 그러데이션 유형의 값을 가진 데이터
 */
enum class HWPGradientType(v: Byte) {
    /**
     * 줄무늬형
     */
    Stripe(1.toByte()),
    /**
     * 원형
     */
    Circle(2.toByte()),
    /**
     * 원뿔형
     */
    Cone(3.toByte()),
    /**
     * 사각형
     */
    Square(4.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPGradientType] enum 값
         */
        fun valueOf(v: Byte) : HWPGradientType {
            for (gt in values())
                if (gt.value == v)
                    return gt
            return Stripe
        }
    }
}

/**
 * 그러데이션을 나타내는 객체
 * 그러데이션 채우기 (type & 0x0000004 != 0)
 *
 * @author accforaus
 *
 * @property [gradientType] [HWPGradientType], 그러데이션 유형 (INT16 - signed 2 bytes)
 * @property [startAngle] [Long], 그러데이션의 기울임(시작각) (INT16 - signed 2 bytes)
 * @property [centerX] [Long], 그러데이션의 가로 중심(중심 X 좌표) (INT16 - signed 2 bytes)
 * @property [centerY] [Long], 그러데이션의 세로 중심(중심 Y 좌표)(INT16 - signed 2 bytes)
 * @property [blurringDegree] [Long], 그러데이션의 번짐 정도(0-100) (INT16 - signed 2 bytes)
 * @property [changePointList] [ArrayList], 색상이 바뀌는 위치(num > 2 일 경우에만) (INT32 - signed 8 bytes)
 * @property [colorList] [ArrayList], 색상 (COLORREF - unsigned 8 bytes)
 * @property [blurringCenter] [Short], 추가 채우기 속성 번점 정도의 중심(0-100) (BYTE - unsigned 1 byte)
 */
class HWPGradientFill {
    var gradientType: HWPGradientType = HWPGradientType.Stripe
    var startAngle: Short = 0
    var centerX: Short = 0
    var centerY: Short = 0
    var blurringDegree: Short = 0
    var changePointList: ArrayList<Int> = ArrayList()
    var colorList: ArrayList<Color4Byte> = ArrayList()
    var blurringCenter: Short = 0

    /**
     * 색상이 바뀌는 위치를 추가하는 함수
     *
     * @param [changePoint] [Int], 색상이 바뀌는 위치를 가진 데이터 값
     */
    fun addChangePoint(changePoint: Int) {
        changePointList.add(changePoint)
    }

    /**
     * 색상을 추가하고 생성된 색을 반환하는 함수
     *
     * @return [Color4Byte] 생성된 색을 반환
     */
    fun addNewColor() : Color4Byte = Color4Byte().apply { colorList.add(this) }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPGradientFill] 복사된 객체 반환
     */
    fun copy() : HWPGradientFill = HWPGradientFill().also {
        it.gradientType = HWPGradientType.valueOf(this.gradientType.value)
        it.startAngle = this.startAngle
        it.centerX = this.centerX
        it.centerY = this.centerY
        it.blurringDegree = this.blurringDegree
        for (int in this.changePointList) it.changePointList.add(int)
        for (color4Byte in this.colorList) it.colorList.add(color4Byte)
        it.blurringCenter = this.blurringCenter
    }
}