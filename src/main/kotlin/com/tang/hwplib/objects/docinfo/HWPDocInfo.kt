package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.objects.HWPRecordHeader
import com.tang.hwplib.objects.docinfo.facename.HWPFaceNameEnum
import com.tang.hwplib.objects.etc.UnknownRecord

class HWPDocInfo {
    var documentProperties: HWPDocumentProperties = HWPDocumentProperties()
        private set
    var idMappings: HWPIDMappings = HWPIDMappings()
        private set
    var binDataList: ArrayList<HWPBinData> = ArrayList()
        private set
    var hangulFaceNameList: ArrayList<HWPFaceName> = ArrayList()
        private set
    var englishFaceNameList: ArrayList<HWPFaceName> = ArrayList()
        private set
    var hanjaFaceNameList: ArrayList<HWPFaceName> = ArrayList()
        private set
    var japaneseFaceNameList: ArrayList<HWPFaceName> = ArrayList()
        private set
    var etcFaceNameList: ArrayList<HWPFaceName> = ArrayList()
        private set
    var symbolFaceNameList: ArrayList<HWPFaceName> = ArrayList()
        private set
    var userFaceNameList: ArrayList<HWPFaceName> = ArrayList()
        private set
    var borderFillList: ArrayList<HWPBorderFill> = ArrayList()
        private set
    var charShapeList: ArrayList<HWPCharShape> = ArrayList()
        private set
    var tabDefList: ArrayList<HWPTabDef> = ArrayList()
        private set
    var numberingList: ArrayList<HWPNumbering> = ArrayList()
        private set
    var bulletList: ArrayList<HWPBullet> = ArrayList()
        private set
    var paraShapeList: ArrayList<HWPParaShape> = ArrayList()
        private set
    var styleList: ArrayList<HWPStyle> = ArrayList()
        private set
    var docData: UnknownRecord? = null
        private set
    var distributeDocData: UnknownRecord? = null
        private set
    var compatibleDocument: HWPCompatibleDocument? = null
        private set
    var layoutCompatibility: HWPLayoutCompatibility? = null
        private set
    var trackChange: UnknownRecord? = null
        private set
    var memoShapeList: ArrayList<UnknownRecord> = ArrayList()
        private set
    var forbiddenChar: UnknownRecord? = null
        private set
    var trackChange2List: ArrayList<UnknownRecord> = ArrayList()
        private set
    var trackChangeAuthorList: ArrayList<UnknownRecord> = ArrayList()
        private set

    fun addNewBinData(): HWPBinData {
        val bd: HWPBinData = HWPBinData()
        binDataList.add(bd)
        return bd
    }

    fun addNewFaceName(target: HWPFaceNameEnum) : HWPFaceName = HWPFaceName().also {
        when (target) {
            HWPFaceNameEnum.HANGUL -> hangulFaceNameList.add(it)
            HWPFaceNameEnum.HANJA -> hanjaFaceNameList.add(it)
            HWPFaceNameEnum.JAPANESE -> japaneseFaceNameList.add(it)
            HWPFaceNameEnum.ETC -> etcFaceNameList.add(it)
            HWPFaceNameEnum.SYMBOL -> symbolFaceNameList.add(it)
            HWPFaceNameEnum.USER -> userFaceNameList.add(it)
            HWPFaceNameEnum.LATIN -> englishFaceNameList.add(it)
        }
    }

    fun addNewBorderFill() : HWPBorderFill = HWPBorderFill().also { borderFillList.add(it) }

    fun addNewCharShape() : HWPCharShape = HWPCharShape().also { charShapeList.add(it) }

    fun addNewTabDef() : HWPTabDef = HWPTabDef().also { tabDefList.add(it) }

    fun addNewNumbering() : HWPNumbering = HWPNumbering().also { numberingList.add(it) }

    fun addNewBullet() : HWPBullet = HWPBullet().also { bulletList.add(it) }

    fun addNewParaShape() : HWPParaShape = HWPParaShape().also { paraShapeList.add(it) }

    fun addNewStyle() : HWPStyle = HWPStyle().also { styleList.add(it) }

    fun createDocData(rh: HWPRecordHeader) {
        docData = UnknownRecord(rh)
    }

    fun deleteDocData() {
        docData = null
    }

    fun createDistributeDocData(rh: HWPRecordHeader) {
        distributeDocData = UnknownRecord(rh)
    }

    fun deleteDistributeDocData() {
        distributeDocData = null
    }

    fun createCompatibleDocument() {
        compatibleDocument = HWPCompatibleDocument()
    }

    fun deleteCompatibleDocument() {
        compatibleDocument = null
    }

    fun createLayoutCompatibility() {
        layoutCompatibility = HWPLayoutCompatibility()
    }

    fun deleteLayoutCompatibility() {
        layoutCompatibility = null
    }

    fun createTrackChange(rh: HWPRecordHeader) {
        trackChange = UnknownRecord(rh)
    }

    fun deleteTrackChange() {
        trackChange = null
    }

    fun addNewMemoShape(rh: HWPRecordHeader) : UnknownRecord = UnknownRecord(rh).also { memoShapeList.add(it) }

    fun createForbiddenChar(rh: HWPRecordHeader) {
        forbiddenChar = UnknownRecord(rh)
    }

    fun deleteForbiddenChar() {
        forbiddenChar = null
    }

    fun addNewTrackChange2(rh: HWPRecordHeader) : UnknownRecord = UnknownRecord(rh).also { trackChange2List.add(it) }

    fun addNewTrackChangeAuthor2(rh: HWPRecordHeader) : UnknownRecord = UnknownRecord(rh).also { trackChangeAuthorList.add(it) }
}