package com.tang.hwplib.builder.etc

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.etc.Color4Byte

class Color4ByteBuilder : HWPBuilder<Color4Byte> {
    private val color4Byte: Color4Byte = Color4Byte.build()

    fun setValue(value: Long) : Color4ByteBuilder = this.apply {
        color4Byte.value = value
    }

    fun setRed(red: Short) : Color4ByteBuilder = this.apply {
        color4Byte.setR(red)
    }

    fun setGreen(green: Short) : Color4ByteBuilder = this.apply {
        color4Byte.setG(green)
    }

    fun setBlue(blue: Short) : Color4ByteBuilder = this.apply {
        color4Byte.setB(blue)
    }

    fun setAlpha(alpha: Short) : Color4ByteBuilder = this.apply {
        color4Byte.setA(alpha)
    }

    override fun build(): Color4Byte = color4Byte
}