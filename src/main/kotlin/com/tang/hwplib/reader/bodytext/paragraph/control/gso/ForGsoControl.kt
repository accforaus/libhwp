package com.tang.hwplib.reader.bodytext.paragraph.control.gso

import com.tang.hwplib.objects.HWPRecordHeader
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPCtrlData
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderGso
import com.tang.hwplib.objects.bodytext.control.gso.*
import com.tang.hwplib.objects.bodytext.control.gso.caption.HWPCaption
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponent
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentContainer
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.arc.HWPArcBorder
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.curve.HWPCurveSegmentType
import com.tang.hwplib.objects.bodytext.control.gso.video.HWPLocalVideoProperty
import com.tang.hwplib.objects.bodytext.control.gso.video.HWPVideoType
import com.tang.hwplib.objects.bodytext.control.gso.video.HWPWebVideoProperty
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.etc.*
import com.tang.hwplib.reader.bodytext.paragraph.control.bookmark.forCtrlData
import com.tang.hwplib.reader.bodytext.paragraph.control.gso.pack.*
import com.tang.hwplib.reader.docinfo.borderfill.forPictureInfo
import com.tang.hwplib.util.exceptions.HWPReadException
import com.tang.hwplib.reader.util.StreamReader

/**
 * 호 개체 [HWPControlArc]를 읽는 함수
 * Tag ID: [SHAPE_COMPONENT_ARC]
 *
 * @author accforaus
 *
 * @param [arc] [HWPControlArc], 빈 호 개체 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
private fun forControlArc(arc: HWPControlArc, sr: StreamReader) {
    arc.run {
        var rh: HWPRecordHeader = sr.readRecordHeader()
        if (rh.tagId == LIST_HEADER) {
            createTextBox()
            forTextBox(textBox!!, sr, arc.docInfo!!)
            if (!sr.isImmediatelyAfterReadingHeader())
                rh = sr.readRecordHeader()
        }
        if (rh.tagId == SHAPE_COMPONENT_ARC) {
            shapeComponentArc.run {
                arcBorder = HWPArcBorder.valueOf(sr.readUInt8().toByte())
                centerX = sr.readInt32()
                centerY = sr.readInt32()
                axis1X = sr.readInt32()
                axis1Y = sr.readInt32()
                axis2X = sr.readInt32()
                axis2Y = sr.readInt32()
            }
        }
    }
}

/**
 * 묶음 개체 [HWPControlContainer]를 읽는 함수
 * Tag ID: [SHAPE_COMPONENT_CONTAINER]
 *
 * @author accforaus
 *
 * @param [container] [HWPControlContainer], 빈 묶음 개체 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
private fun forControlContainer(container: HWPControlContainer, sr: StreamReader) {
    val scc: HWPShapeComponentContainer = container.shapeComponent as HWPShapeComponentContainer
    val childCount: Int = scc.childControlIdList.size
    for (index in 0 until childCount) {
        val fgc: ForGsoControl = ForGsoControl()
        fgc.readInContainer(sr, container.docInfo!!).run { container.addChildControl(this) }
    }
}

/**
 * 곡선 개체 [HWPControlCurve]를 읽는 함수
 * Tag ID: [SHAPE_COMPONENT_CURVE]
 *
 * @author accforaus
 *
 * @param [curve] [HWPControlCurve], 빈 곡선 개체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
private fun forControlCurve(curve: HWPControlCurve, sr: StreamReader) {
    curve.run {
        var rh: HWPRecordHeader = sr.readRecordHeader()
        if (rh.tagId == LIST_HEADER) {
            createTextBox()
            forTextBox(textBox!!, sr, curve.docInfo!!)
            if (!sr.isImmediatelyAfterReadingHeader())
                rh = sr.readRecordHeader()
        }
        if (rh.tagId == SHAPE_COMPONENT_CURVE) {
            shapeComponentCurve.run {
                val positionCount: Int = sr.readInt32()
                for (index in 0 until positionCount)
                    addPosition().apply {
                        x = sr.readInt32().toLong()
                        y = sr.readInt32().toLong()
                    }
                for (index in 0 until positionCount - 1)
                    addCurveSegmentType(HWPCurveSegmentType.valueOf(sr.readUInt8().toByte()))
                sr.skip(4)
            }
        }
    }
}

/**
 * 타원 개체 [HWPControlEllipse]를 읽는 함수
 * Tag ID: [SHAPE_COMPONENT_ELLIPSE]
 *
 * @author accforaus
 *
 * @param [ellipse] [HWPControlEllipse], 빈 타원 개체 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
private fun forControlEllipse(ellipse: HWPControlEllipse, sr: StreamReader) {
    ellipse.run {
        var rh: HWPRecordHeader = sr.readRecordHeader()
        if (rh.tagId == LIST_HEADER) {
            createTextBox()
            forTextBox(textBox!!, sr, ellipse.docInfo!!)
            if (!sr.isImmediatelyAfterReadingHeader())
                rh = sr.readRecordHeader()
        }
        if (rh.tagId == SHAPE_COMPONENT_ELLIPSE) {
            shapeComponentEllipse.run {
                property.value = sr.readUInt32()
                centerX = sr.readInt32()
                centerY = sr.readInt32()
                axis1X = sr.readInt32()
                axis1Y = sr.readInt32()
                axis2X = sr.readInt32()
                axis2Y = sr.readInt32()
                startX = sr.readInt32()
                startY = sr.readInt32()
                endX = sr.readInt32()
                endY = sr.readInt32()
                startX2 = sr.readInt32()
                startY2 = sr.readInt32()
                endX2 = sr.readInt32()
                endY2 = sr.readInt32()
            }
        }
    }
}

/**
 * 선 개체 [HWPControlLine]를 읽는 함수
 * Tag ID: [SHAPE_COMPONENT_LINE]
 *
 * @author accforaus
 *
 * @param [line] [HWPControlLine], 빈 선 개체 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
private fun forControlLine(line: HWPControlLine, sr: StreamReader) {
    sr.readRecordHeader().let {
        if (it.tagId == SHAPE_COMPONENT_LINE) {
            line.shapeComponentLine.run {
                startX = sr.readInt32()
                startY = sr.readInt32()
                endX = sr.readInt32()
                endY = sr.readInt32()
                if (sr.readInt32() == 1)
                    startedRightOrBottom = true
            }
        }
    }
}

private fun forControlObjectLinkLine(objectLinkLine: HWPControlObjectLinkLine, sr: StreamReader) {
    sr.readRecordHeader().let {
        if (it.tagId == SHAPE_COMPONENT_LINE) {
            objectLinkLine.shapeComponentObjectLinkLine.run {
                startX = sr.readInt32()
                startY = sr.readInt32()
                endX = sr.readInt32()
                endY = sr.readInt32()

                val unknownSize = (sr.header.size - sr.readAfterHeader).toInt()
                if (unknownSize > 0) {
                    val unknown: ByteArray = ByteArray(unknownSize)
                    sr.readBytes(unknown)
                    this.unknown = unknown
                }

            }
        }
    }
}

/**
 * OLE 개체 [HWPControlOLE]를 읽는 함수
 * Tag ID: [SHAPE_COMPONENT_OLE]
 *
 * @author accforaus
 *
 * @param [ole] [HWPControlOLE], 빈 OLE 개체 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
private fun forControlOLE(ole: HWPControlOLE, sr: StreamReader) {
    sr.readRecordHeader().let {
        if (it.tagId == SHAPE_COMPONENT_OLE) {
            ole.shapeComponentOLE.run {
                property.value = sr.readUInt32()
                extentWidth = sr.readInt32()
                extentHeight = sr.readInt32()
                binDataId = sr.readUInt16()
                borderColor.value = sr.readColorRef()
                borderThickness = sr.readInt32()
                borderProperty.value = sr.readUInt32()
            }
        }
    }
}

/**
 * 그림 개체 [HWPControlPicture]를 읽는 함수
 * Tag ID: [SHAPE_COMPONENT_PICTURE]
 *
 * @author accforaus
 *
 * @param [picture] [HWPControlPicture], 빈 그림 개체 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
private fun forControlPicture(picture: HWPControlPicture, sr: StreamReader) {
    sr.readRecordHeader().let {
        if (it.tagId == SHAPE_COMPONENT_PICTURE) {
            picture.shapeComponentPicture.run {
                borderColor.value = sr.readColorRef()
                borderThickness = sr.readInt32()
                borderProperty.value = sr.readUInt32()
                leftTop.x = sr.readInt32().toLong()
                leftTop.y = sr.readInt32().toLong()
                rightTop.x = sr.readInt32().toLong()
                rightTop.y = sr.readInt32().toLong()
                rightBottom.x = sr.readInt32().toLong()
                rightBottom.y = sr.readInt32().toLong()
                leftBottom.x = sr.readInt32().toLong()
                leftBottom.y = sr.readInt32().toLong()
                leftAfterCutting = sr.readInt32()
                topAfterCutting = sr.readInt32()
                rightAfterCutting = sr.readInt32()
                bottomAfterCutting = sr.readInt32()

                innerMargin.run {
                    left = sr.readUInt16()
                    right = sr.readUInt16()
                    top = sr.readUInt16()
                    bottom = sr.readUInt16()
                }

                forPictureInfo(pictureInfo, sr)
                if (!sr.isEndOfRecord()) borderTransparency = sr.readUInt8()
                if (!sr.isEndOfRecord()) instanceId = sr.readUInt32()
                if (!sr.isEndOfRecord()) forPictureEffect(pictureEffect, sr)
                if (!sr.isEndOfRecord()) {
                    imageWidth = sr.readUInt32()
                    imageHeight = sr.readUInt32()
                }
                if (!sr.isEndOfRecord()) imageTransparency = sr.readInt8()
            }
        }
    }
}

/**
 * 다각형 개체 [HWPControlPolygon]를 읽는 함수
 * Tag ID: [SHAPE_COMPONENT_POLYGON]
 *
 * @param [polygon] [HWPControlPolygon], 빈 다각형 개체 객체
 * @param [sr] [StreamReader], 스트림 리더
 */
