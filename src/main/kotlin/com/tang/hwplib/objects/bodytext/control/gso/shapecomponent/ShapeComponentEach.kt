package com.tang.hwplib.objects.bodytext.control.gso.shapecomponent

import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.arc.HWPArcBorder
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.curve.HWPCurveSegmentType
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.ellipse.HWPShapeComponentEllipseProperty
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.line.HWPLineInfoProperty
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.ole.HWPShapeComponentOLEProperty
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.picture.HWPInnerMargin
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.picture.HWPPictureEffect
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.polygon.HWPPositionXY
import com.tang.hwplib.objects.bodytext.control.gso.video.HWPVideoProperty
import com.tang.hwplib.objects.bodytext.control.gso.video.HWPVideoType
import com.tang.hwplib.objects.docinfo.HWPBinData
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPictureInfo
import com.tang.hwplib.objects.etc.Color4Byte

/**
 * 호 개체 속성을 나타내는 객체
 * 28 bytes
 *
 * @author accforaus
 *
 * @property [arcBorder] [HWPArcBorder], 속성 (UINT32 - unsigned 4 bytes)
 * @property [centerX] [Int], 타원의 중심 좌표 X 값 (INT32 - signed 4 bytes)
 * @property [centerY] [Int], 타원의 중심 좌표 Y 값 (INT32 - signed 4 bytes)
 * @property [axis1X] [Int], 제 1축 X 좌표 값 (INT32 - signed 4 bytes)
 * @property [axis1Y] [Int], 제 1축 Y 좌표 값 (INT32 - signed 4 bytes)
 * @property [axis2X] [Int], 제 2축 X 좌표 값 (INT32 - signed 4 bytes)
 * @property [axis2Y] [Int], 제 2축 Y 좌표 값 (INT32 - signed 4 bytes)
 */
class HWPShapeComponentArc {
    var arcBorder: HWPArcBorder = HWPArcBorder.Arc
    var centerX: Int = 0
    var centerY: Int = 0
    var axis1X: Int = 0
    var axis1Y: Int = 0
    var axis2X: Int = 0
    var axis2Y: Int = 0
}

/**
 * 곡선 개체 속성을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [positionList] [ArrayList], x/y 좌표 값의 리스트
 * @property [segmentTypeList] [ArrayList], segment type 리스트
 */
class HWPShapeComponentCurve {
    var positionList: ArrayList<HWPPositionXY> = ArrayList()
    var segmentTypeList: ArrayList<HWPCurveSegmentType> = ArrayList()

    /**
     * X/Y 좌표를 추가하고 반환하는 함수
     *
     * @return [HWPPositionXY] 생성된 객체 반환
     */
    fun addPosition() : HWPPositionXY = HWPPositionXY().apply { positionList.add(this) }

    /**
     * 새로운 segment type을 추가하는 함수
     *
     * @param [cst] [HWPCurveSegmentType] 추가할 segment type
     */
    fun addCurveSegmentType(cst: HWPCurveSegmentType) {
        segmentTypeList.add(cst)
    }
}

/**
 * 타원 개체 속성을 나타내는 객체
 * 33 bytes
 *
 * @author accforaus
 *
 * @property [property] [HWPShapeComponentEllipseProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [centerX] [Int], 중심 좌표의 X 값 (INT32 - signed 4 bytes)
 * @property [centerY] [Int], 중심 좌표의 Y 값 (INT32 - signed 4 bytes)
 * @property [axis1X] [Int], 제1축 X 좌표 값 (INT32 - signed 4 bytes)
 * @property [axis1Y] [Int], 제1축 Y 좌표 값 (INT32 - signed 4 bytes)
 * @property [axis2X] [Int], 제2축 X 좌표 값 (INT32 - signed 4 bytes)
 * @property [axis2Y] [Int], 제2축 Y 좌표 값 (INT32 - signed 4 bytes)
 * @property [startX] [Int], start pos X (INT32 - signed 4 bytes)
 * @property [startY] [Int], start pos Y (INT32 - signed 4 bytes)
 * @property [endX] [Int], end pos X (INT32 - signed 4 bytes)
 * @property [endY] [Int], end pos Y (INT32 - signed 4 bytes)
 * @property [startX2] [Int], start pos X2 (INT32 - signed 4 bytes)
 * @property [startY2] [Int], start pos Y2 (INT32 - signed 4 bytes)
 * @property [endX2] [Int], end pos X2 (INT32 - signed 4 bytes)
 * @property [endY2] [Int], end pos Y2 (INT32 - signed 4 bytes)
 */
