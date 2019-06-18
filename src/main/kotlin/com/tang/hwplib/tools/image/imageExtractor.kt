package com.tang.hwplib.tools.image

import com.tang.hwplib.objects.HWPDocument
import java.io.FileOutputStream

fun HWPDocument.imageExtractor(savePath: String) {
    this.binData.embeddedBinaryDataList.forEach {
        val pos = FileOutputStream(savePath + it.name)
        pos.write(it.data)
        pos.close()
    }
}