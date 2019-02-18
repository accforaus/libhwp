package com.tang.hwplib.reader.bodytext.paragraph.control.gso.pack

import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterSet
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterType
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderGso
import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControl
import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControlType
import com.tang.hwplib.objects.bodytext.control.gso.caption.HWPCaption
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponent
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentContainer
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentNormal
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.line.HWPOutlineStyle
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.picture.HWPColorWithEffect
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.picture.HWPPictureEffect
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.render.HWPMatrix
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.shadow.HWPShadowType
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPTextBox
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.reader.bodytext.paragraph.forParagraphList
import com.tang.hwplib.reader.docinfo.borderfill.forFillInfo
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.reader.util.StreamReader
import com.tang.hwplib.objects.etc.*
import com.tang.hwplib.reader.bodytext.paragraph.control.bookmark.ForParameterSet

/**
 * 캡션 [HWPCaption]을 읽는 함수
 *
 * @author accforaus
 *
 * @param [caption] [HWPCaption], 빈 캡션 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forCaption(caption: HWPCaption, sr: StreamReader, docInfo : HWPDocInfo) {
    caption.listHeaderForCaption.run {
        paraCount = sr.readInt32()
        property.value = sr.readUInt32()
        captionProperty.value = sr.readUInt32()
        captionWidth = sr.readHwpUnit()
        spaceBetweenCaptionAndFrame = sr.readUInt16()
        textWidth = sr.readHwpUnit()
        sr.skip(8)
    }
    forParagraphList(caption.paragraphList, sr, docInfo)
}

/**
 * 개체 컨트롤 헤더 [HWPCtrlHeaderGso]를 읽는 함수
 * 개체 공통 속성을 읽는다.
 *
 * @author accforaus
 *
 * @param [header] [HWPCtrlHeaderGso], 빈 개체 컨트롤 헤더
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forCtrlHeaderGso(header: HWPCtrlHeaderGso, sr: StreamReader) {
    header.run {
        property.value = sr.readUInt32()
        yOffset = sr.readHwpUnit()
        xOffset = sr.readHwpUnit()
        width = sr.readHwpUnit()
        height = sr.readHwpUnit()
        zOrder = sr.readInt32()
        outerMarginLeft = sr.readUInt16()
        outerMarginRight = sr.readUInt16()
        outerMarginTop = sr.readUInt16()
        outerMarginBottom = sr.readUInt16()
        instanceId = sr.readUInt32()
        if (sr.header.size > sr.readAfterHeader)
            preventPageDivide = get(sr.readInt32(), 0)
        if (sr.header.size > sr.readAfterHeader)
            explanation = sr.readUTF16LEString()
    }
}

/**
 * 그림 효과 [HWPPictureEffect]를 읽는 함수
 *
 * @author accforaus
 *
 * @param [pe] [HWPPictureEffect], 빈 그림 효과 객체
 * @param [sr] [StreamReader], 스트림 리더 객
 */
internal fun forPictureEffect(pe: HWPPictureEffect, sr: StreamReader) {
    fun colorProperty(cp: HWPColorWithEffect) {
        cp.run {
            type = sr.readInt32()
            if (type == 0) {
                val color: ByteArray = ByteArray(4)
                sr.readBytes(color)
                this.color = color
            } else throw Exception("Not supported Color type !!!")
            val colorEffectCount: Int = sr.readUInt32().toInt()
            for (index in 0 until colorEffectCount) {
                addNewColorEffect().also {
                    it.sort = sr.readInt32()
                    it.value = sr.readFloat()
                }
            }
        }
    }

    pe.property.value = sr.readUInt32()
    if (pe.property.hasShadowEffect()) {
        pe.createShadowEffect()
        pe.shadowEffect?.run {
            style = sr.readInt32()
            transparency = sr.readFloat()
            cloudy = sr.readFloat()
            direction = sr.readFloat()
            distance = sr.readFloat()
            sort = sr.readInt32()
            tiltAngleX = sr.readFloat()
            tiltAngleY = sr.readFloat()
            zoomRateX = sr.readFloat()
            zoomRateY = sr.readFloat()
            rotateWithShape = sr.readInt32()
            colorProperty(color)
        }
    }
    if (pe.property.hasNeonEffect()) {
        pe.createNeonEffect()
        pe.neonEffect?.run {
            transparency = sr.readFloat()
            radius = sr.readFloat()
            colorProperty(color)
        }
    }
    if (pe.property.hasSoftBorderEffect()) {
        pe.createSoftEdgeEffect()
        pe.softEdgeEffect?.run {
            radius = sr.readFloat()
        }
    }
    if (pe.property.hasReflectionEffect()) {
        pe.createReflectionEffect()
        pe.reflectionEffect?.run {
            style = sr.readInt32()
            radius = sr.readFloat()
            direction = sr.readFloat()
            distance = sr.readFloat()
            tiltAngleX = sr.readFloat()
            tiltAngleY = sr.readFloat()
            zoomRateX = sr.readFloat()
            zoomRateY = sr.readFloat()
            rotationStyle = sr.readInt32()
            startTransparency = sr.readFloat()
            startPosition = sr.readFloat()
            endTransparency = sr.readFloat()
            endPosition = sr.readFloat()
            offsetDirection = sr.readFloat()
        }
    }
}

