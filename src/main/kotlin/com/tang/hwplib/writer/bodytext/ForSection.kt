package com.tang.hwplib.writer.bodytext

import com.tang.hwplib.objects.bodytext.HWPSection
import com.tang.hwplib.writer.util.StreamWriter
import com.tang.hwplib.writer.bodytext.paragraph.control.secd.forBatangPageInfo
import com.tang.hwplib.writer.bodytext.paragraph.forParagraphList

/**
 * 섹션 [HWPSection]을 쓰는 객체
 *
 * @author accforaus
 *
 * @param [s] [HWPSection], 섹션 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forSection(s: HWPSection, sw: StreamWriter) {
    forParagraphList(s, sw)
    s.lastBatangPageInfo?.run {
        sw.upRecordLevel()
        forBatangPageInfo(this, sw)
        sw.downRecordLevel()
    }
}