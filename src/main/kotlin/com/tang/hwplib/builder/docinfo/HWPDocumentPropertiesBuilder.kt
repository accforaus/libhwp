package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.objects.docinfo.HWPDocumentProperties
import com.tang.hwplib.objects.docinfo.documentproperties.HWPCaretPosition
import com.tang.hwplib.objects.docinfo.documentproperties.HWPStartNumber

internal fun buildEmptyDocumentProperties() : HWPDocumentProperties = HWPDocumentProperties.build(
        sectionCount = 1,
        startNumber = HWPStartNumber.build(
                1,1,1,1,1,1
        ),
        caretPosition = HWPCaretPosition.build(
                positionInParagraph = 16
        )
)