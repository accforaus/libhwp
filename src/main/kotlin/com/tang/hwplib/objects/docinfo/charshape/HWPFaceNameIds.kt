package com.tang.hwplib.objects.docinfo.charshape

/**
 * 언어별 글꼴 ID(FaceID)를 나타내는 객체
 * WORD array[7] - unsigned 14 bytes
 *
 * @author accforaus
 *
 * @property [array] [IntArray], 언어별 글꼴 ID값을 가진 int 배열
 */
class HWPFaceNameIds {
    var array: IntArray = IntArray(7)

    /**
     * 한글 글꼴 ID를 가져오는 함수
     *
     * @return [Int] 한글 글꼴 ID 반환
     */
    fun getHangul() : Int = array[0]

    /**
     * 한글 글꼴 ID를 설정하는 함수
     *
     * @param [faceNameID] [Int], 한글 글꼴 ID를 가진 데이터
     */
    fun setHangul(faceNameID: Int) {
        array[0] = faceNameID
    }

    /**
     * 영어 글꼴 ID를 가져오는 함수
     *
     * @return [Int] 영어 글꼴 ID 반환
     */
    fun getLatin() : Int = array[1]

    /**
     * 영어 글꼴 ID를 설정하는 함수
     *
     * @param [faceNameID] [Int], 영어 글꼴 ID를 가진 데이터
     */
    fun setLatin(faceNameID: Int) {
        array[1] = faceNameID
    }

    /**
     * 한글 글꼴 ID를 가져오는 함수
     *
     * @return [Int] 한글 글꼴 ID 반환
     */
    fun getHanja() : Int = array[2]

    /**
     * 한자 글꼴 ID를 설정하는 함수
     *
     * @param [faceNameID] [Int], 한자 글꼴 ID를 가진 데이터
     */
    fun setHanja(faceNameID: Int) {
        array[2] = faceNameID
    }

    /**
     * 한자 글꼴 ID를 가져오는 함수
     *
     * @return [Int] 한자 글꼴 ID 반환
     */
    fun getJapanese() : Int = array[3]

    /**
     * 일본어 글꼴 ID를 설정하는 함수
     *
     * @param [faceNameID] [Int], 일본어 글꼴 ID를 가진 데이터
     */
    fun setJapanese(faceNameID: Int) {
        array[3] = faceNameID
    }

    /**
     * 기타 글꼴 ID를 가져오는 함수
     *
     * @return [Int] 기타 글꼴 ID 반환
     */
    fun getOther() : Int = array[4]

    /**
     * 기타 글꼴 ID를 설정하는 함수
     *
     * @param [faceNameID] [Int], 기타 글꼴 ID를 가진 데이터
     */
    fun setOther(faceNameID: Int) {
        array[4] = faceNameID
    }

    /**
     * 기호 글꼴 ID를 가져오는 함수
     *
     * @return [Int] 기호 글꼴 ID 반환
     */
    fun getSymbol() : Int = array[5]

    /**
     * 기호 글꼴 ID를 설정하는 함수
     *
     * @param [faceNameID] [Int], 기호 글꼴 ID를 가진 데이터
     */
    fun setSymbol(faceNameID: Int) {
        array[5] = faceNameID
    }

    /**
     * 사용자 글꼴 ID를 가져오는 함수
     *
     * @return [Int] 사용자 글꼴 ID 반환
     */
    fun getUser() : Int = array[6]

    /**
     * 사용자 글꼴 ID를 설정하는 함수
     *
     * @param [faceNameID] [Int], 사용자 글꼴 ID를 가진 데이터
     */
    fun setUser(faceNameID: Int) {
        array[6] = faceNameID
    }

    /**
     * 글꼴 ID를 같은 값으로 설정하는 함수
     *
     * @param [faceNameID] [Int], 글꼴 ID값을 가진 데이터
     */
    fun setForAll(faceNameID: Int) {
        for (index in 0 until array.size) array[index] = faceNameID
    }
}