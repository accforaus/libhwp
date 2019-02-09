package com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.picture

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 색상 효과 속성을 나타내는 객체
 * 8 bytes
 *
 * @author accforaus
 *
 * @property [sort] [Int], 생상 효과 종류 (INT32 - signed 4 bytes)
 * @property [value] [Float], 색상 효과 값 (float - signed 4 bytes)
 */
class HWPColorEffect {
    var sort: Int = 0
    var value: Float = 0f

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPColorEffect] 복사된 객체 반환
     */
    fun copy() : HWPColorEffect = HWPColorEffect().also {
        it.sort = this.sort
        it.value = this.value
    }
}

/**
 * 색상 속성을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [type] [Int], 생상 타입 (INT32 - signed 4 bytes)
 * @property [color] [ByteArray], 색상
 * @property [colorEffectList] [ArrayList] 생상 효과 리스트
 */
class HWPColorWithEffect {
    var type: Int = 0
    var color: ByteArray? = null
    var colorEffectList: ArrayList<HWPColorEffect> = ArrayList()

    /**
     * 색상 효과를 추가하고 반환하는 함수
     *
     * @return [HWPColorEffect] 생성된 객체 반환
     */
    fun addNewColorEffect() : HWPColorEffect = HWPColorEffect().apply { colorEffectList.add(this) }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPColorWithEffect] 복사된 객체 반환
     */
    fun copy() : HWPColorWithEffect = HWPColorWithEffect().also {
        it.type = this.type
        it.color = this.color?.copyOf()
        for (colorEffect in this.colorEffectList) it.colorEffectList.add(colorEffect.copy())
    }
}

/**
 * 4방향 안쪽 여백을 나타내는 객체
 * 8 bytes
 *
 * @author accforaus
 *
 * @property [left] [Int], 왼쪽 여백 (unsigned 2 bytes)
 * @property [right] [Int], 오른쪽 여백 (unsigned 2 bytes)
 * @property [top] [Int], 위쪽 여백 (unsigned 2 bytes)
 * @property [bottom] [Int], 아래쪽 여백 (unsigned 2 bytes)
 */
class HWPInnerMargin {
    var left: Int = 0
    var right: Int = 0
    var top: Int = 0
    var bottom: Int = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPInnerMargin] 복사된 객체 반환
     */
    fun copy() : HWPInnerMargin = HWPInnerMargin().also {
        it.left = this.left
        it.right = this.right
        it.top = this.top
        it.bottom = this.bottom
    }
}

/**
 * 네온 효과 속성을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [transparency] [Float], 네온 투명도 (float - signed 4 bytes)
 * @property [radius] [Float], 네온 반경 (float - signed 4 bytes)
 * @property [color] [HWPColorWithEffect], 네온 색상 (BYTE stream)
 */
class HWPNeonEffect {
    var transparency: Float = 0f
    var radius: Float = 0f
    var color: HWPColorWithEffect = HWPColorWithEffect()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPNeonEffect] 복사된 객체 반환
     */
    fun copy() : HWPNeonEffect = HWPNeonEffect().also {
        it.transparency = this.transparency
        it.radius = this.radius
        it.color = this.color.copy()
    }
}

/**
 * 그림 효과 정보를 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 그림 효과 정보 값
 */
class HWPPictureEffectProperty {
    var value: Long = 0

    /**
     * 그림자 효과의 유무를 반환하는 함수
     * bit 0
     *
     * @return [Boolean] 그림자 효과의 유무 반환
     */
    fun hasShadowEffect() : Boolean = get(value, 0)

    /**
     * 그림자 효과의 유무를 설정하는 함수
     * bit 0
     *
     * @param [shadowEffect] [Boolean] 그림자 효과의 유무값
     */
    fun setShadowEffect(shadowEffect: Boolean) {
        value = set(value, 0, shadowEffect)
    }

    /**
     * 네온 효과의 유무를 반환하는 함수
     * bit 1
     *
     * @return [Boolean] 네온 효과의 유무 반환
     */
    fun hasNeonEffect() : Boolean = get(value, 1)

    /**
     * 네온 효과의 유무를 설정하는 함수
     * bit 1
     *
     * @param [neonEffect] [Boolean] 네온 효과의 유무값
     */
    fun setNeonEffect(neonEffect: Boolean) {
        value = set(value, 1, neonEffect)
    }

    /**
     * 부드러운 가장자리 효과의 유무를 반환하는 함수
     * bit 2
     *
     * @return [Boolean] 부드러운 가장자리 효과의 유무 반환
     */
    fun hasSoftBorderEffect() : Boolean = get(value, 2)

