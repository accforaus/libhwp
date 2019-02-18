package com.tang.hwplib.reader.bodytext.paragraph.control

import com.tang.hwplib.objects.bodytext.control.*
import com.tang.hwplib.objects.bodytext.control.ctrlheader.additionaltext.HWPAdditionalTextPosition
import com.tang.hwplib.objects.bodytext.control.ctrlheader.header.HWPHeaderFooterApplyPage
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPNumberShape
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderThickness
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.objects.docinfo.parashape.HWPAlignment
import com.tang.hwplib.objects.etc.*
import com.tang.hwplib.reader.bodytext.paragraph.control.bookmark.forCtrlData
import com.tang.hwplib.reader.bodytext.paragraph.control.eqed.forEQEdit
import com.tang.hwplib.reader.bodytext.paragraph.control.gso.pack.forCaption
import com.tang.hwplib.reader.bodytext.paragraph.control.gso.pack.forCtrlHeaderGso
import com.tang.hwplib.reader.bodytext.paragraph.control.secd.*
import com.tang.hwplib.reader.bodytext.paragraph.control.table.forCell
import com.tang.hwplib.reader.bodytext.paragraph.control.table.forTable
import com.tang.hwplib.reader.bodytext.paragraph.forParagraphList
import com.tang.hwplib.util.exceptions.HWPReadException
import com.tang.hwplib.reader.util.StreamReader

