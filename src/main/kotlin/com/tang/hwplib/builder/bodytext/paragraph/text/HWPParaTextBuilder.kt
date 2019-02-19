package com.tang.hwplib.builder.bodytext.paragraph.text

import com.tang.hwplib.builder.bodytext.paragraph.charshape.HWPCharPositionShapeIdPairBuilder
import com.tang.hwplib.builder.docinfo.HWPCharShapeBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.HWPControl
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph
import com.tang.hwplib.objects.bodytext.paragraph.text.HWPCharControlChar
import com.tang.hwplib.objects.bodytext.paragraph.text.HWPCharNormal
import com.tang.hwplib.objects.bodytext.paragraph.text.HWPControlCharType
import com.tang.hwplib.objects.bodytext.paragraph.text.HWPParaText
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.util.exceptions.HWPBuildException

class HWPParaTextBuilder(private val docInfo: HWPDocInfo) : HWPBuilder<HWPParaText> {
    private val text: HWPParaText = HWPParaText()

    fun addExtendChar(control: HWPControl) : HWPParaTextBuilder = this.apply {
        text.addExtendCharByControl(control)
    }

    override fun build(): HWPParaText = text
}

class HWPTextBuildFactory(private val paragraph: HWPParagraph) {
    private class HWPTextBuildException(msg: String) : HWPBuildException(msg)
    private var startFlag: Boolean = false
    private var startIndex: Int = -1
    private val paraText: HWPParaText = paragraph.text ?: HWPParaText()
    private var charShapeBuilder: HWPCharShapeBuilder? = null

    private fun removeParaBreak() {
        if (paraText.charList.size > 0) {
            for (index in paraText.charList.size-1..0 step -1) {
                if (paraText.charList[index].code == HWPControlCharType.ParaBreak.value) {
                    paraText.charList.removeAt(index)
                    break
                }
            }
        }
    }

    private fun addParaBreak() {
        paraText.charList.add(HWPCharControlChar().apply { code = HWPControlCharType.ParaBreak.value })
    }

    private fun verify() : Boolean {
        if (!startFlag)
            throw HWPTextBuildException("HWPTextBuildFactory must call start() method")
        return true
    }

    fun start() : HWPTextBuildFactory = this.apply {
        startFlag = true
        charShapeBuilder = null
    }

    fun setText(text: String) : HWPTextBuildFactory = this.apply {
        if (verify() && text.isNotEmpty()) {
            removeParaBreak()
            startIndex = paraText.charList.size
            for (char in text) {
                paraText.charList.add(HWPCharNormal().apply {
                    code = char.toShort()
                })
            }
            addParaBreak()
        }
    }

    fun setCharShape(charShapeBuilder: HWPCharShapeBuilder) : HWPTextBuildFactory = this.apply {
        this.charShapeBuilder = charShapeBuilder
    }

    fun end() : HWPTextBuildFactory = this.apply {
        if (verify()) {
            if (paragraph.paraCharShape?.positionShapeIdPairList?.get(0)?.position == startIndex.toLong()) {
                paragraph.paraCharShape?.positionShapeIdPairList?.get(0)?.shapeId = this.charShapeBuilder?.proceed()?.toLong() ?: 0
            }
            else {
                paragraph.paraCharShape?.positionShapeIdPairList?.add(
                        HWPCharPositionShapeIdPairBuilder()
                                .setCharPosition(startIndex.toLong())
                                .setCharShapeID(this.charShapeBuilder?.proceed()?.toLong() ?: 0)
                                .build()
                )
            }
            if (paragraph.text == null)
                paragraph.text = paraText
            startFlag = false
            startIndex = -1
        }
    }
}