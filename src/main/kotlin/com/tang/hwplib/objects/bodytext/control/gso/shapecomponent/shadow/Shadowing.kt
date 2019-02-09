package com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.shadow

import com.tang.hwplib.objects.etc.Color4Byte

enum class HWPShadowType(v: Byte) {
    None(0.toByte()), LeftTop(1.toByte()), RightTop(2.toByte()),
    LeftBottom(3.toByte()), RightBottom(4.toByte()), LeftBack(5.toByte()),
    RightBack(6.toByte()), LeftFront(7.toByte()), RightFront(8.toByte()),
    Small(13.toByte()), Large(14.toByte());

    var value: Byte = v

    companion object {
        fun valueOf(v: Byte) : HWPShadowType {
            for (st in values())
                if (st.value == v)
                    return st
            return None
        }
    }
}

class HWPShadowInfo {
    var type: HWPShadowType = HWPShadowType.None
    var color: Color4Byte = Color4Byte()
    var offsetX: Int = 0
    var offsetY: Int = 0
    var transparent: Short = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShadowInfo] 복사된 객체 반환
     */
    fun copy() : HWPShadowInfo = HWPShadowInfo().also {
        it.type.value = this.type.value
        it.color.value = this.color.value
        it.offsetX = this.offsetX
        it.offsetY = this.offsetY
        it.transparent = this.transparent
    }
}