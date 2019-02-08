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
    var lineSegItemList: ArrayList<LineSegItem> = ArrayList()

    fun addNewLineSegItem() : LineSegItem = LineSegItem().also { lineSegItemList.add(it) }
}