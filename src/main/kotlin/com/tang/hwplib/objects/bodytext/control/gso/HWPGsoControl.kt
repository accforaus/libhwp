package com.tang.hwplib.objects.bodytext.control.gso

import com.tang.hwplib.objects.bodytext.control.HWPControl
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderGso
import com.tang.hwplib.objects.bodytext.control.gso.caption.HWPCaption
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.*
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPTextBox
import com.tang.hwplib.objects.etc.*

/**
 * GSO 컨트롤을 나타내는 객체
 * @see [HWPControl]
 *
 * @author accforaus
 *
 * @constructor [HWPCtrlHeaderGso]를 헤더로 설정한다
 *
 * @property [caption] [HWPCaption], 캡션 정보
 * @property [shapeComponent] [HWPShapeComponent], 개체 공통 요소
 */
open class HWPGsoControl: HWPControl {
    var caption: HWPCaption? = null
    var shapeComponent: HWPShapeComponent = HWPShapeComponentNormal()

    constructor() : this(HWPCtrlHeaderGso())
    constructor(header: HWPCtrlHeaderGso?) : super(header)

    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderGso] 헤더 반환
     */
    fun getHeader(): HWPCtrlHeaderGso? = if (header != null) header as HWPCtrlHeaderGso else null

    /**
     * Gso 개체의 ID를 반환하는 함수
     *
     * @return [Long] Gso ID값 반환
     */
    fun getGsoId(): Long? = shapeComponent.gsoId

    /**
     * Gso 개체의 ID를 설정하는 함수
     *
     * @param [gsoId] 설정할 Gso ID값
     */
    fun setGsoId(gsoId: Long) {
        shapeComponent.gsoId = gsoId
    }

    /**
     * Gso 컨트롤 유형을 반환하는 함수
     *
     * @return [HWPGsoControlType] Gso 컨트롤 유형 반환
     */
    fun getGsoType(): HWPGsoControlType = HWPGsoControlType.idOf(getGsoId()!!)

    /**
     * 캡션을 생성하는 함수
     */
    fun createCaption() {
        caption = HWPCaption()
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPGsoControl] 복사된 객체 반환
     */
    override fun copy(): HWPGsoControl = HWPGsoControl().also {
        it.setCtrlData(super.copy().getCtrlData())
        this.caption?.run { it.caption = this.copy() }
    }
}
/**
 * 호 개체
 * Tag ID: HWPTAG_SHAPE_COMPONENT_ARC [SHAPE_COMPONENT_ARC]
 * 가변 길이
 * @see [HWPGsoControl]
 *
 * @author accforaus
 *
 * @constructor [HWPCtrlHeaderGso]를 헤더로 설정한다.
 *
 * @property [textBox] [HWPTextBox], 그리기 객체 글상자용 텍스트 정보
 * @property [shapeComponentArc] [HWPShapeComponentArc], 호 개체 속성 (BYTE stream)
 */
class HWPControlArc: HWPGsoControl {
    var textBox: HWPTextBox? = null
    var shapeComponentArc: HWPShapeComponentArc = HWPShapeComponentArc()

    constructor() : this(HWPCtrlHeaderGso())
    constructor(header: HWPCtrlHeaderGso?) : super(header) {
        setGsoId(HWPGsoControlType.Arc.id)
    }

    /**
     * 그리기 객체 글상자용 텍스트 정보를 생성하는 함수
     */
    fun createTextBox() { textBox = HWPTextBox() }

