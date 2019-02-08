package com.tang.hwplib.writer.autosetter.control.gso

import com.tang.hwplib.objects.bodytext.control.gso.*
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentContainer
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPTextBox
import com.tang.hwplib.writer.autosetter.InstanceID
import com.tang.hwplib.writer.autosetter.autoSetParagraphList
import com.tang.hwplib.writer.autosetter.control.gso.pack.autoSetCaption
import com.tang.hwplib.writer.autosetter.control.gso.pack.autoSetCtrlHeaderGso

/**
 * Gso 컨트롤 [HWPGsoControl]의 자동 설정을 수행하는 함수
 *
 * @author accforaus
 *
 * @param [gsoControl] [HWPGsoControl], Gso 컨트롤 객체
 * @param [iid] [InstanceID], 고유한 ID
 */
fun autoSetGsoControl(gsoControl: HWPGsoControl, iid: InstanceID) {
    /**
     * 묶음 개체 [HWPControlContainer]의 자동 설정을 수행하는 함수
     *
     * @param [container] [HWPControlContainer], 묶음 개체 객체
     */
    fun autoSetControlContainer(container: HWPControlContainer) {
        val scc: HWPShapeComponentContainer = container.shapeComponent as HWPShapeComponentContainer
        scc.childControlIdList.clear()
        for (child in container.childControlList) {
            scc.addChildControlId(child.getGsoId()!!)
            autoSetGsoControl(child, iid)
        }
    }

    /**
     * 그리기 개체 글상자용 텍스트 정보 [HWPTextBox]의 자동 설정을 수행하는 함수
     *
     * @param [textBox] [HWPTextBox], 그리기 개체 글상자용 텍스트 정보 객체
     */
    fun textBox(textBox: HWPTextBox?) {
        textBox?.run {
            listHeader.paraCount = paragraphList.getParagraphCount()
            autoSetParagraphList(paragraphList, iid)
        }
    }

    gsoControl.run {
        autoSetCtrlHeaderGso(getHeader(), iid)
        autoSetCaption(caption, iid)

        when (this) {
            is HWPControlArc -> textBox(textBox)
            is HWPControlContainer -> autoSetControlContainer(this)
            is HWPControlCurve -> textBox(textBox)
            is HWPControlEllipse -> textBox(textBox)
            is HWPControlLine -> {}
            is HWPControlOLE -> {}
            is HWPControlPicture -> {}
            is HWPControlPolygon -> textBox(textBox)
            is HWPControlRectangle -> textBox(textBox)
        }
    }

}
