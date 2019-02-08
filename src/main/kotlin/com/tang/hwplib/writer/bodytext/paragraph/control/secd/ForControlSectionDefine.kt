package com.tang.hwplib.writer.bodytext.paragraph.control.secd

import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPBatangPageInfo
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPFootEndNoteShape
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPPageBorderFill
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPPageDef
import com.tang.hwplib.objects.etc.FOOTNOTE_SHAPE
import com.tang.hwplib.objects.etc.LIST_HEADER
import com.tang.hwplib.objects.etc.PAGE_BORDER_FILL
import com.tang.hwplib.objects.etc.PAGE_DEF
import com.tang.hwplib.writer.util.StreamWriter
import com.tang.hwplib.writer.bodytext.paragraph.forParagraphList

/**
 * 바탕쪽 정보 [HWPBatangPageInfo]를 쓰는 함수
 *
 * @author accforaus
 *
 * @param [bpi] [HWPBatangPageInfo], 바탕쪽 정보 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forBatangPageInfo(bpi: HWPBatangPageInfo, sw: StreamWriter) {
    bpi.listHeader.run {
        sw.writeRecordHeader(LIST_HEADER.toInt(), 34)
        sw.writeInt32(paraCount)
        sw.writeUInt32(property.value)
        sw.writeUInt32(textWidth)
        sw.writeUInt32(textHeight)
        sw.writeZero(18)
    }
    forParagraphList(bpi.paragraphList, sw)
}

/**
 * 각주/미주 모양 [HWPFootEndNoteShape]을 쓰는 함수
 * Tag ID: [FOOTNOTE_SHAPE]
 *
 * @author accforaus
 *
 * @param [fens] [HWPFootEndNoteShape], 각주/미주 모양 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forFootEndNoteShape(fens: HWPFootEndNoteShape?, sw: StreamWriter) {
    fens?.run {
        sw.writeRecordHeader(FOOTNOTE_SHAPE.toInt(), 28)
        sw.writeUInt32(property.value)
        sw.writeWChar(userSymbol)
        sw.writeWChar(beforeDecorativeLetter)
        sw.writeWChar(afterDecorativeLetter)
        sw.writeUInt16(startNumber)
        sw.writeUInt32(divideLineLength)
        sw.writeUInt16(divideLineTopMargin)
        sw.writeUInt16(divideLineBottomMargin)
        sw.writeUInt16(betweenNotesMargin)
        sw.writeUInt8(divideLineSort.value.toShort())
        sw.writeUInt8(divideLineThickness.value.toShort())
        sw.writeColorRef(divideLineColor.value)
    }
}

/**
 * 쪽 테두리/배경 [HWPPageBorderFill]을 쓰는 함수
 * Tag ID: [PAGE_BORDER_FILL]
 *
 * @author accforaus
 *
 * @param [pbf] [HWPPageBorderFill], 쪽 테두리/배경 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forPageBorderFill(pbf: HWPPageBorderFill?, sw: StreamWriter) {
    pbf?.run {
        sw.writeRecordHeader(PAGE_BORDER_FILL.toInt(), 14)
        sw.writeUInt32(property.value)
        sw.writeUInt16(leftGap)
        sw.writeUInt16(rightGap)
        sw.writeUInt16(topGap)
        sw.writeUInt16(bottomGap)
        sw.writeUInt16(borderFillId)
    }
}

/**
 * 용지 설정 [HWPPageDef]을 쓰는 함수
 * Tag ID: [PAGE_DEF]
 *
 * @author accforaus
 *
 * @param [pd] [HWPPageDef], 용지 설정 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forPageDef(pd: HWPPageDef?, sw: StreamWriter) {
    pd?.run {
        sw.writeRecordHeader(PAGE_DEF.toInt(), 40)

        sw.writeUInt32(paperWidth)
        sw.writeUInt32(paperHeight)
        sw.writeUInt32(leftMargin)
        sw.writeUInt32(rightMargin)
        sw.writeUInt32(topMargin)
        sw.writeUInt32(bottomMargin)
        sw.writeUInt32(headerMargin)
        sw.writeUInt32(footerMargin)
        sw.writeUInt32(gutterMargin)
        sw.writeUInt32(property.value)
    }
}