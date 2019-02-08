package com.tang.hwplib.reader.bodytext.paragraph.control.eqed

import com.tang.hwplib.objects.bodytext.control.equation.HWPEQEdit
import com.tang.hwplib.reader.util.StreamReader

/**
 * 수식 개체 속성 [HWPEQEdit]을 읽는 개체
 *
 * @author accforaus
 *
 * @param [eqEdit] [HWPEQEdit], 빈 수식 개체 속성 객체
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forEQEdit(eqEdit: HWPEQEdit, sr: StreamReader) {
    eqEdit.run {
        property = sr.readUInt32()
        script = sr.readUTF16LEString()
        letterSize = sr.readUInt32()
        letterColor.value = sr.readColorRef()
        baseLine = sr.readInt16()
        sr.skip(2)
        versionInfo = sr.readUTF16LEString()
        if (!sr.isEndOfRecord())
            fontName = sr.readUTF16LEString()
        if (!sr.isEndOfRecord())
            unknown = sr.readUTF16LEString()
    }
}