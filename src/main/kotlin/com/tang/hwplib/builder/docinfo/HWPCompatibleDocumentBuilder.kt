package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPCompatibleDocument
import com.tang.hwplib.objects.docinfo.compatibledocument.HWPCompatibleDocumentSort

class HWPCompatibleDocumentBuilder : HWPDocInfoBuilder() {
    private val compatible : HWPCompatibleDocument = HWPCompatibleDocument.build()

    fun setCompatibleDocumentSort(compatibleDocumentSort: HWPCompatibleDocumentSort) : HWPCompatibleDocumentBuilder = this.apply {
        compatible.compatibleDocumentSort = compatibleDocumentSort
    }

    override fun build(): HWPCompatibleDocument = compatible
}