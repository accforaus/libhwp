package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.objects.docinfo.parashape.HWPParaShapeProperty1
import com.tang.hwplib.objects.docinfo.parashape.HWPParaShapeProperty2
import com.tang.hwplib.objects.docinfo.parashape.HWPParaShapeProperty3
import com.tang.hwplib.objects.etc.PARA_SHAPE
/**
 * 문단 모양을 나타내는 객체
 * Tag ID: HWPTAG_PARA_SHAPE [PARA_SHAPE]
 * 54 bytes
 *
 * @author accforaus
 *
 * @property [property1] [HWPParaShapeProperty1], 속성 1 (UINT32 - unsigned 4 bytes)
 * @property [leftMargin] [Int], 왼쪽 여백 (INT32 - signed 4 bytes)
 * @property [rightMargin] [Int], 오른족 여백 (INT32 - signed 4 bytes)
 * @property [indent] [Int], 들여 쓰기/내어 쓰기 (INT32 - signed 4 bytes)
 * @property [topParaSpace] [Int], 문단 간격 위 (INT32 - signed 4 bytes)
 * @property [bottomParaSpace] [Int], 문단 간격 아래 (INT32 - signed 4 bytes)
 * @property [lineSpace] [Int], 줄간격. 한글 2007 이하 버전[<5.0.2.5]에서 사용 (INT32 - signed 4 bytes)
 * @property [tabDefId] [Int], 탭 정의[HWPTabDef] 아이디 참조 값 (UINT16 - unsigned 2 bytes)
 * @property [paraHeadId] [Int], 번호 문단[HWPNumbering] ID 또는 글머리표[HWPBullet] 문단 모양 ID 참조 값 (UINT16 - unsigned 2 bytes)
 * @property [borderFillId] [Int], 테두리/배경[HWPBorderFill] 모양 ID 참조 값 (UINT16 - unsigned 2 bytes)
 * @property [leftBorderSpace] [Short], 문단 테두리 왼쪽 간격 (INT16 - signed 2 bytes)
 * @property [rightBorderSpace] [Short], 문단 테두리 오른쪽 간격 (INT16 - signed 2 bytes)
 * @property [topBorderSpace] [Short], 문단 테두리 위쪽 간격 (INT16 - signed 2 bytes)
 * @property [bottomBorderSpace] [Short], 문단 테두리 아래쪽 간격 (INT16 - signed 2 bytes)
 * @property [property2] [HWPParaShapeProperty2], 속성 2 [>=5.0.1.7] (UINT32 - unsigned 4 bytes)
 * @property [property3] [HWPParaShapeProperty3], 속성 3 [>=5.0.2.5] (UINT32 - unsigned 4 bytes)
 * @property [lineSpace2] [Long], 줄 간격 [>=5.0.2.5] (UINT32 - unsigned 4 bytes)
 * @property [unknown] [Long], 알 수 없는 값 (UINT32 - unsigned 4 bytes)
 */
class HWPParaShape {
    var property1: HWPParaShapeProperty1 = HWPParaShapeProperty1()
    var leftMargin: Int = 0
    var rightMargin: Int = 0
    var indent: Int = 0
    var topParaSpace: Int = 0
    var bottomParaSpace: Int = 0
    var lineSpace: Int = 0
    var tabDefId: Int = 0
    var paraHeadId: Int = 0
    var borderFillId: Int = 0
    var leftBorderSpace: Short = 0
    var rightBorderSpace: Short = 0
    var topBorderSpace: Short = 0
    var bottomBorderSpace: Short = 0
    var property2: HWPParaShapeProperty2 = HWPParaShapeProperty2()
    var property3: HWPParaShapeProperty3 = HWPParaShapeProperty3()
    var lineSpace2: Long = 0
    var unknown: Long = 0
}