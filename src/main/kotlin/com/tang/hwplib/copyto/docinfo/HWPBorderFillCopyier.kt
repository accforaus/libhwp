package com.tang.hwplib.copyto.docinfo

import com.tang.hwplib.objects.docinfo.HWPBorderFill
import com.tang.hwplib.objects.docinfo.borderfill.HWPEachBorder

class HWPBorderFillCopyier(private val docInfo: HWPDocInfoCopier) {
    fun proceed(id: Int) : Int {
        var tempId: Int = id
        if (tempId > 0) tempId -= 1
        else return tempId
        val targetIndex: Int = findByID(tempId)
        if (targetIndex == -1) {
            val original: HWPBorderFill = docInfo.originalDocInfo.borderFillList[tempId]
            val target: HWPBorderFill = original.copy().apply {
                fillInfo = docInfo.fillInfoCopier.copy(original.fillInfo)
            }
            docInfo.targetDocInfo.borderFillList.add(target)
            docInfo.updateIDMappings(IDMappingTypes.BORDERFILL)
            return docInfo.targetDocInfo.borderFillList.size
        }
        return targetIndex
    }

    private fun findByID(id: Int): Int {
        val original: HWPBorderFill = docInfo.originalDocInfo.borderFillList[id]
        for ((index, target) in docInfo.targetDocInfo.borderFillList.withIndex()) {
            if (hasBorderFill(target, original))
                return index + 1
        }
        return -1
    }

    private fun hasBorderFill(target: HWPBorderFill, original: HWPBorderFill) : Boolean {
        fun hasEachBorder(target: HWPEachBorder, source: HWPEachBorder): Boolean {
            if(target.color.value != source.color.value ||
                    target.type.value != source.type.value ||
                    target.thickness.value != source.thickness.value) return false
            return true
        }
        if (target.property.value != original.property.value) return false
        if (!hasEachBorder(target = target.topBorder, source = original.topBorder) ||
                !hasEachBorder(target = target.rightBorder, source = original.rightBorder) ||
                !hasEachBorder(target = target.bottomBorder, source = original.bottomBorder) ||
                !hasEachBorder(target = target.leftBorder, source = original.leftBorder)) return false
        if (target.diagonalSort.value != original.diagonalSort.value ||
                target.diagonalThickness.value != original.diagonalThickness.value ||
                target.diagonalColor.value != original.diagonalColor.value) return false

        if (!docInfo.fillInfoCopier.hasFillInfo(target = target.fillInfo, source = original.fillInfo)) return false
        return true
    }
}