    /**
     * 부드러운 가장자리 효과의 유무를 설정하는 함수
     * bit 2
     *
     * @param [softBorderEffect] [Boolean] 부드러운 가장자리 효과의 유무값
     */
    fun setSoftBorderEffect(softBorderEffect: Boolean) {
        value = set(value, 2, softBorderEffect)
    }

    /**
     * 반사 효과의 유무를 반환하는 함수
     * bit 3
     *
     * @return [Boolean] 반사 효과의 유무 반환
     */
    fun hasReflectionEffect() : Boolean = get(value, 3)

    /**
     * 반사 효과의 유무를 설정하는 함수
     * bit 3
     *
     * @param [reflectionEffect] [Boolean] 반사 효과의 유무값
     */
    fun setReflectionEffect(reflectionEffect: Boolean) {
        value = set(value, 3, reflectionEffect)
    }
}

/**
 * 반사 효과 속성을 나타내는 객체
 * 53 bytes
 *
 * @author accforaus
 *
 * @property [style] [Int], 반사 스타일 (INT32 - signed 4 bytes)
 * @property [radius] [Float], 반경 (float - signed 4 bytes)
 * @property [direction] [Float], 방향 (float - signed 4 bytes)
 * @property [distance] [Float], 거리 (float - signed 4 bytes)
 * @property [tiltAngleX] [Float], 기울기 각도(X) (float - signed 4 bytes)
 * @property [tiltAngleY] [Float], 기울기 각도(Y) (float - signed 4 bytes)
 * @property [zoomRateX] [Float], 확대 비율(X) (float - signed 4 bytes)
 * @property [zoomRateY] [Float], 확대 비율(Y) (float - signed 4 bytes)
 * @property [rotationStyle] [Int], 회전 스타일 (INT32 - signed 4 bytes)
 * @property [startTransparency] [Float], 시작 투명도 (float - signed 4 bytes)
 * @property [startPosition] [Float], 시작 위치 (float - signed 4 bytes)
 * @property [endTransparency] [Float], 끝 투명도 (float - signed 4 bytes)
 * @property [endPosition] [Float], 끝 위치 (float - signed 4 bytes)
 * @property [offsetDirection] [Float], 오프셋 방향 (float - signed 4 bytes)
 */
class HWPReflectionEffect {
    var style: Int = 0
    var radius: Float = 0f
    var direction: Float = 0f
    var distance: Float = 0f
    var tiltAngleX: Float = 0f
    var tiltAngleY: Float = 0f
    var zoomRateX: Float = 0f
    var zoomRateY: Float = 0f
    var rotationStyle: Int = 0
    var startTransparency: Float = 0f
    var startPosition: Float = 0f
    var endTransparency: Float = 0f
    var endPosition: Float = 0f
    var offsetDirection: Float = 0f

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPReflectionEffect] 복사된 객체 반환
     */
    fun copy() : HWPReflectionEffect = HWPReflectionEffect().also {
        it.style = this.style
        it.radius = this.radius
        it.direction = this.direction
        it.distance = this.distance
        it.tiltAngleX = this.tiltAngleX
        it.tiltAngleY = this.tiltAngleY
        it.zoomRateX = this.zoomRateX
        it.zoomRateY = this.zoomRateY
        it.rotationStyle = this.rotationStyle
        it.startTransparency = this.startTransparency
        it.startPosition = this.startPosition
        it.endTransparency = this.endTransparency
        it.endPosition = this.endPosition
        it.offsetDirection = this.offsetDirection
    }
}

/**
 * 그림자 효과 속성을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [style] [Int], 그림자 스타일 (INT32 - signed 4 bytes)
 * @property [transparency] [Float], 그림자 투명도 (float - signed 4 bytes)
 * @property [cloudy] [Float], 그림자 흐릿하게 (float - signed 4 bytes)
 * @property [direction] [Float], 방향 (float - signed 4 bytes)
 * @property [distance] [Float], 거리 (float - signed 4 bytes)
 * @property [sort] [Int], 정렬 (INT32 - signed 4 bytes)
 * @property [tiltAngleX] [Float], 기울기 각도(X) (float - signed 4 bytes)
 * @property [tiltAngleY] [Float], 기울기 각도(Y) (float - signed 4 bytes)
 * @property [zoomRateX] [Float], 확대 비율(X) (float - signed 4 bytes)
 * @property [zoomRateY] [Float], 확대 비율(Y) (float - signed 4 bytes)
 * @property [rotateWithShape] [Int], 도형과 함께 그림자 회전 (INT32 - signed 4 bytes)
 * @property [color] [HWPColorWithEffect], 그림자 생상 (BYTE stream)
 */