    /**
     * 그리기 객체 글상자용 텍스트 정보를 제거하는 함수
     */
    fun deleteTextBox() { textBox = null }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlArc] 복사된 객체 반환
     */
    override fun copy(): HWPControlArc = HWPControlArc(HWPCtrlHeaderGso()).also {
        super.copy().run {
            it.caption = this.caption
            it.setCtrlData(this.getCtrlData())
        }
        it.header = this.getHeader()?.copy()
        this.textBox?.run { it.textBox = this.copy() }
        if (it.shapeComponent is HWPShapeComponentNormal)
            it.shapeComponent = (this.shapeComponent as HWPShapeComponentNormal).copy()
        it.shapeComponentArc = this.shapeComponentArc.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlArc] 생성된 객체 반환
         */
        fun build(textBox: HWPTextBox? = null,
                  shapeComponent: HWPShapeComponentNormal = HWPShapeComponentNormal.build(),
                  shapeComponentArc: HWPShapeComponentArc = HWPShapeComponentArc.build())
                : HWPControlArc = HWPControlArc(HWPCtrlHeaderGso()).apply {
            this.textBox = textBox
            this.shapeComponent = shapeComponent
            this.shapeComponentArc = shapeComponentArc
        }
    }
}

/**
 * 묶음 개체를 나타내는 객체
 * Tag ID: HWPTAG_SHAPE_COMPONENT_CONTAINER [SHAPE_COMPONENT_CONTAINER]
 * 가변 길이
 * @see [HWPGsoControl]
 *
 * @author accforaus
 *
 * @constructor [HWPCtrlHeaderGso]를 헤더로 설정한다.
 *
 * @property [childControlList] [ArrayList], 개체 솎성 x 묶음 개체의 개수 (BYTE stream)
 */
class HWPControlContainer: HWPGsoControl {
    var childControlList: ArrayList<HWPGsoControl> = ArrayList()

    constructor() : this(HWPCtrlHeaderGso())
    constructor(header: HWPCtrlHeaderGso?) : super(header) {
        shapeComponent = HWPShapeComponentContainer()
        setGsoId(HWPGsoControlType.Container.id)
    }

    /**
     * 묶음 개체를 추가하는 함수
     *
     * @param [childControl] [HWPGsoControl], 추가할 Gso 컨트롤 객체
    */
    fun addChildControl(childControl: HWPGsoControl) {
        childControlList.add(childControl)
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlContainer] 복사된 객체 반환
     */
    override fun copy(): HWPControlContainer = HWPControlContainer(HWPCtrlHeaderGso()).also {
        super.copy().run {
            it.caption = this.caption
            it.setCtrlData(this.getCtrlData())
        }
        it.header = this.getHeader()?.copy()
        if (it.shapeComponent is HWPShapeComponentContainer)
            it.shapeComponent = (this.shapeComponent as HWPShapeComponentContainer).copy()
        for (childControl in this.childControlList)
            it.childControlList.add(childControl.copy())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlContainer] 생성된 객체 반환
         */
        fun build(childControlGenerator: () -> ArrayList<HWPGsoControl> = {ArrayList()},
                  shapeComponent: HWPShapeComponentContainer = HWPShapeComponentContainer.build())
                : HWPControlContainer = HWPControlContainer(HWPCtrlHeaderGso()).apply {
            this.shapeComponent = shapeComponent
            this.childControlList = childControlGenerator()
        }
    }
}

/**
 * 곡선 개체
 * Tag ID: HWPTAG_SHAPE_COMPONENT_CURVE [SHAPE_COMPONENT_CURVE]
 * 가변 길이
 * @see [HWPGsoControl]
 *
 * @author accforaus
 *
 * @constructor [HWPCtrlHeaderGso]를 헤더로 설정한다.
 *
 * @property [textBox] [HWPTextBox], 그리기 객체 글상자용 텍스트 정보
 * @property [shapeComponentCurve] [HWPShapeComponentCurve], 곡선 개체 공통 속성
 */
class HWPControlCurve: HWPGsoControl {
    var textBox: HWPTextBox? = null
    var shapeComponentCurve: HWPShapeComponentCurve = HWPShapeComponentCurve()

    constructor() : this(HWPCtrlHeaderGso())
    constructor(header: HWPCtrlHeaderGso?) : super(header) {
        setGsoId(HWPGsoControlType.Curve.id)
    }

    /**
     * 그리기 객체 글상자용 텍스트 정보를 생성하는 함수
     */
    fun createTextBox() { textBox = HWPTextBox() }

