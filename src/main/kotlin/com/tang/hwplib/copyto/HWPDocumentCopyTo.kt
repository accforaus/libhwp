package com.tang.hwplib.copyto

import com.tang.hwplib.copyto.bodytext.copySections
import com.tang.hwplib.copyto.docinfo.HWPDocInfoCopier
import com.tang.hwplib.objects.HWPDocument

internal fun HWPDocumentCopyTo(target: HWPDocument, source: HWPDocument) {
    val docInfo: HWPDocInfoCopier = HWPDocInfoCopier(target, source)
    copySections(target.bodyText.sectionList[0], source.bodyText.sectionList[0], docInfo)
}