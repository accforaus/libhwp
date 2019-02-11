package com.tang.hwplib.objects.bodytext.control.ctrlheader.gso

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 오브젝트 폭의 기준
 *
 * @author accforaus
 *
 * @property [value] [Byte], 오브젝트 폭의 기준값
 */
enum class HWPWidthCriterion(v: Byte) {
    /**
     * paper
     */
    Paper(0.toByte()),
    /**
     * page
     */
    Page(1.toByte()),
    /**
     * column
     */
    Column(2.toByte()),
    /**
     * para
     */
    Para(3.toByte()),
    /**
     * absolute
     */
    Absolute(4.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPWidthCriterion] enum 값
         */
        fun valueOf(v: Byte) : HWPWidthCriterion {
            for (wc in values())
                if (wc.value == v)
                    return wc
            return Paper
        }
    }
}

/**
 * 오브젝트 높이의 기준
 *
 * @author accforaus
 *
 * @property [value] [Byte], 오브젝트 높이의 기준값
 */
enum class HWPHeightCriterion(v: Byte) {
    /**
     * paper
     */
    Paper(0.toByte()),
    /**
     * page
     */
    Page(1.toByte()),
    /**
     * absolute
     */
    Absolute(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPHeightCriterion] enum 값
         */
        fun valueOf(v: Byte) : HWPHeightCriterion {
            for (hc in values())
                if (hc.value == v)
                    return hc
            return Paper
        }
    }
}

/**
 * 가로 위치 기준
 * bit 8-9
 *
 * @author accforaus
 *
 * @property [value] [Byte], 가로 위치 기준값
 */
enum class HWPHorzRelTo(v: Byte) {
    /**
     * paper
     */
    Paper(0.toByte()),
    /**
     * page
     */
    Page(1.toByte()),
    /**
     * column
     */
    Column(2.toByte()),
    /**
     * para
     */
    Para(3.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPHorzRelTo] enum 값
         */
        fun valueOf(v: Byte) : HWPHorzRelTo {
            for (hrt in values())
                if (hrt.value == v)
                    return hrt
            return Paper
        }
    }
}

/**
 * 세로 위치 기준
 * bit 3-4
 *
 * @author accforaus
 *
 * @property [value] [Byte], 세로 위치 기준값
 */
enum class HWPVertRelTo(v: Byte) {
    /**
     * paper
     */
    Paper(0.toByte()),
    /**
     * page
     */
    Page(1.toByte()),
    /**
     * para
     */
    Para(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPVertRelTo] enum 값
         */
        fun valueOf(v: Byte) : HWPVertRelTo {
            for (vrt in values())
                if (vrt.value == v)
                    return vrt
            return Paper
        }
    }
}

/**
 * 이 개체가 속하는 번호 범주
 *
 * @author accforaus
 *
 * @property [value] [Byte], 이 개체가 속하는 번호 범주값
 */
enum class HWPObjectNumberSort(v: Byte) {
    /**
     * none
     */
    None(0.toByte()),
    /**
     * figure
     */
    Figure(1.toByte()),
    /**
     * table
     */
    Table(2.toByte()),
    /**
     * equation
     */
    Equation(3.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPObjectNumberSort] enum 값
         */
        fun valueOf(v: Byte) : HWPObjectNumberSort {
            for (ons in values())
                if (ons.value == v)
                    return ons
            return None
        }
    }
}

/**
 * 세로/가로 위치의 기준에 대한 상대적인 배열 방식
 * bit 5-7
 *
 * @author accforaus
 *
 * @property [value] [Byte], 세로/가로 위치의 기주에 대한 상대적인 배열 방식값
 */
