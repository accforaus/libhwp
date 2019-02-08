package com.tang.hwplib.objects.docinfo.parashape

/**
 * 줄 나눔 기준 영어 단위
 * bit 5-6
 *
 * @author accforaus
 *
 * @property [value] [Byte], 줄 나눔 기준 영어 단위값을 가진 데이터
 */
enum class HWPLineDivideForEnglish(v: Byte) {
    /**
     * 단어
     */
    ByWord(0.toByte()),
    /**
     * 하이픈
     */
    ByHyphen(1.toByte()),
    /**
     * 글자
     */
    ByLetter(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPLineDivideForEnglish] enum 값
         */
        fun valueOf(v: Byte) : HWPLineDivideForEnglish {
            for (ldfe in values())
                if (ldfe.value == v)
                    return ldfe
            return ByWord
        }
    }
}

/**
 * 줄 나눔 기준 한글 단위
 * bit 7
 *
 * @author accforaus
 *
 * @property [value] [Byte], 줄 나눔 기준 한글 단위값을 가진 데이터
 */
enum class HWPLineDivideForHangul(v: Byte) {
    /**
     * 어절
     */
    ByWord(0.toByte()),
    /**
     * 글자
     */
    ByLetter(1.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPLineDivideForHangul] enum 값
         */
        fun valueOf(v: Byte) : HWPLineDivideForHangul {
            for (ldfh in values())
                if (ldfh.value == v)
                    return ldfh
            return ByWord
        }
    }
}