class HWPShapeComponentEllipse {
    var property: HWPShapeComponentEllipseProperty = HWPShapeComponentEllipseProperty()
    var centerX: Int = 0
    var centerY: Int = 0
    var axis1X: Int = 0
    var axis1Y: Int = 0
    var axis2X: Int = 0
    var axis2Y: Int = 0
    var startX: Int = 0
    var startY: Int = 0
    var endX: Int = 0
    var endY: Int = 0
    var startX2: Int = 0
    var startY2: Int = 0
    var endX2: Int = 0
    var endY2: Int = 0
}

/**
 * 선 개체 속성을 나타내는 객체
 * BYTE stream - unsigned 18 bytes
 *
 * @author accforaus
 *
 * @property [startX] [Int], 시작점 X 좌표 (INT32 - signed 4 bytes)
 * @property [startY] [Int], 시작점 Y 좌표 (INT32 - signed 4 bytes)
 * @property [endX] [Int], 끝점 X 좌표 (INT32 - signed 4 bytes)
 * @property [endY] [Int], 끝점 Y 좌표 (INT32 - signed 4 bytes)
 * @property [startedRightOrBottom] [Boolean], 속성. 처음 생성 시 수직 또는 수평선일 때, 선의 방향이 언제나 오른쪽 (위쪽)으로 잡힘으로 인한 현상 때문에 방향을 바르게 잡아주기 위한 플래그 (UINT16 - unsigned 2 bytes)
 */
class HWPShapeComponentLine {
    var startX: Int = 0
    var startY: Int = 0
    var endX: Int = 0
    var endY: Int = 0
    var startedRightOrBottom: Boolean = false
}

/**
 * 연결 선 개체 속성을 나타내는 객체
 * BYTE stream
 *
 * @author accforaus
 *
 * @property [startX] [Int], 시작점 X 좌표 (INT32 - signed 4 bytes)
 * @property [startY] [Int], 시작점 Y 좌표 (INT32 - signed 4 bytes)
 * @property [endX] [Int], 끝점 X 좌표 (INT32 - signed 4 bytes)
 * @property [endY] [Int], 끝점 Y 좌표 (INT32 - signed 4 bytes)
 * @property [unknown] [ByteArray]
 */
class HWPShapeComponentLineForObjectLinkLine {
    var startX: Int = 0
    var startY: Int = 0
    var endX: Int = 0
    var endY: Int = 0
    var unknown: ByteArray? = null
}

/**
 * OLE 개체 속성을 나타내는 객체
 * 24 bytes
 *
 * @author accforaus
 *
 * @property [property] [HWPShapeComponentOLEProperty], 속성 (UINT16 - unsigned 2 bytes)
 * @property [extentWidth] [Int], 오브젝트 자체의 extent x크기 (INT32 - signed 4 bytes)
 * @property [extentHeight] [Int], 오브젝트 자체의 extent y크기 (INT32 - signed 4 bytes)
 * @property [binDataId] [Int], 오브젝트가 사용하는 스토리지의 HWPBinData[HWPBinData] ID (UINT16 - unsigned 2 bytes)
 * @property [borderColor] [Color4Byte], 테두리 색 (COLORREF - unsigned 4 bytes)
 * @property [borderThickness] [Int], 테두리 두께 (INT32 - signed 4 bytes)
 * @property [borderProperty] [HWPLineInfoProperty], 테두리 속성 (UINT32 - unsigned 4 bytes)
 */
class HWPShapeComponentOLE {
    var property: HWPShapeComponentOLEProperty = HWPShapeComponentOLEProperty()
    var extentWidth: Int = 0
    var extentHeight: Int = 0
    var binDataId: Int = 0
    var borderColor: Color4Byte = Color4Byte()
    var borderThickness: Int = 0
    var borderProperty: HWPLineInfoProperty = HWPLineInfoProperty()
}

