package com.tang.hwplib.objects.bodytext.control.ctrlheader.pagenumberposition

import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPNumberShape
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 번호의 표시 위치
 *
 * @author accforaus
 *
 * @property [value] [Byte], 번호의 표시 위치값
 */
enum class HWPNumberPosition(v: Byte) {
    /**
     * 쪽 번호 없음
     */
    None(0.toByte()),
    /**
     * 왼쪽 위
     */
    LeftTop(1.toByte()),
    /**
     * 가운데 위
     */
    CenterTop(2.toByte()),
    /**
     * 오른쪽 위
     */
    RightTop(3.toByte()),
    /**
     * 왼쪽 아래
     */
    LeftBottom(4.toByte()),
    /**
     * 가운데 아래
     */
    CenterBottom(5.toByte()),
    /**
     * 오른쪽 아래
     */
    RightBottom(6.toByte()),
    /**
     * 바깥쪽 위
     */
    OutsideTop(7.toByte()),
    /**
     * 바깥쪽 아래
     */
    OutsideBottom(8.toByte()),
    /**
     * 안쪽 위
     */
    InsideTop(9.toByte()),
    /**
     * 안쪽 아래
     */
    InsideBottom(10.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPNumberPosition] enum 값
         */
        fun valueOf(v: Byte) : HWPNumberPosition {
            for (np in values())
                if (np.value == v)
                    return np
            return None
        }
    }
}

/**
 * 쪽 번호 위치 헤더 속성을 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 쪽 번호 위치 헤더 속성 값
 */
class PageNumberPositionHeaderProperty {
    var value: Long = 0

    /**
     * 번호 모양을 반환하는 함수
     * bit 0-7
     *
     * @return [HWPNumberShape] 번호 모양 반환
     */
    fun getNumberShape(): HWPNumberShape = HWPNumberShape.valueOf(get(value, 0, 7).toShort())

    /**
     * 번호 모양을 설정하는 함수
     * bit 0-7
     *
     * @param [numberShape] [HWPNumberShape] 번호 모양값
     */
    fun setNumberShape(numberShape: HWPNumberShape) {
        value = set(value, 0, 7, numberShape.value.toInt())
    }

    /**
     * 번호의 표시 위치를 반환하는 함수
     * bit 8-11
     *
     * @return [HWPNumberPosition] 번호의 표시 위치 반환
     */
    fun getNumberPosition(): HWPNumberPosition = HWPNumberPosition.valueOf(get(value, 8, 11).toByte())

    /**
     * 번호의 표시 위치를 설정하는 함수
     * bit 8-11
     *
     * @param [numberPosition] [HWPNumberPosition] 번호의 표시 위치값
     */
    fun setNumberPosition(numberPosition: HWPNumberPosition) {
        value = set(value, 8, 11, numberPosition.value.toInt())
    }

}