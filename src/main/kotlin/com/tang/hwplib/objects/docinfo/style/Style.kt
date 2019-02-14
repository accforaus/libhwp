package com.tang.hwplib.objects.docinfo.style

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 스타일 종류
 * bit 0-2
 *
 * @author accforaus
 *
 * @property [value] [Byte], 스타일 종류 값을 가진 데이터
 */
enum class HWPStyleSort(v: Byte) {
    /**
     * 문단 스타일
     */
    ParaStyle(0.toByte()),
    /**
     * 글자 스타일
     */
    CharStyle(1.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPStyleSort] enum 값
         */
        fun valueOf(v: Byte) : HWPStyleSort {
            for (ss in values())
                if (ss.value == v)
                    return ss
            return ParaStyle
        }
    }
}

/**
 * 스타일 속성을 나타내는 객체
 * BYTE - unsigned 1 byte
 *
 * @author accforaus
 *
 * @property [value] [Short], 스타일 속성값을 가진 데이터
 */
class HWPStyleProperty {
    var value: Short = 0
        set(newValue) {
            field = newValue
            _styleSort = getStyleSort()
        }
    private var _styleSort: HWPStyleSort = HWPStyleSort.CharStyle

    /**
     * 스타일 종류를 반환하는 함수
     * bit 0-2
     *
     * @return [HWPStyleSort] 스타일 종류 반환
     */
    fun getStyleSort() : HWPStyleSort = HWPStyleSort.valueOf(get(value, 0, 2).toByte())

    /**
     * 스타일 종류를 설정하는 함수
     * bit 0-2
     *
     * @param [styleSort] [HWPStyleSort], 스타일 종류값을 가진 데이터
     */
    fun setStyleSort(styleSort: HWPStyleSort) {
        value = set(value, 0, 2, styleSort.value.toInt())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPStyleProperty] 생성된 객체 반환
         */
        fun build(styleSort: HWPStyleSort = HWPStyleSort.ParaStyle) : HWPStyleProperty = HWPStyleProperty().apply {
            setStyleSort(styleSort)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPStyleProperty] 생성된 객체 반환
         */
        fun build(value: Short = 0): HWPStyleProperty = HWPStyleProperty().apply {
            this.value = value
        }
    }
}