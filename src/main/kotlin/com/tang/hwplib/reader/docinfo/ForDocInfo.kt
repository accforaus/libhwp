package com.tang.hwplib.reader.docinfo

import com.tang.hwplib.objects.docinfo.*
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataType
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderThickness
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.objects.docinfo.borderfill.HWPEachBorder
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPFillInfo
import com.tang.hwplib.objects.docinfo.bullet.HWPImageBullet
import com.tang.hwplib.objects.docinfo.charshape.*
import com.tang.hwplib.objects.docinfo.documentproperties.HWPCaretPosition
import com.tang.hwplib.objects.docinfo.documentproperties.HWPStartNumber
import com.tang.hwplib.objects.docinfo.compatibledocument.HWPCompatibleDocumentSort
import com.tang.hwplib.objects.docinfo.facename.HWPFontType
import com.tang.hwplib.objects.docinfo.facename.HWPFontTypeInfo
import com.tang.hwplib.objects.docinfo.numbering.HWPExtendNumbering
import com.tang.hwplib.objects.docinfo.numbering.HWPLevelNumbering
import com.tang.hwplib.objects.docinfo.numbering.HWPParagraphHeadInfo
import com.tang.hwplib.objects.docinfo.tabdef.HWPTabInfo
import com.tang.hwplib.objects.docinfo.tabdef.HWPTabSort
import com.tang.hwplib.reader.docinfo.borderfill.forFillInfo
import com.tang.hwplib.reader.util.StreamReader
import com.tang.hwplib.objects.etc.*
import com.tang.hwplib.reader.forUnknown
import com.tang.hwplib.util.exceptions.HWPReadException

