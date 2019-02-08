package com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.curve

/**
 * Segment type
 *
 * @author accforaus
 *
 * @property [value] [Byte], segment type value
 */
enum class HWPCurveSegmentType(v: Byte) {
    /**
     * line
     */
    Line(0.toByte()),
    /**
     * curve
     */
    Curve(1.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPCurveSegmentType] enum 값
         */
        fun valueOf(v: Byte) : HWPCurveSegmentType {
            for (cst in values())
                if (cst.value == v)
                    return cst
            return Line
        }
    }
}