private fun forControlPolygon(polygon: HWPControlPolygon, sr: StreamReader) {
    polygon.run {
        var rh: HWPRecordHeader = sr.readRecordHeader()
        if (rh.tagId == LIST_HEADER) {
            createTextBox()
            forTextBox(textBox!!, sr, docInfo!!)
            if (!sr.isImmediatelyAfterReadingHeader())
                rh = sr.readRecordHeader()
        }
        if (rh.tagId == SHAPE_COMPONENT_POLYGON) {
            shapeComponentPolygon.run {
                for (index in 0 until sr.readInt32())
                    addNewPosition().apply {
                        x = sr.readInt32().toLong()
                        y = sr.readInt32().toLong()
                    }
                sr.skip(4)
            }
        }
    }
}

/**
 * 사각형 개체 [HWPControlRectangle]를 읽는 함수
 * Tag ID: [SHAPE_COMPONENT_RECTANGLE]
 *
 * @author accforaus
 *
 * @param [rectangle] [HWPControlRectangle], 빈 사각형 개체 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
private fun forControlRectangle(rectangle: HWPControlRectangle, sr: StreamReader) {
    rectangle.run {
        var rh: HWPRecordHeader = sr.readRecordHeader()

        if (rh.tagId == CTRL_DATA) {
            createCtrlData()
            forCtrlData(getCtrlData()!!, sr)
            if (!sr.isImmediatelyAfterReadingHeader())
                rh = sr.readRecordHeader()
        }
        if (rh.tagId == LIST_HEADER) {
            createTextBox()
            forTextBox(textBox!!, sr, docInfo!!)
            if (!sr.isImmediatelyAfterReadingHeader())
                rh = sr.readRecordHeader()
        }
        if (rh.tagId == SHAPE_COMPONENT_RECTANGLE) {
            shapeComponentRectangle.run {
                roundRate = sr.readInt8()
                x1 = sr.readInt32()
                y1 = sr.readInt32()
                x2 = sr.readInt32()
                y2 = sr.readInt32()
                x3 = sr.readInt32()
                y3 = sr.readInt32()
                x4 = sr.readInt32()
                y4 = sr.readInt32()
            }
        }
    }
}

/**
 * 동영상 개체 [HWPControlVideo]를 읽는 함수
 * Tag ID: [VIDEO_DATA]
 *
 * @author accforaus
 *
 * @param [video] [HWPControlVideo], 빈 비디오 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
private fun forControlVideo(video: HWPControlVideo, sr: StreamReader) {
    video.run {
        shapeComponentVideo.run {
            videoType = HWPVideoType.valueOf(sr.readInt32())
            when (videoType) {
                HWPVideoType.Local -> {
                    videoProperty = HWPLocalVideoProperty().apply {
                        videoBinDataID = sr.readUInt16()
                        thumbBinDataID = sr.readUInt16()
                    }
                }
                HWPVideoType.Web -> {
                    videoProperty = HWPWebVideoProperty().apply {
                        webTag = sr.readUTF16LEString()
                        thumbBinDataID = sr.readUInt16()
                    }
                }
            }
        }
    }
}

/**
 * 개체 컨트롤 [HWPGsoControl]을 읽는 객체
 *
 * @author accforaus
 *
 * @property [paragraph] [HWPParagraph], 문단 객체
 * @property [sr] [StreamReader], 스트림 리더 객체
 * @property [gsoControl] [HWPGsoControl], 개체 컨트롤 객체
 */
