package com.tang.hwplib.objects.bodytext.control

/**
 * 컨트롤 ID를 생성하는 함수
 *
 * @author accforaus
 *
 * @param [a] [Char]
 * @param [b] [Char]
 * @param [c] [Char]
 * @param [d] [Char]
 *
 * @return [Long] 컨트롤 종류를 나타내는 식별 기호 반환
 */
fun makeCtrlID(a: Char, b: Char, c: Char, d:Char) : Long =
        (a.toInt().shl(24)).or(b.toInt().shl(16)).or(c.toInt().shl(8)).or(d.toInt()).toLong()

/**
 * 컨트롤 종류
 *
 * @author accforaus
 *
 * @property [ctrlId] [Long], 컨트롤 ID값을 가진 데이터
 */
enum class HWPControlType(id: Long) {
    /**
     * 표
     */
    Table(makeCtrlID('t', 'b', 'l', ' ')),
    /**
     * 그리기 객체
     */
    Gso(makeCtrlID('g', 's', 'o', ' ')),
    /**
     * 한글 수식 개체
     */
    Equation(makeCtrlID('e', 'q', 'e', 'd')),
    /**
     * 구역 정의
     */
    SectionDefine(makeCtrlID('s', 'e', 'c', 'd')),
    /**
     * 단 정의
     */
    ColumnDefine(makeCtrlID('c', 'o', 'l', 'd')),
    /**
     * 머리말
     */
    Header(makeCtrlID('h', 'e', 'a', 'd')),
    /**
     * 꼬리말
     */
    Footer(makeCtrlID('f', 'o', 'o', 't')),
    /**
     * 각주
     */
    Footnote(makeCtrlID('f', 'n', ' ', ' ')),
    /**
     * 미주
     */
    Endnote(makeCtrlID('e', 'n', ' ', ' ')),
    /**
     * 자동 번호
     */
    AutoNumber(makeCtrlID('a', 't', 'n', 'o')),
    /**
     * 새 번호 지정
     */
    NewNumber(makeCtrlID('n', 'w', 'n', 'o')),
    /**
     * 감추기
     */
    PageHide(makeCtrlID('p', 'g', 'h', 'd')),
    /**
     * 홀/짝수 조정
     */
    PageOddEvenAdjust(makeCtrlID('p', 'g', 'c', 't')),
    /**
     * 쪽 번호 위치
     */
    PageNumberPosition(makeCtrlID('p', 'g', 'n', 'p')),
    /**
     * 찾아보기 표식
     */
    IndexMark(makeCtrlID('i', 'd', 'x', 'm')),
    /**
     * 책갈피
     */
    Bookmark(makeCtrlID('b', 'o', 'k', 'm')),
    /**
     * 글자 겹침
     */
    OverlappingLetter(makeCtrlID('t', 'c', 'p', 's')),
    /**
     * 덧말
     */
    AdditionalText(makeCtrlID('t', 'd', 'u', 't')),
    /**
     * 숨은 설명
     */
    HiddenComment(makeCtrlID('t', 'c', 'm', 't')),

    FIELD_UNKNOWN(makeCtrlID('%', 'u', 'n', 'k')),
    FIELD_DATE(makeCtrlID('%', 'd', 't', 'e')),
    FIELD_DOCDATE(makeCtrlID('%', 'd', 'd', 't')),
    FIELD_PATH(makeCtrlID('%', 'p', 'a', 't')),
    FIELD_BOOKMARK(makeCtrlID('%', 'b', 'm', 'k')),
    FIELD_MAILMERGE(makeCtrlID('%', 'm', 'm', 'g')),
    FIELD_CROSSREF(makeCtrlID('%', 'x', 'r', 'f')),
    FIELD_FORMULA(makeCtrlID('%', 'f', 'm', 'u')),
    FIELD_CLICKHERE(makeCtrlID('%', 'c', 'l', 'k')),
    FIELD_SUMMARY(makeCtrlID('%', 's', 'm', 'r')),
    FIELD_USERINFO(makeCtrlID('%', 'u', 's', 'r')),
    FIELD_HYPERLINK(makeCtrlID('%', 'h', 'l', 'k')),
    FIELD_REVISION_SIGN(makeCtrlID('%', 's', 'i', 'g')),
    FIELD_REVISION_DELETE(makeCtrlID('%', '%', '*', 'd')),
    FIELD_REVISION_ATTACH(makeCtrlID('%', '%', '*','a')),
    FIELD_REVISION_CLIPPING(makeCtrlID('%', '%', '*', 'C')),
    FIELD_REVISION_SAWTOOTH(makeCtrlID('%', '%', '*', 'S')),
    FIELD_REVISION_THINKING(makeCtrlID('%', '%', '*', 'T')),
    FIELD_REVISION_PRAISE(makeCtrlID('%', '%', '*', 'P')),
    FIELD_REVISION_LINE(makeCtrlID('%', '%', '*', 'L')),
    FIELD_REVISION_SIMPLECHANGE(makeCtrlID('%', '%', '*', 'c')),
    FIELD_REVISION_HYPERLINK(makeCtrlID('%', '%', '*', 'h')),
    FIELD_REVISION_LINEATTACH(makeCtrlID('%', '%', '*', 'A')),
    FIELD_REVISION_LINELINK(makeCtrlID('%', '%', '*', 'i')),
    FIELD_REVISION_LINETRANSFER(makeCtrlID('%', '%', '*', 't')),
    FIELD_REVISION_RIGHTMOVE(makeCtrlID('%', '%', '*', 'r')),
    FIELD_REVISION_LEFTMOVE(makeCtrlID('%', '%', '*', 'l')),
    FIELD_REVISION_TRANSFER(makeCtrlID('%', '%', '*', 'n')),
    FIELD_REVISION_SIMPLEINSERT(makeCtrlID('%', '%', '*', 'e')),
    FIELD_REVISION_SPLIT(makeCtrlID('%', 's', 'p', 'l')),
    FIELD_REVISION_CHANGE(makeCtrlID('%', '%', 'm', 'r')),
    FIELD_MEMO(makeCtrlID('%', '%', 'm', 'e')),
    FIELD_PRIVATE_INFO_SECURITY(makeCtrlID('%', 'c', 'p', 'r')),
    FIELD_TABLEOFCONTENTS(makeCtrlID('%','t','o','c'));

