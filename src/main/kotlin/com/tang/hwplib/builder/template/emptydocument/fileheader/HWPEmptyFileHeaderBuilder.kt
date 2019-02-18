package com.tang.hwplib.builder.template.emptydocument.fileheader

import com.tang.hwplib.builder.fileheader.HWPFileHeaderBuilder
import com.tang.hwplib.builder.fileheader.HWPFileVersionBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.fileheader.HWPEncryptVersionType
import com.tang.hwplib.objects.fileheader.HWPFileHeader
import com.tang.hwplib.objects.fileheader.HWPKOGLLicenceSupportCountry

class HWPEmptyFileHeaderBuilder : HWPBuilder<HWPFileHeader> {
    private fun buildHeader() : HWPFileHeaderBuilder {
        return HWPFileHeaderBuilder().setVersion(HWPFileVersionBuilder()
                .setMM(5)
                .setNN(0)
                .setPP(5)
                .setRR(0))
                .setCompressed(true)
                .setEncryptVersionType(HWPEncryptVersionType.MoreHWP70)
                .setKoglLicenceSupportCountry(HWPKOGLLicenceSupportCountry.KOR)
    }

    override fun build(): HWPFileHeader = buildHeader().build()
}