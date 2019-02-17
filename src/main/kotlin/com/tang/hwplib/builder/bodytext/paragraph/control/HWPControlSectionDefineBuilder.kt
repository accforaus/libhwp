package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.bodytext.paragraph.HWPParagraphListBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.listheader.HWPListHeaderPropertyBuilder
import com.tang.hwplib.builder.etc.Color4ByteBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlSectionDefine
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderSectionDefine
import com.tang.hwplib.objects.bodytext.control.ctrlheader.sectiondefine.HWPSectionDefineHeaderProperty
import com.tang.hwplib.objects.bodytext.control.ctrlheader.sectiondefine.HWPTextDirection
import com.tang.hwplib.objects.bodytext.control.sectiondefine.*
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderThickness
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.objects.etc.Color4Byte
import com.tang.hwplib.reader.bodytext.paragraph.control.secd.forBatangPageInfo

class HWPControlSectionDefineBuilder : HWPControlBuilder<HWPControlSectionDefine> {
    private val control: HWPControlSectionDefine = HWPControlSectionDefine.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderSectionDefineBuilder) : HWPControlSectionDefineBuilder = this.apply {
        control.header = headerBuilder.build()
    }

    fun setPageDef(pageDefBuilder: HWPPageDefBuilder) : HWPControlSectionDefineBuilder = this.apply {
        control.pageDef = pageDefBuilder.build()
    }

    fun setFootnoteShape(footNoteShapeBuilder: HWPFootEndNoteShapeBuilder) : HWPControlSectionDefineBuilder = this.apply {
        control.footnoteShape = footNoteShapeBuilder.build()
    }

    fun setEndnoteShape(endnoteShapeBuilder: HWPFootEndNoteShapeBuilder) : HWPControlSectionDefineBuilder = this.apply {
        control.endnoteShape = endnoteShapeBuilder.build()
    }

    fun setBothPageBorderFill(pageBorderFillBuilder: HWPPageBorderFillBuilder) : HWPControlSectionDefineBuilder = this.apply {
        control.bothPageBorderFill = pageBorderFillBuilder.build()
    }

    fun setEvenPageBorderFill(pageBorderFillBuilder: HWPPageBorderFillBuilder) : HWPControlSectionDefineBuilder = this.apply {
        control.evenPageBorderFill = pageBorderFillBuilder.build()
    }

    fun setOddPageBorderFill(pageBorderFillBuilder: HWPPageBorderFillBuilder) : HWPControlSectionDefineBuilder = this.apply {
        control.oddPageBorderFill = pageBorderFillBuilder.build()
    }

    fun setBatangPageInfoList(batangPageInfoListBuilder: HWPBatangPageInfoListBuilder) : HWPControlSectionDefineBuilder = this.apply {
        control.batangPageInfoList = batangPageInfoListBuilder.build()
    }

    override fun build(): HWPControlSectionDefine = control
}

class HWPCtrlHeaderSectionDefineBuilder : HWPBuilder<HWPCtrlHeaderSectionDefine> {
    private val header : HWPCtrlHeaderSectionDefine = HWPCtrlHeaderSectionDefine.build()

    fun setProperty(propertyBuilder: HWPSectionDefineHeaderPropertyBuilder) : HWPCtrlHeaderSectionDefineBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    fun setColumnGap(columnGap: Int) : HWPCtrlHeaderSectionDefineBuilder = this.apply {
        header.columnGap = columnGap
    }

    fun setVerticalLineAlign(verticalLineAlign: Int) : HWPCtrlHeaderSectionDefineBuilder = this.apply {
        header.verticalLineAlign = verticalLineAlign
    }

    fun setHorizontalLineAlign(horizontalLineAlign: Int) : HWPCtrlHeaderSectionDefineBuilder = this.apply {
        header.horizontalLineAlign = horizontalLineAlign
    }

    fun setDefaultTabGap(defaultTabGap: Long) : HWPCtrlHeaderSectionDefineBuilder = this.apply {
        header.defaultTabGap = defaultTabGap
    }

    fun setNumberParaShapeID(numberParaShapeID: Int) : HWPCtrlHeaderSectionDefineBuilder = this.apply {
        header.numberParaShapeId = numberParaShapeID
    }

    fun setPageStartNumber(pageStartNumber: Int) : HWPCtrlHeaderSectionDefineBuilder = this.apply {
        header.pageStartNumber = pageStartNumber
    }

