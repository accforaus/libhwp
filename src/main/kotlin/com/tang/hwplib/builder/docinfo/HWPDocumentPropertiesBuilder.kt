package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.docinfo.documentproperties.HWPCaretPositionBuilder
import com.tang.hwplib.builder.docinfo.documentproperties.HWPStartNumberBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPDocumentProperties
import com.tang.hwplib.objects.docinfo.documentproperties.HWPCaretPosition
import com.tang.hwplib.objects.docinfo.documentproperties.HWPStartNumber

class HWPDocumentPropertiesBuilder : HWPBuilder<HWPDocumentProperties> {
        private val properties : HWPDocumentProperties = HWPDocumentProperties.build()

        fun setSectionCount(sectionCount: Int) : HWPDocumentPropertiesBuilder = this.apply {
                properties.sectionCount = sectionCount
        }

        fun setStartNumber(startNumberBuilder: HWPStartNumberBuilder) : HWPDocumentPropertiesBuilder = this.apply {
                properties.startNumber = startNumberBuilder.build()
        }

        fun setCaretPosition(caretPositionBuilder: HWPCaretPositionBuilder) : HWPDocumentPropertiesBuilder = this.apply {
                properties.caretPosition = caretPositionBuilder.build()
        }

        override fun build(): HWPDocumentProperties = properties
}

internal fun buildEmptyDocumentProperties() : HWPDocumentProperties = HWPDocumentProperties.build(
        sectionCount = 1,
        startNumber = HWPStartNumber.build(
                1,1,1,1,1,1
        ),
        caretPosition = HWPCaretPosition.build(
                positionInParagraph = 16
        )
)