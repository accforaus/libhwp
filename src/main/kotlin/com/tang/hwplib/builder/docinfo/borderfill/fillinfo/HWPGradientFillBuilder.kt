package com.tang.hwplib.builder.docinfo.borderfill.fillinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.etc.Color4ByteBuilder
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPGradientFill
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPGradientType
import com.tang.hwplib.objects.etc.Color4Byte

class HWPGradientFillBuilder : HWPBuilder<HWPGradientFill> {
    private val gradientFill: HWPGradientFill = HWPGradientFill.build()

    fun setGradientType(gradientType: HWPGradientType) : HWPGradientFillBuilder = this.apply {
        gradientFill.gradientType = gradientType
    }

    fun setStartAngle(startAngle: Long) : HWPGradientFillBuilder = this.apply {
        gradientFill.startAngle = startAngle
    }

    fun setCenterX(centerX: Long) : HWPGradientFillBuilder = this.apply {
        gradientFill.centerX = centerX
    }

    fun setCenterY(centerY: Long) : HWPGradientFillBuilder = this.apply {
        gradientFill.centerY = centerY
    }

    fun setBlurringDegree(blurringDegree: Long) : HWPGradientFillBuilder = this.apply {
        gradientFill.blurringDegree = blurringDegree
    }

    fun setChangePointAndColor(changePointAndColorBuilder: HWPChangePointAndColorBuilder) : HWPGradientFillBuilder = this.apply {
        val (changePointList: ArrayList<Int>, colorList: ArrayList<Color4Byte>) = changePointAndColorBuilder.build()
        gradientFill.changePointList = changePointList
        gradientFill.colorList = colorList
    }

    fun setBlurringCenter(blurringCenter: Short) : HWPGradientFillBuilder = this.apply {
        gradientFill.blurringCenter = blurringCenter
    }

    override fun build(): HWPGradientFill = gradientFill
}

class HWPChangePointAndColorBuilder : HWPBuilder<Pair<ArrayList<Int>, ArrayList<Color4Byte>>> {
    private val changePointList: ArrayList<Int> = ArrayList()
    private val colorList: ArrayList<Color4Byte> = ArrayList()

    fun addPointAndColor(point: Int, colorBuilder: Color4ByteBuilder) : HWPChangePointAndColorBuilder = this.apply {
        changePointList.add(point)
        colorList.add(colorBuilder.build())
    }

    override fun build(): Pair<ArrayList<Int>, ArrayList<Color4Byte>> = Pair(changePointList, colorList)
}