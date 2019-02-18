package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.docinfo.numbering.HWPExtendNumberingBuilder
import com.tang.hwplib.builder.docinfo.numbering.HWPLevelNumberingBuilder
import com.tang.hwplib.objects.docinfo.HWPNumbering
import com.tang.hwplib.objects.docinfo.numbering.HWPLevelNumbering
import com.tang.hwplib.objects.docinfo.numbering.HWPParagraphHeadInfo
import com.tang.hwplib.objects.docinfo.numbering.HWPParagraphHeadInfoProperty

class HWPNumberingBuilder : HWPBuilder<HWPNumbering> {
    private val numbering: HWPNumbering = HWPNumbering.build()

    fun setLevelNumbering(level: Int, levelNumberingBuilder: HWPLevelNumberingBuilder) : HWPNumberingBuilder = this.apply {
        numbering.levelNumberingList[level] = levelNumberingBuilder.build()
    }

    fun setStartNumber(startNumber: Int) : HWPNumberingBuilder = this.apply {
        numbering.startNumber = startNumber
    }

    fun setStartNumberForLevel(level: Int, startNumber: Long) : HWPNumberingBuilder = this.apply {
        numbering.startNumberForLevel[level] = startNumber
    }

    fun setExtendNumbering(level: Int, extendNumberingBuilder: HWPExtendNumberingBuilder) : HWPNumberingBuilder = this.apply {
        numbering.extendLevelNumberingList[level] = extendNumberingBuilder.build()
    }

    fun setExtendStartNumberForLevel(level: Int, extendStartNumber: Long) : HWPNumberingBuilder = this.apply {
        numbering.extendStartNumberForLevel[level] = extendStartNumber
    }

    override fun build(): HWPNumbering = numbering
}

class HWPNumberingListBuilder : HWPBuilder<ArrayList<HWPNumbering>> {
    private val numberingList : ArrayList<HWPNumbering> = ArrayList()

    fun addNumbering(numberingBuilder: HWPNumberingBuilder) : HWPNumberingListBuilder = this.apply {
        numberingList.add(numberingBuilder.build())
    }

    override fun build(): ArrayList<HWPNumbering> = numberingList
}