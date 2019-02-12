package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.objects.bodytext.control.HWPControlSectionDefine
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderSectionDefine
import com.tang.hwplib.objects.bodytext.control.ctrlheader.sectiondefine.HWPSectionDefineHeaderProperty
import com.tang.hwplib.objects.bodytext.control.sectiondefine.*
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderThickness
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.objects.etc.Color4Byte

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