    /**
     * 그리기 객체 글상자용 텍스트 정보를 제거하는 함수
     */
    fun deleteTextBox() { textBox = null }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlCurve] 복사된 객체 반환
     */
    override fun copy(): HWPControlCurve = HWPControlCurve(HWPCtrlHeaderGso()).also {
        super.copy().run {
            it.caption = this.caption
            it.setCtrlData(this.getCtrlData())
        }
        it.header = this.getHeader()?.copy()
        if (it.shapeComponent is HWPShapeComponentNormal)
            it.shapeComponent = (this.shapeComponent as HWPShapeComponentNormal).copy()
        this.textBox?.run { it.textBox = this.copy() }
        it.shapeComponentCurve = this.shapeComponentCurve.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlCurve] 생성된 객체 반환
         */
        fun build(textBox: HWPTextBox? = null,
                  shapeComponent: HWPShapeComponentNormal = HWPShapeComponentNormal.build(),
                  shapeComponentCurve: HWPShapeComponentCurve = HWPShapeComponentCurve.build())
                : HWPControlCurve = HWPControlCurve(HWPCtrlHeaderGso()).apply {
            this.textBox = textBox
            this.shapeComponent = shapeComponent
            this.shapeComponentCurve = shapeComponentCurve
        }
    }
}

/**
 * 타원 객체
 * Tag ID: HWPTAG_SHAPE_COMPONENT_ELLIPSE [SHAPE_COMPONENT_ELLIPSE]
 * 가변 길이
 * @see [HWPGsoControl]
 *
 * @author accforaus
 *
 * @constructor [HWPCtrlHeaderGso]를 헤더로 설정한다.
 *
 * @property [textBox] [HWPTextBox], 그리기 객체 글상자용 텍스트 정보
 * @property [shapeComponentEllipse] [HWPShapeComponentRectangle], 타원 개체 속성 (BYTE stream - unsigned 60 bytes)
 */
class HWPControlEllipse: HWPGsoControl {
    var textBox: HWPTextBox? = null
    var shapeComponentEllipse: HWPShapeComponentEllipse = HWPShapeComponentEllipse()

    constructor() : this(HWPCtrlHeaderGso())
    constructor(header: HWPCtrlHeaderGso?) : super(header) {
        setGsoId(HWPGsoControlType.Ellipse.id)
    }

    /**
     * 그리기 객체 글상자용 텍스트 정보를 생성하는 함수
     */
    fun createTextBox() { textBox = HWPTextBox() }

    /**
     * 그리기 객체 글상자용 텍스트 정보를 제거하는 함수
     */
    fun deleteTextBox() { textBox = null }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlEllipse] 복사된 객체 반환
     */
    override fun copy(): HWPControlEllipse = HWPControlEllipse(HWPCtrlHeaderGso()).also {
        super.copy().run {
            it.caption = this.caption
            it.setCtrlData(this.getCtrlData())
        }
        it.header = this.getHeader()?.copy()
        if (it.shapeComponent is HWPShapeComponentNormal)
            it.shapeComponent = (this.shapeComponent as HWPShapeComponentNormal).copy()
        this.textBox?.run { it.textBox = this.copy() }
        it.shapeComponentEllipse = this.shapeComponentEllipse.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlEllipse] 생성된 객체 반환
         */
        fun build(textBox: HWPTextBox? = null,
                  shapeComponent: HWPShapeComponentNormal = HWPShapeComponentNormal.build(),
                  shapeComponentEllipse: HWPShapeComponentEllipse = HWPShapeComponentEllipse.build())
                : HWPControlEllipse = HWPControlEllipse(HWPCtrlHeaderGso()).apply {
            this.textBox = textBox
            this.shapeComponent = shapeComponent
            this.shapeComponentEllipse = shapeComponentEllipse
        }
    }
}

