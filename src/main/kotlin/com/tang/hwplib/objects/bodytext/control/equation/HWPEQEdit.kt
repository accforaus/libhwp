package com.tang.hwplib.objects.bodytext.control.equation

import com.tang.hwplib.objects.etc.Color4Byte

/**
 * 수식 개체 속성을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [property] [Long], 속성. 스크립트가 차지하는 범위. 첫 비트가 켜져 있으면 줄 단위, 꺼져 있으면 글자 단위 (UINT32 - unsigned 4 bytes)
 * @property [script] [String], 함글 수식 스크립트 (WCHAR array - unsigned 4 bytes)
 * @property [letterSize] [Long], 수식 글자 크기 (HWPUNIT - unsigned 4 bytes)
 * @property [letterColor] [Color4Byte], 글자 생상 (COLORREF - unsigned 4 bytes)
 * @property [baseLine] [Short], base line (INT16 - signed 2 bytes)
 * @property [versionInfo] [String], 수식 버전 정보 (WCHAR array - unsigned 4 bytes)
 * @property [fontName] [String], 수식 폰트 이름 (WCHAR array - unsigned 4 bytes)
 * @property [unknown] [String], unknown
 */
class HWPEQEdit {
    var property: Long = 0
    var script: String? = null
    var letterSize: Long = 0
    var letterColor: Color4Byte = Color4Byte()
    var baseLine: Short = 0
    var versionInfo: String? = null
    var fontName: String? = null
    var unknown: String? = null

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPEQEdit] 복사된 객체 반환
     */
    fun copy() : HWPEQEdit = HWPEQEdit().also {
        it.property = this.property
        it.script = this.script
        it.letterSize = this.letterSize
        it.letterColor.value = this.letterColor.value
        it.baseLine = this.baseLine
        it.versionInfo = this.versionInfo
        it.fontName = this.fontName
        it.unknown = this.unknown
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPEQEdit] 생성된 객체 반환
         */
        fun build(property: Long = 0,
                  script: String? = null,
                  letterSize: Long = 0,
                  letterColor: Color4Byte = Color4Byte.build(),
                  baseLine: Short = 0, versionInfo: String? = null,
                  fontName: String? = null, unknown: String? = null)
                : HWPEQEdit = HWPEQEdit().apply {
            this.property = property
            this.script = script
            this.letterSize = letterSize
            this.letterColor = letterColor
            this.baseLine = baseLine
            this.versionInfo = versionInfo
            this.fontName = fontName
            this.unknown = unknown
        }
    }
}