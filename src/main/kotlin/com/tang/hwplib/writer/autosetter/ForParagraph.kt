package com.tang.hwplib.writer.autosetter

import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphListInterface
import com.tang.hwplib.objects.bodytext.paragraph.text.*
import com.tang.hwplib.writer.autosetter.control.autoSetControl

/**
 * 고유한 ID를 나타내는 객체
 *
 * @author accforaus
 *
 * @property [START_OBJECT_ID] [Int], 시작 ID
 * @property [id] [Long], 고유 ID값
 */
class InstanceID {
    val START_OBJECT_ID: Int = 0x5bb840e1

    var id: Long = START_OBJECT_ID.toLong()

    /**
     * 고유 ID를 반환하는 함수
     *
     * @return [Long] 고유 ID를 반환 후 증가
     */
    fun get(): Long = id++
}

/**
 * 문단 [HWPParagraph]의 자동 설정을 수행하는 함수
 *
 * @author accforaus
 *
 * @param [p] [HWPParagraph], 문단 객체
 * @param [lastInList] [Boolean], 섹션의 마지막 문단 여부
 * @param [iid] [InstanceID], 고유 ID값
 */
fun autoSetParagraph(p: HWPParagraph, lastInList: Boolean, iid: InstanceID) {
    /**
     * 글자 수의 자동 설정을 수행하는 함수
     *
     * @param [text] [HWPParaText], 문단의 글자 객체
     * @return [Int] 문단의 글자 수 반환
     */
    fun getCharacterCount(text: HWPParaText?): Int = Unit.let {
        var charCount: Int = 0
        text?.let {
            for (ch in it.charList) {
                when(ch) {
                    is HWPCharNormal -> charCount += 1
                    is HWPCharControlChar -> charCount += 1
                    is HWPCharControlExtend -> charCount += 8
                    is HWPCharControlInline -> charCount += 8
                }
            }
        }
        return if (charCount == 0) 1 else charCount
    }

    p.header.run {
        this.lastInList = lastInList
        characterCount = getCharacterCount(p.text).toLong()
        p.text?.let {
            controlMask.run {
                this.value = 0
                for (ch in it.charList) {
                    when (ch.code.toInt()) {
                        2 -> setHasSectColDef(true)
                        3 -> setFieldStart(true)
                        4 -> setFieldEnd(true)
                        8 -> setTitleMark(true)
                        9 -> setTab(true)
                        10 -> setHasLineBreak(true)
                        11 -> setGsoTable(true)
                        15 -> setHiddenComment(true)
                        16 -> setHeaderFooter(true)
                        17 -> setFootnoteEndnote(true)
                        18 -> setAutoNumber(true)
                        21 -> setPageControl(true)
                        22 -> setBookmark(true)
                        23 -> setAdditionalTextOverlappingLetter(true)
                        24 -> setHyphen(true)
                        30 -> setBundleBlank(true)
                        31 -> setFixWidthBlank(true)
                    }
                }
            }
        }
        charShapeCount = if (p.paraCharShape != null) p.paraCharShape!!.positionShapeIdPairList.size else 0
        rangeTagCount = if (p.rangeTag != null) p.rangeTag!!.rangeTagItemList.size else 0
        lineAlignCount = if (p.lineSeg != null) p.lineSeg!!.lineSegItemList.size else 0
        instanceID = 0
    }

    p.controlList?.run {
        for (c in this) autoSetControl(c, iid)
    }
}

/**
 * 문단 리스트 [HWPParagraphListInterface]의 자동 설정을 수행하는 함수
 *
 * @author accforaus
 *
 * @param [pli] [HWPParagraphListInterface], 문단 리스트
 * @param [iid] [InstanceID], 고유한 ID
 */
fun autoSetParagraphList(pli: HWPParagraphListInterface, iid: InstanceID) {
    for ((index, value) in pli.iterator().withIndex()) {
        if (index == pli.getParagraphCount() - 1)
            autoSetParagraph(value, true, iid)
        else
            autoSetParagraph(value, false, iid)
    }
}