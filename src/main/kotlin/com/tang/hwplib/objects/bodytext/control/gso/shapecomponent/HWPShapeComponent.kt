package com.tang.hwplib.objects.bodytext.control.gso.shapecomponent

import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControl
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.line.HWPLineInfo
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.render.HWPRenderingInfo
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.render.HWPScaleRotateMatrixPair
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.shadow.HWPShadowInfo
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPFillInfo
import com.tang.hwplib.objects.etc.SHAPE_COMPONENT

/**
 * 개체 요소를 나타내는 객체
 * tag ID: HWPTAG_SHAPE_COMPONENT [SHAPE_COMPONENT]
 * GenShapeObject일 경우 id가 두 번 기롬됨
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [gsoId] [Long], 개체 컨트롤 ID (UINT32 - unsigned 4 bytes)
 * @property [offsetX] [Int], 개체가 속한 그룹 내에서의 X offset (INT32 - signed 4 bytes)
 * @property [offsetY] [Int], 개체가 속한 그룹 내에서의 Y offset (INT32 - signed 4 bytes)
 * @property [groupingCount] [Int], 몇 번이나 그룹 되았는지 (WORD - unsigned 2 bytes)
 * @property [localFileVersion] [Int], 개체 요소의 local file version (WORD - unsigned 2 bytes)
 * @property [widthAtCreate] [Long], 개체 생성 시 초기 폭 (UINT32 - unsigned 4 bytes)
 * @property [heightAtCreate] [Long], 개체 생성 시 초기 높이 (UINT32 - unsigned 4 bytes)
 * @property [widthAtCurrent] [Long], 개체의 현재 폭 (UINT32 - unsigned 4 bytes)
 * @property [heightAtCurrent] [Long], 개체의 현재 높이 (UINT32 - unsigned 4 bytes)
 * @property [property] [Long], 속성 (0: horz flip/1: vert flip) (UINT32 - unsigned 4 bytes)
 * @property [rotateAngle] [Int], 회전 각 (HWPUNIT16 - unsigned 2 bytes)
 * @property [rotateXCenter] [Int], 회전 중심의 x 좌표 (개체 좌표계) (INT32 - signed 4 bytes)
 * @property [rotateYCenter] [Int], 회전 중심의 y 좌표 (개체 좌표계) (INT32 - signed 4 bytes)
 * @property [renderingInfo] [HWPRenderingInfo], Redering 정보 (n bytes)
 */
open class HWPShapeComponent {
    var gsoId: Long? = 0
    var offsetX: Int = 0
    var offsetY: Int = 0
    var groupingCount: Int = 0
    var localFileVersion: Int = 0
    var widthAtCreate: Long = 0
    var heightAtCreate: Long = 0
    var widthAtCurrent: Long = 0
    var heightAtCurrent: Long = 0
    var property: Long = 0
    var rotateAngle: Int = 0
    var rotateXCenter: Int = 0
    var rotateYCenter: Int = 0
    var renderingInfo: HWPRenderingInfo = HWPRenderingInfo()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShapeComponent] 복사된 객체 반환
     */
    open fun copy() : HWPShapeComponent = HWPShapeComponent().also {
        it.gsoId = this.gsoId
        it.offsetX = this.offsetX
        it.offsetY = this.offsetY
        it.groupingCount = this.groupingCount
        it.localFileVersion = this.localFileVersion
        it.widthAtCreate = this.widthAtCreate
        it.heightAtCreate = this.heightAtCreate
        it.widthAtCurrent = this.widthAtCurrent
        it.heightAtCurrent = this.heightAtCurrent
        it.property = this.property
        it.rotateAngle = this.rotateAngle
        it.rotateXCenter = this.rotateXCenter
        it.rotateYCenter = this.rotateYCenter
        it.renderingInfo = this.renderingInfo.copy()
    }

    /**
     * normal matrix로 설정하는 함수
     */
    fun setMatrixsNormal() {
        renderingInfo.translationMatrix.setValue(0, 1.0f.toDouble())
        renderingInfo.translationMatrix.setValue(1, 0.0f.toDouble())
        renderingInfo.translationMatrix.setValue(2, 0.0f.toDouble())
        renderingInfo.translationMatrix.setValue(3, 0.0f.toDouble())
        renderingInfo.translationMatrix.setValue(4, 1.0f.toDouble())
        renderingInfo.translationMatrix.setValue(5, 0.0f.toDouble())

        val pair: HWPScaleRotateMatrixPair = renderingInfo.addNewScaleRotateMatrixPair()
        pair.scaleMatrix.setValue(0, 1.0f.toDouble())
        pair.scaleMatrix.setValue(1, 0.0f.toDouble())
        pair.scaleMatrix.setValue(2, 0.0f.toDouble())
        pair.scaleMatrix.setValue(3, 0.0f.toDouble())
        pair.scaleMatrix.setValue(4, 1.0f.toDouble())
        pair.scaleMatrix.setValue(5, 0.0f.toDouble())

        pair.rotateMatrix.setValue(0, 1.0f.toDouble())
        pair.rotateMatrix.setValue(1, 0.0f.toDouble())
        pair.rotateMatrix.setValue(2, 0.0f.toDouble())
        pair.rotateMatrix.setValue(3, 0.0f.toDouble())
        pair.rotateMatrix.setValue(4, 1.0f.toDouble())
        pair.rotateMatrix.setValue(5, 0.0f.toDouble())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPShapeComponent] 생성된 객체 반환
         */
        fun build(gsoId: Long? = null, offsetX: Int = 0, offsetY: Int = 0,
                  groupingCount: Int = 0, localFileVersion: Int = 0, widthAtCreate: Long = 0,
                  heightAtCreate: Long = 0, widthAtCurrent: Long = 0, heightAtCurrent: Long = 0,
                  property: Long = 0, rotateAngle: Int = 0, rotateXCenter: Int = 0,
                  rotateYCenter: Int = 0, renderingInfo: HWPRenderingInfo = HWPRenderingInfo.build())
                : HWPShapeComponent = HWPShapeComponent().apply {
            this.gsoId = gsoId
            this.offsetX = offsetX
            this.offsetY = offsetY
            this.groupingCount = groupingCount
            this.localFileVersion = localFileVersion
            this.widthAtCreate = widthAtCreate
            this.heightAtCreate = heightAtCreate
            this.widthAtCurrent = widthAtCurrent
            this.heightAtCurrent = heightAtCurrent
            this.property = property
            this.rotateAngle = rotateAngle
            this.rotateXCenter = rotateXCenter
            this.rotateYCenter = rotateYCenter
            this.renderingInfo = renderingInfo
        }
    }
}

