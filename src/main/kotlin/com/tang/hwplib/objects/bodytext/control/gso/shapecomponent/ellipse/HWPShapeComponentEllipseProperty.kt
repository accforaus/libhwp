package com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.ellipse

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 타원/호 개체 속성의 속성을 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 타원/호 개체 속성의 속성값
 */
class HWPShapeComponentEllipseProperty {
    var value: Long = 0

    /**
     * 호(ARC)로 바뀌었을 때, interval을 다시 계산해야 할 필요가 있는지 여부를 반환하는 함수
     * (interval - 원 위에 존재하는 두 점 사이의 거리)
     * bit 0
     *
     * @return [Boolean] 호(ARC)로 바뀌었을 때, interval을 다시 계산해야 할 필요가 있는지 여부 반환
     */
    fun isRecalculateIntervalWhenChangingArc() : Boolean = get(value, 0)

    /**
     * 호(ARC)로 바뀌었을 때, interval을 다시 계산해야 할 필요가 있는지 여부를 설정하는 함수
     * (interval - 원 위에 존재하는 두 점 사이의 거리)
     * bit 0
     *
     * @param [recalcurateIntervalWhenChangingArc] [Boolean] 호(ARC)로 바뀌었을 때, interval을 다시 계산해야 할 필요가 있는지 여부값
     */
    fun setRecalculateIntervalWhenChanginArc(recalcurateIntervalWhenChangingArc: Boolean) {
        value = set(value, 0, recalcurateIntervalWhenChangingArc)
    }

    /**
     * 호(ARC)로 바뀌었는지 여부를 반환하는 함수
     * bit 1
     *
     * @return [Boolean] 호(ARC)로 바뀌었는지 여부 반환
     */
    fun isChangeArc() : Boolean = get(value, 1)

    /**
     * 호(ARC)로 바뀌었는지 여부를 설정하는 함수
     * bit 1
     *
     * @param [changeArc] [Boolean] 호(ARC)로 바뀌었는지 여부값
     */
    fun setChangeArc(changeArc: Boolean) {
        value = set(value, 1, changeArc)
    }

    /**
     * 호(ARC)의 종류를 반환하는 함수
     * bit 2-9
     *
     * @return [Short] 호(ARC)의 종류 반환
     */
    fun getArcSort() : Short = get(value, 2 ,9).toShort()

    /**
     * 호(ARC)의 종류를 설정하는 함수
     * bit 2-9
     *
     * @param [arcSort] [Short] 호(ARC)의 종류값
     */
    fun setArcSort(arcSort: Short) {
        value = set(value, 2, 9, arcSort.toInt())
    }
}