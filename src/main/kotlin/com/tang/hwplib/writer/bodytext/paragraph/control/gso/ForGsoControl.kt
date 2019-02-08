package com.tang.hwplib.writer.bodytext.paragraph.control.gso

import com.tang.hwplib.objects.bodytext.control.gso.*
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentContainer
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentCurve
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentLineForObjectLinkLine
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentNormal
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.curve.HWPCurveSegmentType
import com.tang.hwplib.objects.etc.*
import com.tang.hwplib.writer.util.StreamWriter
import com.tang.hwplib.writer.bodytext.paragraph.control.bookmark.forCtrlData
import com.tang.hwplib.writer.bodytext.paragraph.control.gso.pack.*
import com.tang.hwplib.writer.docinfo.borderfill.forPictureInfo

/**
 * 호 개체 [HWPControlArc]를 쓰는 함수
 * Tag ID: [SHAPE_COMPONENT_ARC]
 *
 * @author accforaus
 *
 * @param [arc] [HWPControlArc], 호 개체 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
private fun forControlArc(arc: HWPControlArc, sw: StreamWriter) {
    sw.upRecordLevel()
    forTextBox(arc.textBox, sw)
    arc.shapeComponentArc.run {
        sw.writeRecordHeader(SHAPE_COMPONENT_ARC.toInt(), 25)
        sw.writeUInt8(arcBorder.value.toShort())
        sw.writeInt32(centerX)
        sw.writeInt32(centerY)
        sw.writeInt32(axis1X)
        sw.writeInt32(axis1Y)
        sw.writeInt32(axis2X)
        sw.writeInt32(axis2Y)
    }

    sw.downRecordLevel()
}

/**
 * 묶음 개체 [HWPControlContainer]를 쓰는 함수
 * Tag ID: [SHAPE_COMPONENT_CONTAINER]
 *
 * @author accforaus
 *
 * @param [container] [HWPControlContainer], 묶음 개체 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
private fun forControlContainer(container: HWPControlContainer, sw: StreamWriter) {
    for (child in container.childControlList)
        ForGsoControl.writeInContainers(child, sw)
}

/**
 * 곡선 개체 [HWPControlCurve]를 쓰는 함수
 * Tag ID: [SHAPE_COMPONENT_CURVE]
 *
 * @author accforaus
 *
 * @param [curve] [HWPControlCurve], 곡선 개체 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
private fun forControlCurve(curve: HWPControlCurve, sw: StreamWriter) {
    /**
     * 곡선 개체의 전체 크기를 반환하는 함수
     *
     * @return [Int] 곡선 개체의 전체 크기를 반환
     */
    fun getSize(scc: HWPShapeComponentCurve): Int {
        var size: Int = 0
        size += 4
        size += scc.positionList.size * 8
        size += scc.positionList.size - 1
        size += 4
        return size
    }
    sw.upRecordLevel()

    forTextBox(curve.textBox, sw)
    curve.shapeComponentCurve.run {
        sw.writeRecordHeader(SHAPE_COMPONENT_CURVE.toInt(), getSize(this))
        val positionCount: Int = positionList.size
        sw.writeInt32(positionCount)
        for (p in positionList) {
            sw.writeInt32(p.x.toInt())
            sw.writeInt32(p.y.toInt())
        }
        for (index in 0 until positionCount - 1) {
            val cst: HWPCurveSegmentType = segmentTypeList[index]
            sw.writeUInt8(cst.value.toShort())
        }
        sw.writeZero(4)
    }
    sw.downRecordLevel()
}

/**
 * 타원 개체 [HWPControlEllipse]를 쓰는 함수
 * Tag ID: [SHAPE_COMPONENT_ELLIPSE]
 *
 * @author accforaus
 *
 * @param [ellipse] [HWPControlEllipse], 타원 개체 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
private fun forControlEllipse(ellipse: HWPControlEllipse, sw: StreamWriter) {
    sw.upRecordLevel()

    forTextBox(ellipse.textBox, sw)
    ellipse.shapeComponentEllipse.run {
        sw.writeRecordHeader(SHAPE_COMPONENT_ELLIPSE.toInt(), 60)

        sw.writeUInt32(property.value)
        sw.writeInt32(centerX)
        sw.writeInt32(centerY)
        sw.writeInt32(axis1X)
        sw.writeInt32(axis1Y)
        sw.writeInt32(axis2X)
        sw.writeInt32(axis2Y)
        sw.writeInt32(startX)
        sw.writeInt32(startY)
        sw.writeInt32(endX)
        sw.writeInt32(endY)
        sw.writeInt32(startX2)
        sw.writeInt32(startY2)
        sw.writeInt32(endX2)
        sw.writeInt32(endY2)
    }

    sw.downRecordLevel()
}

/**
 * 선 개체 [HWPControlLine]를 쓰는 함수
 * Tag ID: [SHAPE_COMPONENT_LINE]
 *
 * @author accforaus
 *
 * @param [line] [HWPControlLine], 선 개체 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
private fun forControlLine(line: HWPControlLine, sw: StreamWriter) {
    sw.upRecordLevel()

    line.shapeComponentLine.run {
        sw.writeRecordHeader(SHAPE_COMPONENT_LINE.toInt(), 20)
        sw.writeInt32(startX)
        sw.writeInt32(startY)
        sw.writeInt32(endX)
        sw.writeInt32(endY)
        val temp = if (startedRightOrBottom) 1 else 0
        sw.writeInt32(temp)
    }

    sw.downRecordLevel()
}

/**
 * 오브젝트 연결 선 개체 [HWPControlObjectLinkLine]를 쓰는 함수
 * Tag ID: [SHAPE_COMPONENT_LINE]
 *
 * @author accforaus
 *
 * @param [oll] [HWPControlObjectLinkLine], 오브젝트 연결 선 개체 객체
 * @param [sw] [StreamWriter], 스르팀 쓰기 객체
 */
