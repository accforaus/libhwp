package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.docinfo.tabdef.HWPTabDefPropertyBuilder
import com.tang.hwplib.builder.docinfo.tabdef.HWPTabInfoBuilder
import com.tang.hwplib.objects.docinfo.HWPTabDef
import com.tang.hwplib.objects.docinfo.tabdef.HWPTabDefProperty

class HWPTabDefBuilder : HWPBuilder<HWPTabDef> {
    private val tabDef: HWPTabDef = HWPTabDef.build()

    fun setProperty(propertyBuilder: HWPTabDefPropertyBuilder) : HWPTabDefBuilder = this.apply {
        tabDef.property = propertyBuilder.build()
    }

    fun addTabInfo(tabInfoBuilder: HWPTabInfoBuilder) : HWPTabDefBuilder = this.apply {
        tabDef.tabInfoList.add(tabInfoBuilder.build())
    }

    override fun build(): HWPTabDef = tabDef
}

internal fun buildEmptyTabDef() : ArrayList<HWPTabDef> {
    val tabDefList: ArrayList<HWPTabDef> = ArrayList()
    tabDefList.run {
        add(HWPTabDef.build(
                property = HWPTabDefProperty.build(0)
        ))
        add(HWPTabDef.build(
                property = HWPTabDefProperty.build(1)
        ))
        add(HWPTabDef.build(
                property = HWPTabDefProperty.build(2)
        ))
    }
    return tabDefList
}