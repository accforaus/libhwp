package com.tang.hwplib.builder.fileheader

import com.tang.hwplib.objects.HWPDocument
import com.tang.hwplib.objects.fileheader.HWPEncryptVersionType
import com.tang.hwplib.objects.fileheader.HWPFileHeader
import com.tang.hwplib.objects.fileheader.HWPFileVersion

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
internal fun buildEmptyFileHeader(mm: Short = 5, nn: Short = 0, pp :Short = 5, rr :Short = 0): HWPFileHeader = HWPFileHeader().apply {
    version.run {
        this.mm = mm
        this.nn = nn
        this.pp = pp
        this.rr = rr
    }
    compressed = true
    encryptVersionType = HWPEncryptVersionType.MoreHWP70
}