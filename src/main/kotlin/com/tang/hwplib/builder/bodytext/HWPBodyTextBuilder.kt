package com.tang.hwplib.builder.bodytext

import com.tang.hwplib.objects.bodytext.HWPBodyText

internal fun buildEmptyBodyText() : HWPBodyText = HWPBodyText().apply {
    sectionList.add(buildEmptySection())
}