package com.tang.hwplib.builder.docinfo.borderfill.fillinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.etc.Color4ByteBuilder
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPatternFill
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPatternType

class HWPPatternFillBuilder : HWPBuilder<HWPPatternFill> {
    private val patternFill: HWPPatternFill = HWPPatternFill.build()

    fun setBackColor(color4ByteBuilder: Color4ByteBuilder) : HWPPatternFillBuilder = this.apply {
        patternFill.backColor = color4ByteBuilder.build()
    }

    fun setPatternColor(color4ByteBuilder: Color4ByteBuilder) : HWPPatternFillBuilder = this.apply {
        patternFill.patternColor = color4ByteBuilder.build()
    }

    fun setPatternType(patternType: HWPPatternType) : HWPPatternFillBuilder = this.apply {
        patternFill.patternType = patternType
    }

    override fun build(): HWPPatternFill = patternFill
}