/**
 * 개체 요소 [HWPShapeComponent]를 읽는 함수
 * Tag ID: [SHAPE_COMPONENT]
 *
 * @author accforaus
 *
 * @param [gsoControl] [HWPGsoControl], 개체 컨트롤 헤더 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forShapeComponent(gsoControl: HWPGsoControl, sr: StreamReader) {
    /**
     * matrix [HWPMatrix]를 읽는 함수
     *
     * @param [m] [HWPMatrix], 빈 matrix 객체
     */
    fun matrix(m: HWPMatrix) {
        for (index in 0 until 6)
            m.setValue(index, sr.readDouble()!!)
    }

    /**
     * 공통 요소를 읽는 함수
     *
     * @param [sc] [HWPShapeComponent], 빈 개체 요소 객체
     */
    fun commonPart(sc: HWPShapeComponent) {
        sc.run {
            offsetX = sr.readInt32()
            offsetY = sr.readInt32()
            groupingCount = sr.readWord()
            localFileVersion = sr.readWord()
            widthAtCreate = sr.readUInt32()
            heightAtCreate = sr.readUInt32()
            widthAtCurrent = sr.readUInt32()
            heightAtCurrent = sr.readUInt32()
            property = sr.readUInt32()
            rotateAngle = sr.readUInt16()
            rotateXCenter = sr.readInt32()
            rotateYCenter = sr.readInt32()

            renderingInfo.let {
                val scaleRotateMatrixCount: Int = sr.readWord()
                matrix(it.translationMatrix)
                for (index in 0 until scaleRotateMatrixCount) {
                    it.addNewScaleRotateMatrixPair().apply {
                        matrix(this.scaleMatrix)
                        matrix(this.rotateMatrix)
                    }
                }
            }
        }
    }

    /**
     * 그리기 개체용 개체 요소 [HWPShapeComponentNormal]를 읽는 함수
     *
     * @param [scn] [HWPShapeComponentNormal], 그리기 개체용 개체 요소 객체
     */
    fun forShapeComponentNormal(scn: HWPShapeComponentNormal) {
        scn.run {
            commonPart(this)
            if (sr.isEndOfRecord()) return
            createLineInfo()
            lineInfo?.run {
                color.value = sr.readColorRef()
                thickness = sr.readInt32()
                property.value = sr.readUInt32()
                outlineStyle = HWPOutlineStyle.valueOf(sr.readByte().toByte())
            }
            if (sr.isEndOfRecord()) return
            createFillInfo()
            forFillInfo(fillInfo!!, sr)
            if (sr.isEndOfRecord()) return
            createShadowInfo()
            shadowInfo?.run {
                type = HWPShadowType.valueOf(sr.readUInt32().toByte())
                color.value = sr.readColorRef()
                offsetX = sr.readInt32()
                offsetY = sr.readInt32()
                sr.skip(5)
                transparent = sr.readUInt8()
            }
        }
    }

    /**
     * 컨테이너 개체용 요소 [HWPShapeComponentContainer]를 읽는 함수
     *
     * @param [scc] [HWPShapeComponentContainer], 빈 컨테이녀 개체 요소 객체
     */
    fun forShapeComponentContainer(scc: HWPShapeComponentContainer) {
        commonPart(scc)
        val count: Int = sr.readUInt16()
        for (index in 0 until count)
            scc.addChildControlId(sr.readUInt32())
        sr.skip(4)
    }

    if (gsoControl.getGsoType() != HWPGsoControlType.Container) {
        forShapeComponentNormal(gsoControl.shapeComponent as HWPShapeComponentNormal)
    } else
        forShapeComponentContainer(gsoControl.shapeComponent as HWPShapeComponentContainer)
}

/**
 * 그리기 개체 글상자용 텍스트 속성 [HWPTextBox]을 읽는 함수
 *
 * @author accforaus
 *
 * @param [textBox] [HWPTextBox], 글상자 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forTextBox(textBox: HWPTextBox, sr: StreamReader, docInfo: HWPDocInfo) {
    /**
     * 문단 리스트를 읽는 함수
     */
    fun forParagraph() {
        forParagraphList(textBox.paragraphList, sr, docInfo)
    }
    textBox.listHeader.run {
        paraCount = sr.readInt32()
        property.value = sr.readUInt32()
        leftMargin = sr.readUInt16()
        rightMargin = sr.readUInt16()
        topMargin = sr.readUInt16()
        bottomMargin = sr.readUInt16()
        textWidth = sr.readHwpUnit()

        if (sr.isEndOfRecord()) {
            forParagraph()
            return
        }

        sr.skip(8)

        if (!sr.isEndOfRecord()) {
            val temp: Int = sr.readInt32()
            val check = fun (value: Int): Boolean = value == 1
            editableAtFormMode = check(temp)
            if (sr.readUInt8() == 0xff.toShort()) {
                val ps: HWPParameterSet = HWPParameterSet()
                ForParameterSet.read(ps, sr)
                if (ps.id == 0x21b) {
                    for (pi in ps.parameterItemList)
                        if (pi.id == 0x4000.toLong() && pi.type == HWPParameterType.String)
                            fieldName = pi.value_BSTR
                }
            }
        }
    }
    forParagraph()
}