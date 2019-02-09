package com.tang.hwplib.objects.docinfo.charshape

/**
 * 언어별 장평을 나타내는 객체
 * 50% ~ 200%
 * UINT8 array[7] - unsigned 7 bytes
 *
 * @author accforaus
 *
 * @property [array] [Short], 장평값을 가지고 있는 short 배열
 */
class HWPRatios {
    var array: ShortArray = ShortArray(7)

    /**
     * 한글 장평을 가져오는 함수
     *
     * @return [Short] 한글 장평을 반환
     */
    fun getHangul() : Short = array[0]

    /**
     * 한글 장평을 설정하는 함수
     *
     * @param [ratio] [Short], 한글 장평 값을 가진 데이터
     */
    fun setHangul(ratio: Short) {
        array[0] = ratio
    }

    /**
     * 영어 장평을 가져오는 함수
     *
     * @return [Short] 영어 장평을 반환
     */
    fun getLatin() : Short = array[1]

    /**
     * 영어 장평을 설정하는 함수
     *
     * @param [ratio] [Short], 영어 장평 값을 가진 데이터
     */
    fun setLatin(ratio: Short) {
        array[1] = ratio
    }

    /**
     * 한자 장평을 가져오는 함수
     *
     * @return [Short] 한자 장평을 반환
     */
    fun getHanja() : Short = array[2]

    /**
     * 한자 장평을 설정하는 함수
     *
     * @param [ratio] [Short], 한자 장평 값을 가진 데이터
     */
    fun setHanja(ratio: Short) {
        array[2] = ratio
    }

    /**
     * 일본어 장평을 가져오는 함수
     *
     * @return [Short] 일본어 장평을 반환
     */
    fun getJapanese() : Short = array[3]

    /**
     * 일본어 장평을 설정하는 함수
     *
     * @param [ratio] [Short], 일본어 장평 값을 가진 데이터
     */
    fun setJapanese(ratio: Short) {
        array[3] = ratio
    }

    /**
     * 기타 장평을 가져오는 함수
     *
     * @return [Short] 기타 장평을 반환
     */
    fun getOther() : Short = array[4]

    /**
     * 기타 장평을 설정하는 함수
     *
     * @param [ratio] [Short], 기타 장평 값을 가진 데이터
     */
    fun setOther(ratio: Short) {
        array[4] = ratio
    }

    /**
     * 기호 장평을 가져오는 함수
     *
     * @return [Short] 기호 장평을 반환
     */
    fun getSymbol() : Short = array[5]

    /**
     * 기호 장평을 설정하는 함수
     *
     * @param [ratio] [Short], 기호 장평 값을 가진 데이터
     */
    fun setSymbol(ratio: Short) {
        array[5] = ratio
    }

    /**
     * 사용자 장평을 가져오는 함수
     *
     * @return [Short] 사용자 장평을 반환
     */
    fun getUser() : Short = array[6]

    /**
     * 사용자 장평을 설정하는 함수
     *
     * @param [ratio] [Short], 사용자 장평 값을 가진 데이터
     */
    fun setUser(ratio: Short) {
        array[6] = ratio
    }

    /**
     * 장평을 같은 값으로 설정하는 함수
     *
     * @param [ratio] [Short], 장평값을 가진 데이터
     */
    fun setForAll(ratio: Short) {
        for (index in 0 until array.size) array[index] = ratio
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPRatios] 복사된 객체 반환
     */
    fun copy() : HWPRatios = HWPRatios().also {
        it.array = this.array.copyOf()
    }
}