/**
 * 그리기 개체 공통 속성을 나타내는 객체
 * Tag ID: HWPTAG_SHAPE_COMPONENT [HWPShapeComponent]
 * GenShapeObject 일 경우 id가 두 번 기록됨
 * 선, 사각형, 타원, 호, 다각형, 곡선
 * 모든 그리기 개체에 대한 serialization은 우선 base인 그리기 개체 공통 속성을 serialize한 다음
 * 자신이 가지고 있는 개체 요소 속성을 seralize한다.
 * 가변 길이
 *
 * @see [HWPShapeComponent]
 *
 * @author accforaus
 *
 * @property [lineInfo] [HWPLineInfo], 테두리 선 정보 (BYTE stream - unsigned 11 bytes)
 * @property [fillInfo] [HWPFillInfo], 채우기 정보 (BYTE stream - unsigned n bytes)
 * @property [shadowInfo] [HWPShadowInfo], 그림자 정보 (BYTE stream - unsigned n bytes)
 */
class HWPShapeComponentNormal: HWPShapeComponent() {
    var lineInfo: HWPLineInfo? = null
    var fillInfo: HWPFillInfo? = null
    var shadowInfo: HWPShadowInfo? = null

    /**
     * 테두리 선 정보를 생성하는 함수
     */
    fun createLineInfo() { lineInfo = HWPLineInfo() }

    /**
     * 테두리 선 정보를 제거하는 함수
     */
    fun deleteLineInfo() { lineInfo = null }

    /**
     * 채우기 정보를 생성하는 함수
     */
    fun createFillInfo() { fillInfo = HWPFillInfo() }

    /**
     * 채우기 정보를 제거하는 함수
     */
    fun deleteFillInfo() { fillInfo = null }

    /**
     * 그림자 정보를 생성하는 함수
     */
    fun createShadowInfo() { shadowInfo = HWPShadowInfo() }

