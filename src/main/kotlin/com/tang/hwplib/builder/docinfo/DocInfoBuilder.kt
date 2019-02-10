package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.HWPFaceName
import com.tang.hwplib.objects.docinfo.facename.HWPFaceNameEnum

internal fun buildDocInfo() : HWPDocInfo = HWPDocInfo().apply {
    fun addFaceNames() {
        fun addDotum(faceName: HWPFaceName) {
            faceName.run {
                property.value = 33
                name = "함초롬돋움"
                baseFontName = "HCR Dotum"
            }
        }
        fun addBatang(faceName: HWPFaceName) {
            faceName.run {
                property.value = 33
                name = "함초롬바탕"
                baseFontName = "HCR Batang"
            }
        }

        for (index in 0..1) {
            addDotum(addNewFaceName(HWPFaceNameEnum.HANGUL))
            addBatang(addNewFaceName(HWPFaceNameEnum.HANGUL))
            addDotum(addNewFaceName(HWPFaceNameEnum.LATIN))
            addBatang(addNewFaceName(HWPFaceNameEnum.LATIN))
            addDotum(addNewFaceName(HWPFaceNameEnum.HANJA))
            addBatang(addNewFaceName(HWPFaceNameEnum.HANJA))
            addDotum(addNewFaceName(HWPFaceNameEnum.JAPANESE))
            addBatang(addNewFaceName(HWPFaceNameEnum.JAPANESE))
            addDotum(addNewFaceName(HWPFaceNameEnum.ETC))
            addBatang(addNewFaceName(HWPFaceNameEnum.ETC))
            addDotum(addNewFaceName(HWPFaceNameEnum.SYMBOL))
            addBatang(addNewFaceName(HWPFaceNameEnum.SYMBOL))
            addDotum(addNewFaceName(HWPFaceNameEnum.USER))
            addBatang(addNewFaceName(HWPFaceNameEnum.USER))
        }
    }

    documentProperties.run {
        sectionCount = 1
        startNumber.run {
            page = 1
            footnote = 1
            endnote = 1
            picture = 1
            table = 1
            equation = 1
        }
        caretPosition.run {
            listID = 0
            paragraphID = 0
            positionInParagraph = 16
        }
    }

    idMappings.run {
        binDataCount = 0
        hangulFaceNameCount = 2
        englishFaceNameCount = 2
        hanjaFaceNameCount = 2
        japaneseFaceNameCount = 2
        etcFaceNameCount = 2
        symbolFaceNameCount = 2
        userFaceNameCount = 2
        borderFillCount = 2
        charShapeCount = 7
        tabDefCount = 3
        bulletCount = 0
        paraShapeCount = 16
        styleCount = 18
        memoShapeCount = 0
        trackChangeCount = 0
        trackChangeAuthorCount = 0
    }

    addFaceNames()


}