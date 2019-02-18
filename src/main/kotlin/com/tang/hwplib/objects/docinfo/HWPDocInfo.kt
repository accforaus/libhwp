package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.builder.docinfo.*
import com.tang.hwplib.builder.etc.HWPDocInfoBuilderType
import com.tang.hwplib.copyto.docinfo.IDMappingTypes
import com.tang.hwplib.objects.HWPRecordHeader
import com.tang.hwplib.objects.bindata.HWPEmbeddedBinaryData
import com.tang.hwplib.objects.docinfo.facename.HWPFaceNameEnum
import com.tang.hwplib.objects.etc.UnknownRecord
import com.tang.hwplib.util.exceptions.HWPBuildException

/**
 * 문서 정보를 나타내는 객체
 *
 * @author accforaus
 *
 * @property [documentProperties] [HWPDocumentProperties], 문서 속성
 * @property [idMappings] [HWPIDMappings], 아이디 매핑 헤더
 * @property [binDataList] [ArrayList], BinData 리스트
 * @property [hangulFaceNameList] [ArrayList], 한글 글꼴
 * @property [englishFaceNameList] [ArrayList], 영어 글꼴
 * @property [hanjaFaceNameList] [ArrayList], 한자 글꼴
 * @property [japaneseFaceNameList] [ArrayList], 일본어 글꼴
 * @property [etcFaceNameList] [ArrayList], 기타 글꼴
 * @property [symbolFaceNameList] [ArrayList], 기호 글꼴
 * @property [userFaceNameList] [ArrayList], 사용자 글꼴
 * @property [borderFillList] [ArrayList], 테두리/배경
 * @property [charShapeList] [ArrayList], 글자 모양
 * @property [tabDefList] [ArrayList], 탭 정의
 * @property [numberingList] [ArrayList], 번호 정의
 * @property [bulletList] [ArrayList], 불릿 정의
 * @property [paraShapeList] [ArrayList], 문단 모양
 * @property [styleList] [ArrayList], 스타일
 * @property [docData] [UnknownRecord], 문서의 임의의 데이터
 * @property [distributeDocData] [UnknownRecord], 배포용 문서 데이터
 * @property [compatibleDocument] [HWPCompatibleDocument], 호환 문서
 * @property [layoutCompatibility] [HWPLayoutCompatibility], 레이아웃 호환성
 * @property [trackChange] [UnknownRecord], 변경 추적 정보
 * @property [memoShapeList] [ArrayList], 메모 모양
 * @property [forbiddenChar] [UnknownRecord], 금칙처리 문자
 * @property [trackChange2List] [ArrayList], 변경 추적 내용 및 문자
 * @property [trackChangeAuthorList] [ArrayList], 변경 추적 작성자
 */
class HWPDocInfo {
    var documentProperties: HWPDocumentProperties = HWPDocumentProperties()
    var idMappings: HWPIDMappings = HWPIDMappings()
    var binDataList: ArrayList<HWPBinData> = ArrayList()
    var hangulFaceNameList: ArrayList<HWPFaceName> = ArrayList()
    var englishFaceNameList: ArrayList<HWPFaceName> = ArrayList()
    var hanjaFaceNameList: ArrayList<HWPFaceName> = ArrayList()
    var japaneseFaceNameList: ArrayList<HWPFaceName> = ArrayList()
    var etcFaceNameList: ArrayList<HWPFaceName> = ArrayList()
    var symbolFaceNameList: ArrayList<HWPFaceName> = ArrayList()
    var userFaceNameList: ArrayList<HWPFaceName> = ArrayList()
    var borderFillList: ArrayList<HWPBorderFill> = ArrayList()
    var charShapeList: ArrayList<HWPCharShape> = ArrayList()
    var tabDefList: ArrayList<HWPTabDef> = ArrayList()
    var numberingList: ArrayList<HWPNumbering> = ArrayList()
    var bulletList: ArrayList<HWPBullet> = ArrayList()
    var paraShapeList: ArrayList<HWPParaShape> = ArrayList()
    var styleList: ArrayList<HWPStyle> = ArrayList()
    var docData: UnknownRecord? = null
    var distributeDocData: UnknownRecord? = null
    var compatibleDocument: HWPCompatibleDocument? = null
    var layoutCompatibility: HWPLayoutCompatibility? = null
    var trackChange: UnknownRecord? = null
    var memoShapeList: ArrayList<UnknownRecord> = ArrayList()
    var forbiddenChar: UnknownRecord? = null
    var trackChange2List: ArrayList<UnknownRecord> = ArrayList()
    var trackChangeAuthorList: ArrayList<UnknownRecord> = ArrayList()
    var binData: com.tang.hwplib.objects.bindata.HWPBinData? = null

