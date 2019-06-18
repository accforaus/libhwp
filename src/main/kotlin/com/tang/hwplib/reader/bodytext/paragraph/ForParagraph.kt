package com.tang.hwplib.reader.bodytext.paragraph

import com.tang.hwplib.objects.bodytext.control.HWPControl
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphListInterface
import com.tang.hwplib.objects.bodytext.paragraph.charshape.HWPParaCharShape
import com.tang.hwplib.objects.bodytext.paragraph.header.HWPParaHeader
import com.tang.hwplib.objects.bodytext.paragraph.linesegment.HWPLineSegItem
import com.tang.hwplib.objects.bodytext.paragraph.linesegment.HWPParaLineSeg
import com.tang.hwplib.objects.bodytext.paragraph.memo.ListHeaderForHWPMemo
import com.tang.hwplib.objects.bodytext.paragraph.memo.HWPMemo
import com.tang.hwplib.objects.bodytext.paragraph.memo.HWPMemoList
import com.tang.hwplib.objects.bodytext.paragraph.rangetag.HWPParaRangeTag
import com.tang.hwplib.objects.bodytext.paragraph.rangetag.HWPRangeTagItem
import com.tang.hwplib.objects.bodytext.paragraph.text.*
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.etc.*
import com.tang.hwplib.reader.bodytext.paragraph.control.forControl
import com.tang.hwplib.reader.bodytext.paragraph.control.gso.ForGsoControl
import com.tang.hwplib.util.exceptions.HWPReadException
import com.tang.hwplib.reader.util.StreamReader

/**
 * 문단 리스트 [HWPParagraphListInterface] 를 읽는 함수
 *
 * @author accforaus
 *
 * @param [pli] [HWPParagraphListInterface], 빈 문단 리스트 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forParagraphList(pli: HWPParagraphListInterface, sr: StreamReader, docInfo: HWPDocInfo) {
    val fp: ForParagraph = ForParagraph(docInfo)
    sr.readRecordHeader()
    while (!sr.isEndOfStream()) {
        val para = pli.addNewParagraph()
        para.docInfo = docInfo
        fp.read(para, sr)
        if (para.header.lastInList)
            break
    }
}

/**
 * 문단을 읽는 객체
 *
 * @author accforaus
 *
 * @property [paragraph] [HWPParagraph], 현재 읽고 있는 문단 객체
 * @property [sr] [StreamReader], 스트림 리더 객체
 * @property [paraHeaderLevel], 문단 머리 레벨
 */
private class ForParagraph(val docInfo : HWPDocInfo) {
    private var paragraph: HWPParagraph? = null
    private var sr: StreamReader? = null
    private var paraHeaderLevel: Short? = null

    /**
     * 문단을 읽는 함수
     *
     * @param [paragraph] [HWPParagraph], 빈 문단 객체
     * @param [sr] [StreamReader], 스트림 리더 객체
     */
    fun read(paragraph: HWPParagraph, sr: StreamReader) {
        this.paragraph = paragraph
        this.sr = sr
        this.paraHeaderLevel = sr.header.level

        val isOutOfParagraph = fun (): Boolean = this.paraHeaderLevel!! >= this.sr!!.header.level
        val isFollowLastBatangPageInfo = fun (): Boolean = paraHeaderLevel == 0.toShort() && this.sr!!.header.tagId == LIST_HEADER && this.sr!!.header.level == 1.toShort()

        if (this.sr!!.header.tagId != PARA_HEADER) throw HWPReadException("This is not paragraph")
        forParaHeader(this.paragraph!!.header)
        while (!this.sr!!.isEndOfStream()) {
            if (!this.sr!!.isImmediatelyAfterReadingHeader()) this.sr!!.readRecordHeader()
            if (isOutOfParagraph() || isFollowLastBatangPageInfo()) break
            when (this.sr!!.header.tagId) {
                PARA_TEXT -> forParaText(paragraph)
                PARA_CHAR_SHAPE -> forParaCharShape(paragraph)
                PARA_LINE_SEG -> forLineSeg(paragraph)
                PARA_RANGE_TAG -> forRangeTag(paragraph, this.sr!!.header.size)
                CTRL_HEADER -> {
                    val id: Long = this.sr!!.readUInt32()
                    if (id == HWPControlType.Gso.ctrlId) {
                        val forGsoControl: ForGsoControl = ForGsoControl()
                        forGsoControl.read(paragraph, this.sr!!)
                    } else {
                        val c: HWPControl? = paragraph.addNewControl(id)
                        c?.docInfo = docInfo
                        forControl(c!!, this.sr!!)
                    }
                }
                MEMO_LIST -> forMemo(paragraph.addNewMemo())
                else -> {
                    val buffer: ByteArray = ByteArray(this.sr!!.header.size.toInt())
                    this.sr!!.readBytes(buffer)
                }
            }
        }
    }

