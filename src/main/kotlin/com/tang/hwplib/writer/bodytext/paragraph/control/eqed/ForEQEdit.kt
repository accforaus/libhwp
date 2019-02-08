package com.tang.hwplib.writer.bodytext.paragraph.control.eqed

import com.tang.hwplib.objects.bodytext.control.equation.HWPEQEdit
import com.tang.hwplib.objects.etc.EQEDIT
import com.tang.hwplib.util.string.getUTF16LEStringSize
import com.tang.hwplib.writer.util.StreamWriter

/**
 * 수식 개체 속성 [HWPEQEdit]을 쓰는 함수
 *
 * @author accforaus
 *
 * @param [ee] [HWPEQEdit], 수식 개체 속성 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forEQEdit(ee: HWPEQEdit, sw: StreamWriter) {
    /**
     * 수식 개체 속성의 전체 크기를 반환하는 함수
     *
     * @return [Int] 수식 개체 속성의 전체 크기 반환
     */
    fun getSize(): Int {
        ee.run {
            var size: Int = 0
            size += 4
            size += getUTF16LEStringSize(script)
            size += 12
            size += getUTF16LEStringSize(versionInfo)
            if (fontName != null && fontName!!.isNotEmpty())
                size += getUTF16LEStringSize(ee.fontName)
            if (unknown != null && unknown!!.isNotEmpty())
                size += getUTF16LEStringSize(unknown)
            return size
        }
    }

    sw.writeRecordHeader(EQEDIT.toInt(), getSize())
    ee.run {
        sw.writeUInt32(property)
        sw.writeUTF16LEString(script)
        sw.writeUInt32(letterSize)
        sw.writeUInt32(letterColor.value)
        sw.writeInt16(baseLine)
        sw.writeZero(2)
        sw.writeUTF16LEString(versionInfo)
        if (fontName != null && fontName!!.isNotEmpty())
            sw.writeUTF16LEString(fontName)
        if (unknown != null && unknown!!.isNotEmpty())
            sw.writeUTF16LEString(unknown)
    }
}