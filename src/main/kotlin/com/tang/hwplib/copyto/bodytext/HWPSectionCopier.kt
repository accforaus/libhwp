package com.tang.hwplib.copyto.bodytext

import com.tang.hwplib.copyto.bodytext.paragraph.copyParagraph
import com.tang.hwplib.copyto.docinfo.HWPDocInfoCopier
import com.tang.hwplib.objects.bodytext.HWPSection
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph

fun copySections(target: HWPSection, original: HWPSection, docInfo: HWPDocInfoCopier) {
    for ((index, originalParagraph) in original.paragraphList.withIndex()) {
        if (index > 0) {
            val targetParagraph: HWPParagraph = HWPParagraph()
            copyParagraph(targetParagraph, originalParagraph, docInfo)
            target.paragraphList.add(targetParagraph)
        }
    }
}