package com.tang.hwplib.writer.bodytext.paragraph.control

import com.tang.hwplib.objects.bodytext.control.*
import com.tang.hwplib.objects.bodytext.control.ctrlheader.*
import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControl
import com.tang.hwplib.objects.etc.CTRL_HEADER
import com.tang.hwplib.objects.etc.EQEDIT
import com.tang.hwplib.objects.etc.TABLE
import com.tang.hwplib.util.string.getUTF16LEStringSize
import com.tang.hwplib.writer.util.StreamWriter
import com.tang.hwplib.writer.bodytext.paragraph.control.bookmark.forCtrlData
import com.tang.hwplib.writer.bodytext.paragraph.control.endnote.forListHeaderForFootnoteEndnote
import com.tang.hwplib.writer.bodytext.paragraph.control.eqed.forEQEdit
import com.tang.hwplib.writer.bodytext.paragraph.control.gso.ForGsoControl
import com.tang.hwplib.writer.bodytext.paragraph.control.gso.pack.forCaption
import com.tang.hwplib.writer.bodytext.paragraph.control.gso.pack.forCtrlHeaderGso
import com.tang.hwplib.writer.bodytext.paragraph.control.headerfooter.forListheaderForHeaderFooter
import com.tang.hwplib.writer.bodytext.paragraph.control.hiddencomment.forListHeaderForHiddenComment
import com.tang.hwplib.writer.bodytext.paragraph.control.secd.forBatangPageInfo
import com.tang.hwplib.writer.bodytext.paragraph.control.secd.forFootEndNoteShape
import com.tang.hwplib.writer.bodytext.paragraph.control.secd.forPageBorderFill
import com.tang.hwplib.writer.bodytext.paragraph.control.secd.forPageDef
import com.tang.hwplib.writer.bodytext.paragraph.control.table.forCell
import com.tang.hwplib.writer.bodytext.paragraph.control.table.forTable
import com.tang.hwplib.writer.bodytext.paragraph.forParagraphList

