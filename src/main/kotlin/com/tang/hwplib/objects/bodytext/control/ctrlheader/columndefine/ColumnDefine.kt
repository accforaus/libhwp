package com.tang.hwplib.objects.bodytext.control.ctrlheader.columndefine

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 단 종류
 *
 * @author accforaus
 *
 * @property [value] [Byte], 단 종류값
 */
enum class HWPColumnSort(v: Byte) {
    /**
     * 일반 다단
     */
    Normal(0.toByte()),
    /**
     * 배분 다단
     */
    Distribution(1.toByte()),
    /**
     * 평행 다단
     */
    Parallel(1.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPColumnSort] enum 값
         */
        fun valueOf(v: Byte) : HWPColumnSort {
            for (cs in values())
                if (cs.value == v)
                    return cs
            return Normal
        }
    }
}

/**
 * 단 방향 지정
 *
 * @author accforaus
 *
 * @property [value] [Byte], 단 방향 지정값
 */
enum class HWPColumnDirection(v: Byte) {
    /**
     * 왼쪽부터
     */
    FromLeft(0.toByte()),
    /**
     * 오른쪽부터
     */
    FromRight(1.toByte()),
    /**
     * 맞쪽
     */
    Both(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPColumnDirection] enum 값
         */
        fun valueOf(v: Byte) : HWPColumnDirection {
            for (cd in values())
                if (cd.value == v)
                    return cd
            return FromLeft
        }
    }
}

/**
 * 단 정보를 나타내는 객체
 *
 * @author accforaus
 *
 * @property [width] [Int], 단의 폭
 * @property [gap] [Int], 단의 간격
 */
class HWPColumnInfo {
    var width: Int = 0
    var gap: Int = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPColumnInfo] 복사된 객체 반환
     */
    fun copy() : HWPColumnInfo = HWPColumnInfo().also {
        it.width = this.width
        it.gap = this.gap
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPColumnInfo] 생성된 객체 반환
         */
        fun build(width: Int = 0, gap: Int = 0) : HWPColumnInfo = HWPColumnInfo().apply {
            this.width = width
            this.gap = gap
        }
    }
}

/**
 * 단 정의 속성을 나타내는 객체
 *
 * @author accforaus
 *
 * @property [value] [Int], 단 정의 속성값
 */
class HWPColumnDefineHeaderProperty {
    var value: Int = 0

    /**
     * 단 종류를 반환하는 함수
     * bit 0-1
     *
     * @return [HWPColumnSort] 단 종류 반환
     */
    fun getColumnSort() : HWPColumnSort = HWPColumnSort.valueOf(get(value, 0, 1).toByte())

    /**
     * 단 종류를 설정하는 함수
     * bit 0-1
     *
     * @param [columnSort] [HWPColumnSort] 단 종류 값
     */
    fun setColumnSort(columnSort: HWPColumnSort) {
        value = set(value, 0, 1, columnSort.value.toInt())
    }

    /**
     * 단 개수(1-255)를 반환하는 함수
     * bit 2-9
     *
     * @return [Short] 단 개수 반환
     */
    fun getColumnCount() : Short = get(value, 2, 9).toShort()

    /**
     * 단 개수(1-255)를 설정하는 함수
     * bit 2-9
     *
     * @param [columnCount] [Short] 단 개수 값
     */
    fun setColumnCount(columnCount: Short) {
        value = set(value, 2, 9, columnCount.toInt())
    }

    /**
     * 단 방향 지정을 반환하는 함수
     * bit 10-11
     *
     * @return [HWPColumnDirection] 단 방향 지정 반환
     */
    fun getColumnDirection() : HWPColumnDirection = HWPColumnDirection.valueOf(get(value, 10, 11).toByte())

    /**
     * 단 방향 지정을 설정하는 함수
     * bit 10-11
     *
     * @param [columnDirection] [HWPColumnDirection] 단 방향 지정값
     */
    fun setColumnDirection(columnDirection: HWPColumnDirection) {
        value = set(value, 10, 11, columnDirection.value.toInt())
    }

    /**
     * 단 너비 동일하게 여부를 반환하는 함수
     * bit 12
     *
     * @return [Boolean] 단 너비 동일하게 여부 반환
     */
    fun isSameWidth() : Boolean = get(value, 12)

    /**
     * 단 너비 동일하게 여부를 설정하는 함수
     * bit 12
     *
     * @param [sameWidth] [Boolean] 단 너비 동일하게 여부 값
     */
    fun setSameWidth(sameWidth: Boolean) {
        value = set(value, 12, sameWidth)
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPColumnDefineHeaderProperty] 생성된 객체 반환
         */
        fun build(columnSort: HWPColumnSort = HWPColumnSort.Normal,
                  columnCount: Short = 0,
                  columnDirection: HWPColumnDirection = HWPColumnDirection.FromLeft,
                  sameWidth: Boolean = false)
                : HWPColumnDefineHeaderProperty = HWPColumnDefineHeaderProperty().apply {
            setColumnSort(columnSort)
            setColumnCount(columnCount)
            setColumnDirection(columnDirection)
            setSameWidth(sameWidth)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPColumnDefineHeaderProperty] 생성된 객체 반환
         */
        fun build(value: Int = 0) : HWPColumnDefineHeaderProperty = HWPColumnDefineHeaderProperty().apply {
            this.value = value
        }
    }
}