internal class ForGsoControl {
    var paragraph: HWPParagraph? = null
    var sr: StreamReader? = null
    var gsoControl: HWPGsoControl? = null

    /**
     * 개체 컨트롤 [HWPGsoControl]을 읽는 함수
     *
     * @param [paragraph] [HWPParagraph], 문단 객체
     * @param [sr] [StreamReader], 스트림 리더 객체
     */
    fun read(paragraph: HWPParagraph, sr: StreamReader) {
        this.paragraph = paragraph
        this.sr = sr
        val header: HWPCtrlHeaderGso = ctrlHeader()
        val caption: HWPCaption? = caption()
        val ctrlData: HWPCtrlData? = ctrlData()
        val gsoId: Long = gsoIDFromShapeComponent()
        this.gsoControl = createGsoControl(header, caption, ctrlData, gsoId)
        this.gsoControl?.docInfo = paragraph.docInfo
        restPartOfShapeComponent()
        restPartOfControl()
    }

    /**
     * 컨트롤 헤더 [HWPCtrlHeaderGso]를 읽고 반환하는 함수
     *
     * @return [HWPCtrlHeaderGso] 읽혀진 컨트롤 헤더 반환
     */
    private fun ctrlHeader(): HWPCtrlHeaderGso {
        val header: HWPCtrlHeaderGso = HWPCtrlHeaderGso()
        forCtrlHeaderGso(header, sr!!)
        return header
    }

