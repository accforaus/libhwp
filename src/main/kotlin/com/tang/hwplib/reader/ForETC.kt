package com.tang.hwplib.reader

import com.tang.hwplib.objects.etc.UnknownRecord
import com.tang.hwplib.objects.fileheader.*
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.exceptions.HWPReadException
import com.tang.hwplib.reader.util.StreamReader
import java.util.*

/**
 * 파일 헤더 [HWPFileHeader]를 읽는 함수
 *
 * @author accforaus
 *
 * @param [fh] [HWPFileHeader], 빈 파일 헤더 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forFileHeader(fh: HWPFileHeader, sr: StreamReader) {
    /**
     * signature를 읽는 함수
     */
    fun signature() {
        val sign: ByteArray = ByteArray(32)
        sr.readBytes(sign)

        if (!Arrays.equals(HWPFileHeader.getFileSignature(), sign))
            throw HWPReadException("This is not hwp File")
    }

    /**
     * 파일 버전 [HWPFileVersion]을 읽는 함수
     *
     * @param [fv] [HWPFileVersion], 빈 파일 버전 객체
     */
    fun fileVersion(fv: HWPFileVersion): Unit = fv.setVersion(sr.readDWord())

    /**
     * 공공 누리 정보 [HWPKOGLCCLInfo]을 읽는 함수
     *
     * @param [koglcclInfo] [HWPKOGLCCLInfo], 빈 공공 누리 정보 객체
     */
    fun koglCclInfo(koglcclInfo: HWPKOGLCCLInfo) {
        koglcclInfo.value = sr.readDWord()
    }

    /**
     * 속성을 읽는 함수
     *
     * @param [fh] [HWPFileHeader], 파일 헤더 객체
     */
    fun properties(fh: HWPFileHeader): Unit = fh.run {
        val flag: Long = sr.readDWord()
        compressed = get(flag, 0)
        hasPassword = get(flag, 1)
        isDeploymentDocument = get(flag, 2)
        saveScript = get(flag, 3)
        isDRMDocument = get(flag, 4)
        hasXMLTemplate = get(flag, 5)
        hasDocumentHistory = get(flag, 6)
        hasSignature = get(flag, 7)
        encryptPublicCertification = get(flag, 8)
        savePrepareSignature = get(flag, 9)
        isPublicCertificationDRMDocument = get(flag, 10)
        isCCLDocument = get(flag, 11)
        isMobileOptimize = get(flag, 12)
        isSecurityPersonalPrivacy = get(flag, 13)
        hasTrackChange = get(flag, 14)
        hasKOGLCopyright = get(flag, 15)
        hasVideoControl = get(flag, 16)
        hasOrderFieldControl = get(flag, 17)
    }

    signature()
    fileVersion(fh.version)
    properties(fh)
    koglCclInfo(fh.koglCCLInfo)
    fh.encryptVersionType = HWPEncryptVersionType.valueOf(sr.readDWord())
    fh.koglLicenceSupportCountry = HWPKOGLLicenceSupportCountry.valueOf(sr.readByte().toByte())
}

/**
 * 알려지지 않은 레코드 [UnknownRecord]를 읽는 개체
 *
 * @author accforaus
 *
 * @param [unknown] [UnknownRecord], 빈 알려지지 않은 레코드 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forUnknown(unknown: UnknownRecord, sr: StreamReader) {
    val body: ByteArray = ByteArray(unknown.header!!.size.toInt())
    sr.readBytes(body)
    unknown.body = body
}