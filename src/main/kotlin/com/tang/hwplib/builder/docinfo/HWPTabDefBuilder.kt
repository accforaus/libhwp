package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.docinfo.tabdef.HWPTabDefPropertyBuilder
import com.tang.hwplib.builder.docinfo.tabdef.HWPTabInfoBuilder
import com.tang.hwplib.copyto.docinfo.IDMappingTypes
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.HWPTabDef
import com.tang.hwplib.objects.docinfo.tabdef.HWPTabDefProperty

class HWPTabDefBuilder(private val docInfo: HWPDocInfo) : HWPDocInfoBuilder() {
    private val tabDef: HWPTabDef = HWPTabDef.build()

    fun setProperty(propertyBuilder: HWPTabDefPropertyBuilder) : HWPTabDefBuilder = this.apply {
        tabDef.property = propertyBuilder.build()
    }

    fun addTabInfo(tabInfoBuilder: HWPTabInfoBuilder) : HWPTabDefBuilder = this.apply {
        tabDef.tabInfoList.add(tabInfoBuilder.build())
    }

    fun proceed() : Int = build().run {
        docInfo.tabDefList.size - 1
    }

    override fun build(): HWPTabDef = tabDef.apply {
        docInfo.tabDefList.add(this)
        docInfo.updateIDMappings(IDMappingTypes.TABDEF)
    }
}

class HWPTabDefListBuilder : HWPBuilder<ArrayList<HWPTabDef>> {
    private val tabDefList : ArrayList<HWPTabDef> = ArrayList()

    fun addTabDef(tabDefBuilder: HWPTabDefBuilder) : HWPTabDefListBuilder = this.apply {
        tabDefList.add(tabDefBuilder.build())
    }

    override fun build(): ArrayList<HWPTabDef> = tabDefList
}