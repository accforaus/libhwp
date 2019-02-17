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
        set(newValue) {
            field = newValue
            _isRecalculateIntervalWhenChangingArc = isRecalculateIntervalWhenChangingArc()
            _isChangeArc = isChangeArc()
            _arcSort = getArcSort()
        }
    private var _isRecalculateIntervalWhenChangingArc: Boolean = false
    private var _isChangeArc: Boolean = false
    private var _arcSort: Short = 0
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
     * @param [recalculateIntervalWhenChangingArc] [Boolean] 호(ARC)로 바뀌었을 때, interval을 다시 계산해야 할 필요가 있는지 여부값
     */
    fun setRecalculateIntervalWhenChangingArc(recalculateIntervalWhenChangingArc: Boolean) {
        value = set(value, 0, recalculateIntervalWhenChangingArc)
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

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPShapeComponentEllipseProperty] 생성된 객체 반환
         */
        fun build(recalculateIntervalWhenChangingArc: Boolean = false,
                  changeArc: Boolean = false, arcSort: Short = 0)
                : HWPShapeComponentEllipseProperty = HWPShapeComponentEllipseProperty().apply {
            setRecalculateIntervalWhenChangingArc(recalculateIntervalWhenChangingArc)
            setChangeArc(changeArc)
            setArcSort(arcSort)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPShapeComponentEllipseProperty] 생성된 객체 반환
         */
        fun build(value: Long = 0) : HWPShapeComponentEllipseProperty = HWPShapeComponentEllipseProperty().apply {
            this.value = value
        }
    }
}