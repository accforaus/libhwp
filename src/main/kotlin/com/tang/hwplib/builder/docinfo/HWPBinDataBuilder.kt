package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.docinfo.bindata.HWPBinDataPropertyBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPBinData
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataProperty

class HWPBinDataBuilder(private val docInfo: HWPDocInfo) : HWPBuilder<HWPBinData> {
    private val binData : HWPBinData = HWPBinData.build()

    fun setProperty(propertyBuilder: HWPBinDataPropertyBuilder) : HWPBinDataBuilder = this.apply {
        binData.property = propertyBuilder.build()
    }

    fun setAbsolutePathForLink(absolutePathForLink: String) : HWPBinDataBuilder = this.apply {
        binData.absolutePathForLink = absolutePathForLink
    }

    fun setRelativePathForLink(relativePathForLink: String) : HWPBinDataBuilder = this.apply {
        binData.relativePathForLink = relativePathForLink
    }

    fun setBinDataID(binDataID: Int) : HWPBinDataBuilder = this.apply {
        binData.binDataID = binDataID
    }

    fun setExtensionForEmbedding(extensionForEmbedding: String) : HWPBinDataBuilder = this.apply {
        binData.extensionForEmbedding = extensionForEmbedding
    }

    override fun build(): HWPBinData = binData
}