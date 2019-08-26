package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.objects.docinfo.facename.HWPFaceNameProperty
import com.tang.hwplib.objects.docinfo.facename.HWPFontType
import com.tang.hwplib.objects.docinfo.facename.HWPFontTypeInfo
import com.tang.hwplib.objects.etc.FACE_NAME
import com.tang.hwplib.util.extension.nullEquals
import com.tang.hwplib.util.exceptions.HWPBuildException

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
class HWPFaceName : HWPDocInfoElement() {
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
    override fun copy() : HWPFaceName = HWPFaceName().also {
        it.property.value = this.property.value
        it.name = this.name
        this.substituteFontType?.run {
            it.substituteFontType = HWPFontType.valueOf(this.value)
        }
        it.substituteFontName = this.substituteFontName
        it.fontTypeInfo = this.fontTypeInfo.copy()
        it.baseFontName = this.baseFontName
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPFaceName] 생성된 객체 반환
         */
        fun build(property: HWPFaceNameProperty = HWPFaceNameProperty.build(),
                  name: String? = null,
                  substituteFontType: HWPFontType? = null,
                  substituteFontName: String? = null,
                  fontTypeInfo: HWPFontTypeInfo = HWPFontTypeInfo.build(),
                  baseFontName: String? = null)
                : HWPFaceName = HWPFaceName().apply {
            property.run {
                if (hasSubstituteFont())
                    if (substituteFontName == null)
                        throw HWPBuildException("[HWPFaceName] Substitute Font flag: true, Substitute font must be not null")
                if (hasBaseFont())
                    if (baseFontName == null)
                        throw HWPBuildException("[HWPFaceName] Base font flag: true, Base font name must be not null")
            }
            this.property = property
            this.name = name
            this.substituteFontType = substituteFontType
            this.substituteFontName = substituteFontName
            this.fontTypeInfo = fontTypeInfo
            this.baseFontName = baseFontName
        }
    }


    override fun equals(other: Any?): Boolean = (other as HWPFaceName).let {
        return property == it.property
                && nullEquals(name, it.name)
                && nullEquals(substituteFontType, it.substituteFontType)
                && nullEquals(substituteFontName, it.substituteFontName)
                && fontTypeInfo == it.fontTypeInfo
                && nullEquals(baseFontName, it.baseFontName)
    }
}