    /**
     * 메모 [HWPMemo]를 읽는 함수
     * Tag ID: [MEMO_LIST]
     *
     * @param [m] [HWPMemo], 빈 메모 객체
     */
    private fun forMemo(m: HWPMemo) {
        /**
         * 메모 리스트 [HWPMemoList]를 읽는 함수
         *
         * @param [ml] [HWPMemoList], 빈 메모 리스트 객체
         */
        fun forMemoList(ml: HWPMemoList) {
            ml.memoIndex = sr!!.readUInt32()
        }

        /**
         * 메모 문단 리스트 헤더 [ListHeaderForHWPMemo]를 읽는 함수
         *
         * @param [listHeaderForMemo] [ListHeaderForHWPMemo], 빈 메모 문단 리스트 헤더 객체
         */
        fun listHeader(listHeaderForMemo: ListHeaderForHWPMemo) {
            sr!!.readRecordHeader()
            listHeaderForMemo.run {
                paraCount = sr!!.readInt32()
                property.value = sr!!.readUInt32()
                textHeight = sr!!.readUInt32()
                textHeight = sr!!.readUInt32()
            }
            sr!!.skipToEndRecord()
        }

        m.run {
            forMemoList(memoList)
            listHeader(listHeader)
            forParagraphList(paragraphList, sr!!, docInfo)
        }
    }

    /**
     * 문단 글자 모양 [HWPParaCharShape]를 읽는 함수
     * Tag ID: [PARA_CHAR_SHAPE]
     *
     * @param [paragraph] [HWPParagraph], 문단 글자 모양을 읽을 문단 객체
     */
    private fun forParaCharShape(paragraph: HWPParagraph) {
        paragraph.createCharShape()

        for (index in 0 until paragraph.header.charShapeCount) {
            val position: Long = sr!!.readUInt32()
            val charShapeId: Long = sr!!.readUInt32()
            paragraph.paraCharShape!!.addParaCharShape(position, charShapeId)
        }
    }

    /**
     * 문단 헤더 [HWPParaHeader]를 읽는 함수
     * Tag ID: [PARA_HEADER]
     *
     * @param [ph] [HWPParaHeader], 빈 문단 헤더 객체
     */
    private fun forParaHeader(ph: HWPParaHeader) {
        /**
         * 마지막 문단과 글자 수를 읽는 함수
         */
        fun lastInList_TextCount() {
            val value: Long = sr!!.readUInt32()
            if (value.and(0x80000000) == 0x80000000) {
                ph.lastInList = true
                ph.characterCount = value.and(0x7fffffff)
            } else {
                ph.lastInList = false
                ph.characterCount = value
            }
        }

        lastInList_TextCount()
        ph.run {
            controlMask.value = sr!!.readUInt32()
            paraShapeId = sr!!.correctParaShapeId(sr!!.readUInt16())
            styleId = sr!!.readUInt8()
            divideSort.value = sr!!.readUInt8()
            charShapeCount = sr!!.readUInt16()
            rangeTagCount = sr!!.readUInt16()
            lineAlignCount = sr!!.readUInt16()
            instanceID = sr!!.readUInt32()
            if (!sr!!.isEndOfRecord() && sr!!.fileVersion.isOver(5, 0, 3, 2))
                isMergedByTrack = sr!!.readUInt16()
        }
        sr!!.skipToEndRecord()
    }

