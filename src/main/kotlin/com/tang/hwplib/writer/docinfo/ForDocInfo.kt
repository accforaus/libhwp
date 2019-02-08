package com.tang.hwplib.writer.docinfo

import com.tang.hwplib.objects.docinfo.*
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataType
import com.tang.hwplib.objects.docinfo.borderfill.HWPEachBorder
import com.tang.hwplib.objects.docinfo.bullet.HWPImageBullet
import com.tang.hwplib.objects.docinfo.numbering.HWPParagraphHeadInfo
import com.tang.hwplib.objects.etc.*
import com.tang.hwplib.util.string.getUTF16LEStringSize
import com.tang.hwplib.writer.util.StreamWriter
import com.tang.hwplib.writer.docinfo.borderfill.forFillInfo
import com.tang.hwplib.writer.docinfo.borderfill.getFillInfoSize
import com.tang.hwplib.writer.forUnknown

/**
 * 문단 머리 정보 [HWPParagraphHeadInfo]를 쓰는 함수
 *
 * @author accforaus
 */
private fun forParagraphHeadInfo(phi: HWPParagraphHeadInfo, sw: StreamWriter) {
    phi.run {
        sw.writeUInt32(property.value)
        sw.writeInt16(correctionValueForWidth)
        sw.writeInt16(distanceFromBody)
        sw.writeUInt32(charShapeID)
    }
}

