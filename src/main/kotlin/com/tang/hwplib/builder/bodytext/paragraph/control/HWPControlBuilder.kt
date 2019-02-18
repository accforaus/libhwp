package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControl
import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControl

abstract class HWPControlBuilder : HWPBuilder<HWPControl> {
    override fun build(): HWPControl = HWPControl(null)
}

abstract class HWPGsoControlBuilder : HWPControlBuilder() {
    override fun build(): HWPGsoControl = HWPGsoControl()
}