private fun forControlObjectLinkLine(oll: HWPControlObjectLinkLine, sw: StreamWriter) {
    sw.upRecordLevel()

    oll.shapeComponentObjectLinkLine.run {
        fun getSize(scl: HWPShapeComponentLineForObjectLinkLine) : Int = if (scl.unknown != null) 16 + scl.unknown!!.size else 16
        sw.writeRecordHeader(SHAPE_COMPONENT_LINE.toInt(), getSize(this))

        sw.writeInt32(startX)
        sw.writeInt32(startY)
        sw.writeInt32(endX)
        sw.writeInt32(endY)
        if (unknown != null) sw.writeBytes(unknown!!)
    }

    sw.downRecordLevel()
}

/**
 * OLE 개체 [HWPControlOLE]를 쓰는 함수
 * Tag ID: [SHAPE_COMPONENT_OLE]
 *
 * @author accforaus
 *
 * @param [ole] [HWPControlOLE], OLE 개체 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
private fun forControlOLE(ole: HWPControlOLE, sw: StreamWriter) {
    sw.upRecordLevel()

    ole.shapeComponentOLE.run {
        sw.writeRecordHeader(SHAPE_COMPONENT_OLE.toInt(), 26)

        sw.writeUInt32(property.value)
        sw.writeInt32(extentWidth)
        sw.writeInt32(extentHeight)
        sw.writeUInt16(binDataId)
        sw.writeColorRef(borderColor.value)
        sw.writeInt32(borderThickness)
        sw.writeUInt32(borderProperty.value)
    }

    sw.downRecordLevel()
}

/**
 * 그림 개체 [HWPControlPicture]를 쓰는 함수
 * Tag ID: [SHAPE_COMPONENT_PICTURE]
 *
 * @author accforaus
 *
 * @param [picture] [HWPControlPicture], 그림 개체 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
private fun forControlPicture(picture: HWPControlPicture, sw: StreamWriter) {
    sw.upRecordLevel()

    picture.shapeComponentPicture.run {
        /**
         * 그림 개체의 전체 크기를 반환하는 함수
         *
         * @return [Int] 그림 개체의 전체 크기 반환
         */
        fun getSize() : Int {
            var size: Int = 0
            size += 60
            size += 8
            size += 5
            size += 5
            size += getPictureEffectSize(pictureEffect)
            size += 9
            return size
        }
        sw.writeRecordHeader(SHAPE_COMPONENT_PICTURE.toInt(), getSize())
        sw.writeColorRef(borderColor.value)
        sw.writeInt32(borderThickness)
        sw.writeUInt32(borderProperty.value)
        sw.writeInt32(leftTop.x.toInt())
        sw.writeInt32(leftTop.y.toInt())
        sw.writeInt32(rightTop.x.toInt())
        sw.writeInt32(rightTop.y.toInt())
        sw.writeInt32(rightBottom.x.toInt())
        sw.writeInt32(rightBottom.y.toInt())
        sw.writeInt32(leftBottom.x.toInt())
        sw.writeInt32(leftBottom.y.toInt())
        sw.writeInt32(leftAfterCutting)
        sw.writeInt32(topAfterCutting)
        sw.writeInt32(rightAfterCutting)
        sw.writeInt32(bottomAfterCutting)
        innerMargin.let {
            sw.writeUInt16(it.left)
            sw.writeUInt16(it.right)
            sw.writeUInt16(it.top)
            sw.writeUInt16(it.bottom)
        }
        forPictureInfo(pictureInfo, sw)
        sw.writeUInt8(borderTransparency)
        sw.writeUInt32(instanceId)
        forPictureEffect(pictureEffect, sw)
        sw.writeUInt32(imageWidth)
        sw.writeUInt32(imageHeight)
        sw.writeInt8(imageTransparency)
    }

    sw.downRecordLevel()
}

