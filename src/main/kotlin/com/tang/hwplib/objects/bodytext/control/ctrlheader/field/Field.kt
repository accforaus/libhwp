package com.tang.hwplib.objects.bodytext.control.ctrlheader.field

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 필드 속성을 나타내는 객체
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], 필드 속성값
 */
class HWPFieldHeaderProperty {
    var value: Long = 0

    /**
     * 읽기 전용 상태에서도 수정 가능한지 여부를 반환하는 함수
     * bit 0
     *
     * @return [Boolean] 읽기 전용 상태에서도 수정 가능한지 여부 반환
     */
    fun canModifyInReadOnlyState() : Boolean = get(value, 0)

    /**
     * 읽기 전용 상태에서도 수정 가능한지 여부를 설정하는 함수
     * bit 0
     *
     * @param [modifyReadOnlyProperty] [Boolean] 읽기 전용 상태에서도 수정 가능한지 여부값
     */
    fun setModifyInReadOnlyState(modifyReadOnlyProperty: Boolean) {
        set(value, 0, modifyReadOnlyProperty)
    }

    /**
     * 하이퍼링크 필드 업데이트 시 글자 속성이 열어보지 않은 링크인지 여부를 반환하는 함수
     * bit 11
     *
     * @return [Boolean] 하이퍼링크 필드 업데이트 시 글자 속성이 열어보지 않은 링크인지 여부 반환
     */
    fun isUpdateTextPropertyAtUpdatingHyperlinkNotOpened() : Boolean = get(value, 11)

    /**
     * 하이퍼링크 필드 업데이트 시 글자 속성이 열어보지 않은 링크인지 여부를 설정하는 함수
     * bit 11
     *
     * @param [updateTextPropertyAtUpdatingHyperlinkNotOpened] [Boolean] 하이퍼링크 필드 업데이트 시 글자 속성이 열어보지 않은 링크인지 여부값
     */
    fun setUpdateTextPropertyAtUpdatingHyperlinkNotOpened(updateTextPropertyAtUpdatingHyperlinkNotOpened: Boolean) {
        value = set(value, 11, updateTextPropertyAtUpdatingHyperlinkNotOpened)
    }

    /**
     * 하이퍼링크 필드 업데이트 시 글자 속성이 열어본 링크인지 여부를 반환하는 함수
     * bit 12
     *
     * @return [Boolean] 하이퍼링크 필드 업데이트 시 글자 속성이 열어본 링크인지 여부 반환
     */
    fun isUpdateTextPropertyAtUpdatingHyperlinkOpened() : Boolean = get(value, 12)

    /**
     * 하이퍼링크 필드 업데이트 시 글자 속성이 열어본 링크인지 여부를 설정하는 함수
     * bit 12
     *
     * @param [updateTextPropertyAtUpdatingHyperlinkOpened] [Boolean] 하이퍼링크 필드 업데이트 시 글자 속성이 열어본 링크인지 여부값
     */
    fun setUpdateTextPropertyAtUpdatingHyperlinkOpened(updateTextPropertyAtUpdatingHyperlinkOpened: Boolean) {
        value = set(value, 12, updateTextPropertyAtUpdatingHyperlinkOpened)
    }

    /**
     * 하이퍼링크 필드 업데이트 시 글자 속성이 링크 생성인지 여부를 반환하는 함수
     * bit 13
     *
     * @return [Boolean] 하이퍼링크 필드 업데이트 시 글자 속성이 링크 생성인지 여부 반환
     */
    fun isUpdateTextPropertyAtUpdatingHyperlinkCreating() : Boolean = get(value, 13)

    /**
     * 하이퍼링크 필드 업데이트 시 글자 속성이 링크 생성인지 여부를 설정하는 함수
     * bit 13
     *
     * @param [updateTextPropertyAtUpdatingHyperlinkCreating] [Boolean] 하이퍼링크 필드 업데이트 시 글자 속성이 링크 생성인지 여부값
     */
    fun setUpdateTextPropertyAtUpdatingHyperlinkCreating(updateTextPropertyAtUpdatingHyperlinkCreating: Boolean) {
        value = set(value, 13, updateTextPropertyAtUpdatingHyperlinkCreating)
    }

    /**
     * 필드 내용이 수정되었는지 여부를 반환하는 함수
     * bit 15
     *
     * @return [Boolean] 필드 내용이 수정되었는지 여부 반환
     */
    fun isModifiedContent() : Boolean = get(value, 15)

    /**
     * 필드 내용이 수정되었는지 여부를 설정하는 함수
     * bit 15
     *
     * @param [modifiedContent] [Boolean] 필드 내용이 수정되었는지 여부값
     */
    fun setModifiedContent(modifiedContent: Boolean) {
        value = set(value, 15, modifiedContent)
    }
}