package com.tang.hwplib.builder.bindata

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.copyto.bindata.getBinDataName
import com.tang.hwplib.objects.bindata.HWPBinData
import com.tang.hwplib.objects.bindata.HWPEmbeddedBinaryData
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataCompress
import com.tang.hwplib.util.exceptions.HWPBuildException

class HWPEmbeddedBinDataBuilder(private val binData: HWPBinData) : HWPBuilder<HWPEmbeddedBinaryData> {
    private var name: String = ""
    private var data: ByteArray? = null
    private var compressMethod: HWPBinDataCompress? = null

    fun setExtension(extension: String) : HWPEmbeddedBinDataBuilder {
        this.name = getBinDataName(binData.embeddedBinaryDataList.size, extension)
        return this
    }

    fun setData(data: ByteArray) : HWPEmbeddedBinDataBuilder {
        this.data = data
        return this
    }

    fun setCompressMethod(compress: HWPBinDataCompress) : HWPEmbeddedBinDataBuilder {
        this.compressMethod = compress
        return this
    }

    override fun build(): HWPEmbeddedBinaryData {
        if (name == "")
            throw HWPBuildException("Embedded Binary Data name must have extension")
        if (data == null)
            throw HWPBuildException("Embedded Binary Data must be not null")
        if (compressMethod == null)
            throw HWPBuildException("Embedded Binary Data Compress Method must be not null")
        return HWPEmbeddedBinaryData.build(name = name, data = data!!, compressMethod = compressMethod!!)
    }
}