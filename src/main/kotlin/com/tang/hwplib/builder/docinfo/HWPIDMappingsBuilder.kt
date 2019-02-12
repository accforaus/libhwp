package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.objects.docinfo.HWPIDMappings

internal fun buildEmptyIDMappings(): HWPIDMappings = HWPIDMappings.build(
        binDataCount = 0, hangulFaceNameCount = 2, englishFaceNameCount = 2, hanjaFaceNameCount = 2,
        japaneseFaceNameCount = 2, etcFaceNameCount = 2, symbolFaceNameCount = 2, userFaceNameCount = 2,
        borderFillCount = 2, charShapeCount = 7, tabDefCount = 3, bulletCount = 0, paraShapeCount = 16,
        styleCount = 18, memoShapeCount = 0, trackChangeCount = 0, trackChangeAuthorCount = 0)