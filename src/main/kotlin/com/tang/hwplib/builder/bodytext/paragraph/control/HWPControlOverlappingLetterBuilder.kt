package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.bodytext.paragraph.charshape.HWPParaCharShapeBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControl
import com.tang.hwplib.objects.bodytext.control.HWPControlOverlappingLetter
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderOverlappingLetter

class HWPControlOverlappingLetterBuilder : HWPControlBuilder<HWPControlOverlappingLetter> {
    private val control : HWPControlOverlappingLetter = HWPControlOverlappingLetter.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderOverlappingLetterBuilder) : HWPControlOverlappingLetterBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlOverlappingLetterBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlOverlappingLetter = control
}

class HWPCtrlHeaderOverlappingLetterBuilder : HWPBuilder<HWPCtrlHeaderOverlappingLetter> {
    private val header : HWPCtrlHeaderOverlappingLetter = HWPCtrlHeaderOverlappingLetter.build()

    fun setOverlappingLetterList(overlappingLetterBuilder: HWPOverlappingLetterListBuilder) : HWPCtrlHeaderOverlappingLetterBuilder = this.apply {
        header.overlappingLetterList = overlappingLetterBuilder.build()
    }

    fun setBorderType(borderType: Short) : HWPCtrlHeaderOverlappingLetterBuilder = this.apply {
        header.borderType = borderType
    }

    fun setInternalFontSize(internalFontSize: Byte) : HWPCtrlHeaderOverlappingLetterBuilder = this.apply {
        header.internalFontSize = internalFontSize
    }

    fun setExpendInsideLetter(expendInsideLetter: Short) : HWPCtrlHeaderOverlappingLetterBuilder = this.apply {
        header.expendInsideLetter = expendInsideLetter
    }

    fun setCharShapeIDList(charShapeIDListBuilder: HWPCharShapeIDListBuilder) : HWPCtrlHeaderOverlappingLetterBuilder = this.apply {
        header.charShapeIdList = charShapeIDListBuilder.build()
    }

    override fun build(): HWPCtrlHeaderOverlappingLetter = header
}

class HWPOverlappingLetterListBuilder : HWPBuilder<ArrayList<String>> {
    private val letterList : ArrayList<String> = ArrayList()

    fun addOverlappingLetter(letter: String) : HWPOverlappingLetterListBuilder = this.apply {
        letterList.add(letter)
    }

    override fun build(): ArrayList<String> = letterList
}

class HWPCharShapeIDListBuilder : HWPBuilder<ArrayList<Long>> {
    private val charShapeList: ArrayList<Long> = ArrayList()

    fun addCharShapeID(charShapeID: Long) : HWPCharShapeIDListBuilder = this.apply {
        charShapeList.add(charShapeID)
    }

    override fun build(): ArrayList<Long> = charShapeList
}