/**
 * 그림 개체 속성을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [borderColor] [Color4Byte], 테두리 색 (COLORREF - unsigned 4 bytes)
 * @property [borderThickness] [Int], 테두리 두께 (INT32 - signed 4 bytes)
 * @property [borderProperty] [HWPLineInfoProperty], 테두리 속성 (UINT32 - unsigned 4 bytes)
 * @property [leftTop] [HWPPositionXY], 이미지 테두리 사각형의 왼쪽 위 (x, y)좌표 (INT32 - signed 4 bytes)
 * @property [rightTop] [HWPPositionXY], 이미지 테두리 사각형의 오른쪽 위 (x, y)좌표 (INT32 - signed 4 bytes)
 * @property [leftBottom] [HWPPositionXY], 이미지 테두리 사각형의 왼쪽 아래 (x, y)좌표 (INT32 - signed 4 bytes)
 * @property [rightBottom] [HWPPositionXY], 이미지 테두리 사각형의 오른쪽 위 (x, y)좌표 (INT32 - signed 4 bytes)
 * @property [leftAfterCutting] [Int], 자르기 한 후 사각형의 left (INT32 - signed 4 bytes)
 * @property [topAfterCutting] [Int], 자르기 한 후 사각형의 top (INT32 - signed 4 bytes)
 * @property [rightAfterCutting] [Int], 자르기 한 후 사각형의 right (INT32 - signed 4 bytes)
 * @property [bottomAfterCutting] [Int], 자르기 한 후 사각형의 bottom (INT32 - signed 4 bytes)
 * @property [innerMargin] [HWPInnerMargin], 안쪽 여백 정보 (BYTE stream - unsigned 8 bytes)
 * @property [pictureInfo] [HWPPictureInfo], 그림 정보 (BYTE stream)
 * @property [borderTransparency] [Short], 테두리 투명도 (BYTE - unsigned 1 byte)
 * @property [instanceId] [Long], 문서 내 각 개체에 대한 고유 아이디(instance ID) (UINT32 - unsigned 4 bytes)
 * @property [pictureEffect] [HWPPictureEffect], 그림 효과 정보 (BYTE stream)
 * @property [imageWidth] [Long], 그림 최초 생성 시 기준 이미지 폭 (HWPUNIT - unsigned 4 bytes)
 * @property [imageHeight] [Long], 그림 최초 생성 시 기준 이미지 높이 (HWPUNIT - unsigned 4 bytes)
 * @property [imageTransparency] [Byte], 이미지 투명도
 */
class HWPShapeComponentPicture {
    var borderColor: Color4Byte = Color4Byte()
    var borderThickness: Int = 0
    var borderProperty: HWPLineInfoProperty = HWPLineInfoProperty()
    var leftTop: HWPPositionXY = HWPPositionXY()
    var rightTop: HWPPositionXY = HWPPositionXY()
    var leftBottom: HWPPositionXY = HWPPositionXY()
    var rightBottom: HWPPositionXY = HWPPositionXY()
    var leftAfterCutting: Int = 0
    var topAfterCutting: Int = 0
    var rightAfterCutting: Int = 0
    var bottomAfterCutting: Int = 0
    var innerMargin: HWPInnerMargin = HWPInnerMargin()
    var pictureInfo: HWPPictureInfo = HWPPictureInfo()
    var borderTransparency: Short = 0
    var instanceId:Long = 0
    var pictureEffect: HWPPictureEffect = HWPPictureEffect()
    var imageWidth: Long = 0
    var imageHeight: Long = 0
    var imageTransparency: Byte = 0
}

/**
 * 다각형 개체 속성을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [positionList] [ArrayList], x좌표와 y좌표를 가진 리스트 (INT32 array)
 */
class HWPShapeComponentPolygon {
    var positionList: ArrayList<HWPPositionXY> = ArrayList()

    /**
     * X/Y좌표를 추가하고 반환하는 함수
     *
     * @return [HWPPositionXY] 생성된 객체 반환
     */
    fun addNewPosition() : HWPPositionXY = HWPPositionXY().apply { positionList.add(this) }
}

/**
 * 사각형 개체 속성을 나타내는 객체
 * 33 bytes
 * @author accforaus
 *
 * @property [roundRate] [Byte], 사각형 모서리 곡률(%) 직각은 0, 둥근 모양은 20, 반원은 50, 그 외는 적당한 값을 % 단위로 사용한다.
 * @property [x1] [Int], 사각형의 좌표 (x1) (INT32 - signed 4 bytes)
 * @property [y1] [Int], 사각형의 좌표 (y1) (INT32 - signed 4 bytes)
 * @property [x2] [Int], 사각형의 좌표 (x2) (INT32 - signed 4 bytes)
 * @property [y2] [Int], 사각형의 좌표 (y2) (INT32 - signed 4 bytes)
 * @property [x3] [Int], 사각형의 좌표 (x3) (INT32 - signed 4 bytes)
 * @property [y3] [Int], 사각형의 좌표 (y3) (INT32 - signed 4 bytes)
 * @property [x4] [Int], 사각형의 좌표 (x4) (INT32 - signed 4 bytes)
 * @property [y4] [Int], 사각형의 좌표 (y4) (INT32 - signed 4 bytes)
 */
class HWPShapeComponentRectangle {
    var roundRate: Byte = 0
    var x1: Int = 0
    var y1: Int = 0
    var x2: Int = 0
    var y2: Int = 0
    var x3: Int = 0
    var y3: Int = 0
    var x4: Int = 0
    var y4: Int = 0
}

/**
 * 동영상 개체 속성을 나타내는 객체
 * 가변 길이
 * @author accforaus
 *
 * @property [videoType] [HWPVideoType], 동영상 타입 (INT32 - signed 4 bytes)
 * @property [videoProperty] [HWPVideoProperty], 동영상 타입에 따라 길이가 다름 (BYTE stream)
 */
class HWPShapeComponentVideo {
    var videoType: HWPVideoType = HWPVideoType.Local
    var videoProperty: HWPVideoProperty? = null
}