    /**
     * 문단의 레이아웃 [HWPParaLineSeg]을 읽는 함수
     * Tag ID: [PARA_LINE_SEG]
     *
     * @param [p] [HWPParagraph], 문단 객체
     */
    private fun forLineSeg(p: HWPParagraph) {
        /**
         * 문단 세그먼트 아이디 아이템 [HWPLineSegItem]을 읽는 함수
         *
         * @param [plsi] [HWPLineSegItem], 빈 문단 세그먼트 아이템
         */
        fun paraLineSegItem(plsi: HWPLineSegItem) {
            plsi.run {
                textStartPosition = sr!!.readUInt32()
                lineVerticalPosition = sr!!.readInt32()
                lineHeight = sr!!.readInt32()
                textPartHeight = sr!!.readInt32()
                distanceBaseLineToLineVerticalPosition = sr!!.readInt32()
                lineSpace = sr!!.readInt32()
                startPositionFromColumn = sr!!.readInt32()
                segmentWidth = sr!!.readInt32()
                tag.value = sr!!.readUInt32()
            }
        }

        p.createLineSeg()
        if (sr!!.header.size.toInt() == 4095) sr!!.header.size = sr!!.readUInt32()

        val pls: HWPParaLineSeg = p.lineSeg!!
        for (index in 0 until p.header.lineAlignCount)
            paraLineSegItem(pls.addNewLineSegItem())
    }

    /**
     * 문단의 영역 태그 [HWPParaRangeTag]를 읽는 함수
     * Tag ID: [PARA_RANGE_TAG]
     *
     * @param [p] [HWPParagraph], 문단 객체
     * @param [size] [Long], 영역 태그의 길이
     */
    private fun forRangeTag(p: HWPParagraph, size: Long) {
        /**
         * 문단의 영역 태그 아이템 [HWPRangeTagItem]을 읽는 객체
         *
         * @param [rti] [HWPRangeTagItem], 빈 문단의 영역 태그 아이템
         */
        fun tag(rti: HWPRangeTagItem) {
            val data: ByteArray = ByteArray(3)
            sr!!.readBytes(data)
            rti.run {
                this.data = data
                sort = sr!!.readUInt8()
            }
        }

        p.createRangeTag()
        val prt: HWPParaRangeTag = p.rangeTag!!
        val count: Long = size / 12
        for (index in 0 until count) {
            prt.addNewRangeTagItem().apply {
                this.rangeStart = sr!!.readUInt32()
                this.rangeEnd = sr!!.readUInt32()
                tag(this)
            }
        }
    }

    /**
     * 문단 텍스트 [HWPParaText]를 읽는 함수
     * Tag ID: [PARA_TEXT]
     *
     * @param [p] [HWPParagraph], 문단 객체
     */
    private fun forParaText(p: HWPParagraph) {
        /**
         * 인라인 문자 [HWPCharControlInline]를 읽는 함수
         *
         * @param [inlineChar] [HWPCharControlInline], 빈 인라인 문자 객체
         */
        fun inlineChar(inlineChar: HWPCharControlInline) {
            val addition: ByteArray = ByteArray(12)
            sr!!.readBytes(addition)
            inlineChar.addition = addition
            inlineChar.code = sr!!.readInt16()
        }

        /**
         * 확장 문자 [HWPCharControlExtend]를 읽는 함수
         *
         * @param [extendChar] [HWPCharControlExtend], 빈 확장 문자 객체
         */
        fun extendChar(extendChar: HWPCharControlExtend) {
            val addition: ByteArray = ByteArray(12)
            sr!!.readBytes(addition)
            extendChar.addition = addition
            extendChar.code = sr!!.readInt16()
        }

        /**
         * 문단 텍스트 [HWPParaText]를 읽고 길이를 반환하는 함수
         *
         * @param [paraText] [HWPParaText]
         * @return [Int] 문자의 길이
         */
        fun hwpChar(paraText: HWPParaText) : Int {
            val code: Short = sr!!.readInt16()
            when (HWPChar.type(code.toInt())) {
                HWPCharType.Normal -> paraText.addNewNormalChar().run {
                    this.code = code
                    return 2
                }
                HWPCharType.ControlChar -> paraText.addNewCharControlChar().run {
                    this.code = code
                    return 2
                }
                HWPCharType.ControlExtend -> paraText.addNewExtendControlChar().run {
                    extendChar(this)
                    return 16
                }
                HWPCharType.ControlInline -> paraText.addNewInlineControlChar().run {
                    inlineChar(this)
                    return 16
                }
                else -> return 2
            }
        }

        p.createText()
        var recordSize: Long = p.header.characterCount * 2
        if (recordSize > 4095)
            recordSize = sr!!.readUInt32()

        var read: Long = 0
        while (read < recordSize)
            read += hwpChar(p.text!!)
    }
}