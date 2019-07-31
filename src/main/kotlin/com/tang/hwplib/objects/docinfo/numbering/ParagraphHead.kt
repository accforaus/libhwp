package com.tang.hwplib.objects.docinfo.numbering

import com.tang.hwplib.annotation.ID
import com.tang.hwplib.annotation.IDTypes
import com.tang.hwplib.annotation.LinkID
import com.tang.hwplib.objects.docinfo.HWPCharShape
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 수준별 본문과의 거리 종류
 * bit 4
 *
 * @author accforaus
 *
 * @property [value] [Byte], 수준별 본문과의 거리 종류값을 가진 데이터
 */
enum class HWPValueType(v: Byte) {
    /**
     * 글자 크기에 대한 상대 비율
     */
    RatioForLetter(0.toByte()),
    /**
     * 값
     */
    Value(1.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPValueType] enum 값
         */
        fun valueOf(v: Byte) : HWPValueType {
            for (vt in values())
                if (vt.value == v)
                    return vt
            return RatioForLetter
        }
    }
}

/**
 * 문단의 정렬 종류
 * bit 0-1
 *
 * @author accforaus
 *
 * @property [value] [Byte], 문단의 정렬 종류값을 가진 데이터
 */
enum class HWPParagraphAlignment(v: Byte) {
    /**
     * 왼쪽
     */
    Left(0.toByte()),
    /**
     * 가운데
     */
    Center(1.toByte()),
    /**
     * 오른쪽
     */
    Right(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPParagraphAlignment] enum 값
         */
        fun valueOf(v: Byte) : HWPParagraphAlignment {
            for (pa in values())
                if (pa.value == v)
                    return pa
            return Left
        }
    }
}

/**
 * 문단 머리 정보 속성을 나타내는 객체
 * UINT - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 문단 머리 정보 속성값을 가진 데이터
 */
class HWPParagraphHeadInfoProperty {
    var value: Long = 0
        set(newValue) {
            field = newValue
            _paragraphAlignment = getParagraphAlignment()
            _followStringWidth = isFollowStringWidth()
            _autoIndent = isAutoIndent()
            _valueTypeForDistanceFromBody = getValueTypeForDistanceFromBody()
        }
    private var _paragraphAlignment: HWPParagraphAlignment = HWPParagraphAlignment.Left
    private var _followStringWidth: Boolean = false
    private var _autoIndent: Boolean = false
    private var _valueTypeForDistanceFromBody: HWPValueType = HWPValueType.RatioForLetter

    /**
     * 문단의 정렬 종류를 반환하는 함수
     * bit 0-1
     *
     * @return [HWPParagraphAlignment] 문단의 정렬 종류 반환
     */
    fun getParagraphAlignment() : HWPParagraphAlignment = HWPParagraphAlignment.valueOf(get(value,0,1).toByte())

    /**
     * 문단의 정렬 종류를 설정하는 함수
     * bit 0-1
     *
     * @param [paragraphAlignment] [HWPParagraphAlignment], 문단의 정렬 종류값을 가진 데이터
     */
    fun setParagraphAlignment(paragraphAlignment: HWPParagraphAlignment) {
        value = set(value, 0, 1, paragraphAlignment.value.toInt())
    }

    /**
     * 번호 너비를 실제 인스턴스 문자열의 너비에 따를지 여부를 반환하는 함수
     * bit 2
     *
     * @return [Boolean] 번호 너비를 실제 인스턴스 문자열의 너비에 따를지 여부 반환
     */
    fun isFollowStringWidth() : Boolean = get(value, 2)

    /**
     * 번호 너비를 실제 인스턴스 문자열의 너비에 따를지 여부를 설정하는 함수
     * bit 2
     *
     * @param [followStringWidth] [Boolean], 번호 너비를 실제 인스턴스 문자열의 너비에 따를지 여부의 참/거짓 값을 가진 데이터
     */
    fun setFollowStringWidth(followStringWidth: Boolean) {
        value = set(value, 2, followStringWidth)
    }

    /**
     * 자동 내어 쓰기 여부를 반환하는 함수
     * bit 3
     *
     * @return [Boolean] 자동 내어 쓰기 여부를 반환
     */
    fun isAutoIndent() : Boolean = get(value, 3)

