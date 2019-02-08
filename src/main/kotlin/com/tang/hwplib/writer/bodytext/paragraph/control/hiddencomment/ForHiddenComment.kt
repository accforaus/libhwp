package com.tang.hwplib.writer.bodytext.paragraph.control.hiddencomment

import com.tang.hwplib.objects.bodytext.control.hiddencomment.ListHeaderForHiddenComment
import com.tang.hwplib.objects.etc.LIST_HEADER
import com.tang.hwplib.writer.util.StreamWriter

internal fun forListHeaderForHiddenComment(lh: ListHeaderForHiddenComment, sw: StreamWriter) {
    sw.writeRecordHeader(LIST_HEADER.toInt(), 16)

    lh.run {
        sw.writeInt32(paraCount)
        sw.writeUInt32(property.value)
        sw.writeZero(8)
    }
}