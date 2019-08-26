package com.tang.hwplib.objects.docinfo.borderfill.fillinfo

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set
import com.tang.hwplib.util.extension.nullEquals
import com.tang.hwplib.util.exceptions.HWPBuildException

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
        set(newValue) {
            field = newValue
            _patternFill = hasPatternFill()
            _imageFill = hasImageFill()
            _gradientFill = hasGradientFill()
        }
    private var _patternFill: Boolean = false
    private var _imageFill: Boolean = false
    private var _gradientFill: Boolean = false
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
    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPFillType] 생성된 객체 반환
         */
        fun build(hasPatternFill: Boolean = false,
                  hasImageFill: Boolean = false,
                  hasGradientFill: Boolean = false): HWPFillType = HWPFillType().apply {
            setPatternFill(hasPatternFill)
            setImageFill(hasImageFill)
            setGradientFill(hasGradientFill)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPFillType] 생성된 객체 반환
         */
        fun build(value: Long = 0): HWPFillType = HWPFillType().apply {
            this.value = value
        }
    }

    override fun equals(other: Any?): Boolean = value == (other as HWPFillType).value
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
    var gradientFill: HWPGradientFill? = null
    var imageFill: HWPImageFill? = null

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

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPFillInfo] 생성된 객체 반환
         */
        fun build(type: HWPFillType = HWPFillType.build(),
                  patternFill: HWPPatternFill? = null,
                  gradientFill: HWPGradientFill? = null,
                  imageFill: HWPImageFill? = null): HWPFillInfo = HWPFillInfo().apply {
            type.run {
                if (hasGradientFill())
                    if (gradientFill == null) throw HWPBuildException("[HWPFillInfo] Gradient Fill Flag: true, Gradient Fill must be not null")
                if (hasPatternFill())
                    if (patternFill == null) throw HWPBuildException("[HWPFillInfo Pattern Fill Flag: true, Pattern Fill must be not null")
                if (hasImageFill())
                    if (imageFill == null) throw HWPBuildException("[HWPFillInfo] Image Fill Flag: true, Image Fill must be not null")
            }
            this.type = type
            this.patternFill = patternFill
            this.gradientFill = gradientFill
            this.imageFill = imageFill
        }
    }

    override fun equals(other: Any?): Boolean = (other as HWPFillInfo).let { info ->
        return type.value == info.type.value
                && nullEquals(patternFill, info.patternFill)
                && nullEquals(gradientFill, info.gradientFill)
                && nullEquals(imageFill, info.imageFill)
    }
}