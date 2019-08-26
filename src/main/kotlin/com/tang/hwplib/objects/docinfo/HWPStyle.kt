package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.annotation.ID
import com.tang.hwplib.annotation.IDTypes
import com.tang.hwplib.annotation.LinkID
import com.tang.hwplib.objects.docinfo.style.HWPStyleProperty
import com.tang.hwplib.objects.docinfo.style.HWPStyleSort
import com.tang.hwplib.objects.etc.STYLE
import com.tang.hwplib.util.extension.nullEquals
import com.tang.hwplib.util.exceptions.HWPBuildException

/**
 * 스타일(문단 스타일)을 나타내는 객체
 * Tag ID: HWPTAG_STYLE [STYLE]
 * 가변 길이
 * @author accforaus
 *
 * @property [hangulName] [String], 로컬 스타일 이름. 한글 윈도우에서는 한글 스타일 이름 (WCHAR array[2] - unsigned 4 bytes)
 * @property [englishName] [String], 영문 스타일 이름 (WCHAR array[2] - unsigned 4 bytes)
 * @property [property] [HWPStyleProperty], 속성 (BYTE - unsigned 1 byte)
 * @property [nextStyleId] [Short], 다음 스타일[HWPStyle] 아이디 참조값 (BYTE - unsigned 1 byte)
 * @property [languageId] [Short], 언어 아이디 (INT16 - signed 2 bytes)
 * @property [paraShapeId] [Int], 문단 모양[HWPParaShape] 참조값(문단 모양의 아이디 속성) 스타일의 종류가 문단인 경우 반드시 지정해야 한다. (UINT16 - unsigned 2 bytes)
 * @property [charShapeId] [Int], 글자 모양[HWPCharShape] 참조값(글자 모양의 아이디 속성) 스타일의 종류가 글자인 경우 반드시 지정해야 한다. (UINT16 - unsigned 2 bytes)
 */
@LinkID class HWPStyle : HWPDocInfoElement() {
    var hangulName: String? = null
    var englishName: String? = null
    var property: HWPStyleProperty = HWPStyleProperty()
    var nextStyleId: Short = 0
    var languageId: Short = 0
    @ID(IDTypes.ParaShape)
    var paraShapeId: Int = 0
    @ID(IDTypes.CharShape)
    var charShapeId: Int = 0

    override fun equals(other: Any?): Boolean = (other as HWPStyle).let {
        return nullEquals(hangulName, it.hangulName)
                && nullEquals(englishName, it.englishName)
                && property == it.property
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPStyle] 복사된 객체 반환
     */
    override fun copy() : HWPStyle = HWPStyle().also {
        it.hangulName = this.hangulName
        it.englishName = this.englishName
        it.property.value = this.property.value
        it.nextStyleId = this.nextStyleId
        it.languageId = this.languageId
        it.paraShapeId = this.paraShapeId
        it.charShapeId = this.charShapeId
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPStyle] 생성된 객체 반환
         */
        fun build(hangulName: String? = null,
                  englishName: String? = null,
                  property: HWPStyleProperty = HWPStyleProperty.build(0),
                  nextStyleId: Short = 0,
                  languageId: Short = 0,
                  paraShapeId: Int = 0,
                  charShapeId: Int = 0)
                : HWPStyle = HWPStyle().apply {
            property.run {
                when (getStyleSort()) {
                    HWPStyleSort.ParaStyle -> {
                        if (paraShapeId < 0)
                            throw HWPBuildException("[HWPStyle] Style Sort is ParaStyle, paraShapeId($paraShapeId) must set greater than 0")
                    }
                    HWPStyleSort.CharStyle -> {
                        if (charShapeId < 0)
                            throw HWPBuildException("[HWPStyle] Style Sort is CharStyle, charShapeId($charShapeId) must set grader than 0")
                    }
                }
            }
            this.hangulName = hangulName
            this.englishName = englishName
            this.property = property
            this.nextStyleId = nextStyleId
            this.languageId = languageId
            this.paraShapeId = paraShapeId
            this.charShapeId = charShapeId
        }
    }
}