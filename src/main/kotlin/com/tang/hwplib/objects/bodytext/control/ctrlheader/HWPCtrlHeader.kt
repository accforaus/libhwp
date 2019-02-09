package com.tang.hwplib.objects.bodytext.control.ctrlheader

import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.control.ctrlheader.additionaltext.HWPAdditionalTextPosition
import com.tang.hwplib.objects.bodytext.control.ctrlheader.autonumber.HWPAutoNumberHeaderProperty
import com.tang.hwplib.objects.bodytext.control.ctrlheader.columndefine.HWPColumnDefineHeaderProperty
import com.tang.hwplib.objects.bodytext.control.ctrlheader.columndefine.HWPColumnInfo
import com.tang.hwplib.objects.bodytext.control.ctrlheader.field.HWPFieldHeaderProperty
import com.tang.hwplib.objects.bodytext.control.ctrlheader.gso.HWPGsoHeaderProperty
import com.tang.hwplib.objects.bodytext.control.ctrlheader.header.HWPHeaderFooterApplyPage
import com.tang.hwplib.objects.bodytext.control.ctrlheader.newnumber.HWPNewNumberHeaderProperty
import com.tang.hwplib.objects.bodytext.control.ctrlheader.pagehide.HWPPageHideHeaderProperty
import com.tang.hwplib.objects.bodytext.control.ctrlheader.pagenumberposition.PageNumberPositionHeaderProperty
import com.tang.hwplib.objects.bodytext.control.ctrlheader.pageoddevenadjust.PageOddEvenAdjustHeaderProperty
import com.tang.hwplib.objects.bodytext.control.ctrlheader.sectiondefine.HWPSectionDefineHeaderProperty
import com.tang.hwplib.objects.bodytext.control.sectiondefine.HWPNumberShape
import com.tang.hwplib.objects.docinfo.HWPCharShape
import com.tang.hwplib.objects.docinfo.HWPParaShape
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderThickness
import com.tang.hwplib.objects.docinfo.borderfill.HWPBorderType
import com.tang.hwplib.objects.docinfo.parashape.HWPAlignment
import com.tang.hwplib.objects.etc.Color4Byte

/**
 * 컨트롤 헤더를 나타내는 객체
 *
 * @author accforaus
 *
 * @property [ctrlId] [Long], 컨트롤 ID
 */
open class HWPCtrlHeader(var ctrlId: Long) {
    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeader] 복사된 객체 반환
     */
    open fun copy(): HWPCtrlHeader = HWPCtrlHeader(this.ctrlId)
}

/**
 * 덧말 컨트롤 헤더를 나타내는 객체
 * 18 bytes
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @property [mainText] [String], main Text (WCHAR - unsigned 2 bytes)
 * @property [subText] [String], sub Text (WCHAR - unsigned 2 bytes)
 * @property [position] [HWPAdditionalTextPosition], 덧말의 위치 (UINT32 - unsigned 4 bytes)
 * @property [fsizeratio] [Long], Fsizeratio (UINT32 - unsigned 4 bytes)
 * @property [option] [Long], Option (UINT32 - unsgiend 4 bytes)
 * @property [styleId] [Long], HWPStyle Number (UINT32 - unsigned 4 bytes)
 * @property [alignment] [HWPAlignment], 정렬 기준 (UINT32 - unsigned 4 bytes)
 */
class HWPCtrlHeaderAdditionalText : HWPCtrlHeader(HWPControlType.AdditionalText.ctrlId) {
    var mainText: String? = null
    var subText: String? = null
    var position: HWPAdditionalTextPosition? = null
    var fsizeratio: Long = 0
    var option: Long = 0
    var styleId: Long = 0
    var alignment: HWPAlignment? = null

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderAdditionalText] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderAdditionalText = HWPCtrlHeaderAdditionalText().also {
        super.copy()
        it.mainText = this.mainText
        it.subText = this.subText
        this.position?.run { it.position = HWPAdditionalTextPosition.valueOf(this.value) }
        it.fsizeratio = this.fsizeratio
        it.option = this.option
        it.styleId = this.styleId
        this.alignment?.run { it.alignment = HWPAlignment.valueOf(this.value) }
    }
}

