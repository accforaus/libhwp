package com.tang.hwplib.copyto.docinfo

import com.tang.hwplib.objects.docinfo.HWPTabDef
import com.tang.hwplib.objects.docinfo.tabdef.HWPTabInfo

class HWPTabDefCopier(private val docInfo: HWPDocInfoCopier) {
    fun proceed(id: Int) : Int {
        val targetIndex: Int = findByID(id)
        if (targetIndex == -1) {
            val original: HWPTabDef = docInfo.originalDocInfo.tabDefList[id]
            val target: HWPTabDef = original.copy()
            docInfo.targetDocInfo.tabDefList.add(target)
            docInfo.updateIDMappings(IDMappingTypes.TABDEF)
            return docInfo.targetDocInfo.tabDefList.size - 1
        }
        return targetIndex
    }

    private fun findByID(id: Int) : Int {
        val original: HWPTabDef = docInfo.originalDocInfo.tabDefList[id]
        for ((index, target) in docInfo.targetDocInfo.tabDefList.withIndex()) {
            if (has(target, original)) return index
        }
        return -1
    }

    private fun has(target: HWPTabDef, original: HWPTabDef) : Boolean {
        fun hasTabInfo(target: HWPTabInfo, original: HWPTabInfo) : Boolean {
            if (target.position != original.position) return false
            if (target.fillSort == null) {
                if (original.fillSort != null) return false
            } else {
                if (original.fillSort == null) return false
                if (target.fillSort!!.value != original.fillSort!!.value) return false
            }

            if (target.tabSort == null) {
                if (original.tabSort != null) return false
            } else {
                if (original.tabSort == null) return false
                if (target.tabSort!!.value != original.tabSort!!.value) return false
            }
            return true
        }

        if (target.property.value != original.property.value) return false
        if (target.tabInfoList.size != original.tabInfoList.size) return false
        for ((index, value) in target.tabInfoList.withIndex()) {
            if (!hasTabInfo(value, original.tabInfoList[index])) return false
        }
        return true
    }
}