    var ctrlId: Long = id

    fun isField() : Boolean = HWPControlType.isField(ctrlId)

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [ctrlId] [Long], 파일에 저장되는 정수값
         * @return [HWPControlType] enum 값
         */
        fun ctrlIdOf(ctrlId: Long) : HWPControlType {
            for (ct in values())
                if (ct.ctrlId == ctrlId)
                    return ct
            return Table
        }

        /**
         * [ctrlId]가 필드 컨트롤 객체인지 여부를 반환하는 함수
         *
         * @param [ctrlId] [Long], 컨트롤 ID
         * @return [Boolean] 컨트롤 필드인지 여부 반환
         */
        fun isField(ctrlId: Long) : Boolean = ctrlId == FIELD_UNKNOWN.ctrlId || ctrlId == FIELD_DATE.ctrlId || ctrlId == FIELD_DOCDATE.ctrlId
                || ctrlId == FIELD_PATH.ctrlId || ctrlId == FIELD_BOOKMARK.ctrlId || ctrlId == FIELD_MAILMERGE.ctrlId
                || ctrlId == FIELD_CROSSREF.ctrlId || ctrlId == FIELD_FORMULA.ctrlId || ctrlId == FIELD_CLICKHERE.ctrlId
                || ctrlId == FIELD_SUMMARY.ctrlId || ctrlId == FIELD_USERINFO.ctrlId || ctrlId == FIELD_HYPERLINK.ctrlId
                || ctrlId == FIELD_REVISION_SIGN.ctrlId || ctrlId == FIELD_REVISION_DELETE.ctrlId || ctrlId == FIELD_REVISION_SAWTOOTH.ctrlId
                || ctrlId == FIELD_REVISION_ATTACH.ctrlId || ctrlId == FIELD_REVISION_CLIPPING.ctrlId
                || ctrlId == FIELD_REVISION_THINKING.ctrlId || ctrlId == FIELD_REVISION_PRAISE.ctrlId
                || ctrlId == FIELD_REVISION_LINE.ctrlId || ctrlId == FIELD_REVISION_SIMPLECHANGE.ctrlId
                || ctrlId == FIELD_REVISION_HYPERLINK.ctrlId || ctrlId == FIELD_REVISION_LINEATTACH.ctrlId
                || ctrlId == FIELD_REVISION_LINELINK.ctrlId || ctrlId == FIELD_REVISION_LINETRANSFER.ctrlId
                || ctrlId == FIELD_REVISION_RIGHTMOVE.ctrlId || ctrlId == FIELD_REVISION_LEFTMOVE.ctrlId
                || ctrlId == FIELD_REVISION_TRANSFER.ctrlId || ctrlId == FIELD_REVISION_SIMPLEINSERT.ctrlId
                || ctrlId == FIELD_REVISION_SPLIT.ctrlId || ctrlId == FIELD_REVISION_CHANGE.ctrlId
                || ctrlId == FIELD_MEMO.ctrlId || ctrlId == FIELD_PRIVATE_INFO_SECURITY.ctrlId || ctrlId == FIELD_TABLEOFCONTENTS.ctrlId
    }
}