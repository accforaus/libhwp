package com.tang.hwplib.objects.docinfo.facename

/**
 * 대체 글꼴 유형
 * BYTE - unsigned 1 byte
 *
 * @author accforaus
 *
 * @property [value] [Byte], 대체 글꼴 유형정보를 가지고 있는 데이터
 */
enum class HWPFontType(v: Byte) {
    Unknown(0.toByte()), TTF(1.toByte()), HFT(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPFontType] enum 값
         */
        fun valueOf(v: Byte) : HWPFontType {
            for (ft in values())
                if (ft.value == v)
                    return ft
            return Unknown
        }
    }
}

/**
 * 글꼴 유형 정보
 * BYTE array[len * 2] = unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [fontType] [Short], 글꼴 계열 (BYTE - unsigned 1 byte)
 * @property [serifType] [Short], 세리프 유형 (BYTE - unsigned 1 byte)
 * @property [thickness] [Short], 굵기 (BYTE - unsigned 1 byte)
 * @property [ratio] [Short], 비례 (BYTE - unsigned 1 byte)
 * @property [contrast] [Short], 대조 (BYTE - unsigned 1 byte)
 * @property [strokeDeviation] [Short], 스트로크 편차 (BYTE - unsigned 1 byte)
 * @property [characterStrokeType] [Short], 자획 유형 (BYTE - unsigned 1 byte)
 * @property [characterShape] [Short], 글자형 (BYTE - unsigned 1 byte)
 * @property [middleLine] [Short], 중간선 (BYTE - unsigned 1 byte)
 * @property [xHeight] [Short], X-높이 (BYTE - unsigned 1 byte)
 */
class HWPFontTypeInfo {
    var fontType: Short = 0
    var serifType: Short = 0
    var thickness: Short = 0
    var ratio: Short = 0
    var contrast: Short = 0
    var strokeDeviation: Short = 0
    var characterStrokeType: Short = 0
    var characterShape: Short = 0
    var middleLine: Short = 0
    var xHeight: Short = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPFontTypeInfo] 복사된 객체 반환
     */
    fun copy() : HWPFontTypeInfo = HWPFontTypeInfo().also {
        it.fontType = this.fontType
        it.serifType = this.serifType
        it.thickness = this.thickness
        it.ratio = this.ratio
        it.contrast = this.contrast
        it.strokeDeviation = this.strokeDeviation
        it.characterStrokeType = this.characterStrokeType
        it.characterShape = this.characterShape
        it.middleLine = this.middleLine
        it.xHeight = this.xHeight
    }
}