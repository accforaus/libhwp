package com.tang.hwplib.builder.fileheader

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.fileheader.HWPKOGLCCLInfo

class HWPKOGLCCLInfoBuilder : HWPBuilder<HWPKOGLCCLInfo> {
    private val info : HWPKOGLCCLInfo = HWPKOGLCCLInfo.build()

    fun setKoglCclInfo(koglCclInfo: Boolean) : HWPKOGLCCLInfoBuilder = this.apply {
        info.setKoglCclInfo(koglCclInfo)
    }

    fun setPreventCopy(preventCopy: Boolean) : HWPKOGLCCLInfoBuilder = this.apply {
        info.setPreventCopy(preventCopy)
    }

    fun setPermitCopy(permitCopy: Boolean) : HWPKOGLCCLInfoBuilder = this.apply {
        info.setPermitCopy(permitCopy)
    }

    override fun build(): HWPKOGLCCLInfo = info
}