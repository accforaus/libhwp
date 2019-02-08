package com.tang.hwplib.objects.fileheader

/**
 * Encrypt Version을 나타내는 enum 객체
 * DWORD(4byte) - (unsigned 4 bytes)
 *
 * @author accforaus
 *
 * @property [value] [Long], 파일에 저장되는 값
 */
enum class HWPEncryptVersionType(v: Long) {
    /**
     * None
     */
    None(0.toLong()),
    /**
     * 한글 2.5 버전 이하
     */
    LessHWP25(1.toLong()),
    /**
     * 한글 3.0 버전 Enhanced
     */
    EnhancedHWP30(2.toLong()),
    /**
     * 한글 3.0 버전 Old
     */
    OldHWP30(3.toLong()),
    /**
     * 한글 7.0 버전 이후
     */
    MoreHWP70(4.toLong());

    var value: Long = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPKOGLLicenceSupportCountry] enum 값
         */
        fun valueOf(v: Long) : HWPEncryptVersionType {
            for (evt in values())
                if (evt.value == v)
                    return evt
            return None
        }
    }
}