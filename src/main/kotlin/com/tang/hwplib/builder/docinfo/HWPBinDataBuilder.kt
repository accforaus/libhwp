package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.bindata.HWPEmbeddedBinDataBuilder
import com.tang.hwplib.builder.docinfo.bindata.HWPBinDataPropertyBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.copyto.docinfo.IDMappingTypes
import com.tang.hwplib.objects.bindata.HWPEmbeddedBinaryData
import com.tang.hwplib.objects.docinfo.HWPBinData
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataProperty
import com.tang.hwplib.util.exceptions.HWPBuildException

class HWPBinDataBuilder(private val docInfo: HWPDocInfo) : HWPDocInfoBuilder() {
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

    fun setBinDataID(embeddedBinDataBuilder: HWPEmbeddedBinDataBuilder) : HWPBinDataBuilder = this.apply {
        embeddedBinDataBuilder.build().run {
            binData.binDataID = docInfo.binData?.embeddedBinaryDataList?.size ?: 0
        }
    }

    fun setBinDataID(binDataName: String) : HWPBinDataBuilder = this.apply {
        fun findIndexByName() : Int {
            val embeddedList: ArrayList<HWPEmbeddedBinaryData> = docInfo.binData?.embeddedBinaryDataList ?: throw HWPBuildException("BinData in DocInfo must not be null")
            for ((index, bin) in embeddedList.withIndex()) {
                if (bin.name.contains(binDataName))
                    return index
            }
            return -1
        }
        val findIndex: Int = findIndexByName()
        if (findIndex != -1) setBinDataID(findIndex)
        else throw HWPBuildException("BinData doesn't have $binDataName")
    }

    fun setExtensionForEmbedding(extensionForEmbedding: String) : HWPBinDataBuilder = this.apply {
        binData.extensionForEmbedding = extensionForEmbedding
    }

    fun proceed() : Int = build().run {
        docInfo.binDataList.size
    }

    override fun build(): HWPBinData = binData.apply {
        docInfo.let {
            it.binDataList.add(this)
            it.updateIDMappings(IDMappingTypes.BINDATA)
        }
    }
}

class HWPBinDataListBuilder : HWPBuilder<ArrayList<HWPBinData>> {
    private val binDataList: ArrayList<HWPBinData> = ArrayList()

    fun addBinData(binDataBuilder: HWPBinDataBuilder) : HWPBinDataListBuilder = this.apply {
        binDataList.add(binDataBuilder.build())
    }

    override fun build(): ArrayList<HWPBinData> = binDataList
}