package com.tang.hwplib.objects.bodytext.control.sectiondefine

import com.tang.hwplib.objects.bodytext.control.gso.listheader.HWPListHeaderProperty
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphList

/**
 * 바탕쪽 문단 리스트 헤더를 나타내는 객체
 * 10 bytes
 * @author accforaus
 *
 * @property [paraCount] [Int], 문단 수 (INT16 - signed 2 bytes)
 * @property [property] [HWPListHeaderProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [textWidth] [Long], 텍스트의 최대 길이 (=개체의 폭) (HWPUNIT - unsigned 4 bytes)
 * @property [textHeight] [Long], 텍스트의 최대 높이 (=개체의 높이) (HWPUNIT - unsigned 4 bytes)
 */
class ListHeaderForBatangPage {
    var paraCount: Int = 0
    var property: HWPListHeaderProperty = HWPListHeaderProperty()
    var textWidth: Long = 0
    var textHeight: Long = 0
}

/**
 * 바탕쪽 정보를 나타내는 객체
 *
 * @author accforaus
 *
 * @property [listHeader] [ListHeaderForBatangPage], 바탕쪽 문단 리스트 헤더 (BYTE stream)
 * @property [paragraphList] [HWPParagraphList], 문단 리스트 (BYTE stream)
 */
class HWPBatangPageInfo {
    var listHeader: ListHeaderForBatangPage = ListHeaderForBatangPage()
    var paragraphList: HWPParagraphList = HWPParagraphList()
}