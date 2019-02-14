package com.tang.hwplib.copyto.docinfo

import com.tang.hwplib.objects.docinfo.HWPCharShape

class HWPCharShapeCopier(private val docInfo: HWPDocInfoCopier) {
    fun proceed(id: Int) : Int {
        val targetIndex = findByID(id)
        if (targetIndex == -1) {
            val original: HWPCharShape = docInfo.originalDocInfo.charShapeList[id]
            val target: HWPCharShape = original.copy().apply {
                faceNameIds.array = docInfo.faceNameCopier.proceed(original.faceNameIds.array).copyOf()
                this.borderFillId = docInfo.borderFillCopier.proceed(this.borderFillId)
            }
            docInfo.targetDocInfo.charShapeList.add(target)
            docInfo.updateIDMappings(IDMappingTypes.CHARSHAPE)
            return docInfo.targetDocInfo.charShapeList.size - 1
        }
        return targetIndex
    }

    private fun findByID(id: Int): Int {
        val original: HWPCharShape = docInfo.originalDocInfo.charShapeList[id]
        for ((index, target) in docInfo.targetDocInfo.charShapeList.withIndex()) {
            if (hasCharShape(target, original)) return index
        }
        return -1
    }
    private fun hasCharShape(target: HWPCharShape, original: HWPCharShape) : Boolean {
        fun checkArray(target: IntArray, original: IntArray): Boolean {
            for (i in 0 until target.size)
                if (target[i] != original[i])
                    return false
            return true
        }

        fun checkArray(target: ShortArray, original: ShortArray): Boolean {
            for (i in 0 until target.size)
                if (target[i] != original[i])
                    return false
            return true
        }

        fun checkArray(target: ByteArray, original: ByteArray): Boolean {
            for (i in 0 until target.size)
                if (target[i] != original[i])
                    return false
            return true
        }

        target.run {
            original.let {
                if (baseSize != it.baseSize ||
                        shadowGap1 != it.shadowGap1 ||
                        shadowGap2 != it.shadowGap2) return false
                if (!checkArray(charOffsets.array, it.charOffsets.array) ||
                        !checkArray(ratios.array, it.ratios.array) ||
                        !checkArray(charSpaces.array, it.charSpaces.array) ||
                        !checkArray(relativeSizes.array, it.relativeSizes.array)) return false
                if (property.value != it.property.value) return false
                if (underLineColor.value != it.underLineColor.value ||
                        shadeColor.value != it.shadeColor.value ||
                        shadowColor.value != it.shadowColor.value ||
                        charColor.value != it.charColor.value ||
                        strikeLineColor.value != it.strikeLineColor.value) return false
            }
        }
        return true
    }
}