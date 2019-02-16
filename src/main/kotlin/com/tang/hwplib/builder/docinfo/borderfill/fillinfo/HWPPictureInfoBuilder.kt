package com.tang.hwplib.builder.docinfo.borderfill.fillinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPictureEffect
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPictureInfo

class HWPPictureInfoBuilder : HWPBuilder<HWPPictureInfo> {
    private val pictureInfo: HWPPictureInfo = HWPPictureInfo.build()

    fun setBrightness(brightness: Byte) : HWPPictureInfoBuilder = this.apply {
        pictureInfo.brightness = brightness
    }

    fun setContrast(contrast: Byte) : HWPPictureInfoBuilder = this.apply {
        pictureInfo.contrast = contrast
    }

    fun setEffect(effect: HWPPictureEffect) : HWPPictureInfoBuilder = this.apply {
        pictureInfo.effect = effect
    }

    fun setBinItemID(binItemID: Int) : HWPPictureInfoBuilder = this.apply {
        pictureInfo.binItemID = binItemID
    }

    override fun build(): HWPPictureInfo = pictureInfo
}