/**
 * 선 개체
 * Tag ID: HWPTAG_SHAPE_COMPONENT_LINE [SHAPE_COMPONENT_LINE]
 * 18 bytes
 *
 * @author accforaus
 *
 * @constructor [HWPCtrlHeaderGso]를 헤더로 설정한다
 *
 * @property [shapeComponentLine] [HWPShapeComponentLine], 선 개체 속성 (BYTE stream - unsigned 18 bytes)
 */
class HWPControlLine: HWPGsoControl {
    var shapeComponentLine: HWPShapeComponentLine = HWPShapeComponentLine()

    constructor() : this(HWPCtrlHeaderGso())
    constructor(header: HWPCtrlHeaderGso?) : super(header) {
        setGsoId(HWPGsoControlType.Line.id)
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlLine] 복사된 객체 반환
     */
    override fun copy(): HWPControlLine = HWPControlLine(HWPCtrlHeaderGso()).also {
        super.copy().run {
            it.caption = this.caption
            it.setCtrlData(this.getCtrlData())
        }
        it.header = this.getHeader()?.copy()
        if (it.shapeComponent is HWPShapeComponentNormal)
            it.shapeComponent = (this.shapeComponent as HWPShapeComponentNormal).copy()
        it.shapeComponentLine = this.shapeComponentLine.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlLine] 생성된 객체 반환
         */
        fun build(shapeComponent: HWPShapeComponentNormal = HWPShapeComponentNormal.build(),
                  shapeComponentLine: HWPShapeComponentLine = HWPShapeComponentLine.build())
                : HWPControlLine = HWPControlLine(HWPCtrlHeaderGso()).apply {
            this.shapeComponent = shapeComponent
            this.shapeComponentLine = shapeComponentLine
        }
    }
}

/**
 * 개체 연결선 개체를 나타내는 객체
 * Tag ID: HWPTAG_SHAPE_COMPONENT_LINE [SHAPE_COMPONENT_LINE]
 * @see [HWPGsoControl]
 *
 * @author accforaus
 *
 * @constructor [HWPCtrlHeaderGso]를 헤더로 설정한다.
 *
 * @property [shapeComponentObjectLinkLine] [HWPShapeComponentLineForObjectLinkLine], 개체 연결선 속성 (BYTE stream)
 */
class HWPControlObjectLinkLine: HWPGsoControl {
    var shapeComponentObjectLinkLine: HWPShapeComponentLineForObjectLinkLine = HWPShapeComponentLineForObjectLinkLine()

    constructor() : this(HWPCtrlHeaderGso())
    constructor(header: HWPCtrlHeaderGso?) : super(header) {
        setGsoId(HWPGsoControlType.ObjectLinkLine.id)
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlObjectLinkLine] 복사된 객체 반환
     */
    override fun copy(): HWPControlObjectLinkLine = HWPControlObjectLinkLine(HWPCtrlHeaderGso()).also {
        super.copy().run {
            it.caption = this.caption
            it.setCtrlData(this.getCtrlData())
        }
        it.header = this.getHeader()?.copy()
        if (it.shapeComponent is HWPShapeComponentNormal)
            it.shapeComponent = (this.shapeComponent as HWPShapeComponentNormal).copy()
        it.shapeComponentObjectLinkLine = this.shapeComponentObjectLinkLine
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlObjectLinkLine] 생성된 객체 반환
         */
        fun build(shapeComponent: HWPShapeComponentNormal = HWPShapeComponentNormal.build(),
                  shapeComponentObjectLinkLine: HWPShapeComponentLineForObjectLinkLine = HWPShapeComponentLineForObjectLinkLine.build())
                : HWPControlObjectLinkLine = HWPControlObjectLinkLine(HWPCtrlHeaderGso()).apply {
            this.shapeComponent = shapeComponent
            this.shapeComponentObjectLinkLine = shapeComponentObjectLinkLine
        }
    }
}