/**
 * 컨트롤 개체 [HWPControl]를 쓰는 함수
 *
 * @author accforaus
 *
 * @param [c] [HWPControl], 컨트롤 개체 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forControl(c: HWPControl, sw: StreamWriter) {
    /**
     * 덧말 [HWPControlAdditionalText]을 쓰는 함수
     *
     * @param [at] [HWPControlAdditionalText], 덧말 객체
     */
    fun forControlAdditionalText(at: HWPControlAdditionalText) {
        /**
         * 덧말의 전체 길이를 반환하는 함수
         *
         * @param [h] [HWPCtrlHeaderAdditionalText], 덧말 헤더 객체
         * @return [Int] 덧말의 전체 길이 반환
         */
        fun getSize(h: HWPCtrlHeaderAdditionalText): Int {
            var size: Int = 0
            size += 4
            size += getUTF16LEStringSize(h.mainText)
            size += getUTF16LEStringSize(h.subText)
            size += 20
            return size
        }
        at.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), getSize(this))

            sw.writeUInt32(ctrlId)
            sw.writeUTF16LEString(mainText)
            sw.writeUTF16LEString(subText)
            sw.writeUInt32(position!!.value.toLong())
            sw.writeUInt32(fsizeratio)
            sw.writeUInt32(option)
            sw.writeUInt32(styleId)
            sw.writeUInt32(alignment!!.value.toLong())
        }
    }

    /**
     * 자동 번호 [HWPControlAutoNumber]를 쓰는 함수
     *
     * @param [an] [HWPControlAutoNumber], 자동 번호 객체
     */
    fun forControlAutoNumber(an: HWPControlAutoNumber) {
        an.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), 16)
            sw.writeUInt32(ctrlId)
            sw.writeUInt32(property.value)
            sw.writeUInt16(number)
            sw.writeWChar(userSymbol!!)
            sw.writeWChar(beforeDecorationLetter)
            sw.writeWChar(afterDecorationLetter)
        }
    }

    /**
     * 책갈피 [HWPControlBookmark]를 쓰는 함수
     *
     * @param [b] [HWPControlBookmark], 책갈피 객체
     */
    fun forControlBookmark(b: HWPControlBookmark) {
        b.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), 4)
            sw.writeUInt32(ctrlId)
        }
        sw.upRecordLevel()
        forCtrlData(b.getCtrlData(), sw)
        sw.downRecordLevel()
    }

    /**
     * 단 정의 [HWPControlColumnDefine]를 쓰는 함수
     *
     * @param [cd] [HWPControlColumnDefine], 단 정의 객체
     */
    fun forControlColumnDefine(cd: HWPControlColumnDefine) {
        /**
         * 단 정의 헤더의 크기를 반환하는 함수
         *
         * @param [header] [HWPCtrlHeaderColumnDefine], 단 정의 헤더 객체
         * @return [Int] 단 정의 헤더의 크기 반환
         */
        fun getSize(header: HWPCtrlHeaderColumnDefine): Int {
            var size: Int = 0
            size += 6

            val columnCount: Int = header.property.getColumnCount().toInt()
            val sameWidth: Boolean = header.property.isSameWidth()
            if (columnCount < 2 || sameWidth) size += 4
            else size += columnCount * 4
            size += 6
            return size
        }
        cd.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), getSize(this))
            sw.writeUInt32(ctrlId)

            sw.writeUInt16(property.value)
            val columnCount: Int = property.getColumnCount().toInt()
            val sameWidth: Boolean = property.isSameWidth()
            if (columnCount < 2 || sameWidth) {
                sw.writeUInt16(gapBetweenColumn)
                sw.writeUInt16(property2)
            } else {
                for (index in 0 until property.getColumnCount()) {
                    addNewColumnInfo().let {
                        sw.writeUInt16(it.width)
                        sw.writeUInt16(it.gap)
                    }
                }
            }

            sw.writeUInt8(divideLineSort!!.value.toShort())
            sw.writeUInt8(divideLineThickness!!.value.toShort())
            sw.writeColorRef(divideLineColor.value)
        }
    }

    /**
     * 미주 [HWPControlEndNote]를 쓰는 함수
     *
     * @param [en] [HWPControlEndNote], 미주 객체
     */
    fun forControlEndnote(en: HWPControlEndNote) {
        en.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), 20)
            sw.writeUInt32(ctrlId)

            sw.writeUInt32(number)
            sw.writeWChar(beforeDecorationLetter)
            sw.writeWChar(afterDecorationLetter)
            sw.writeUInt32(numberShape!!.value.toLong())
            sw.writeUInt32(instanceId)
        }

        sw.upRecordLevel()
        forListHeaderForFootnoteEndnote(en.listHeader, sw)
        forParagraphList(en.paragraphList, sw)
        sw.downRecordLevel()
    }

    /**
     * 한글 수식 개체 [HWPControlEquation]를 쓰는 함수
     * Tag ID: [EQEDIT]
     *
     * @param [eqed] [HWPControlEquation], 한글 수식 개체 객체
     */
    fun forControlEquation(eqed: HWPControlEquation) {
        forCtrlHeaderGso(eqed.getHeader(), sw)

        sw.upRecordLevel()

        forCaption(eqed.caption, sw)
        forEQEdit(eqed.eqEdit, sw)

        sw.downRecordLevel()
    }

    /**
     * 필드 시작 [HWPControlField]을 쓰는 함수
     *
     * @param [field] [HWPControlField], 필드 시작 객체
     */
    fun forControlField(field: HWPControlField) {
        /**
         * 필드 시작의 전체 길이를 반환하는 함수
         *
         * @param [header] [HWPCtrlHeaderField], 필드 시작 헤더 객체
         * @return [Int] 필드 시작의 전체 길이 반환
         */
        fun getSize(header: HWPCtrlHeaderField): Int {
            var size: Int = 0
            size += 9
            size += getUTF16LEStringSize(header.command)
            size += 8
            return size
        }

        field.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), getSize(this))
            sw.writeUInt32(ctrlId)

            sw.writeUInt32(property.value)
            sw.writeUInt8(etcProperty)
            sw.writeUTF16LEString(command)
            sw.writeUInt32(instanceId)

            if (ctrlId == HWPControlType.FIELD_UNKNOWN.ctrlId) sw.writeInt32(memoIndex)
            else sw.writeZero(4)
        }

        sw.upRecordLevel()

        forCtrlData(field.getCtrlData(), sw)

        sw.downRecordLevel()
    }

    /**
     * 꼬리말 [HWPControlFooter]을 쓰는 함수
     *
     * @param [footer] [HWPControlFooter], 꼬리말 객체
     */
    fun forControlFooter(footer: HWPControlFooter) {
        footer.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), 12)
            sw.writeUInt32(ctrlId)
            sw.writeUInt32(applyPage!!.value.toLong())
            sw.writeInt32(createIndex)
        }

        sw.upRecordLevel()

        forListheaderForHeaderFooter(footer.listHeader, sw)
        forParagraphList(footer.paragraphList, sw)

        sw.downRecordLevel()
    }

    /**
     * 각주 [HWPControlFootnote]를 쓰는 함수
     *
     * @param [fn] [HWPControlFootnote], 각주 객체
     */
    fun forControlFootnote(fn: HWPControlFootnote) {
        fn.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), 20)
            sw.writeUInt32(ctrlId)

            sw.writeUInt32(number)
            sw.writeWChar(beforeDecorationLetter)
            sw.writeWChar(afterDecorationLetter)
            sw.writeUInt32(numberShape!!.value.toLong())
            sw.writeUInt32(instanceId)
        }

        sw.upRecordLevel()

        forListHeaderForFootnoteEndnote(fn.listHeader, sw)
        forParagraphList(fn.paragraphList, sw)

        sw.downRecordLevel()
    }

    /**
     * 머리말 [HWPControlHeader]을 쓰는 함수
     *
     * @param [header] [HWPControlHeader], 머리말 객체
     */
    fun forControlHeader(header: HWPControlHeader) {
        header.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), 12)
            sw.writeUInt32(ctrlId)
            sw.writeUInt32(applyPage!!.value.toLong())
            sw.writeInt32(createIndex)
        }

        sw.upRecordLevel()

        forListheaderForHeaderFooter(header.listHeader, sw)
        forParagraphList(header.paragraphList, sw)

        sw.downRecordLevel()
    }

    /**
     * 숨은 설명 [HWPControlHiddenComment]을 쓰는 함수
     *
     * @param [hc] [HWPControlHiddenComment], 숨은 설명 객체
     */
    fun forControlHiddenComment(hc: HWPControlHiddenComment) {
        hc.header!!.run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), 4)
            sw.writeUInt32(ctrlId)
        }

        sw.upRecordLevel()

        forListHeaderForHiddenComment(hc.listHeader, sw)
        forParagraphList(hc.paragraphList, sw)

        sw.downRecordLevel()
    }

    /**
     * 찾아보기 표식 [HWPControlIndexMark]을 쓰는 함수
     *
     * @param [im] [HWPControlIndexMark], 찾아보기 표식 객체
     */
    fun forControlIndexMark(im: HWPControlIndexMark) {
        /**
         * 찾아보기 표식의 전체 크기를 반환하는 함수
         *
         * @param [header] [HWPCtrlHeaderIndexMark], 찾아보기 표식 헤더 객체
         * @return [Int] 찾아보기 표식의 전체 크기 반환
         */
        fun getSize(header: HWPCtrlHeaderIndexMark): Int {
            var size: Int = 0
            size += 4
            size += getUTF16LEStringSize(header.keyword1)
            size += getUTF16LEStringSize(header.keyword2)
            return size
        }

        im.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), getSize(this))
            sw.writeUInt32(ctrlId)

            sw.writeUTF16LEString(keyword1)
            sw.writeUTF16LEString(keyword2)
        }
    }

    /**
     * 새 번호 지정 [HWPControlNewNumber]을 쓰는 함수
     *
     * @param [nn] [HWPControlNewNumber], 새 번호 지정 객체
     */
    fun forControlNewNumber(nn: HWPControlNewNumber) {
        nn.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), 10)
            sw.writeUInt32(ctrlId)

            sw.writeUInt32(property.value)
            sw.writeUInt16(number)
        }
    }

    /**
     * 글자 겹침 [HWPControlOverlappingLetter]을 쓰는 함수
     *
     * @param [ol] [HWPControlOverlappingLetter], 글자 겹침 객체
     */
    fun forControlOverlappingLetter(ol: HWPControlOverlappingLetter) {
        /**
         * 글자 겹침의 전체 길이를 반환하는 함수
         *
         * @param [header] [HWPCtrlHeaderOverlappingLetter], 글자 겹침 헤더 객체
         * @return [Int] 글자 겹침의 전체 길이 반환
         */
        fun getSize(header: HWPCtrlHeaderOverlappingLetter): Int {
            var size: Int = 0
            size += 4
            size += 2
            size += header.overlappingLetterList.size * 2
            size += 3
            size += 1
            size += header.charShapeIdList.size * 4
            return size
        }

        ol.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), getSize(this))
            sw.writeUInt32(ctrlId)

            sw.writeUInt16(overlappingLetterList.size)
            for (letter in overlappingLetterList) sw.writeWChar(letter)

            sw.writeUInt8(borderType)
            sw.writeInt8(internalFontSize)
            sw.writeUInt8(expendInsideLetter)

            sw.writeUInt8(charShapeIdList.size.toShort())
            for (charShapeId in charShapeIdList) sw.writeUInt32(charShapeId)
        }
    }

    /**
     * 감추기 [HWPControlPageHide]를 쓰는 함수
     *
     * @param [ph] [HWPControlPageHide], 감추기 객체
     */
    fun forControlPageHide(ph: HWPControlPageHide) {
        ph.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), 8)
            sw.writeUInt32(ctrlId)
            sw.writeUInt32(property.value)
        }
    }

    /**
     * 홀/짝수 조정 [HWPControlPageOddEvenAdjust]을 쓰는 함수
     *
     * @param [pdea] [HWPControlPageOddEvenAdjust], 홀/짝수 조정 객체
     */
    fun forControlPageOddEvenAdjust(pdea: HWPControlPageOddEvenAdjust) {
        pdea.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), 8)
            sw.writeUInt32(ctrlId)
            sw.writeUInt32(property.value)
        }
    }

    /**
     * 쪽 번호 위치 [HWPControlPageNumberPosition]를 쓰는 함수
     *
     * @param [pnp] [HWPControlPageNumberPosition], 쪽 번호 위치 객체
     */
    fun forControlPageNumberPosition(pnp: HWPControlPageNumberPosition) {
        pnp.getHeader().run {
            sw.writeRecordHeader(CTRL_HEADER.toInt(), 16)
            sw.writeUInt32(ctrlId)

            sw.writeUInt32(property.value)
            sw.writeUInt16(number)
            sw.writeWChar(userSymbol)
            sw.writeWChar(beforeDecorationLetter)
            sw.writeWChar(afterDecorationLetter)
        }
    }

    /**
     * 구역 정의 [HWPControlSectionDefine]를 쓰는 함수
     *
     * @param [sd] [HWPControlSectionDefine], 구역 정의 객체
     */
    fun forControlSectionDefine(sd: HWPControlSectionDefine) {
        sd.getHeader().run {
            val size: Int = if (sw.version.isOver(5,0,1,2)) 38 else 36
            sw.writeRecordHeader(CTRL_HEADER.toInt(), size)
            sw.writeUInt32(ctrlId)

            sw.writeUInt32(property.value)
            sw.writeUInt16(columnGap)
            sw.writeUInt16(verticalLineAlign)
            sw.writeUInt16(horizontalLineAlign)
            sw.writeUInt32(defaultTabGap)
            sw.writeUInt16(numberParaShapeId)
            sw.writeUInt16(pageStartNumber)
            sw.writeUInt16(imageStartNumber)
            sw.writeUInt16(tableStartNumber)
            sw.writeUInt16(equationStartNumber)
            if (sw.version.isOver(5,0,1,2)) sw.writeUInt16(defaultLanguage)
            sw.writeZero(8)
        }

        sw.upRecordLevel()

        forPageDef(sd.pageDef, sw)
        forFootEndNoteShape(sd.footnoteShape, sw)
        forFootEndNoteShape(sd.endnoteShape, sw)
        forPageBorderFill(sd.bothPageBorderFill, sw)
        forPageBorderFill(sd.evenPageBorderFill, sw)
        forPageBorderFill(sd.oddPageBorderFill, sw)

        for (bpi in sd.batangPageInfoList) forBatangPageInfo(bpi, sw)

        sd.getCtrlData()?.run { forCtrlData(this, sw) }

        sw.downRecordLevel()
    }

    /**
     * 표 개체 [HWPControlTable]를 쓰는 함수
     * Tag ID: [TABLE]
     *
     * @param [table] [HWPControlTable], 표 개체 객체
     */
    fun forControlTable(table: HWPControlTable) {
        table.run {
            forCtrlHeaderGso(getHeader(), sw)

            sw.upRecordLevel()

            forCaption(caption, sw)
            forTable(this.table, sw)

            for (row in rowList) {
                for (cell in row.cellList) {
                    forCell(cell, sw)
                }
            }

            sw.downRecordLevel()
        }
    }

    c.run {
        if (HWPControlType.isField(getType().ctrlId))
            forControlField(c as HWPControlField)
        when (c) {
            is HWPControlTable -> forControlTable(c)
            is HWPControlEquation -> forControlEquation(c)
            is HWPControlSectionDefine -> forControlSectionDefine(c)
            is HWPControlColumnDefine -> forControlColumnDefine(c)
            is HWPControlHeader -> forControlHeader(c)
            is HWPControlFooter -> forControlFooter(c)
            is HWPControlFootnote -> forControlFootnote(c)
            is HWPControlEndNote -> forControlEndnote(c)
            is HWPControlAutoNumber -> forControlAutoNumber(c)
            is HWPControlPageHide -> forControlPageHide(c)
            is HWPControlNewNumber -> forControlNewNumber(c)
            is HWPControlPageOddEvenAdjust -> forControlPageOddEvenAdjust(c)
            is HWPControlPageNumberPosition -> forControlPageNumberPosition(c)
            is HWPControlIndexMark -> forControlIndexMark(c)
            is HWPControlBookmark -> forControlBookmark(c)
            is HWPControlOverlappingLetter -> forControlOverlappingLetter(c)
            is HWPControlHiddenComment -> forControlHiddenComment(c)
            is HWPControlAdditionalText -> forControlAdditionalText(c)
            is HWPGsoControl -> ForGsoControl.write(c, sw)
        }
    }
}