/**
 * 자동 번호 컨트롤 헤더를 나타내는 객체
 * 12 bytes
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @property [property] [HWPAutoNumberHeaderProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [number] [Int], 번호 (UINT16 - unsigned 2 bytes)
 * @property [userSymbol] [String], 사용자 기호 (WCHAR - unsigned 2 bytes)
 * @property [beforeDecorationLetter] [String], 앞 장식 문자 (WCHAR - unsigned 2 bytes)
 * @property [afterDecorationLetter] [String], 뒤 장식 문자 (WCHAR - unsigned 2 bytes)
 */
class HWPCtrlHeaderAutoNumber: HWPCtrlHeader(HWPControlType.AutoNumber.ctrlId) {
    var property: HWPAutoNumberHeaderProperty = HWPAutoNumberHeaderProperty()
    var number: Int = 0
    var userSymbol: String? = null
    var beforeDecorationLetter: String? = null
    var afterDecorationLetter: String? = null

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderAutoNumber] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderAutoNumber = HWPCtrlHeaderAutoNumber().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        it.property.value = this.property.value
        it.number = this.number
        it.userSymbol = this.userSymbol
        it.beforeDecorationLetter = this.beforeDecorationLetter
        it.afterDecorationLetter = this.afterDecorationLetter
    }
}

/**
 * 책갈피 헤더를 나타내는 객체
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 */
class HWPCtrlHeaderBookmark: HWPCtrlHeader(HWPControlType.Bookmark.ctrlId) {
    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderBookmark] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderBookmark = HWPCtrlHeaderBookmark().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
    }
}

/**
 * 단 정의 컨트롤 헤더를 나타내는 객체
 * 가변 길이
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @property [property] [HWPColumnDefineHeaderProperty], 속성의 bit 0-15 (UINT16 - unsigned 2 bytes)
 * @property [gapBetweenColumn] [Int], 단 사이 간격 (HWPUNIT16 - unsigned 2 bytes)
 * @property [property2] [Int], 속성의 bit 16-32 (UINT16 - unsigned 2 bytes)
 * @property [columnInfoList] [ArrayList], 단 너비가 동일하지 않으면 단의 수만큼 단의 폭
 * @property [divideLineSort] [HWPBorderType], 단 구분선 종류 (UINT8 - unsigned 1 byte)
 * @property [divideLineThickness] [HWPBorderThickness], 단 구분선 굵기 (UINT8 - unsigned 1 byte)
 * @property [divideLineColor] [Color4Byte], 단 구분선 색상 (COLORREF - unsigned 4 bytes)
 */
class HWPCtrlHeaderColumnDefine: HWPCtrlHeader(HWPControlType.ColumnDefine.ctrlId) {
    var property: HWPColumnDefineHeaderProperty = HWPColumnDefineHeaderProperty()
    var gapBetweenColumn: Int = 0
    var property2: Int = 0
    var columnInfoList: ArrayList<HWPColumnInfo> = ArrayList()
    var divideLineSort: HWPBorderType? = null
    var divideLineThickness: HWPBorderThickness? = null
    var divideLineColor: Color4Byte = Color4Byte()

    /**
     * 단 정보를 추가하고 반환하는 함수
     *
     * @return [HWPColumnInfo] 생성된 객체 반환
     */
    fun addNewColumnInfo() : HWPColumnInfo = HWPColumnInfo().also { columnInfoList.add(it) }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderColumnDefine] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderColumnDefine = HWPCtrlHeaderColumnDefine().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        it.property.value = this.property.value
        it.gapBetweenColumn = this.gapBetweenColumn
        it.property2 = this.property2
        for (columnInfo in this.columnInfoList) it.columnInfoList.add(columnInfo.copy())
        this.divideLineSort?.run { it.divideLineSort = HWPBorderType.valueOf(this.value) }
        this.divideLineThickness?.run { it.divideLineThickness = HWPBorderThickness.valueOf(this.value) }
        it.divideLineColor.value = this.divideLineColor.value
    }
}

/**
 * 필드 시작 컨트롤 헤더를 나타내는 객체
 * 가변 길이
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @constructor [HWPControlType.FIELD_UNKNOWN.ctrlId]로 헤더값을 설정한다.
 * @constructor [ctrlId]로 헤더값을 설정한다.
 *
 * @property [property] [HWPFieldHeaderProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [etcProperty] [Short], 기타 속성 (BYTE - unsigned 1 byte)
 * @property [command] [String], command (각 필드 종류마다 처리해야할 고유 정보) (WCHAR array)
 * @property [instanceId] [Long], ID (문서 내 고유 아이디) (UINT32 - unsigned 4 bytes)
 * @property [memoIndex] [Int], memo index
 */