    fun setImageStartNumber(imageStartNumber: Int) : HWPCtrlHeaderSectionDefineBuilder = this.apply {
        header.imageStartNumber = imageStartNumber
    }

    fun setTableStartNumber(tableStartNumber: Int) : HWPCtrlHeaderSectionDefineBuilder = this.apply {
        header.tableStartNumber = tableStartNumber
    }

    fun setEquationStartNumber(equationStartNumber: Int) : HWPCtrlHeaderSectionDefineBuilder = this.apply {
        header.equationStartNumber = equationStartNumber
    }

    fun setDefaultLanguage(defaultLanguage: Int) : HWPCtrlHeaderSectionDefineBuilder = this.apply {
        header.defaultLanguage = defaultLanguage
    }

    override fun build(): HWPCtrlHeaderSectionDefine = header
}

class HWPSectionDefineHeaderPropertyBuilder : HWPBuilder<HWPSectionDefineHeaderProperty> {
    private val property : HWPSectionDefineHeaderProperty = HWPSectionDefineHeaderProperty.build()

    fun setValue(value: Long) : HWPSectionDefineHeaderPropertyBuilder = this.apply {
        property.value = value
    }

    fun setHideHeader(hideHeader: Boolean) : HWPSectionDefineHeaderPropertyBuilder = this.apply {
        property.setHideHeader(hideHeader)
    }

    fun setHideFooter(hideFooter: Boolean) : HWPSectionDefineHeaderPropertyBuilder = this.apply {
        property.setHideFooter(hideFooter)
    }

    fun setHideBatangPage(hideBatangPage: Boolean) : HWPSectionDefineHeaderPropertyBuilder = this.apply {
        property.setHideBatangPage(hideBatangPage)
    }

    fun setHideBorder(hideBorder: Boolean) : HWPSectionDefineHeaderPropertyBuilder = this.apply {
        property.setHideBorder(hideBorder)
    }

    fun setHideBackground(hideBackground: Boolean) : HWPSectionDefineHeaderPropertyBuilder = this.apply {
        property.setHideBackground(hideBackground)
    }

    fun setHidePageNumberPosition(hidePageNumberPosition: Boolean) : HWPSectionDefineHeaderPropertyBuilder = this.apply {
        property.setHidePageNumberPosition(hidePageNumberPosition)
    }

    fun setDisplayBorderAtFirstPageOfSection(displayBorderAtFirstPageOfSection: Boolean) : HWPSectionDefineHeaderPropertyBuilder = this.apply {
        property.setDisplayBorderAtFirstPageOfSection(displayBorderAtFirstPageOfSection)
    }

    fun setDisplayBackgroundAtFirstPageOfSection(displayBackgroundAtFirstPageOfSection: Boolean) : HWPSectionDefineHeaderPropertyBuilder = this.apply {
        property.setDisplayBackgroundAtFirstPageOfSection(displayBackgroundAtFirstPageOfSection)
    }

    fun setTextDirection(textDirection: HWPTextDirection) : HWPSectionDefineHeaderPropertyBuilder = this.apply {
        property.setTextDirection(textDirection)
    }

    fun setHideEmptyLine(hideEmptyLine: Boolean) : HWPSectionDefineHeaderPropertyBuilder = this.apply {
        property.setHideEmptyLine(hideEmptyLine)
    }

    fun setApplyPageNumberByDivideSection(applyPageNumberByDivideSection: Boolean) : HWPSectionDefineHeaderPropertyBuilder = this.apply {
        property.setApplyPageNumberByDivideSection(applyPageNumberByDivideSection)
    }

    fun setApplyWongoji(applyWongoji: Boolean) : HWPSectionDefineHeaderPropertyBuilder = this.apply {
        property.setApplyWongoji(applyWongoji)
    }

    override fun build(): HWPSectionDefineHeaderProperty = property
}

class HWPPageDefBuilder : HWPBuilder<HWPPageDef> {
    private val pageDef : HWPPageDef = HWPPageDef.build()

    fun setPaperWidth(paperWidth: Long) : HWPPageDefBuilder = this.apply {
        pageDef.paperWidth = paperWidth
    }

    fun setPaperHeight(paperHeight: Long) : HWPPageDefBuilder = this.apply {
        pageDef.paperHeight = paperHeight
    }

