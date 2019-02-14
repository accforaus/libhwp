package com.tang.hwplib.copyto.bodytext.paragraph.control

import com.tang.hwplib.copyto.bodytext.paragraph.copyParagraphList
import com.tang.hwplib.copyto.docinfo.HWPDocInfoCopier
import com.tang.hwplib.objects.bodytext.control.*
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPCtrlData
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterItem
import com.tang.hwplib.objects.bodytext.control.gso.*
import com.tang.hwplib.objects.bodytext.control.gso.caption.HWPCaption
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.HWPShapeComponentNormal
import com.tang.hwplib.objects.bodytext.control.gso.textbox.HWPTextBox

private fun setBinDataInCtrlData(ctrlData: HWPCtrlData?, docInfo: HWPDocInfoCopier) {
    fun setParameterArray(parameterArray : Array<HWPParameterItem>?) {
        parameterArray?.run {
            for (value in this) {
                if (value.value_binData >= 0)
                    value.value_binData = docInfo.proceedBinData(value.value_binData)
                setParameterArray(value.value_ParameterArray)
            }
        }
    }
    ctrlData?.run {
        for (parameterItem in this.parameterSet.parameterItemList) {
            if(parameterItem.value_binData >= 0)
                parameterItem.value_binData = docInfo.proceedBinData(parameterItem.value_binData)
            setParameterArray(parameterItem.value_ParameterArray)
        }
    }
}

private fun copyParagraphInTextBox(target: HWPTextBox?, original: HWPTextBox?, docInfo: HWPDocInfoCopier) {
    original?.run {
        copyParagraphList(target!!.paragraphList, this.paragraphList, docInfo)
    }
}

private fun copyParagraphInCaption(target: HWPCaption?, original: HWPCaption?, docInfo: HWPDocInfoCopier) {
    original?.run {
        copyParagraphList(target!!.paragraphList, this.paragraphList, docInfo)
    }
}

fun copyControls(original: HWPControl, docInfo: HWPDocInfoCopier) : HWPControl? = when (original) {
    is HWPControlField -> original.copy().apply {
        setBinDataInCtrlData(this.getCtrlData(), docInfo)
    }
    is HWPControlAdditionalText -> original.copy().apply {
        getHeader().apply {
            this.styleId = docInfo.styleCopier.proceed(this.styleId.toInt()).toLong()
        }
        setBinDataInCtrlData(this.getCtrlData(), docInfo)
    }
    is HWPControlAutoNumber -> original.copy().apply {
        setBinDataInCtrlData(this.getCtrlData(), docInfo)
    }
    is HWPControlColumnDefine -> original.copy().apply {
        setBinDataInCtrlData(this.getCtrlData(), docInfo)
    }
    is HWPControlEndNote -> original.copy().apply {
        setBinDataInCtrlData(this.getCtrlData(), docInfo)
        copyParagraphList(this.paragraphList, original.paragraphList, docInfo)
    }
    is HWPControlFootnote -> original.copy().apply {
        setBinDataInCtrlData(this.getCtrlData(), docInfo)
        copyParagraphList(this.paragraphList, original.paragraphList, docInfo)
    }
    is HWPControlHeader -> original.copy().apply {
        setBinDataInCtrlData(this.getCtrlData(), docInfo)
        copyParagraphList(this.paragraphList, original.paragraphList, docInfo)
    }
    is HWPControlFooter -> original.copy().apply {
        setBinDataInCtrlData(this.getCtrlData(), docInfo)
        copyParagraphList(this.paragraphList, original.paragraphList, docInfo)
    }
    is HWPControlEquation -> original.copy().apply {
        setBinDataInCtrlData(this.getCtrlData(), docInfo)
    }
    is HWPControlHiddenComment -> original.copy().apply {
        setBinDataInCtrlData(this.getCtrlData(), docInfo)
        copyParagraphList(this.paragraphList, original.paragraphList, docInfo)
    }
    is HWPControlIndexMark -> original.copy().apply {
        setBinDataInCtrlData(this.getCtrlData(), docInfo)
    }
    is HWPControlNewNumber -> original.copy()
    is HWPControlOverlappingLetter -> original.copy().apply {
        getHeader().apply {
            for (index in 0 until this.charShapeIdList.size)
                charShapeIdList[index] = docInfo.charShapeCopier.proceed(charShapeIdList[index].toInt()).toLong()
        }
        setBinDataInCtrlData(this.getCtrlData(), docInfo)
    }
    is HWPControlPageHide -> original.copy().apply { setBinDataInCtrlData(this.getCtrlData(), docInfo) }
    is HWPControlPageNumberPosition -> original.copy().apply { setBinDataInCtrlData(this.getCtrlData(), docInfo) }
    is HWPControlPageOddEvenAdjust -> original.copy().apply { setBinDataInCtrlData(this.getCtrlData(), docInfo) }
    is HWPControlSectionDefine -> original.copy().apply {
        bothPageBorderFill.run {
            borderFillId = docInfo.borderFillCopier.proceed(borderFillId)
        }
        evenPageBorderFill.run {
            borderFillId = docInfo.borderFillCopier.proceed(borderFillId)
        }
        oddPageBorderFill.run {
            borderFillId = docInfo.borderFillCopier.proceed(borderFillId)
        }
        for ((index, batangPageInfo) in batangPageInfoList.withIndex())
            copyParagraphList(batangPageInfo.paragraphList, original.batangPageInfoList[index].paragraphList, docInfo)

        setBinDataInCtrlData(this.getCtrlData(), docInfo)
    }
    is HWPControlTable -> original.copy().apply {
        table.run {
            borderFillId = docInfo.borderFillCopier.proceed(borderFillId)
            for (zoneInfo in zoneInfoList)
                zoneInfo.borderFillId = docInfo.borderFillCopier.proceed(zoneInfo.borderFillId)
        }
        for ((rowIndex, row) in rowList.withIndex()) {
            for ((cellIndex, cell) in row.cellList.withIndex()) {
                cell.listHeader.borderFillId = docInfo.borderFillCopier.proceed(cell.listHeader.borderFillId)
                copyParagraphList(cell.paragraphList, original.rowList[rowIndex].cellList[cellIndex].paragraphList, docInfo)
            }
        }
        setBinDataInCtrlData(this.getCtrlData(), docInfo)
    }
    is HWPGsoControl -> copyGsoControl(original, docInfo).apply {
        this?.run {
            setBinDataInCtrlData(this.getCtrlData(), docInfo)
            copyParagraphInCaption(this.caption, original.caption, docInfo)
        }
    }
    else -> null
}

