package com.tang.hwplib.builder.docinfo.tabdef

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.objects.docinfo.tabdef.HWPTabInfo
import com.tang.hwplib.objects.docinfo.tabdef.HWPTabSort

class HWPTabInfoBuilder : HWPBuilder<HWPTabInfo> {
    private val tabInfo: HWPTabInfo = HWPTabInfo.build()

    fun setPosition(position: Long) : HWPTabInfoBuilder = this.apply {
        tabInfo.position = position
    }

    fun setTabSort(tabSort: HWPTabSort?) : HWPTabInfoBuilder = this.apply {
        tabInfo.tabSort = tabSort
    }

    fun setFillSort(fillSort: HWPBorderType?) : HWPTabInfoBuilder = this.apply {
        tabInfo.fillSort = fillSort
    }

    override fun build(): HWPTabInfo = tabInfo
}