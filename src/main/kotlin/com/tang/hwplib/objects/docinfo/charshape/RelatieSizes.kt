package com.tang.hwplib.objects.docinfo.charshape

/**
 * 언어별 상대 크기를 나타내는 객체
 * 10% ~ 250%
 * UINT8 array[7] - unsigned 7 bytes
 *
 * @author accforaus
 *
 * @property [array] [Short], 상대 크기값을 가진 short 배열
 */
class HWPRelativeSizes {
    var array: ShortArray = ShortArray(7)

    /**
     * 한글 상대 크기값을 가져오는 함수
     * 
     * @return [Short] 한글 상대 크기값을 반환
     */
    fun getHangul() : Short = array[0]

    /**
     * 한글 상대 크기값을 설정하는 함수
     * 
     * @param [relativeSize] [Short], 한글 상대 크기값을 가진 데이터
     */
    fun setHangul(relativeSize: Short) {
        array[0] = relativeSize
    }

    /**
     * 영어 상대 크기값을 가져오는 함수
     *
     * @return [Short] 영어 상대 크기값을 반환
     */
    fun getLatin() : Short = array[1]

    /**
     * 영어 상대 크기값을 설정하는 함수
     *
     * @param [relativeSize] [Short], 영어 상대 크기값을 가진 데이터
     */
    fun setLatin(relativeSize: Short) {
        array[1] = relativeSize
    }

    /**
     * 한자 상대 크기값을 가져오는 함수
     *
     * @return [Short] 한자 상대 크기값을 반환
     */
    fun getHanja() : Short = array[2]

    /**
     * 한자 상대 크기값을 설정하는 함수
     *
     * @param [relativeSize] [Short], 한자 상대 크기값을 가진 데이터
     */
    fun setHanja(relativeSize: Short) {
        array[2] = relativeSize
    }

    /**
     * 일본어 상대 크기값을 가져오는 함수
     *
     * @return [Short] 일본어 상대 크기값을 반환
     */
    fun getJapanese() : Short = array[3]

    /**
     * 일본어 상대 크기값을 설정하는 함수
     *
     * @param [relativeSize] [Short], 일본어 상대 크기값을 가진 데이터
     */
    fun setJapanese(relativeSize: Short) {
        array[3] = relativeSize
    }

    /**
     * 기타 상대 크기값을 가져오는 함수
     *
     * @return [Short] 기타 상대 크기값을 반환
     */
    fun getOther() : Short = array[4]

    /**
     * 기타 상대 크기값을 설정하는 함수
     *
     * @param [relativeSize] [Short], 기타 상대 크기값을 가진 데이터
     */
    fun setOther(relativeSize: Short) {
        array[4] = relativeSize
    }

    /**
     * 기호 상대 크기값을 가져오는 함수
     *
     * @return [Short] 기호 상대 크기값을 반환
     */
    fun getSymbol() : Short = array[5]

    /**
     * 기호 상대 크기값을 설정하는 함수
     *
     * @param [relativeSize] [Short], 기호 상대 크기값을 가진 데이터
     */
    fun setSymbol(relativeSize: Short) {
        array[5] = relativeSize
    }

    /**
     * 사용자 상대 크기값을 가져오는 함수
     *
     * @return [Short] 사용자 상대 크기값을 반환
     */
    fun getUser() : Short = array[6]

    /**
     * 사용자 상대 크기값을 설정하는 함수
     *
     * @param [relativeSize] [Short], 사용자 상대 크기값을 가진 데이터
     */
    fun setUser(relativeSize: Short) {
        array[6] = relativeSize
    }

    /**
     * 상대 크기 값을 같은 값으로 설정하는 함수
     *
     * @param [relativeSize] [Short], 상대 크기 값을 가진 데이터
     */
    fun setForAll(relativeSize: Short) {
        for (index in 0 until array.size) array[index] = relativeSize
    }
}