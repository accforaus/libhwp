package com.tang.hwplib.objects.bodytext.control

import com.tang.hwplib.objects.bodytext.control.bookmark.HWPCtrlData
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterItem
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterType
import com.tang.hwplib.objects.bodytext.control.ctrlheader.*
import com.tang.hwplib.objects.bodytext.control.equation.HWPEQEdit
import com.tang.hwplib.objects.bodytext.control.footnoteendnote.ListHeaderForFootnoteEndnote
import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControl
import com.tang.hwplib.objects.bodytext.control.gso.caption.HWPCaption
import com.tang.hwplib.objects.bodytext.control.gso.copyGsoControl
import com.tang.hwplib.objects.bodytext.control.headerfooter.ListHeaderForHeaderFooter
import com.tang.hwplib.objects.bodytext.control.hiddencomment.ListHeaderForHiddenComment
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPBatangPageInfo
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPFootEndNoteShape
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPPageBorderFill
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPPageDef
import com.tang.hwplib.objects.bodytext.control.table.HWPRow
import com.tang.hwplib.objects.bodytext.control.table.HWPTable
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraph
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphList

import com.tang.hwplib.objects.etc.*

/**
 * 컨트롤을 나타내는 객체
 *
 * @author accforaus
 *
 * @property [ctrlData] [HWPCtrlData] 컴트롤 임의의 데이터
 */
open class HWPControl(var header: HWPCtrlHeader?) {
    private var ctrlData: HWPCtrlData? = null

    /**
     * 컨트롤 유형을 반환하는 함수
     *
     * @return [HWPControlType] 컨트롤 유형 바환
     */
    fun getType() : HWPControlType = HWPControlType.ctrlIdOf(header!!.ctrlId)

    /**
     * 필드 컨트롤 유무를 반환하는 함수
     *
     * @return [Boolean] 필드 컨트롤 유무 반환
     */
    fun isField() : Boolean = HWPControlType.isField(header!!.ctrlId)

    /**
     * 컨트롤 임의의 데이터를 생성하는 함수
     */
    fun createCtrlData() {
        ctrlData = HWPCtrlData()
    }

    /**
     * 컨트롤 임의의 데이터를 반환하는 함수
     *
     * @return [HWPCtrlData] 컨트롤 임의의 데이터
     */
    fun getCtrlData(): HWPCtrlData? = ctrlData

    /**
     * 컨트롤 임의의 데이터를 설정하는 함수
     *
     * @param [ctrlData] [HWPCtrlData] 컨트롤 임의의 데이터
     */
    fun setCtrlData(ctrlData: HWPCtrlData?) {
        this.ctrlData = ctrlData
    }

    open fun copy() : HWPControl = HWPControl(null).also {
        this.ctrlData?.run { it.ctrlData = this.copy() }
    }
}

/**
 * 덧말 개체를 나타내는 객체
 * 18 bytes
 * @see [HWPControl]
 *
 * @author accforaus
 */
class HWPControlAdditionalText: HWPControl(HWPCtrlHeaderAdditionalText()) {
    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderAdditionalText] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderAdditionalText = header as HWPCtrlHeaderAdditionalText

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlAdditionalText] 복사된 객체 반환
     */
    override fun copy(): HWPControlAdditionalText = HWPControlAdditionalText().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlAdditionalText] 생성된 객체 반환
         */
        fun build() : HWPControlAdditionalText = HWPControlAdditionalText()
    }
}

/**
 * 자동 번호 개체를 나타내는 객체
 * 12 bytes
 * @see [HWPControl]
 *
 * @author accforaus
 */
class HWPControlAutoNumber: HWPControl(HWPCtrlHeaderAutoNumber()) {
    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderAutoNumber] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderAutoNumber = header as HWPCtrlHeaderAutoNumber

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlAutoNumber] 복사된 객체 반환
     */
    override fun copy(): HWPControlAutoNumber = HWPControlAutoNumber().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlAutoNumber] 생성된 객체 반환
         */
        fun build() : HWPControlAutoNumber = HWPControlAutoNumber()
    }
}

