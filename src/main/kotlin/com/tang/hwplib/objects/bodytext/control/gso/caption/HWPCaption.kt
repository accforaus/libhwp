package com.tang.hwplib.objects.bodytext.control.gso.caption

import com.tang.hwplib.objects.bodytext.control.gso.listheader.HWPListHeaderProperty
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphList
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set
import com.tang.hwplib.objects.etc.LIST_HEADER

/**
 * 캡션 방향
 * bit 0-1
 *
 * @author accforaus
 *
 * @property [value] [Byte], 캡션 방향값
 */
enum class HWPCaptionDirection(v: Byte) {
    /**
     * left
     */
    Left(0.toByte()),
    /**
     * right
     */
    Right(1.toByte()),
    /**
     * top
     */
    Top(2.toByte()),
    /**
     * bottom
     */
    Bottom(3.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPCaptionDirection] enum 값
         */
        fun valueOf(v: Byte) : HWPCaptionDirection {
            for (cd in values())
                if (cd.value == v)
                    return cd
            return Left
        }
    }
}

/**
 * 캡션 속성을 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 캡션 속성값
 */
class ListHeaderCaptionProperty {
    var value: Long = 0

    /**
     * 캡션 방향을 반환하는 함수
     * bit 0-1
     *
     * @return [HWPCaptionDirection] 캡션 방향을 반환
     */
    fun getDirection() : HWPCaptionDirection = HWPCaptionDirection.valueOf(get(value, 0, 1).toByte())

    /**
     * 캡션 방향을 설정하는 함수
     * bit 0-1
     *
     * @param [direction] [HWPCaptionDirection] 캡션 방향값
     */
    fun setDirection(direction: HWPCaptionDirection) {
        value = set(value, 0, 1, direction.value.toInt())
    }

    /**
     * 캡션 폭에 마진을 포함할 지 여부를 반환하는 함수
     * bit 2
     *
     * @return [Boolean] 캡션 폭에 마진을 포함할 지 여부 번환
     */
    fun isIncludeMargin() : Boolean = get(value, 2)

    /**
     * 캡션 폭에 마진을 포함할 지 여부를 반환하는 함수
     * bit 2
     *
     * @param [includeMargin] [Boolean], 캡션 폭에 마진을 포함할 지 여부 값
     */
    fun setIncludeMargin(includeMargin: Boolean) {
        value = set(value, 2, includeMargin)
    }
}

/**
 * 캡션 문단 리스트 헤더를 나타내는 객체
 * Tag ID - HWPTAG_LIST_HEADER [LIST_HEADER]
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [paraCount] [Int], 문단 수 (INT16 - signed 2 bytes)
 * @property [property] [HWPListHeaderProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [captionProperty] [ListHeaderCaptionProperty], 캡션 속성 (UINT32 - unsigned 4 bytes)
 * @property [captionWidth] [Long], 캡션 폭 (세로 방향일 때만 사용) (HWPUNIT - unsigned 4 bytes)
 * @property [spaceBetweenCaptionAndFrame] [Int], 캡션과 특 사이 간격 (HWPUNIT16 - unsigned 2 bytes)
 * @property [textWidth] [Long], 텍스트의 최대 길이 (=개채의 폭) (HWPUNIT - unsigned 4 bytes)
 */
class ListHeaderForHWPCaption {
    var paraCount: Int = 0
    var property: HWPListHeaderProperty = HWPListHeaderProperty()
    var captionProperty: ListHeaderCaptionProperty = ListHeaderCaptionProperty()
    var captionWidth: Long = 0
    var spaceBetweenCaptionAndFrame: Int = 0
    var textWidth: Long = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [ListHeaderForHWPCaption] 복사된 객체 반환
     */
    fun copy() : ListHeaderForHWPCaption = ListHeaderForHWPCaption().also {
        it.paraCount = this.paraCount
        it.property.value = this.property.value
        it.captionProperty.value = this.captionProperty.value
        it.captionWidth = this.captionWidth
        it.spaceBetweenCaptionAndFrame = this.spaceBetweenCaptionAndFrame
        it.textWidth = this.textWidth
    }
}

/**
 * 캡션을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [listHeaderForCaption] [ListHeaderCaptionProperty], 캡션 문단 리스트
 * @property [paragraphList] [HWPParagraphList], 문단 리스트
 */
class HWPCaption {
    var listHeaderForCaption: ListHeaderForHWPCaption = ListHeaderForHWPCaption()
    var paragraphList: HWPParagraphList = HWPParagraphList()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCaption] 복사된 객체 반환
     */
    fun copy() : HWPCaption = HWPCaption().also {
        it.listHeaderForCaption = this.listHeaderForCaption.copy()
        it.paragraphList = this.paragraphList.copy()
    }
}