/**
 * OLE 개체를 나타내는 객체
 * Tag ID: HWPTAG_SHAPE_COMPONENT_OLE [SHAPE_COMPONENT_OLE]
 * 가변 길이
 * @see [HWPGsoControl]
 *
 * @author accforaus
 *
 * @constructor [HWPCtrlHeaderGso]를 헤더로 설정한다.
 *
 * @property [shapeComponentOLE] [HWPShapeComponentOLE], OLE 개체 속성 (BYTE stream)
 */
class HWPControlOLE: HWPGsoControl {
    var shapeComponentOLE: HWPShapeComponentOLE = HWPShapeComponentOLE()

    constructor() : this(HWPCtrlHeaderGso())
    constructor(header: HWPCtrlHeaderGso?) : super(header) {
        setGsoId(HWPGsoControlType.OLE.id)
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlOLE] 복사된 객체 반환
     */
    override fun copy(): HWPControlOLE = HWPControlOLE(HWPCtrlHeaderGso()).also {
        super.copy().run {
            it.caption = this.caption
            it.setCtrlData(this.getCtrlData())
        }
        it.header = this.getHeader()?.copy()
        if (it.shapeComponent is HWPShapeComponentNormal)
            it.shapeComponent = (this.shapeComponent as HWPShapeComponentNormal).copy()
        it.shapeComponentOLE = this.shapeComponentOLE.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlOLE] 생성된 객체 반환
         */
        fun build(shapeComponent: HWPShapeComponentNormal = HWPShapeComponentNormal.build(),
                  shapeComponentOLE: HWPShapeComponentOLE = HWPShapeComponentOLE.build())
                : HWPControlOLE = HWPControlOLE(HWPCtrlHeaderGso()).apply {
            this.shapeComponent = shapeComponent
            this.shapeComponentOLE = shapeComponentOLE
        }
    }
}

/**
 * 그림 개체를 나타내는 객체
 * Tag ID: HWPTAG_SHAPE_COMPONENT_PICTURE [SHAPE_COMPONENT_PICTURE]
 * 가변 길이
 * @see [HWPGsoControl]
 *
 * @author accforaus
 *
 * @constructor [HWPCtrlHeaderGso]를 헤더로 설정한다.
 *
 * @property [shapeComponentPicture] [HWPShapeComponentPicture], 그림 개체 속성 (BYTE stream)
 */
class HWPControlPicture: HWPGsoControl {
    var shapeComponentPicture: HWPShapeComponentPicture = HWPShapeComponentPicture()

    constructor() : this(HWPCtrlHeaderGso())
    constructor(header: HWPCtrlHeaderGso?) : super(header) {
        setGsoId(HWPGsoControlType.Picture.id)
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlPicture] 복사된 객체 반환
     */
    override fun copy(): HWPControlPicture = HWPControlPicture(HWPCtrlHeaderGso()).also {
        super.copy().run {
            it.caption = this.caption
            it.setCtrlData(this.getCtrlData())
        }
        it.header = this.getHeader()?.copy()
        if (it.shapeComponent is HWPShapeComponentNormal)
            it.shapeComponent = (this.shapeComponent as HWPShapeComponentNormal).copy()
        it.shapeComponentPicture = this.shapeComponentPicture.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlPicture] 생성된 객체 반환
         */
        fun build(shapeComponent: HWPShapeComponentNormal = HWPShapeComponentNormal.build(),
                  shapeComponentPicture: HWPShapeComponentPicture = HWPShapeComponentPicture.build())
                : HWPControlPicture = HWPControlPicture(HWPCtrlHeaderGso()).apply {
            this.shapeComponent = shapeComponent
            this.shapeComponentPicture = shapeComponentPicture
        }
    }
}

/**
 * 다각형 개체
 * Tag ID: HWPTAG_SHAPE_COMPONENT_POLYGON [SHAPE_COMPONENT_POLYGON]
 * 가변 길이
 * @see [HWPGsoControl]
 *
 * @author accforaus
 *
 * @constructor [HWPCtrlHeaderGso]를 헤더로 설정한다.
 *
 * @property [textBox] [HWPTextBox], 그리기 객체 글상자용 텍스트 정보
 * @property [shapeComponentPolygon] [HWPShapeComponentPolygon], 다각형 개체 속성 (BYTE stream)
 */