/**
 * 책갈피 개체를 나타내는 객체
 * Tag ID: HWPTAG_CTRL_DATA [CTRL_DATA]
 * 책갈피로서 갖는 정보로서 '책갈피 이름'밖에 없다
 * 컴트롤 임의의 데이터인 [CTRL_DATA]로 레코드 된다.
 * @see [HWPControl]
 *
 * @author accforaus
 */
class HWPControlBookmark: HWPControl(HWPCtrlHeaderBookmark()) {
    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderBookmark] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderBookmark = header as HWPCtrlHeaderBookmark

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlBookmark] 복사된 객체 반환
     */
    override fun copy(): HWPControlBookmark = HWPControlBookmark().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlBookmark] 생성된 객체 반환
         */
        fun build() : HWPControlBookmark = HWPControlBookmark()
    }
}

/**
 *  단 정의 개체를 나타내는 객체
 *  가변 길이
 *  @see [HWPControl]
 *
 *  @author accforaus
 */
class HWPControlColumnDefine: HWPControl(HWPCtrlHeaderColumnDefine()) {
    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderColumnDefine] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderColumnDefine = header as HWPCtrlHeaderColumnDefine

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlColumnDefine] 복사된 객체 반환
     */
    override fun copy(): HWPControlColumnDefine = HWPControlColumnDefine().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlColumnDefine] 생성된 객체 반환
         */
        fun build() : HWPControlColumnDefine = HWPControlColumnDefine()
    }
}

/**
 * 미주 개체를 나타내는 객체
 * 가변 길이
 * @see [HWPControl]
 *
 * @author accforaus
 *
 * @property [listHeader] [ListHeaderForFootnoteEndnote], 속성 (UINT32 - unsigned 4 bytes)
 * @property [paragraphList] [HWPParagraphList], 문단 리스트 (BYTE stream)
 */
class HWPControlEndNote: HWPControl(HWPCtrlHeaderEndnote()) {
    var listHeader: ListHeaderForFootnoteEndnote = ListHeaderForFootnoteEndnote()
    var paragraphList: HWPParagraphList = HWPParagraphList()

    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderEndnote] 컨트롤 헤더 반환
     */
    fun getHeader(): HWPCtrlHeaderEndnote = header as HWPCtrlHeaderEndnote

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlEndNote] 복사된 객체 반환
     */
    override fun copy(): HWPControlEndNote = HWPControlEndNote().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
        it.listHeader = this.listHeader.copy()
        it.paragraphList = this.paragraphList.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlEndNote] 생성된 객체 반환
         */
        fun build(listHeader: ListHeaderForFootnoteEndnote = ListHeaderForFootnoteEndnote.build(),
                  paragraphList: HWPParagraphList = HWPParagraphList.build())
                : HWPControlEndNote = HWPControlEndNote().apply {
            this.listHeader = listHeader
            this.paragraphList = paragraphList
        }
    }
}

/**
 * 한글 수식 개체를 나타내는 객체
 * Tag ID: HWPTAG_EQEDIT
 * 가변 길이
 * @see [HWPControl]
 *
 * @author accforaus
 *
 * @property [caption] [HWPCaption], 캡션 객체 (BYTE stream)
 * @property [eqEdit] [HWPEQEdit], 수식 개체 속성  (BYTE stream)
 */
class HWPControlEquation: HWPControl(HWPCtrlHeaderGso(HWPControlType.Equation)) {
    var caption: HWPCaption? = null
    var eqEdit: HWPEQEdit = HWPEQEdit()

    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderGso] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderGso = header as HWPCtrlHeaderGso

    /**
     * 캡션을 생성하는 함수
     */
    fun createCaption() {
        caption = HWPCaption()
    }

    /**
     * 캡션을 제거하는 함수
     */
    fun deleteCaption() {
        caption = null
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlEquation] 복사된 객체 반환
     */
    override fun copy(): HWPControlEquation = HWPControlEquation().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
        this.caption?.run { it.caption = this.copy() }
        it.eqEdit = this.eqEdit.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlEquation] 생성된 객체 반환
         */
        fun build(caption: HWPCaption? = null,
                  eqEdit: HWPEQEdit = HWPEQEdit.build())
                : HWPControlEquation = HWPControlEquation().apply {
            this.caption = caption
            this.eqEdit = eqEdit
        }
    }
}

