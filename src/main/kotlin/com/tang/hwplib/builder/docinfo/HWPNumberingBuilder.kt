package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.objects.docinfo.HWPNumbering
import com.tang.hwplib.objects.docinfo.numbering.HWPLevelNumbering
import com.tang.hwplib.objects.docinfo.numbering.HWPParagraphHeadInfo
import com.tang.hwplib.objects.docinfo.numbering.HWPParagraphHeadInfoProperty

internal fun buildEmptyNumbering() : ArrayList<HWPNumbering> {
    fun getLongArray(size: Int, init: Int) : LongArray {
        val array: LongArray = LongArray(7)
        for ((index, value) in array.withIndex())
            array[index] = value
        return array
    }
    val numberingList: ArrayList<HWPNumbering> = ArrayList()

    numberingList.add(HWPNumbering.build(
            levelNumberingGenerator = {
                val levelNumberingArray: ArrayList<HWPLevelNumbering> = ArrayList()
                levelNumberingArray.run {
                    add(HWPLevelNumbering.build(
                            paragraphHeadInfo = HWPParagraphHeadInfo.build(
                                    property = HWPParagraphHeadInfoProperty.build(12),
                                    correctionValueForWidth = 0,
                                    distanceFromBody = 50,
                                    charShapeID = -1
                            ),
                            numberFormat = "^1."
                    ))
                    add(HWPLevelNumbering.build(
                            paragraphHeadInfo = HWPParagraphHeadInfo.build(
                                    property = HWPParagraphHeadInfoProperty.build(268),
                                    correctionValueForWidth = 0,
                                    distanceFromBody = 50,
                                    charShapeID = -1
                            ),
                            numberFormat = "^2."
                    ))
                    add(HWPLevelNumbering.build(
                            paragraphHeadInfo = HWPParagraphHeadInfo.build(
                                    property = HWPParagraphHeadInfoProperty.build(12),
                                    correctionValueForWidth = 0,
                                    distanceFromBody = 50,
                                    charShapeID = -1
                            ),
                            numberFormat = "^3)"
                    ))
                    add(HWPLevelNumbering.build(
                            paragraphHeadInfo = HWPParagraphHeadInfo.build(
                                    property = HWPParagraphHeadInfoProperty.build(268),
                                    correctionValueForWidth = 0,
                                    distanceFromBody = 50,
                                    charShapeID = -1
                            ),
                            numberFormat = "^4)"
                    ))
                    add(HWPLevelNumbering.build(
                            paragraphHeadInfo = HWPParagraphHeadInfo.build(
                                    property = HWPParagraphHeadInfoProperty.build(12),
                                    correctionValueForWidth = 0,
                                    distanceFromBody = 50,
                                    charShapeID = -1
                            ),
                            numberFormat = "(^5)"
                    ))
                    add(HWPLevelNumbering.build(
                            paragraphHeadInfo = HWPParagraphHeadInfo.build(
                                    property = HWPParagraphHeadInfoProperty.build(268),
                                    correctionValueForWidth = 0,
                                    distanceFromBody = 50,
                                    charShapeID = -1
                            ),
                            numberFormat = "(^6)"
                    ))
                    add(HWPLevelNumbering.build(
                            paragraphHeadInfo = HWPParagraphHeadInfo.build(
                                    property = HWPParagraphHeadInfoProperty.build(44),
                                    correctionValueForWidth = 0,
                                    distanceFromBody = 50,
                                    charShapeID = -1
                            ),
                            numberFormat = "^7"
                    ))
                }
                levelNumberingArray
            },
            startNumber = 0,
            startNumberForLevel = getLongArray(7, 1)
    ))

    return numberingList
}