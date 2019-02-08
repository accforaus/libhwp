package com.tang.hwplib.writer.bodytext.paragraph.control.gso.pack

import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponent
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentContainer
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentNormal
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.line.HWPLineInfo
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.render.HWPMatrix
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.shadow.HWPShadowInfo
import com.tang.hwplib.objects.etc.SHAPE_COMPONENT
import com.tang.hwplib.writer.util.StreamWriter
import com.tang.hwplib.writer.docinfo.borderfill.forFillInfo
import com.tang.hwplib.writer.docinfo.borderfill.getFillInfoSize

/**
 * 개체 요소 공통 속성 [HWPShapeComponent]의 전체 길이를 반환하는
 * Tag ID: [SHAPE_COMPONENT]
 *
 * @author accforaus
 *
 * @param [sc] [HWPShapeComponent], 개체 요소 공통 속성
 * @return [Int] 개체 요소 공통 속성의 전체 길이 반환
 */
internal fun getCommonPartSize(sc: HWPShapeComponent): Int {
    var size: Int = 0
    size += 42
    size += 2
    size += 48
    size += 48 * 2 * sc.renderingInfo.scaleRotateMatrixPairList.size
    return size
}

/**
 * 개체 요소 공통 속성 [HWPShapeComponent]을 쓰는 함수
 * Tag ID: [SHAPE_COMPONENT]
 *
 * @author accforaus
 *
 * @param [sc] [HWPShapeComponent], 개체 요소 공통 속성
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forCommonPart(sc: HWPShapeComponent, sw: StreamWriter) {
    /**
     * Matrix [HWPMatrix]를 쓰는 함수
     *
     * @param [m] [HWPMatrix], Matrix 객체
     */
    fun matrix(m: HWPMatrix) {
        for (index in 0 until 6) sw.writeDouble(m.getValue(index))
    }

    sc.run {
        sw.writeInt32(offsetX)
        sw.writeInt32(offsetY)
        sw.writeUInt16(groupingCount)
        sw.writeUInt16(localFileVersion)
        sw.writeUInt32(widthAtCreate)
        sw.writeUInt32(heightAtCreate)
        sw.writeUInt32(widthAtCurrent)
        sw.writeUInt32(heightAtCurrent)
        sw.writeUInt32(property)
        sw.writeUInt16(rotateAngle)
        sw.writeInt32(rotateXCenter)
        sw.writeInt32(rotateYCenter)

        renderingInfo.let {
            val scaleRotateMatrixCount: Int = it.scaleRotateMatrixPairList.size
            sw.writeUInt16(scaleRotateMatrixCount)
            matrix(it.translationMatrix)
            for (srmp in it.scaleRotateMatrixPairList) {
                matrix(srmp.scaleMatrix)
                matrix(srmp.rotateMatrix)
            }
        }
    }
}

/**
 * 그리기 개체 공통 요소 [HWPShapeComponentNormal]의 전체 길이를 반환하는 함수
 *
 * @author accforaus
 *
 * @param [scn] [HWPShapeComponentNormal], 그리기 개체 공통 요소 객체
 * @return [Int] 그리기 개체 공통 요소의 전체 길이 반환
 */
private fun getShapeComponentForNormalSize(scn: HWPShapeComponentNormal): Int {
    var size: Int = 0
    size += 8
    size += getCommonPartSize(scn)
    if (scn.lineInfo != null) size += 13
    if (scn.fillInfo != null) size += getFillInfoSize(scn.fillInfo!!)
    if (scn.shadowInfo != null) size += 22
    return size
}

