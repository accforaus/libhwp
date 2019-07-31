package com.tang.hwplib.objects.docinfo.tabdef

import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.util.compare.nullEquals

/**
 * 탭의 종류
 * UINT8 - unsigned 1 byte
 *
 * @author accforaus
 *
 * @property [value] [Byte], 탭의 종류의 값을 가진 데이터
 */
enum class HWPTabSort(v: Byte) {
    /**
     * 왼쪽
     */
    Left(0.toByte()),
    /**
     * 오른쪽
     */
    Right(1.toByte()),
    /**
     * 가운데
     */
    Center(2.toByte()),
    /**
     * 소수점
     */
    DecimalPoint(3.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPTabSort] enum 값
         */
        fun valueOf(v: Byte) : HWPTabSort {
            for (ts in values())
                if (ts.value == v)
                    return ts
            return Left
        }
    }
}

/**
 * 탭 정보를 나타내는 객체
 * count 개수만큼
 * 8 bytes
 *
 * @author accforaus
 *
 * @property [position] [Long], 탭의 위치 (HWPUINT - unsigned 4 bytes)
 * @property [tabSort] [HWPTabSort], 탭의 종류 (UINT8 - unsigned 1 byte)
 * @property [fillSort] [HWPBorderType], 채움 종류 (UINT8 - unsigned 1 byte)
 */
class HWPTabInfo {
    var position: Long = 0
    var tabSort: HWPTabSort? = null
    var fillSort: HWPBorderType? = null

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPTabInfo] 복사된 객체 반환
     */
    fun copy() : HWPTabInfo = HWPTabInfo().also {
        it.position = this.position
        this.tabSort?.run { it.tabSort = HWPTabSort.valueOf(this.value) }
        this.fillSort?.run { it.fillSort = HWPBorderType.valueOf(this.value) }
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPTabInfo] 생성된 객체 반환
         */
        fun build(position: Long = 0, tabSort: HWPTabSort? = null, fillSort: HWPBorderType? = null)
                : HWPTabInfo = HWPTabInfo().apply {
            this.position = position
            this.tabSort = tabSort
            this.fillSort = fillSort
        }
    }

    override fun equals(other: Any?): Boolean = (other as HWPTabInfo).let {
        return position == it.position
                && tabSort == it.tabSort
                && nullEquals(fillSort, it.fillSort)
    }
}