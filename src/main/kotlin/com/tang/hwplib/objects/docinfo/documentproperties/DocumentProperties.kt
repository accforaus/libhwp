package com.tang.hwplib.objects.docinfo.documentproperties

/**
 *  문서 내 캐럿의 위치 정보 (12 bytes)
 *
 *  @author accforaus
 *
 *  @property [listID] [Long], 리스트 아이디(UINT32) (unsigned 4 bytes)
 *  @property [paragraphID] [Long], 문단 아이디(UINT32) (unsigned 4 bytes)
 *  @property [positionInParagraph] [Long], 문단 내에서의 글자 단위 위치(UINT32) (unsigned 4 bytes)
 */
class HWPCaretPosition {
    var listID: Long = 0
    var paragraphID: Long = 0
    var positionInParagraph: Long = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCaretPosition] 복사된 객체 반환
     */
    fun copy() : HWPCaretPosition = HWPCaretPosition().also {
        it.listID = this.listID
        it.paragraphID = this.paragraphID
        it.positionInParagraph = this.positionInParagraph
    }
}

/**
 * 문서 내 각종 시작번호에 대한 정보를 나타내는 객체 (26 bytes)
 *
 * @author accforaus
 *
 * @property [page] [Int], 페이지 시작 번호(UINT16) (unsigned 2 bytes)
 * @property [footnote] [Int], 각주 시작 번호(UINT16) (unsigned 2 bytes)
 * @property [endnote] [Int], 미주 시작 번호(UINT16) (unsigned 2 bytes)
 * @property [picture] [Int], 그림 시작 번호(UINT16) (unsigned 2 bytes)
 * @property [table] [Int], 표 시작 번호(UINT16) (unsigned 2 bytes)
 * @property [equation] [Int], 수식 시작 번호(UINT16) (unsigned 2 bytes)
 */
class HWPStartNumber {
    var page: Int = 0
    var footnote: Int = 0
    var endnote: Int = 0
    var picture: Int = 0
    var table: Int = 0
    var equation: Int = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPStartNumber] 복사된 객체 반환
     */
    fun copy() : HWPStartNumber = HWPStartNumber().also {
        it.page = this.page
        it.footnote = this.footnote
        it.endnote = this.endnote
        it.picture = this.picture
        it.table = this.table
        it.equation = this.equation
    }
}