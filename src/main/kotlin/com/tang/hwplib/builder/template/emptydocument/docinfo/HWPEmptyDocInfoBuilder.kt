package com.tang.hwplib.builder.template.emptydocument.docinfo

import com.tang.hwplib.builder.docinfo.buildEmptyIDMappings
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.HWPFaceName
import com.tang.hwplib.objects.docinfo.facename.HWPFaceNameEnum

class HWPEmptyDocInfoBuilder : HWPBuilder<HWPDocInfo> {
    override fun build(): HWPDocInfo = HWPDocInfo().apply {
        documentProperties = HWPEmptyDocumentPropertiesBuilder().build()
        idMappings = HWPEmptyIDMappingsBuilder().build()
        HWPEmptyFaceNameBuilder(this).build()
        borderFillList = HWPEmptyBorderFillBuilder().build()
        charShapeList = HWPEmptyCharShapeBuilder().build()
        tabDefList = HWPEmptyTabDefBuilder().build()
        numberingList = HWPEmptyNumberingBuilder().build()
        paraShapeList = HWPEmptyParaShapeBuilder().build()
        styleList = HWPEmptyStyleBuilder().build()
        compatibleDocument = HWPEmptyCompatibleDocumentBuilder().build()
        layoutCompatibility = HWPEmptyLayoutCompatibilityBuilder().build()
        trackChange = HWPEmptyTrackChangeBuilder().build()
        forbiddenChar = HWPEmptyForbiddenCharBuilder().build()
    }
}