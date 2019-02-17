package com.tang.hwplib.objects.fileheader

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 공공누리(KOGL) 라이센스 지원 국가
 * BYTE (unsigned 1 byte)
 * @author accforaus
 *
 * @property [value] [Byte], 파일에 저장되는 값
 */
enum class HWPKOGLLicenceSupportCountry(v: Byte) {
    /**
     * KOR
     */
    KOR(6.toByte()),

    /**
     * US
     */
    US(15.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPKOGLLicenceSupportCountry] enum 값
         */
        fun valueOf(v: Byte): HWPKOGLLicenceSupportCountry {
            for (koglsc in values())
                if (koglsc.value == v)
                    return koglsc
            return KOR
        }
    }
}

/**
 * CCL, 공공누리 라이센스 정보를 나타내는 객체
 *
 * @author accforaus
 * @property [koglCclInfo] [Boolean], CCL, 공공누리 라이센스 정보
 * @property [isPreventCopy] [Boolean], 복제 제한 여부
 * @property [isPermitCopy] [Boolean], 동일 조건 하에 복제 허가 여부 (복제 제한인 경우 무시)
 */
class HWPKOGLCCLInfo {
    var value: Long = 0
        set(newValue) {
            field = newValue
            _koglCclInfo = getKoglCclInfo()
            _isPreventCopy = isPreventCopy()
            _isPermitCopy = isPermitCopy()
        }
    private var _koglCclInfo: Boolean = false
    private var _isPreventCopy: Boolean = false
    private var _isPermitCopy: Boolean = false

    fun getKoglCclInfo() : Boolean = get(value, 0)
    fun setKoglCclInfo(koglCclInfo: Boolean) {
        value = set(value, 0, koglCclInfo)
    }

    fun isPreventCopy() : Boolean = get(value, 1)
    fun setPreventCopy(preventCopy: Boolean) {
        value = set(value, 1, preventCopy)
    }

    fun isPermitCopy() : Boolean = get(value, 2)
    fun setPermitCopy(permitCopy: Boolean) {
        value = set(value, 1, permitCopy)
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPKOGLCCLInfo] 복사된 객체 반환
     */
    fun copy() : HWPKOGLCCLInfo = HWPKOGLCCLInfo().also {
        it.value = this.value
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPKOGLCCLInfo] 생성된 객체 반환
         */
        fun build(koglCclInfo: Boolean = false,
                  isPreventCopy: Boolean = false,
                  isPermitCopy: Boolean = false):
                HWPKOGLCCLInfo = HWPKOGLCCLInfo().apply {
            setKoglCclInfo(koglCclInfo)
            setPreventCopy(isPreventCopy)
            setPermitCopy(isPermitCopy)
        }
    }
}