    /**
     * 그림자 정보를 제거하는 함수
     */
    fun deleteShadowInfo() { shadowInfo = null }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShapeComponentNormal] 복사된 객체 반환
     */
    override fun copy(): HWPShapeComponentNormal = HWPShapeComponentNormal().also {
        super.copy().run {
            it.gsoId = this.gsoId
            it.offsetX = this.offsetX
            it.offsetY = this.offsetY
            it.groupingCount = this.groupingCount
            it.localFileVersion = this.localFileVersion
            it.widthAtCreate = this.widthAtCreate
            it.heightAtCreate = this.heightAtCreate
            it.widthAtCurrent = this.widthAtCurrent
            it.heightAtCurrent = this.heightAtCurrent
            it.property = this.property
            it.rotateAngle = this.rotateAngle
            it.rotateXCenter = this.rotateXCenter
            it.rotateYCenter = this.rotateYCenter
            it.renderingInfo = this.renderingInfo.copy()
        }
        this.lineInfo?.run { it.lineInfo = this.copy() }
        this.fillInfo?.run { it.fillInfo = this.copy() }
        this.shadowInfo?.run { it.shadowInfo = this.copy() }
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPShapeComponentNormal] 생성된 객체 반환
         */
        fun build(shapeComponent: HWPShapeComponent = HWPShapeComponent.build(),
                  lineInfo: HWPLineInfo = HWPLineInfo.build(),
                  fillInfo: HWPFillInfo = HWPFillInfo.build(),
                  shadowInfo: HWPShadowInfo = HWPShadowInfo.build())
                : HWPShapeComponentNormal = HWPShapeComponentNormal().apply {
            shapeComponent.let {
                this.gsoId = it.gsoId
                this.offsetX = it.offsetX
                this.offsetY = it.offsetY
                this.groupingCount = it.groupingCount
                this.localFileVersion = it.localFileVersion
                this.widthAtCreate = it.widthAtCreate
                this.heightAtCreate = it.heightAtCreate
                this.widthAtCurrent = it.widthAtCurrent
                this.heightAtCurrent = it.heightAtCurrent
                this.property = it.property
                this.rotateAngle = it.rotateAngle
                this.rotateXCenter = it.rotateXCenter
                this.rotateYCenter = it.rotateYCenter
                this.renderingInfo = it.renderingInfo
            }
            this.lineInfo = lineInfo
            this.fillInfo = fillInfo
            this.shadowInfo = shadowInfo
        }
    }
}

/**
 * 묶음 개체 속성을 나타내는 객체
 * @see [HWPShapeComponent]
 *
 * @author accforaus
 *
 * @property [childControlIdList] [ArrayList], 개체의 컨트롤 ID 리스트 (UINT32 array)
 */
class HWPShapeComponentContainer: HWPShapeComponent() {
    var childControlIdList: ArrayList<Long> = ArrayList()

    /**
     * 개체의 컨트롤 ID를 추가하는 함수
     *
     * @param [id] [Long], 추가할 개체의 컨트롤 ID
     */
    fun addChildControlId(id: Long) { childControlIdList.add(id) }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPShapeComponentContainer] 복사된 객체 반환
     */
    override fun copy(): HWPShapeComponentContainer = HWPShapeComponentContainer().also {
        super.copy().run {
            it.gsoId = this.gsoId
            it.offsetX = this.offsetX
            it.offsetY = this.offsetY
            it.groupingCount = this.groupingCount
            it.localFileVersion = this.localFileVersion
            it.widthAtCreate = this.widthAtCreate
            it.heightAtCreate = this.heightAtCreate
            it.widthAtCurrent = this.widthAtCurrent
            it.heightAtCurrent = this.heightAtCurrent
            it.property = this.property
            it.rotateAngle = this.rotateAngle
            it.rotateXCenter = this.rotateXCenter
            it.rotateYCenter = this.rotateYCenter
            it.renderingInfo = this.renderingInfo.copy()
        }
        for (childControlId in this.childControlIdList) it.childControlIdList.add(childControlId)
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPShapeComponentContainer] 생성된 객체 반환
         */
        fun build(shapeComponent: HWPShapeComponent = HWPShapeComponent.build(),
                  childControlIDGenerator: () -> ArrayList<Long> = {ArrayList()})
                : HWPShapeComponentContainer = HWPShapeComponentContainer().apply {
            shapeComponent.let {
                this.gsoId = it.gsoId
                this.offsetX = it.offsetX
                this.offsetY = it.offsetY
                this.groupingCount = it.groupingCount
                this.localFileVersion = it.localFileVersion
                this.widthAtCreate = it.widthAtCreate
                this.heightAtCreate = it.heightAtCreate
                this.widthAtCurrent = it.widthAtCurrent
                this.heightAtCurrent = it.heightAtCurrent
                this.property = it.property
                this.rotateAngle = it.rotateAngle
                this.rotateXCenter = it.rotateXCenter
                this.rotateYCenter = it.rotateYCenter
                this.renderingInfo = it.renderingInfo
            }
            this.childControlIdList = childControlIDGenerator()
        }
    }
}