package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControl

class HWPControlListBuilder : HWPBuilder<ArrayList<HWPControl>> {
    private val controlList : ArrayList<HWPControl> = ArrayList()

    fun addControl(controlBuilder : HWPControlBuilder) : HWPControlListBuilder = this.apply {
        controlList.add(controlBuilder.build())
    }

    override fun build(): ArrayList<HWPControl> = controlList
}