package com.tang.hwplib.objects.bodytext

import com.tang.hwplib.builder.bodytext.paragraph.HWPParagraphBuilder
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPBatangPageInfo
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphListInterface
import com.tang.hwplib.objects.bodytext.paragraph.charshape.HWPCharPositionShapeIdPair
import com.tang.hwplib.objects.bodytext.paragraph.charshape.HWPParaCharShape

/**
 * 섹션을 나타내는 객체
 * 가변 길이
 * @see [HWPParagraphListInterface]
 *
 * @author accforaus
 *
 * @property [paragraphList] [ArrayList], 문단 리스트
 * @property [lastBatangPageInfo] [HWPBatangPageInfo], 마지막 바탕 정보
 */
class HWPSection: HWPParagraphListInterface {
    var paragraphList: ArrayList<HWPParagraph> = ArrayList()
    var lastBatangPageInfo: HWPBatangPageInfo? = null

    /**
     * 새로운 문단을 추가하고 반환하는 함수
     *
     * @return [HWPParagraph] 생성된 문단 객체 반환
     */
    override fun addNewParagraph(): HWPParagraph = HWPParagraph().apply { paragraphList.add(this) }

    /**
     * 문단 수를 반환하는 함수
     *
     * @return [Int] 문단 수 반환
     */
    override fun getParagraphCount(): Int = paragraphList.size

    /**
     * [index] 번째의 문단을 반환하는 함수
     *
     * @param [index] [Int], 반환할 인덱스
     * @return [HWPParagraph] [index] 번째 문단 반환
     */
    override fun getParagraph(index: Int): HWPParagraph = paragraphList[index]

    /**
     * 반복자를 반환하는 함수
     *
     * @return [Iterator] 문단 반복자 반환
     */
    override fun iterator(): Iterator<HWPParagraph> = paragraphList.iterator()

    /**
     * 마지막 바탕 정보를 생성하는 함수
     */
    fun createLastBatangPageInfo() { lastBatangPageInfo = HWPBatangPageInfo() }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPSection] 복사된 객체 반환
     */
    fun copy() : HWPSection = HWPSection().also {
        for (paragraph in this.paragraphList)
            it.paragraphList.add(paragraph.copy())
        this.lastBatangPageInfo?.run { it.lastBatangPageInfo = this.copy() }
    }

    fun addNewParagraph(paragraphBuilder: HWPParagraphBuilder) : HWPParagraph = paragraphBuilder.build().apply {
        if (this.paraCharShape == null) {
            this.paraCharShape = HWPParaCharShape().apply {
                positionShapeIdPairList.add(HWPCharPositionShapeIdPair(0, 0))
            }
        }
        paragraphList.add(this)
    }
}