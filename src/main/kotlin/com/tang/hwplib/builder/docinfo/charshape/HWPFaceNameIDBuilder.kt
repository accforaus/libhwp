package com.tang.hwplib.builder.docinfo.charshape

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.charshape.HWPFaceNameIds

class HWPFaceNameIDBuilder : HWPBuilder<HWPFaceNameIds> {
    private val faceNameIds: HWPFaceNameIds = HWPFaceNameIds.build()

    fun setHangul(hangul: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setHangul(hangul)
    }

    fun setEnglish(english: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setLatin(english)
    }

    fun setHanja(hanja: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setHanja(hanja)
    }

    fun setJapanese(japanese: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setJapanese(japanese)
    }

    fun setOther(other: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setOther(other)
    }

    fun setSymbol(symbol: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setSymbol(symbol)
    }

    fun setUser(user: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setUser(user)
    }

    fun setForAll(value: Int) : HWPFaceNameIDBuilder = this.apply {
        faceNameIds.setForAll(value)
    }

    override fun build(): HWPFaceNameIds = faceNameIds
}