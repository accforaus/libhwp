package com.tang.hwplib.builder.docinfo.borderfill

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.etc.Color4ByteBuilder
import com.tang.hwplib.objects.docinfo.borderfill.*

class HWPEachBorderBuilder : HWPBuilder<HWPEachBorder> {
    private val eachBorder: HWPEachBorder = HWPEachBorder.build()

    fun setType(type: HWPBorderType) : HWPEachBorderBuilder = this.apply {
        eachBorder.type = type
    }

    fun setThickness(thickness: HWPBorderThickness) : HWPEachBorderBuilder = this.apply {
        eachBorder.thickness = thickness
    }

    fun setColor(color4ByteBuilder: Color4ByteBuilder) : HWPEachBorderBuilder = this.apply {
        eachBorder.color = color4ByteBuilder.build()
    }

    override fun build(): HWPEachBorder = eachBorder
}

class HWPBorderFillPropertyBuilder : HWPBuilder<HWPBorderFillProperty> {
    private val property: HWPBorderFillProperty = HWPBorderFillProperty.build()

    fun set3DEffect(_3DEffect: Boolean) : HWPBorderFillPropertyBuilder = this.apply {
        property.set3DEffect(_3DEffect)
    }

    fun setShadowEffect(shadowEffect: Boolean) : HWPBorderFillPropertyBuilder = this.apply {
        property.setShadowEffect(shadowEffect)
    }

    fun setSlashDiagonalShape(diagonalShape: HWPSlashDiagonalShape) : HWPBorderFillPropertyBuilder = this.apply {
        property.setSlashDiagonalShape(diagonalShape)
    }

    fun setBackSlashDiagonalShape(backSlashDiagonalShape: HWPBackSlashDiagonalShape) : HWPBorderFillPropertyBuilder = this.apply {
        property.setBackSlashDiagonalShape(backSlashDiagonalShape)
    }

    fun setBrokenSlashDiagonal(brokenSlashDiagonal: Boolean) : HWPBorderFillPropertyBuilder = this.apply {
        property.setBrokenSlashDiagonal(brokenSlashDiagonal)
    }

    fun setBrokenBackSlashDiagonal(brokenBackSlashDiagonal: Boolean) : HWPBorderFillPropertyBuilder = this.apply {
        property.setBrokenBackSlashDiagonal(brokenBackSlashDiagonal)
    }

    fun setRotateSlashDiagonal180(rotateSlashDiagonal180: Boolean) : HWPBorderFillPropertyBuilder = this.apply {
        property.setRotateSlashDiagonal180(rotateSlashDiagonal180)
    }

    fun setRotateBackSlashDiagonal180(rotateBackSlashDiagonal180: Boolean) : HWPBorderFillPropertyBuilder = this.apply {
        property.setRotateBackSlashDiagonal180(rotateBackSlashDiagonal180)
    }

    fun setCenterLine(centerLine: Boolean) : HWPBorderFillPropertyBuilder = this.apply {
        property.setHasCenterLine(centerLine)
    }

    override fun build(): HWPBorderFillProperty = property
}