/**
 * 컨트롤 개체 [HWPControl]를 읽는 함수
 *
 * @author accforaus
 *
 * @param [c] [HWPControl], 빈 컨트롤 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forControl(c: HWPControl, sr: StreamReader) {
    /**
     * 덧말 [HWPControlAdditionalText]을 읽는 함수
     *
     * @param [at] [HWPControlAdditionalText], 빈 덧말 객체
     */
    fun forControlAdditionalText(at: HWPControlAdditionalText) {
        at.getHeader().run {
            this.mainText = sr.readUTF16LEString()
            this.subText = sr.readUTF16LEString()
            this.position = HWPAdditionalTextPosition.valueOf(sr.readUInt32().toByte())
            this.fsizeratio = sr.readUInt32()
            this.option = sr.readUInt32()
            this.styleId = sr.readUInt32()
            this.alignment = HWPAlignment.valueOf(sr.readUInt32().toByte())
        }
    }

    /**
     * 자동 번호 [HWPControlAutoNumber]를 읽는 함수
     *
     * @param [an] [HWPControlAutoNumber], 빈 자동 번호 객체
     */
    fun forControlAutoNumber(an: HWPControlAutoNumber) {
        an.getHeader().run {
            this.property.value = sr.readUInt32()
            this.number = sr.readUInt16()
            this.userSymbol = sr.readWChar()
            this.beforeDecorationLetter = sr.readWChar()
            this.afterDecorationLetter = sr.readWChar()
        }
    }

    /**
     * 책갈피 [HWPControlBookmark]를 읽는 함수
     *
     * @param [b] [HWPControlBookmark], 빈 책갈피 객체
     */
    fun forControlBookmark(b: HWPControlBookmark) {
        sr.readRecordHeader().run {
            if (this.tagId == CTRL_DATA) {
                b.createCtrlData()
                forCtrlData(b.getCtrlData()!!, sr)
            }
        }
    }

    /**
     * 단 정의 [HWPControlColumnDefine]를 읽는 함수
     *
     * @param [cd] [HWPControlColumnDefine], 빈 단 정의 객체
     */
    fun forControlColumnDefine(cd: HWPControlColumnDefine) {
        cd.getHeader().run {
            this.property.value = sr.readUInt16()
            val count: Int = this.property.getColumnCount().toInt()
            val sameWith: Boolean = this.property.isSameWidth()
            if (count < 2 || sameWith) {
                this.gapBetweenColumn = sr.readUInt16()
                this.property2 = sr.readUInt16()
            } else {
                val count2: Short = this.property.getColumnCount()
                for (index in 0 until count2) {
                    this.addNewColumnInfo().let {
                        it.width = sr.readUInt16()
                        it.gap = sr.readUInt16()
                    }
                }
            }
            this.divideLineSort = HWPBorderType.valueOf(sr.readUInt8().toByte())
            this.divideLineThickness = HWPBorderThickness.valueOf(sr.readUInt8().toByte())
            this.divideLineColor.value = sr.readColorRef()

            sr.skipToEndRecord()
        }
    }

    /**
     * 미주 [HWPControlEndNote]를 읽는 함수
     *
     * @throws [HWPReadException]
     * @param [en] [HWPControlEndNote], 빈 미주 객체
     */
    fun forControlEndnote(en: HWPControlEndNote) {
        en.getHeader().run {
            this.number = sr.readUInt32()
            this.beforeDecorationLetter = sr.readWChar()
            this.afterDecorationLetter = sr.readWChar()
            this.numberShape = HWPNumberShape.valueOf(sr.readUInt32().toShort())
            if (!sr.isEndOfRecord())
                this.instanceId = sr.readUInt32()
        }

        sr.readRecordHeader().run {
            if (this.tagId == LIST_HEADER) {
                en.listHeader.let {
                    it.paraCount = sr.readInt32()
                    it.property.value = sr.readUInt32()
                }
                sr.skipToEndRecord()
            } else throw HWPReadException("[ControlEndnote] List header must be located.")
        }
        forParagraphList(en.paragraphList, sr, en.docInfo!!)
    }

    /**
     * 한글 수식 개체 [HWPControlEquation]을 읽는 함수
     * Tag ID: [EQEDIT]
     *
     * @param [eqed] [HWPControlEquation], 빈 한글 수식 개체 객체
     */
    fun forControlEquation(eqed: HWPControlEquation) {
        eqed.run {
            val ctrlHeaderLevel: Short = sr.header.level

            forCtrlHeaderGso(getHeader(), sr)
            sr.readRecordHeader()
            if (sr.header.tagId == LIST_HEADER) {
                createCaption()
                forCaption(caption!!, sr, docInfo!!)
            }

            while (!sr.isEndOfStream()) {
                if (!sr.isImmediatelyAfterReadingHeader())
                    sr.readRecordHeader()
                if (ctrlHeaderLevel >= sr.header.level)
                    break
                when (sr.header.tagId) {
                    EQEDIT -> forEQEdit(eqEdit, sr)
                }
            }
        }
    }

    /**
     * 필드 시작 [HWPControlField]을 읽는 함수
     *
     * @param [field] [HWPControlField], 빈 필드 시작 객체
     */
    fun forControlField(field: HWPControlField) {
        field.getHeader().run {
            this.property.value = sr.readUInt32()
            this.etcProperty = sr.readUInt8()
            this.command = sr.readUTF16LEString()
            this.instanceId = sr.readUInt32()
            if (!sr.isEndOfRecord() && this.ctrlId == HWPControlType.FIELD_UNKNOWN.ctrlId)
                this.memoIndex = sr.readInt32()
            sr.skipToEndRecord()
        }
        sr.readRecordHeader().run {
            if (this.tagId == CTRL_DATA) {
                field.createCtrlData()
                forCtrlData(field.getCtrlData()!!, sr)
            }
        }
    }

    /**
     * 꼬리말 [HWPControlFooter]을 읽는 함수
     *
     * @throws [HWPReadException]
     * @param [foot] [HWPControlFooter], 빈 꼬리말 객체
     */
    fun forControlFooter(foot: HWPControlFooter) {
        foot.getHeader().run {
            this.applyPage = HWPHeaderFooterApplyPage.valueOf(sr.readUInt32().toByte())
            if (sr.header.size > sr.readAfterHeader) this.createIndex = sr.readInt32()
        }

        sr.readRecordHeader().run {
            if (this.tagId == LIST_HEADER) {
                foot.listHeader.run {
                    this.paraCount = sr.readInt32()
                    this.property.value = sr.readUInt32()
                    this.textWidth = sr.readUInt32()
                    this.textHeight = sr.readUInt32()
                    sr.skipToEndRecord()
                }
            } else throw HWPReadException("[HWPControlFooter] List header must be located")
        }
        forParagraphList(foot.paragraphList, sr, foot.docInfo!!)
    }

    /**
     * 각주 [HWPControlFootnote]를 읽는 함수
     *
     * @throws [HWPReadException]
     * @param [fn] [HWPControlFootnote], 빈 각주 객체
     */
    fun forControlFootnote(fn: HWPControlFootnote) {
        fn.getHeader().run {
            this.number = sr.readUInt32()
            this.beforeDecorationLetter = sr.readWChar()
            this.afterDecorationLetter = sr.readWChar()
            this.numberShape =  HWPNumberShape.valueOf(sr.readUInt32().toShort())
            this.instanceId = sr.readUInt32()
        }

        sr.readRecordHeader().run {
            if (this.tagId == LIST_HEADER) {
                fn.listHeader.let {
                    it.paraCount = sr.readInt32()
                    it.property.value = sr.readUInt32()
                    sr.skipToEndRecord()
                }
            } else throw HWPReadException("[HWPControlFootnote] List header must be located")
        }
        forParagraphList(fn.paragraphList, sr, fn.docInfo!!)
    }

    /**
     * 머리말 [HWPControlHeader]을 읽는 함수
     *
     * @throws [HWPReadException]
     * @param [head] [HWPControlHeader], 빈 머리말 객체
     */
    fun forControlHeader(head: HWPControlHeader) {
        head.getHeader().run {
            this.applyPage = HWPHeaderFooterApplyPage.valueOf(sr.readUInt32().toByte())
            if (!sr.isEndOfRecord()) this.createIndex = sr.readInt32()
        }

        sr.readRecordHeader().run {
            if (this.tagId == LIST_HEADER) {
                head.listHeader.let {
                    it.paraCount = sr.readInt32()
                    it.property.value = sr.readUInt32()
                    it.textWidth = sr.readUInt32()
                    it.textHeight = sr.readUInt32()
                    sr.skipToEndRecord()
                }
            } else throw HWPReadException("[HWPControlHeader] List header must be located")
        }

        forParagraphList(head.paragraphList, sr, head.docInfo!!)
    }

    /**
     * 숨은 설명 [HWPControlHiddenComment]을 읽는 함수
     *
     * @throws [HWPReadException]
     * @param [tcmt] [HWPControlHiddenComment], 빈 숨은 설명 객체
     */
    fun forControlHiddenComment(tcmt: HWPControlHiddenComment) {
        sr.readRecordHeader().run {
            if (this.tagId == LIST_HEADER) {
                tcmt.listHeader.let {
                    it.paraCount = sr.readInt32()
                    it.property.value = sr.readUInt32()
                    sr.skipToEndRecord()
                }
            } else throw Exception("[HWPControlHiddenComment] List header must be located")
        }

        forParagraphList(tcmt.paragraphList, sr, tcmt.docInfo!!)
    }

    /**
     * 찾아보기 표식 [HWPControlIndexMark]를 읽는 함수
     *
     * @param [idxm] [HWPControlIndexMark], 빈 찾아보기 표식 객체
     */
    fun forControlIndexMark(idxm: HWPControlIndexMark) {
        idxm.getHeader().run {
            this.keyword1 = sr.readUTF16LEString()
            this.keyword2 = sr.readUTF16LEString()
        }
    }

    /**
     * 새 번호 지정 [HWPControlNewNumber]를 읽는 함수
     *
     * @param [nwno] [HWPControlNewNumber], 빈 새 번호 지정 객체
     */
    fun forControlNewNumber(nwno: HWPControlNewNumber) {
        nwno.getHeader().run {
            this.property.value = sr.readUInt32()
            this.number = sr.readUInt16()
        }
    }

    /**
     * 글자 겹침 [HWPControlOverlappingLetter]을 읽는 함수
     *
     * @param [tcps] [HWPControlOverlappingLetter], 빈 글자 겹침 객체
     */
    fun forControlOverlappingLetter(tcps: HWPControlOverlappingLetter) {
        tcps.getHeader().run {
            val count: Int = sr.readWord()
            for (index in 0 until count) this.addOverlappingLetter(sr.readWChar())
            if (!sr.isEndOfRecord()) {
                this.borderType = sr.readUInt8()
                this.internalFontSize = sr.readInt8()
                this.expendInsideLetter = sr.readUInt8()
                val count2: Short = sr.readUInt8()
                for (index in 0 until count2)
                    this.addCharShapeId(sr.readUInt32())
            }
        }
    }

    /**
     * 감추기 [HWPControlPageHide]를 읽는 함수
     *
     * @param [pghd] [HWPControlPageHide], 빈 감추기 객체
     */
    fun forControlPageHide(pghd: HWPControlPageHide) {
        pghd.getHeader().property.value = sr.readUInt32()
    }

    /**
     * 홀/짝수 조정 [HWPControlPageOddEvenAdjust]을 읽는 함수
     *
     * @param [pgod] [HWPControlPageOddEvenAdjust] 빈 홀/짝수 조정 객체
     */
    fun forControlPageOddEven(pgod: HWPControlPageOddEvenAdjust) {
        pgod.getHeader().run {
            property.value = sr.readUInt32()
        }
    }

    /**
     * 쪽 번호 위치 [HWPControlPageNumberPosition]를 읽는 함수
     *
     * @param [pgnp] [HWPControlPageNumberPosition], 빈 쪽 번호 위치 객체
     */
    fun forControlPageNumberPosition(pgnp: HWPControlPageNumberPosition) {
        pgnp.getHeader().run {
            this.property.value = sr.readUInt32()
            this.number = sr.readUInt16()
            this.userSymbol = sr.readWChar()
            this.beforeDecorationLetter = sr.readWChar()
            this.afterDecorationLetter = sr.readWChar()
        }
    }

    /**
     * 구역 정의 개체 [HWPControlSectionDefine]를 읽는 함수
     *
     * @param [secd] [HWPControlSectionDefine], 빈 구역 정의 개체 객체
     */
    fun forControlSectionDefine(secd: HWPControlSectionDefine) {
        secd.run {
            var endFootnoteShapeIndex: Int = 0
            var pageBorderFillIndex: Int = 0
            val ctrlHeaderLevel: Short = sr.header.level
            forCtrlHeaderSecd(getHeader(), sr)

            while (!sr.isEndOfStream()) {
                if (!sr.isImmediatelyAfterReadingHeader()) {
                    sr.readRecordHeader()
                }

                if (ctrlHeaderLevel >= sr.header.level) break
                when (sr.header.tagId) {
                    PAGE_DEF -> forPageDef(pageDef, sr)
                    FOOTNOTE_SHAPE -> {
                        when (endFootnoteShapeIndex) {
                            0 -> forFootEndNoteShape(footnoteShape, sr)
                            1 -> forFootEndNoteShape(endnoteShape, sr)
                        }
                        endFootnoteShapeIndex++
                    }
                    PAGE_BORDER_FILL -> {
                        when (pageBorderFillIndex) {
                            0 -> forPageBorderFill(bothPageBorderFill, sr)
                            1 -> forPageBorderFill(evenPageBorderFill, sr)
                            2 -> forPageBorderFill(oddPageBorderFill, sr)
                        }
                        pageBorderFillIndex++
                    }
                    LIST_HEADER -> forBatangPageInfo(addNewBatangPageInfo(), sr, this.docInfo!!)
                    CTRL_DATA -> {
                        createCtrlData()
                        forCtrlData(getCtrlData()!!, sr)
                    }
                }
            }
        }
    }

    /**
     * 표 개체 [HWPControlTable]를 읽는 함수
     * Tag ID: [TABLE]
     *
     * @param [table] [HWPControlTable], 빈 표 개체 객체
     */
    fun forControlTable(table: HWPControlTable) {
        table.run {
            forCtrlHeaderGso(getHeader(), sr)
            sr.readRecordHeader()
            if (sr.header.tagId == CTRL_DATA) {
                createCtrlData()
                forCtrlData(getCtrlData()!!, sr)
            }
            if (!sr.isImmediatelyAfterReadingHeader())
                sr.readRecordHeader()
            if (sr.header.tagId == LIST_HEADER) {
                createCaption()
                forCaption(caption!!, sr, docInfo!!)
            }
            if (!sr.isImmediatelyAfterReadingHeader())
                sr.readRecordHeader()
            if (sr.header.tagId == TABLE)
                forTable(this.table, sr)

            val rowCount: Int = this.table.rowCount
            val cellCountOfRow: ArrayList<Int> = this.table.cellCountOfRowList
            for (rowIndex in 0 until rowCount) {
                addNewRow().apply {
                    for (cellIndex in 0 until cellCountOfRow[rowIndex]) {
                        this.addNewCell().also { forCell(it, sr, table.docInfo!!) }
                    }
                }
            }
        }
    }


    if (HWPControlType.isField(c.getType().ctrlId)) {
        forControlField(c as HWPControlField)
    }
    when (c.getType()) {
        HWPControlType.Table -> forControlTable(c as HWPControlTable)
        HWPControlType.Equation -> forControlEquation(c as HWPControlEquation)
        HWPControlType.SectionDefine -> forControlSectionDefine(c as HWPControlSectionDefine)
        HWPControlType.ColumnDefine -> forControlColumnDefine(c as HWPControlColumnDefine)
        HWPControlType.Header -> forControlHeader(c as HWPControlHeader)
        HWPControlType.Footer -> forControlFooter(c as HWPControlFooter)
        HWPControlType.Footnote -> forControlFootnote(c as HWPControlFootnote)
        HWPControlType.Endnote -> forControlEndnote(c as HWPControlEndNote)
        HWPControlType.AutoNumber -> forControlAutoNumber(c as HWPControlAutoNumber)
        HWPControlType.NewNumber -> forControlNewNumber(c as HWPControlNewNumber)
        HWPControlType.PageHide -> forControlPageHide(c as HWPControlPageHide)
        HWPControlType.PageOddEvenAdjust -> forControlPageOddEven(c as HWPControlPageOddEvenAdjust)
        HWPControlType.PageNumberPosition -> forControlPageNumberPosition(c as HWPControlPageNumberPosition)
        HWPControlType.IndexMark -> forControlIndexMark(c as HWPControlIndexMark)
        HWPControlType.Bookmark -> forControlBookmark(c as HWPControlBookmark)
        HWPControlType.OverlappingLetter -> forControlOverlappingLetter(c as HWPControlOverlappingLetter)
        HWPControlType.AdditionalText -> forControlAdditionalText(c as HWPControlAdditionalText)
        HWPControlType.HiddenComment -> forControlHiddenComment(c as HWPControlHiddenComment)
        else -> {}
    }
}