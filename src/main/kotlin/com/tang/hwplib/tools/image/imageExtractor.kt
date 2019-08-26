package com.tang.hwplib.tools.image

import com.tang.hwplib.objects.HWPDocument
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.imageio.ImageIO

fun HWPDocument.imageExtractor(savePath: String) {
    this.binData.embeddedBinaryDataList.forEach {
        val pos = FileOutputStream(savePath + it.name)
        pos.write(it.data)
        pos.close()
    }
}

fun HWPDocument.extractCompressedImage(path: String) {
    binData.embeddedBinaryDataList.forEach {
        val extension = it.name.split(".")[1]
        val bufferedImage = ImageIO.read(ByteArrayInputStream(it.data!!))
        val bos = ByteArrayOutputStream()
        val outputFile = File(path + it.name)
        ImageIO.write(bufferedImage, extension, bos)
        bos.writeTo(outputFile.outputStream())
    }
}