    /**
     * 바이너리 데이터를 추가하고 반환하는 함수
     *
     * @return [HWPBinData] 생성된 객체 반환
     */
    fun addNewBinData(): HWPBinData {
        val bd: HWPBinData = HWPBinData()
        binDataList.add(bd)
        return bd
    }

    /**
     * 글꼴을 추가하고 반환하는 함수
     *
     * @param [target] [HWPFaceNameEnum], 글꼴 종류
     * @return [HWPFaceName] 생성된 객체 반환
     */
    fun addNewFaceName(target: HWPFaceNameEnum) : HWPFaceName = HWPFaceName().also {
        when (target) {
            HWPFaceNameEnum.HANGUL -> hangulFaceNameList.add(it)
            HWPFaceNameEnum.HANJA -> hanjaFaceNameList.add(it)
            HWPFaceNameEnum.JAPANESE -> japaneseFaceNameList.add(it)
            HWPFaceNameEnum.ETC -> etcFaceNameList.add(it)
            HWPFaceNameEnum.SYMBOL -> symbolFaceNameList.add(it)
            HWPFaceNameEnum.USER -> userFaceNameList.add(it)
            HWPFaceNameEnum.ENGLISH -> englishFaceNameList.add(it)
        }
    }

    fun proceedBinData(binDataName: String) : Int {
        fun findIndexByName() : Int {
            val embeddedList : ArrayList<HWPEmbeddedBinaryData> = binData?.embeddedBinaryDataList ?: throw HWPBuildException("BinData must be not null")
            for ((index, bin) in embeddedList.withIndex())
                if (bin.name.contains(binDataName))
                    return index + 1
            return -1
        }
        return findIndexByName()
    }

    fun proceedFaceName(name: String, type: HWPFaceNameEnum) : Int {
        fun findByName(faceNames: ArrayList<HWPFaceName>) : Int {
            for ((index, faceName) in faceNames.withIndex()) {
                if (faceName.name == name)
                    return index
            }
            return -1
        }
        when (type) {
            HWPFaceNameEnum.HANGUL -> return findByName(hangulFaceNameList)
            HWPFaceNameEnum.ENGLISH -> return findByName(englishFaceNameList)
            HWPFaceNameEnum.HANJA -> return findByName(hanjaFaceNameList)
            HWPFaceNameEnum.JAPANESE -> return findByName(japaneseFaceNameList)
            HWPFaceNameEnum.ETC -> return findByName(etcFaceNameList)
            HWPFaceNameEnum.SYMBOL -> return findByName(symbolFaceNameList)
            HWPFaceNameEnum.USER -> return findByName(userFaceNameList)
            HWPFaceNameEnum.ALL -> return findByName(hanjaFaceNameList)
        }
    }

    /**
     * 테두리/배경을 추가하고 반환하는 함수
     *
     * @return [HWPBorderFill] 생성된 객체 반환
     */
    fun addNewBorderFill() : HWPBorderFill = HWPBorderFill().also { borderFillList.add(it) }

    /**
     * 글자 모양을 추가하고 반환하는 함수
     *
     * @return [HWPCharShape] 생성된 객체 반환
     */
    fun addNewCharShape() : HWPCharShape = HWPCharShape().also { charShapeList.add(it) }

    /**
     * 탭 정의를 추가하고 반환하는 함수
     *
     * @return [HWPTabDef] 생성된 객체 반환
     */
    fun addNewTabDef() : HWPTabDef = HWPTabDef().also { tabDefList.add(it) }

    /**
     * 번호 정의를 추가하고 반환하는 함수
     *
     * @return [HWPNumbering] 생성된 객체 반환
     */
    fun addNewNumbering() : HWPNumbering = HWPNumbering().also { numberingList.add(it) }

    /**
     * 불릿을 추가하고 반환하는 함수
     *
     * @return [HWPBullet] 생성된 객체 반환
     */
    fun addNewBullet() : HWPBullet = HWPBullet().also { bulletList.add(it) }

    /**
     * 문단 모양을 추가 하고 반환하는 함수
     *
     * @return [HWPParaShape] 생성된 객체 반환
     */
    fun addNewParaShape() : HWPParaShape = HWPParaShape().also { paraShapeList.add(it) }

    /**
     * 스타일을 추가하고 반환하는 함수
     *
     * @return [HWPStyle] 생성된 객체 반환
     */
    fun addNewStyle() : HWPStyle = HWPStyle().also { styleList.add(it) }

    /**
     * 문서의 임의의 데이터를 생성하는 함수
     *
     * @param [rh] [HWPRecordHeader], 레코드 헤더
     */
    fun createDocData(rh: HWPRecordHeader) {
        docData = UnknownRecord(rh)
    }