    /**
     * 캡션 객체 [HWPCaption]를 읽고 반환하는 함수
     *
     * @return [HWPCaption] 읽혀진 캡션 객체 반환 (존재하지 않으면 NULL)
     */
    private fun caption(): HWPCaption? {
        sr!!.readRecordHeader()
        if (sr!!.header.tagId == LIST_HEADER) {
            val caption: HWPCaption = HWPCaption()
            forCaption(caption, sr!!, paragraph!!.docInfo!!)
            return caption
        } else {
            return null
        }
    }

    /**
     * 컨트롤 데이터 [HWPCtrlData]를 읽고 반환하는 함수
     *
     * @return [HWPCtrlData] 읽혀진 컨트롤 데이터 반환 (존재하지 않으면 NULL)
     */
    private fun ctrlData(): HWPCtrlData? {
        if (!sr!!.isImmediatelyAfterReadingHeader()) sr!!.readRecordHeader()
        if (sr!!.header.tagId == CTRL_DATA) {
            val ctrlData: HWPCtrlData = HWPCtrlData()
            forCtrlData(ctrlData, sr!!)
            return ctrlData
        } else
            return null
    }

    /**
     * 개체 공통 요소 [HWPShapeComponent]로 부터 개체 컨트롤 ID를 반환하는 함수
     *
     * @throws [HWPReadException]
     * @return [Long] 개체 공통 요소로 부터 반환된 ID
     */
    private fun gsoIDFromShapeComponent(): Long {
        if (!sr!!.isImmediatelyAfterReadingHeader()) sr!!.readRecordHeader()
        if (sr!!.header.tagId == SHAPE_COMPONENT) {
            val  id: Long = sr!!.readUInt32()
            sr!!.skip(4)
            return id
        } else throw HWPReadException("[gsoIDFromShapeComponent()] Shape Component must come after HWPCtrlHeader for gso control.")
    }

