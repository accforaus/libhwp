package com.tang.hwplib.builder.template.emptydocument.bodytext.paragraph

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.HWPSection

class HWPEmptySectionBuilder : HWPBuilder<HWPSection> {
    override fun build(): HWPSection = HWPSection().apply {
        paragraphList.add(HWPBuildEmptyParagraphBuilder().build())
    }
}