private fun copyGsoControl(gsoControl: HWPGsoControl, docInfo: HWPDocInfoCopier): HWPGsoControl? = when (gsoControl) {
    is HWPControlArc -> gsoControl.copy().apply {
        setShapeComponentNormal(shapeComponent as HWPShapeComponentNormal, docInfo)
        copyParagraphInTextBox(this.textBox, gsoControl.textBox, docInfo)
    }
    is HWPControlCurve -> gsoControl.copy().apply {
        setShapeComponentNormal(shapeComponent as HWPShapeComponentNormal, docInfo)
        copyParagraphInTextBox(this.textBox, gsoControl.textBox, docInfo)
    }
    is HWPControlEllipse -> gsoControl.copy().apply {
        setShapeComponentNormal(shapeComponent as HWPShapeComponentNormal, docInfo)
        copyParagraphInTextBox(this.textBox, gsoControl.textBox, docInfo)
    }
    is HWPControlLine -> gsoControl.copy().apply { setShapeComponentNormal(shapeComponent as HWPShapeComponentNormal, docInfo) }
    is HWPControlObjectLinkLine -> gsoControl.copy().apply { setShapeComponentNormal(shapeComponent as HWPShapeComponentNormal, docInfo) }
    is HWPControlOLE -> gsoControl.copy().apply {
        setShapeComponentNormal(shapeComponent as HWPShapeComponentNormal, docInfo)
        shapeComponentOLE.run {
            binDataId = docInfo.proceedBinData(binDataId)
        }
    }
    is HWPControlPicture -> gsoControl.copy().apply {
        setShapeComponentNormal(shapeComponent as HWPShapeComponentNormal, docInfo)
        shapeComponentPicture.run {
            pictureInfo.run {
                binItemID = docInfo.proceedBinData(binItemID)
            }
        }
    }
    is HWPControlPolygon -> gsoControl.copy().apply {
        setShapeComponentNormal(shapeComponent as HWPShapeComponentNormal, docInfo)
        copyParagraphInTextBox(this.textBox, gsoControl.textBox, docInfo)
    }
    is HWPControlRectangle -> gsoControl.copy().apply {
        setShapeComponentNormal(shapeComponent as HWPShapeComponentNormal, docInfo)
        copyParagraphInTextBox(this.textBox, gsoControl.textBox, docInfo)
    }
    else -> null
}

private fun setShapeComponentNormal(shapeComponent: HWPShapeComponentNormal, docInfo: HWPDocInfoCopier) {
    if (shapeComponent.fillInfo != null) {
        shapeComponent.fillInfo = docInfo.fillInfoCopier.copy(shapeComponent.fillInfo!!)
    }
}