/**
 * 문서 정보 [HWPDocInfo]를 쓰는 함수
 *
 * @author accforaus
 *
 * @param [docInfo] [HWPDocInfo], 문서 정보 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forDocInfo(docInfo: HWPDocInfo, sw: StreamWriter) {
    /**
     * 바이너리 데이터 [HWPBinData]를 쓰는 함수
     * Tag ID: [BIN_DATA]
     *
     * @param [bd] [HWPBinData], 바이너리 데이터 객체
     */
    fun forBinData(bd: HWPBinData) {
        /**
         * 바이너리 데이터의 전체 길이를 반환하는 함수
         *
         * @return [Int] 바이너리 데이터의 전체 길이 반환
         */
        fun getSize(): Int {
            var size: Int = 0
            size += 2
            when (bd.property.getType()) {
                HWPBinDataType.Link -> {
                    size += getUTF16LEStringSize(bd.absolutePathForLink)
                    size += getUTF16LEStringSize(bd.relativePathForLink)
                }
                else -> {
                    size += 2
                    size += getUTF16LEStringSize(bd.extensionForEmbedding)
                }
            }
            return size
        }
        sw.writeRecordHeader(BIN_DATA.toInt(), getSize())
        bd.run {
            sw.writeUInt16(property.value)
            when (this.property.getType()) {
                HWPBinDataType.Link -> {
                    sw.writeUTF16LEString(absolutePathForLink)
                    sw.writeUTF16LEString(relativePathForLink)
                }
                else -> {
                    sw.writeUInt16(binDataID)
                    sw.writeUTF16LEString(extensionForEmbedding)
                }
            }
        }
    }

    /**
     * 테두리/배경 [HWPBorderFill]을 쓰는 함수
     * Tag ID: [BORDER_FILL]
     *
     * @param [bf] [HWPBorderFill], 테두리/배경 객체
     */
    fun forBorderFill(bf: HWPBorderFill) {
        /**
         * 4방향 테두리를 쓰는 함수
         *
         * @param [eb] [HWPEachBorder], 4방향 테두리 객체
         */
        fun eachBorder(eb: HWPEachBorder) {
            eb.run {
                sw.writeUInt8(type.value.toShort())
                sw.writeUInt8(thickness.value.toShort())
                sw.writeColorRef(color.value)
            }
        }

        /**
         * 테두리/배경 전체 길이를 반환하는 함수
         *
         * @return [Int] 테두리/배경 전체 길이 반환
         */
        fun getSize() : Int {
            var size: Int = 0
            size += 2
            size += (1 + 1 + 4) * 5
            size += getFillInfoSize(bf.fillInfo)
            return size
        }

        bf.run {
            sw.writeRecordHeader(BORDER_FILL.toInt(), getSize())
            sw.writeUInt16(property.value)
            eachBorder(leftBorder)
            eachBorder(rightBorder)
            eachBorder(topBorder)
            eachBorder(bottomBorder)
            sw.writeUInt8(diagonalSort.value.toShort())
            sw.writeUInt8(diagonalThickness.value.toShort())
            sw.writeColorRef(diagonalColor.value)
            forFillInfo(fillInfo, sw)
        }
    }

    /**
     * 글머리표 [HWPBullet]를 쓰는 함수
     * Tag ID: [BULLET]
     *
     * @param [b] [HWPBullet], 글머리표 객체
     */
    fun forBullet(b: HWPBullet) {
        /**
         * 글머리표 전체 길이를 반환하는 함수
         *
         * @return [Int] 글머리표 전체 길이 반환
         */
        fun getSize(): Int {
            var size: Int = 0
            size += 12
            size += 2
            size += 4
            size += 4
            size += 2
            return size
        }

        /**
         * 이미지 글머리 [HWPImageBullet]를 쓰는 함수
         *
         * @param [ib] [HWPImageBullet], 이미지 글머리 객체
         */
        fun forImageBullet(ib: HWPImageBullet) {
            sw.writeByte(ib.contrast)
            sw.writeByte(ib.brightness)
            sw.writeByte(ib.effects)
            sw.writeByte(ib.id)
        }
        b.run {
            sw.writeRecordHeader(BULLET.toInt(), getSize())
            forParagraphHeadInfo(paragraphHeadInfo, sw)
            sw.writeWChar(bulletChar)
            sw.writeInt32(imageBulletCheck)
            forImageBullet(imageBullet)
            sw.writeWChar(checkBulletChar)
        }
    }

    /**
     * 글자 모양 [HWPCharShape]을 쓰는 함수
     * Tag ID: [CHAR_SHAPE]
     *
     * @param [cs] [HWPCharShape], 글자 모양 객체
     */
    fun forCharShape(cs: HWPCharShape) {
        /**
         * 글자 모양 전체 길이를 반환하는 함수
         *
         * @return [Int] 글자 모양 전체 길이 반환
         */
        fun getSize(): Int {
            var size: Int = 0
            size += 14 + 7 + 7 + 7 + 7
            size += 26
            if (sw.version.isOver(5,0,2,1)) size += 2
            if (sw.version.isOver(5,0,3,0)) size += 4
            return size
        }

        cs.run {
            sw.writeRecordHeader(CHAR_SHAPE.toInt(), getSize())
            for (faceNameID in faceNameIds.array) sw.writeWord(faceNameID)
            for (ratio in ratios.array) sw.writeUInt8(ratio)
            for (charSpace in charSpaces.array) sw.writeInt8(charSpace)
            for (rs in relativeSizes.array) sw.writeUInt8(rs)
            for (co in charOffsets.array) sw.writeInt8(co)

            sw.writeInt32(baseSize)
            sw.writeUInt32(property.value)
            sw.writeInt8(shadowGap1)
            sw.writeInt8(shadowGap2)
            sw.writeColorRef(charColor.value)
            sw.writeColorRef(underLineColor.value)
            sw.writeColorRef(shadeColor.value)
            sw.writeColorRef(shadowColor.value)

            if (sw.version.isOver(5, 0, 2, 1)) sw.writeUInt16(borderFillId)
            if (sw.version.isOver(5,0,3,0)) sw.writeColorRef(strikeLineColor.value)
        }
    }

    /**
     * 호환 문서 [HWPCompatibleDocument]를 쓰는 함수
     * Tag ID: [COMPATIBLE_DOCUMENT]
     *
     * @param [cd] [HWPCompatibleDocument], 호환 문서 객체
     */
    fun forCompatibleDocument(cd: HWPCompatibleDocument) {
        sw.writeRecordHeader(COMPATIBLE_DOCUMENT.toInt(), 4)
        sw.writeUInt32(cd.compatibleDocumentSort!!.value.toLong())
    }

    /**
     * 문단 속성 [HWPDocumentProperties]을 쓰는 함수
     * Tag ID: [DOCUMENT_PROPERTIES]
     *
     * @param [dp] [HWPDocumentProperties], 문단 속성 객체
     */
    fun forDocumentProperties(dp: HWPDocumentProperties) {
        sw.writeRecordHeader(DOCUMENT_PROPERTIES.toInt(), 26)
        dp.run {
            sw.writeUInt16(sectionCount)

            startNumber.let {
                sw.writeUInt16(it.page)
                sw.writeUInt16(it.footnote)
                sw.writeUInt16(it.endnote)
                sw.writeUInt16(it.picture)
                sw.writeUInt16(it.table)
                sw.writeUInt16(it.equation)
            }

            caretPosition.let {
                sw.writeUInt32(it.listID)
                sw.writeUInt32(it.paragraphID)
                sw.writeUInt32(it.positionInParagraph)
            }
        }
    }

    /**
     * 글꼴 [HWPFaceName]을 쓰는 함수
     * Tag ID: [FACE_NAME]
     *
     * @param [fn] [HWPFaceName], 글꼴 객체
     */
    fun forFaceName(fn: HWPFaceName) {
        /**
         * 글꼴의 전체 길이를 반환하는 함수
         *
         * @return [Int] 글꼴의 전체 길이 반환
         */
        fun getSize(): Int {
            var size: Int = 0
            size += 1

            size += getUTF16LEStringSize(fn.name)
            if (fn.property.hasSubstituteFont()) {
                size += 1
                size += getUTF16LEStringSize(fn.substituteFontName)
            }
            if (fn.property.hasFontInfo()) size += 10
            if (fn.property.hasBaseFont()) size += getUTF16LEStringSize(fn.baseFontName)
            return size
        }

        fn.run {
            sw.writeRecordHeader(FACE_NAME.toInt(), getSize())
            sw.writeByte(property.value)
            sw.writeUTF16LEString(name)
            if (property.hasSubstituteFont()) {
                sw.writeByte(substituteFontType!!.value.toShort())
                sw.writeUTF16LEString(substituteFontName)
            }
            if (property.hasFontInfo()) {
                fontTypeInfo.let {
                    sw.writeByte(it.fontType)
                    sw.writeByte(it.serifType)
                    sw.writeByte(it.thickness)
                    sw.writeByte(it.ratio)
                    sw.writeByte(it.contrast)
                    sw.writeByte(it.strokeDeviation)
                    sw.writeByte(it.characterStrokeType)
                    sw.writeByte(it.characterShape)
                    sw.writeByte(it.middleLine)
                    sw.writeByte(it.xHeight)
                }
            }
            if (property.hasBaseFont()) sw.writeUTF16LEString(baseFontName)
        }

    }

    /**
     * 아이디 매핑 헤더 [HWPIDMappings]를 쓰는 함수
     * Tag ID: [ID_MAPPINGS]
     *
     * @param [im] [HWPIDMappings], 아이디 매핑 헤더 객체
     */
    fun forIDMapping(im: HWPIDMappings) {
        /**
         * 아이디 매핑 헤더의 길이를 반환하는 함수
         *
         * @return [Int] 아이디 매핑 헤더의 전체 길이 반환
         */
        fun getSize(): Int {
            if (sw.version.isOver(5,0,3,2)) return 72
            else if (sw.version.isOver(5,0,2,1)) return 64
            else return 60
        }

        sw.writeRecordHeader(ID_MAPPINGS.toInt(), getSize())

        im.run {
            sw.writeInt32(binDataCount)
            sw.writeInt32(hangulFaceNameCount)
            sw.writeInt32(englishFaceNameCount)
            sw.writeInt32(hanjaFaceNameCount)
            sw.writeInt32(japaneseFaceNameCount)
            sw.writeInt32(etcFaceNameCount)
            sw.writeInt32(symbolFaceNameCount)
            sw.writeInt32(userFaceNameCount)
            sw.writeInt32(borderFillCount)
            sw.writeInt32(charShapeCount)
            sw.writeInt32(tabDefCount)
            sw.writeInt32(numberingCount)
            sw.writeInt32(bulletCount)
            sw.writeInt32(paraShapeCount)
            sw.writeInt32(styleCount)
            if (sw.version.isOver(5,0,2,1))
                sw.writeInt32(memoShapeCount)
            if (sw.version.isOver(5,0,3,2)) {
                sw.writeInt32(trackChangeCount)
                sw.writeInt32(trackChangeAuthorCount)
            }
        }
    }

    /**
     * 레이아웃 호환성 [HWPLayoutCompatibility]을 쓰는 함수
     * Tag ID: [LAYOUT_COMPATIBILITY]
     *
     * @param [lc] [HWPLayoutCompatibility], 레이아웃 호환성 객체
     */
    fun forLayoutCompatibility(lc: HWPLayoutCompatibility) {
        sw.writeRecordHeader(LAYOUT_COMPATIBILITY.toInt(), 20)
        lc.run {
            sw.writeUInt32(letterLevelFormat)
            sw.writeUInt32(paragraphLevelFormat)
            sw.writeUInt32(sectionLevelFormat)
            sw.writeUInt32(objectLevelFormat)
            sw.writeUInt32(fieldLevelFormat)
        }
    }

    /**
     * 문단 번호 [HWPNumbering]를 쓰는 함수
     * Tag ID: [NUMBERING]
     *
     * @param [n] [HWPNumbering], 문단 번호 객체
     */
    fun forNumbering(n: HWPNumbering) {
        /**
         * 문단 번호 전체 길이를 반환하는 함수
         *
         * @return [Int] 문단 번호 전체 길이 반환
         */
        fun getSize(): Int {
            var size: Int = 0
            for (level in 1..7) {
                size += 12 + getUTF16LEStringSize(n.getLevelNumbering(level).numberFormat)
            }
            size += 2
            if (sw.version.isOver(5,0,2,5)) size += 4 * 7
            for (level in 8..10) {
                size += getUTF16LEStringSize(n.getExtendStartLevelNumbering(level).numberFormat)
            }
            if (sw.version.isOver(5, 1,0,0)) size += 4 * 7
            return size
        }

        sw.writeRecordHeader(NUMBERING.toInt(), getSize())
        n.run {
            for (level in 1..7) {
                getLevelNumbering(level).let {
                    forParagraphHeadInfo(it.paragraphHeadInfo, sw)
                    sw.writeUTF16LEString(it.numberFormat)
                }
            }
            sw.writeUInt16(startNumber)
            if (sw.version.isOver(5,0,2,5)) {
                for (level in 1..7) sw.writeUInt32(getStartNumberForLevel(level))
            }
            for (level in 8..10) {
                getExtendStartLevelNumbering(level).let {
                    sw.writeUTF16LEString(it.numberFormat)
                }
            }
            if (sw.version.isOver(5,1,0,0)) {
                for (level in 8..10) sw.writeUInt32(getExtendStartNumberForLevel(level))
            }
        }
    }

    /**
     * 문단 모양 [HWPParaShape]을 쓰는 함수
     * Tag ID: [PARA_SHAPE]
     *
     * @param [ps] [HWPParaShape], 문단 모양 객체
     */
    fun forParaShape(ps: HWPParaShape) {
        /**
         * 문단 모양 전체 길이를 반환하는 함수
         *
         * @return [Int] 문단 모양 전체 길이 반환
         */
        fun getSize(): Int {
            var size: Int = 0
            size += 42
            if (sw.version.isOver(5,0,1,7)) size += 4
            if (sw.version.isOver(5,0,2,5)) size += 8
            if (sw.version.isOver(5,0,255,255)) size += 4
            return size
        }

        sw.writeRecordHeader(PARA_SHAPE.toInt(), getSize())

        ps.run {
            sw.writeUInt32(property1.value)
            sw.writeInt32(leftMargin)
            sw.writeInt32(rightMargin)
            sw.writeInt32(indent)
            sw.writeInt32(topParaSpace)
            sw.writeInt32(bottomParaSpace)
            sw.writeInt32(lineSpace)
            sw.writeUInt16(tabDefId)
            sw.writeUInt16(paraHeadId)
            sw.writeUInt16(borderFillId)
            sw.writeInt16(leftBorderSpace)
            sw.writeInt16(rightBorderSpace)
            sw.writeInt16(topBorderSpace)
            sw.writeInt16(bottomBorderSpace)
            if (sw.version.isOver(5,0,1,7)) sw.writeUInt32(property2.value)
            if (sw.version.isOver(5,0,2,5)) {
                sw.writeUInt32(property3.value)
                sw.writeUInt32(lineSpace2)
            }
            if (sw.version.isOver(5,0,255,255)) sw.writeUInt32(unknown)
        }
    }

    /**
     * 스타일 [HWPStyle]을 쓰는 함수
     * Tag ID: [STYLE]
     *
     * @param [s] [HWPStyle], 스타일 객체
     */
    fun forStyle(s: HWPStyle) {
        /**
         * 스타일 전체 길이를 반환하는 함수
         *
         * @return [Int] 스타일 전체 길이 반환
         */
        fun getSize(): Int {
            var size: Int = 0
            size += getUTF16LEStringSize(s.hangulName)
            size += getUTF16LEStringSize(s.englishName)
            size += 8 + 2
            return size
        }

        sw.writeRecordHeader(STYLE.toInt(), getSize())

        s.run {
            sw.writeUTF16LEString(hangulName)
            sw.writeUTF16LEString(englishName)
            sw.writeByte(property.value)
            sw.writeByte(nextStyleId)
            sw.writeUInt16(languageId.toInt())
            sw.writeUInt16(paraShapeId)
            sw.writeUInt16(charShapeId)
            sw.writeZero(2)
        }
    }

    /**
     * 탭 정의 [HWPTabDef]를 쓰는 함수
     * Tag ID: [TAB_DEF]
     *
     * @param [td] [HWPTabDef] 탭 정의 객체
     */
    fun forTabDef(td: HWPTabDef) {
        /**
         * 탭 정의 전체 길이를 반환한느 함수
         *
         * @return [Int] 탭 정의 전체 길이 반환
         */
        fun getSize(): Int {
            var size: Int = 0
            size += 8
            size += 8 * td.tabInfoList.size
            return size
        }

        sw.writeRecordHeader(TAB_DEF.toInt(), getSize())
        td.run {
            sw.writeUInt32(property.value)
            val tabInfoCount: Int = tabInfoList.size
            sw.writeUInt32(tabInfoCount.toLong())
            if (tabInfoCount > 0) {
                for (ti in tabInfoList) {
                    sw.writeHwpUnit(ti.position)
                    sw.writeUInt8(ti.tabSort!!.value.toShort())
                    sw.writeUInt8(ti.fillSort!!.value.toShort())
                    sw.writeZero(2)
                }
            }
        }
    }

    /**
     * 글꼴 [HWPFaceName] 별로 스트림에 저장하는 함수
     *
     * @param [faceNameList] [ArrayList], 글꼴 리스트
     */
    fun faceNameList(faceNameList: ArrayList<HWPFaceName>) {
        for (fa in faceNameList)
            forFaceName(fa)
    }

    docInfo.run {
        val dp: HWPDocumentProperties? = documentProperties
        if (dp != null) forDocumentProperties(dp)

        val im: HWPIDMappings? = idMappings
        if (im != null) forIDMapping(im)

        sw.upRecordLevel()

        for (bd in binDataList) forBinData(bd)

        faceNameList(hangulFaceNameList)
        faceNameList(englishFaceNameList)
        faceNameList(hanjaFaceNameList)
        faceNameList(japaneseFaceNameList)
        faceNameList(etcFaceNameList)
        faceNameList(symbolFaceNameList)
        faceNameList(userFaceNameList)

        for (bf in borderFillList) forBorderFill(bf)

        for (cs in charShapeList) forCharShape(cs)

        for (td in tabDefList) forTabDef(td)

        for (n in numberingList) forNumbering(n)

        for (b in bulletList) forBullet(b)

        for (ps in paraShapeList) forParaShape(ps)

        for (s in styleList) forStyle(s)

        sw.downRecordLevel()

        if (docData != null) forUnknown(docData!!, DOC_DATA.toInt(), sw)

        sw.upRecordLevel()

        if (forbiddenChar != null) forUnknown(forbiddenChar!!, FORBIDDEN_CHAR.toInt(), sw)

        sw.downRecordLevel()

        if (distributeDocData != null) forUnknown(distributeDocData!!, DISTRIBUTE_DOC_DATA.toInt(), sw)

        if (compatibleDocument != null) forCompatibleDocument(compatibleDocument!!)

        sw.upRecordLevel()

        if (layoutCompatibility != null) forLayoutCompatibility(layoutCompatibility!!)

        if (trackChange != null) forUnknown(trackChange!!, TRACKCHANGE.toInt(), sw)

        for (memoShape in memoShapeList) forUnknown(memoShape, MEMO_SHAPE.toInt(), sw)

        for (trackChange2 in trackChange2List) forUnknown(trackChange2, TRACK_CHANGE.toInt(), sw)

        for (trackChangeAuthor in trackChangeAuthorList) forUnknown(trackChangeAuthor, TRACK_CHANGE_AUTHOR.toInt(), sw)

        sw.downRecordLevel()
    }
}