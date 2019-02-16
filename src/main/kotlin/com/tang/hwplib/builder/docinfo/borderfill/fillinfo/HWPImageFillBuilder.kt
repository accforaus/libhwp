package com.tang.hwplib.builder.docinfo.borderfill.fillinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPImageFill
import com.tang.hwplib.objects.docinfo.borderfill.fillinfo.HWPImageFillType

class HWPImageFillBuilder : HWPBuilder<HWPImageFill> {
    private val imageFill: HWPImageFill = HWPImageFill.build()

    fun setImageFillType(imageFillType: HWPImageFillType) : HWPImageFillBuilder = this.apply {
        imageFill.imageFillType = imageFillType
    }

    fun setPictureInfo(pictureInfoBuilder: HWPPictureInfoBuilder) : HWPImageFillBuilder = this.apply {
        imageFill.pictureInfo = pictureInfoBuilder.build()
    }

    override fun build(): HWPImageFill = imageFill
}