/**
 * 선 정보 [HWPLineInfo]를 쓰는 함수
 *
 * @author accforaus
 *
 * @param [li] [HWPLineInfo], 선 정보 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
private fun forLineInfo(li: HWPLineInfo?, sw: StreamWriter) {
    li?.let {
        sw.writeColorRef(it.color.value)
        sw.writeInt32(it.thickness)
        sw.writeUInt32(it.property.value)
        sw.writeUInt8(it.outlineStyle.value.toShort())
    }
}

/**
 * 그림자 효과 정보[HWPShadowInfo]를 쓰는 함수
 *
 * @author accforaus
 *
 * @param [si] [HWPShadowInfo], 그림자 정보 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
private fun forShadowInfo(si: HWPShadowInfo?, sw: StreamWriter) {
    si?.let {
        sw.writeUInt32(it.type.value.toLong())
        sw.writeColorRef(it.color.value)
        sw.writeInt32(it.offsetX)
        sw.writeInt32(it.offsetY)
        sw.writeZero(5)
        sw.writeUInt8(it.transparent)
    }
}

/**
 * 그리기 개체 공통 요소 [HWPShapeComponentNormal]를 쓰는 함수
 *
 * @author accforaus
 *
 * @param [scn] [HWPShapeComponentNormal], 그리기 개체 공통 요소 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forShapeComponentForNormal(scn: HWPShapeComponentNormal, sw: StreamWriter) {
    sw.writeRecordHeader(SHAPE_COMPONENT.toInt(), getShapeComponentForNormalSize(scn))
    scn.run {
        sw.writeUInt32(gsoId!!)
        sw.writeUInt32(gsoId!!)
        forCommonPart(this, sw)
        lineInfo?.run { forLineInfo(this, sw) }
        fillInfo?.run { forFillInfo(this, sw) }
        shadowInfo?.run { forShadowInfo(this, sw) }
    }
}

/**
 * 묶음 개체 공통 요소 [HWPShapeComponentContainer]의 전체 길이를 반환하는 함수
 *
 * @author accforaus
 *
 * @param [scc] [HWPShapeComponentContainer], 묶음 개체 공통 요소 객체
 * @return [Int] 묶음 개체 공통 요소의 전체 길이 반환
 */
private fun getShapeComponentForContainerSize(scc: HWPShapeComponentContainer) : Int {
    var size: Int = 0
    size += 8
    size += getCommonPartSize(scc)
    size += 2
    size += 4 * scc.childControlIdList.size
    size += 4
    return size
}

/**
 * 자식 컨트롤 정보를 쓰는 함수
 *
 * @author accforaus
 *
 * @param [scc] [HWPShapeComponentContainer], 묶음 개체 공통 요소 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
private fun forChildInfo(scc: HWPShapeComponentContainer, sw: StreamWriter) {
    val count: Int = scc.childControlIdList.size
    sw.writeUInt16(count)
    for (childId in scc.childControlIdList) sw.writeUInt32(childId)
    sw.writeZero(4)
}

/**
 * 묶음 개체 공통 요소 [HWPShapeComponentContainer]를 쓰는 함수
 *
 * @author accforaus
 *
 * @param [scc] [HWPShapeComponentContainer], 묶음 개체 공통 요소 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forShapeComponentForContainer(scc: HWPShapeComponentContainer, sw: StreamWriter) {
    scc.run {
        sw.writeRecordHeader(SHAPE_COMPONENT.toInt(), getShapeComponentForContainerSize(this))
        sw.writeUInt32(gsoId!!)
        sw.writeUInt32(gsoId!!)
        forCommonPart(this, sw)
        forChildInfo(this, sw)
    }
}

/**
 * 그리기 개체 요소 [HWPShapeComponentNormal]를 쓰는 함수
 *
 * @param [scn] [HWPShapeComponentNormal], 그리기 개체 요소 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun writeInContainer(scn: HWPShapeComponentNormal, sw: StreamWriter) {
    scn.run {
        sw.writeRecordHeader(SHAPE_COMPONENT.toInt(), getShapeComponentForNormalSize(this) - 4)
        sw.writeUInt32(gsoId!!)
        forCommonPart(this, sw)
        forLineInfo(lineInfo, sw)
        fillInfo?.run { forFillInfo(this, sw) }
        forShadowInfo(shadowInfo, sw)
    }
}

/**
 * 묶음 개체 요소 [HWPShapeComponentNormal]를 쓰는 함수
 *
 * @param [scc] [HWPShapeComponentContainer], 묶음 개체 요소 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun writeInContainer(scc: HWPShapeComponentContainer, sw: StreamWriter) {
    scc.run {
        sw.writeRecordHeader(SHAPE_COMPONENT.toInt(), getShapeComponentForContainerSize(this) - 4)
        sw.writeUInt32(gsoId!!)
        forCommonPart(this, sw)
        forChildInfo(this, sw)
    }
}