    fun setLeftMargin(leftMargin: Long) : HWPPageDefBuilder = this.apply {
        pageDef.leftMargin = leftMargin
    }

    fun setRightMargin(rightMargin: Long) : HWPPageDefBuilder = this.apply {
        pageDef.rightMargin = rightMargin
    }

    fun setTopMargin(topMargin: Long) : HWPPageDefBuilder = this.apply {
        pageDef.topMargin = topMargin
    }

    fun setBottomMargin(bottomMargin: Long) : HWPPageDefBuilder = this.apply {
        pageDef.bottomMargin = bottomMargin
    }

    fun setHeaderMargin(headerMargin: Long) : HWPPageDefBuilder = this.apply {
        pageDef.headerMargin = headerMargin
    }

    fun setFooterMargin(footerMargin: Long) : HWPPageDefBuilder = this.apply {
        pageDef.footerMargin = footerMargin
    }

    fun setGutterMargin(gutterMargin: Long) : HWPPageDefBuilder = this.apply {
        pageDef.gutterMargin = gutterMargin
    }

    fun setProperty(propertyBuilder: HWPPageDefPropertyBuilder) : HWPPageDefBuilder = this.apply {
        pageDef.property = propertyBuilder.build()
    }

    override fun build(): HWPPageDef = pageDef
}

class HWPPageDefPropertyBuilder : HWPBuilder<HWPPageDefProperty> {
    private val property : HWPPageDefProperty = HWPPageDefProperty.build()

    fun setValue(value: Long) : HWPPageDefPropertyBuilder = this.apply {
        property.value = value
    }

    fun setPaperDirection(paperDirection: HWPPaperDirection) : HWPPageDefPropertyBuilder = this.apply {
        property.setPaperDirection(paperDirection)
    }

    fun setMakingBookMethod(makingBookMethod: HWPMakingBookMethod) : HWPPageDefPropertyBuilder = this.apply {
        property.setMakingBookMethod(makingBookMethod)
    }

    override fun build(): HWPPageDefProperty = property
}

class HWPPageBorderFillBuilder : HWPBuilder<HWPPageBorderFill> {
    private val borderFill: HWPPageBorderFill = HWPPageBorderFill.build()

    fun setProperty(propertyBuilder: HWPPageBorderFillPropertyBuilder) : HWPPageBorderFillBuilder = this.apply {
        borderFill.property = propertyBuilder.build()
    }

    fun setLeftGap(leftGap: Int) : HWPPageBorderFillBuilder = this.apply {
        borderFill.leftGap = leftGap
    }

    fun setRightGap(rightGap: Int) : HWPPageBorderFillBuilder = this.apply {
        borderFill.rightGap = rightGap
    }

    fun setTopGap(topGap: Int) : HWPPageBorderFillBuilder = this.apply {
        borderFill.topGap = topGap
    }

    fun setBottomGap(bottomGap: Int) : HWPPageBorderFillBuilder = this.apply {
        borderFill.bottomGap = bottomGap
    }

    fun setBorderFillID(borderFillID: Int) : HWPPageBorderFillBuilder = this.apply {
        borderFill.borderFillId = borderFillID
    }

    override fun build(): HWPPageBorderFill = borderFill
}

class HWPPageBorderFillPropertyBuilder : HWPBuilder<HWPPageBorderFillProperty> {
    private val property : HWPPageBorderFillProperty = HWPPageBorderFillProperty.build()

    fun setValue(value: Long) : HWPPageBorderFillPropertyBuilder = this.apply {
        property.value = value
    }

    fun setPositionCriterion(positionCriterion: HWPPositionCriterion) : HWPPageBorderFillPropertyBuilder = this.apply {
        property.setPositionCriterion(positionCriterion)
    }

    fun setIncludeHeader(includeHeader: Boolean) : HWPPageBorderFillPropertyBuilder = this.apply {
        property.setIncludeHeader(includeHeader)
    }

    fun setIncludeFooter(includeFooter: Boolean) : HWPPageBorderFillPropertyBuilder = this.apply {
        property.setIncludeFooter(includeFooter)
    }

    fun setFillArea(fillArea: HWPFillArea) : HWPPageBorderFillPropertyBuilder = this.apply {
        property.setFillArea(fillArea)
    }

    override fun build(): HWPPageBorderFillProperty = property
}

