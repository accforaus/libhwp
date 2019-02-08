package com.tang.hwplib.objects

import com.tang.hwplib.objects.bindata.HWPBinData
import com.tang.hwplib.objects.bodytext.HWPBodyText
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.fileheader.HWPFileHeader
import com.tang.hwplib.reader.fromFile
import com.tang.hwplib.reader.fromURL
import com.tang.hwplib.writer.toHWPFile
import java.net.URL

/**
 * HWP 문서를 나타내는 객체
 *
 * @author accforaus
 *
 * @constructor 로컬 파일을 읽는다
 * @constructor URL 경로 파일을 읽는다
 *
 * @property [fileHeader] [HWPFileHeader], 파일 헤더
 * @property [docInfo] [HWPDocInfo], 문서 정보
 * @property [bodyText] [HWPBodyText], 본문
 * @property [binData] [HWPBinData], 바이너리 데이터
 */
class HWPDocument {
    var fileHeader: HWPFileHeader = HWPFileHeader()
    var docInfo: HWPDocInfo = HWPDocInfo()
    var bodyText: HWPBodyText = HWPBodyText()
    var binData: HWPBinData = HWPBinData()

    constructor()

    constructor(path: String) {
        fromFile(path).let {
            this.fileHeader = it.fileHeader
            this.docInfo = it.docInfo
            this.bodyText = it.bodyText
            this.binData = it.binData
        }
    }

    constructor(url: URL) {
        fromURL(url).let {
            this.fileHeader = it.fileHeader
            this.docInfo = it.docInfo
            this.bodyText = it.bodyText
            this.binData = it.binData
        }
    }

    /**
     * HWP 문서를 지정된 경로로 저장하는 함수
     *
     * @param [path] [String], 저장 경로
     */
    fun write(path: String) {
        toHWPFile(this, path)
    }
}