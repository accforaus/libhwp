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

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [ListHeaderForBatangPage] 복사된 객체 반환
     */
    fun copy() : ListHeaderForBatangPage = ListHeaderForBatangPage().also {
        it.paraCount = this.paraCount
        it.property.value = this.property.value
        it.textWidth = this.textWidth
        it.textHeight = this.textHeight
    }
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

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPBatangPageInfo] 복사된 객체 반환
     */
    fun copy() : HWPBatangPageInfo = HWPBatangPageInfo().also {
        it.listHeader = this.listHeader.copy()
        it.paragraphList = this.paragraphList.copy()
    }
}