class HWPCtrlHeaderField: HWPCtrlHeader {
    var property: HWPFieldHeaderProperty = HWPFieldHeaderProperty()
    var etcProperty: Short = 0
    var command: String? = null
    var instanceId: Long = 0
    var memoIndex: Int = 0

    constructor() : this(HWPControlType.FIELD_UNKNOWN.ctrlId)
    constructor(ctrlId: Long) : super(ctrlId)

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderField] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderField = HWPCtrlHeaderField(HWPControlType.FIELD_UNKNOWN.ctrlId).also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        it.property.value = this.property.value
        it.etcProperty = this.etcProperty
        it.command = this.command
        it.instanceId = this.instanceId
        it.memoIndex = this.memoIndex
    }
}

/**
 * 미주 컨트롤 헤더를 나타내는 객체
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @property [number] [Long], 숫자 (UINT32 - unsigned 4 bytes)
 * @property [beforeDecorationLetter] [String], 앞 장식 문자 (WCHAR - unsigned 2 bytes)
 * @property [afterDecorationLetter] [String], 뒤 장식 문자 (WCHAR - unsigned 2 bytes)
 * @property [numberShape] [HWPNumberShape], 숫자 모양
 * @property [instanceId] [Long], 고유 ID (instance ID) (UINT32 - unsigned 4 bytes)
 */
class HWPCtrlHeaderEndnote: HWPCtrlHeader(HWPControlType.Endnote.ctrlId) {
    var number: Long = 0
    var beforeDecorationLetter: String? = null
    var afterDecorationLetter: String? = null
    var numberShape: HWPNumberShape? = null
    var instanceId: Long = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderEndnote] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderEndnote = HWPCtrlHeaderEndnote().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        it.number = this.number
        it.beforeDecorationLetter = this.beforeDecorationLetter
        it.afterDecorationLetter = this.afterDecorationLetter
        this.numberShape?.run { it.numberShape = HWPNumberShape.valueOf(this.value) }
        it.instanceId = this.instanceId
    }
}

/**
 * 꼬리말 컨트롤 헤더를 나타내는 객체
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @property [applyPage] [HWPHeaderFooterApplyPage], 속성 (UINT32 - unsignegd 4 bytes)
 * @property [createIndex] [Int], create Index
 */
class HWPCtrlHeaderFooter: HWPCtrlHeader(HWPControlType.Footer.ctrlId) {
    var applyPage: HWPHeaderFooterApplyPage? = null
    var createIndex: Int = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderFooter] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderFooter = HWPCtrlHeaderFooter().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        this.applyPage?.run { it.applyPage = HWPHeaderFooterApplyPage.valueOf(this.value) }
        it.createIndex = this.createIndex
    }
}

/**
 * 각주 컨트롤 헤더를 나타내는 객체
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @property [number] [Long], 숫자 (UINT32 - unsigned 4 bytes)
 * @property [beforeDecorationLetter] [String], 앞 장식 문자 (WCHAR - unsigned 2 bytes)
 * @property [afterDecorationLetter] [String], 뒤 장식 문자 (WCHAR - unsigned 2 bytes)
 * @property [numberShape] [HWPNumberShape], 숫자 모양
 * @property [instanceId] [Long], 고유 ID (instance ID) (UINT32 - unsigned 4 bytes)
 */
class HWPCtrlHeaderFootnote: HWPCtrlHeader(HWPControlType.Footnote.ctrlId) {
    var number: Long = 0
    var beforeDecorationLetter: String? = null
    var afterDecorationLetter: String? = null
    var numberShape: HWPNumberShape? = null
    var instanceId: Long = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderFootnote] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderFootnote = HWPCtrlHeaderFootnote().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        it.number = this.number
        it.beforeDecorationLetter = this.beforeDecorationLetter
        it.afterDecorationLetter = this.afterDecorationLetter
        this.numberShape?.run { it.numberShape = HWPNumberShape.valueOf(this.value) }
        it.instanceId = this.instanceId
    }
}

