package com.tang.hwplib.reader.docinfo.borderfill

import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.*
import com.tang.hwplib.reader.util.StreamReader

/**
 * 그럼 정보 [HWPPictureInfo]를 읽는 함수
 *
 * @author accforaus
 *
 * @param [pi] [HWPPictureInfo], 빈 그림 정보 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forPictureInfo(pi: HWPPictureInfo, sr: StreamReader) {
    pi.run {
        brightness = sr.readInt8()
        contrast = sr.readInt8()
        effect = HWPPictureEffect.valueOf(sr.readByte().toByte())
        binItemID = sr.readUInt16()
    }
}

/**
 * 채우기 정보 [HWPFillInfo]를 읽는 함수
 *
 * @author accforaus
 *
 * @param [fi] [HWPFillInfo], 빈 채우기 정보 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forFillInfo(fi: HWPFillInfo, sr: StreamReader) {
    /**
     * 단색 채우기 정보 [HWPPatternFill]을 읽는 객체
     *
     * @param [pf] [HWPPatternFill], 빈 단색 채우기 정보 객체
     */
    fun patternFill(pf: HWPPatternFill) {
        pf.run {
            backColor.value = sr.readColorRef()
            patternColor.value = sr.readColorRef()
            patternType = HWPPatternType.valueOf(sr.readInt32().toByte())
        }
    }

    /**
     * 그러데이션 정보 [HWPGradientFill]를 읽는 함수
     *
     * @param [gf] [HWPGradientFill], 빈 그러데이션 정보 객체
     */
    fun gradientFill(gf: HWPGradientFill) {
        gf.run {
            gradientType = HWPGradientType.valueOf(sr.readInt8())
            startAngle = sr.readUInt32()
            centerX = sr.readUInt32()
            centerY = sr.readUInt32()
            blurringDegree = sr.readUInt32()

            val colorCount: Long = sr.readUInt32()
            if (colorCount > 2) {
                for (index in 0 until colorCount)
                    addChangePoint(sr.readInt32())
            }
            for (index in 0 until colorCount)
                addNewColor().value = sr.readColorRef()
        }
    }

    /**
     * 이미지 채우기 정보 [HWPImageFill]를 읽는 객체
     *
     * @param [imf] [HWPImageFill], 빈 이미지 채우기 정보 객체
     */
    fun imageFill(imf: HWPImageFill) {
        imf.run {
            imageFillType = HWPImageFillType.valueOf(sr.readByte().toByte())
            forPictureInfo(pictureInfo, sr)
        }
    }

    /**
     * 추가 채우기 속성을 읽는 객체
     *
     * @param [fi] [HWPFillInfo], 채우기 정보 객체
     */
    fun additionalProperty(fi: HWPFillInfo) {
        fi.run {
            val size: Long = sr.readDWord()
            if (size.toInt() == 1) {
                if (type.hasGradientFill())
                    gradientFill!!.blurringCenter = sr.readByte()
            } else
                sr.skip(size)
        }
    }

    /**
     * 알려지지 않은 데이터를 읽는 함수
     *
     * @param [fi] [HWPFillInfo], 채우기 정보 객체
     */
    fun unknownBytes(fi: HWPFillInfo) {
        fi.run {
            if (type.hasPatternFill())
                sr.skip(1)
            if (type.hasGradientFill())
                sr.skip(1)
            if (type.hasImageFill())
                sr.skip(1)
        }
    }

    fi.run {
        type.value = sr.readUInt32()
        if (type.value.toInt() != 0) {
            if (type.hasPatternFill()) {
                createPatternFill()
                patternFill(patternFill!!)
            }

            if (type.hasGradientFill()) {
                createGradientFill()
                gradientFill(gradientFill!!)
            }

            if (type.hasImageFill()) {
                createImageFill()
                imageFill(imageFill!!)
            }
            additionalProperty(this)
            if (!sr.isEndOfRecord())
                unknownBytes(this)
        } else
            sr.skip(4)
    }
}