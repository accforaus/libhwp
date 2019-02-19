package com.tang.hwplib.builder.template.emptydocument

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.template.emptydocument.bodytext.HWPEmptyBodyTextBuilder
import com.tang.hwplib.builder.template.emptydocument.docinfo.HWPEmptyDocInfoBuilder
import com.tang.hwplib.builder.template.emptydocument.fileheader.HWPEmptyFileHeaderBuilder
import com.tang.hwplib.objects.HWPDocument

class HWPEmptyDocumentTemplate : HWPBuilder<HWPDocument> {
    override fun build(): HWPDocument = HWPDocument().apply {
        fileHeader = HWPEmptyFileHeaderBuilder().build()
        docInfo = HWPEmptyDocInfoBuilder().build()
        bodyText = HWPEmptyBodyTextBuilder(docInfo).build()
    }
}