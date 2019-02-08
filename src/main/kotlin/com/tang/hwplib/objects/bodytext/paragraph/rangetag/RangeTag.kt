package com.tang.hwplib.objects.bodytext.paragraph.rangetag

import com.tang.hwplib.objects.etc.PARA_RANGE_TAG

/**
 * 영역 태그 아이템을 나타내는 객체
 * 12 bytes
 *
 * @author accforaus
 *
 * @property [rangeStart] [Long], 영역 시작 (UINT32 - unsigned 4 bytes)
 * @property [rangeEnd] [Long], 영역 끝 (UINT32 - unsigned 4 bytes)
 * @property [sort] [Short], 종류 (UINT32 상위 8 bit - unsigned 1 byte)
 * @property [data] [ByteArray], 데이터 (UINT32 하위 24 bit - unsigned 3 bytes)
 */
class HWPRangeTagItem {
    var rangeStart: Long = 0
    var rangeEnd: Long = 0
    var sort: Short = 0
    var data: ByteArray? = null
}

/**
 * 문단의 영역 태그를 나타내는 객체
 * Tag ID: HWPTAG_PARA_RANGE_TAG [PARA_RANGE_TAG]
 * 가변 길이
 *
 * range tag 정보를 정보 수 만큼 읽어 온다
 * range tag는 텍스트의 일정 영역을 마킹하는 용도
 * 글자 모양과 달리 각 영역은 서로 겹칠 수 있다
 * (형광펜, 교정 부호 등)
 *
 * @author accforaus
 *
 * @property [rangeTagItemList] [ArrayList] 영역 태그 아이템 객체를 담은 리스트
 */
class HWPParaRangeTag {
    var rangeTagItemList: ArrayList<HWPRangeTagItem> = ArrayList()

    fun addNewRangeTagItem() : HWPRangeTagItem = HWPRangeTagItem().also { rangeTagItemList.add(it) }
}