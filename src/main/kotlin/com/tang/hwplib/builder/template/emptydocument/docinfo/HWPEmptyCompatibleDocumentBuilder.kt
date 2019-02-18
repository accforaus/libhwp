package com.tang.hwplib.builder.template.emptydocument.docinfo

import com.tang.hwplib.builder.docinfo.HWPCompatibleDocumentBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPCompatibleDocument
import com.tang.hwplib.objects.docinfo.compatibledocument.HWPCompatibleDocumentSort

class HWPEmptyCompatibleDocumentBuilder : HWPBuilder<HWPCompatibleDocument> {
    private fun buildCompatible() : HWPCompatibleDocumentBuilder = HWPCompatibleDocumentBuilder()
            .setCompatibleDocumentSort(HWPCompatibleDocumentSort.HWPCurrent)

    override fun build(): HWPCompatibleDocument = buildCompatible().build()
}