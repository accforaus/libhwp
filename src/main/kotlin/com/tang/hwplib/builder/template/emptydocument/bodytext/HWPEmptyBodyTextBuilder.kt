package com.tang.hwplib.builder.template.emptydocument.bodytext

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.template.emptydocument.bodytext.paragraph.HWPEmptySectionBuilder
import com.tang.hwplib.objects.bodytext.HWPBodyText
import com.tang.hwplib.objects.docinfo.HWPDocInfo

class HWPEmptyBodyTextBuilder(private val docInfo : HWPDocInfo) : HWPBuilder<HWPBodyText> {
    override fun build(): HWPBodyText = HWPBodyText().apply {
        sectionList.add(HWPEmptySectionBuilder(docInfo).build())
    }
}