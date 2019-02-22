package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.objects.docinfo.borderfill.*
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPFillInfo
import com.tang.hwplib.objects.etc.Color4Byte
import com.tang.hwplib.objects.etc.BORDER_FILL
/**
 * 테두리/배경을 나타내는 객체
 * Tag ID: HWPTAG_BORDER_FILL [BORDER_FILL]
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [property] [HWPBorderFillProperty], 속성 (UINT16 - unsigned 2 bytes)
 * @property [leftBorder] [HWPEachBorder], 왼쪽 방향 테두리 (UINT8 - unsigned 1 byte)
 * @property [rightBorder] [HWPEachBorder], 오른쪽 방향 테두리 (UINT8 - unsigned 1 byte)
 * @property [topBorder] [HWPEachBorder], 위쪽 방향 테두리 (UINT8 - unsigned 1 byte)
 * @property [bottomBorder] [HWPEachBorder], 아래쪽 방향 테두리 (UINT8 - unsigned 1 byte)
 * @property [diagonalSort] [HWPBorderType], 대각선 종류 (UINT8 - unsigned 1 byte)
 * @property [diagonalThickness] [HWPBorderThickness], 대각선 굵기 (UINT8 - unsigned 1 byte)
 * @property [diagonalColor] [Color4Byte], 대각선 색상 (COLORREF - unsigned 4 bytes)
 * @property [fillInfo] [HWPFillInfo], 채우기 정보 (BYTE stream - n bytes)
 */
class HWPBorderFill : HWPDocInfoElement() {
    var property: HWPBorderFillProperty = HWPBorderFillProperty()
    var leftBorder: HWPEachBorder = HWPEachBorder()
    var rightBorder: HWPEachBorder = HWPEachBorder()
    var topBorder: HWPEachBorder = HWPEachBorder()
    var bottomBorder: HWPEachBorder = HWPEachBorder()
    var diagonalSort: HWPDiagonalSort = HWPDiagonalSort.Slash
    var diagonalThickness: HWPBorderThickness = HWPBorderThickness.MM0_1
    var diagonalColor: Color4Byte = Color4Byte()
    var fillInfo: HWPFillInfo = HWPFillInfo()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPBorderFill] 복사된 객체 반환
     */
    override fun copy(): HWPBorderFill = HWPBorderFill().also {
        it.property.value = this.property.value
        it.leftBorder = this.leftBorder.copy()
        it.rightBorder = this.rightBorder.copy()
        it.topBorder = this.topBorder.copy()
        it.bottomBorder = this.bottomBorder.copy()
        it.diagonalSort = HWPDiagonalSort.valueOf(this.diagonalSort.value)
        it.diagonalThickness = HWPBorderThickness.valueOf(this.diagonalThickness.value)
        it.diagonalColor.value = this.diagonalColor.value
        it.fillInfo = this.fillInfo.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPBorderFill] 생성된 객체 반환
         */
        fun build(property: HWPBorderFillProperty = HWPBorderFillProperty.build(),
                  leftBorder: HWPEachBorder = HWPEachBorder.build(),
                  rightBorder: HWPEachBorder = HWPEachBorder.build(),
                  topBorder: HWPEachBorder = HWPEachBorder.build(),
                  bottomBorder: HWPEachBorder = HWPEachBorder.build(),
                  diagonalSort: HWPDiagonalSort = HWPDiagonalSort.Slash,
                  diagonalThickness: HWPBorderThickness = HWPBorderThickness.MM0_1,
                  diagonalColor: Color4Byte = Color4Byte.build(),
                  fillInfo: HWPFillInfo = HWPFillInfo.build())
                : HWPBorderFill = HWPBorderFill().apply {
            this.property = property
            this.leftBorder = leftBorder
            this.rightBorder = rightBorder
            this.topBorder = topBorder
            this.bottomBorder = bottomBorder
            this.diagonalSort = diagonalSort
            this.diagonalThickness = diagonalThickness
            this.diagonalColor = diagonalColor
            this.fillInfo = fillInfo
        }
    }
}