package com.tang.hwplib.objects.bodytext.control.ctrlheader.autonumber

import com.tang.hwplib.objects.bodytext.control.ctrlheader.newnumber.HWPNewNumberHeaderProperty
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPNumberShape
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 번호 종류
 *
 * @author accforaus
 *
 * @property [values] [Byte], 번호 종류 값
 */
enum class HWPNumberSort(v: Byte) {
    /**
     * 쪽 번호
     */
    Page(0.toByte()),
    /**
     * 각주 번호
     */
    FootNote(1.toByte()),
    /**
     * 미주 번호
     */
    EndNote(2.toByte()),
    /**
     * 그림 버호
     */
    Picture(3.toByte()),
    /**
     * 표 번호
     */
    Table(4.toByte()),
    /**
     * 수식 번호
     */
    Equation(5.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPNumberSort] enum 값
         */
        fun valueOf(v: Byte) : HWPNumberSort {
            for (ns in values())
                if (ns.value == v)
                    return ns
            return Page
        }
    }
}

/**
 * 자동 번호 속성을 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 자동 번호 속성 값
 */
class HWPAutoNumberHeaderProperty {
    var value: Long = 0
        set(newValue) {
            field = newValue
            _numberShape = getNumberShape()
            _numberSort = getNumberSort()
            _isSuperScript = isSuperScript()
        }
    private var _numberSort: HWPNumberSort = HWPNumberSort.Page
    private var _numberShape: HWPNumberShape = HWPNumberShape.Type0
    private var _isSuperScript: Boolean = false
    /**
     * 번호 종류를 반환하는 함수
     *  bit 0-3
     *
     * @return [HWPNumberSort] 번호 종류 반환
     */
    fun getNumberSort() : HWPNumberSort = HWPNumberSort.valueOf(get(value, 0, 3).toByte())

    /**
     * 번호 종류를 설정하는 함수
     *  bit 0-3
     *
     * @param [numberSort] [HWPNumberSort] 번호 종류값
     */
    fun setNumberSort(numberSort: HWPNumberSort) {
        value = set(value, 0, 3, numberSort.value.toInt())
    }

    /**
     * 번호 모양을 반환하는 함수
     * bit 4-11
     *
     * @return [HWPNumberShape] 번호 모양 반환
     */
    fun getNumberShape() : HWPNumberShape = HWPNumberShape.valueOf(get(value, 4, 11).toShort())

    /**
     * 번호 모양을 설정하는 함수
     * bit 4-11
     *
     * @param [numberShape] [HWPNumberShape] 번호 모양값
     */
    fun setNumberShape(numberShape: HWPNumberShape) {
        value = set(value, 4, 11, numberShape.value.toInt())
    }

    /**
     * 각주 내용 중 번호 코드의 모양을 위 첨자 형식으로 할지 여부를 반환하는 함수
     * 각주에서만 사용된다
     * bit 12
     *
     * @return [Boolean] 각주 내용 중 번호 코드의 모양을 위 첨자 형식으로 할지 여부 반환
     */
    fun isSuperScript(): Boolean = get(value, 12)

    /**
     * 각주 내용 중 번호 코드의 모양을 위 첨자 형식으로 할지 여부를 설정하는 함수
     * 각주에서만 사용된다
     * bit 12
     *
     * @param [superScript] [Boolean] 각주 내용 중 번호 코드의 모양을 위 첨자 형식으로 할지 여부 값
     */
    fun setSuperScript(superScript: Boolean) {
        value = set(value, 12, superScript)
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPAutoNumberHeaderProperty] 생성된 객체 반환
         */
        fun build(numberSort: HWPNumberSort = HWPNumberSort.Page,
                  numberShape: HWPNumberShape = HWPNumberShape.Type0,
                  superScript: Boolean = false)
                : HWPAutoNumberHeaderProperty = HWPAutoNumberHeaderProperty().apply {
            setNumberSort(numberSort)
            setNumberShape(numberShape)
            setSuperScript(superScript)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPAutoNumberHeaderProperty] 생성된 객체 반환
         */
        fun build(value: Long = 0) : HWPAutoNumberHeaderProperty = HWPAutoNumberHeaderProperty().apply {
            this.value = value
        }
    }
}