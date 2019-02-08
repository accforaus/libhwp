package com.tang.hwplib.writer

import com.tang.hwplib.objects.fileheader.HWPFileHeader
import com.tang.hwplib.util.binary.set
import com.tang.hwplib.writer.util.StreamWriter

/**
 * 한글 파일 헤더 [HWPFileHeader]를 쓰는 함수
 *
 * @author accforaus
 *
 * @param [fh] [HWPFileHeader], 빈 한글 파일 헤더 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forFileHeader(fh: HWPFileHeader, sw: StreamWriter) {
    val signature: ByteArray = byteArrayOf(0x48, 0x57, 0x50, 0x20, 0x44, 0x6F, 0x63, 0x75,
            0x6D, 0x65, 0x6E, 0x74, 0x20, 0x46, 0x69, 0x6C, 0x65, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00)
    sw.writeBytes(signature)
    sw.writeDWord(fh.version.getVersion())

    fh.run {
        var properties: Long = 0
        properties = set(properties, 0, compressed)
        properties = set(properties, 1, hasPassword)
        properties = set(properties, 2, isDeploymentDocument)
        properties = set(properties, 3, saveScript)
        properties = set(properties, 4, isDRMDocument)
        properties = set(properties, 5, hasXMLTemplate)
        properties = set(properties, 6, hasDocumentHistory)
        properties = set(properties, 7, hasSignature)
        properties = set(properties, 8, encryptPublicCertification)
        properties = set(properties, 9, savePrepareSignature)
        properties = set(properties, 10, isPublicCertificationDRMDocument)
        properties = set(properties, 11, isCCLDocument)
        properties = set(properties, 12, isMobileOptimize)
        properties = set(properties, 13, isSecurityPersonalPrivacy)
        properties = set(properties, 14, hasTrackChange)
        properties = set(properties, 15, hasKOGLCopyright)
        properties = set(properties, 16, hasVideoControl)
        properties = set(properties, 17, hasOrderFieldControl)
        sw.writeDWord(properties)
        sw.writeDWord(koglCCLInfo.getValue())
        sw.writeDWord(encryptVersionType.value)
        sw.writeByte(koglLicenceSupportCountry.value.toShort())
    }

    sw.writeZero(207)
}