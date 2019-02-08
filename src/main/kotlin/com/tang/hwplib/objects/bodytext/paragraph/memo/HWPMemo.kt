package com.tang.hwplib.objects.bodytext.paragraph.memo

import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphList

/**
 * 메모 리스트를 나타내는 객체
 *
 * @author accforaus
 *
 * @property [memoIndex] [Long], 메모 인덱스 데이터
 */
class HWPMemoList {
    var memoIndex: Long = 0
}

/**
 * 메모를 나타내는 객체
 *
 * @author accforaus
 *
 * @property [memoList] [HWPMemoList], 메모 리스트 객체
 * @property [listHeader] [ListHeaderForHWPMemo], 메모를 위한 리스트 헤더 객체
 * @property [paragraphList] [HWPParagraphList], 문단 리스트
 */
class HWPMemo {
    var memoList: HWPMemoList = HWPMemoList()
    var listHeader: ListHeaderForHWPMemo = ListHeaderForHWPMemo()
    var paragraphList: HWPParagraphList = HWPParagraphList()
}