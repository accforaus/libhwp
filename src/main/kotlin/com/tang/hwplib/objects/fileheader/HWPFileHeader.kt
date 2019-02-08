package com.tang.hwplib.objects.fileheader

/**
 * 파일 인식 정보를 나타내는 객체
 * 한글의 문서 파일이라는 것을 나타내기 위해 사용
 *
 * @author accforaus
 *
 * @property [version] [HWPFileVersion], 파일 버전 (DWORD) (unsigned 4 bytes)
 * @property [compressed] [Boolean], 압축 여부 (bit 00)
 * @property [hasPassword] [Boolean], 암호 설정 여부 (bit 01)
 * @property [isDeploymentDocument] [Boolean], 배포용 문서 여부 (bit 02)
 * @property [saveScript] [Boolean], 스크립트 저장 여부  (bit 03)
 * @property [isDRMDocument] [Boolean], DRM 무서 보안 여부 (bit 04)
 * @property [hasXMLTemplate] [Boolean], XMLTemplate 스토리지 존재 여부 (bit 05)
 * @property [hasDocumentHistory] [Boolean], 문서 이력 관리 존재 여부 (bit 06)
 * @property [hasSignature] [Boolean], 전자 서명 정보 존재 여부 (bit 07)
 * @property [encryptPublicCertification] [Boolean], 공인 인증서 암호화 여부 (bit 08)
 * @property [savePrepareSignature] [Boolean], 전자 서명 예비 저장 여부 (bit 09)
 * @property [isPublicCertificationDRMDocument] [Boolean], 공인 인증서 DRM 보안 문서 여부 (bit 10)
 * @property [isCCLDocument] [Boolean], CCL 문서 여부 (bit 11)
 * @property [isMobileOptimize] [Boolean], 모바일 최적화 여부 (bit 12)
 * @property [isSecurityPersonalPrivacy] [Boolean], 개인 정보 보안 문서 여부 (bit 13)
 * @property [hasTrackChange] [Boolean], 변경 추적 문서 여부 (bit 14)
 * @property [hasKOGLCopyright] [Boolean], 공공누리(KOGL) 저작권 문서 (bit 15)
 * @property [hasVideoControl] [Boolean], 비디오 컨트롤 포함 여부 (bit 16)
 * @property [hasOrderFieldControl] [Boolean], 차례 필드 컨트롤 포함 여부 (bit 17)
 *
 * @property [koglCCLInfo] [HWPKOGLCCLInfo], CCL, 공공누리 라이센스 정보 등,(DWORD - unsigned 4 bytes)
 * @property [encryptVersionType] [HWPEncryptVersionType], Encrypt Version 정보 (DWORD - unsigned 4 bytes)
 * @property [koglLicenceSupportCountry] [HWPKOGLLicenceSupportCountry], 공공누리(KOGL) 라이센스 지원 국가 (BYTE - unsigned 1 byte)
 */
class HWPFileHeader {
    var version: HWPFileVersion = HWPFileVersion()
    var compressed: Boolean = false
    var hasPassword: Boolean = false
    var isDeploymentDocument: Boolean = false
    var saveScript: Boolean = false
    var isDRMDocument: Boolean = false
    var hasXMLTemplate: Boolean = false
    var hasDocumentHistory: Boolean = false
    var hasSignature: Boolean = false
    var encryptPublicCertification: Boolean = false
    var savePrepareSignature: Boolean = false
    var isPublicCertificationDRMDocument: Boolean = false
    var isCCLDocument: Boolean = false
    var isMobileOptimize: Boolean = false
    var isSecurityPersonalPrivacy: Boolean = false
    var hasTrackChange: Boolean =false
    var hasKOGLCopyright: Boolean = false
    var hasVideoControl: Boolean = false
    var hasOrderFieldControl: Boolean = false
    var koglCCLInfo: HWPKOGLCCLInfo = HWPKOGLCCLInfo()
    var encryptVersionType: HWPEncryptVersionType = HWPEncryptVersionType.None
    var koglLicenceSupportCountry: HWPKOGLLicenceSupportCountry = HWPKOGLLicenceSupportCountry.KOR

    /**
     * HWP 문서의 signature을 반환하는 함수
     * signature은 파일이 HWP 파일인지 여부를 체크하는데 사용된
     * 문서 파일은 "HWP Document FILE"
     * BYTE array[32] - [ByteArray]
     *
     * return [ByteArray], HWP 문서의 signature
     */
    companion object {
        fun getFileSignature() = byteArrayOf(
                0x48, 0x57, 0x50, 0x20, 0x44, 0x6f, 0x63, 0x75, 0x6d,
                0x65, 0x6e, 0x74, 0x20, 0x46, 0x69, 0x6c, 0x65, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00)
    }
}