/**
 * 다각형 개체 [HWPControlPolygon]을 쓰는 객체
 * Tag ID: [SHAPE_COMPONENT_POLYGON]
 *
 * @author accforaus
 *
 * @param [polygon] [HWPControlPolygon], 다각형 개체 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
private fun forControlPolygon(polygon: HWPControlPolygon, sw: StreamWriter) {
    sw.upRecordLevel()

    forTextBox(polygon.textBox, sw)
    polygon.shapeComponentPolygon.run {
        /**
         * 다각형 개체의 크기를 반환하는 함수
         *
         * @return [Int] 다각형 개케의 크기 반환
         */
        fun getSize(): Int {
            var size: Int = 0
            size += 4
            size += 8 * this.positionList.size
            size += 4
            return size
        }

        sw.writeRecordHeader(SHAPE_COMPONENT_POLYGON.toInt(), getSize())
        sw.writeInt32(positionList.size)
        for (p in positionList) {
            sw.writeInt32(p.x.toInt())
            sw.writeInt32(p.y.toInt())
        }
        sw.writeZero(4)
    }

    sw.downRecordLevel()
}

/**
 * 사각형 개체 [HWPControlRectangle]를 쓰는 함수
 * Tag ID: [SHAPE_COMPONENT_RECTANGLE]
 *
 * @author accforaus
 *
 * @param [rect] [HWPControlRectangle], 사각형 개체 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
private fun forControlRectangle(rect: HWPControlRectangle, sw: StreamWriter) {
    sw.upRecordLevel()

    rect.getCtrlData()?.run { forCtrlData(this, sw) }
    forTextBox(rect.textBox, sw)
    rect.shapeComponentRectangle.run {
        sw.writeRecordHeader(SHAPE_COMPONENT_RECTANGLE.toInt(), 33)

        sw.writeInt8(roundRate)
        sw.writeInt32(x1)
        sw.writeInt32(y1)
        sw.writeInt32(x2)
        sw.writeInt32(y2)
        sw.writeInt32(x3)
        sw.writeInt32(y3)
        sw.writeInt32(x4)
        sw.writeInt32(y4)
    }

    sw.downRecordLevel()
}

/**
 * Gso 개체를 쓰는 객체
 *
 * @author accforaus
 */
internal class ForGsoControl {
    companion object {
        /**
         * Gso 개체를 쓰는 함수
         *
         * @param [gso] [HWPGsoControl], Gso 개체 객체
         * @param [sw] [StreamWriter], 스트림 쓰기 객체
         */
        fun write(gso: HWPGsoControl, sw: StreamWriter) {
            forCtrlHeaderGso(gso.getHeader()!!, sw)

            sw.upRecordLevel()

            forCaption(gso.caption, sw)
            shapeComponent(gso, sw)
            restPart(gso, sw)

            sw.downRecordLevel()
        }

        /**
         * 개체 공통 요소를 쓰는 함수
         *
         * @param [gso] [HWPGsoControl], Gso 개체 객체
         * @param [sw] [StreamWriter], 스트림 쓰기 객체
         */
        private fun shapeComponent(gso: HWPGsoControl, sw: StreamWriter) {
            if (gso.getGsoType() == HWPGsoControlType.Container)
                forShapeComponentForContainer(gso.shapeComponent as HWPShapeComponentContainer, sw)
            else
                forShapeComponentForNormal(gso.shapeComponent as HWPShapeComponentNormal, sw)
        }

        /**
         * Gso 개체의 종류별로 쓰는 함수
         *
         * @param [gso] [HWPGsoControl], Gso 개체 객체
         * @param [sw] [StreamWriter], 스트림 쓰기 객체
         */
        private fun restPart(gso: HWPGsoControl, sw: StreamWriter) {
            when (gso.getGsoType()) {
                HWPGsoControlType.Line -> forControlLine(gso as HWPControlLine, sw)
                HWPGsoControlType.Rectangle -> forControlRectangle(gso as HWPControlRectangle, sw)
                HWPGsoControlType.Ellipse -> forControlEllipse(gso as HWPControlEllipse, sw)
                HWPGsoControlType.Arc -> forControlArc(gso as HWPControlArc, sw)
                HWPGsoControlType.Polygon -> forControlPolygon(gso as HWPControlPolygon, sw)
                HWPGsoControlType.Curve -> forControlCurve(gso as HWPControlCurve, sw)
                HWPGsoControlType.Picture -> forControlPicture(gso as HWPControlPicture, sw)
                HWPGsoControlType.OLE -> forControlOLE(gso as HWPControlOLE, sw)
                HWPGsoControlType.Container -> forControlContainer(gso as HWPControlContainer, sw)
                HWPGsoControlType.ObjectLinkLine -> forControlObjectLinkLine(gso as HWPControlObjectLinkLine, sw)
                HWPGsoControlType.Video -> {}
            }
        }

        /**
         * 묶음 개체를 쓰는 함수
         *
         * @param [child] [HWPGsoControl], 자식 Gso 개체
         * @param [sw] [StreamWriter], 스트림 쓰기 객체
         */
        fun writeInContainers(child: HWPGsoControl, sw: StreamWriter) {
            sw.upRecordLevel()

            if (child.getGsoType() == HWPGsoControlType.Container)
                writeInContainer(child.shapeComponent as HWPShapeComponentContainer, sw)
            else writeInContainer(child.shapeComponent as HWPShapeComponentNormal, sw)

            restPart(child, sw)

            sw.downRecordLevel()
        }
    }
}