package com.tang.hwplib.writer.bodytext.paragraph.control.gso.pack

import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterSet
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderGso
import com.tang.hwplib.objects.bodytext.control.gso.caption.HWPCaption
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.picture.*
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPTextBox
import com.tang.hwplib.objects.etc.CTRL_HEADER
import com.tang.hwplib.objects.etc.LIST_HEADER
import com.tang.hwplib.util.binary.set
import com.tang.hwplib.util.exceptions.HWPWriteException
import com.tang.hwplib.util.string.getUTF16LEStringSize
import com.tang.hwplib.writer.util.StreamWriter
import com.tang.hwplib.writer.bodytext.paragraph.control.bookmark.ForParameterSet
import com.tang.hwplib.writer.bodytext.paragraph.forParagraphList

/**
 * 캡션 [HWPCaption]을 쓰는 함수
 *
 * @author accforaus
 *
 * @param [caption] [HWPCaption], 캡션 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forCaption(caption: HWPCaption?, sw: StreamWriter) {
    caption?.listHeaderForCaption?.run {
        sw.writeRecordHeader(LIST_HEADER.toInt(), 30)
        sw.writeInt32(paraCount)
        sw.writeUInt32(property.value)
        sw.writeUInt32(captionProperty.value)
        sw.writeHwpUnit(captionWidth)
        sw.writeUInt16(spaceBetweenCaptionAndFrame)
        sw.writeHwpUnit(textWidth)
        sw.writeZero(8)
        forParagraphList(caption.paragraphList, sw)
    }
}

/**
 * 개체 공통 속성 [HWPCtrlHeaderGso]을 쓰는 함수
 *
 * @author accforaus
 *
 * @param [header] [HWPCtrlHeaderGso], 개체 공통 속성 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forCtrlHeaderGso(header: HWPCtrlHeaderGso, sw: StreamWriter) {
    /**
     * 개체 공통 속성의 전체 길이를 반환하는 함수
     *
     * @return [Int] 개체 공통 속성의 전체 길이 반환
     */
    fun getSize(): Int {
        var size: Int = 0
        size += 44
        size += getUTF16LEStringSize(header.explanation)
        return size
    }

    header.run {
        sw.writeRecordHeader(CTRL_HEADER.toInt(), getSize())
        sw.writeUInt32(ctrlId)

        sw.writeUInt32(property.value)
        sw.writeUInt32(yOffset)
        sw.writeUInt32(xOffset)
        sw.writeUInt32(width)
        sw.writeUInt32(height)
        sw.writeInt32(zOrder)
        sw.writeUInt16(outerMarginLeft)
        sw.writeUInt16(outerMarginRight)
        sw.writeUInt16(outerMarginTop)
        sw.writeUInt16(outerMarginBottom)
        sw.writeUInt32(instanceId)
        var temp: Int = 0
        temp = set(temp, 0, preventPageDivide)
        sw.writeInt32(temp)
        sw.writeUTF16LEString(explanation)
    }
}

/**
 * 색상 효과 속성 [HWPColorWithEffect]의 전체 크기를 반환하는 함수
 *
 * @author accforaus
 *
 * @param [color] [HWPColorWithEffect], 색상 효과 객체
 * @return [Int] 색상 효과 속성의 전체 크기 반환
 */
internal fun getColorEffectSize(color: HWPColorWithEffect): Int {
    var size: Int = 0
    size += 8
    size += 4
    size += 8 * color.colorEffectList.size
    return size
}

/**
 * 그림 효과 [HWPPictureEffect]의 전체 크기를 반환하는 함수
 *
 * @author accforaus
 *
 * @param [pe] [HWPPictureEffect], 그림 효과 객체
 * @return [Int] 그림 효과의 전체 크기 반환
 */
internal fun getPictureEffectSize(pe: HWPPictureEffect): Int {
    var size: Int = 0
    size += 4
    pe.shadowEffect?.run {
        size += 44
        size += getColorEffectSize(color)
    }
    pe.neonEffect?.run {
        size += 8
        size += getColorEffectSize(color)
    }
    pe.softEdgeEffect?.run { size += 4 }
    pe.reflectionEffect?.run { size += 56 }
    return size
}