class HWPControlPolygon: HWPGsoControl {
    var textBox: HWPTextBox? = null
    var shapeComponentPolygon: HWPShapeComponentPolygon = HWPShapeComponentPolygon()

    constructor() : this(HWPCtrlHeaderGso())
    constructor(header: HWPCtrlHeaderGso?) : super(header) {
        setGsoId(HWPGsoControlType.Polygon.id)
    }

    /**
     * 그리기 객체 글상자용 텍스트 정보를 생성하는 함수
     */
    fun createTextBox() { textBox = HWPTextBox() }

    /**
     * 그리기 객체 글상자용 텍스트 정보를 제거하는 함수
     */
    fun deleteTextBox() { textBox = null }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlPolygon] 복사된 객체 반환
     */
    override fun copy(): HWPControlPolygon = HWPControlPolygon(HWPCtrlHeaderGso()).also {
        super.copy().run {
            it.caption = this.caption
            it.setCtrlData(this.getCtrlData())
        }
        it.header = this.getHeader()?.copy()
        if (it.shapeComponent is HWPShapeComponentNormal)
            it.shapeComponent = (this.shapeComponent as HWPShapeComponentNormal).copy()
        this.textBox?.run { it.textBox = this.copy() }
        it.shapeComponentPolygon = this.shapeComponentPolygon.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlPolygon] 생성된 객체 반환
         */
        fun build(textBox: HWPTextBox? = null,
                  shapeComponent: HWPShapeComponentNormal = HWPShapeComponentNormal.build(),
                  shapeComponentPolygon: HWPShapeComponentPolygon = HWPShapeComponentPolygon.build())
                : HWPControlPolygon = HWPControlPolygon(HWPCtrlHeaderGso()).apply {
            this.textBox = textBox
            this.shapeComponent = shapeComponent
            this.shapeComponentPolygon = shapeComponentPolygon
        }
    }
}

/**
 * 사각형 개체
 * Tag ID: HWPTAG_SHAPE_COMPONENT_RECTANGLE [SHAPE_COMPONENT_RECTANGLE]
 * 33 bytes
 * @see [HWPGsoControl]
 *
 * @author accforaus
 *
 * @constructor [HWPCtrlHeaderGso]를 헤더로 설정한다
 *
 * @property [textBox] [HWPTextBox], 그리기 객체 글상자용 텍스트 정보
 * @property [shapeComponentRectangle] [HWPShapeComponentRectangle], 사각형 개체 속성 (BYTE stream - unsigned 33 bytes)
 */
class HWPControlRectangle: HWPGsoControl {
    var textBox: HWPTextBox? = null
    var shapeComponentRectangle: HWPShapeComponentRectangle = HWPShapeComponentRectangle()

    constructor() : this(HWPCtrlHeaderGso())
    constructor(header: HWPCtrlHeaderGso?) : super(header) {
        setGsoId(HWPGsoControlType.Rectangle.id)
    }

    /**
     * 그리기 객체 글상자용 텍스트 정보를 생성하는 함수
     */
    fun createTextBox() { textBox = HWPTextBox() }

    /**
     * 그리기 객체 글상자용 텍스트 정보를 제거하는 함수
     */
    fun deleteTextBox() { textBox = null }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlRectangle] 복사된 객체 반환
     */
    override fun copy(): HWPControlRectangle =  HWPControlRectangle(HWPCtrlHeaderGso()).also {
        super.copy().run {
            it.caption = this.caption
            it.setCtrlData(this.getCtrlData())
        }
        it.header = this.getHeader()?.copy()
        if (it.shapeComponent is HWPShapeComponentNormal)
            it.shapeComponent = (this.shapeComponent as HWPShapeComponentNormal).copy()
        this.textBox?.run { it.textBox = this.copy() }
        it.shapeComponentRectangle = this.shapeComponentRectangle.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlRectangle] 생성된 객체 반환
         */
        fun build(textBox: HWPTextBox? = null,
                  shapeComponent: HWPShapeComponentNormal = HWPShapeComponentNormal.build(),
                  shapeComponentRectangle: HWPShapeComponentRectangle = HWPShapeComponentRectangle.build())
                : HWPControlRectangle = HWPControlRectangle(HWPCtrlHeaderGso()).apply {
            this.textBox = textBox
            this.shapeComponent = shapeComponent
            this.shapeComponentRectangle = shapeComponentRectangle
        }
    }
}

