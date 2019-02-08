package com.tang.hwplib.writer.autosetter.control

import com.tang.hwplib.objects.bodytext.control.*
import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControl
import com.tang.hwplib.writer.autosetter.InstanceID
import com.tang.hwplib.writer.autosetter.autoSetParagraphList
import com.tang.hwplib.writer.autosetter.control.gso.autoSetGsoControl
import com.tang.hwplib.writer.autosetter.control.gso.pack.autoSetCaption
import com.tang.hwplib.writer.autosetter.control.gso.pack.autoSetCtrlHeaderGso

/**
 * 컨트롤 개체 [HWPControl]의 자동 설정을 수행하는 함수
 *
 * @author accforaus
 *
 * @param [control] [HWPControl], 컨트롤 개체 객체
 * @param [iid] [InstanceID], 고유한 ID
 */
fun autoSetControl(control: HWPControl, iid: InstanceID) {
    /**
     * 단 정의 [HWPControlColumnDefine]의 자동 설정을 수행하는 함수
     *
     * @param [cd] [HWPControlColumnDefine], 단 정의 객체
     */
    fun autoSetControlColumnDefine(cd: HWPControlColumnDefine) {
        cd.getHeader().run {
            if (columnInfoList.size > 0)
                property.setColumnCount(columnInfoList.size.toShort())
            else
                property.setColumnCount(property.getColumnCount())
        }
    }

    /**
     * 미주 [HWPControlEndNote]의 자동 설정을 수행하는 함수
     *
     * @param [en] [HWPControlEndNote], 미주 객체
     */
    fun autoSetControlEndnote(en: HWPControlEndNote) {
        en.run {
            listHeader.paraCount = paragraphList.getParagraphCount()
            autoSetParagraphList(paragraphList, iid)
        }
    }

    /**
     * 한글 수식 개체 [HWPControlEquation]의 자동 설정을 수행하는 함수
     *
     * @param [eq] [HWPControlEquation], 한글 수식 개체 객체
     */
    fun autoSetControlEquation(eq: HWPControlEquation) {
        eq.run {
            autoSetCtrlHeaderGso(getHeader(), iid)
            autoSetCaption(caption, iid)
        }
    }

    /**
     * 필드 시작 [HWPControlField]의 자동 설정을 수행하는 함수
     *
     * @param [field] [HWPControlField], 필드 시작 개체
     */
    fun autoSetControlField(field: HWPControlField) {
        field.getHeader().run {
            instanceId = iid.get()
        }
    }

    /**
     * 꼬리말 [HWPControlFooter]의 자동 설정을 수행하는 함수
     *
     * @param [footer] [HWPControlFooter], 꼬리말 객체
     */
    fun autoSetControlFooter(footer: HWPControlFooter) {
        footer.listHeader.run {
            paraCount = footer.paragraphList.getParagraphCount()
        }
        autoSetParagraphList(footer.paragraphList, iid)
    }

    /**
     * 각주 [HWPControlFootnote]의 자동 설정을 수행하는 함수
     *
     * @param [fn] [HWPControlFootnote], 각주 객체
     */
    fun autoSetControlFootnote(fn: HWPControlFootnote) {
        fn.run {
            getHeader().instanceId = iid.get()
            listHeader.paraCount = paragraphList.getParagraphCount()
            autoSetParagraphList(paragraphList, iid)
        }
    }

    /**
     * 머리말 [HWPControlHeader]의 자동 설정을 수행하는 함수
     *
     * @param [fh] [HWPControlHeader], 머리말 객체
     */
    fun autoSetControlHeader(fh: HWPControlHeader) {
        fh.run {
            listHeader.paraCount = paragraphList.getParagraphCount()
            autoSetParagraphList(paragraphList, iid)
        }
    }

    /**
     * 숨은 설명 [HWPControlHiddenComment]의 자동 설정을 수행하는 함수
     *
     * @param [hc] [HWPControlHiddenComment], 숨은 설명 객체
     */
    fun autoSetControlHiddenComment(hc: HWPControlHiddenComment) {
        hc.run {
            listHeader.paraCount = paragraphList.getParagraphCount()
            autoSetParagraphList(paragraphList, iid)
        }
    }

    /**
     * 구역 정의 [HWPControlSectionDefine]의 자동 설정을 수행하는 함수
     *
     * @param [sd] [HWPControlSectionDefine], 구역 정의 객체
     */
    fun autoSetControlSectionDefine(sd: HWPControlSectionDefine) {
        sd.run {
            for (bpi in batangPageInfoList) {
                bpi.listHeader.paraCount = bpi.paragraphList.getParagraphCount()
                autoSetParagraphList(bpi.paragraphList, iid)
            }
        }
    }

    /**
     * 표 개체 [HWPControlTable]의 자동 설정을 수행하는 함수
     *
     * @param [table] [HWPControlTable], 표 개체 객체
     */
    fun autoSetControlTable(table: HWPControlTable) {
        autoSetCtrlHeaderGso(table.getHeader(), iid)
        autoSetCaption(table.caption, iid)
        table.table.run {
            rowCount = table.rowList.size
            cellCountOfRowList.clear()
            for (row in table.rowList)
                cellCountOfRowList.add(row.cellList.size)
        }

        table.run {
            for (row in rowList) {
                for (cell in row.cellList) {
                    cell.listHeader.paraCount = cell.paragraphList.getParagraphCount()
                    autoSetParagraphList(cell.paragraphList, iid)
                }
            }
        }
    }

    if (control.isField()) autoSetControlField(control as HWPControlField)
    when (control) {
        is HWPControlAdditionalText -> {}
        is HWPControlAutoNumber -> {}
        is HWPControlBookmark -> {}
        is HWPControlColumnDefine -> autoSetControlColumnDefine(control)
        is HWPControlEndNote -> autoSetControlEndnote(control)
        is HWPControlEquation -> autoSetControlEquation(control)
        is HWPControlFooter -> autoSetControlFooter(control)
        is HWPControlFootnote -> autoSetControlFootnote(control)
        is HWPGsoControl -> autoSetGsoControl(control, iid)
        is HWPControlHeader -> autoSetControlHeader(control)
        is HWPControlHiddenComment -> autoSetControlHiddenComment(control)
        is HWPControlIndexMark -> {}
        is HWPControlNewNumber -> {}
        is HWPControlOverlappingLetter -> {}
        is HWPControlPageHide -> {}
        is HWPControlPageNumberPosition -> {}
        is HWPControlSectionDefine -> autoSetControlSectionDefine(control)
        is HWPControlTable -> autoSetControlTable(control)
    }
}