/**
 * 그림 효과 [HWPPictureEffect]를 쓰는 함수
 *
 * @author accforaus
 *
 * @throws [HWPWriteException]
 * @param [pe] [HWPPictureEffect], 그림 효과 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forPictureEffect(pe: HWPPictureEffect, sw: StreamWriter) {
    /**
     * 색상 효과 속성 [HWPColorWithEffect]를 쓰는 함수
     *
     * @throws [HWPWriteException]
     * @param [cp] [HWPColorWithEffect], 색상 효과 객체
     */
    fun colorProperty(cp: HWPColorWithEffect) {
        cp.run {
            sw.writeInt32(type)
            if (type == 0) sw.writeBytes(color!!)
            else throw HWPWriteException("[HWPColorWithEffect] not supported color type!!")

            val colorEffectCount: Int = colorEffectList.size
            sw.writeUInt32(colorEffectCount.toLong())

            for (ce in colorEffectList) {
                sw.writeInt32(ce.sort)
                sw.writeFloat(ce.value)
            }
        }
    }

    /**
     * 그림자 효과 [HWPShadowEffect]를 쓰는 함수
     *
     * @param [se] [HWPShadowEffect], 그림자 효과 객체
     */
    fun shadowEffect(se: HWPShadowEffect?) {
        se?.run {
            sw.writeInt32(style)
            sw.writeFloat(transparency)
            sw.writeFloat(cloudy)
            sw.writeFloat(direction)
            sw.writeFloat(distance)
            sw.writeInt32(sort)
            sw.writeFloat(tiltAngleX)
            sw.writeFloat(tiltAngleY)
            sw.writeFloat(zoomRateX)
            sw.writeFloat(zoomRateY)
            sw.writeInt32(rotateWithShape)
            colorProperty(color)
        }
    }

    /**
     * 네온 효과 [HWPNeonEffect]를 쓰는 함수
     *
     * @param [ne] [HWPNeonEffect], 네온 효과 객체
     */
    fun neonEffect(ne: HWPNeonEffect?) {
        ne?.run {
            sw.writeFloat(transparency)
            sw.writeFloat(radius)
            colorProperty(color)
        }
    }

    /**
     * 부드러운 가장자리 효과 [HWPSoftEdgeEffect]를 쓰는 함수
     *
     * @param [see] [HWPSoftEdgeEffect], 부드러운 가장자리 효과 객체
     */
    fun softEdgeEffect(see: HWPSoftEdgeEffect?) {
        see?.run {
            sw.writeFloat(radius)
        }
    }

    /**
     * 반사 효과 [HWPReflectionEffect]를 쓰는 함수
     *
     * @param [re] [HWPReflectionEffect], 반사 효과 객체
     */
    fun reflectionEffect(re: HWPReflectionEffect?) {
        re?.run {
            sw.writeInt32(style)
            sw.writeFloat(radius)
            sw.writeFloat(direction)
            sw.writeFloat(distance)
            sw.writeFloat(tiltAngleX)
            sw.writeFloat(tiltAngleY)
            sw.writeFloat(zoomRateX)
            sw.writeFloat(zoomRateY)
            sw.writeInt32(rotationStyle)
            sw.writeFloat(startTransparency)
            sw.writeFloat(startPosition)
            sw.writeFloat(endTransparency)
            sw.writeFloat(endPosition)
            sw.writeFloat(offsetDirection)
        }
    }

    pe.run {
        sw.writeUInt32(property.value)

        shadowEffect(shadowEffect)
        neonEffect(neonEffect)
        softEdgeEffect(softEdgeEffect)
        reflectionEffect(reflectionEffect)
    }
}

/**
 * 그리기 개체 글상자용 텍스트 정보 [HWPTextBox]를 쓰는 함수
 *
 * @author accforaus
 *
 * @param [tb] [HWPTextBox], 그리기 개체 글상자용 텍스트 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forTextBox(tb: HWPTextBox?, sw: StreamWriter) {
    /**
     * 그리기 개체 글상자용 텍스트 정보 전체 길이를 반환하는 함수
     *
     * @return [Int] 그리기 개체 글상자용 텍스트 정보 전체 길이 반환
     */
    fun getSize(psFieldName: HWPParameterSet?): Int {
        var size: Int = 0
        size += 32
        if (psFieldName != null) {
            size += 1
            size += ForParameterSet.getSize(psFieldName)
        } else size += 1
        return size
    }

    tb?.listHeader?.run {
        val psFieldName: HWPParameterSet? = HWPParameterSet.createForFieldName(fieldName)
        sw.writeRecordHeader(LIST_HEADER.toInt(), getSize(psFieldName))

        sw.writeInt32(paraCount)
        sw.writeUInt32(property.value)
        sw.writeUInt16(leftMargin)
        sw.writeUInt16(rightMargin)
        sw.writeUInt16(topMargin)
        sw.writeUInt16(bottomMargin)
        sw.writeHwpUnit(textWidth)
        sw.writeZero(8)
        if (editableAtFormMode) sw.writeInt32(1)
        else sw.writeInt32(0)

        if (psFieldName != null) {
            val flag: Short = 0xff
            sw.writeUInt8(flag)

            ForParameterSet.write(psFieldName, sw)
        } else {
            val flag: Short = 0x0
            sw.writeUInt8(flag)
        }
        forParagraphList(tb.paragraphList, sw)
    }
}