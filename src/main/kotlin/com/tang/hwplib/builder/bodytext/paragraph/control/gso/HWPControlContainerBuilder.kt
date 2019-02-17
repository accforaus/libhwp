package com.tang.hwplib.builder.bodytext.paragraph.control.gso

import com.tang.hwplib.builder.bodytext.paragraph.control.HWPControlEquationBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.HWPCtrlDataBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.ctrlheader.HWPCtrlHeaderGsoBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPCaptionBuilder
import com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent.HWPShapeComponentContainerBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.interfaces.HWPControlBuilder
import com.tang.hwplib.builder.interfaces.HWPGsoControlBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.control.gso.HWPControlContainer
import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControl

class HWPControlContainerBuilder : HWPGsoControlBuilder<HWPControlContainer> {
    private val control: HWPControlContainer = HWPControlContainer.build()

    fun setHeader(headerBuilder: HWPCtrlHeaderGsoBuilder) : HWPControlContainerBuilder = this.apply {
        control.header = headerBuilder.build().apply {
            ctrlId = HWPControlType.Gso.ctrlId
        }
    }

    fun setChildControlList(childControlListBuilder: ChildControlListBuilder) : HWPControlContainerBuilder = this.apply {
        control.childControlList = childControlListBuilder.build()
    }

    fun setShapeComponent(shapeComponentBuilder: HWPShapeComponentContainerBuilder) : HWPControlContainerBuilder = this.apply {
        control.shapeComponent = shapeComponentBuilder.build()
    }

    fun setCaption(captionBuilder: HWPCaptionBuilder) : HWPControlContainerBuilder = this.apply {
        control.caption = captionBuilder.build()
    }

    fun setCtrlData(ctrlDataBuilder: HWPCtrlDataBuilder) : HWPControlContainerBuilder = this.apply {
        control.setCtrlData(ctrlDataBuilder.build())
    }

    override fun build(): HWPControlContainer = control
}

class ChildControlListBuilder : HWPBuilder<ArrayList<HWPGsoControl>> {
    private val controlList : ArrayList<HWPGsoControl> = ArrayList()

    fun addChildControl(controlBuilder : HWPGsoControlBuilder<HWPGsoControl>) : ChildControlListBuilder = this.apply {
        controlList.add(controlBuilder.build())
    }

    override fun build(): ArrayList<HWPGsoControl> = controlList
}