package com.tang.hwplib.builder.docinfo.bindata

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataCompress
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataProperty
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataState
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataType

class HWPBinDataPropertyBuilder : HWPBuilder<HWPBinDataProperty> {
    private val property : HWPBinDataProperty = HWPBinDataProperty.build()

    fun setType(type: HWPBinDataType) : HWPBinDataPropertyBuilder = this.apply {
        property.setType(type)
    }

    fun setCompress(compress: HWPBinDataCompress) : HWPBinDataPropertyBuilder = this.apply {
        property.setCompress(compress)
    }

    fun setState(state: HWPBinDataState) : HWPBinDataPropertyBuilder = this.apply {
        property.setState(state)
    }

    override fun build(): HWPBinDataProperty = property
}