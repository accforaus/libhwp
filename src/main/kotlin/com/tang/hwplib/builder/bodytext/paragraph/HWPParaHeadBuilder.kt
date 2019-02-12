package com.tang.hwplib.builder.bodytext.paragraph

import com.tang.hwplib.objects.bodytext.paragraph.header.HWPControlMask
import com.tang.hwplib.objects.bodytext.paragraph.header.HWPDivideSort
import com.tang.hwplib.objects.bodytext.paragraph.header.HWPParaHeader

internal fun buildEmptyParaHeader() : HWPParaHeader = HWPParaHeader.build(
        lastInList = true, characterCount = 17,
        controlMask = HWPControlMask.build(4),
        paraShapeId = 3, divideSort = HWPDivideSort.build(3),
        charShapeCount = 1, lineAlignCount = 1
)