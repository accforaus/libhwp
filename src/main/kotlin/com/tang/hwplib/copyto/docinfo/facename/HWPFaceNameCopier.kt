package com.tang.hwplib.copyto.docinfo.facename

import com.tang.hwplib.copyto.docinfo.HWPDocInfoCopier
import com.tang.hwplib.copyto.docinfo.IDMappingTypes
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.HWPFaceName

abstract class HWPBaseFaceName(val targetFaceNames: ArrayList<HWPFaceName>, val originalFaceNames: ArrayList<HWPFaceName>) {
    abstract fun proceed(id: Int) : Int
    fun findByIndex(id: Int) : Int {
        val original: HWPFaceName = originalFaceNames[id]
        for ((index, target) in targetFaceNames.withIndex()) {
            if (has(target, original)) return index
        }
        return -1
    }

    fun has(target: HWPFaceName, original: HWPFaceName) : Boolean {
        target.run {
            original.let {
                if (name == null) {
                    if (it.name != null) return false
                } else {
                    if (it.name == null) return false
                    if (name!! != it.name!!) return false
                }

                if (baseFontName == null) {
                    if (it.baseFontName != null) return false
                } else {
                    if (it.baseFontName == null) return false
                    if (baseFontName!! != it.baseFontName!!) return false
                }

                if (substituteFontName == null) {
                    if (it.substituteFontName != null) return false
                } else {
                    if (it.substituteFontName == null) return false
                    if (substituteFontName!! != it.substituteFontName!!) return false
                }

                if (substituteFontType == null) {
                    if (it.substituteFontType != null) return false
                } else {
                    if (it.substituteFontType == null) return false
                    if (substituteFontType!!.value != it.substituteFontType!!.value) return false
                }

                if (property.value != it.property.value) return false

                return true
            }
        }
    }
}

class FaceNameEnglish(private val docInfoCopier: HWPDocInfoCopier)
    : HWPBaseFaceName(targetFaceNames = docInfoCopier.targetDocInfo.englishFaceNameList, originalFaceNames = docInfoCopier.originalDocInfo.englishFaceNameList) {
    override fun proceed(id: Int): Int {
        val targetIndex = findByIndex(id)
        if (targetIndex == -1) {
            val original: HWPFaceName = originalFaceNames[id]
            val target: HWPFaceName = original.copy()
            targetFaceNames.add(target)
            docInfoCopier.updateIDMappings(IDMappingTypes.ENGLISH_FACENAME)
            return targetFaceNames.size - 1
        }
        return targetIndex
    }
}

class FaceNameHangul(private val docInfoCopier: HWPDocInfoCopier)
    : HWPBaseFaceName(docInfoCopier.targetDocInfo.hangulFaceNameList, docInfoCopier.originalDocInfo.hangulFaceNameList) {
    override fun proceed(id: Int): Int {
        val targetIndex = findByIndex(id)
        if (targetIndex == -1) {
            val original: HWPFaceName = originalFaceNames[id]
            val target: HWPFaceName = original.copy()
            targetFaceNames.add(target)
            docInfoCopier.updateIDMappings(IDMappingTypes.HANGUL_FACENAME)
            return targetFaceNames.size - 1
        }
        return targetIndex
    }
}

class FaceNameHanja(private val docInfoCopier: HWPDocInfoCopier)
    : HWPBaseFaceName(docInfoCopier.targetDocInfo.hanjaFaceNameList, docInfoCopier.originalDocInfo.hanjaFaceNameList) {
    override fun proceed(id: Int): Int {
        val targetIndex = findByIndex(id)
        if (targetIndex == -1) {
            val original: HWPFaceName = originalFaceNames[id]
            val target: HWPFaceName = original.copy()
            targetFaceNames.add(target)
            docInfoCopier.updateIDMappings(IDMappingTypes.HANJA_FACENAME)
            return targetFaceNames.size - 1
        }
        return targetIndex
    }
}

class FaceNameJapanese(private val docInfoCopier: HWPDocInfoCopier)
    : HWPBaseFaceName(docInfoCopier.targetDocInfo.japaneseFaceNameList, docInfoCopier.originalDocInfo.japaneseFaceNameList) {
    override fun proceed(id: Int): Int {
        val targetIndex = findByIndex(id)
        if (targetIndex == -1) {
            val original: HWPFaceName = originalFaceNames[id]
            val target: HWPFaceName = original.copy()
            targetFaceNames.add(target)
            docInfoCopier.updateIDMappings(IDMappingTypes.JAPANESE_FACENAME)
            return targetFaceNames.size - 1
        }
        return targetIndex
    }
}

class FaceNameEtc(private val docInfoCopier: HWPDocInfoCopier)
    : HWPBaseFaceName(docInfoCopier.targetDocInfo.etcFaceNameList, docInfoCopier.originalDocInfo.etcFaceNameList) {
    override fun proceed(id: Int): Int {
        val targetIndex = findByIndex(id)
        if (targetIndex == -1) {
            val original: HWPFaceName = originalFaceNames[id]
            val target: HWPFaceName = original.copy()
            targetFaceNames.add(target)
            docInfoCopier.updateIDMappings(IDMappingTypes.ETC_FACENAME)
            return targetFaceNames.size - 1
        }
        return targetIndex
    }
}

class FaceNameSymbol(private val docInfoCopier: HWPDocInfoCopier)
    : HWPBaseFaceName(docInfoCopier.targetDocInfo.symbolFaceNameList, docInfoCopier.originalDocInfo.symbolFaceNameList) {
    override fun proceed(id: Int): Int {
        val targetIndex = findByIndex(id)
        if (targetIndex == -1) {
            val original: HWPFaceName = originalFaceNames[id]
            val target: HWPFaceName = original.copy()
            targetFaceNames.add(target)
            docInfoCopier.updateIDMappings(IDMappingTypes.SYMBOL_FACENAME)
            return targetFaceNames.size - 1
        }
        return targetIndex
    }
}

class FaceNameUser(private val docInfoCopier: HWPDocInfoCopier)
    : HWPBaseFaceName(docInfoCopier.targetDocInfo.userFaceNameList, docInfoCopier.originalDocInfo.userFaceNameList) {
    override fun proceed(id: Int): Int {
        val targetIndex = findByIndex(id)
        if (targetIndex == -1) {
            val original: HWPFaceName = originalFaceNames[id]
            val target: HWPFaceName = original.copy()
            targetFaceNames.add(target)
            docInfoCopier.updateIDMappings(IDMappingTypes.USER_FACENAME)
            return targetFaceNames.size - 1
        }
        return targetIndex
    }
}