class HWPBatangPageInfoListBuilder : HWPBuilder<ArrayList<HWPBatangPageInfo>> {
    private val batangPageInfoList: ArrayList<HWPBatangPageInfo> = ArrayList()

    fun addBatangPageInfo(batangPageBuilder: HWPBatangPageInfoBuilder) : HWPBatangPageInfoListBuilder = this.apply {
        batangPageInfoList.add(batangPageBuilder.build())
    }

    override fun build(): ArrayList<HWPBatangPageInfo> = batangPageInfoList
}

class HWPBatangPageInfoBuilder : HWPBuilder<HWPBatangPageInfo> {
    private val batangPageInfo : HWPBatangPageInfo = HWPBatangPageInfo.build()

    fun setListHeader(listHeaderBuilder: ListHeaderForBatangPageBuilder) : HWPBatangPageInfoBuilder = this.apply {
        batangPageInfo.listHeader = listHeaderBuilder.build()
    }

    fun setParagraphList(paragraphListBuilder: HWPParagraphListBuilder) : HWPBatangPageInfoBuilder = this.apply {
        batangPageInfo.paragraphList = paragraphListBuilder.build()
    }

    override fun build(): HWPBatangPageInfo = batangPageInfo
}

class ListHeaderForBatangPageBuilder : HWPBuilder<ListHeaderForBatangPage> {
    private val header : ListHeaderForBatangPage = ListHeaderForBatangPage.build()

    fun setParaCount(paraCount: Int) : ListHeaderForBatangPageBuilder = this.apply {
        header.paraCount = paraCount
    }

    fun setProperty(propertyBuilder: HWPListHeaderPropertyBuilder) : ListHeaderForBatangPageBuilder = this.apply {
        header.property = propertyBuilder.build()
    }

    fun setTextWidth(textWidth: Long) : ListHeaderForBatangPageBuilder = this.apply {
        header.textWidth = textWidth
    }

    fun setTextHeight(textHeight: Long) : ListHeaderForBatangPageBuilder = this.apply {
        header.textHeight = textHeight
    }

    override fun build(): ListHeaderForBatangPage = header
}

class HWPFootEndNoteShapeBuilder : HWPBuilder<HWPFootEndNoteShape> {
    private val shape : HWPFootEndNoteShape = HWPFootEndNoteShape.build()

    fun setProperty(propertyBuilder: HWPFootnoteShapePropertyBuilder) : HWPFootEndNoteShapeBuilder = this.apply {
        shape.property = propertyBuilder.build()
    }

    fun setUserSymbol(userSymbol: String) : HWPFootEndNoteShapeBuilder = this.apply {
        shape.userSymbol = userSymbol
    }

    fun setBeforeDecorativeLetter(beforeDecorativeLetter: String) : HWPFootEndNoteShapeBuilder = this.apply {
        shape.beforeDecorativeLetter = beforeDecorativeLetter
    }

    fun setAfterDecorativeLetter(afterDecorativeLetter: String) : HWPFootEndNoteShapeBuilder = this.apply {
        shape.afterDecorativeLetter = afterDecorativeLetter
    }

    fun setStartNumber(startNumber: Int) : HWPFootEndNoteShapeBuilder = this.apply {
        shape.startNumber = startNumber
    }

    fun setDivideLineLength(divideLineLength: Long) : HWPFootEndNoteShapeBuilder = this.apply {
        shape.divideLineLength = divideLineLength
    }

    fun setDivideLineTopMargin(divideLineTopMargin: Int) : HWPFootEndNoteShapeBuilder = this.apply {
        shape.divideLineTopMargin = divideLineTopMargin
    }

    fun setDivideLineBottomMargin(divideLineBottomMargin: Int) : HWPFootEndNoteShapeBuilder = this.apply {
        shape.divideLineBottomMargin = divideLineBottomMargin
    }

    fun setBetweenNotesMargin(betweenNotesMargin: Int) : HWPFootEndNoteShapeBuilder = this.apply {
        shape.betweenNotesMargin = betweenNotesMargin
    }

    fun setDivideLineSort(divideLineSort: HWPBorderType) : HWPFootEndNoteShapeBuilder = this.apply {
        shape.divideLineSort = divideLineSort
    }

    fun setDivideLineThickness(divideLineThickness: HWPBorderThickness) : HWPFootEndNoteShapeBuilder = this.apply {
        shape.divideLineThickness = divideLineThickness
    }

