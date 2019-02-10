package com.tang.hwplib.objects.bodytext.paragraph.text

enum class HWPControlCharType(v: Short) {
    None(0.toShort()),
    /**
     * Line break
     */
    LineBreak(10.toShort()),
    /**
     * Para Break
     */
    ParaBreak(13.toShort()),
    /**
     * Hyphen
     */
    Hyphen(24.toShort()),
    /**
     * Blank Container
     */
    BlankContainer(30.toShort()),
    /**
     * Blank Fixed Width
     */
    BlankFixedWidth(31.toShort());

    var value: Short = v

    companion object {
        fun valueOf(v: Short): HWPControlCharType {
            for (cct in values())
                if (cct.value == v)
                    return cct
            return None
        }
    }
}

enum class HWPControlExtendType(v: Short) {
    None(0.toShort()),
    /**
     * 구역 정의/단 정의
     */
    SectionColumnDefine(2.toShort()),
    /**
     * 필드 시작
     * (누름틀, 하이퍼링크, 블록 책갈피, 표 계산식 문서 요약, 사용자 정보,...)
     */
    FieldStart(3.toShort()),
    /**
     * Gso Control/Table
     */
    GsoControl(11.toShort()),
    /**
     * Hidden Comment
     */
    HiddenComment(15.toShort()),
    /**
     * Header Footer
     */
    HeaderFooter(16.toShort()),
    /**
     * Foot/End note
     */
    FootEndNote(17.toShort()),
    /**
     * Auto Number
     */
    AutoNumber(18.toShort()),
    /**
     * Page Control
     * Hide, new Number...
     */
    PageControl(21.toShort()),
    /**
     * Bookmark, Index Mark
     */
    BookIndexMark(22.toShort()),
    /**
     * Additional Text, Overlapping Letter
     */
    AdditionalOverlappingText(23.toShort());

    var value: Short = v

    companion object {
        fun valueOf(v: Short) : HWPControlExtendType {
            for (cet in values())
                if (cet.value == v)
                    return cet
            return None
        }
    }
}

enum class HWPControlInlineType(v: Short) {
    None(0.toShort()),
    /**
     * 필드 끝
     */
    FieldEnd(4.toShort()),
    /**
     * title mark
     */
    TitleMark(8.toShort()),
    /**
     * Tab
     */
    Tab(9.toShort());

    var value: Short = v

    companion object {
        fun valueOf(v: Short) : HWPControlInlineType {
            for (cit in values())
                if (cit.value == v)
                    return cit
            return None
        }
    }
}