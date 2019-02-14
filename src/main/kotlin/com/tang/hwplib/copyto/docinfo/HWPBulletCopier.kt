package com.tang.hwplib.copyto.docinfo

import com.tang.hwplib.objects.docinfo.HWPBullet
import com.tang.hwplib.objects.docinfo.numbering.HWPParagraphHeadInfo

class HWPBulletCopier(private val docInfo: HWPDocInfoCopier) {
    fun proceed(id: Int) : Int {
        var temp: Int = id
        if (temp > 0) temp -= 1
        val targetIndex = findByID(temp)
        if (targetIndex == -1) {
            val original: HWPBullet = docInfo.originalDocInfo.bulletList[temp]
            val target: HWPBullet = original.copy().apply {
                paragraphHeadInfo = docInfo.paragraphHeaderInfoCopier.copy(original.paragraphHeadInfo)
            }
            docInfo.targetDocInfo.bulletList.add(target)
            docInfo.updateIDMappings(IDMappingTypes.BULLET)
            return docInfo.targetDocInfo.bulletList.size
        }
        docInfo.targetDocInfo.bulletList[targetIndex].run {
            val originalParaHeadInfo: HWPParagraphHeadInfo = docInfo.originalDocInfo.bulletList[id].paragraphHeadInfo
            this.paragraphHeadInfo = docInfo.paragraphHeaderInfoCopier.copy(originalParaHeadInfo)
        }
        return targetIndex + 1
    }

    private fun findByID(id: Int) : Int {
        val original: HWPBullet = docInfo.originalDocInfo.bulletList[id]
        for ((index, target) in docInfo.targetDocInfo.bulletList.withIndex()) {
            if (has(target, original)) return index
        }
        return -1
    }

    private fun has(target: HWPBullet, original: HWPBullet) : Boolean {
        target.run {
            original.let {
                if (this.bulletChar != it.bulletChar) return false
                if (imageBullet.brightness != it.imageBullet.brightness ||
                        imageBullet.contrast != it.imageBullet.contrast ||
                        imageBullet.effects != it.imageBullet.effects ||
                        imageBullet.id != it.imageBullet.id) return false
                if (checkBulletChar != it.checkBulletChar) return false
                if (!docInfo.paragraphHeaderInfoCopier.has(this.paragraphHeadInfo, it.paragraphHeadInfo)) return false
                if (this.imageBulletCheck != it.imageBulletCheck) return false
            }
        }
        return true
    }
}