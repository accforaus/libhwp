package com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.line

import com.tang.hwplib.objects.etc.Color4Byte
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 화살표 모양
 *
 * @author accforaus
 *
 * @property [valut] [Byte], 화살표 모양 값
 */
enum class HWPLineArrowShape(v: Byte) {
    /**
     * 모양 없음
     */
    None(0.toByte()),
    /**
     * 화살 모양
     */
    Arrow(1.toByte()),
    /**
     * 오목한 화살 모양
     */
    ConcaveArrow(2.toByte()),
    /**
     * 속이 빈 다이아몬드 모양
     */
    EmptyDiamond(3.toByte()),
    /**
     * 속이 빈 원 모양
     */
    EmptyCircle(4.toByte()),
    /**
     * 속이 빈 사각 모양
     */
    EmptyRectangle(5.toByte()),
    /**
     * 속이 채워진 다이아몬드 모양
     */
    Diamond(6.toByte()),
    /**
     * 속이 채워진 원 모양
     */
    Circle(7.toByte()),
    /**
     * 속이 채워진 사각 모양
     */
    Rectangle(8.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPLineArrowShape] enum 값
         */
        fun valueOf(v: Byte) : HWPLineArrowShape {
            for (las in values())
                if (las.value == v)
                    return las
            return None
        }
    }
}

/**
 * 화살표 크기
 *
 * @author accforaus
 *
 * @property [value] [Byte], 화살표 크기 값
 */
enum class HWPLineArrowSize(v: Byte) {
    /**
     * 작은-작은
     */
    SmallSmall(0.toByte()),
    /**
     * 작은-중간
     */
    SmallMiddle(1.toByte()),
    /**
     * 작은-큰
     */
    SmallBig(2.toByte()),
    /**
     * 중간-작은
     */
    MiddleSmall(3.toByte()),
    /**
     * 중간-중간
     */
    MiddleMiddle(4.toByte()),
    /**
     * 중간-큰
     */
    MiddleBig(5.toByte()),
    /**
     * 큰-작은
     */
    BigSmall(6.toByte()),
    /**
     * 큰-중간
     */
    BigMiddle(7.toByte()),
    /**
     * 큰-큰
     */
    BigBig(8.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPLineArrowSize] enum 값
         */
        fun valueOf(v: Byte) : HWPLineArrowSize {
            for (las in values())
                if (las.value == v)
                    return las
            return SmallSmall
        }
    }
}

/**
 * 선 끝 모양
 *
 * @author accforaus
 *
 * @property [value] [Byte], 선 끝 모양 값
 */
enum class HWPLineEndShape(v: Byte) {
    /**
     * round (그림일 때 default)
     */
    Round(0.toByte()),
    /**
     * flat (개체들일 때 default)
     */
    Flat(1.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPLineEndShape] enum 값
         */
        fun valueOf(v: Byte) : HWPLineEndShape {
            for (les in values())
                if (les.value == v)
                    return les
            return Round
        }
    }
}

/**
 * 선 종류
 *
 * @author accforaus
 *
 * @property [value] [Byte], 선 종류 값
 */
enum class HWPLineType(v: Byte) {
    /**
     * 없음
     */
    None(0.toByte()),
    /**
     * 실선
     */
    Solid(1.toByte()),
    /**
     * 긴 점선
     */
    Dash(2.toByte()),
    /**
     * 점선
     */
    Dot(3.toByte()),
    /**
     * -.-.-.-.-
     */
    DashDot(4.toByte()),
    /**
     * -..-..-..-..
     */
    DashDotDot(5.toByte()),
    /**
     * [DashDot]보다 긴 선분의 반복
     */
    LongDash(6.toByte()),
    /**
     * [Dot]보다 큰 동그라미의 반복
     */
    CircleDot(7.toByte()),
    /**
     * 2중선
     */
    Double(8.toByte()),
    /**
     * 가는선 + 굵은선 2중선
     */
    ThinBold(9.toByte()),
    /**
     * 굵은선 + 가는선 2중선
     */
    BoldThin(10.toByte()),
    /**
     * 가는선 + 굵은선 + 가는선 3중선
     */
    ThinBoldThin(11.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPLineType] enum 값
         */
        fun valueOf(v: Byte) : HWPLineType {
            for (lt in values())
                if (lt.value == v)
                    return lt
            return None
        }
    }
}

/**
 * Outline style
 *
 * @author accforaus
 *
 * @property [value] [Byte], outline style value
 */
