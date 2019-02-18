package com.tang.hwplib.builder.docinfo.borderfill.fillinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPFillInfo
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPFillType
import com.tang.hwplib.util.exceptions.HWPBuildException

class HWPFillInfoBuilder(var docInfo: HWPDocInfo) : HWPBuilder<HWPFillInfo> {
    class HWPFillInfoBuildException(msg: String) : HWPBuildException(msg)

    private val fillInfo : HWPFillInfo = HWPFillInfo.build()

    fun setFillType(fillTypeBuilder: HWPFillTypeBuilder) : HWPFillInfoBuilder = this.apply {
        fillInfo.type = fillTypeBuilder.build()
    }

    fun setPatternFill(patternFillBuilder: HWPPatternFillBuilder) : HWPFillInfoBuilder = this.apply {
        fillInfo.patternFill = patternFillBuilder.build()
    }

    fun setGradientFill(gradientFillBuilder: HWPGradientFillBuilder) : HWPFillInfoBuilder = this.apply {
        fillInfo.gradientFill = gradientFillBuilder.build()
    }

    fun setImageFill(imageFillBuilder: HWPImageFillBuilder) : HWPFillInfoBuilder = this.apply {
        fillInfo.imageFill = imageFillBuilder.build()
    }

    private fun verify() : Boolean {
        fillInfo.run {
            if (type.hasImageFill())
                if (imageFill == null)
                    throw HWPFillInfoBuildException("FillInfo has image fill, ImageFill must be not null")
            if (type.hasGradientFill())
                if (gradientFill == null)
                    throw HWPFillInfoBuildException("FillInfo has gradient fill, GradientFill must be not null")
            if (type.hasPatternFill())
                if (patternFill == null)
                    throw HWPFillInfoBuildException("FillInfo has pattern fill, PatternFill must be not null")
        }
        return true
    }

    override fun build(): HWPFillInfo = fillInfo.apply {
        verify()
    }
}

class HWPFillTypeBuilder : HWPBuilder<HWPFillType> {
    private val fillType: HWPFillType = HWPFillType.build()

    fun setValue(value: Long) : HWPFillTypeBuilder = this.apply {
        fillType.value = value
    }

    fun setPatternFill(patternFill: Boolean) : HWPFillTypeBuilder = this.apply {
        fillType.setPatternFill(patternFill)
    }

    fun setImageFill(imageFill: Boolean) : HWPFillTypeBuilder = this.apply {
        fillType.setImageFill(imageFill)
    }

    fun setGradientFill(gradientFill: Boolean) : HWPFillTypeBuilder = this.apply {
        fillType.setGradientFill(gradientFill)
    }

    override fun build(): HWPFillType = fillType
}