package com.tang.hwplib.builder.bodytext.paragraph

import com.tang.hwplib.builder.bodytext.paragraph.charshape.HWPParaCharShapeBuilder
import com.tang.hwplib.builder.bodytext.paragraph.charshape.buildEmptyParaCharShape
import com.tang.hwplib.builder.bodytext.paragraph.control.buildEmptyColumnDefine
import com.tang.hwplib.builder.bodytext.paragraph.control.buildEmptySectionDefine
import com.tang.hwplib.builder.bodytext.paragraph.header.HWPParaHeadBuilder
import com.tang.hwplib.builder.bodytext.paragraph.header.buildEmptyParaHeader
import com.tang.hwplib.builder.bodytext.paragraph.lineseg.HWPParaLineSegBuilder
import com.tang.hwplib.builder.bodytext.paragraph.lineseg.buildEmptyParaLineSeg
import com.tang.hwplib.builder.bodytext.paragraph.rangetag.HWPParaRangeTagBuilder
import com.tang.hwplib.builder.bodytext.paragraph.text.HWPParaTextBuilder
import com.tang.hwplib.builder.bodytext.paragraph.text.buildEmptyParaText
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphList

class HWPParagraphListBuilder : HWPBuilder<HWPParagraphList> {
    private val paragraphList: HWPParagraphList = HWPParagraphList()

    fun addParagraph(paragraphBuilder: HWPParagraphBuilder) : HWPParagraphListBuilder = this.apply {
        paragraphList.paragraphList.add(paragraphBuilder.build())
    }

    override fun build(): HWPParagraphList = paragraphList
}

class HWPParagraphBuilder : HWPBuilder<HWPParagraph> {
    private val paragraph: HWPParagraph = HWPParagraph()

    fun setHeader(headerBuilder: HWPParaHeadBuilder) : HWPParagraphBuilder = this.apply {
        paragraph.header = headerBuilder.build()
    }

    fun setParaShape(paraShapeBuilder: HWPParaCharShapeBuilder) : HWPParagraphBuilder = this.apply {
        paragraph.paraCharShape = paraShapeBuilder.build()
    }

    fun setLineSeg(paraLineSegBuilder: HWPParaLineSegBuilder) : HWPParagraphBuilder = this.apply {
        paragraph.lineSeg = paraLineSegBuilder.build()
    }

    fun setRangeTag(paraRangeTagBuilder: HWPParaRangeTagBuilder) : HWPParagraphBuilder = this.apply {
        paragraph.rangeTag = paraRangeTagBuilder.build()
    }

    fun setParaText(paraTextBuilder: HWPParaTextBuilder) : HWPParagraphBuilder = this.apply {
        paragraph.text = paraTextBuilder.build()
    }

    override fun build(): HWPParagraph = paragraph
}

internal fun buildEmptyParagraph() : HWPParagraph {
    val paragraph: HWPParagraph = HWPParagraph()
    paragraph.run {
        header = buildEmptyParaHeader()
        paraCharShape = buildEmptyParaCharShape()
        lineSeg = buildEmptyParaLineSeg()
        text = buildEmptyParaText()
        controlList = arrayListOf(buildEmptySectionDefine(), buildEmptyColumnDefine())
    }
    return paragraph
}