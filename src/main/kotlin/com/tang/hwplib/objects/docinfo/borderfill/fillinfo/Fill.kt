package com.tang.hwplib.objects.docinfo.borderfill.fillinfo

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 채우기 종류를 나타내는 객체
 * UINT - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 채우기 종류 속성값을 가진 데이터
 *
 * 0x00000000 - 채우기 없음
 */
class HWPFillType {
    var value: Long = 0

    /**
     * 단색 채우기 유무를 반환하는 함수
     * type & 0x0000001 != 0 - 단색 채우기
     *
     * @return [Boolean] 단색 채우기 유무 반환
     */
    fun hasPatternFill() : Boolean = get(value, 0)

    /**
     * 단색 채우기 유무를 설정하는 함수
     * type & 0x0000001 != 0 - 단색 채우기
     *
     * @param [patternFill] [Boolean], 단색 채우기 유무의 참/거짓 값을 가지는 데이터
     */
    fun setPatternFill(patternFill: Boolean) {
        value = set(value, 0, patternFill)
    }

    /**
     * 그러데이션 채우기 유무를 반환하는 함수
     * type & 0x0000004 != 0 - 이미지 채우기
     *
     * @return [Boolean] 그러데이션 채우기 유무 반환
     */
    fun hasImageFill() : Boolean = get(value, 1)

    /**
     * 그러데이션 채우기 유무를 설정하는 함수
     * type & 0x0000004 != 0 - 그러데이션 채우기
     *
     * @param [imageFill] [Boolean], 그러데이션 채우기 유무의 참/거짓 값을 가진 데이터
     */
    fun setImageFill(imageFill: Boolean) {
        value = set(value, 1, imageFill)
    }

    /**
     * 이미지 채우기 유무를 반환하는 함수
     * type & 0x0000002 != 0 - 이미지 채우지
     *
     * @return [Boolean] 이미지 채우기 유무 반환
     */
    fun hasGradientFill() : Boolean = get(value, 2)

    /**
     * 이미지 채우기 유무를 설정하는 함수
     * type & 0x0000002 != 0 - 이미지 채우지
     *
     * @param [gradientFill] [Boolean], 이미지 채우기 유무의 참/거짓 값을 가진 데이터
     */
    fun setGradientFill(gradientFill: Boolean) {
        value = set(value, 2, gradientFill)
    }
}

/**
 * 채우기 정보를 나타내는 객체
 * BYTE stream - n size
 *
 * @author accforaus
 *
 * @property [type] [HWPFillType], 채우기 종류(type) (UINT - unsigned 4 bytes)
 * @property [patternFill] [HWPPatternFill], 단색 채우기
 * @property [gradientFill] [HWPGradientFill], 그러데이션 채우기
 * @property [imageFill] [HWPImageFill], 이미지 채우기
 */
class HWPFillInfo {
    var type: HWPFillType = HWPFillType()
    var patternFill: HWPPatternFill? = null
        private set
    var gradientFill: HWPGradientFill? = null
        private set
    var imageFill: HWPImageFill? = null
        private set

    /**
     * 단색 채우기를 생성하는 함수
     */
    fun createPatternFill() {
        patternFill = HWPPatternFill()
    }

    /**
     * 단색 채우기를 제거하는 함수
     */
    fun deletePatternFill() {
        patternFill = null
    }

    /**
     * 그러데이션 채우기를 생성하는 함수
     */
    fun createGradientFill() {
        gradientFill = HWPGradientFill()
    }

    /**
     * 그러데이션 채우기를 제거하는 함수
     */
    fun deleteGradientFill() {
        gradientFill = null
    }

    /**
     * 이미지 채우기를 생성하는 함수
     */
    fun createImageFill() {
        imageFill = HWPImageFill()
    }

    /**
     * 이미지 채우기를 제거하는 함수
     */
    fun deleteImageFill() {
        imageFill = null
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPFillInfo] 복사된 객체 반환
     */
    fun copy() : HWPFillInfo = HWPFillInfo().also {
        it.type.value = this.type.value
        it.patternFill = this.patternFill?.copy()
        it.gradientFill = this.gradientFill?.copy()
        it.imageFill = this.imageFill?.copy()
    }
}