/**
 * 머리말 컨트롤 헤더를 나타내는 객체
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @property [applyPage] [HWPHeaderFooterApplyPage], 속성 (UINT32 - unsignegd 4 bytes)
 * @property [createIndex] [Int], create Index
 */
class HWPCtrlHeaderHeader: HWPCtrlHeader(HWPControlType.Header.ctrlId) {
    var applyPage: HWPHeaderFooterApplyPage? = null
    var createIndex: Int = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderHeader] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderHeader = HWPCtrlHeaderHeader().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        this.applyPage?.run { it.applyPage = HWPHeaderFooterApplyPage.valueOf(this.value) }
        it.createIndex = this.createIndex
    }
}

/**
 * Gso 컨트롤 헤더를 나타내는 객체
 * 개체 공통 속성
 * 가변 길이
 *
 * @author accforaus
 * @see [HWPCtrlHeader]
 *
 * @property [property] [HWPGsoHeaderProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [yOffset] [Long], 세로 오프셋 값 (HWPUNIT - unsigned 4 bytes)
 * @property [xOffset] [Long], 가로 오프셋 값 (HWPUNIT - unsigned 4 bytes)
 * @property [width] [Long], width 오브젝트의 폭 (HWPUNIT - unsigned 4 bytes)
 * @property [height] [Long], height 오브젝트의 높이 (HWPUNIT - unsigned 4 bytes)
 * @property [zOrder] [Int], z-order (INT32 - signed 4 bytes)
 * @property [outerMarginLeft] [Int], 오브젝트의 바깥 왼쪽 여백 (HWPUNIT16 - signed 2 bytes)
 * @property [outerMarginRight] [Int], 오브젝트의 바깥 오른쪽 여백 (HWPUNIT16 - signed 2 bytes)
 * @property [outerMarginTop] [Int], 오브젝트의 바깥 위쪽 여백 (HWPUNIT16 - signed 2 bytes)
 * @property [outerMarginBottom] [Int], 오브젝트의 바깥 아래쪽 여백 (HWPUNIT16 - signed 2 bytes)
 * @property [instanceId] [Long], 문서 내 각 개체에 대한 고유 아이디 (instance ID) (UINT32 - unsigned 4 bytes)
 * @property [preventPageDivide] [Boolean], 쪽 나눔 방지 on(1) / off(0) (INT32 - signed 4 bytes)
 * @property [explanation] [String], 개채 설명문 글자 (WCHAR array[2] - signed 4 bytes)
 */
class HWPCtrlHeaderGso: HWPCtrlHeader {
    var property: HWPGsoHeaderProperty = HWPGsoHeaderProperty()
    var yOffset: Long = 0
    var xOffset: Long = 0
    var width: Long = 0
    var height: Long = 0
    var zOrder: Int = 0
    var outerMarginLeft: Int = 0
    var outerMarginRight: Int = 0
    var outerMarginTop: Int = 0
    var outerMarginBottom: Int = 0
    var instanceId: Long = 0
    var preventPageDivide: Boolean = false
    var explanation: String? = null

    constructor() : super(HWPControlType.Gso.ctrlId)
    constructor(controlType: HWPControlType) : super(controlType.ctrlId)

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderGso] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderGso = HWPCtrlHeaderGso().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        it.property.value = this.property.value
        it.yOffset = this.yOffset
        it.xOffset = this.xOffset
        it.width = this.width
        it.height = this.height
        it.zOrder = this.zOrder
        it.outerMarginLeft = this.outerMarginLeft
        it.outerMarginRight = this.outerMarginRight
        it.outerMarginTop = this.outerMarginTop
        it.outerMarginBottom = this.outerMarginBottom
        it.instanceId = this.instanceId
        it.preventPageDivide = this.preventPageDivide
        it.explanation = this.explanation
    }
}

/**
 * 새 번호 지정 컨트롤 헤더를 나타내는 객체
 * 8 bytes
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @property [property] [HWPNewNumberHeaderProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [number] [Int], 번호 (UINT16 - unsigned 2 bytes)
 */
class HWPCtrlHeaderNewNumber: HWPCtrlHeader(HWPControlType.NewNumber.ctrlId) {
    var property: HWPNewNumberHeaderProperty = HWPNewNumberHeaderProperty()
    var number: Int = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderNewNumber] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderNewNumber = HWPCtrlHeaderNewNumber().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        it.property.value = this.property.value
        it.number = this.number
    }
}

