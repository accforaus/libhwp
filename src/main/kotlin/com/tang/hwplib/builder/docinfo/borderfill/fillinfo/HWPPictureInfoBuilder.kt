package com.tang.hwplib.builder.docinfo.borderfill.fillinfo

import com.tang.hwplib.builder.docinfo.HWPBinDataBuilder
import com.tang.hwplib.builder.docinfo.HWPBinDataListBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPictureEffect
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPPictureInfo

class HWPPictureInfoBuilder(var docInfo: HWPDocInfo) : HWPBuilder<HWPPictureInfo> {
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

    fun setBinItemID(binDataBuilder: HWPBinDataBuilder) : HWPPictureInfoBuilder = this.apply {
        pictureInfo.binItemID = binDataBuilder.proceed()
    }

    fun setBinItemID(binDataName: String) : HWPPictureInfoBuilder = this.apply {
        pictureInfo.binItemID = docInfo.proceedBinData(binDataName)
    }

    override fun build(): HWPPictureInfo = pictureInfo
}