enum class HWPOutlineStyle(v: Byte) {
    /**
     * normal
     */
    Normal(0.toByte()),
    /**
     * outer
     */
    Outter(1.toByte()),
    /**
     * inner
     */
    Inner(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPOutlineStyle] enum 값
         */
        fun valueOf(v: Byte) : HWPOutlineStyle {
            for (os in values())
                if (os.value == v)
                    return os
            return Normal
        }
    }
}

/**
 * 테두리 선 속성을 나타내는 객체
 *
 * @author accforaus
 *
 * @property [value] [Long], 테두리 선 속성 값
 */
class HWPLineInfoProperty {
    var value: Long = 0
        set(newValue) {
            field = newValue
            _lineType = getLineType()
            _lineEndShape = getLineEndShape()
            _startArrowShape = getStartArrowShape()
            _endArrowShape = getEndArrowShape()
            _startArrowSize = getStartArrowSize()
            _endArrowSize = getEndArrowSize()
            _isFillStartArrow = isFillStartArrow()
            _isFillEndArrow = isFillEndArrow()
        }
    private var _lineType: HWPLineType = HWPLineType.None
    private var _lineEndShape: HWPLineEndShape = HWPLineEndShape.Round
    private var _startArrowShape: HWPLineArrowShape = HWPLineArrowShape.Arrow
    private var _endArrowShape: HWPLineArrowShape = HWPLineArrowShape.Arrow
    private var _startArrowSize: HWPLineArrowSize = HWPLineArrowSize.SmallSmall
    private var _endArrowSize: HWPLineArrowSize = HWPLineArrowSize.SmallSmall
    private var _isFillStartArrow: Boolean = false
    private var _isFillEndArrow: Boolean = false
    /**
     * 선 종류를 반환하는 함수
     * bit 0-5
     *
     * @return [HWPLineType] 선 종류 반환
     */
    fun getLineType() : HWPLineType = HWPLineType.valueOf(get(value, 0, 5).toByte())

    /**
     * 선 종류를 설정하는 함수
     * bit 0-5
     *
     * @param [lineType] [HWPLineType] 선 종류값
     */
    fun setLineType(lineType: HWPLineType) {
        value = set(value, 0, 5, lineType.value.toInt())
    }

    /**
     * 선 끝 모양을 반환하는 함수
     * bit 6-9
     *
     * @return [HWPLineEndShape] 선 끝 모양 반환
     */
    fun getLineEndShape() : HWPLineEndShape = HWPLineEndShape.valueOf(get(value, 6, 9).toByte())

    /**
     * 선 끝 모양을 설정하는 함수
     * bit 6-9
     *
     * @param [lineEndShape] [HWPLineEndShape] 선 끝 모양값
     */
    fun setLineEndShape(lineEndShape: HWPLineEndShape) {
        value = set(value, 6, 9, lineEndShape.value.toInt())
    }

    /**
     * 화살표 시작 모양을 반환하는 함수
     * bit 10-15
     *
     * @return [HWPLineArrowSize] 화살표 시작 모양 반환
     */
    fun getStartArrowShape() : HWPLineArrowShape = HWPLineArrowShape.valueOf(get(value, 10, 15).toByte())

    /**
     * 화살표 시작 모양을 설정하는 함수
     * bit 10-15
     *
     * @param [startArrowShape] [HWPLineArrowSize] 화살표 시작 모양값
     */
    fun setStartArrowShape(startArrowShape: HWPLineArrowShape) {
        value = set(value, 10, 15, startArrowShape.value.toInt())
    }

    /**
     * 화살표 끝 모양을 반환하는 함수
     * bit 16-21
     *
     * @return [HWPLineArrowSize] 화살표 끝 모양 반환
     */
    fun getEndArrowShape() : HWPLineArrowShape = HWPLineArrowShape.valueOf(get(value, 16, 21).toByte())

    /**
     * 화살표 끝 모양을 설정하는 함수
     * bit 16-21
     *
     * @param [endArrowShape] [HWPLineArrowSize] 화살표 끝 모양값
     */
    fun setEndArrowShape(endArrowShape: HWPLineArrowShape) {
        value = set(value, 16, 21, endArrowShape.value.toInt())
    }

    /**
     * 화살표 시작 크기를 반환하는 함수
     * bit 22-25
     *
     * @return [HWPLineArrowSize] 화살표 시작 크기 반환
     */
    fun getStartArrowSize() : HWPLineArrowSize = HWPLineArrowSize.valueOf(get(value, 22, 25).toByte())

    /**
     * 화살표 시작 크기를 설정하는 함수
     * bit 22-25
     *
     * @param [startArrowSize] [HWPLineArrowSize] 화살표 시작 크기값
     */
    fun setStartArrowSize(startArrowSize: HWPLineArrowSize) {
        value = set(value, 22, 25, startArrowSize.value.toInt())
    }