    /**
     * 문서의 임의의 데이터를 제거하는 함수
     */
    fun deleteDocData() {
        docData = null
    }

    /**
     * 배포용 문서 데이터를 추가하는 함수
     *
     * @param [rh] [HWPRecordHeader], 레코드 헤더
     */
    fun createDistributeDocData(rh: HWPRecordHeader) {
        distributeDocData = UnknownRecord(rh)
    }

    /**
     * 배포용 문서 데이터를 제거하는 함수
     */
    fun deleteDistributeDocData() {
        distributeDocData = null
    }

    /**
     * 호환 문서를 생성하는 함수
     */
    fun createCompatibleDocument() {
        compatibleDocument = HWPCompatibleDocument()
    }

    /**
     * 호환 문서를 제거하는 함수
     */
    fun deleteCompatibleDocument() {
        compatibleDocument = null
    }

    /**
     * 레이아웃 호환성을 생성하는 함수
     */
    fun createLayoutCompatibility() {
        layoutCompatibility = HWPLayoutCompatibility()
    }

    /**
     * 레이아웃 호환성을 제거하는 함수
     */
    fun deleteLayoutCompatibility() {
        layoutCompatibility = null
    }

    /**
     * 변경 추적 정보를 생성하는 함수
     *
     * @param [rh] [HWPRecordHeader], 레코드 헤더
     */
    fun createTrackChange(rh: HWPRecordHeader) {
        trackChange = UnknownRecord(rh)
    }

    /**
     * 변경 추적 정보를 제거하는 함수
     */
    fun deleteTrackChange() {
        trackChange = null
    }

    /**
     * 메모 모양을 추가하고 반환하는 함수
     *
     * @param [rh] [HWPRecordHeader], 레코드 헤더
     * @return [HWPRecordHeader] 생성된 객체 반환
     */
    fun addNewMemoShape(rh: HWPRecordHeader) : UnknownRecord = UnknownRecord(rh).also { memoShapeList.add(it) }

    /**
     * 금칙처리 문자를 생성하는 함수
     *
     * @param [rh] [HWPRecordHeader], 레코드 헤더
     */
    fun createForbiddenChar(rh: HWPRecordHeader) {
        forbiddenChar = UnknownRecord(rh)
    }

    /**
     * 금칙 처리 문자를 제거하는 함수
     */
    fun deleteForbiddenChar() {
        forbiddenChar = null
    }

    /**
     * 변경 추적 내용 및 모양을 추가하고 반환하는 함수
     *
     * @param [rh] [HWPRecordHeader], 레코드 헤더
     * @return [UnknownRecord] 생성된 객체 반환
     */
    fun addNewTrackChange2(rh: HWPRecordHeader) : UnknownRecord = UnknownRecord(rh).also { trackChange2List.add(it) }

    /**
     * 변경 추적 작성자를 추가하고 반환하는 함수
     *
     * @param [rh] [HWPRecordHeader], 레코드 헤더
     * @return [UnknownRecord] 생성된 객체 번환
     */
    fun addNewTrackChangeAuthor2(rh: HWPRecordHeader) : UnknownRecord = UnknownRecord(rh).also { trackChangeAuthorList.add(it) }

    fun updateIDMappings(type: IDMappingTypes) {
        when (type) {
            IDMappingTypes.BINDATA -> {
                idMappings.binDataCount = idMappings.binDataCount + 1
            }
            IDMappingTypes.BULLET -> {
                idMappings.bulletCount = idMappings.bulletCount + 1
            }
            IDMappingTypes.TABDEF -> {
                idMappings.tabDefCount = idMappings.tabDefCount + 1
            }
            IDMappingTypes.CHARSHAPE -> {
                idMappings.charShapeCount = idMappings.charShapeCount + 1
            }
            IDMappingTypes.NUMBERING -> {
                idMappings.numberingCount = idMappings.numberingCount + 1
            }
            IDMappingTypes.PARASHAPE -> {
                idMappings.paraShapeCount = idMappings.paraShapeCount + 1
            }
            IDMappingTypes.BORDERFILL -> {
                idMappings.borderFillCount = idMappings.borderFillCount + 1
            }
            IDMappingTypes.ETC_FACENAME -> {
                idMappings.etcFaceNameCount = idMappings.etcFaceNameCount + 1
            }
            IDMappingTypes.SYMBOL_FACENAME -> {
                idMappings.symbolFaceNameCount = idMappings.symbolFaceNameCount + 1
            }
            IDMappingTypes.USER_FACENAME -> {
                idMappings.userFaceNameCount = idMappings.userFaceNameCount + 1
            }
            IDMappingTypes.HANJA_FACENAME -> {
                idMappings.hanjaFaceNameCount = idMappings.hanjaFaceNameCount + 1
            }
            IDMappingTypes.HANGUL_FACENAME -> {
                idMappings.hangulFaceNameCount = idMappings.hangulFaceNameCount + 1
            }
            IDMappingTypes.ENGLISH_FACENAME -> {
                idMappings.englishFaceNameCount = idMappings.englishFaceNameCount + 1
            }
            IDMappingTypes.JAPANESE_FACENAME -> {
                idMappings.japaneseFaceNameCount = idMappings.japaneseFaceNameCount + 1
            }
            IDMappingTypes.STYLE -> {
                idMappings.styleCount = idMappings.styleCount + 1
            }
        }
    }

