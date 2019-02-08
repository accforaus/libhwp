package com.tang.hwplib.reader.bodytext.paragraph.control.secd

import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderSectionDefine
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPBatangPageInfo
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPFootEndNoteShape
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPPageBorderFill
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPPageDef
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderThickness
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.reader.bodytext.paragraph.forParagraphList
import com.tang.hwplib.reader.util.StreamReader
import com.tang.hwplib.objects.etc.*

/**
 * 구역 정의 개체 헤더 [HWPCtrlHeaderSectionDefine]를 읽는 함수
 *
 * @author accforaus
 *
 * @param [header] [HWPCtrlHeaderSectionDefine], 빈 구역 정의 개체 헤더 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forCtrlHeaderSecd(header: HWPCtrlHeaderSectionDefine, sr: StreamReader) {
    header.property.value = sr.readUInt32()
    header.columnGap = sr.readUInt16()
    header.verticalLineAlign = sr.readUInt16()
    header.horizontalLineAlign = sr.readUInt16()
    header.defaultTabGap = sr.readUInt32()
    header.numberParaShapeId = sr.readUInt16()
    header.pageStartNumber = sr.readUInt16()
    header.imageStartNumber = sr.readUInt16()
    header.tableStartNumber = sr.readUInt16()
    header.equationStartNumber = sr.readUInt16()
    if (!sr.isEndOfRecord() && sr.fileVersion!!.isOver(5, 0, 1, 2))
        header.defaultLanguage = sr.readUInt16()
    sr.skipToEndRecord()
}

/**
 * 각주/미주 모양 [HWPFootEndNoteShape]를 읽는 함수
 * Tag ID: [FOOTNOTE_SHAPE]
 *
 * @author accforaus
 *
 * @param [fens] [HWPFootEndNoteShape], 빈 각주/미주 모양 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forFootEndNoteShape(fens: HWPFootEndNoteShape, sr: StreamReader) {
    fens.property.value = sr.readUInt32()
    fens.userSymbol = sr.readWChar()
    fens.beforeDecorativeLetter = sr.readWChar()
    fens.afterDecorativeLetter = sr.readWChar()
    fens.startNumber = sr.readUInt16()
    fens.divideLineLength = sr.readUInt32()
    fens.divideLineTopMargin = sr.readUInt16()
    fens.divideLineBottomMargin = sr.readUInt16()
    fens.betweenNotesMargin = sr.readUInt16()
    fens.divideLineSort = HWPBorderType.valueOf(sr.readUInt8().toByte())
    fens.divideLineThickness = HWPBorderThickness.valueOf(sr.readUInt8().toByte())
    fens.divideLineColor.value = sr.readColorRef()
}

/**
 * 쪽 테두리/배경 [HWPPageBorderFill]을 읽는 함수
 * Tag ID: [PAGE_BORDER_FILL]
 *
 * @author accforaus
 *
 * @param [pbf] [HWPPageBorderFill], 빈 쪽 테두리/배경 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forPageBorderFill(pbf: HWPPageBorderFill, sr: StreamReader) {
    pbf.property.value = sr.readUInt32()
    pbf.leftGap = sr.readUInt16()
    pbf.rightGap = sr.readUInt16()
    pbf.topGap = sr.readUInt16()
    pbf.bottomGap = sr.readUInt16()
    pbf.borderFillId = sr.readUInt16()
}

/**
 * 용지 설정 [HWPPageDef]를 읽는 함수
 * Tag ID: [PAGE_DEF]
 *
 * @author accforaus
 *
 * @param [pd] [HWPPageDef], 빈 용지 설정 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forPageDef(pd: HWPPageDef, sr: StreamReader) {
    pd.paperWidth = sr.readHwpUnit()
    pd.paperHeight = sr.readHwpUnit()
    pd.leftMargin = sr.readHwpUnit()
    pd.rightMargin = sr.readHwpUnit()
    pd.topMargin = sr.readHwpUnit()
    pd.bottomMargin = sr.readHwpUnit()
    pd.headerMargin = sr.readHwpUnit()
    pd.footerMargin = sr.readHwpUnit()
    pd.gutterMargin = sr.readHwpUnit()
    pd.property.value = sr.readUInt32()
}

/**
 * 바탕쪽 정보 [HWPBatangPageInfo]를 읽는 함수
 *
 * @author accforaus
 *
 * @param [bpi] [HWPBatangPageInfo], 빈 바탕쪽 정보 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forBatangPageInfo(bpi: HWPBatangPageInfo, sr: StreamReader) {
    bpi.listHeader.run {
        paraCount = sr.readInt32()
        property.value = sr.readUInt32()
        textWidth = sr.readUInt32()
        textHeight = sr.readUInt32()
        sr.skipToEndRecord()
    }
    forParagraphList(bpi.paragraphList, sr)
}