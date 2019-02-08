package com.tang.hwplib.writer

import com.tang.hwplib.objects.etc.UnknownRecord
import com.tang.hwplib.writer.util.StreamWriter

/**
 * 알려지지 않은 레코드를 쓰는 함수
 *
 * @author accforaus
 *
 * @param [ur] [UnknownRecord], 알려지지 않은 레코드 객체
 * @param [tagID] [Int], 테그 ID
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forUnknown(ur: UnknownRecord, tagID: Int, sw: StreamWriter) {
    sw.writeRecordHeader(tagID, ur.body!!.size)
    sw.writeBytes(ur.body!!)
}