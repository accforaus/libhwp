package com.tang.hwplib.copyto.docinfo

import com.tang.hwplib.objects.docinfo.numbering.HWPParagraphHeadInfo

class HWPParagraphHeadInfoCopier(private val docInfo: HWPDocInfoCopier) {
    fun copy(original: HWPParagraphHeadInfo) : HWPParagraphHeadInfo = original.copy().apply {
        if (this.charShapeID >= 0)
            this.charShapeID = docInfo.charShapeCopier.proceed(this.charShapeID.toInt()).toLong()
    }

    fun has(target: HWPParagraphHeadInfo, original: HWPParagraphHeadInfo): Boolean {
        target.run {
            original.let {
                if (distanceFromBody != it.distanceFromBody ||
                        correctionValueForWidth != it.correctionValueForWidth ||
                        property.value != it.property.value) return false
            }
        }
        return true
    }
}