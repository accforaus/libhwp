package com.tang.hwplib.reader

import com.tang.hwplib.objects.HWPDocument
import com.tang.hwplib.objects.bodytext.HWPBodyText
import com.tang.hwplib.objects.docinfo.HWPBinData
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataCompress
import com.tang.hwplib.objects.fileheader.HWPFileHeader
import com.tang.hwplib.objects.fileheader.HWPFileVersion
import com.tang.hwplib.reader.bodytext.forSection
import com.tang.hwplib.reader.docinfo.forDocInfo
import com.tang.hwplib.util.exceptions.HWPReadException
import com.tang.hwplib.reader.util.CompoundFileReader
import com.tang.hwplib.reader.util.StreamReader
import java.io.FileInputStream
import java.io.InputStream
import java.net.URL

/**
 * HWP 문서를 읽는 함수
 *
 * @author accforaus
 *
 * @param [inputStream] [InputStream], 문서의 입력 스트림
 * @return [HWPDocument] 생성된 HWP 문서
 */
private fun fromInputStream(inputStream: InputStream) : HWPDocument {
    val hwpDocument: HWPDocument = HWPDocument().apply {
        this.fileHeader = HWPFileHeader()
        this.bodyText = HWPBodyText()
        this.binData = com.tang.hwplib.objects.bindata.HWPBinData()
        this.docInfo = HWPDocInfo()
    }
    val cfr: CompoundFileReader = CompoundFileReader(inputStream)

    val isCompressed = fun(): Boolean = hwpDocument.fileHeader.compressed
    val getVersion = fun(): HWPFileVersion = hwpDocument.fileHeader.version

    /**
     * HWP 문서의 헤더를 읽는 함수
     */
    fun fileHeader() {
        val sr: StreamReader = cfr.getChildStreamReader("FileHeader", false, null)
        forFileHeader(hwpDocument.fileHeader, sr)
        sr.close()
    }

    /**
     * HWP 문서의 정보를 읽는 함수
     */
    fun docInfo() {
        val sr: StreamReader = cfr.getChildStreamReader("DocInfo", isCompressed(), getVersion())
        forDocInfo(hwpDocument.docInfo, sr)
        sr.close()
    }

    /**
     * HWP 문서의 문단을 읽는 함수
     */
    fun bodyText() {
        cfr.moveChildStorage("BodyText")
        val sectionCount: Int = hwpDocument.docInfo.documentProperties.sectionCount
        for (index in 0 until sectionCount) {
            val sr: StreamReader = cfr.getChildStreamReader("Section$index", isCompressed(), getVersion())
            sr.docInfo = hwpDocument.docInfo
            forSection(hwpDocument.bodyText.addNewSection(), sr)
            sr.close()
        }
        cfr.moveParentStorage()
    }

    /**
     * 바이너리 데이터 압축 방식 [HWPBinDataCompress]을 반환하는 함수
     *
     * @param [id] [Int], 바이너리 데이터[HWPBinData] ID
     * @return [HWPBinDataCompress] 압축 방식 반환
     */
    fun getCompressMethod(id: Int) : HWPBinDataCompress {
        val binData: HWPBinData? = try {
            hwpDocument.docInfo.binDataList[id - 1]
        } catch (e: Exception) { null }
        if (binData != null)
            return binData.property.getCompress()
        return HWPBinDataCompress.ByStorageDefault
    }

    /**
     * 바이너리 데이터 압축 방식 [HWPBinDataCompress] 여부를 반환하는 함수
     *
     * @param [compress] [HWPBinDataCompress], 바이너리 데이터 압축 방식
     * @return [Boolean] 바이너리 데이터 압축 방식 여부 반환
     */
    fun isCompressBinData(compress: HWPBinDataCompress): Boolean = when (compress) {
        HWPBinDataCompress.ByStorageDefault -> isCompressed()
        HWPBinDataCompress.Compress -> true
        HWPBinDataCompress.NoCompress -> false
    }

    /**
     * 저장된 바이너리 데이터 [HWPBinData]를 읽고 반환하는 함수
     *
     * @param [name] [String], 바이너리 데이터 이름
     * @param [compress] [HWPBinDataCompress], 바이너리 데이터 압축 방식
     * @return [ByteArray] 저장된 바이너리 데이터 값 반환
     */
    fun readEmbeddedBinaryData(name: String, compress: HWPBinDataCompress) : ByteArray {
        val sr: StreamReader = cfr.getChildStreamReader(name, isCompressBinData(compress), null)
        val binaryData: ByteArray = ByteArray(sr.size.toInt())
        sr.readBytes(binaryData)
        sr.close()
        return binaryData
    }

    /**
     * 바이너리 데이터 [HWPBinData]를 읽는 함수
     */
    fun binData() {
        if (cfr.isChildStorage("BinData")) {
            cfr.moveChildStorage("BinData")
            var id: Int = 1
            val ss: Set<String> = cfr.listChildNames()
            val it: Iterator<String> = ss.iterator()
            while (it.hasNext()) {
                val name: String = it.next()
                val compressMethod: HWPBinDataCompress = getCompressMethod(id)
                hwpDocument.binData.addNewEmbeddedBinaryData().apply {
                    this.name = name
                    this.data = readEmbeddedBinaryData(name, compressMethod)
                    this.compressMethod = compressMethod
                }
                id++
            }
            cfr.moveParentStorage()
        }
    }

    fileHeader()
    if (hwpDocument.fileHeader.hasPassword) throw HWPReadException("Sorry! files with passwords are not supported yet.")

    docInfo()
    bodyText()
    binData()

    hwpDocument.docInfo.binData = hwpDocument.binData
    cfr.close()
    return hwpDocument
}

/**
 * 로컬 파일로 부터 HWP 문서를 읽는 함수
 *
 * @author accforaus
 *
 * @param [filePath] [String], 파일 경로
 * @return [HWPDocument] HWP 파일 반환
 */
internal fun fromFile(filePath: String): HWPDocument = fromInputStream(FileInputStream(filePath))

/**
 * URL로 부터 HWP 문서를 읽는 함수
 *
 * @param [url] [URL], URL
 * @return [HWPDocument] HWP 파일 반환
 */
internal fun fromURL(url: URL): HWPDocument = fromInputStream(url.openStream())