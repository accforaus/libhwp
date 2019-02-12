package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.objects.docinfo.*

internal fun buildEmptyDocInfo(docInfo: HWPDocInfo) {
    docInfo.run {
        documentProperties = buildEmptyDocumentProperties()
        idMappings = buildEmptyIDMappings()
        buildEmptyFaceName(this)
        borderFillList = buildEmptyBorderFill()
        charShapeList = buildEmptyCharShapes()
        tabDefList = buildEmptyTabDef()
        numberingList = buildEmptyNumbering()
        paraShapeList = buildEmptyParaShape()
        styleList = buildEmptyStyle()
        compatibleDocument = buildEmptyCompatibleDocument()
        layoutCompatibility = buildEmptyLayoutCompatibility()
        trackChange = buildEmptyTrackChange()
        forbiddenChar = buildEmptyForbiddenChar()
    }
}