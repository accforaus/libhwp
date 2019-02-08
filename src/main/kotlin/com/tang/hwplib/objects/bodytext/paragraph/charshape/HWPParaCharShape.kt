package com.tang.hwplib.objects.bodytext.paragraph.charshape

import com.tang.hwplib.objects.docinfo.HWPCharShape
import com.tang.hwplib.objects.etc.PARA_CHAR_SHAPE
/**
 * 글자 위치와 모양 쌍을 나타내는 객체
 * 8 bytes
 *
 * @author accforaus
 *
 * @property [position] [Long], 글자 모양[HWPCharShape]이 바뀌는 시작 위치 (UINT32 - unsigned 4 bytes)
 * @property [shapeId] [Long], 글자 모양[HWPCharShape] ID (UINT32 - unsigned 4 bytes)
 */
open class HWPCharPositionShapeIdPair(var position: Long, var shapeId: Long)

/**
 * 문단의 글자 모양을 나타내는 객체
 * Tag ID: HWPTAG_PARA_CHAR_SHAPE [PARA_CHAR_SHAPE]
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [positionShapeIdPairList] [HWPCharPositionShapeIdPair], 글자 위치와 모양 쌍을 담고 있는 리스트
 */
class HWPParaCharShape {
    var positionShapeIdPairList: ArrayList<HWPCharPositionShapeIdPair> = ArrayList()

    /**
     * 글자 위치와 모양을 추가하는 함수
     *
     * @param [position] [Long], 글자 시작 위치
     * @param [charShapeId] [Long], 글자 모양[HWPCharShape] ID
     */
    fun addParaCharShape(position: Long, charShapeId: Long) {
        val cpsp: HWPCharPositionShapeIdPair = HWPCharPositionShapeIdPair(position, charShapeId)
        positionShapeIdPairList.add(cpsp)
    }
}