class HWPShadowEffect {
    var style: Int = 0
    var transparency: Float = 0f
    var cloudy: Float = 0f
    var direction: Float = 0f
    var distance: Float = 0f
    var sort: Int = 0
    var tiltAngleX: Float = 0f
    var tiltAngleY: Float = 0f
    var zoomRateX: Float = 0f
    var zoomRateY: Float = 0f
    var rotateWithShape: Int = 0
    var color: HWPColorWithEffect = HWPColorWithEffect()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShadowEffect] 복사된 객체 반환
     */
    fun copy() : HWPShadowEffect = HWPShadowEffect().also {
        it.style = this.style
        it.transparency = this.transparency
        it.cloudy = this.cloudy
        it.direction = this.direction
        it.distance = this.distance
        it.sort = this.sort
        it.tiltAngleX = this.tiltAngleX
        it.tiltAngleY = this.tiltAngleY
        it.zoomRateX = this.zoomRateX
        it.zoomRateY = this.zoomRateY
        it.rotateWithShape = this.rotateWithShape
        it.color = this.color.copy()
    }
}

/**
 * 부드러운 가장자리 효과 속성을 나타내는 객체
 * 4 bytes
 *
 * @author accforaus
 *
 * @property [radius] [Float], 부드러운 가장자리 반경 (float - signed 4 bytes)
 */
class HWPSoftEdgeEffect {
    var radius: Float = 0f

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPSoftEdgeEffect] 복사된 객체 반환
     */
    fun copy() : HWPSoftEdgeEffect = HWPSoftEdgeEffect().also {
        it.radius = this.radius
    }
}

/**
 * 그림 효과 속성을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [property] [HWPPictureEffectProperty], 그림 효과 정보 (UINT32 - unsigned 4 bytes)
 * @property [shadowEffect] [HWPShadowEffect], 그림자 효과 정보 (BYTE stream)
 * @property [neonEffect] [HWPNeonEffect], 네온 효과 정보 (BYTE stream)
 * @property [softEdgeEffect] [HWPSoftEdgeEffect], 부드러운 가장자리 효과 정보 (BYTE stream)
 * @property [reflectionEffect] [HWPReflectionEffect], 반사 효과 정보 (BYTE stream)
 */
class HWPPictureEffect {
    var property: HWPPictureEffectProperty = HWPPictureEffectProperty()
    var shadowEffect: HWPShadowEffect? = null
    var neonEffect: HWPNeonEffect? = null
    var softEdgeEffect: HWPSoftEdgeEffect? = null
    var reflectionEffect: HWPReflectionEffect? = null

    /**
     * 그림자 효과를 생성하는 함수
     */
    fun createShadowEffect() { shadowEffect = HWPShadowEffect() }

    /**
     * 그림자 효과를 제거하는 함수
     */
    fun deleteShadowEffect() { shadowEffect = null }

    /**
     * 네온 효과를 생성하는 함수
     */
    fun createNeonEffect() { neonEffect = HWPNeonEffect() }

    /**
     * 네온 효과를 제거하는 함수
     */
    fun deleteNeonEffect() { neonEffect = null }

    /**
     * 부드러운 가장자리 효과를 생성하는 함수
     */
    fun createSoftEdgeEffect() { softEdgeEffect = HWPSoftEdgeEffect() }

    /**
     * 부드러운 가장자리 효과를 제거하는 함수
     */
    fun deleteSoftEdgeEffect() { softEdgeEffect = null }

    /**
     * 반사 효과를 생성하는 함수
     */
    fun createReflectionEffect() { reflectionEffect = HWPReflectionEffect()}

    /**
     * 반사 효과를 제거하는 함수
     */
    fun deleteReflectionEffect() { reflectionEffect = null }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPPictureEffect] 복사된 객체 반환
     */
    fun copy() : HWPPictureEffect = HWPPictureEffect().also {
        it.property.value = this.property.value
        this.shadowEffect?.run { it.shadowEffect = this.copy() }
        this.neonEffect?.run { it.neonEffect = this.copy() }
        this.softEdgeEffect?.run { it.softEdgeEffect = this.copy() }
        this.reflectionEffect?.run { it.reflectionEffect = this.copy() }
    }
}