package com.tang.hwplib.builder.bodytext.paragraph.control

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPCtrlData
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterItem
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterSet
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterType

class HWPCtrlDataBuilder : HWPBuilder<HWPCtrlData> {
    private val ctrlData: HWPCtrlData = HWPCtrlData()

    fun setParameterSet(parameterSetBuilder: HWPParameterSetBuilder) : HWPCtrlDataBuilder = this.apply {
        ctrlData.parameterSet = parameterSetBuilder.build()
    }

    override fun build(): HWPCtrlData = ctrlData
}

class HWPParameterSetBuilder: HWPBuilder<HWPParameterSet> {
    private val parameterSet: HWPParameterSet = HWPParameterSet.build()

    fun addParamterItem(parameterItemBuilder: HWPParameterItemBuilder) : HWPParameterSetBuilder = this.apply {
        parameterSet.parameterItemList.add(parameterItemBuilder.build())
    }

    override fun build(): HWPParameterSet = parameterSet
}

class HWPParameterItemBuilder : HWPBuilder<HWPParameterItem> {
    private val parameterItem: HWPParameterItem = HWPParameterItem.build()

    fun setId(id: Long) : HWPParameterItemBuilder = this.apply {
        parameterItem.id = id
    }

    fun setType(type: HWPParameterType?) : HWPParameterItemBuilder = this.apply {
        parameterItem.type = type
    }

    fun setValue_BSTR(value_BSTR: String?) : HWPParameterItemBuilder = this.apply {
        parameterItem.value_BSTR = value_BSTR
    }

    fun setValue_I1(value_I1: Byte) : HWPParameterItemBuilder = this.apply {
        parameterItem.value_I1 = value_I1
    }

    fun setValue_I2(value_I2: Short) : HWPParameterItemBuilder = this.apply {
        parameterItem.value_I2 = value_I2
    }

    fun setValue_I4(value_I4: Int) : HWPParameterItemBuilder = this.apply {
        parameterItem.value_I4 = value_I4
    }

    fun setValue_I(value_I: Int) : HWPParameterItemBuilder = this.apply {
        parameterItem.value_I = value_I
    }

    fun setValue_UI1(value_UI1: Short) : HWPParameterItemBuilder = this.apply {
        parameterItem.value_UI1 = value_UI1
    }

    fun setValue_UI2(value_UI2: Int) : HWPParameterItemBuilder = this.apply {
        parameterItem.value_UI2 = value_UI2
    }

    fun setValue_UI4(value_UI4: Long) : HWPParameterItemBuilder = this.apply {
        parameterItem.value_UI4 = value_UI4
    }

    fun setValue_UI(value_UI: Long) : HWPParameterItemBuilder = this.apply {
        parameterItem.value_UI = value_UI
    }

    fun setValue_ParameterSet(parameterSetBuilder: HWPParameterSetBuilder) : HWPParameterItemBuilder = this.apply {
        parameterItem.value_ParameterSet = parameterSetBuilder.build()
    }

    fun setValue_ParameterArray(parameterArrayBuilder: HWPValueParameterArrayBuilder) : HWPParameterItemBuilder = this.apply {
        parameterItem.value_ParameterArray = parameterArrayBuilder.build()
    }

    fun setValue_BinDataID(binDataID: Int) : HWPParameterItemBuilder = this.apply {
        parameterItem.value_binData = binDataID
    }

    override fun build(): HWPParameterItem = parameterItem
}

class HWPValueParameterArrayBuilder(size: Int) : HWPBuilder<Array<HWPParameterItem>> {
    private val parameterArray: ArrayList<HWPParameterItem> = ArrayList()

    fun addParameterItem(parameterItemBuilder: HWPParameterItemBuilder) : HWPValueParameterArrayBuilder = this.apply {
        parameterArray.add(parameterItemBuilder.build())
    }

    override fun build(): Array<HWPParameterItem> = parameterArray.toTypedArray()
}