package com.tang.hwplib.writer.bodytext.paragraph.control.endnote

import com.tang.hwplib.objects.bodytext.control.footnoteendnote.ListHeaderForFootnoteEndnote
import com.tang.hwplib.objects.etc.LIST_HEADER
import com.tang.hwplib.writer.util.StreamWriter

/**
 * 각주/미주 문단 리스트 헤더 [ListHeaderForFootnoteEndnote]를 쓰는 함수
 *
 * @param [lh] [ListHeaderForFootnoteEndnote], 각주/미주 문단 리스트 헤더
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forListHeaderForFootnoteEndnote(lh: ListHeaderForFootnoteEndnote, sw: StreamWriter) {
    sw.writeRecordHeader(LIST_HEADER.toInt(), 16)
    lh.run {
        sw.writeInt32(paraCount)
        sw.writeUInt32(property.value)
        sw.writeZero(8)
    }
}