/**
 * 동영상 개체를 나타내는 객체
 * Tag ID: [VIDEO_DATA]
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [shapeComponentVideo] [HWPShapeComponentVideo], 동영상 개체 속성
 */
class HWPControlVideo: HWPGsoControl {
    var shapeComponentVideo : HWPShapeComponentVideo = HWPShapeComponentVideo()

    constructor() : this(HWPCtrlHeaderGso())
    constructor(header: HWPCtrlHeaderGso?) : super(header) {
        setGsoId(HWPGsoControlType.Video.id)
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlVideo] 복사된 객체 반환
     */
    override fun copy(): HWPControlVideo = HWPControlVideo(HWPCtrlHeaderGso()).also {
        super.copy().run {
            it.caption = this.caption
            it.setCtrlData(this.getCtrlData())
        }
        it.header = this.getHeader()?.copy()
        if (it.shapeComponent is HWPShapeComponentNormal)
            it.shapeComponent = (this.shapeComponent as HWPShapeComponentNormal).copy()
        it.shapeComponentVideo = this.shapeComponentVideo.copy()
    }
}
/**
 * Gso 컨트롤을 생성하는 함수
 *
 * @author accforaus
 *
 * @param [gsoId] [Long], 추가할 Gso ID
 * @param [header] [HWPCtrlHeaderGso], 추가할 Gso 컨트롤 헤더
 *
 * @return [HWPGsoControl] 생성된 Gso 컨트롤 (존재하지 않으면 null)
 */
fun createHWPGSOControl(gsoId: Long, header: HWPCtrlHeaderGso?) : HWPGsoControl? = when(gsoId) {
    HWPGsoControlType.Line.id -> HWPControlLine(header)
    HWPGsoControlType.Rectangle.id -> HWPControlRectangle(header)
    HWPGsoControlType.Ellipse.id -> HWPControlEllipse(header)
    HWPGsoControlType.Polygon.id -> HWPControlPolygon(header)
    HWPGsoControlType.Arc.id -> HWPControlArc(header)
    HWPGsoControlType.Curve.id -> HWPControlCurve(header)
    HWPGsoControlType.Picture.id -> HWPControlPicture(header)
    HWPGsoControlType.OLE.id -> HWPControlOLE(header)
    HWPGsoControlType.Container.id -> HWPControlContainer(header)
    HWPGsoControlType.ObjectLinkLine.id -> HWPControlObjectLinkLine(header)
    else -> null
}

/**
 * 개체 컨트롤을 복사하는 함수
 *
 * @param [gsoControl] [HWPGsoControl] 개체 컨트롤 객체
 * @return [HWPGsoControl] 복사된 객체 반환 (존재하지 않으면 NULL)
 */
fun copyGsoControl(gsoControl: HWPGsoControl): HWPGsoControl? = when(gsoControl) {
    is HWPControlLine -> gsoControl.copy()
    is HWPControlRectangle -> gsoControl.copy()
    is HWPControlEllipse -> gsoControl.copy()
    is HWPControlArc -> gsoControl.copy()
    is HWPControlPolygon -> gsoControl.copy()
    is HWPControlCurve -> gsoControl.copy()
    is HWPControlPicture -> gsoControl.copy()
    is HWPControlOLE -> gsoControl.copy()
    is HWPControlContainer -> gsoControl.copy()
    is HWPControlObjectLinkLine -> gsoControl.copy()
    is HWPControlVideo -> gsoControl.copy()
    else -> null
}