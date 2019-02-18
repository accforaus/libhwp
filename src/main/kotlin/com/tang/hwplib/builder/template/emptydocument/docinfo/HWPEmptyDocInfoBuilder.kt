package com.tang.hwplib.builder.template.emptydocument.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.HWPFaceName
import com.tang.hwplib.objects.docinfo.facename.HWPFaceNameEnum

class HWPEmptyDocInfoBuilder : HWPBuilder<HWPDocInfo> {
    override fun build(): HWPDocInfo = HWPDocInfo().apply {
        documentProperties = HWPEmptyDocumentPropertiesBuilder().build()
        HWPEmptyFaceNameBuilder(this).build()
        HWPEmptyBorderFillBuilder().build(this)
        HWPEmptyCharShapeBuilder().build(this)
        HWPEmptyTabDefBuilder().build(this)
        HWPEmptyNumberingBuilder().build(this)
        HWPEmptyParaShapeBuilder().build(this)
        HWPEmptyStyleBuilder().build(this)
        compatibleDocument = HWPEmptyCompatibleDocumentBuilder().build()
        layoutCompatibility = HWPEmptyLayoutCompatibilityBuilder().build()
        trackChange = HWPEmptyTrackChangeBuilder().build()
        forbiddenChar = HWPEmptyForbiddenCharBuilder().build()
    }
}