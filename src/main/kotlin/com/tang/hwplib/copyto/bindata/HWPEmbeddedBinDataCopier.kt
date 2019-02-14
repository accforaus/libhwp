package com.tang.hwplib.copyto.bindata

import com.tang.hwplib.copyto.docinfo.HWPDocInfoCopier
import com.tang.hwplib.objects.bindata.HWPBinData
import com.tang.hwplib.objects.bindata.HWPEmbeddedBinaryData
import com.tang.hwplib.objects.docinfo.numbering.HWPExtendNumbering
import com.tang.hwplib.util.exceptions.HWPCopyToExcention
import kotlin.math.E

fun getBinDataName(lastIndex: Int, extend: String? = null) : String = StringBuilder("BIN").run {
    val number: String = lastIndex.toString(radix = 16).toUpperCase()
    when (number.length) {
        1 -> {
            for(i in 0..2) this.append("0")
            this.append(number)
        }
        2 -> {
            for (i in 0..1) this.append("0")
            this.append(number)
        }
        3 -> this.append("0").append(number)
        4 -> this.append(number)
    }
    extend?.let {
        this.append(".").append(it)
    }
    this.toString()
}

fun proceedEmbeddedBinData(originalIndex: Int, targetData: HWPBinData, sourceData: HWPBinData) : Int {
    fun copy(index: Int, source: HWPEmbeddedBinaryData) : HWPEmbeddedBinaryData = source.copy().apply {
        this.name = getBinDataName(index, source.name.substring(8))
    }

    fun findByID(id: Int) : Int {
        val name: String = getBinDataName(lastIndex = id)
        for ((index, data) in sourceData.embeddedBinaryDataList.withIndex())
            if (data.name.contains(name)) return index
        return -1
    }

    val originalDataIndex = findByID(originalIndex)
    if (originalDataIndex != -1) {
        val original: HWPEmbeddedBinaryData = sourceData.embeddedBinaryDataList[originalDataIndex]
        val newTargetBinDataID: Int = targetData.embeddedBinaryDataList.size + 1
        val target: HWPEmbeddedBinaryData = original.copy().apply {
            this.name = getBinDataName(newTargetBinDataID, original.name.substring(8))
        }
        targetData.embeddedBinaryDataList.add(target)
        return newTargetBinDataID
    }
    throw HWPCopyToExcention("Cannot find index in [EmbeddedData]")
}