    /**
     * 화살표 끝 크기를 반환하는 함수
     * bit 26-29
     *
     * @return [HWPLineArrowSize] 화살표 시작 크기 반환
     */
    fun getEndArrowSize() : HWPLineArrowSize = HWPLineArrowSize.valueOf(get(value, 26, 29).toByte())

    /**
     * 화살표 끈 크기를 설정하는 함수
     * bit 26-29
     *
     * @param [endArrowShape] [HWPLineArrowSize] 화살표 끝 크기값
     */
    fun setEndArrowSize(endArrowShape: HWPLineArrowSize) {
        value = set(value, 26, 29, endArrowShape.value.toInt())
    }

    /**
     * 시작부분 화살표 채움 여부를 반환하는 함수
     * bit 30
     *
     * @return [Boolean] 시작부분 화살표 채움 여부 반환
     */
    fun isFillStartArrow() : Boolean = get(value, 30)

    /**
     * 시작부분 화살표 채움 여부를 반환하는 함수
     * bit 30
     *
     * @param [fillStartArrow] [Boolean] 시작부분 화살표 채움값
     */
    fun setFillStartArrow(fillStartArrow: Boolean) {
        value = set(value, 30, fillStartArrow)
    }

    /**
     * 끝부분 화살표 채움 여부를 반환하는 함수
     * bit 31
     *
     * @return [Boolean] 끝부분 화살표 채움 여부 반환
     */
    fun isFillEndArrow() : Boolean = get(value, 31)

    /**
     * 끝부분 화살표 채움 여부를 반환하는 함수
     * bit 31
     *
     * @param [fillEndArrow] [Boolean] 끝부분 화살표 채움값
     */
    fun setFillEndArrow(fillEndArrow: Boolean) {
        value = set(value, 31, fillEndArrow)
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPLineInfoProperty] 생성된 객체 반환
         */
        fun build(lineType: HWPLineType = HWPLineType.None,
                  lineEndShape: HWPLineEndShape = HWPLineEndShape.Round,
                  startArrowShape: HWPLineArrowShape = HWPLineArrowShape.None,
                  endArrowShape: HWPLineArrowShape = HWPLineArrowShape.None,
                  startArrowSize: HWPLineArrowSize = HWPLineArrowSize.SmallSmall,
                  endArrowSize: HWPLineArrowSize = HWPLineArrowSize.SmallSmall,
                  fillStartArrow: Boolean = false, fillEndArrow: Boolean = false)
                : HWPLineInfoProperty = HWPLineInfoProperty().apply {
            setLineType(lineType)
            setLineEndShape(lineEndShape)
            setStartArrowShape(startArrowShape)
            setEndArrowShape(endArrowShape)
            setStartArrowSize(startArrowSize)
            setEndArrowSize(endArrowSize)
            setFillStartArrow(fillStartArrow)
            setFillEndArrow(fillEndArrow)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPLineInfoProperty] 생성된 객체 반환
         */
        fun build(value: Long = 0) : HWPLineInfoProperty = HWPLineInfoProperty().apply {
            this.value = value
        }
    }
}

/**
 * 테두리 선 정보를 나타내는 객체
 * 11 bytes
 *
 * @author accforaus
 *
 * @property [color] [Color4Byte], 선 색상 (COLORREF - unsigned 4 bytes)
 * @property [thickness] [Int], 선 굵기 (INT16 - signed 2 bytes)
 * @property [property] [HWPLineInfoProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [outlineStyle] [HWPOutlineStyle], Outline style (BYTE - unsigned 1 byte)
 */
class HWPLineInfo {
    var color: Color4Byte = Color4Byte()
    var thickness: Int = 0
    var property: HWPLineInfoProperty = HWPLineInfoProperty()
    var outlineStyle: HWPOutlineStyle = HWPOutlineStyle.Normal

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPLineInfo] 복사된 객체 반환
     */
    fun copy() : HWPLineInfo = HWPLineInfo().also {
        it.color.value = this.color.value
        it.thickness = this.thickness
        it.property.value = this.property.value
        it.outlineStyle.value = this.outlineStyle.value
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPLineInfo] 생성된 객체 반환
         */
        fun build(color: Color4Byte = Color4Byte.build(),
                  thickness: Int = 0,
                  property: HWPLineInfoProperty = HWPLineInfoProperty.build(),
                  outlineStyle: HWPOutlineStyle = HWPOutlineStyle.Normal)
                : HWPLineInfo = HWPLineInfo().apply {
            this.color = color
            this.thickness = thickness
            this.property = property
            this.outlineStyle = outlineStyle
        }
    }
}