package com.tang.hwplib.objects.bodytext.paragraph.header

import com.tang.hwplib.objects.etc.PARA_HEADER
import com.tang.hwplib.objects.docinfo.*
import com.tang.hwplib.objects.bodytext.paragraph.rangetag.HWPParaRangeTag
/**
 * 문단 헤더를 나타내는 객체
 * Tag ID: HWPTAG_PARA_HEADER [PARA_HEADER]
 * 24 bytes
 *
 * @author accforaus
 *
 * @property [lastInList] [Boolean], 현재 문단이 마지막 문단인지 여부, if (nchars & 0x80000000) == true
 * @property [characterCount] [Long], 현재 문단이 가진 글자 수 text(=chars) nchars &= 0x7fffffff (UINT32 - unsigned 4 bytes)
 * @property [controlMask] [HWPControlMask], control mask (UINT32)(1<<ctrlch) 조합 (UINT32 - unsigned 4 bytes)
 * @property [paraShapeId] [Int], 문단 모양[HWPParaShape] 아이디 참조값 (UINT16 - unsigned 2 bytes)
 * @property [styleId] [Short], 스타일 모양[HWPStyle] 아이디 참조값 (UINT8 - unsigned 2 bytes)
 * @property [divideSort] [HWPDivideSort], 단 나누기 종류 (UINT8 - unsigned 1 byte)
 * @property [charShapeCount] [Int], 글자 모양[HWPCharShape] 정보 수 (UINT16 - unsigned 2 bytes)
 * @property [rangeTagCount] [Int], range tag[HWPParaRangeTag] 정보 수 (UINT16 - unsigned 2 bytes)
 * @property [lineAlignCount] [Int], 각 줄에 대한 align에 대한 정보 수 (UINT16 - unsigned 2 bytes)
 * @property [instanceID] [Long], 문단 Instance ID (unique ID) (UINT32 - unsigned 4 bytes)
 * @property [isMergedByTrack] [Int], 변경 추적 병합 문단 여부 [>=5.0.3.2] (UINT16 - unsigned 2 bytes)
 *
 * 텍스트 수가 1 이상이면 문자 수만큼 텍스트를 로드하고 그렇지 않으면 PARA_BREAK로 문단을 생성한다
 */
class HWPParaHeader {
    var lastInList: Boolean = false
    var characterCount: Long = 0
    var controlMask: HWPControlMask = HWPControlMask()
    var paraShapeId: Int = 0
    var styleId: Short = 0
    var divideSort: HWPDivideSort = HWPDivideSort()
    var charShapeCount: Int = 0
    var rangeTagCount: Int = 0
    var lineAlignCount: Int = 0
    var instanceID: Long = 0
    var isMergedByTrack: Int = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPParaHeader] 복사된 객체 반환
     */
    fun copy() : HWPParaHeader = HWPParaHeader().also {
        it.lastInList = this.lastInList
        it.characterCount = this.characterCount
        it.controlMask.value = this.controlMask.value
        it.paraShapeId = this.paraShapeId
        it.styleId = this.styleId
        it.divideSort.value = this.divideSort.value
        it.charShapeCount = this.charShapeCount
        it.rangeTagCount = this.rangeTagCount
        it.lineAlignCount = this.lineAlignCount
        it.instanceID = this.instanceID
        it.isMergedByTrack = this.isMergedByTrack
    }
}