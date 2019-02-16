package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControl

class HWPControlBuilder : HWPBuilder<HWPControl> {
    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder, control: HWPControl) {
        control.setCtrlData(ctrlDataBuilder.build())
    }
    override fun build(): HWPControl = HWPControl(null)
}