package com.tang.hwplib.copyto.docinfo

import com.tang.hwplib.objects.docinfo.HWPNumbering
import com.tang.hwplib.objects.docinfo.numbering.HWPExtendNumbering
import com.tang.hwplib.objects.docinfo.numbering.HWPLevelNumbering
import com.tang.hwplib.objects.docinfo.numbering.HWPParagraphHeadInfo

class HWPNumberingCopier(private val docInfo: HWPDocInfoCopier) {
    fun proceed(id: Int) : Int {
        var temp: Int = id
        if (temp > 0) temp -= 1
        val targetIndex: Int = findByID(temp)
        if (targetIndex == -1) {
            val original: HWPNumbering = docInfo.originalDocInfo.numberingList[temp]
            val target: HWPNumbering = original.copy().apply {
                for (level in 0 until levelNumberingList.size)
                    this.levelNumberingList[level].paragraphHeadInfo =
                            docInfo.paragraphHeaderInfoCopier.copy(original.levelNumberingList[level].paragraphHeadInfo)
            }
            docInfo.targetDocInfo.numberingList.add(target)
            docInfo.updateIDMappings(IDMappingTypes.NUMBERING)
            return docInfo.targetDocInfo.numberingList.size
        }
        val targetNumbering: HWPNumbering = docInfo.targetDocInfo.numberingList[targetIndex]
        for (level in 0 until targetNumbering.levelNumberingList.size) {
            val paragraphHeadInfo: HWPParagraphHeadInfo = docInfo.paragraphHeaderInfoCopier.copy(docInfo.originalDocInfo.numberingList[temp].levelNumberingList[level].paragraphHeadInfo)
            targetNumbering.levelNumberingList[level].paragraphHeadInfo = paragraphHeadInfo
        }
        return targetIndex + 1
    }

    private fun findByID(id: Int) : Int {
        val original: HWPNumbering = docInfo.originalDocInfo.numberingList[id]
        for ((index, target) in docInfo.targetDocInfo.numberingList.withIndex()) {
            if (has(target, original)) return index
        }
        return -1
    }

    private fun has(target: HWPNumbering, original: HWPNumbering) : Boolean {
        fun hasLevelNumbering(target: HWPLevelNumbering, original: HWPLevelNumbering) : Boolean {
            if (target.numberFormat == null) {
                if (original.numberFormat != null) return false
            } else {
                if (original.numberFormat == null) return false
                if (target.numberFormat!! != original.numberFormat!!) return false
            }
            if (!docInfo.paragraphHeaderInfoCopier.has(target.paragraphHeadInfo, original.paragraphHeadInfo)) return false
            return true
        }
        fun hasExtendNumbering(target: HWPExtendNumbering, original: HWPExtendNumbering) : Boolean {
            if (target.numberFormat == null) {
                if (original.numberFormat != null) return false
            } else {
                if (original.numberFormat == null) return false
                if (target.numberFormat!! != original.numberFormat!!) return false
            }
            return true
        }
        target.run {
            original.let {
                if (startNumber != it.startNumber) return false
                for (level in 1..7) {
                    if (!hasLevelNumbering(this.getLevelNumbering(level), it.getLevelNumbering(level))) return false
                    if (getStartNumberForLevel(level) != it.getStartNumberForLevel(level)) return false
                }
                for (level in 8..10) {
                    if (!hasExtendNumbering(getExtendStartLevelNumbering(level), it.getExtendStartLevelNumbering(level))) return false
                    if (getExtendStartNumberForLevel(level) != it.getExtendStartNumberForLevel(level)) return false
                }
            }
        }
        return true
    }
}