    /**
     * 자동 내어 쓰기 여부를 설정하는 함수
     * bit 3
     *
     * @param [autoIndent] [Boolean], 자동 내어 쓰기 여부의 참/거짓 값을 가진 데이터
     */
    fun setAutoIndent(autoIndent: Boolean) {
        value = set(value, 3, autoIndent)
    }

    /**
     * 수준별 본문과의 거리 종류를 반환하는 함수
     * bit 4
     *
     * @return [HWPValueType] 수준별 본문과의 거리 종류 반환
     */
    fun getValueTypeForDistanceFromBody() : HWPValueType = if (!get(value, 4)) HWPValueType.RatioForLetter else HWPValueType.Value

    /**
     * 수준별 본문과의 거리 종류를 설정하는 함수
     * bit 4
     *
     * @param [valueType] [HWPValueType], 수준별 본문과의 거리 종류값을 가진 데이터
     */
    fun setValueTypeForDistanceFromBody(valueType: HWPValueType) {
        if (valueType == HWPValueType.RatioForLetter)
            value = set(value, 4, false)
        else
            value = set(value, 4, true)
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPParagraphHeadInfoProperty] 생성된 객체 반환
         */
        fun build(paragraphAlignment: HWPParagraphAlignment = HWPParagraphAlignment.Left,
                  isFollowStringWidth: Boolean = false,
                  isAutoIndent: Boolean = false,
                  valueTypeForDistanceFromBody: HWPValueType = HWPValueType.RatioForLetter)
                : HWPParagraphHeadInfoProperty = HWPParagraphHeadInfoProperty().apply {
            setParagraphAlignment(paragraphAlignment)
            setFollowStringWidth(isFollowStringWidth)
            setAutoIndent(isAutoIndent)
            setValueTypeForDistanceFromBody(valueTypeForDistanceFromBody)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPParagraphHeadInfoProperty] 생성된 객체 반환
         */
        fun build(value: Long = 0): HWPParagraphHeadInfoProperty = HWPParagraphHeadInfoProperty().apply {
            this.value = value
        }
    }

    override fun equals(other: Any?): Boolean = value == (other as HWPParagraphHeadInfoProperty).value
}

/**
 * 문단 머리 정보를 나타내는 객체
 * 8 bytes
 *
 * @author accforaus
 *
 * @property [property] [HWPParagraphHeadInfoProperty], 속성 (UINT - unsigned 4 bytes)
 * @property [correctionValueForWidth] [Short], 너비 보정값 (HWPUINT16 - unsigned 2 bytes)
 * @property [distanceFromBody] [Short], 본문과의 거리 (HWPUINT16 - unsigned 2 bytes)
 * @property [charShapeID] [Long], 글자 모양[HWPCharShape] 아이디 참조 (UINT - unsigned 4 bytes)
 */
@LinkID class HWPParagraphHeadInfo {
    var property: HWPParagraphHeadInfoProperty = HWPParagraphHeadInfoProperty()
    var correctionValueForWidth: Short = 0
    var distanceFromBody: Short = 0
    @ID(IDTypes.CharShape)
    var charShapeID: Long = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPParagraphHeadInfo] 복사된 객체 반환
     */
    fun copy() : HWPParagraphHeadInfo = HWPParagraphHeadInfo().also {
        it.property.value = this.property.value
        it.correctionValueForWidth = this.correctionValueForWidth
        it.distanceFromBody = this.distanceFromBody
        it.charShapeID = this.charShapeID
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPParagraphHeadInfo] 생성된 객체 반환
         */
        fun build(property: HWPParagraphHeadInfoProperty = HWPParagraphHeadInfoProperty.build(),
                  correctionValueForWidth: Short = 0,
                  distanceFromBody: Short = 0,
                  charShapeID: Long = 0)
                : HWPParagraphHeadInfo = HWPParagraphHeadInfo().apply {
            this.property = property
            this.correctionValueForWidth = correctionValueForWidth
            this.distanceFromBody = distanceFromBody
            this.charShapeID = charShapeID
        }
    }

    override fun equals(other: Any?): Boolean = (other as HWPParagraphHeadInfo).let {
        return property == it.property
                && correctionValueForWidth == it.correctionValueForWidth
                && distanceFromBody == it.distanceFromBody
    }
}