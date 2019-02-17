package com.tang.hwplib.builder.fileheader

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.fileheader.HWPFileVersion

class HWPFileVersionBuilder : HWPBuilder<HWPFileVersion> {
    private val version : HWPFileVersion = HWPFileVersion.build()

    fun setMM(mm: Short) : HWPFileVersionBuilder = this.apply {
        version.mm = mm
    }

    fun setNN(nn: Short) : HWPFileVersionBuilder = this.apply {
        version.nn = nn
    }

    fun setPP(pp: Short) : HWPFileVersionBuilder = this.apply {
        version.pp = pp
    }

    fun setRR(rr: Short) : HWPFileVersionBuilder = this.apply {
        version.rr = rr
    }

    override fun build(): HWPFileVersion = version
}