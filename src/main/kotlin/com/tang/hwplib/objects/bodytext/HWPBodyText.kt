package com.tang.hwplib.objects.bodytext

/**
 * 본문을 나타내는 객체
 *
 * @author accforaus
 *
 * @property [sectionList] [ArrayList], 섹션 리스트
 */
class HWPBodyText {
    var sectionList: ArrayList<HWPSection> = ArrayList()

    /**
     * 섹션을 추가하고 반환하는 함수
     *
     * @return [HWPSection] 생성된 객체 반환
     */
    fun addNewSection() : HWPSection = HWPSection().apply { sectionList.add(this) }
}