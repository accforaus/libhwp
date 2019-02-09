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

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPMemoList] 복사된 객체 반환
     */
    fun copy() : HWPMemoList = HWPMemoList().also { it.memoIndex = this.memoIndex }
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

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPMemo] 복사된 객체 반환
     */
    fun copy() : HWPMemo = HWPMemo().also {
        it.memoList = this.memoList
        it.listHeader = this.listHeader.copy()
        it.paragraphList = this.paragraphList.copy()
    }
}