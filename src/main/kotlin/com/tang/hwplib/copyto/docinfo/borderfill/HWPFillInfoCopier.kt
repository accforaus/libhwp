package com.tang.hwplib.copyto.docinfo.borderfill

import com.tang.hwplib.copyto.docinfo.HWPDocInfoCopier
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPFillInfo

class HWPFillInfoCopier(private val docInfos: HWPDocInfoCopier) {
    fun copy(original: HWPFillInfo) : HWPFillInfo = original.copy().apply {
        imageFill?.pictureInfo?.run {
            this.binItemID = docInfos.proceedBinData(this.binItemID)
        }
    }
    fun hasFillInfo(target: HWPFillInfo, source: HWPFillInfo) : Boolean {
        if (target.type.value != source.type.value) return false

        target.gradientFill?.run {
            if (source.gradientFill == null) return false
            source.gradientFill?.let {
                if (this.blurringCenter != it.blurringCenter ||
                        this.centerX != it.centerX ||
                        this.centerY != it.centerY ||
                        this.blurringDegree != it.blurringDegree ||
                        this.startAngle != it.startAngle) return false
                if (this.changePointList.size != 0) {
                    if (it.changePointList.size == 0) return false
                    if (this.changePointList.size != it.changePointList.size) return false
                    for (index in 0 until this.changePointList.size) {
                        if (this.changePointList[index] != it.changePointList[index]) return false
                    }
                }
                if (this.colorList.size != 0) {
                    if (it.colorList.size == 0) return false
                    if (this.colorList.size != it.colorList.size) return false
                    for (index in 0 until this.colorList.size) {
                        if (this.colorList[index].value != it.colorList[index].value) return false
                    }
                }
            }
        }
        target.imageFill?.run {
            if (source.imageFill == null) return false
            source.imageFill?.let {
                if (this.imageFillType.value != it.imageFillType.value ||
                        this.pictureInfo.effect.value != it.pictureInfo.effect.value ||
                        this.pictureInfo.contrast != it.pictureInfo.contrast ||
                        this.pictureInfo.brightness != it.pictureInfo.brightness) return false
            }
        }
        target.patternFill?.run {
            if (source.patternFill == null) return false
            source.patternFill?.let {
                if (this.patternColor.value != it.patternColor.value ||
                        this.backColor.value != it.backColor.value ||
                        this.patternType.value != it.patternType.value) return false
            }
        }
        return true
    }
}