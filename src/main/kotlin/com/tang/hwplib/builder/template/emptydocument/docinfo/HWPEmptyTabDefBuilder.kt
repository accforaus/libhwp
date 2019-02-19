package com.tang.hwplib.builder.template.emptydocument.docinfo

import com.tang.hwplib.builder.docinfo.HWPTabDefBuilder
import com.tang.hwplib.builder.docinfo.tabdef.HWPTabDefPropertyBuilder
import com.tang.hwplib.builder.etc.HWPDocInfoBuilderType
import com.tang.hwplib.objects.docinfo.HWPDocInfo

class HWPEmptyTabDefBuilder {
    private fun getBuilder(docInfo: HWPDocInfo) : HWPTabDefBuilder = docInfo.getBuilder(HWPDocInfoBuilderType.TabDef) as HWPTabDefBuilder

    fun build(docInfo: HWPDocInfo) {
        getBuilder(docInfo)
                .setProperty(HWPTabDefPropertyBuilder().setValue(0)).build()
        getBuilder(docInfo)
                .setProperty(HWPTabDefPropertyBuilder().setValue(1)).build()
        getBuilder(docInfo)
                .setProperty(HWPTabDefPropertyBuilder().setValue(2)).build()
    }
}