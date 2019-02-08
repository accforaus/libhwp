package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataProperty
import com.tang.hwplib.objects.etc.BIN_DATA

/**
 * 바이너리 데이터 (가변)
 * Tag ID: HWPTAG_BIN_DATA [BIN_DATA]
 * 그림, OLE 등의 바이너리 데이터 아이템에 대한 정보
 *
 * @author accforaus
 *
 * @property [property] [HWPBinDataProperty], 속성 (UINT16 - unsigned 2 bytes)
 * @property [absolutePathForLink] [String], Type이 "LINK"일 때, 연결 파일의 절대 경로 (WCHAR array - unsigned 4 bytes)
 * @property [relativePathForLink] [String], Type이 "LINK"일 때, 연결 파일의 상대 경로 (WCHAR array - unsigned 4 bytes)
 * @property [binDataID] [Int], Type이 "EMBEDDING"이거나 "STORAGE"일 때, BINDATASTORAGE에 저장된 바이너리 데이터 아이디 (UINT16 - unsigned 2 bytes)
 * @property [extensionForEmbedding] [String], Tyype이 EMBEDDING일 때 extension("." 제외) 그림(jpg, bmp, gif) OLE(ole) (WCHAR array - unsigned 4 bytes)
 */
class HWPBinData {
    var property: HWPBinDataProperty = HWPBinDataProperty()
    var absolutePathForLink: String? = null
    var relativePathForLink: String? = null
    var binDataID: Int = 0
    var extensionForEmbedding: String? = null
}