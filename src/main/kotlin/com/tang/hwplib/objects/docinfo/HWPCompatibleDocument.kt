package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.objects.docinfo.compatibledocument.HWPCompatibleDocumentSort
import com.tang.hwplib.objects.etc.COMPATIBLE_DOCUMENT
/**
 * 호환 문서를 나타내는 객체
 * Tag ID: HWPTAG_COMPATIBLE_DOCUMENT [COMPATIBLE_DOCUMENT]
 * 4 bytes
 *
 * @author accforaus
 *
 * @property [compatibleDocumentSort] [HWPCompatibleDocumentSort], 대상 프로그램 (UINT32 - unsigned 4 bytes)
 */
class HWPCompatibleDocument {
    var compatibleDocumentSort: HWPCompatibleDocumentSort? = null
}