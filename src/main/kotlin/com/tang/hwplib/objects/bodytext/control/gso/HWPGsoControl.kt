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
    fun getHeader() : HWPCtrlHeaderGso? = if (header != null) header as HWPCtrlHeaderGso else null

    /**
     * Gso 개체의 ID를 반환하는 함수
     *
     * @return [Long] Gso ID값 반환
     */
    fun getGsoId() : Long? = shapeComponent.gsoId

    /**
     * Gso 개체의 ID를 설정하는 함수
     *
     * @param [gsoId] 설정할 Gso ID값
     */
    fun setGsoId(gsoId: Long) { shapeComponent.gsoId = gsoId}

    /**
     * Gso 컨트롤 유형을 반환하는 함수
     *
     * @return [HWPGsoControlType] Gso 컨트롤 유형 반환
     */
    fun getGsoType() : HWPGsoControlType = HWPGsoControlType.idOf(getGsoId()!!)

    /**
     * 캡션을 생성하는 함수
     */
    fun createCaption() { caption = HWPCaption() }
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