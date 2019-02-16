package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPBinData
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataProperty

class HWPBinDataBuilder(private val docInfo: HWPDocInfo) : HWPBuilder<HWPBinData> {
    private var HWPBinDataProperty: HWPBinDataProperty = HWPBinDataProperty()

    override fun build(): HWPBinData {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}