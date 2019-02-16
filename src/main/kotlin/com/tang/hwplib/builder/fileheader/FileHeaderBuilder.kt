package com.tang.hwplib.builder.fileheader

import com.tang.hwplib.objects.HWPDocument
import com.tang.hwplib.objects.fileheader.HWPEncryptVersionType
import com.tang.hwplib.objects.fileheader.HWPFileHeader
import com.tang.hwplib.objects.fileheader.HWPFileVersion
import com.tang.hwplib.objects.fileheader.HWPKOGLLicenceSupportCountry

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