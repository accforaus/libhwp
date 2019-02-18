package com.tang.hwplib.builder.template.emptydocument.docinfo

import com.tang.hwplib.builder.docinfo.HWPTabDefBuilder
import com.tang.hwplib.builder.docinfo.HWPTabDefListBuilder
import com.tang.hwplib.builder.docinfo.tabdef.HWPTabDefPropertyBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPTabDef

class HWPEmptyTabDefBuilder : HWPBuilder<ArrayList<HWPTabDef>> {
    private fun buildTabDefList() : HWPTabDefListBuilder = HWPTabDefListBuilder()
            .addTabDef(HWPTabDefBuilder()
                    .setProperty(HWPTabDefPropertyBuilder().setValue(0)))
            .addTabDef(HWPTabDefBuilder()
                    .setProperty(HWPTabDefPropertyBuilder().setValue(1)))
            .addTabDef(HWPTabDefBuilder()
                    .setProperty(HWPTabDefPropertyBuilder().setValue(2)))

    override fun build(): ArrayList<HWPTabDef> = buildTabDefList().build()
}