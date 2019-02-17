package com.tang.hwplib.objects.bodytext.control.gso.listheader

import com.tang.hwplib.objects.bodytext.control.ctrlheader.sectiondefine.HWPTextDirection
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPLineChange
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPTextVerticalAlignment
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 문단 리스트 헤더 속성을 나타내는 객체
 * Tag ID: HWPTAG_LIST_HEADER
 *
 * @author accforaus
 *
 * @property [value] [Long], 문단 리스트 헤더 속성값
 */
class HWPListHeaderProperty {
    var value: Long = 0
        set(newValue) {
            field = newValue
            _textDirection = getTextDirection()
            _lineChange = getLineChange()
            _textVerticalAlignment = getTextVerticalAlignment()
        }
    private var _textDirection : HWPTextDirection = HWPTextDirection.Horizontal
    private var _lineChange: HWPLineChange = HWPLineChange.Normal
    private var _textVerticalAlignment: HWPTextVerticalAlignment = HWPTextVerticalAlignment.Top
    /**
     * 텍스트 방향을 반환하는 함수
     * bit 0-2
     *
     * @return [HWPTextDirection] 텍스트 방향 반환
     */
    fun getTextDirection() : HWPTextDirection = HWPTextDirection.valueOf(get(value, 0, 2).toByte())

    /**
     * 텍스트 방향을 설정하는 함수
     * bit 0-2
     *
     * @param [textDirection] [HWPTextDirection] 텍스트 방향 값
     */
    fun setTextDirection(textDirection: HWPTextDirection) {
        value = set(value, 0, 2, textDirection.value.toInt())
    }

    /**
     * 문단의 줄바꿈을 반환하는 함수
     * bit 3-4
     *
     * @return [HWPLineChange] 문단의 줄바꿈 반환
     */
    fun getLineChange() : HWPLineChange = HWPLineChange.valueOf(get(value, 3, 4).toByte())

    /**
     * 문단의 줄바꿈을 설정하는 함수
     * bit 3-4
     *
     * @param [lineChange] [HWPLineChange] 문단의 줄바꿈값
     */
    fun setLineChange(lineChange: HWPLineChange) {
        value = set(value, 3, 4, lineChange.value.toInt())
    }

    /**
     * 세로 정렬을 반환하는 함수
     * bit 5-6
     *
     * @return [HWPTextVerticalAlignment] 세로 정렬 반환
     */
    fun getTextVerticalAlignment() : HWPTextVerticalAlignment = HWPTextVerticalAlignment.valueOf(get(value, 5, 6).toByte())

    /**
     * 세로 정렬을 설정하는 함수
     * bit 5-6
     *
     * @param [textVerticalAlignment] [HWPTextVerticalAlignment] 세로 정렬값
     */
    fun setTextVerticalAlignment(textVerticalAlignment: HWPTextVerticalAlignment) {
        value = set(value, 5, 6, textVerticalAlignment.value.toInt())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPTextDirection] 생성된 객체 반환
         */
        fun build(textDirection: HWPTextDirection = HWPTextDirection.Horizontal,
                  lineChange: HWPLineChange = HWPLineChange.Normal,
                  textVerticalAlignment: HWPTextVerticalAlignment = HWPTextVerticalAlignment.Top)
                : HWPListHeaderProperty = HWPListHeaderProperty().apply {
            setTextDirection(textDirection)
            setLineChange(lineChange)
            setTextVerticalAlignment(textVerticalAlignment)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPListHeaderProperty] 생성된 객체 반환
         */
        fun build(value: Long) : HWPListHeaderProperty = HWPListHeaderProperty().apply {
            this.value = value
        }
    }
}