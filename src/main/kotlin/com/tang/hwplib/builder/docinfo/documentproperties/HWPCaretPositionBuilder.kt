package com.tang.hwplib.builder.docinfo.documentproperties

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.documentproperties.HWPCaretPosition

class HWPCaretPositionBuilder : HWPBuilder<HWPCaretPosition> {
    private val caretPosition : HWPCaretPosition = HWPCaretPosition.build()

    fun setListID(listID: Long) : HWPCaretPositionBuilder = this.apply {
        caretPosition.listID = listID
    }

    fun setParagraphID(paragraphID: Long) : HWPCaretPositionBuilder = this.apply {
        caretPosition.paragraphID = paragraphID
    }

    fun setPositionInParagraph(positionInParagraph: Long) : HWPCaretPositionBuilder = this.apply {
        caretPosition.positionInParagraph = positionInParagraph
    }

    override fun build(): HWPCaretPosition = caretPosition
}