    /**
     * 개체 컨트롤 [HWPGsoControl]를 생성하고 반환하는 함수
     *
     * @param [header] [HWPCtrlHeaderGso], 개체 컨트롤 헤더 객체
     * @param [caption] [HWPCaption], 캡션 객체
     * @param [ctrlData] [HWPCtrlData], 컨트롤 데이터 객체
     * @param [gsoId] [Long], 개체 컨트롤 ID 값
     *
     * @return [HWPGsoControl] 생성된 개체 컨트롤 객체 반환 (존재하지 않으면 NULL)
     */
    private fun createGsoControl(header: HWPCtrlHeaderGso, caption: HWPCaption?, ctrlData: HWPCtrlData?, gsoId: Long) : HWPGsoControl? = paragraph!!.addNewGsoControl(gsoId, header).also {
        it?.caption = caption
        it?.setCtrlData(ctrlData)
    }

    /**
     * 개체 공통 요소 [HWPShapeComponent]를 읽는 함수
     */
    private fun restPartOfShapeComponent() {
        forShapeComponent(gsoControl!!, sr!!)
    }

    /**
     * 개체 컨트롤 타입 [HWPGsoControlType]별로 읽는 함수
     */
    private fun restPartOfControl() {
        when (gsoControl!!.getGsoType()) {
            HWPGsoControlType.Line -> forControlLine(gsoControl as HWPControlLine, sr!!)
            HWPGsoControlType.Rectangle -> forControlRectangle(gsoControl as HWPControlRectangle, sr!!)
            HWPGsoControlType.Ellipse -> forControlEllipse(gsoControl as HWPControlEllipse, sr!!)
            HWPGsoControlType.Arc -> forControlArc(gsoControl as HWPControlArc, sr!!)
            HWPGsoControlType.Polygon -> forControlPolygon(gsoControl as HWPControlPolygon, sr!!)
            HWPGsoControlType.Curve -> forControlCurve(gsoControl as HWPControlCurve, sr!!)
            HWPGsoControlType.Picture -> forControlPicture(gsoControl as HWPControlPicture, sr!!)
            HWPGsoControlType.OLE -> forControlOLE(gsoControl as HWPControlOLE, sr!!)
            HWPGsoControlType.Container -> forControlContainer(gsoControl as HWPControlContainer, sr!!)
            HWPGsoControlType.ObjectLinkLine -> forControlObjectLinkLine(gsoControl as HWPControlObjectLinkLine, sr!!)
            HWPGsoControlType.Video -> forControlVideo(gsoControl as HWPControlVideo, sr!!)
        }
    }

    /**
     * 묶음 개체 [HWPControlContainer]를 읽고 반환하는 함수
     *
     * @return [HWPGsoControl] 읽혀진 묶음 개체 컨트롤 객체 반환
     */
    fun readInContainer(sr: StreamReader, docInfo: HWPDocInfo) : HWPGsoControl {
        this.sr = sr
        shapeComponentInContainer(docInfo)
        restPartOfControl()
        return this.gsoControl!!
    }

    /**
     * 묶음 개체 공통 요소 [HWPShapeComponentContainer]를 읽는 함수
     *
     * @throws [HWPReadException]
     */
    private fun shapeComponentInContainer(docInfo: HWPDocInfo) {
        sr!!.readRecordHeader()
        if (sr!!.header.tagId == SHAPE_COMPONENT) {
            val id: Long = sr!!.readUInt32()
            this.gsoControl = createHWPGSOControl(id, null)
            this.gsoControl?.docInfo = docInfo
            forShapeComponent(this.gsoControl!!, sr!!)
        } else throw HWPReadException("[shapeComponentInContainer()] Shape Component must come after HWPCtrlHeader for gso control")
    }
}