/**
 * 필드 시작 개체를 나타내는 객체
 * 가변 길이
 * @see [HWPControl]
 *
 * @author accforaus
 *
 * @constructor [HWPCtrlHeaderField]로 헤더를 설정한다.
 * @constructor [HWPCtrlHeaderField(ctrlId)]로 헤더를 설정한다.
 */
class HWPControlField: HWPControl {
    var fieldName: String? = getName()
    constructor() : super(HWPCtrlHeaderField())
    constructor(ctrlId: Long) : super(HWPCtrlHeaderField(ctrlId))

    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderField] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderField = header as HWPCtrlHeaderField

    /**
     * 필드 컨트롤의 이름을 반환하는 함수
     *
     * @return [String] 필드 컨트롤의 이름 반환 (존재하지 않으면 NULL)
    */
    fun getName(): String? {
        if (getCtrlData() != null) {
            if (getCtrlData()!!.parameterSet.id == 0x021B) {
                val pi: HWPParameterItem? = getCtrlData()!!.parameterSet.getParameterItem(0x400)
                if (pi != null && pi.type == HWPParameterType.String)
                    return pi.value_BSTR
            }
        }
        return null
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlField] 복사된 객체 반환
     */
    override fun copy(): HWPControlField = HWPControlField().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlField] 생성된 객체 반환
         */
        fun build(ctrlId: Long = 0) : HWPControlField = HWPControlField(ctrlId)
    }
}

/**
 * 꼬리말을 나타내는 객체
 * 가변 길이
 * @see [HWPControl]
 *
 * @author accforaus
 *
 * @property [listHeader] [ListHeaderForHeaderFooter], 속성 (UINT32 - unsigned 4 bytes)
 * @property [paragraphList] [HWPParagraphList], 문단 리스트 (BYTE stream)
 */
class HWPControlFooter: HWPControl(HWPCtrlHeaderFooter()) {
    var listHeader: ListHeaderForHeaderFooter = ListHeaderForHeaderFooter()
    var paragraphList: HWPParagraphList = HWPParagraphList()

    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderFooter] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderFooter = header as HWPCtrlHeaderFooter

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlFooter] 복사된 객체 반환
     */
    override fun copy(): HWPControlFooter = HWPControlFooter().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
        it.listHeader = this.listHeader.copy()
        it.paragraphList = this.paragraphList.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlFooter] 생성된 객체 반환
         */
        fun build(listHeader: ListHeaderForHeaderFooter = ListHeaderForHeaderFooter.build(),
                  paragraphList: HWPParagraphList = HWPParagraphList.build())
                : HWPControlFooter = HWPControlFooter().apply {
            this.listHeader = listHeader
            this.paragraphList = paragraphList
        }
    }
}

/**
 * 각주 개체를 나타내는 객체
 * 가변 길이
 * @see [HWPControl]
 *
 * @author accforaus
 *
 * @property [listHeader] [ListHeaderForFootnoteEndnote], 속성 (UINT32 - unsigned 4 bytes)
 * @property [paragraphList] [HWPParagraphList], 문단 리스트 (BYTE stream)
 */
class HWPControlFootnote: HWPControl(HWPCtrlHeaderFootnote()) {
    var listHeader: ListHeaderForFootnoteEndnote = ListHeaderForFootnoteEndnote()
    var paragraphList: HWPParagraphList = HWPParagraphList()

    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderFootnote] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderFootnote = header as HWPCtrlHeaderFootnote

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlFootnote] 복사된 객체 반환
     */
    override fun copy(): HWPControlFootnote = HWPControlFootnote().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
        it.listHeader = this.listHeader.copy()
        it.paragraphList = this.paragraphList.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlFootnote] 생성된 객체 반환
         */
        fun build(listHeader: ListHeaderForFootnoteEndnote = ListHeaderForFootnoteEndnote.build(),
                  paragraphList: HWPParagraphList = HWPParagraphList.build())
                : HWPControlFootnote = HWPControlFootnote().apply {
            this.listHeader = listHeader
            this.paragraphList = paragraphList
        }
    }
}

/**
 * 머리말을 나타내는 객체
 * 가변 길이
 * @see [HWPControl]
 *
 * @author accforaus
 *
 * @property [listHeader] [ListHeaderForHeaderFooter], 속성 (UINT32 - unsigned 4 bytes)
 * @property [paragraphList] [HWPParagraphList], 문단 리스트 (BYTE stream)
 */
