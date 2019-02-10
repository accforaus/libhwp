package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.objects.docinfo.tabdef.HWPTabDefProperty
import com.tang.hwplib.objects.docinfo.tabdef.HWPTabInfo
import com.tang.hwplib.objects.etc.TAB_DEF
/**
 * 탭 정의를 나타내는 객체
 * Tag ID: HWPTAG_TAB_DEF [TAB_DEF]
 * 8 + (8 x count) bytes
 *
 * @author accforaus
 *
 * @property [property] [HWPTabDefProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [tabInfoList] [ArrayList], 탭 정보 (count 개수) (8 * count bytes)
 */
class HWPTabDef {
    var property: HWPTabDefProperty = HWPTabDefProperty()
    var tabInfoList: ArrayList<HWPTabInfo> = ArrayList()

    /**
     * 탭 정보를 추가하고 생성된 정보를 반환하는 함수
     *
     * @return [HWPTabInfo] 생성된 정보 반환
     */
    fun addNewTabInfo() : HWPTabInfo = HWPTabInfo().also { tabInfoList.add(it) }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPTabDef] 복사된 객체 반환
     */
    fun copy() : HWPTabDef = HWPTabDef().also {
        it.property.value = this.property.value
        for (tabInfo in this.tabInfoList) it.tabInfoList.add(tabInfo)
    }

    /**
     * 객체를 생성하고 반환하는 함수
     *
     * @return [HWPTabDef] 생성된 객체 반환
     */
    companion object {
        fun build(property: HWPTabDefProperty = HWPTabDefProperty.build(),
                  tabInfoGenerator: () -> ArrayList<HWPTabInfo> = {ArrayList()})
                : HWPTabDef = HWPTabDef().apply {
            this.property = property
            this.tabInfoList = tabInfoGenerator()
        }
    }
}