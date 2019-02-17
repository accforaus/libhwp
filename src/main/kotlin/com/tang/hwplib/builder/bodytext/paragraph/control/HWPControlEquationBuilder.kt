package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.bodytext.paragraph.control.ctrlheader.HWPCtrlHeaderGsoBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPCaptionBuilder
import com.tang.hwplib.builder.etc.Color4ByteBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlEquation
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.control.equation.HWPEQEdit

class HWPControlEquationBuilder : HWPControlBuilder<HWPControlEquation> {
    private val control : HWPControlEquation = HWPControlEquation.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderGsoBuilder) : HWPControlEquationBuilder = this.apply {
        control.header = headerBuilder.build().apply {
            ctrlId = HWPControlType.Equation.ctrlId
        }
    }

    fun setEQEdit(eqEditBuilder: HWPEQEditBuilder) : HWPControlEquationBuilder = this.apply {
        control.eqEdit = eqEditBuilder.build()
    }

    fun setCaption(captionBuilder: HWPCaptionBuilder) : HWPControlEquationBuilder = this.apply {
        control.caption = captionBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlEquationBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlEquation = control
}

class HWPEQEditBuilder : HWPBuilder<HWPEQEdit> {
    private val eqEdit : HWPEQEdit = HWPEQEdit.build()

    fun setProperty(property: Long) : HWPEQEditBuilder = this.apply {
        eqEdit.property = property
    }

    fun setScript(script: String) : HWPEQEditBuilder = this.apply {
        eqEdit.script = script
    }

    fun setLetterSize(letterSize: Long) : HWPEQEditBuilder = this.apply {
        eqEdit.letterSize = letterSize
    }

    fun setLetterColor(colorBuilder: Color4ByteBuilder) : HWPEQEditBuilder = this.apply {
        eqEdit.letterColor = colorBuilder.build()
    }

    fun setBaseLine(baseLine: Short) : HWPEQEditBuilder = this.apply {
        eqEdit.baseLine = baseLine
    }

    fun setVersionInfo(versionInfo: String) : HWPEQEditBuilder = this.apply {
        eqEdit.versionInfo
    }

    fun setFontName(fontName: String) : HWPEQEditBuilder = this.apply {
        eqEdit.fontName
    }

    override fun build(): HWPEQEdit = eqEdit
}