enum class HWPRelativeArrange(v: Byte) {
    /**
     * [HWPVertRelTo]이 [HWPVertRelTo.Paper]나 [HWPVertRelTo.Page]이면 top, 그렇지 않으면 left
     * [HWPHorzRelTo]이 [HWPHorzRelTo.Paper]나 [HWPHorzRelTo.Page]이면 top, 그렇지 않으면 left
     */
    TopOfLeft(0.toByte()),
    /**
     * [HWPVertRelTo]이 [HWPVertRelTo.Paper]나 [HWPVertRelTo.Page]이면 center
     * [HWPHorzRelTo]이 [HWPHorzRelTo.Paper]나 [HWPHorzRelTo.Page]이면 center
     */
    Center(1.toByte()),
    /**
     * [HWPVertRelTo]이 [HWPVertRelTo.Paper]나 [HWPVertRelTo.Page]이면 bottom, 그렇지 않으면 right
     * [HWPHorzRelTo]이 [HWPHorzRelTo.Paper]나 [HWPHorzRelTo.Page]이면 bottom, 그렇지 않으면 right
     */
    BottomOrRight(2.toByte()),
    /**
     * [HWPVertRelTo]이 [HWPVertRelTo.Paper]나 [HWPVertRelTo.Page]이면 inside
     * [HWPHorzRelTo]이 [HWPHorzRelTo.Paper]나 [HWPHorzRelTo.Page]이면 inside
     */
    Inside(3.toByte()),
    /**
     * [HWPVertRelTo]이 [HWPVertRelTo.Paper]나 [HWPVertRelTo.Page]이면 outside
     * [HWPHorzRelTo]이 [HWPHorzRelTo.Paper]나 [HWPHorzRelTo.Page]이면 outside
     */
    Outside(4.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPRelativeArrange] enum 값
         */
        fun valueOf(v: Byte) : HWPRelativeArrange {
            for (ra in values())
                if (ra.value == v)
                    return ra
            return TopOfLeft
        }
    }
}

/**
 * 오브젝트 주위를 텍스트가 어떻게 흘러갈지 지정하는 옵션
 * bit 21-23
 *
 * @author accforaus
 *
 * @property [value] [Byte] 오브젝트 주위를 텍스트가 어떻게 흘러갈지 지정하는 옵션값
 */
enum class HWPTextFlowMethod(v: Byte) {
    /**
     * Square
     * bound rect를 따라
     */
    Square(0.toByte()),
    /**
     * Tight
     * 오브젝트의 outline을 따라
     */
    Tight(1.toByte()),
    /**
     * Through
     * 오브젝트 내부의 빈 공간 까지
     */
    Through(2.toByte()),
    /**
     * TopAndBottom
     * 좌, 우에는 텍스트를 배치하지 않음
     */
    TopAndBottom(3.toByte()),
    /**
     * BehindText
     * 글과 겹치게 하여 글 뒤로
     */
    BehindText(4.toByte()),
    /**
     * InFrontOfText
     * 글과 겹치게 하여 글 앞으로
     */
    InFrontOfText(5.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPTextFlowMethod] enum 값
         */
        fun valueOf(v: Byte) : HWPTextFlowMethod {
            for (tfm in values())
                if (tfm.value == v)
                    return tfm
            return Square
        }
    }
}

/**
 * 오브젝트의 좌/우 어느 족에 글을 배치할지 지정하는 옵션
 *
 * @author accforaus
 *
 * @property [value] [Byte], 오브젝트의 좌/우 어느 족에 글을 배치할지 지정하는 옵션값
 */
enum class HWPTextHorzArrange(v: Byte) {
    /**
     * BothSides
     */
    BothSides(0.toByte()),
    /**
     * LeftOnly
     */
    LeftOnly(1.toByte()),
    /**
     * RightOnly
     */
    RightOnly(2.toByte()),
    /**
     * LargestOnly
     */
    LargestOnly(3.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPTextHorzArrange] enum 값
         */
        fun valueOf(v: Byte) : HWPTextHorzArrange {
            for (tha in values())
                if (tha.value == v)
                    return tha
            return BothSides
        }
    }
}

/**
 * Gso 컨트롤 헤더의 속성을 나타내는 객체
 * 개체 공통 속성의 속성
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], Gso 컨트롤 헤더값
 */
class HWPGsoHeaderProperty {
    var value: Long = 0

    /**
     * 글자처럼 취급 여부를 반환하는 함수
     * bit 0
     *
     * @return [Boolean] 글자처럼 취급 여부 반환
     */
    fun isLikeWord() : Boolean = get(value, 0)

    /**
     * 글자처럼 취급 여부를 설정하는 함수
     * bit 0
     *
     * @param [likeWord] [Boolean], 글자처럼 취급 여부의 참/거짓 값을 가진 데이터
     */
    fun setLikeWord(likeWord: Boolean) {
        set(value, 0, likeWord)
    }

