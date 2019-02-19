package com.tang.hwplib.builder.template.emptydocument.bodytext.paragraph

import com.tang.hwplib.builder.bodytext.paragraph.HWPParagraphBuilder
import com.tang.hwplib.builder.bodytext.paragraph.charshape.HWPParaCharShapeBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.*
import com.tang.hwplib.builder.bodytext.paragraph.header.HWPDivideSortBuilder
import com.tang.hwplib.builder.bodytext.paragraph.header.HWPParaHeadBuilder
import com.tang.hwplib.builder.bodytext.paragraph.lineseg.HWPLineSegItemBuilder
import com.tang.hwplib.builder.bodytext.paragraph.lineseg.HWPLineSegItemTagBuilder
import com.tang.hwplib.builder.bodytext.paragraph.lineseg.HWPParaLineSegBuilder
import com.tang.hwplib.builder.bodytext.paragraph.text.HWPParaTextBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControl
import com.tang.hwplib.objects.bodytext.control.HWPControlColumnDefine
import com.tang.hwplib.objects.bodytext.control.HWPControlSectionDefine
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderThickness
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType

class HWPBuildEmptyParagraphBuilder(private val docInfo : HWPDocInfo) : HWPBuilder<HWPParagraph> {
    private fun buildParagraph() : HWPParagraphBuilder = HWPParagraphBuilder()
            .setHeader(HWPParaHeadBuilder()
                    .setLastInList(true).setCharacterCount(17)
                    .setParaShapeID(3).setDivideSort(HWPDivideSortBuilder().setValue(3))
                    .setCharShapeCount(1).setLineAlignCount(1))
            .setParaShape(HWPParaCharShapeBuilder().addParaCharShape(0, 0))
            .setLineSeg(HWPParaLineSegBuilder()
                    .addLineSegItem(HWPLineSegItemBuilder()
                            .setTag(HWPLineSegItemTagBuilder().setValue(393216))
                            .setTextPartHeight(1000)
                            .setDistanceBaseLineToLineVerticalPosition(850)
                            .setLineSpace(600)
                            .setSegmentWidth(42520)
                            .setLineHeight(1000)))
            .setParaText(HWPParaTextBuilder(docInfo)
                    .addExtendChar(HWPControlSectionDefine())
                    .addExtendChar(HWPControlColumnDefine()))
            .setControlList(HWPControlListBuilder()
                    .addControl(buildSectionDefine())
                    .addControl(buildColumnDef()))

    private fun buildSectionDefine() : HWPControlSectionDefineBuilder = HWPControlSectionDefineBuilder()
            .setPageDef(HWPPageDefBuilder()
                    .setPaperWidth(59528)
                    .setPaperHeight(84188)
                    .setLeftMargin(8504)
                    .setRightMargin(8504)
                    .setTopMargin(5668)
                    .setBottomMargin(4552)
                    .setHeaderMargin(4252)
                    .setFooterMargin(4252))
            .setFootnoteShape(HWPFootEndNoteShapeBuilder()
                    .setProperty(HWPFootnoteShapePropertyBuilder().setValue(0))
                    .setUserSymbol("\u0000")
                    .setBeforeDecorativeLetter("\u0000")
                    .setAfterDecorativeLetter(")")
                    .setStartNumber(1)
                    .setDivideLineLength(-1)
                    .setDivideLineTopMargin(850)
                    .setDivideLineBottomMargin(567)
                    .setBetweenNotesMargin(283)
                    .setDivideLineSort(HWPBorderType.Dash)
                    .setDivideLineThickness(HWPBorderThickness.MM0_12))
            .setEndnoteShape(HWPFootEndNoteShapeBuilder()
                    .setProperty(HWPFootnoteShapePropertyBuilder().setValue(0))
                    .setUserSymbol("\u0000")
                    .setBeforeDecorativeLetter("\u0000")
                    .setAfterDecorativeLetter(")")
                    .setStartNumber(1)
                    .setDivideLineLength(14692344)
                    .setDivideLineTopMargin(850)
                    .setDivideLineBottomMargin(567)
                    .setDivideLineSort(HWPBorderType.Dash)
                    .setDivideLineThickness(HWPBorderThickness.MM0_12))
            .setBothPageBorderFill(HWPPageBorderFillBuilder()
                    .setProperty(HWPPageBorderFillPropertyBuilder().setValue(1))
                    .setLeftGap(1417).setRightGap(1417).setTopGap(1417).setBottomGap(1417)
                    .setBorderFillID(1))
            .setEvenPageBorderFill(HWPPageBorderFillBuilder()
                    .setProperty(HWPPageBorderFillPropertyBuilder().setValue(1))
                    .setLeftGap(1417).setRightGap(1417).setTopGap(1417).setBottomGap(1417)
                    .setBorderFillID(1))
            .setOddPageBorderFill(HWPPageBorderFillBuilder()
                    .setProperty(HWPPageBorderFillPropertyBuilder().setValue(1))
                    .setLeftGap(1417).setRightGap(1417).setTopGap(1417).setBottomGap(1417)
                    .setBorderFillID(1))

    private fun buildColumnDef() : HWPControlColumnDefineBuilder = HWPControlColumnDefineBuilder()
            .setHeader(HWPCtrlHeaderColumnDefineBuilder()
                    .setProperty(HWPColumnDefineHeaderPropertyBuilder().setValue(4100))
                    .setDivideLineSort(HWPBorderType.Solid)
                    .setDivideLineThickness(HWPBorderThickness.MM0_1))

    override fun build(): HWPParagraph = buildParagraph().build()
}