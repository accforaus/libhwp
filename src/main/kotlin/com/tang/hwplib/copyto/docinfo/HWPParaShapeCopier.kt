package com.tang.hwplib.copyto.docinfo

import com.tang.hwplib.objects.docinfo.HWPParaShape
import com.tang.hwplib.objects.docinfo.parashape.HWPParaHeadShape

class HWPParaShapeCopier(private val docInfo: HWPDocInfoCopier) {
    fun proceed(id: Int) : Int {
        val targetIndex: Int = findByID(id)
        if (targetIndex == -1) {
            val original: HWPParaShape = docInfo.originalDocInfo.paraShapeList[id]
            val target: HWPParaShape = original.copy().apply {
                this.tabDefId = docInfo.tabDefCopier.proceed(this.tabDefId)
                this.paraHeadId = getParaHeadIndex(original)
                this.borderFillId = docInfo.borderFillCopier.proceed(this.borderFillId)
            }
            docInfo.targetDocInfo.paraShapeList.add(target)
            docInfo.updateIDMappings(IDMappingTypes.PARASHAPE)
            return docInfo.targetDocInfo.paraShapeList.size - 1
        }
        return targetIndex
    }

    private fun findByID(id :Int) : Int {
        val original: HWPParaShape = docInfo.originalDocInfo.paraShapeList[id]
        for ((index, target) in docInfo.targetDocInfo.paraShapeList.withIndex()) {
            if (has(target, original)) return index
        }
        return -1
    }

    private fun getParaHeadIndex(original: HWPParaShape) : Int = when (original.property1.getParaHeadShape()) {
        HWPParaHeadShape.None, HWPParaHeadShape.Outline -> original.paraHeadId
        HWPParaHeadShape.Numbering -> docInfo.numberingCopier.proceed(original.paraHeadId)
        HWPParaHeadShape.Bullet -> docInfo.bulletCopier.proceed(original.paraHeadId)
    }
    private fun has(target: HWPParaShape, original: HWPParaShape) : Boolean {
        target.run {
            original.let {
                if (this.bottomBorderSpace != it.bottomBorderSpace ||
                        this.indent != it.indent ||
                        this.leftBorderSpace != it.leftBorderSpace ||
                        this.bottomParaSpace != it.bottomParaSpace ||
                        this.leftMargin != it.leftMargin ||
                        this.lineSpace != it.lineSpace ||
                        this.lineSpace2 != it.lineSpace2 ||
                        this.rightBorderSpace != it.rightBorderSpace ||
                        this.rightMargin != it.rightMargin ||
                        this.topBorderSpace != it.topBorderSpace ||
                        this.topParaSpace != it.topParaSpace ||
                        this.property1.value != it.property1.value ||
                        this.property2.value != it.property2.value ||
                        this.property3.value != it.property3.value) return false
                return true
            }
        }
    }
}