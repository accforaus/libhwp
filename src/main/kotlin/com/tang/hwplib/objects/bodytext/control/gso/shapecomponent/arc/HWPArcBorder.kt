package com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.arc

/**
 * 호의 종류
 *
 * @author accforaus
 *
 * @property [value] [Byte], 호의 종류 값
*/
enum class HWPArcBorder(v: Byte) {
    /**
     * arc
     */
    Arc(0.toByte()),
    /**
     * circular sector
     */
    CircularSector(1.toByte()),
    /**
     * bow
     */
    Bow(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPArcBorder] enum 값
         */
        fun valueOf(v: Byte) : HWPArcBorder {
            for (ab in values())
                if (ab.value == v)
                    return ab
            return Arc
        }
    }
}