/**
 * 문서 정보 [HWPDocInfo]를 읽는 함수
 *
 * @author accforaus
 *
 * @param [docInfo] [HWPDocInfo], 빈 문서 정보 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forDocInfo(docInfo: HWPDocInfo, sr: StreamReader) {
    /**
     * 문단 머리 정보 [HWPParagraphHeadInfo]를 읽는 함수
     *
     * @param [phi] [HWPParagraphHeadInfo], 빈 문단 머리 정보 객체
     */
    fun paragraphHeadInfo(phi: HWPParagraphHeadInfo) {
        phi.run {
            property.value = sr.readUInt32()
            correctionValueForWidth = sr.readHwpUnit16()
            distanceFromBody = sr.readHwpUnit16()
            charShapeID = sr.readUInt32()
        }
    }

    /**
     * 바이너리 데이터 [HWPBinData]를 읽는 함수
     * Tag ID: [BIN_DATA]
     *
     * @param [bd] [HWPBinData], 빈 바이너리 데이터 객체
     */
    fun forBinData(bd: HWPBinData) {
        bd.run {
            property.value = sr.readUInt16()
            if (property.getType() == HWPBinDataType.Link) {
                absolutePathForLink = sr.readUTF16LEString()
                relativePathForLink = sr.readUTF16LEString()
            }

            if (property.getType() == HWPBinDataType.Embedding || property.getType() == HWPBinDataType.Storage) {
                binDataID = sr.readUInt16()
                extensionForEmbedding = sr.readUTF16LEString()
            }
        }
    }

    /**
     * 테두리/배경 [HWPBorderFill]을 읽는 함수
     * Tag ID: [BORDER_FILL]
     *
     * @param [bf] [HWPBorderFill], 빈 테두리/배경 객체
     */
    fun forBorderFill(bf: HWPBorderFill) {
        /**
         * 4방향 테두리 [HWPEachBorder]을 읽는 함수
         *
         * @param [eb] [HWPEachBorder], 빈 4방향 테두리 객체
         */
        fun eachBorder(eb: HWPEachBorder) {
            eb.run {
                type = HWPBorderType.valueOf(sr.readUInt8().toByte())
                thickness = HWPBorderThickness.valueOf(sr.readUInt8().toByte())
                color.value = sr.readColorRef()
            }
        }

        /**
         * 대각선을 읽는 함수
         *
         * @param [bf] [HWPBorderFill] 테두리/배경 객체
         */
        fun diagonal(bf: HWPBorderFill) {
            bf.run {
                diagonalSort = HWPBorderType.valueOf(sr.readUInt8().toByte())
                diagonalThickness = HWPBorderThickness.valueOf(sr.readInt8())
                diagonalColor.value = sr.readColorRef()
            }
        }

        /**
         * 채우기 정보 [HWPFillInfo]를 읽는 함수
        */
        fun fillInfo(fi: HWPFillInfo) {
            forFillInfo(fi, sr)
        }

        bf.run {
            property.value = sr.readUInt16()
            eachBorder(leftBorder)
            eachBorder(rightBorder)
            eachBorder(topBorder)
            eachBorder(bottomBorder)
            diagonal(this)
            fillInfo(fillInfo)
        }
    }

    /**
     * 글머리표 [HWPBullet]을 읽는 함수
     * Tag ID: [BULLET]
     *
     * @param [b] [HWPBullet], 빈 글머리표 객체
     */
    fun forBullet(b: HWPBullet) {
        /**
         * 이미지 글머리를 읽는 함수
         *
         * @param [ib] [HWPImageBullet], 빈 이미지 글머리
         */
        fun forImageBullet(ib: HWPImageBullet) {
            ib.run {
                contrast = sr.readByte()
                brightness = sr.readByte()
                effects = sr.readByte()
                id = sr.readByte()
            }
        }

        val unknownBytes = fun (sr: StreamReader) { sr.skipToEndRecord() }
        b.run {
            paragraphHeadInfo(paragraphHeadInfo)
            bulletChar = sr.readWChar()
            imageBulletCheck = sr.readInt32()
            forImageBullet(imageBullet)
            checkBulletChar = sr.readWChar()
            if (!sr.isEndOfRecord())
                unknownBytes(sr)
        }
    }

    /**
     * 글자 모양 [HWPCharShape] 을 읽는 함수
     * Tag ID: [CHAR_SHAPE]
     *
     * @param [cs] [HWPCharShape], 빈 글자 모양 객체
     */
   fun forCharShape(cs: HWPCharShape) {
        /**
         * 언어별 글꼴 [HWPFaceNameIds]를 읽는 함수
         *
         * @param [fnis] [HWPFaceNameIds], 빈 글꼴 객체
         */
        fun faceNameIds(fnis: HWPFaceNameIds) {
            val array: IntArray = IntArray(7)
            for (index in 0 until 7)
                array[index] = sr.readWord()
            fnis.array = array
        }

        /**
         * 언어별 장평 [HWPRatios]를 읽는 함수
         *
         * @param [rs] [HWPRatios], 빈 장평 객체
         */
        fun ratios(rs: HWPRatios) {
            val array: ShortArray = ShortArray(7)
            for (index in 0 until 7)
                array[index] = sr.readUInt8()
            rs.array = array
        }

        /**
         * 언어별 자간 [HWPCharSpaces]을 읽는 함수
         *
         * @param [css] [HWPCharSpaces], 빈 자간 객체
         */
        fun charSpaces(css: HWPCharSpaces) {
            val array: ByteArray = ByteArray(7)
            for (index in 0 until 7)
                array[index] = sr.readInt8()
            css.array = array
        }

        /**
         * 언어별 상대 크기 [HWPRelativeSizes]를 읽는 함수
         *
         * @param [rss] [HWPRelativeSizes], 빈 상대 크기 객체
         */
        fun relativeSizes(rss: HWPRelativeSizes) {
            val array: ShortArray = ShortArray(7)
            for (index in 0 until 7) array[index] = sr.readUInt8()
            rss.array = array
        }

        /**
         * 언어별 상대 위치 [HWPCharOffsets]를 읽는 함수
         *
         * @param [cos] [HWPCharOffsets], 빈 상대 위치 객체
         */
        fun charPositions(cos: HWPCharOffsets) {
            val array: ByteArray = ByteArray(7)
            for (index in 0 until 7) array[index] = sr.readInt8()
            cos.array = array
        }

        cs.run {
            faceNameIds(faceNameIds)
            ratios(ratios)
            charSpaces(charSpaces)
            relativeSizes(relativeSizes)
            charPositions(charOffsets)

            baseSize = sr.readInt32()
            property.value = sr.readUInt32()
            shadowGap1 = sr.readInt8()
            shadowGap2 = sr.readInt8()
            charColor.value = sr.readColorRef()
            underLineColor.value = sr.readColorRef()
            shadeColor.value = sr.readColorRef()
            shadowColor.value = sr.readColorRef()

            if (!sr.isEndOfRecord() && sr.fileVersion!!.isOver(5, 0, 2, 1))
                borderFillId = sr.readUInt16()
            if (!sr.isEndOfRecord() && sr.fileVersion!!.isOver(5,0,3,0))
                strikeLineColor.value = sr.readColorRef()
        }

    }

    /**
     * 호환 문서 [HWPCompatibleDocument]을 읽는 함수
     * Tag ID: [COMPATIBLE_DOCUMENT]
     *
     * @param [cd] [HWPCompatibleDocument], 빈 호환 문서 객체
     */
    fun forCompatibleDocument(cd: HWPCompatibleDocument) {
        cd.compatibleDocumentSort = HWPCompatibleDocumentSort.valueOf(sr.readUInt32().toByte())
    }

    /**
     * 문서 속성 [HWPDocumentProperties]을 읽는 함수
     * Tag ID: [DOCUMENT_PROPERTIES]
     *
     * @param [dp] [HWPDocumentProperties], 빈 문서 속성
     */
    fun forDocumentProperties(dp: HWPDocumentProperties) {
        /**
         * 문서 내 각종 시작번호 [HWPStartNumber]를 읽는 함수
         *
         * @param [sn] [HWPStartNumber], 빈 문서 내 각종 시작번호 객체
         */
        fun startNumber(sn: HWPStartNumber) {
            sn.run {
                page = sr.readUInt16()
                footnote = sr.readUInt16()
                endnote = sr.readUInt16()
                picture = sr.readUInt16()
                table = sr.readUInt16()
                equation = sr.readUInt16()
            }
        }

        /**
         * 문서 내 캐럿의 위치 [HWPCaretPosition]를 읽는 함수
         *
         * @param [cp] [HWPCaretPosition], 빈 문서 내 캐럿의 위치 객체
         */
        fun caretPosition(cp: HWPCaretPosition) {
            cp.run {
                listID = sr.readUInt32()
                paragraphID = sr.readUInt32()
                positionInParagraph = sr.readUInt32()
            }
        }

        dp.run {
            sectionCount = sr.readUInt16()
            startNumber(startNumber)
            caretPosition(caretPosition)
        }
    }

    /**
     * 글꼴 [HWPFaceName]을 읽는 함수
     * Tag ID: [FACE_NAME]
     *
     * @param [fn] [HWPFaceName], 빈 글꼴 객체
     */
    fun forFaceName(fn: HWPFaceName) {
        /**
         * 대체 글골 유향을 읽는 함수
         *
         * @param [fn] [HWPFaceName], 글골 객체
         */
        fun substituteFontInfo(fn: HWPFaceName) {
            fn.run {
                val substituteFontType: HWPFontType = HWPFontType.valueOf(sr.readByte().toByte())
                this.substituteFontType = substituteFontType
                substituteFontName = sr.readUTF16LEString()
            }
        }

        /**
         * 글꼴 유형 정보 [HWPFontTypeInfo]를 읽는 함수
         *
         * @param [fti] [HWPFontTypeInfo], 빈 글꼴 유형 정보
         */
        fun fontTypeInfo(fti: HWPFontTypeInfo) {
            fti.run {
                fontType = sr.readByte()
                serifType = sr.readByte()
                thickness = sr.readByte()
                ratio = sr.readByte()
                contrast = sr.readByte()
                strokeDeviation = sr.readByte()
                characterStrokeType = sr.readByte()
                characterShape = sr.readByte()
                middleLine = sr.readByte()
                xHeight = sr.readByte()
            }
        }

        fn.run {
            property.value = sr.readByte()
            name = sr.readUTF16LEString()

            if (property.hasSubstituteFont())
                substituteFontInfo(this)
            if (property.hasFontInfo())
                fontTypeInfo(fontTypeInfo)
            if (property.hasBaseFont())
                baseFontName = sr.readUTF16LEString()
        }
    }

    /**
     * 아이디 매핑 헤더 [HWPIDMappings]를 읽는 함수
     * Tag ID: [ID_MAPPINGS]
     *
     * @param [idm] [HWPIDMappings], 빈 아이디 매핑 헤더 객체
     */
    fun forIDMappings(idm: HWPIDMappings) {
        idm.run {
            binDataCount = sr.readInt32()
            hangulFaceNameCount = sr.readInt32()
            englishFaceNameCount = sr.readInt32()
            hanjaFaceNameCount = sr.readInt32()
            japaneseFaceNameCount = sr.readInt32()
            etcFaceNameCount = sr.readInt32()
            symbolFaceNameCount = sr.readInt32()
            userFaceNameCount = sr.readInt32()
            borderFillCount = sr.readInt32()
            charShapeCount = sr.readInt32()
            tabDefCount = sr.readInt32()
            numberingCount = sr.readInt32()
            bulletCount = sr.readInt32()
            paraShapeCount = sr.readInt32()
            styleCount = sr.readInt32()
            if (!sr.isEndOfRecord() && sr.fileVersion!!.isOver(5, 0, 2,1))
                memoShapeCount = sr.readInt32()
            if (!sr.isEndOfRecord() && sr.fileVersion!!.isOver(5,0,3,2)) {
                trackChangeCount = sr.readInt32()
                trackChangeAuthorCount = sr.readInt32()
            }
        }
    }

    /**
     * 레이아웃 호환성 [HWPLayoutCompatibility]을 읽는 함수
     * Tag ID: [LAYOUT_COMPATIBILITY]
     *
     * @param [lc] [HWPLayoutCompatibility], 빈 레이아웃 호환성 객체
     */
    fun forLayoutCompatibility(lc: HWPLayoutCompatibility) {
        lc.run {
            letterLevelFormat = sr.readUInt32()
            paragraphLevelFormat = sr.readUInt32()
            sectionLevelFormat = sr.readUInt32()
            objectLevelFormat = sr.readUInt32()
            fieldLevelFormat = sr.readUInt32()
        }
    }

    /**
     * 문단 번호 [HWPNumbering]를 읽는 함수
     * Tag ID: [NUMBERING]
     *
     * @param [n] [HWPNumbering], 빈 문단 번호 객체
     */
    fun forNumbering(n: HWPNumbering) {
        /**
         * 수준 번호 [HWPLevelNumbering]를 읽는 함수
         *
         * @param [ln] [HWPLevelNumbering], 빈 수준 번호 객체
         */
        fun levelNumbering(ln: HWPLevelNumbering) {
            paragraphHeadInfo(ln.paragraphHeadInfo)
            ln.numberFormat = sr.readUTF16LEString()!!
        }

        /**
         * 확장 수준 번호 [HWPExtendNumbering]를 읽는 함수
         *
         * @param [eln] [HWPExtendNumbering], 빈 확장 수준 번호
         */
        fun extendNumbering(eln: HWPExtendNumbering) {
            eln.numberFormat = sr.readUTF16LEString()
        }

        /**
         * 수준별 번호를 읽는 함수
         *
         * @param [n] [HWPNumbering], 문단 번호 객체
         */
        fun levelNumberings(n: HWPNumbering) {
            for (level in 1..7) levelNumbering(n.getLevelNumbering(level))
        }

        /**
         * 확장 수준별 번호를 읽는 함수
         *
         * @param [n] [HWPNumbering], 문단 번호 객체
         */
        fun extendNumberings(n: HWPNumbering) {
            for (level in 8..10) extendNumbering(n.getExtendStartLevelNumbering(level))
        }

        /**
         * 수준별 번호를 읽는 함수
         *
         * @param [n] [HWPNumbering],  문단 번호 객체
         */
        fun startNumberForLevels(n: HWPNumbering) {
            for (level in 1..7)
                n.setStartNumberForLevel(sr.readUInt32(), level)
        }

        /**
         * 확장 수준별 시작번호를 읽는 함수
         *
         * @param [n] [HWPNumbering], 문단 번호 객체
         */
        fun extendStartNumberForLevels(n: HWPNumbering) {
            for (level in 8..10)
                n.setExtendStartNumberForLevel(sr.readUInt32(), level)
        }

        n.run {
            levelNumberings(this)
            startNumber = sr.readUInt16()
            if (!sr.isEndOfRecord() && sr.fileVersion!!.isOver(5, 0, 2, 5))
                startNumberForLevels(this)
            if (!sr.isEndOfRecord())
                extendNumberings(this)
            if (!sr.isEndOfRecord() && sr.fileVersion!!.isOver(5,1,0,0))
                extendStartNumberForLevels(this)
        }
    }

    /**
     * 문단 모양 [HWPParaShape]을 읽는 함수
     * Tag ID: [PARA_SHAPE]
     *
     * @param [ps] [HWPParaShape], 빈 문단 모양 객체
     */
    fun forParaShape(ps: HWPParaShape) {
        ps.run {
            property1.value = sr.readUInt32()
            leftMargin = sr.readInt32()
            rightMargin = sr.readInt32()
            indent = sr.readInt32()
            topParaSpace = sr.readInt32()
            bottomParaSpace = sr.readInt32()
            lineSpace = sr.readInt32()
            tabDefId = sr.readUInt16()
            paraHeadId = sr.readUInt16()
            borderFillId = sr.readUInt16()
            leftBorderSpace = sr.readInt16()
            rightBorderSpace = sr.readInt16()
            topBorderSpace = sr.readInt16()
            bottomBorderSpace = sr.readInt16()
            if (!sr.isEndOfRecord() && sr.fileVersion!!.isOver(5, 0, 1,7))
                property2.value = sr.readUInt32()
            if (!sr.isEndOfRecord() && sr.fileVersion!!.isOver(5,0,2,5)) {
                property3.value = sr.readUInt32()
                lineSpace2 = sr.readUInt32()
            }
            if (!sr.isEndOfRecord())
                ps.unknown = sr.readUInt32()
        }
    }

    /**
     * 스타일 [HWPStyle]을 읽는 함수
     * Tag ID: [STYLE]
     *
     * @param [s] [HWPStyle], 빈 스타일 객체
     */
    fun forStyle(s: HWPStyle) {
        s.run {
            hangulName = sr.readUTF16LEString()
            englishName = sr.readUTF16LEString()
            property.value = sr.readByte()
            nextStyleId = sr.readByte()
            languageId = sr.readInt16()
            paraShapeId = sr.readUInt16()
            charShapeId = sr.readUInt16()
        }
        sr.skip(2)
    }

    /**
     * 탭 정의 [HWPTabDef]를 읽는 함수
     * Tag ID: [TAB_DEF]
     *
     * @param [td] [HWPTabDef], 빈 탭 정의 객체
     */
    fun forTabDef(td: HWPTabDef) {
        /**
         * 탭 정의 속성 [HWPTabInfo]을 읽는 함수
         *
         * @param [td] [HWPTabDef], 탭 정의 객체
         * @param [tabInfoCount] [Int], 탭 정의 속성 개수
         */
        fun tabInfos(td: HWPTabDef, tabInfoCount: Int) {
            for (index in 0 until tabInfoCount)
                td.addNewTabInfo().apply {
                    position = sr.readHwpUnit()
                    tabSort = HWPTabSort.valueOf(sr.readUInt8().toByte())
                    fillSort = HWPBorderType.valueOf(sr.readUInt8().toByte())
                    sr.skip(2)
                }
        }
        td.property.value = sr.readUInt32()
        val tabInfoCount: Int = sr.readInt32()
        if (tabInfoCount > 0)
            tabInfos(td, tabInfoCount)
    }

    /**
     * [HWPIDMappings]값에 의해 [HWPFaceName]을 추가하는 함수
     *
     * @param [fn] [HWPFaceName], 추가될 글골
     */
    fun addFaceNameByIDMappings(fn: HWPFaceName) {
        val idm: HWPIDMappings = docInfo.idMappings
        if (docInfo.hangulFaceNameList.size < idm.hangulFaceNameCount)
            docInfo.hangulFaceNameList.add(fn)
        else if (docInfo.englishFaceNameList.size < idm.englishFaceNameCount)
            docInfo.englishFaceNameList.add(fn)
        else if (docInfo.hanjaFaceNameList.size < idm.hanjaFaceNameCount)
            docInfo.hanjaFaceNameList.add(fn)
        else if (docInfo.japaneseFaceNameList.size < idm.japaneseFaceNameCount)
            docInfo.japaneseFaceNameList.add(fn)
        else if (docInfo.etcFaceNameList.size < idm.etcFaceNameCount)
            docInfo.etcFaceNameList.add(fn)
        else if (docInfo.symbolFaceNameList.size < idm.symbolFaceNameCount)
            docInfo.symbolFaceNameList.add(fn)
        else if (docInfo.userFaceNameList.size < idm.userFaceNameCount)
            docInfo.userFaceNameList.add(fn)
        else
            throw HWPReadException("Count of HWPFaceName is greater than ID Mappings")
    }

    while (!sr.isEndOfStream()) {
        sr.readRecordHeader()
        when(sr.header.tagId) {
            DOCUMENT_PROPERTIES -> forDocumentProperties(docInfo.documentProperties)
            ID_MAPPINGS -> forIDMappings(docInfo.idMappings)
            BIN_DATA -> docInfo.addNewBinData().apply { forBinData(this) }
            FACE_NAME -> HWPFaceName().apply {
                forFaceName(this)
                addFaceNameByIDMappings(this)
            }
            BORDER_FILL -> docInfo.addNewBorderFill().apply { forBorderFill(this) }
            CHAR_SHAPE -> docInfo.addNewCharShape().apply { forCharShape(this) }
            TAB_DEF -> docInfo.addNewTabDef().apply { forTabDef(this) }
            NUMBERING -> docInfo.addNewNumbering().apply { forNumbering(this) }
            BULLET -> docInfo.addNewBullet().apply { forBullet(this) }
            PARA_SHAPE -> docInfo.addNewParaShape().apply { forParaShape(this) }
            STYLE -> docInfo.addNewStyle().apply { forStyle(this) }
            DOC_DATA -> {
                docInfo.createDocData(sr.header)
                forUnknown(docInfo.docData!!, sr)
            }
            FORBIDDEN_CHAR -> {
                docInfo.createForbiddenChar(sr.header)
                forUnknown(docInfo.forbiddenChar!!, sr)
            }
            DISTRIBUTE_DOC_DATA -> {
                docInfo.createDistributeDocData(sr.header)
                forUnknown(docInfo.distributeDocData!!, sr)
            }
            COMPATIBLE_DOCUMENT -> {
                docInfo.createCompatibleDocument()
                forCompatibleDocument(docInfo.compatibleDocument!!)
            }
            LAYOUT_COMPATIBILITY -> {
                docInfo.createLayoutCompatibility()
                forLayoutCompatibility(docInfo.layoutCompatibility!!)
            }
            TRACKCHANGE -> {
                docInfo.createTrackChange(sr.header)
                forUnknown(docInfo.trackChange!!, sr)
            }
            MEMO_SHAPE -> docInfo.addNewMemoShape(sr.header).apply { forUnknown(this, sr) }
            TRACK_CHANGE -> docInfo.addNewTrackChange2(sr.header).apply { forUnknown(this, sr) }
            TRACK_CHANGE_AUTHOR -> docInfo.addNewTrackChangeAuthor2(sr.header).apply { forUnknown(this, sr) }
        }
    }
}
