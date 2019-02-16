package com.tang.hwplib.builder.docinfo.charshape

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.charshape.HWPCharOffsets
import com.tang.hwplib.objects.docinfo.charshape.HWPCharSpaces
import com.tang.hwplib.objects.docinfo.charshape.HWPRatios
import com.tang.hwplib.objects.docinfo.charshape.HWPRelativeSizes

class HWPCharOffsetsBuilder : HWPBuilder<HWPCharOffsets> {
    private val charOffsets: HWPCharOffsets = HWPCharOffsets.build()

    fun setHangul(hangul: Byte) : HWPCharOffsetsBuilder = this.apply {
        charOffsets.setHangul(hangul)
    }

    fun setEnglish(english: Byte) : HWPCharOffsetsBuilder = this.apply {
        charOffsets.setLatin(english)
    }

    fun setHanja(hanja: Byte) : HWPCharOffsetsBuilder = this.apply {
        charOffsets.setHanja(hanja)
    }

    fun setJapanese(japanese: Byte) : HWPCharOffsetsBuilder = this.apply {
        charOffsets.setJapanese(japanese)
    }

    fun setOther(other: Byte) : HWPCharOffsetsBuilder = this.apply {
        charOffsets.setOther(other)
    }

    fun setSymbol(symbol: Byte) : HWPCharOffsetsBuilder = this.apply {
        charOffsets.setSymbol(symbol)
    }

    fun setUser(user: Byte) : HWPCharOffsetsBuilder = this.apply {
        charOffsets.setUser(user)
    }

    fun setForAll(value: Byte) : HWPCharOffsetsBuilder = this.apply {
        charOffsets.setForAll(value)
    }

    override fun build(): HWPCharOffsets = charOffsets
}

class HWPRatiosBuilder : HWPBuilder<HWPRatios> {
    private val ratios: HWPRatios = HWPRatios.build()

    fun setHangul(hangul: Short) : HWPRatiosBuilder = this.apply {
        ratios.setHangul(hangul)
    }

    fun setEnglish(english: Short) : HWPRatiosBuilder = this.apply {
        ratios.setLatin(english)
    }

    fun setHanja(hanja: Short) : HWPRatiosBuilder = this.apply {
        ratios.setHanja(hanja)
    }

    fun setJapanese(japanese: Short) : HWPRatiosBuilder = this.apply {
        ratios.setJapanese(japanese)
    }

    fun setOther(other: Short) : HWPRatiosBuilder = this.apply {
        ratios.setOther(other)
    }

    fun setSymbol(symbol: Short) : HWPRatiosBuilder = this.apply {
        ratios.setSymbol(symbol)
    }

    fun setUser(user: Short) : HWPRatiosBuilder = this.apply {
        ratios.setUser(user)
    }

    fun setForAll(value: Short) : HWPRatiosBuilder = this.apply {
        ratios.setForAll(value)
    }

    override fun build(): HWPRatios = ratios
}

class HWPRelativeSizeBuilder : HWPBuilder<HWPRelativeSizes> {
    private val relativeSizes: HWPRelativeSizes = HWPRelativeSizes.build()

    fun setHangul(hangul: Short) : HWPRelativeSizeBuilder = this.apply {
        relativeSizes.setHangul(hangul)
    }

    fun setEnglish(english: Short) : HWPRelativeSizeBuilder = this.apply {
        relativeSizes.setLatin(english)
    }

    fun setHanja(hanja: Short) : HWPRelativeSizeBuilder = this.apply {
        relativeSizes.setHanja(hanja)
    }

    fun setJapanese(japanese: Short) : HWPRelativeSizeBuilder = this.apply {
        relativeSizes.setJapanese(japanese)
    }

    fun setOther(other: Short) : HWPRelativeSizeBuilder = this.apply {
        relativeSizes.setOther(other)
    }

    fun setSymbol(symbol: Short) : HWPRelativeSizeBuilder = this.apply {
        relativeSizes.setSymbol(symbol)
    }

    fun setUser(user: Short) : HWPRelativeSizeBuilder = this.apply {
        relativeSizes.setUser(user)
    }

    fun setForAll(value: Short) : HWPRelativeSizeBuilder = this.apply {
        relativeSizes.setForAll(value)
    }

    override fun build(): HWPRelativeSizes = relativeSizes
}

class HWPCharSpaceBuilder : HWPBuilder<HWPCharSpaces> {
    private val charSpaces: HWPCharSpaces = HWPCharSpaces.build()

    fun setHangul(hangul: Byte) : HWPCharSpaceBuilder = this.apply {
        charSpaces.setHangul(hangul)
    }

    fun setEnglish(english: Byte) : HWPCharSpaceBuilder = this.apply {
        charSpaces.setLatin(english)
    }

    fun setHanja(hanja: Byte) : HWPCharSpaceBuilder = this.apply {
        charSpaces.setHanja(hanja)
    }

    fun setJapanese(japanese: Byte) : HWPCharSpaceBuilder = this.apply {
        charSpaces.setJapanese(japanese)
    }

    fun setOther(other: Byte) : HWPCharSpaceBuilder = this.apply {
        charSpaces.setOther(other)
    }

    fun setSymbol(symbol: Byte) : HWPCharSpaceBuilder = this.apply {
        charSpaces.setSymbol(symbol)
    }

    fun setUser(user: Byte) : HWPCharSpaceBuilder = this.apply {
        charSpaces.setUser(user)
    }

    fun setForAll(value: Byte) : HWPCharSpaceBuilder = this.apply {
        charSpaces.setForAll(value)
    }

    override fun build(): HWPCharSpaces = charSpaces
}