package com.tang.hwplib.writer.bodytext.paragraph.control.headerfooter

import com.tang.hwplib.objects.bodytext.control.headerfooter.ListHeaderForHeaderFooter
import com.tang.hwplib.objects.etc.LIST_HEADER
import com.tang.hwplib.writer.util.StreamWriter

/**
 * 머리말/꼬리말 문단 리스트 헤더 [ListHeaderForHeaderFooter]를 쓰는 함수
 *
 * @param [lh] [ListHeaderForHeaderFooter], 머리말/꼬리말 문단 리스트 헤더 객체
 * @param [sw] [StreamWriter], 리스트 쓰기 객체
 */
internal fun forListheaderForHeaderFooter(lh: ListHeaderForHeaderFooter, sw: StreamWriter) {
    sw.writeRecordHeader(LIST_HEADER.toInt(), 34)

    lh.run {
        sw.writeInt32(paraCount)
        sw.writeUInt32(property.value)
        sw.writeUInt32(textWidth)
        sw.writeUInt32(textHeight)
        sw.writeZero(18)
    }
}