    /**
     * 줄 간격에 영향을 줄지 여부 여부를 반환하는 함수
     * bit 2
     *
     * @return [Boolean] 줄 간격에 영향을 줄지 여부 여부 반환
     */
    fun isApplyLineSpace() : Boolean = get(value, 2)

    /**
     * 줄 간격에 영향을 줄지 여부 여부를 설정하는 함수
     * bit 2
     *
     * @param [applyLineSpace] [Boolean], 줄 간격에 영향을 줄지 여부 여부의 참/거짓 값을 가진 데이터
     */
    fun setApplyLineSpace(applyLineSpace: Boolean) {
        value = set(value, 2, applyLineSpace)
    }

    /**
     * 세로 위기의 기준 [HWPVertRelTo]를 반환하는 함수
     *
     * @return [HWPVertRelTo], 세로 위기의 기준 반환
     */
    fun getVertRelTo() : HWPVertRelTo = HWPVertRelTo.valueOf(get(value, 3, 4).toByte())

    /**
     * 세로 위기의 기준 [HWPVertRelTo]를 설정하는 함수
     *
     * @param [vertRelTo] [HWPVertRelTo], 세로 위치 기준값
     */
    fun setVertRelTo(vertRelTo: HWPVertRelTo) {
        value = set(value, 3, 4, vertRelTo.value.toInt())
    }

    /**
     * 세로 위기의 기준에 대한 상대적인 배열 방식을 반환하는 함수
     *
     * @return [HWPRelativeArrange] 세로 위기의 기준에 대한 상대적인 배열 방식 반환
     */
    fun getVertRelativeArrange() : HWPRelativeArrange = HWPRelativeArrange.valueOf(get(value, 5, 7).toByte())

    /**
     * 세로 위기의 기준에 대한 상대적인 배열 방식을 설정하는 함수
     *
     * @param [vertRelativeArrange] [HWPRelativeArrange] 세로 위기의 기준에 대한 상대적인 배열 방식값
     */
    fun setVertRelativeArrange(vertRelativeArrange: HWPRelativeArrange) {
        value = set(value, 5, 7, vertRelativeArrange.value.toInt())
    }

    /**
     * 가로 위기의 기준 [HWPHorzRelTo]를 반환하는 함수
     *
     * @return [HWPHorzRelTo], 가로 위기의 기준 반환
     */
    fun getHorzRelTo() : HWPHorzRelTo = HWPHorzRelTo.valueOf(get(value, 8, 9).toByte())

    /**
     * 가로 위기의 기준 [HWPHorzRelTo]를 설정하는 함수
     *
     * @param [horzRelTo] [HWPHorzRelTo], 가로 위치 기준값
     */
    fun setHorzRelTo(horzRelTo: HWPHorzRelTo) {
        value = set(value, 8, 9, horzRelTo.value.toInt())
    }

    /**
    * 가로 위기의 기준에 대한 상대적인 배열 방식을 반환하는 함수
    *
    * @return [HWPRelativeArrange] 가로 위기의 기준에 대한 상대적인 배열 방식 반환
    */
    fun getHorzRelativeArrange() : HWPRelativeArrange = HWPRelativeArrange.valueOf(get(value, 10, 12).toByte())

    /**
     * 가로 위기의 기준에 대한 상대적인 배열 방식을 설정하는 함수
     *
     * @param [vertRelativeArrange] [HWPRelativeArrange] 가로 위기의 기준에 대한 상대적인 배열 방식값
     */
    fun setHorzRelativeArrange(horzRelativeArrange: HWPRelativeArrange) {
        value = set(value, 10, 12, horzRelativeArrange.value.toInt())
    }

    /**
     * [HWPVertRelTo]이 [HWPVertRelTo.Para]일 때 오브젝트의 세로 위치를 본문 영역으로 제한할지 여부를 반환하는 함수
     * bit 13
     * off(0) / on(1)
     *
     * @return [Boolean] [HWPVertRelTo]이 [HWPVertRelTo.Para]일 때 오브젝트의 세로 위치를 본문 영역으로 제한할지 여부 반환
     */
    fun isVertRelToParaLimit() : Boolean = get(value, 13)

