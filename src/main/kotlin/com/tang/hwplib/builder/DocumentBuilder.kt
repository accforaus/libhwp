package com.tang.hwplib.builder

import com.tang.hwplib.builder.bodytext.buildEmptyBodyText
import com.tang.hwplib.builder.docinfo.buildEmptyDocInfo
import com.tang.hwplib.builder.fileheader.buildEmptyFileHeader
import com.tang.hwplib.objects.HWPDocument
import com.tang.hwplib.objects.docinfo.HWPDocumentProperties

internal fun buildEmptyHWPDocument() : HWPDocument = HWPDocument().apply {
    this.fileHeader = buildEmptyFileHeader()
    buildEmptyDocInfo(this.docInfo)
    this.bodyText = buildEmptyBodyText()
}