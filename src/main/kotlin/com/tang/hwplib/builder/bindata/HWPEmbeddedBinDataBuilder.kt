package com.tang.hwplib.builder.bindata

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.copyto.bindata.getBinDataName
import com.tang.hwplib.objects.bindata.HWPBinData
import com.tang.hwplib.objects.bindata.HWPEmbeddedBinaryData
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataCompress
import com.tang.hwplib.util.exceptions.HWPBuildException

class HWPEmbeddedBinDataBuilder(private val binData: HWPBinData) : HWPBuilder<HWPEmbeddedBinaryData> {
    private val embeddedBinData : HWPEmbeddedBinaryData = HWPEmbeddedBinaryData.build()

    fun setExtension(extension: String) : HWPEmbeddedBinDataBuilder = this.apply {
        embeddedBinData.name = getBinDataName(binData.embeddedBinaryDataList.size, extension)
    }

    fun setData(data: ByteArray) : HWPEmbeddedBinDataBuilder = this.apply {
        embeddedBinData.data = data
    }

    fun setCompressMethod(compress: HWPBinDataCompress) : HWPEmbeddedBinDataBuilder = this.apply {
        embeddedBinData.compressMethod = compress
    }

    override fun build(): HWPEmbeddedBinaryData {
        if (embeddedBinData.name == "")
            throw HWPBuildException("Embedded Binary Data name must have extension")
        if (embeddedBinData.data == null)
            throw HWPBuildException("Embedded Binary Data must be not null")
        if (embeddedBinData.compressMethod == null)
            throw HWPBuildException("Embedded Binary Data Compress Method must be not null")
        return embeddedBinData
    }
}