/**
 * 홀/짝수 조정 컨트롤 헤더를 나타내는 객체
 * 4 bytes
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @property [property] [PageOddEvenAdjustHeaderProperty], 속성 (UINT32 - unsigned 4 bytes)
 */
class HWPCtrlHeaderPageOddEvenAdjust: HWPCtrlHeader(HWPControlType.PageOddEvenAdjust.ctrlId) {
    var property: PageOddEvenAdjustHeaderProperty = PageOddEvenAdjustHeaderProperty()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderPageOddEvenAdjust] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderPageOddEvenAdjust = HWPCtrlHeaderPageOddEvenAdjust().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        it.property.value = this.property.value
    }
}

/**
 * 글가 겹침 컨트롤 헤더를 나타내는 객체
 * 가변 길이
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @property [overlappingLetterList] [ArrayList],  겹칠 글자 리스트
 * @property [borderType] [Short], 테두리 타임 (UINT8 - unsigned 1 byte)
 * @property [internalFontSize] [Byte], 내부 글자 크기 (INT8 - signed 1 byte)
 * @property [expendInsideLetter] [Short], 테두리 내부 글자 펼침 (UINT8 - unsigned 1 byte)
 * @property [charShapeIdList] [ArrayList], 테두리 네부 글자의 HWPCharShape[HWPCharShape] ID 리스트
 */
class HWPCtrlHeaderOverlappingLetter: HWPCtrlHeader(HWPControlType.OverlappingLetter.ctrlId) {
    var overlappingLetterList: ArrayList<String> = ArrayList()
    var borderType: Short = 0
    var internalFontSize: Byte = 0
    var expendInsideLetter: Short = 0
    var charShapeIdList: ArrayList<Long> = ArrayList()

    /**
     * 글자 겹침을 추가하는 함수
     *
     * @param [overlappingLetter] [String] 글자 겹침
     */
    fun addOverlappingLetter(overlappingLetter: String) {
        overlappingLetterList.add(overlappingLetter)
    }

    /**
     * 글자 모양[HWPCharShape]를 추가하는 함수
     *
     * @param [charShapeId] [Long], 글자 모양 ID
     */
    fun addCharShapeId(charShapeId: Long) {
        charShapeIdList.add(charShapeId)
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderOverlappingLetter] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderOverlappingLetter = HWPCtrlHeaderOverlappingLetter().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        for (overlappingLetter in this.overlappingLetterList) it.overlappingLetterList.add(overlappingLetter)
        it.borderType = this.borderType
        it.internalFontSize = this.internalFontSize
        it.expendInsideLetter = this.expendInsideLetter
        for (charShapeID in this.charShapeIdList) it.charShapeIdList.add(charShapeID)
    }
}

/**
 * 찾아보기 표식 헤더를 나타내는 객체
 * 가변 길이
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @property [keyword1] [String], 찾아보기에 사용할 첫 번째 키워드 (WCHAR array)
 * @property [keyword2] [String], 찾아보기에 사용할 두 번째 키워드 (WCHAR array)
 */
class HWPCtrlHeaderIndexMark: HWPCtrlHeader(HWPControlType.IndexMark.ctrlId) {
    var keyword1: String? = null
    var keyword2: String? = null

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderIndexMark] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderIndexMark = HWPCtrlHeaderIndexMark().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        it.keyword1 = this.keyword1
        it.keyword2 = this.keyword2
    }
}

/**
 * 감추기 컨트롤 헤더를 나타내는 객체
 * 2 bytes
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @property [property] [HWPPageHideHeaderProperty], 속성 (UINT32 - unsigned 4 bytes)
 */
class HWPCtrlHeaderPageHide: HWPCtrlHeader(HWPControlType.PageHide.ctrlId) {
    var property: HWPPageHideHeaderProperty = HWPPageHideHeaderProperty()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderPageHide] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderPageHide = HWPCtrlHeaderPageHide().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        it.property.value = this.property.value
    }
}

/**
 * 쪽 번호 위치 헤더를 나타내는 객체
 * 12 bytes
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @property [property] [PageNumberPositionHeaderProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [number] [Int], 번호 (UINT16 - unsigned 4 bytes)
 * @property [userSymbol] [String], 사용자 기호 (WCHAR - unsigned 2 bytes)
 * @property [beforeDecorationLetter] [String], 앞 장식 문자 (WCHAR - unsigned 2 bytes)
 * @property [afterDecorationLetter] [String], 뒤 장식 문자 (WCHAR - unsigned 2 bytes)
 */
