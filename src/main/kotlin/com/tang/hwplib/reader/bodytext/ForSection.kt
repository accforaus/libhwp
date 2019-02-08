package com.tang.hwplib.reader.bodytext

import com.tang.hwplib.objects.bodytext.HWPSection
import com.tang.hwplib.reader.bodytext.paragraph.control.secd.forBatangPageInfo
import com.tang.hwplib.reader.bodytext.paragraph.forParagraphList
import com.tang.hwplib.reader.util.StreamReader

/**
 * 섹션 객체 [HWPSection]를 읽는 함수
 *
 * @author accforaus
 *
 * @param [s] [HWPSection], 빈 섹션 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forSection(s: HWPSection, sr: StreamReader) {
    forParagraphList(s, sr)
    if (!sr.isEndOfStream()) {
        s.createLastBatangPageInfo()
        forBatangPageInfo(s.lastBatangPageInfo!!, sr)
    }
}