package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.objects.docinfo.HWPTabDef
import com.tang.hwplib.objects.docinfo.tabdef.HWPTabDefProperty

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