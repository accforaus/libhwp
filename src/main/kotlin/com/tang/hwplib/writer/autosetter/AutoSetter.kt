package com.tang.hwplib.writer.autosetter

import com.tang.hwplib.objects.HWPDocument

/**
 * HWP 문서 저장을 도와주는 자동 설정 함수
 *
 * @author accforaus
 *
 * @param [hwpDocument] [HWPDocument], HWP 문서 객체
 * @param [instanceID] [InstanceID], 고유한 ID
 */
fun autoSetter(hwpDocument: HWPDocument, instanceID: InstanceID) {
    autoSetDocInfo(hwpDocument.docInfo, hwpDocument.bodyText)
    for (s in hwpDocument.bodyText.sectionList)
        autoSetParagraphList(s, instanceID)
}