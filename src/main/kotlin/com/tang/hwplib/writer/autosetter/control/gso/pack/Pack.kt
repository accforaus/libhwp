package com.tang.hwplib.writer.autosetter.control.gso.pack

import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderGso
import com.tang.hwplib.objects.bodytext.control.gso.caption.HWPCaption
import com.tang.hwplib.writer.autosetter.InstanceID
import com.tang.hwplib.writer.autosetter.autoSetParagraphList

/**
 * 개체 공통 요소 [HWPCtrlHeaderGso]의 자동 설정을 수행하는 함수
 *
 * @author accforaus
 *
 * @param [header] [HWPCtrlHeaderGso], 개체 공통 요소 객체
 * @param [iid] [InstanceID], 고유한 ID
 */
fun autoSetCtrlHeaderGso(header: HWPCtrlHeaderGso?, iid: InstanceID) {
    header?.run { instanceId = iid.get() }
}

/**
 * 캡션 [HWPCaption]의 자동 설정을 수행하는 함수
 *
 * @author accforaus
 *
 * @param [caption] [HWPCaption], 캡션 객체
 * @param [iid] [InstanceID], 고유한 ID
 */
fun autoSetCaption(caption: HWPCaption?, iid: InstanceID) {
    caption?.run {
        listHeaderForCaption.paraCount = paragraphList.getParagraphCount()
        autoSetParagraphList(paragraphList, iid)
    }
}