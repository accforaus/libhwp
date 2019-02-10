package com.tang.hwplib.writer.docinfo.borderfill

import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPFillInfo
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPictureInfo
import com.tang.hwplib.writer.util.StreamWriter

/**
 * 그림 정보 [HWPPictureInfo]를 쓰는 함수
 *
 * @author accforaus
 *
 * @param [pi] [HWPPictureInfo], 그림 정보 객체
 * @param [sw], [StreamWriter], 스트림 쓰기 객체
 */
internal fun forPictureInfo(pi: HWPPictureInfo, sw: StreamWriter) {
    sw.run {
        writeInt8(pi.brightness)
        writeInt8(pi.contrast)
        writeByte(pi.effect.value.toShort())
        writeUInt16(pi.binItemID)
    }
}

/**
 * 채우기 정보의 길이를 반환하는 함수
 *
 * @param [fi] [HWPFillInfo], 채우기 정보 객체
 *
 * @return [Int] 채우기 정보의 길이 반환
 */
internal fun getFillInfoSize(fi: HWPFillInfo): Int {
    var size: Int = 0
    size += 4
    if (fi.type.value != 0.toLong()) {
        if (fi.type.hasPatternFill()) size += 12
        if (fi.type.hasGradientFill()) {
            size += 17
            size += 4
            val colorCount: Int = fi.gradientFill!!.colorList.size
            if (colorCount > 2) size += colorCount * 4
            size += colorCount * 4
        }
        if (fi.type.hasImageFill()) size += 6

        if (fi.type.hasGradientFill()) {
            size += 4
            size += 1
        } else size +=4

        if (fi.type.hasPatternFill()) size += 1
        if (fi.type.hasGradientFill()) size += 1
        if (fi.type.hasImageFill()) size += 1
    } else
        size += 4
    return size
}

/**
 * 채우지 정보 [HWPFillInfo]를 쓰는 함수
 *
 * @author accforaus
 * @param [fi] [HWPFillInfo], 채우기 정보 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forFillInfo(fi: HWPFillInfo, sw: StreamWriter) {
    sw.writeUInt32(fi.type.value)
    if (fi.type.value != 0.toLong()) {
        if (fi.type.hasPatternFill()) {
            fi.patternFill?.run {
                sw.writeColorRef(backColor.value)
                sw.writeColorRef(patternColor.value)
                sw.writeUInt32(patternType.value.toLong())
            }
        }
        if (fi.type.hasGradientFill()) {
            fi.gradientFill?.run {
                sw.writeInt8(gradientType.value)
                sw.writeInt16(startAngle)
                sw.writeInt16(centerX)
                sw.writeInt16(centerY)
                sw.writeInt16(blurringDegree)

                val colorCount: Int = colorList.size
                sw.writeUInt32(colorCount.toLong())
                if (colorCount > 2) {
                    for (cp in changePointList)
                        sw.writeInt32(cp)
                }
                for (c in colorList)
                    sw.writeUInt32(c.value)
            }
        }
        if (fi.type.hasImageFill()) {
            fi.imageFill?.run {
                sw.writeUInt8(imageFillType.value.toShort())
                forPictureInfo(pictureInfo, sw)
            }
        }

        if (fi.type.hasGradientFill()) {
            sw.writeUInt32(1)
            sw.writeUInt8(fi.gradientFill!!.blurringCenter)
        } else
            sw.writeUInt32(0)

        if (fi.type.hasPatternFill()) sw.writeZero(1)
        if (fi.type.hasGradientFill()) sw.writeZero(1)
        if (fi.type.hasImageFill()) sw.writeZero(1)
    } else sw.writeZero(4)
}