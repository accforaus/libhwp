package com.tang.hwplib.copyto

import com.tang.hwplib.copyto.bodytext.copySections
import com.tang.hwplib.copyto.bodytext.paragraph.copyParagraph
import com.tang.hwplib.copyto.docinfo.HWPDocInfoCopier
import com.tang.hwplib.objects.HWPDocument
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph

internal fun HWPDocumentCopyTo(target: HWPDocument, source: HWPDocument) {
    val docInfo: HWPDocInfoCopier = HWPDocInfoCopier(target, source)
    copySections(target.bodyText.sectionList[0], source.bodyText.sectionList[0], docInfo)
}

internal fun appendParagraph(paragraph: HWPParagraph, target: HWPDocument, source: HWPDocument) {
    val docInfo: HWPDocInfoCopier = HWPDocInfoCopier(target, source)
    target.bodyText.sectionList[0].run {
        val targetParagraph: HWPParagraph = HWPParagraph()
        copyParagraph(targetParagraph, paragraph, docInfo)
        paragraphList.add(targetParagraph)
    }
}

internal fun appendParagraphList(paragraphs: ArrayList<HWPParagraph>, target: HWPDocument, source: HWPDocument) {
    for (paragraph in paragraphs)
        appendParagraph(paragraph, target, source)
}