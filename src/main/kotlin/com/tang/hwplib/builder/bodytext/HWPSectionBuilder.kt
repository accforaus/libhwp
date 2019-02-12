package com.tang.hwplib.builder.bodytext

import com.tang.hwplib.builder.bodytext.paragraph.buildEmptyParagraph
import com.tang.hwplib.objects.bodytext.HWPSection

internal fun buildEmptySection() : HWPSection = HWPSection().apply {
    paragraphList.add(buildEmptyParagraph())
}