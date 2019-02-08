package com.tang.hwplib.objects.docinfo.borderfill.fillinfo

import com.tang.hwplib.objects.docinfo.HWPBinData

/**
 * 그림 효과
 * BYTE - unsigned 1 byte
 *
 * @author accforaus
 *
 * @property [value] [Byte], 그림 효과 값을 가지는 데이터
 */
enum class HWPPictureEffect(v: Byte) {
    /**
     * REAL_PIC
     */
    RealPicture(0.toByte()),
    /**
     * GRAY_SCALE
     */
    GrayScale(1.toByte()),
    /**
     * BLACK_WHITE
     */
    BlackWhite(2.toByte()),
    /**
     * PATTERN8x8
     */
    Pattern8x8(3.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPPictureInfo] enum 값
         */
        fun valueOf(v: Byte) : HWPPictureEffect {
            for (pe in values())
                if (pe.value == v)
                    return pe
            return RealPicture
        }
    }
}

/**
 * 그림 정보를 나타내는 객체
 * BYTE stream - unsigned 5 bytes
 *
 * @author accforaus
 *
 * @property [brightness] [Byte], 빍기 (INT8 - signed 1 byte)
 * @property [contrast] [Byte], 명암 (INT8 - signed 1 byte)
 * @property [effect] [HWPPictureEffect], 그림 효과 (BYTE - unsigned 1 byte)
 * @property [binItemID] [Int], BinItem[HWPBinData]의 아이디 참조값 (UINT16 - unsigned 2 bytes)
 */
class HWPPictureInfo {
    var brightness: Byte = 0
    var contrast: Byte = 0
    var effect: HWPPictureEffect = HWPPictureEffect.RealPicture
    var binItemID: Int = 0
}