    /**
     * [HWPVertRelTo]이 [HWPVertRelTo.Para]일 때 오브젝트의 세로 위치를 본문 영역으로 제한할지 여부를 설정하는 함수
     * bit 13
     * off(0) / on(1)
     *
     * @param [vertRelToParaLimit] [Boolean], [HWPVertRelTo]이 [HWPVertRelTo.Para]일 때 오브젝트의 세로 위치를 본문 영역으로 제한할지 여부의 참/거짓 값을 가진 데이터
     */
    fun setVertRelToParaLimit(vertRelToParaLimit: Boolean) {
        value = set(value, 13, vertRelToParaLimit)
    }

    /**
     * 다른 오브젝트와 겹치는 것을 허용할지 여부를 반환하는 함수
     * bit 14
     * 오브젝트의 위치가 본문 영역으로 제한되면 언제나 false로 간주한다
     *
     * @return [Boolean] 다른 오브젝트와 겹치는 것을 허용할지 여부 반환
     */
    fun isAllowOverlap() : Boolean = get(value, 14)

    /**
     * 다른 오브젝트와 겹치는 것을 허용할지 여부를 설정하는 함수
     * bit 14
     * 오브젝트의 위치가 본문 영역으로 제한되면 언제나 false로 간주한다
     *
     * @param [allowOverlap] [Boolean], 다른 오브젝트와 겹치는 것을 허용할지 여부의 참/거짓 값을 가진 데이터
     */
    fun setAllowOverlap(allowOverlap: Boolean) {
        value = set(value, 14, allowOverlap)
    }

    /**
     * 오브젝트 폭의 기준을 반환하는 함수
     *
     * @return [HWPWidthCriterion] 오브젝트 폭의 기준 반환
     */
    fun getWidthCriterion() : HWPWidthCriterion = HWPWidthCriterion.valueOf(get(value, 15, 17).toByte())

    /**
     * 오브젝트 폭의 기준을 설정하는 함수
     *
     * @param [widthCriterion] [HWPWidthCriterion], 오브젝트 폭의 기준값
     */
    fun setWidthCriterion(widthCriterion: HWPWidthCriterion) {
        value = set(value, 15, 17, widthCriterion.value.toInt())
    }

    /**
     * 오브젝트 높이의 기준을 반환하는 함수
     *
     * @return [HWPHeightCriterion] 오브젝트 높이의 기준 반환
     */
    fun getHeightCriterion() : HWPHeightCriterion = HWPHeightCriterion.valueOf(get(value, 18, 19).toByte())

    /**
     * 오브젝트 높이의 기준을 설정하는 함수
     *
     * @param [heightCriterion] [HWPWidthCriterion], 오브젝트 높이의 기준값
     */
    fun setHeightCriterion(heightCriterion: HWPHeightCriterion) {
        value = set(value, 18, 19, heightCriterion.value.toInt())
    }

    /**
     * [HWPVertRelTo]이 [HWPVertRelTo.Para]일 때 크기 보호 여부를 반환하는 함수
     * bit 20
     * off(0) / on(1)
     *
     * @return [Boolean] [HWPVertRelTo]이 [HWPVertRelTo.Para]일 때 크기 보호 여부 반환
     */
    fun isProtectSize() : Boolean = get(value, 20)

    /**
     * [HWPVertRelTo]이 [HWPVertRelTo.Para]일 때 크기 보호 여부를 설정하는 함수
     * bit 20
     * off(0) / on(1)
     *
     * @param [protectSize] [Boolean], [HWPVertRelTo]이 [HWPVertRelTo.Para]일 때 크기 보호 여부의 참/거짓이 저장된 데이터
     */
    fun setProtectSize(protectSize: Boolean) {
        value = set(value, 20, protectSize)
    }

    /**
     * 오브젝트 주위를 텍스트가 어떻게 흘러갈지 지정하는 옵션을 반환하는 함수
     *
     * @return [HWPTextFlowMethod] 오브젝트 주위를 텍스트가 어떻게 흘러갈지 지정하는 옵션을 반환
     */
    fun getTextFlowMethod() : HWPTextFlowMethod = HWPTextFlowMethod.valueOf(get(value, 21, 23).toByte())

