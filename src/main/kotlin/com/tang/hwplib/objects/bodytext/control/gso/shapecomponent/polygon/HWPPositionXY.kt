package com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.polygon

/**
 * X와 Y좌표를 나타내는 객체
 * 8 bytes
 *
 * @author accforaus
 *
 * @property [x] [Long], x 좌표
 * @property [y] [Long], y 좌표
 */
class HWPPositionXY {
    var x: Long = 0
    var y: Long = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPPositionXY] 복사된 객체 반환
     */
    fun copy() : HWPPositionXY = HWPPositionXY().also {
        it.x = this.x
        it.y = this.y
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPPositionXY] 생성된 객체 반환
         */
        fun build(x: Long = 0, y: Long = 0) : HWPPositionXY = HWPPositionXY().apply {
            this.x = x
            this.y = y
        }
    }
}