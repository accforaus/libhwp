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
    var koglCclInfo: Boolean = false
    var isPreventCopy: Boolean = false
    var isPermitCopy: Boolean = false

    /**
     * 데이터를 설정해주는 함수
     *
     * @param [value] [Long], 데이터 값
     * @return [Unit]
     */
    fun setValue(value: Long) {
        koglCclInfo = get(value, 0)
        isPreventCopy = get(value, 1)
        isPermitCopy = get(value, 2)
    }

    /**
     * 데이터를 반환해주는 함수
     *
     * @return [Long] 데이터 값
     */
    fun getValue(): Long {
        var value: Long = 0
        value = set(value, 0, koglCclInfo)
        value = set(value, 1, isPreventCopy)
        value = set(value, 2, isPermitCopy)
        return value
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPKOGLCCLInfo] 복사된 객체 반환
     */
    fun copy() : HWPKOGLCCLInfo = HWPKOGLCCLInfo().also {
        it.koglCclInfo = this.koglCclInfo
        it.isPreventCopy = this.isPreventCopy
        it.isPermitCopy = this.isPermitCopy
    }
}