    /**
     * 오브젝트 주위를 텍스트가 어떻게 흘러갈지 지정하는 옵션을 설정하는 함수
     *
     * @param [textFlowMethod] [HWPTextFlowMethod], 오브젝트 주위를 텍스트가 어떻게 흘러갈지 지정하는 옵션값
     */
    fun setTextFlowMethod(textFlowMethod: HWPTextFlowMethod) {
        value = set(value, 21, 23, textFlowMethod.value.toInt())
    }

    /**
     * 오브젝트의 좌/우 어느쪽에 글을 배치할지 지정하는 옵션을 반환하는 함수
     *
     * @return [HWPTextHorzArrange] 오브젝트의 좌/우 어느쪽에 글을 배치할지 지정하는 옵션 반환
     */
    fun getTextHorzArrange() : HWPTextHorzArrange = HWPTextHorzArrange.valueOf(get(value, 24, 25).toByte())

    /**
     * 오브젝트의 좌/우 어느쪽에 글을 배치할지 지정하는 옵션을 반환하는 함수
     *
     * @param [textHorzArrange] [HWPTextHorzArrange] 오브젝트의 좌/우 어느쪽에 글을 배치할지 지정하는 옵션값
     */
    fun setTextHorzArrange(textHorzArrange: HWPTextHorzArrange) {
        value = set(value, 24, 25, textHorzArrange.value.toInt())
    }

    /**
     * 이 개체가 속하는 번호 범주를 반환하는 함수
     *
     * @return [HWPObjectNumberSort] 이 개체가 속하는 번호 범주 반환
     */
    fun getObjectNumberSort() : HWPObjectNumberSort = HWPObjectNumberSort.valueOf(get(value, 26, 28).toByte())

    /**
     * 이 개체가 속하는 번호 범주를 설정하는 함수
     *
     * @param [objectNumberSort] [HWPObjectNumberSort], 이 개체가 속하는 번호 범주값
     */
    fun setObjectNumberSort(objectNumberSort: HWPObjectNumberSort) {
        value = set(value, 26, 28, objectNumberSort.value.toInt())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPGsoHeaderProperty] 생성된 객체 반환
         */
        fun build(likeWord: Boolean = false,
                  applyLineSpace: Boolean = false,
                  vertRelTo: HWPVertRelTo = HWPVertRelTo.Page,
                  vertRelativeArrange: HWPRelativeArrange = HWPRelativeArrange.TopOfLeft,
                  horzRelTo: HWPHorzRelTo = HWPHorzRelTo.Page,
                  horzRelativeArrange: HWPRelativeArrange = HWPRelativeArrange.TopOfLeft,
                  vertRelToParaLimit: Boolean = false,
                  allowOverlap: Boolean = false,
                  widthCriterion: HWPWidthCriterion = HWPWidthCriterion.Page,
                  heightCriterion: HWPHeightCriterion = HWPHeightCriterion.Page,
                  protectSize: Boolean = false,
                  textFlowMethod: HWPTextFlowMethod = HWPTextFlowMethod.Square,
                  textHorzArrange: HWPTextHorzArrange = HWPTextHorzArrange.BothSides,
                  objectNumberSort: HWPObjectNumberSort = HWPObjectNumberSort.None)
                : HWPGsoHeaderProperty = HWPGsoHeaderProperty().apply {
            setLikeWord(likeWord)
            setApplyLineSpace(applyLineSpace)
            setVertRelTo(vertRelTo)
            setVertRelativeArrange(vertRelativeArrange)
            setHorzRelTo(horzRelTo)
            setHorzRelativeArrange(horzRelativeArrange)
            setVertRelToParaLimit(vertRelToParaLimit)
            setAllowOverlap(allowOverlap)
            setWidthCriterion(widthCriterion)
            setHeightCriterion(heightCriterion)
            setProtectSize(protectSize)
            setTextFlowMethod(textFlowMethod)
            setTextHorzArrange(textHorzArrange)
            setObjectNumberSort(objectNumberSort)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPGsoHeaderProperty] 생성된 객체 반환
         */
        fun build(value: Long = 0) : HWPGsoHeaderProperty = HWPGsoHeaderProperty().apply {
            this.value = value
        }
    }
}