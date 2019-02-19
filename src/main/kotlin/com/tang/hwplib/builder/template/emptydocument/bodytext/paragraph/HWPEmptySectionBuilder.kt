package com.tang.hwplib.builder.template.emptydocument.bodytext.paragraph

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.HWPSection
import com.tang.hwplib.objects.docinfo.HWPDocInfo

class HWPEmptySectionBuilder(private val docInfo : HWPDocInfo) : HWPBuilder<HWPSection> {
    override fun build(): HWPSection = HWPSection().apply {
        paragraphList.add(HWPBuildEmptyParagraphBuilder(docInfo).build())
    }
}