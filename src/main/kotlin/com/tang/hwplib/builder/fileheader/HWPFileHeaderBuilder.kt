package com.tang.hwplib.builder.fileheader

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.HWPDocument
import com.tang.hwplib.objects.fileheader.HWPEncryptVersionType
import com.tang.hwplib.objects.fileheader.HWPFileHeader
import com.tang.hwplib.objects.fileheader.HWPFileVersion
import com.tang.hwplib.objects.fileheader.HWPKOGLLicenceSupportCountry

class HWPFileHeaderBuilder : HWPBuilder<HWPFileHeader> {
    private val header : HWPFileHeader = HWPFileHeader.build()

    fun setVersion(versionBuilder: HWPFileVersionBuilder) : HWPFileHeaderBuilder = this.apply {
        header.version = versionBuilder.build()
    }

    fun setCompressed(compress: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.compressed = compress
    }

    fun setHasPassword(password: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.hasPassword = password
    }

    fun setIsDeploymentDocument(isDeploymentDocument: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.isDeploymentDocument = isDeploymentDocument
    }

    fun setSaveScript(saveScript: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.saveScript = saveScript
    }

    fun setIsDRMDocument(isDRMDocument: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.isDRMDocument = isDRMDocument
    }

    fun setHasXMLTemplate(hasXMLTemplate: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.hasXMLTemplate = hasXMLTemplate
    }

    fun setHasDocumentHistory(hasDocumentHistory: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.hasDocumentHistory = hasDocumentHistory
    }

    fun setHasSignature(hasSignature: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.hasSignature = hasSignature
    }

    fun setEncryptPublicCertification(encryptPublicCertification: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.encryptPublicCertification = encryptPublicCertification
    }

    fun setSavePrepareSignature(savePrepareSignature: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.savePrepareSignature = savePrepareSignature
    }

    fun setIsPublicCertificationDRMDocument(isPublicCertificationDRMDocument: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.isPublicCertificationDRMDocument = isPublicCertificationDRMDocument
    }

    fun setIsCCLDocument(isCCLDocument: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.isCCLDocument = isCCLDocument
    }

    fun setIsMobileOptimize(isMobileOptimize: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.isMobileOptimize = isMobileOptimize
    }

    fun setIsSecurityPersonalPrivacy(isSecurityPersonalPrivacy: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.isSecurityPersonalPrivacy = isSecurityPersonalPrivacy
    }

    fun setHasTrackChange(hasTrackChange: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.hasTrackChange = hasTrackChange
    }

    fun setHasKOGLCopyright(hasKOGLCopyright: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.hasKOGLCopyright = hasKOGLCopyright
    }

    fun setHasVideoControl(hasVideoControl: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.hasVideoControl = hasVideoControl
    }

    fun setHasOrderFieldControl(hasOrderFieldControl: Boolean) : HWPFileHeaderBuilder = this.apply {
        header.hasOrderFieldControl = hasOrderFieldControl
    }

    fun setKoglCCLInfo(koglCclInfoBuilder: HWPKOGLCCLInfoBuilder) : HWPFileHeaderBuilder = this.apply {
        header.koglCCLInfo = koglCclInfoBuilder.build()
    }

    fun setEncryptVersionType(encryptVersionType: HWPEncryptVersionType) : HWPFileHeaderBuilder = this.apply {
        header.encryptVersionType = encryptVersionType
    }

    fun setKoglLicenceSupportCountry(koglHWPKOGLLicenceSupportCountry: HWPKOGLLicenceSupportCountry) : HWPFileHeaderBuilder = this.apply {
        header.koglLicenceSupportCountry = koglHWPKOGLLicenceSupportCountry
    }

    override fun build(): HWPFileHeader = header
}

/**
 * HWP 빈 문서의 파일 헤더를 생성하는 함수
 *
 * @author accforaus
 *
 * @param [mm] [Short], MM 문서 형식의 구조가 완전히 바뀌는 것
 * @param [nn] [Short], nn 큰 변화가 있는 것
 * @param [pp] [Short], Record 추가 된 것
 * @param [rr] [Short], 정보들이 추가 된 것
 *
 * @return [HWPFileHeader] 빈 문서의 파일 헤더 반환
 */
internal fun buildEmptyFileHeader(): HWPFileHeader = HWPFileHeader.build(
        version = HWPFileVersion.build(5,0,5,0),
        compressed = true,
        encryptVersionType = HWPEncryptVersionType.MoreHWP70,
        koglLicenceSupportCountry =  HWPKOGLLicenceSupportCountry.KOR
)