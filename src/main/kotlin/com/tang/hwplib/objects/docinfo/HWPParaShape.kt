package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.annotation.ID
import com.tang.hwplib.annotation.IDTypes
import com.tang.hwplib.annotation.LinkID
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
@LinkID class HWPParaShape : HWPDocInfoElement() {
    var property1: HWPParaShapeProperty1 = HWPParaShapeProperty1()
    var leftMargin: Int = 0
    var rightMargin: Int = 0
    var indent: Int = 0
    var topParaSpace: Int = 0
    var bottomParaSpace: Int = 0
    var lineSpace: Int = 0
    @ID(IDTypes.TabDef)
    var tabDefId: Int = 0
    @ID(IDTypes.Numbering)
    var paraHeadId: Int = 0
    @ID(IDTypes.BorderFill)
    var borderFillId: Int = 0
    var leftBorderSpace: Short = 0
    var rightBorderSpace: Short = 0
    var topBorderSpace: Short = 0
    var bottomBorderSpace: Short = 0
    var property2: HWPParaShapeProperty2 = HWPParaShapeProperty2()
    var property3: HWPParaShapeProperty3 = HWPParaShapeProperty3()
    var lineSpace2: Long = 0
    var unknown: Long = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPParaShape] 복사된 객체 반환
     */
    override fun copy() : HWPParaShape = HWPParaShape().also {
        it.property1.value = this.property1.value
        it.leftMargin = this.leftMargin
        it.rightMargin = this.rightMargin
        it.indent = this.indent
        it.topParaSpace = this.topParaSpace
        it.bottomParaSpace = this.bottomParaSpace
        it.lineSpace = this.lineSpace
        it.tabDefId = this.tabDefId
        it.paraHeadId = this.paraHeadId
        it.borderFillId = this.borderFillId
        it.leftBorderSpace = this.leftBorderSpace
        it.rightBorderSpace = this.rightBorderSpace
        it.topBorderSpace = this.topBorderSpace
        it.bottomBorderSpace = this.bottomBorderSpace
        it.property2.value = this.property2.value
        it.property3.value = this.property3.value
        it.lineSpace2 = this.lineSpace2
        it.unknown = this.unknown
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPParaShape] 생성된 객체 반환
         */
        fun build(property1: HWPParaShapeProperty1 = HWPParaShapeProperty1.build(),
                  leftMargin: Int = 0, rightMargin: Int = 0, indent: Int = 0,
                  topParaSpace: Int = 0, bottomParaSpace: Int = 0,
                  lineSpace: Int = 0, tabDefId: Int = 0, paraHeadId: Int = 0,
                  borderFillId: Int = 0, leftBorderSpace: Short = 0,
                  rightBorderSpace: Short = 0, topBorderSpace: Short = 0,
                  bottomBorderSpace: Short = 0,
                  property2: HWPParaShapeProperty2 = HWPParaShapeProperty2.build(),
                  property3: HWPParaShapeProperty3 = HWPParaShapeProperty3.build(0),
                  lineSpace2: Long = 0, unknown: Long = 0)
                : HWPParaShape = HWPParaShape().apply {
            this.property1 = property1
            this.leftMargin = leftMargin
            this.rightMargin = rightMargin
            this.indent = indent
            this.topParaSpace = topParaSpace
            this.bottomParaSpace = bottomParaSpace
            this.lineSpace = lineSpace
            this.tabDefId = tabDefId
            this.paraHeadId = paraHeadId
            this.borderFillId = borderFillId
            this.leftBorderSpace = leftBorderSpace
            this.rightBorderSpace = rightBorderSpace
            this.topBorderSpace = topBorderSpace
            this.bottomBorderSpace = bottomBorderSpace
            this.property2 = property2
            this.property3 = property3
            this.lineSpace2 = lineSpace2
            this.unknown = unknown
        }
    }
}