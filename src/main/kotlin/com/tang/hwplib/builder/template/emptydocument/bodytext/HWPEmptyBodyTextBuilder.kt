package com.tang.hwplib.builder.template.emptydocument.bodytext

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.template.emptydocument.bodytext.paragraph.HWPEmptySectionBuilder
import com.tang.hwplib.objects.bodytext.HWPBodyText

class HWPEmptyBodyTextBuilder : HWPBuilder<HWPBodyText> {
    override fun build(): HWPBodyText = HWPBodyText().apply {
        sectionList.add(HWPEmptySectionBuilder().build())
    }
}