package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.objects.docinfo.facename.HWPFaceNameProperty
import com.tang.hwplib.objects.docinfo.facename.HWPFontType
import com.tang.hwplib.objects.docinfo.facename.HWPFontTypeInfo
import com.tang.hwplib.objects.etc.FACE_NAME
/**
 * 글꼴 (가변)
 * Tag ID: HWPTAG_FACE_NAME [FACE_NAME]
 *
 * @author accforaus
 *
 * @property [property] [HWPFaceNameProperty], 속성 (BYTE - unsigned 1 byte)
 * @property [name] [String], 글꼴 이름 (WCHAR array - unsigned 4 bytes)
 * @property [substituteFontType] [HWPFontType], 대체 글꼴 유형 (BYTE - unsigned 1 byte)
 * @property [substituteFontName] [String], 대체 글꼴 이름 (WCHAR array - unsigned 4 bytes)
 * @property [fontTypeInfo] [HWPFontTypeInfo], 글꼴 유형 정보 (BYTE array - unsigned 10 bytes)
 * @property [baseFontName] [String], 기본 글꼴 이름 (WCHAR array - unsigned 4 bytes)
 */
class HWPFaceName {
    var property: HWPFaceNameProperty = HWPFaceNameProperty()
    var name: String? = null
    var substituteFontType: HWPFontType? = null
    var substituteFontName: String? = null
    var fontTypeInfo: HWPFontTypeInfo = HWPFontTypeInfo()
    var baseFontName: String? = null

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPFaceName] 복사된 객체 반환
     */
    fun copy() : HWPFaceName = HWPFaceName().also {
        it.property.value = this.property.value
        it.name = this.name
        this.substituteFontType?.run {
            it.substituteFontType = HWPFontType.valueOf(this.value)
        }
        it.substituteFontName = this.substituteFontName
        it.fontTypeInfo = this.fontTypeInfo.copy()
        it.baseFontName = this.baseFontName
    }
}