class HWPControlHeader: HWPControl(HWPCtrlHeaderHeader()) {
    var listHeader: ListHeaderForHeaderFooter = ListHeaderForHeaderFooter()
    var paragraphList: HWPParagraphList = HWPParagraphList()

    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderHeader] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderHeader = header as HWPCtrlHeaderHeader

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlHeader] 복사된 객체 반환
     */
    override fun copy(): HWPControlHeader = HWPControlHeader().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
        it.listHeader = this.listHeader.copy()
        it.paragraphList = this.paragraphList.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlHeader] 생성된 객체 반환
         */
        fun build(listHeader: ListHeaderForHeaderFooter = ListHeaderForHeaderFooter.build(),
                  paragraphList: HWPParagraphList = HWPParagraphList.build())
                : HWPControlHeader = HWPControlHeader().apply {
            this.listHeader = listHeader
            this.paragraphList = paragraphList
        }
    }
}

/**
 * 숨은 설명 개체를 나타내는 객체
 * 문단 리스트만을 포함한다
 * 문서 보안 레벨에 따라 숨은 설명 데이터들은 무효화될 수 있다.
 * @see [HWPControl]
 *
 * @author accforaus
 *
 * @property [listHeader] [ListHeaderForHiddenComment], 숨은 설명 문단 리스트 헤더
 * @property [paragraphList] [HWPParagraphList], 문단 리스트
 */
class HWPControlHiddenComment: HWPControl(HWPCtrlHeader(HWPControlType.HiddenComment.ctrlId)) {
    var listHeader: ListHeaderForHiddenComment = ListHeaderForHiddenComment()
    var paragraphList: HWPParagraphList = HWPParagraphList()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlHiddenComment] 복사된 객체 반환
     */
    override fun copy(): HWPControlHiddenComment = HWPControlHiddenComment().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = this.header?.copy()
        it.listHeader = this.listHeader.copy()
        it.paragraphList = this.paragraphList.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlHiddenComment] 생성된 객체 반환
         */
        fun build(listHeader: ListHeaderForHiddenComment = ListHeaderForHiddenComment.build(),
                  paragraphList: HWPParagraphList = HWPParagraphList.build())
                : HWPControlHiddenComment = HWPControlHiddenComment().apply {
            this.listHeader = listHeader
            this.paragraphList = paragraphList
        }
    }
}

/**
 * 찾아보기 표식 개체를 나타내는 객체
 * 가변 길이
 * @see [HWPControl]
 *
 * @author accforaus
 */
class HWPControlIndexMark: HWPControl(HWPCtrlHeaderIndexMark()) {
    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderIndexMark] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderIndexMark = header as HWPCtrlHeaderIndexMark

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlIndexMark] 복사된 객체 반환
     */
    override fun copy(): HWPControlIndexMark = HWPControlIndexMark().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlIndexMark] 생성된 객체 반환
         */
        fun build() : HWPControlIndexMark = HWPControlIndexMark()
    }
}

/**
 * 새 번호 지정 개체를 나타내는 객체
 * 8 bytes
 * @see [HWPControl]
 *
 * @author accforaus
 */
class HWPControlNewNumber: HWPControl(HWPCtrlHeaderNewNumber()) {
    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderNewNumber] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderNewNumber = header as HWPCtrlHeaderNewNumber

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlNewNumber] 복사된 객체 반환
     */
    override fun copy(): HWPControlNewNumber = HWPControlNewNumber().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlNewNumber] 생성된 객체 반환
         */
        fun build() : HWPControlNewNumber = HWPControlNewNumber()
    }
}

/**
 * 글자 겹침 개체를 나타내는 객체
 * 가변 길이
 * @see [HWPControl]
 *
 * @author accforaus
 */
class HWPControlOverlappingLetter: HWPControl(HWPCtrlHeaderOverlappingLetter()) {
    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderOverlappingLetter] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderOverlappingLetter = header as HWPCtrlHeaderOverlappingLetter

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlOverlappingLetter] 복사된 객체 반환
     */
    override fun copy(): HWPControlOverlappingLetter = HWPControlOverlappingLetter().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlOverlappingLetter] 생성된 객체 반환
         */
        fun build() : HWPControlOverlappingLetter = HWPControlOverlappingLetter()
    }
}

