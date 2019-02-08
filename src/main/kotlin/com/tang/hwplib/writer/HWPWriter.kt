package com.tang.hwplib.writer

import com.tang.hwplib.objects.HWPDocument
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataCompress
import com.tang.hwplib.writer.util.CompoundFileWriter
import com.tang.hwplib.writer.util.StreamWriter
import com.tang.hwplib.writer.autosetter.InstanceID
import com.tang.hwplib.writer.autosetter.autoSetter
import com.tang.hwplib.writer.bodytext.forSection
import com.tang.hwplib.writer.docinfo.forDocInfo

internal fun toHWPFile(hwpDocument: HWPDocument, path: String) {
    val version = { hwpDocument.fileHeader.version }
    val compress = { hwpDocument.fileHeader.compressed }
    val hasBinData = { hwpDocument.binData.embeddedBinaryDataList.size > 0}
    val isCompressBinData = { compressMethod: HWPBinDataCompress -> when (compressMethod) {
        HWPBinDataCompress.ByStorageDefault -> compress()
        HWPBinDataCompress.Compress -> true
        HWPBinDataCompress.NoCompress -> false
    }}

    if (hwpDocument.fileHeader.hasPassword) throw Exception("Files with passwords ar not supported.")

    hwpDocument.run {
        val cfw: CompoundFileWriter = CompoundFileWriter()
        val iid: InstanceID = InstanceID()
        autoSetter(this, iid)

        var sw: StreamWriter? = cfw.openCurrentStream("FileHeader", false, version())
        forFileHeader(fileHeader, sw!!)
        cfw.closeCurrentStream()

        sw = cfw.openCurrentStream("DocInfo", compress(), version())
        forDocInfo(docInfo, sw!!)
        cfw.closeCurrentStream()

        cfw.openCurrentStorage("BodyText")
        for ((index, s) in bodyText.sectionList.withIndex()) {
            sw = cfw.openCurrentStream("Section$index", compress(), version())
            forSection(s, sw!!)
            cfw.closeCurrentStream()
        }
        cfw.closeCurrentStorage()

        if (hasBinData()) {
            cfw.openCurrentStorage("BinData")
            for (ebd in binData.embeddedBinaryDataList) {
                sw = cfw.openCurrentStream(ebd.name, isCompressBinData(ebd.compressMethod!!), version())
                sw!!.writeBytes(ebd.data!!)
                cfw.closeCurrentStream()
            }
            cfw.closeCurrentStorage()
        }

        cfw.write(path)
        cfw.close()
    }
}