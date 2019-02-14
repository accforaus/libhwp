package com.tang.hwplib.copyto.bodytext.paragraph

import com.tang.hwplib.copyto.bodytext.paragraph.control.copyControls
import com.tang.hwplib.copyto.docinfo.HWPDocInfoCopier
import com.tang.hwplib.objects.bodytext.control.HWPControl
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphList

fun copyParagraphList(target: HWPParagraphList, original: HWPParagraphList, docInfo: HWPDocInfoCopier) {
    target.paragraphList.clear()
    for (paragraph in original.paragraphList) {
        val targetParagraph: HWPParagraph = HWPParagraph()
        copyParagraph(targetParagraph, paragraph, docInfo)
        target.paragraphList.add(targetParagraph)
    }
}

fun copyParagraph(target: HWPParagraph, original: HWPParagraph,  docInfo: HWPDocInfoCopier) {
    fun copyParaHeader() {
        target.header = original.header.copy().apply {
            this.paraShapeId = docInfo.paraShapeCopier.proceed(this.paraShapeId)
            this.styleId = docInfo.styleCopier.proceed(this.styleId.toInt()).toShort()
        }
    }

    fun copyCharShape() {
        original.paraCharShape?.run {
            target.paraCharShape = this.copy().apply {
                for (pair in this.positionShapeIdPairList)
                    pair.shapeId = docInfo.charShapeCopier.proceed(pair.shapeId.toInt()).toLong()
            }
        }
    }

    fun copyLineSeg() {
        original.lineSeg?.run {
            target.lineSeg = this.copy()
        }
    }

    fun copyText() {
        original.text?.run {
            target.text = this.copy()
        }
    }

    fun copyRangeTag() {
        original.rangeTag?.run {
            target.rangeTag = this.copy()
        }
    }

    fun copyControl() {
        original.controlList?.run {
            if (target.controlList == null) target.controlList = ArrayList()
            for (control in this) {
                val targetControl: HWPControl? = copyControls(control, docInfo)
                targetControl?.let { target.controlList!!.add(it) }
            }
        }
    }

    copyParaHeader()
    copyCharShape()
    copyLineSeg()
    copyText()
    copyRangeTag()
    copyControl()
}