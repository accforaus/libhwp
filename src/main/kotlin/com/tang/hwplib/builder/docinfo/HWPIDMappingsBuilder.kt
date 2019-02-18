package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPIDMappings

class HWPIDMappingsBuilder : HWPDocInfoBuilder() {
    private val idMappings : HWPIDMappings = HWPIDMappings.build()

    fun setBinDataCount(binDataCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.binDataCount = binDataCount
    }

    fun setHangulFaceNameCount(hangulFaceNameCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.hangulFaceNameCount = hangulFaceNameCount
    }

    fun setHanjaFaceNameCount(hanjaFaceNameCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.hanjaFaceNameCount = hanjaFaceNameCount
    }

    fun setEnglishFaceNameCount(englishFaceNameCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.englishFaceNameCount = englishFaceNameCount
    }

    fun setJapaneseFaceNameCount(japaneseFaceNameCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.japaneseFaceNameCount = japaneseFaceNameCount
    }

    fun setEtcFaceNameCount(etcFaceNameCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.etcFaceNameCount = etcFaceNameCount
    }

    fun setSymbolFaceNameCount(symbolFaceNameCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.symbolFaceNameCount = symbolFaceNameCount
    }

    fun setUserFaceNameCount(userFaceNameCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.userFaceNameCount = userFaceNameCount
    }

    fun setBorderFillCount(borderFillCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.borderFillCount = borderFillCount
    }

    fun setCharShapeCount(charShapeCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.charShapeCount = charShapeCount
    }

    fun setTabDefCount(tabDefCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.tabDefCount = tabDefCount
    }

    fun setNumberingCount(numberingCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.numberingCount = numberingCount
    }

    fun setBulletCount(bulletCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.bulletCount = bulletCount
    }

    fun setParaShapeCount(paraShapeCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.paraShapeCount = paraShapeCount
    }

    fun setStyleCount(styleCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.styleCount = styleCount
    }

    fun setMemoShapeCount(memoShapeCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.memoShapeCount = memoShapeCount
    }

    fun setTrackChangeCount(trackChangeCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.trackChangeCount = trackChangeCount
    }

    fun setTrackChangeAuthorCount(trackChangeAuthorCount: Int) : HWPIDMappingsBuilder = this.apply {
        idMappings.trackChangeAuthorCount = trackChangeAuthorCount
    }

    override fun build(): HWPIDMappings = idMappings
}