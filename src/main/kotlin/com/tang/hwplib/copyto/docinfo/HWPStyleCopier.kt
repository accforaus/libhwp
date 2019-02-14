package com.tang.hwplib.copyto.docinfo

import com.tang.hwplib.objects.docinfo.HWPStyle

class HWPStyleCopier(private val docInfo: HWPDocInfoCopier) {
    fun proceed(id: Int) : Int {
        val targetIndex: Int = findByID(id)
        if (targetIndex == -1) {
            val original: HWPStyle = docInfo.originalDocInfo.styleList[id]
            val target: HWPStyle = original.copy().apply {
                this.nextStyleId = docInfo.targetDocInfo.styleList.size.toShort()
                this.paraShapeId = docInfo.paraShapeCopier.proceed(this.paraShapeId)
                this.charShapeId = docInfo.charShapeCopier.proceed(this.charShapeId)
            }
            docInfo.targetDocInfo.styleList.add(target)
            docInfo.updateIDMappings(IDMappingTypes.STYLE)
            return docInfo.targetDocInfo.styleList.size - 1
        }
        return targetIndex
    }

    private fun findByID(id: Int) : Int {
        val original: HWPStyle = docInfo.originalDocInfo.styleList[id]
        for ((index, target) in docInfo.targetDocInfo.styleList.withIndex()) {
            if (has(target, original)) return index
        }
        return -1
    }

    private fun has(target: HWPStyle, original: HWPStyle) : Boolean {
        if (target.englishName == null) {
            if (original.englishName != null) return false
        } else {
            if (original.englishName == null) return false
            if (target.englishName!! != original.englishName!!) return false
        }

        if (target.hangulName == null) {
            if (original.hangulName != null) return false
        } else {
            if (original.hangulName == null) return false
            if (target.hangulName!! != original.hangulName!!) return false
        }

        if (target.languageId != original.languageId ||
                target.property.value != original.property.value) return false
        return true
    }
}