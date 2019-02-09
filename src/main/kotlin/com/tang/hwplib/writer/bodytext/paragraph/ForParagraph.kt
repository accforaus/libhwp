package com.tang.hwplib.writer.bodytext.paragraph

import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphListInterface
import com.tang.hwplib.objects.bodytext.paragraph.charshape.HWPParaCharShape
import com.tang.hwplib.objects.bodytext.paragraph.header.HWPParaHeader
import com.tang.hwplib.objects.bodytext.paragraph.linesegment.HWPParaLineSeg
import com.tang.hwplib.objects.bodytext.paragraph.memo.HWPMemo
import com.tang.hwplib.objects.bodytext.paragraph.memo.HWPMemoList
import com.tang.hwplib.objects.bodytext.paragraph.rangetag.HWPParaRangeTag
import com.tang.hwplib.objects.bodytext.paragraph.text.*
import com.tang.hwplib.objects.etc.*
import com.tang.hwplib.writer.util.StreamWriter
import com.tang.hwplib.writer.bodytext.paragraph.control.forControl

/**
 * 문단 본문 [HWPParagraph]을 쓰는 함수
 *
 * @author accforaus
 *
 * @param [p] [HWPParagraph], 문단 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forParagraph(p: HWPParagraph, sw: StreamWriter) {
    /**
     * 문단 헤더 [HWPParaHeader]를 쓰는 함수
     * Tag ID: [PARA_HEADER]
     *
     * @param [ph] [HWPParaHeader], 문단 헤더 객체
     */
    fun forParaHeader(ph: HWPParaHeader) {
        /**
         * 문단 헤더의 전체 길이를 반환하는 함수
         *
         * @return [Int] 문단 헤더의 전체 길이
         */
        fun getSize(): Int {
            var size: Int = 0
            size += 22
            if (sw.version.isOver(5,0,3,2)) size += 2
            return size
        }

        sw.writeRecordHeader(PARA_HEADER.toInt(), getSize())

        ph.run {
            var value: Long = 0
            if (lastInList) value += -2147483648L
            value += characterCount.and(2147483647L)
            sw.writeUInt32(value)

            sw.writeUInt32(controlMask.value)
            sw.writeUInt16(paraShapeId)
            sw.writeUInt8(styleId)
            sw.writeUInt8(divideSort.value)
            sw.writeUInt16(charShapeCount)
            sw.writeUInt16(rangeTagCount)
            sw.writeUInt16(lineAlignCount)
            sw.writeUInt32(instanceID)
            if (sw.version.isOver(5,0,3,2)) sw.writeUInt16(isMergedByTrack)
        }
    }

    /**
     * 문단의 레이아웃 [HWPParaLineSeg]을 쓰는 함수
     * Tag ID: [PARA_LINE_SEG]
     *
     * @param [pls] [HWPParaLineSeg], 문단의 레이아웃 객체
     */
    fun forParaLineSeg(pls: HWPParaLineSeg?) {
        pls?.run {
            var size: Long = lineSegItemList.size.toLong() * 36
            size = if (size > 4095) 4095 else size
            sw.writeRecordHeader(PARA_LINE_SEG.toInt(), size.toInt())

            size = lineSegItemList.size.toLong() * 36
            if (size > 4095) sw.writeUInt32(size)

            for (lsi in lineSegItemList) {
                lsi.run {
                    sw.writeUInt32(textStartPosition)
                    sw.writeInt32(lineVerticalPosition)
                    sw.writeInt32(lineHeight)
                    sw.writeInt32(textPartHeight)
                    sw.writeInt32(distanceBaseLineToLineVerticalPosition)
                    sw.writeInt32(lineSpace)
                    sw.writeInt32(startPositionFromColumn)
                    sw.writeInt32(segmentWidth)
                    sw.writeUInt32(tag.value)
                }
            }
        }
    }

    /**
     * 문단의 영역 태그 [HWPParaRangeTag]를 쓰는 함수
     * Tag ID: [PARA_RANGE_TAG]
     *
     * @param [prt] [HWPParaRangeTag], 문단의 영역 태그 객체
     */
    fun forParaRangeTag(prt: HWPParaRangeTag?) {
        prt?.run {
            sw.writeRecordHeader(PARA_RANGE_TAG.toInt(), rangeTagItemList.size * 12)
            for (rti in rangeTagItemList) {
                rti.run {
                    sw.writeUInt32(rangeStart)
                    sw.writeUInt32(rangeEnd)
                    sw.writeBytes(data!!, 3)
                    sw.writeUInt8(sort)
                }
            }
        }
    }

    /**
     * 문단의 텍스트 [HWPParaText]를 쓰는 함수
     * Tag ID: [PARA_TEXT]
     *
     * @param [p] [HWPParagraph], 문단 객체
     */
    fun forParaText(p: HWPParagraph) {
        /**
         * 확장 문자 [HWPCharControlExtend]를 쓰는 함수
         *
         * @param [hc] [HWPCharControlExtend], 확장 문자 객체
         */
        fun forControlExtend(hc: HWPCharControlExtend) {
            hc.run {
                sw.writeInt16(code)
                sw.writeBytes(addition)
                sw.writeInt16(code)
            }
        }

        /**
         * 인라인 문자 [HWPCharControlInline]를 쓰는 함수
         *
         * @param [hc] [HWPCharControlInline], 인라인 문자 객체
         */
        fun forControlInline(hc: HWPCharControlInline) {
            hc.run {
                sw.writeInt16(code)
                sw.writeBytes(addition)
                sw.writeInt16(code)
            }
        }

        /**
         * 레코드 헤더를 쓰는 함수
         *
         * @param [size] [Long], 문자 길이
         */
        fun recordHeader(size: Long) {
            if (size > 4095.toLong()) sw.writeRecordHeader(PARA_TEXT.toInt(), 4095)
            else sw.writeRecordHeader(PARA_TEXT.toInt(), size.toInt())
        }

        /**
         * 실제 레코드 크기를 반환하는 함수
         *
         * @param [size] [Long] 실제 레코드 크기를 쓰는 함수
         */
        fun realRecordSize(size: Long) {
            if (size > 4095) sw.writeUInt32(size)
        }

        p.text?.run {
            val size: Long = p.header.characterCount * 2
            recordHeader(size)
            realRecordSize(size)
            for (hc in charList) {
                when (hc) {
                    is HWPCharNormal -> sw.writeInt16(hc.code)
                    is HWPCharControlChar -> sw.writeInt16(hc.code)
                    is HWPCharControlExtend -> forControlExtend(hc)
                    is HWPCharControlInline -> forControlInline(hc)
                }
            }
        }
    }

    /**
     * 문단의 글자 모양 [HWPParaCharShape]을 쓰는 함수
     * Tag ID: [PARA_CHAR_SHAPE]
     *
     * @param [pcs] [HWPParaCharShape], 문단의 글자 모양 객체
     **/
    fun forParaCharShape(pcs: HWPParaCharShape?) {
        pcs?.run {
            sw.writeRecordHeader(PARA_CHAR_SHAPE.toInt(), positionShapeIdPairList.size * 8)
            for (cpsip in positionShapeIdPairList) {
                sw.writeUInt32(cpsip.position)
                sw.writeUInt32(cpsip.shapeId)
            }
        }
    }

    /**
     * 메모 [HWPMemo]를 쓰는 함수
     * Tag ID: [MEMO_LIST]
     *
     * @param [memoList] [ArrayList], 메모 리스트
     */
    fun forMemo(memoList: ArrayList<HWPMemo>?) {
        /**
         * 메모 리스트 [HWPMemoList]를 쓰는 함수
         *
         * @param [ml] [HWPMemoList], 메모 리스트 객체
         */
        fun forMemoList(ml: HWPMemoList?) {
            ml?.run {
                sw.writeRecordHeader(MEMO_LIST.toInt(), 4)
                sw.writeUInt32(ml.memoIndex)
            }
        }

        /**
         * 메모 [HWPMemo]를 쓰는 함수
         *
         * @param [m] [HWPMemo], 메모 객체
         */
        fun memo(m: HWPMemo) {
            forMemoList(m.memoList)
            m.listHeader.run {
                sw.writeRecordHeader(LIST_HEADER.toInt(), 16)
                sw.writeInt32(paraCount)
                sw.writeUInt32(property.value)
                sw.writeUInt32(textWidth)
                sw.writeUInt32(textHeight)
            }
            forParagraphList(m.paragraphList, sw)
        }

        memoList?.run {
            for (m in this) memo(m)
        }
    }

    p.run {
        forParaHeader(header)

        sw.upRecordLevel()

        forParaText(this)
        forParaCharShape(paraCharShape)
        forParaLineSeg(lineSeg)
        forParaRangeTag(rangeTag)
        forMemo(memoList)
        if (controlList != null) {
            for (c in controlList!!)
                forControl(c, sw)
        }
        sw.downRecordLevel()
    }
}

/**
 * 문단 리스트 [HWPParagraphListInterface]를 쓰는 함수
 *
 * @author accforaus
 *
 * @param [pl] [HWPParagraphListInterface], 문단 리스트
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forParagraphList(pl: HWPParagraphListInterface, sw: StreamWriter) {
     for (p in pl) forParagraph(p, sw)
}