package com.tang.hwplib.objects

import com.tang.hwplib.builder.fileheader.buildEmptyFileHeader
import com.tang.hwplib.builder.template.emptydocument.HWPEmptyDocumentTemplate
import com.tang.hwplib.builder.template.emptydocument.bodytext.HWPEmptyBodyTextBuilder
import com.tang.hwplib.builder.template.emptydocument.docinfo.HWPEmptyDocInfoBuilder
import com.tang.hwplib.builder.template.emptydocument.fileheader.HWPEmptyFileHeaderBuilder
import com.tang.hwplib.copyto.HWPDocumentCopyTo
import com.tang.hwplib.copyto.appendParagraph
import com.tang.hwplib.objects.bindata.HWPBinData
import com.tang.hwplib.objects.bodytext.HWPBodyText
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.fileheader.HWPFileHeader
import com.tang.hwplib.reader.fromFile
import com.tang.hwplib.reader.fromURL
import com.tang.hwplib.writer.toHWPFile
import java.io.File
import java.net.URL

/**
 * HWP 문서를 나타내는 객체
 *
 * @author accforaus
 *
 * @constructor 빈 문서를 만든다.
 * @constructor 로컬 파일을 읽는다
 * @constructor URL 경로 파일을 읽는다
 *
 * @property [fileHeader] [HWPFileHeader], 파일 헤더
 * @property [docInfo] [HWPDocInfo], 문서 정보
 * @property [bodyText] [HWPBodyText], 본문
 * @property [binData] [HWPBinData], 바이너리 데이터
 */
open class HWPDocument {
    var filename: String
    var fileHeader: HWPFileHeader = HWPFileHeader()
    var docInfo: HWPDocInfo = HWPDocInfo()
    var bodyText: HWPBodyText = HWPBodyText()
    var binData: HWPBinData = HWPBinData()

    constructor() {
        this.fileHeader = HWPEmptyFileHeaderBuilder().build()
        this.docInfo = HWPEmptyDocInfoBuilder().build()
        this.bodyText = HWPEmptyBodyTextBuilder(docInfo).build()
        filename = ""
    }

    constructor(path: String) {
        fromFile(path).let {
            this.fileHeader = it.fileHeader
            this.docInfo = it.docInfo
            this.bodyText = it.bodyText
            this.binData = it.binData
            filename = path
        }
    }

    constructor(url: URL) {
        fromURL(url).let {
            this.fileHeader = it.fileHeader
            this.docInfo = it.docInfo
            this.bodyText = it.bodyText
            this.binData = it.binData
            filename = url.path
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

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPDocument] 복사된 객체 반환
     */
    fun copy() : HWPDocument = HWPDocument().also {
        it.fileHeader = this.fileHeader.copy()
        it.docInfo = this.docInfo.copy()
        it.bodyText = this.bodyText.copy()
        it.binData = this.binData.copy()
    }

    /**
     * 문단 (Paragraph)을 추가하는 함수
     *
     * @param [paragraph] [HWPParagraph], 추가 할 문단
     * @param [originalHWP] [HWPDocument], 추가 할 문단을 가진 문서
     */
    fun addParagraph(paragraph: HWPParagraph, originalHWP: HWPDocument) {
        appendParagraph(paragraph, this, originalHWP)
    }

    /**
     * 문단 (Paragraph) 리스트를 추가하는 함수
     *
     * @param [paragraphs] [ArrayList], 추가 할 문단 리스트
     * @param [originalHWP] [HWPDocument], 추가 할 문단을 가진 문서
     */
    fun addParagraphs(paragraphs: ArrayList<HWPParagraph>, originalHWP: HWPDocument) {
        for (paragraph in paragraphs)
            addParagraph(paragraph, originalHWP)
    }

    /**
     * 문서를 이어 붙히는 함수
     *
     * @param [hwpDocument] [HWPDocument] 이어 붙힐 문서
     * @return [HWPDocument] 이어 붙혀진 문서
     */
    operator fun plus(hwpDocument: HWPDocument) : HWPDocument {
        HWPDocumentCopyTo(this, hwpDocument)
        return hwpDocument
    }

    fun toFile() : File = File(filename)
}