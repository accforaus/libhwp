package com.tang.hwplib.builder.docinfo.charshape

import com.tang.hwplib.builder.docinfo.HWPFaceNameBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.charshape.HWPFaceNameIds
import com.tang.hwplib.objects.docinfo.facename.HWPFaceNameEnum

class HWPFaceNameIDBuilder(val docInfo : HWPDocInfo? = null) : HWPBuilder<HWPFaceNameIds> {
    private val faceNameIds: HWPFaceNameIds = HWPFaceNameIds.build()

    fun setHangul(hangul: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setHangul(hangul)
    }

    fun setHangul(faceNameBuilder: HWPFaceNameBuilder) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setHangul(faceNameBuilder.proceed())
    }

    fun setHangul(name: String) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setHangul(docInfo?.proceedFaceName(name, HWPFaceNameEnum.HANGUL) ?: 0)
    }

    fun setEnglish(english: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setLatin(english)
    }

    fun setEnglish(faceNameBuilder: HWPFaceNameBuilder) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setLatin(faceNameBuilder.proceed())
    }

    fun setEnglish(name: String) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setLatin(docInfo?.proceedFaceName(name, HWPFaceNameEnum.ENGLISH) ?: 0)
    }

    fun setHanja(hanja: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setHanja(hanja)
    }

    fun setHanja(faceNameBuilder: HWPFaceNameBuilder) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setHanja(faceNameBuilder.proceed())
    }

    fun setHanja(name: String) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setHanja(docInfo?.proceedFaceName(name, HWPFaceNameEnum.HANJA) ?: 0)
    }

    fun setJapanese(japanese: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setJapanese(japanese)
    }

    fun setJapanese(faceNameBuilder: HWPFaceNameBuilder) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setJapanese(faceNameBuilder.proceed())
    }

    fun setJapanese(name: String) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setJapanese(docInfo?.proceedFaceName(name, HWPFaceNameEnum.JAPANESE) ?: 0)
    }

    fun setETC(other: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setOther(other)
    }

    fun setETC(faceNameBuilder: HWPFaceNameBuilder) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setOther(faceNameBuilder.proceed())
    }

    fun setETC(name: String) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setOther(docInfo?.proceedFaceName(name, HWPFaceNameEnum.ETC) ?: 0)
    }

    fun setSymbol(symbol: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setSymbol(symbol)
    }

    fun setSymbol(faceNameBuilder: HWPFaceNameBuilder) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setSymbol(faceNameBuilder.proceed())
    }

    fun setSymbol(name: String) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setSymbol(docInfo?.proceedFaceName(name, HWPFaceNameEnum.SYMBOL) ?: 0)
    }

    fun setUser(user: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setUser(user)
    }

    fun setUser(faceNameBuilder: HWPFaceNameBuilder) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setUser(faceNameBuilder.proceed())
    }

    fun setUser(name: String) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setUser(docInfo?.proceedFaceName(name, HWPFaceNameEnum.USER) ?: 0)
    }

    fun setForAll(value: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setForAll(value)
    }

    fun setForAll(faceNameBuilder: HWPFaceNameBuilder) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setForAll(faceNameBuilder.proceed())
    }

    fun setForAll(name: String) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setForAll(docInfo?.proceedFaceName(name, HWPFaceNameEnum.HANGUL) ?: 0)
    }

    override fun build(): HWPFaceNameIds = faceNameIds
}