/**
 * 감추기 개체를 나타내는 객체
 * 2 bytes
 * @see [HWPControl]
 *
 * @author accforaus
 */
class HWPControlPageHide: HWPControl(HWPCtrlHeaderPageHide()) {
    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderPageHide] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderPageHide = header as HWPCtrlHeaderPageHide

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlPageHide] 복사된 객체 반환
     */
    override fun copy(): HWPControlPageHide = HWPControlPageHide().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlPageHide] 생성된 객체 반환
         */
        fun build() : HWPControlPageHide = HWPControlPageHide()
    }
}

/**
 * 홀/짝수 조정 개체를 나타내는 객체
 * 4 bytes
 * @see [HWPControl]
 *
 * @author accforaus
 */
class HWPControlPageOddEvenAdjust: HWPControl(HWPCtrlHeaderPageOddEvenAdjust()) {
    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderPageOddEvenAdjust] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderPageOddEvenAdjust = header as HWPCtrlHeaderPageOddEvenAdjust

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlPageOddEvenAdjust] 복사된 객체 반환
     */
    override fun copy(): HWPControlPageOddEvenAdjust = HWPControlPageOddEvenAdjust().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlPageOddEvenAdjust] 생성된 객체 반환
         */
        fun build() : HWPControlPageOddEvenAdjust = HWPControlPageOddEvenAdjust()
    }
}

/**
 * 쪽 번호 위치 개체를 나타내는 객체
 * 12 bytes
 * @see [HWPControl]
 *
 * @author accforaus
 */
class HWPControlPageNumberPosition: HWPControl(HWPCtrlHeaderPageNumberPosition()) {
    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderPageNumberPosition] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderPageNumberPosition = header as HWPCtrlHeaderPageNumberPosition

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlPageNumberPosition] 복사된 객체 반환
     */
    override fun copy(): HWPControlPageNumberPosition = HWPControlPageNumberPosition().also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlPageNumberPosition] 생성된 객체 반환
         */
        fun build() : HWPControlPageNumberPosition = HWPControlPageNumberPosition()
    }
}

/**
 * 구역 정의를 나타내는 객체
 * 140 bytes
 * @see [HWPControl]
 *
 * @author accforaus
 *
 * @property [pageDef] [HWPPageDef],  용지 설정 정보 (BYTE stream - unsigned 40 bytes)
 * @property [footnoteShape] [HWPFootEndNoteShape], 각주 모양 정보 (BYTE stream - unsigned 26 bytes)
 * @property [endnoteShape] [HWPFootEndNoteShape], 미주 모양 정보 (BYTE stream - unsigned 26 bytes)
 * @property [bothPageBorderFill] [HWPPageBorderFill], 양 쪽 바탕쪽의 내용이 있으면 바탕쪽 정보를 얻는다 (BYTE stream - unsigned 10 bytes)
 * @property [evenPageBorderFill] [HWPPageBorderFill], 홀수 쪽 바탕쪽의 내용이 있으면 바탕쪽 정보를 얻는다 (BYTE stream - unsigned 10 bytes)
 * @property [oddPageBorderFill] [HWPPageBorderFill], 짝수 쪽 바탕쪽의 내용이 있으면 바탕쪽 정보를 얻는다 (BYTE stream - unsigned 10 bytes)
 * @property [batangPageInfoList] [ArrayList], 바탕쪽 장보, 문단 리스트를 포함한다. (BYTE stream - unsigned 6 bytes)
 */