    fun setDivideLineColor(colorBuilder: Color4ByteBuilder) : HWPFootEndNoteShapeBuilder = this.apply {
        shape.divideLineColor = colorBuilder.build()
    }

    override fun build(): HWPFootEndNoteShape = shape
}

class HWPFootnoteShapePropertyBuilder : HWPBuilder<HWPFootnoteShapeProperty> {
    private val property : HWPFootnoteShapeProperty = HWPFootnoteShapeProperty.build()

    fun setValue(value: Long) : HWPFootnoteShapePropertyBuilder = this.apply {
        property.value = value
    }

    fun setNumberShape(numberShape: HWPNumberShape) : HWPFootnoteShapePropertyBuilder = this.apply {
        property.setNumberShape(numberShape)
    }

    fun setFootnoteDisplayMethod(footnoteDisplayMethod: HWPFootnoteDisplayMethod) : HWPFootnoteShapePropertyBuilder = this.apply {
        property.setFootnoteDisplayMethod(footnoteDisplayMethod)
    }

    fun setEndnoteDisplayMethod(endnoteDisplayMethod: HWPEndnoteDisplayMethod) : HWPFootnoteShapePropertyBuilder = this.apply {
        property.setEndnoteDisplayMethod(endnoteDisplayMethod)
    }

    fun setNumberingMethod(numberingMethod: HWPNumberingMethod) : HWPFootnoteShapePropertyBuilder = this.apply {
        property.setNumberingMethod(numberingMethod)
    }

    fun setDisplayWithSupScript(displayWithSupScript: Boolean) : HWPFootnoteShapePropertyBuilder = this.apply {
        property.setDisplayWithSupscript(displayWithSupScript)
    }

    fun setContinueFromText(continueFromText: Boolean) : HWPFootnoteShapePropertyBuilder = this.apply {
        property.setContinueFromText(continueFromText)
    }

    override fun build(): HWPFootnoteShapeProperty = property
}

internal fun buildEmptySectionDefine() : HWPControlSectionDefine = HWPControlSectionDefine.build(
        pageDef = HWPPageDef.build(
                paperWidth = 59528, paperHeight = 84188, leftMargin = 8504, rightMargin = 8504,
                topMargin = 5668, bottomMargin = 4252, headerMargin = 4252, footerMargin = 4252
        ),
        footnoteShape = HWPFootEndNoteShape.build(
                property = HWPFootnoteShapeProperty.build(0),
                userSymbol = "\u0000",
                beforeDecorativeLetter = "\u0000",
                afterDecorativeLetter = ")", startNumber = 1,
                divideLineLength = -1, divideLineTopMargin = 850,
                divideLineBottomMargin = 567, betweenNotesMargin = 283,
                divideLineSort = HWPBorderType.Dash,
                divideLineThickness = HWPBorderThickness.MM0_12
        ),
        endnoteShape = HWPFootEndNoteShape.build(
                property = HWPFootnoteShapeProperty.build(0),
                userSymbol = "\u0000", beforeDecorativeLetter = "\u0000",
                afterDecorativeLetter = ")", startNumber = 1,
                divideLineLength = 14692344,
                divideLineTopMargin = 850, divideLineBottomMargin = 567,
                divideLineSort = HWPBorderType.Dash, divideLineThickness = HWPBorderThickness.MM0_12
        ),
        bothPageBorderFill = HWPPageBorderFill.build(
                property = HWPPageBorderFillProperty.build(1),
                leftGap = 1417, rightGap = 1417, topGap = 1417, bottomGap = 1417,
                borderFillId = 1
        ),
        evenPageBorderFill = HWPPageBorderFill.build(
                property = HWPPageBorderFillProperty.build(1),
                leftGap = 1417, rightGap = 1417, topGap = 1417, bottomGap = 1417,
                borderFillId = 1
        ),
        oddPageBorderFill = HWPPageBorderFill.build(
                property = HWPPageBorderFillProperty.build(1),
                leftGap = 1417, rightGap = 1417, topGap = 1417, bottomGap = 1417,
                borderFillId = 1
        )
).apply {
    header = HWPCtrlHeaderSectionDefine.build(
            property = HWPSectionDefineHeaderProperty.build(0),
            columnGap = 1134, defaultTabGap = 8000, numberParaShapeId = 1
    )
    setCtrlData(null)
}