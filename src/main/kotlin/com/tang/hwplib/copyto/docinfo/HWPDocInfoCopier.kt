package com.tang.hwplib.copyto.docinfo

import com.tang.hwplib.copyto.bindata.proceedEmbeddedBinData
import com.tang.hwplib.copyto.docinfo.borderfill.HWPFillInfoCopier
import com.tang.hwplib.objects.HWPDocument
import com.tang.hwplib.objects.docinfo.HWPBinData
import com.tang.hwplib.objects.docinfo.HWPBorderFill
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import kotlin.reflect.KClass

enum class IDMappingTypes {
    BINDATA,
    HANGUL_FACENAME,
    ENGLISH_FACENAME,
    HANJA_FACENAME,
    JAPANESE_FACENAME,
    ETC_FACENAME,
    SYMBOL_FACENAME,
    USER_FACENAME,
    BORDERFILL,
    CHARSHAPE,
    TABDEF,
    NUMBERING,
    BULLET,
    PARASHAPE,
    STYLE,
}

class HWPDocInfoCopier(private val targetDocument: HWPDocument, private val originalDocument: HWPDocument) {
    val originalDocInfo: HWPDocInfo = originalDocument.docInfo
    val targetDocInfo: HWPDocInfo = targetDocument.docInfo
    val borderFillCopier: HWPBorderFillCopyier = HWPBorderFillCopyier(this)
    val fillInfoCopier: HWPFillInfoCopier = HWPFillInfoCopier(this)
    val faceNameCopier: HWPFaceNameCopier = HWPFaceNameCopier(this)
    val charShapeCopier: HWPCharShapeCopier = HWPCharShapeCopier(this)
    val paragraphHeaderInfoCopier: HWPParagraphHeadInfoCopier = HWPParagraphHeadInfoCopier(this)
    val numberingCopier: HWPNumberingCopier = HWPNumberingCopier(this)
    val bulletCopier: HWPBulletCopier = HWPBulletCopier(this)
    val tabDefCopier: HWPTabDefCopier = HWPTabDefCopier(this)
    val paraShapeCopier: HWPParaShapeCopier = HWPParaShapeCopier(this)
    val styleCopier: HWPStyleCopier = HWPStyleCopier(this)

    fun proceedBinData(originalIndex: Int) : Int {
        val original: HWPBinData = originalDocInfo.binDataList[originalIndex - 1]
        val target: HWPBinData = original.copy().apply {
            binDataID = proceedEmbeddedBinData(binDataID, targetData = targetDocument.binData, sourceData = originalDocument.binData)
        }
        targetDocInfo.binDataList.add(target)
        updateIDMappings(IDMappingTypes.BINDATA)
        return targetDocInfo.binDataList.size
    }

    fun updateIDMappings(type: IDMappingTypes) {
        when (type) {
            IDMappingTypes.BINDATA -> {
                targetDocInfo.idMappings.binDataCount = targetDocInfo.idMappings.binDataCount + 1
            }
            IDMappingTypes.BULLET -> {
                targetDocInfo.idMappings.bulletCount = targetDocInfo.idMappings.bulletCount + 1
            }
            IDMappingTypes.TABDEF -> {
                targetDocInfo.idMappings.tabDefCount = targetDocInfo.idMappings.tabDefCount + 1
            }
            IDMappingTypes.CHARSHAPE -> {
                targetDocInfo.idMappings.charShapeCount = targetDocInfo.idMappings.charShapeCount + 1
            }
            IDMappingTypes.NUMBERING -> {
                targetDocInfo.idMappings.numberingCount = targetDocInfo.idMappings.numberingCount + 1
            }
            IDMappingTypes.PARASHAPE -> {
                targetDocInfo.idMappings.paraShapeCount = targetDocInfo.idMappings.paraShapeCount + 1
            }
            IDMappingTypes.BORDERFILL -> {
                targetDocInfo.idMappings.borderFillCount = targetDocInfo.idMappings.borderFillCount + 1
            }
            IDMappingTypes.ETC_FACENAME -> {
                targetDocInfo.idMappings.etcFaceNameCount = targetDocInfo.idMappings.etcFaceNameCount + 1
            }
            IDMappingTypes.SYMBOL_FACENAME -> {
                targetDocInfo.idMappings.symbolFaceNameCount = targetDocInfo.idMappings.symbolFaceNameCount + 1
            }
            IDMappingTypes.USER_FACENAME -> {
                targetDocInfo.idMappings.userFaceNameCount = targetDocInfo.idMappings.userFaceNameCount + 1
            }
            IDMappingTypes.HANJA_FACENAME -> {
                targetDocInfo.idMappings.hanjaFaceNameCount = targetDocInfo.idMappings.hanjaFaceNameCount + 1
            }
            IDMappingTypes.HANGUL_FACENAME -> {
                targetDocInfo.idMappings.hangulFaceNameCount = targetDocInfo.idMappings.hangulFaceNameCount + 1
            }
            IDMappingTypes.ENGLISH_FACENAME -> {
                targetDocInfo.idMappings.englishFaceNameCount = targetDocInfo.idMappings.englishFaceNameCount + 1
            }
            IDMappingTypes.JAPANESE_FACENAME -> {
                targetDocInfo.idMappings.japaneseFaceNameCount = targetDocInfo.idMappings.japaneseFaceNameCount + 1
            }
            IDMappingTypes.STYLE -> {
                targetDocInfo.idMappings.styleCount = targetDocInfo.idMappings.styleCount + 1
            }
        }
    }
}