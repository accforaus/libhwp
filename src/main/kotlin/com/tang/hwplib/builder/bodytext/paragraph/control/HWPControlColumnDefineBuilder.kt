package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.objects.bodytext.control.HWPControlColumnDefine
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderColumnDefine
import com.tang.hwplib.objects.bodytext.control.ctrlheader.columndefine.HWPColumnDefineHeaderProperty
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderThickness
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType

internal fun buildEmptyColumnDefine() : HWPControlColumnDefine = HWPControlColumnDefine.build().apply {
    header = HWPCtrlHeaderColumnDefine.build(
            property = HWPColumnDefineHeaderProperty.build(4100),
            divideLineSort = HWPBorderType.Solid,
            divideLineThickness = HWPBorderThickness.MM0_1
    )
    setCtrlData(null)
}