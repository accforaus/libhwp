package com.tang.hwplib.copyto.docinfo

import com.tang.hwplib.copyto.docinfo.facename.*

class HWPFaceNameCopier {
    private val baseNames: ArrayList<HWPBaseFaceName> = ArrayList()

    constructor(docInfoCopier: HWPDocInfoCopier) {
        baseNames.run {
            add(FaceNameHangul(docInfoCopier))
            add(FaceNameEnglish(docInfoCopier))
            add(FaceNameHanja(docInfoCopier))
            add(FaceNameJapanese(docInfoCopier))
            add(FaceNameEtc(docInfoCopier))
            add(FaceNameSymbol(docInfoCopier))
            add(FaceNameUser(docInfoCopier))
        }
    }

    fun proceed(ids: IntArray) : IntArray {
        val targetIds: IntArray = IntArray(7)
        for (index in 0 until targetIds.size)
            targetIds[index] = baseNames[index].proceed(ids[index])
        return targetIds
    }
}