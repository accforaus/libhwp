package com.tang.hwplib.objects.bodytext.control.gso.video

/**
 * 동영상 타입
 *
 * @author accforaus
 *
 * @property [value] [Int], 동영상 타입 값
 */
enum class HWPVideoType(v: Int) {
    /**
     * 로컬 동영상
     */
    Local(0),
    /**
     * 웹 동영상
     */
    Web(1);

    var value: Int = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPVideoType] enum 값
         */
        fun valueOf(v: Int) : HWPVideoType {
            for (vt in values())
                if (vt.value == v)
                    return vt
            return Local
        }
    }
}

/**
 * 동영상 타입 속성을 나타내는 객체
 *
 * @author accforaus
 *
 * @property [thumbBinDataID] [Short], 썸내일 파일이 사용하는 스토리지의 BinData ID (UINT16 - unsigned 2 bytes)
 */
open class HWPVideoProperty {
    var thumbBinDataID: Int = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPVideoProperty] 복사된 객체 반환
     */
    open fun copy() : HWPVideoProperty = HWPVideoProperty().also { it.thumbBinDataID = this.thumbBinDataID }
}

/**
 * 로컬 동영상 속성을 나타내는 객체
 * 4 bytes
 * @see [HWPVideoProperty]
 *
 * @author accforaus
 *
 * @property [videoBinDataID] [Short], 비디오 파일이 사용하는 스토리지의 BinData ID (UINT16 - unsigned 2 bytes)
 */
class HWPLocalVideoProperty: HWPVideoProperty() {
    var videoBinDataID: Int = 0

    override fun copy(): HWPLocalVideoProperty = HWPLocalVideoProperty().also {
        super.copy()
        it.videoBinDataID = this.videoBinDataID
    }
}

/**
 * 웹 동영상 속성을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [webTag] [String], 웹 태그 (WCHAR array)
 */
class HWPWebVideoProperty: HWPVideoProperty() {
    var webTag: String? = null

    override fun copy(): HWPWebVideoProperty = HWPWebVideoProperty().also {
        super.copy()
        it.webTag = this.webTag
    }
}