class HWPCtrlHeaderPageNumberPosition: HWPCtrlHeader(HWPControlType.PageNumberPosition.ctrlId) {
    var property: PageNumberPositionHeaderProperty = PageNumberPositionHeaderProperty()
    var number: Int = 0
    var userSymbol: String? = null
    var beforeDecorationLetter: String? = null
    var afterDecorationLetter: String? = null

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderPageNumberPosition] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderPageNumberPosition = HWPCtrlHeaderPageNumberPosition().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        it.property.value = this.property.value
        it.number = this.number
        it.userSymbol = this.userSymbol
        it.beforeDecorationLetter = this.beforeDecorationLetter
        it.afterDecorationLetter = this.afterDecorationLetter
    }
}

/**
 * 구역 정보 헤더를 나타내는 객체
 * 26 bytes
 * @see [HWPCtrlHeader]
 *
 * @author accforaus
 *
 * @property [property] [HWPSectionDefineHeaderProperty], 속성 (UINT32 - unsigned 4 bytes)
 * @property [columnGap] [Int], 동일한 페이지에서 서로 다른 단 사이의 간격 (HWPUNIT16 - unsigned 2 bytes)
 * @property [verticalLineAlign] [Int], 세로로 줄맞춤을 할지 여부 (off(0), 1 - n 간격을 HWPUNIT 단위로 지정) (HWPUNIT16 - unsigned 2 bytes)
 * @property [horizontalLineAlign] [Int], 가로로 줄맞춤을 할지 여부 (off(0), 1 - n 간격을 HWPUNIT 단위로 지정) (HWPUNIT16 - unsigned 2 bytes)
 * @property [defaultTabGap] [Long], 기본 탭 간격 (HWPUNIT 또는 relative characters) (HWPUNIT - unsigned 4 bytes)
 * @property [numberParaShapeId] [Int], 번호 문단 모양[HWPParaShape] ID (UINT16 - unsigned 2 bytes)
 * @property [pageStartNumber] [Int], 쪽 번호 (UINT16 - unsigned 2 bytes)
 * @property [imageStartNumber] [Int], 그림 시작 번호 (UINT16 - unsigned 2 bytes)
 * @property [tableStartNumber] [Int], 표 시작 번호 (UINT16 - unsigned 2 bytes)
 * @property [equationStartNumber] [Int], 수식 시작 번호 (UINT16 - unsigned 2 bytes)
 * @property [defaultLanguage] [Int],, 대표 Language(Language값이 없으면 0, Application에 지정된 Language) [>=5.0.1.5] (UINT16 - unsigned 2 bytes)
 */
class HWPCtrlHeaderSectionDefine: HWPCtrlHeader(HWPControlType.SectionDefine.ctrlId) {
    var property: HWPSectionDefineHeaderProperty = HWPSectionDefineHeaderProperty()
    var columnGap: Int = 0
    var verticalLineAlign: Int = 0
    var horizontalLineAlign: Int = 0
    var defaultTabGap: Long = 0
    var numberParaShapeId: Int = 0
    var pageStartNumber: Int = 0
    var imageStartNumber: Int = 0
    var tableStartNumber: Int = 0
    var equationStartNumber: Int = 0
    var defaultLanguage: Int = 0

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlHeaderSectionDefine] 복사된 객체 반환
     */
    override fun copy(): HWPCtrlHeaderSectionDefine = HWPCtrlHeaderSectionDefine().also {
        super.copy().run {
            it.ctrlId = this.ctrlId
        }
        it.property.value = this.property.value
        it.columnGap = this.columnGap
        it.verticalLineAlign = this.verticalLineAlign
        it.horizontalLineAlign = this.horizontalLineAlign
        it.defaultTabGap = this.defaultTabGap
        it.numberParaShapeId = this.numberParaShapeId
        it.pageStartNumber = this.pageStartNumber
        it.imageStartNumber = this.imageStartNumber
        it.tableStartNumber = this.tableStartNumber
        it.equationStartNumber = this.equationStartNumber
        it.defaultLanguage = this.defaultLanguage
    }
}