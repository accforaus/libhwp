package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.annotation.ID
import com.tang.hwplib.annotation.IDTypes
import com.tang.hwplib.annotation.LinkID
import com.tang.hwplib.objects.docinfo.charshape.*
import com.tang.hwplib.objects.etc.Color4Byte
import com.tang.hwplib.objects.etc.CHAR_SHAPE
/**
 * 글자 모양을 나타내는 객체
 * Tag ID: HWPTAG_CHAR_SHAPE [CHAR_SHAPE]
 * 72 byte
 *
 * @author accforaus
 *
 * @property [faceNameIds] [HWPFaceNameIds], 언어별 글꼴 ID(FaceID) 참조값 (WORD array[7] - unsigned 14 bytes)
 * @property [ratios] [HWPRatios], 언어별 장평 50% - 200% (UINT8 array[7] - unsigned 7 bytes)
 * @property [charSpaces] [HWPCharSpaces], 언어별 자간 -50% ~ 50% (INT8 array[7] - signed 7 bytes)
 * @property [relativeSizes] [HWPRelativeSizes], 언어별 상대 크기 10% ~ 250% (UINT8 array[7] - unsigned 7 bytes)
 * @property [charOffsets] [HWPCharOffsets], 언어별 글자 위치 -100% ~ 100% (INT8 array[7] - signed 7 bytes)
 * @property [baseSize] [Int], 기준 크기 0pt ~ 4096pt (INT32 - signed 4 bytes)
 * @property [property] [HWPCharShapeProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [shadowGap1] [Byte], 그림자 간격1 -100% ~ 100% (INT8 - signed 1 byte)
 * @property [shadowGap2] [Byte], 그림자 간격2 -100% ~ 100% (INT8 - signed 1 byte)
 * @property [charColor] [Color4Byte], 글자 색 (COLORREF - unsigned 4 bytes)
 * @property [underLineColor] [Color4Byte], 밑줄 색 (COLORREF - unsigned 4 bytes)
 * @property [shadeColor] [Color4Byte], 음영 색 (COLORREF - unsigned 4 bytes)
 * @property [shadowColor] [Color4Byte], 그림자 색 (COLORREF - unsigned 4 bytes)
 * @property [borderFillId] [Int], 글자 테두리/배경 ID(CharShapeBorderFill) 참조 값 [>=5.0.2.1] (UINT16 - unsigned 2 bytes)
 * @property [strikeLineColor] [Color4Byte], 취소선 색 [>=5.0.3.0] (COLORREF - unsigned 4 bytes)
 */
@LinkID class HWPCharShape : HWPDocInfoElement() {
    @ID(IDTypes.FaceName)
    var faceNameIds: HWPFaceNameIds = HWPFaceNameIds()
    var ratios: HWPRatios = HWPRatios()
    var charSpaces: HWPCharSpaces = HWPCharSpaces()
    var relativeSizes: HWPRelativeSizes = HWPRelativeSizes()
    var charOffsets: HWPCharOffsets = HWPCharOffsets()
    var baseSize: Int = 0
    var property: HWPCharShapeProperty = HWPCharShapeProperty()
    var shadowGap1: Byte = 0
    var shadowGap2: Byte = 0
    var charColor: Color4Byte = Color4Byte()
    var underLineColor: Color4Byte = Color4Byte()
    var shadeColor: Color4Byte = Color4Byte()
    var shadowColor: Color4Byte = Color4Byte()
    var borderFillId: Int = 0
    var strikeLineColor: Color4Byte = Color4Byte()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCharShape] 복사된 객체 반환
     */
    override fun copy() : HWPCharShape = HWPCharShape().also {
        it.faceNameIds = this.faceNameIds.copy()
        it.ratios = this.ratios.copy()
        it.charSpaces = this.charSpaces.copy()
        it.relativeSizes = this.relativeSizes.copy()
        it.charOffsets = this.charOffsets.copy()
        it.baseSize = this.baseSize
        it.property.value = this.property.value
        it.shadowGap1 = this.shadowGap1
        it.shadowGap2 = this.shadowGap2
        it.charColor.value = this.charColor.value
        it.underLineColor.value = this.underLineColor.value
        it.shadeColor.value = this.shadeColor.value
        it.shadowColor.value = this.shadowColor.value
        it.borderFillId = this.borderFillId
        it.strikeLineColor.value = this.strikeLineColor.value
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPCharShape] 생성된 객체 반환
         */
        fun build(faceNameIds: HWPFaceNameIds = HWPFaceNameIds.build(),
                  ratios: HWPRatios = HWPRatios.build(),
                  charSpaces: HWPCharSpaces = HWPCharSpaces.build(),
                  relativeSizes: HWPRelativeSizes = HWPRelativeSizes.build(),
                  charOffsets: HWPCharOffsets = HWPCharOffsets.build(),
                  baseSize: Int = 0,
                  property: HWPCharShapeProperty = HWPCharShapeProperty.build(),
                  shadowGap1: Byte = 0, shadowGap2: Byte = 0,
                  charColor: Color4Byte = Color4Byte.build(),
                  underLineColor: Color4Byte = Color4Byte.build(),
                  shadeColor: Color4Byte = Color4Byte.build(),
                  shadowColor: Color4Byte = Color4Byte.build(),
                  borderFillId: Int = 0,
                  strikeLineColor: Color4Byte = Color4Byte.build())
                : HWPCharShape = HWPCharShape().apply {
            this.faceNameIds = faceNameIds
            this.ratios = ratios
            this.charSpaces = charSpaces
            this.relativeSizes = relativeSizes
            this.charOffsets = charOffsets
            this.baseSize = baseSize
            this.property = property
            this.shadowGap1 = shadowGap1
            this.shadowGap2 = shadowGap2
            this.charColor = charColor
            this.underLineColor = underLineColor
            this.shadeColor = shadeColor
            this.shadowColor = shadowColor
            this.borderFillId = borderFillId
            this.strikeLineColor = strikeLineColor
        }
    }

    override fun equals(other: Any?): Boolean = (other as HWPCharShape).let {
        return ratios == it.ratios
                && charSpaces == it.charSpaces
                && relativeSizes == it.relativeSizes
                && charOffsets == it.charOffsets
                && baseSize == it.baseSize
                && property == it.property
                && shadowGap1 == it.shadowGap1
                && shadowGap2 == it.shadowGap2
                && charColor == it.charColor
                && underLineColor == it.underLineColor
                && shadeColor == it.shadeColor
                && shadowColor == it.shadowColor
                && strikeLineColor == it.strikeLineColor
    }
}