package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.objects.docinfo.HWPStyle
import com.tang.hwplib.objects.docinfo.style.HWPStyleProperty

internal fun buildEmptyStyle() : ArrayList<HWPStyle> {
    val styleList: ArrayList<HWPStyle> = ArrayList()

    styleList.run {
        add(HWPStyle.build(
                hangulName = "바탕글",
                englishName = "Normal",
                property = HWPStyleProperty.build(value = 0),
                languageId = 1042,
                paraShapeId = 3
        ))
        add(HWPStyle.build(
                hangulName = "본문",
                englishName = "Body",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 1,
                languageId = 1042,
                paraShapeId = 11
        ))
        add(HWPStyle.build(
                hangulName = "개요 1",
                englishName = "Outline 1",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 2,
                languageId = 1042,
                paraShapeId = 10
        ))
        add(HWPStyle.build(
                hangulName = "개요 2",
                englishName = "Outline 2",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 3,
                languageId = 1042,
                paraShapeId = 9
        ))
        add(HWPStyle.build(
                hangulName = "개요 3",
                englishName = "Outline 3",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 4,
                languageId = 1042,
                paraShapeId = 8
        ))
        add(HWPStyle.build(
                hangulName = "개요 4",
                englishName = "Outline 4",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 5,
                languageId = 1042,
                paraShapeId = 7
        ))
        add(HWPStyle.build(
                hangulName = "개요 5",
                englishName = "Outline 5",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 6,
                languageId = 1042,
                paraShapeId = 6
        ))
        add(HWPStyle.build(
                hangulName = "개요 6",
                englishName = "Outline 6",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 7,
                languageId = 1042,
                paraShapeId = 5
        ))
        add(HWPStyle.build(
                hangulName = "개요 7",
                englishName = "Outline 7",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 8,
                languageId = 1042,
                paraShapeId = 4
        ))
        add(HWPStyle.build(
                hangulName = "쪽 번호",
                englishName = "Page Number",
                property = HWPStyleProperty.build(value = 1),
                languageId = 1042,
                charShapeId = 1
        ))
        add(HWPStyle.build(
                hangulName = "머리말",
                englishName = "Header",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 10,
                languageId = 1042,
                paraShapeId = 2,
                charShapeId = 2
        ))
        add(HWPStyle.build(
                hangulName = "각주",
                englishName = "Footnote",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 11,
                languageId = 1042,
                paraShapeId = 1,
                charShapeId = 3
        ))
        add(HWPStyle.build(
                hangulName = "미주",
                englishName = "Endnote",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 12,
                languageId = 1042,
                paraShapeId = 1,
                charShapeId = 3
        ))
        add(HWPStyle.build(
                hangulName = "메모",
                englishName = "Memo",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 13,
                languageId = 1042,
                paraShapeId = 0,
                charShapeId = 4
        ))
        add(HWPStyle.build(
                hangulName = "차례 제목",
                englishName = "TOC Heading",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 14,
                languageId = 1042,
                paraShapeId = 12,
                charShapeId = 5
        ))
        add(HWPStyle.build(
                hangulName = "차례 1",
                englishName = "TOC 1",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 15,
                languageId = 1042,
                paraShapeId = 13,
                charShapeId = 6
        ))
        add(HWPStyle.build(
                hangulName = "차례 2",
                englishName = "TOC 2",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 16,
                languageId = 1042,
                paraShapeId = 14,
                charShapeId = 6
        ))
        add(HWPStyle.build(
                hangulName = "차례 3",
                englishName = "TOC 3",
                property = HWPStyleProperty.build(value = 0),
                nextStyleId = 17,
                languageId = 1042,
                paraShapeId = 15,
                charShapeId = 6
        ))
    }

    return styleList
}