    fun builderFactory(builderType: HWPDocInfoBuilderType) : HWPDocInfoBuilder = when (builderType) {
        HWPDocInfoBuilderType.BinData -> HWPBinDataBuilder(this)
        HWPDocInfoBuilderType.BorderFill -> HWPBorderFillBuilder(this)
        HWPDocInfoBuilderType.CharShape -> HWPCharShapeBuilder(this)
        HWPDocInfoBuilderType.Numbering -> HWPNumberingBuilder(this)
        HWPDocInfoBuilderType.ParaShape -> HWPParaShapeBuilder(this)
        HWPDocInfoBuilderType.Style -> HWPStyleBuilder(this)
        HWPDocInfoBuilderType.TabDef -> HWPTabDefBuilder(this)
        HWPDocInfoBuilderType.Bullet -> HWPBulletBuilder(this)
        HWPDocInfoBuilderType.Hangul -> HWPFaceNameBuilder(this, HWPFaceNameEnum.HANGUL)
        HWPDocInfoBuilderType.English -> HWPFaceNameBuilder(this, HWPFaceNameEnum.ENGLISH)
        HWPDocInfoBuilderType.Hanja -> HWPFaceNameBuilder(this, HWPFaceNameEnum.HANJA)
        HWPDocInfoBuilderType.Japanese -> HWPFaceNameBuilder(this, HWPFaceNameEnum.JAPANESE)
        HWPDocInfoBuilderType.Etc -> HWPFaceNameBuilder(this, HWPFaceNameEnum.ETC)
        HWPDocInfoBuilderType.Symbol -> HWPFaceNameBuilder(this, HWPFaceNameEnum.SYMBOL)
        HWPDocInfoBuilderType.User -> HWPFaceNameBuilder(this, HWPFaceNameEnum.USER)
        HWPDocInfoBuilderType.All -> HWPFaceNameBuilder(this, HWPFaceNameEnum.ALL)
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPDocInfo] 복사된 객체 반환
     */
    fun copy() : HWPDocInfo = HWPDocInfo().also {
        it.documentProperties = this.documentProperties.copy()
        it.idMappings = this.idMappings.copy()
        for (binData in this.binDataList) it.binDataList.add(binData.copy())
        for (hangul in this.hangulFaceNameList) it.hangulFaceNameList.add(hangul.copy())
        for (english in this.englishFaceNameList) it.englishFaceNameList.add(english.copy())
        for (hanja in this.hanjaFaceNameList) it.hanjaFaceNameList.add(hanja.copy())
        for (japanese in this.japaneseFaceNameList) it.japaneseFaceNameList.add(japanese.copy())
        for (etc in this.etcFaceNameList) it.etcFaceNameList.add(etc.copy())
        for (symbol in this.symbolFaceNameList) it.symbolFaceNameList.add(symbol.copy())
        for (user in this.userFaceNameList) it.userFaceNameList.add(user.copy())
        for (borderFill in this.borderFillList) it.borderFillList.add(borderFill.copy())
        for (charShape in this.charShapeList) it.charShapeList.add(charShape.copy())
        for (tab in this.tabDefList) it.tabDefList.add(tab.copy())
        for (numbering in this.numberingList) it.numberingList.add(numbering.copy())
        for (bullet in this.bulletList) it.bulletList.add(bullet.copy())
        for (paraShape in this.paraShapeList) it.paraShapeList.add(paraShape.copy())
        for (style in this.styleList) it.styleList.add(style.copy())
        this.docData?.run { it.docData = this.copy() }
        this.compatibleDocument?.run { it.compatibleDocument = this.copy() }
        this.layoutCompatibility?.run { it.layoutCompatibility = this.copy() }
        this.trackChange?.run { it.trackChange = this.copy() }
        for (memo in this.memoShapeList) it.memoShapeList.add(memo.copy())
        this.forbiddenChar?.run { it.forbiddenChar = this.copy() }
        for (trackChange in this.trackChange2List) it.trackChange2List.add(trackChange.copy())
        for (trackChangeAuthor in this.trackChangeAuthorList) it.trackChangeAuthorList.add(trackChangeAuthor.copy())
    }
}