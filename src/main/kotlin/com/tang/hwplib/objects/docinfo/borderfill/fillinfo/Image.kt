package com.tang.hwplib.objects.docinfo.borderfill.fillinfo

/**
 * 이미지 채우기 유형
 * BYTE - unsigned 1 byte
 *
 * @author accforaus
 *
 * @property [value] [Byte], 이미지 채우기 유형값을 가진 데이터
 */
enum class HWPImageFillType(v: Byte) {
    /**
     * 바둑판식으로-모두
     */
    TileAll(0.toByte()),
    /**
     * 바둑판식으로-가로/위
     */
    TileHorizontalTop(1.toByte()),
    /**
     * 바둑판식으로-가로/아래
     */
    TileHorizontalBottom(2.toByte()),
    /**
     * 바둑판식으로-세로/왼쪽
     */
    TileVerticalLeft(3.toByte()),
    /**
     * 바둑판식으로-세로/오른쪽
     */
    TileVerticalRight(4.toByte()),
    /**
     * 크기에 맞추어
     */
    FitSize(5.toByte()),
    /**
     * 가운데로
     */
    Center(6.toByte()),
    /**
     * 가운데 위로
     */
    CenterTop(7.toByte()),
    /**
     * 가운데 아래로
     */
    CenterBottom(8.toByte()),
    /**
     * 왼쪽 가운데로
     */
    LeftCenter(9.toByte()),
    /**
     * 왼쪽 위로
     */
    LeftTop(10.toByte()),
    /**
     * 왼쪽 아래로
     */
    LeftBottom(11.toByte()),
    /**
     * 오른쪽 가운데로
     */
    RightCenter(12.toByte()),
    /**
     * 오른쪽 위로
     */
    RightTop(13.toByte()),
    /**
     * 오른쪽 아래로
     */
    RightBottom(14.toByte()),
    /**
     * NONE
     */
    None(15.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPImageFillType] enum 값
         */
        fun valueOf(v: Byte) : HWPImageFillType {
            for (ift in values())
                if (ift.value == v)
                    return ift
            return None
        }
    }
}

/**
 * 이미지를 나타내는 객체
 * 이미지 채우기 (type & 0x0000002 != 0)
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [imageFillType] [HWPImageFillType], 이미지 채우기 유형 (BYTE - unsigned 1 byte)
 * @property [pictureInfo] [HWPPictureInfo], 그림 정보 (BYTE stream - unsigned 5 bytes)
 */
class HWPImageFill {
    var imageFillType: HWPImageFillType = HWPImageFillType.None
    var pictureInfo: HWPPictureInfo = HWPPictureInfo()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPImageFill] 복사된 객체 반환
     */
    fun copy() : HWPImageFill = HWPImageFill().also {
        it.imageFillType = HWPImageFillType.valueOf(this.imageFillType.value)
        it.pictureInfo = this.pictureInfo.copy()
    }
}