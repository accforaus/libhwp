package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.objects.docinfo.HWPParaShape
import com.tang.hwplib.objects.docinfo.parashape.HWPParaShapeProperty1
import com.tang.hwplib.objects.docinfo.parashape.HWPParaShapeProperty2
import com.tang.hwplib.objects.docinfo.parashape.HWPParaShapeProperty3

internal fun buildEmptyParaShape() : ArrayList<HWPParaShape> {
    val paraShapeList: ArrayList<HWPParaShape> = ArrayList()
    paraShapeList.run {
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(260),
                lineSpace = 130,
                borderFillId = 2,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 130
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(384),
                indent = -2620,
                lineSpace = 130,
                borderFillId = 2,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 130
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(256),
                lineSpace = 150,
                borderFillId = 2,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 150
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(384),
                lineSpace = 160,
                borderFillId = 2,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 160
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(209725824),
                leftMargin = 14000,
                lineSpace = 160,
                tabDefId = 1,
                borderFillId = 2,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 160
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(176171392),
                leftMargin = 12000,
                lineSpace = 160,
                tabDefId = 1,
                borderFillId = 2,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 160
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(142616960),
                leftMargin = 10000,
                lineSpace = 160,
                tabDefId = 1,
                borderFillId = 2,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 160
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(109062528),
                leftMargin = 8000,
                lineSpace = 160,
                tabDefId = 1,
                borderFillId = 2,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 160
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(75508096),
                leftMargin = 6000,
                lineSpace = 160,
                borderFillId = 2,
                tabDefId = 1,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 160
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(419653664),
                leftMargin = 4000,
                lineSpace = 160,
                borderFillId = 2,
                tabDefId = 1,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 160
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(8399232),
                leftMargin = 2000,
                lineSpace = 160,
                borderFillId = 2,
                tabDefId = 1,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 160
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(384),
                leftMargin = 3000,
                lineSpace = 160,
                borderFillId = 2,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 160
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(10500),
                topParaSpace = 2400,
                bottomParaSpace = 600,
                lineSpace = 160,
                borderFillId = 2,
                tabDefId = 1,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 160
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(260),
                bottomParaSpace = 1400,
                lineSpace = 160,
                borderFillId = 2,
                tabDefId = 2,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 160
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(260),
                leftMargin = 2200,
                bottomParaSpace = 1400,
                lineSpace = 160,
                borderFillId = 2,
                tabDefId = 2,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 160
        ))
        add(HWPParaShape.build(
                property1 = HWPParaShapeProperty1.build(260),
                leftMargin = 4400,
                bottomParaSpace = 1400,
                lineSpace = 160,
                borderFillId = 2,
                tabDefId = 2,
                property2 = HWPParaShapeProperty2.build(0),
                property3 = HWPParaShapeProperty3.build(value = 0),
                lineSpace2 = 160
        ))
    }
    return paraShapeList
}