class HWPControlSectionDefine: HWPControl(HWPCtrlHeaderSectionDefine()) {
    var pageDef: HWPPageDef = HWPPageDef()
    var footnoteShape: HWPFootEndNoteShape = HWPFootEndNoteShape()
    var endnoteShape: HWPFootEndNoteShape = HWPFootEndNoteShape()
    var bothPageBorderFill: HWPPageBorderFill = HWPPageBorderFill()
    var evenPageBorderFill: HWPPageBorderFill = HWPPageBorderFill()
    var oddPageBorderFill: HWPPageBorderFill = HWPPageBorderFill()
    var batangPageInfoList: ArrayList<HWPBatangPageInfo> = ArrayList()

    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderSectionDefine] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderSectionDefine = header as HWPCtrlHeaderSectionDefine

    /**
     * 바탕쪽 정보를 추가하고 반환하는 함수
     *
     * @return [HWPBatangPageInfo] 생성된 객체 반환
     */
    fun addNewBatangPageInfo() : HWPBatangPageInfo = HWPBatangPageInfo().apply { batangPageInfoList.add(this) }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPControlSectionDefine] 복사된 객체 반환
     */
    override fun copy(): HWPControlSectionDefine =  HWPControlSectionDefine().also {
        super.copy()
        it.header = getHeader().copy()
        it.pageDef = this.pageDef.copy()
        it.footnoteShape = this.footnoteShape.copy()
        it.endnoteShape = this.endnoteShape.copy()
        it.bothPageBorderFill = this.bothPageBorderFill.copy()
        it.evenPageBorderFill = this.evenPageBorderFill.copy()
        it.oddPageBorderFill = this.oddPageBorderFill.copy()
        for (batangPage in this.batangPageInfoList) it.batangPageInfoList.add(batangPage.copy())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlSectionDefine] 생성된 객체 반환
         */
        fun build(pageDef: HWPPageDef = HWPPageDef.build(),
                  footnoteShape: HWPFootEndNoteShape = HWPFootEndNoteShape.build(),
                  endnoteShape: HWPFootEndNoteShape = HWPFootEndNoteShape.build(),
                  bothPageBorderFill: HWPPageBorderFill = HWPPageBorderFill.build(),
                  evenPageBorderFill: HWPPageBorderFill = HWPPageBorderFill.build(),
                  oddPageBorderFill: HWPPageBorderFill = HWPPageBorderFill.build(),
                  batangPageGenerator: () -> ArrayList<HWPBatangPageInfo> = {ArrayList()})
                : HWPControlSectionDefine = HWPControlSectionDefine().apply {
            this.pageDef = pageDef
            this.footnoteShape = footnoteShape
            this.endnoteShape = endnoteShape
            this.bothPageBorderFill = bothPageBorderFill
            this.evenPageBorderFill = evenPageBorderFill
            this.oddPageBorderFill = oddPageBorderFill
            this.batangPageInfoList = batangPageGenerator()
        }
    }
}

/**
 * 표 개체를 나타내는 객체
 * Tag ID: HWPTAG_TABLE [TABLE]
 * 가변 길이
 * @see [HWPControl]
 *
 * @author accforaus
 *
 * @constructor [HWPCtrlHeaderGso] 데이터를 헤더값으로 설정한다.
 * @constructor [HWPCtrlHeader]을 인수값으로 헤더값을 설정한다.
 *
 * @property [table] [HWPTable], 표 개체 속성
 * @property [rowList] [ArrayList], 셀 리스트 (셀 size x 셀 개수)
 * @property [caption] [HWPCaption] 캡션 객체
 */
class HWPControlTable: HWPControl {
    var table: HWPTable = HWPTable()
    var rowList: ArrayList<HWPRow> = ArrayList()
    var caption: HWPCaption? = null

    constructor() : this(HWPCtrlHeaderGso(HWPControlType.Table))
    constructor(header: HWPCtrlHeader) : super(header)

    /**
     * 새로운 [HWPRow]를 생성하고 반환하는 함수
     *
     * @return [HWPRow] 생성된 객체 반환
     */
    fun addNewRow() : HWPRow = HWPRow().apply { rowList.add(this) }

    /**
     * 컨트롤 헤더를 반환하는 함수
     *
     * @return [HWPCtrlHeaderGso] 컨트롤 헤더 반환
     */
    fun getHeader() : HWPCtrlHeaderGso = header as HWPCtrlHeaderGso

    /**
     * 캡션을 생성하는 함수
     */
    fun createCaption() { caption = HWPCaption() }

