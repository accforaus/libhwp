package com.tang.hwplib.builder.template.emptydocument.docinfo

import com.tang.hwplib.builder.docinfo.HWPDocumentPropertiesBuilder
import com.tang.hwplib.builder.docinfo.documentproperties.HWPCaretPositionBuilder
import com.tang.hwplib.builder.docinfo.documentproperties.HWPStartNumberBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPDocumentProperties

class HWPEmptyDocumentPropertiesBuilder : HWPBuilder<HWPDocumentProperties> {
    override fun build(): HWPDocumentProperties = HWPDocumentPropertiesBuilder()
            .setSectionCount(1)
            .setStartNumber(HWPStartNumberBuilder()
                    .setPage(1).setFootnote(1).setEndnote(1)
                    .setPicture(1).setTable(1).setEquation(1))
            .setCaretPosition(HWPCaretPositionBuilder()
                    .setPositionInParagraph(16))
            .build()
}