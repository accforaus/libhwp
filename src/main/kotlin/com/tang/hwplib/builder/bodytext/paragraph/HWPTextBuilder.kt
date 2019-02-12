package com.tang.hwplib.builder.bodytext.paragraph

import com.tang.hwplib.objects.bodytext.control.HWPControlColumnDefine
import com.tang.hwplib.objects.bodytext.control.HWPControlSectionDefine
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.paragraph.text.HWPControlCharType
import com.tang.hwplib.objects.bodytext.paragraph.text.HWPParaText

internal fun buildEmptyParaText() : HWPParaText = HWPParaText().apply {
    addExtendCharByControl(HWPControlSectionDefine())
    addExtendCharByControl(HWPControlColumnDefine())
}