    /**
     * 캡션을 제거하는 함수
     */
    fun deleteCaption() { caption = null }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPTable] 복사된 객체 반환
     */
    override fun copy(): HWPControlTable = HWPControlTable(HWPCtrlHeaderGso(HWPControlType.Table)).also {
        it.setCtrlData(super.copy().getCtrlData())
        it.header = getHeader().copy()
        it.table = this.table.copy()
        for (row in this.rowList) it.rowList.add(row.copy())
        this.caption?.run { it.caption = this.copy() }
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPControlTable] 생성된 객체 반환
         */
        fun build(table: HWPTable = HWPTable.build(),
                  rowGenerator: () -> ArrayList<HWPRow> = {ArrayList()},
                  caption: HWPCaption? = null)
                : HWPControlTable = HWPControlTable(HWPCtrlHeaderGso(HWPControlType.Table)).apply {
            this.table = table
            this.rowList = rowGenerator()
            this.caption = caption
        }
    }
}

/**
 * 컨트롤 객체를 생성하는 함수
 *
 * @author accforaus
 *
 * @param [ctrlId] [Long], 컨트롤 ID
 * @return [HWPControl] 생성된 컨트롤 객체 반환 (존재하지 않으면 null)
 */
fun createHWPControl(ctrlId: Long): HWPControl? {
    /**
     * 필드 컨트롤을 제외한 컨트롤 객체를 생성하는 함수
     *
     * @param [ctrlId] [Long], 컨트롤 ID
     * @return [HWPControl] 생성된 컨트롤 객체 (존재하지 않으면 null)
     */
    fun create(ctrlId: Long) : HWPControl? = when(ctrlId) {
        HWPControlType.SectionDefine.ctrlId -> HWPControlSectionDefine()
        HWPControlType.ColumnDefine.ctrlId -> HWPControlColumnDefine()
        HWPControlType.Table.ctrlId -> HWPControlTable()
        HWPControlType.Equation.ctrlId -> HWPControlEquation()
        HWPControlType.Header.ctrlId -> HWPControlHeader()
        HWPControlType.Footer.ctrlId -> HWPControlFooter()
        HWPControlType.Footnote.ctrlId -> HWPControlFootnote()
        HWPControlType.Endnote.ctrlId -> HWPControlEndNote()
        HWPControlType.AutoNumber.ctrlId -> HWPControlAutoNumber()
        HWPControlType.NewNumber.ctrlId -> HWPControlNewNumber()
        HWPControlType.PageHide.ctrlId -> HWPControlPageHide()
        HWPControlType.PageOddEvenAdjust.ctrlId -> HWPControlPageOddEvenAdjust()
        HWPControlType.PageNumberPosition.ctrlId -> HWPControlPageNumberPosition()
        HWPControlType.IndexMark.ctrlId -> HWPControlIndexMark()
        HWPControlType.Bookmark.ctrlId -> HWPControlBookmark()
        HWPControlType.OverlappingLetter.ctrlId -> HWPControlOverlappingLetter()
        HWPControlType.AdditionalText.ctrlId -> HWPControlAdditionalText()
        HWPControlType.HiddenComment.ctrlId -> HWPControlHiddenComment()
        else -> null
    }
    if (HWPControlType.isField(ctrlId))
        return HWPControlField(ctrlId)
    else
        return create(ctrlId)
}

/**
 * 컨트롤을 복사하고 반환하는 함수
 *
 * @param [control] [HWPControl] 컨트롤 객체
 * @return [HWPControl] 복사된 객체 반환 (존재하지 않으면 NULL)
 */
fun copyControl(control: HWPControl) : HWPControl? = when(control) {
    is HWPControlSectionDefine -> control.copy()
    is HWPControlColumnDefine -> control.copy()
    is HWPControlTable -> control.copy()
    is HWPControlEquation -> control.copy()
    is HWPControlHeader -> control.copy()
    is HWPControlFooter -> control.copy()
    is HWPControlFootnote -> control.copy()
    is HWPControlEndNote -> control.copy()
    is HWPControlAutoNumber -> control.copy()
    is HWPControlNewNumber -> control.copy()
    is HWPControlPageHide -> control.copy()
    is HWPControlPageOddEvenAdjust -> control.copy()
    is HWPControlPageNumberPosition -> control.copy()
    is HWPControlIndexMark -> control.copy()
    is HWPControlBookmark -> control.copy()
    is HWPControlOverlappingLetter -> control.copy()
    is HWPControlAdditionalText -> control.copy()
    is HWPControlHiddenComment -> control.copy()
    is HWPControlField -> control.copy()
    is HWPGsoControl -> copyGsoControl(control)
    else -> null
}