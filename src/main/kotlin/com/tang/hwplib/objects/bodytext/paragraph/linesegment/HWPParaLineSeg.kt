package com.tang.hwplib.objects.bodytext.paragraph.linesegment

import com.tang.hwplib.objects.etc.PARA_LINE_SEG

/**
 * 문단의 레이아웃을 나타내는 객체
 * Tag ID: HWPTAG_PARA_LINE_SEG [PARA_LINE_SEG]
 * 가변 길이
 *
 * 문단의 각 줄을 출력할 때 사용한 Cahce 정보
 * 문단 정보의 '각 줄에 대한 align에 대한 정보수'만큼 반복
 *
 * @author accforaus
 *
 * @property [lineSegItemList] [ArrayList], 레이아웃 세그먼트 아이템의 리스트
 */
class HWPParaLineSeg {
    var lineSegItemList: ArrayList<HWPLineSegItem> = ArrayList()

    /**
     * 레이아웃 세그먼트 아이팀을 추가하고 반환하는 함수
     *
     * @return [HWPLineSegItem] 생성된 객체 반환
     */
    fun addNewLineSegItem() : HWPLineSegItem = HWPLineSegItem().also { lineSegItemList.add(it) }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPParaLineSeg] 복사된 객체 반환
     */
    fun copy() : HWPParaLineSeg = HWPParaLineSeg().also {
        for (lineSegItem in this.lineSegItemList)
            it.lineSegItemList.add(lineSegItem.copy())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPParaLineSeg] 생성된 객체 반환
         */
        fun build(
                lineSegItemGenerator: () -> ArrayList<HWPLineSegItem> = {ArrayList()}
        ) : HWPParaLineSeg = HWPParaLineSeg().apply {
            this.lineSegItemList = lineSegItemGenerator()
        }
    }
}