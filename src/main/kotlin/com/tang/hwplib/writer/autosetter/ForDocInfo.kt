package com.tang.hwplib.writer.autosetter

import com.tang.hwplib.objects.bodytext.HWPBodyText
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.docinfo.HWPIDMappings

/**
 * 문서 정보 [HWPDocInfo]의 아이디 매핑 헤더 [HWPIDMappings]의 자동 설정을 수행하는 함수
 *
 * @author accforaus
 *
 * @param [di] [HWPDocInfo], 문서 정보 객체
 * @param [bt] [HWPBodyText], 본문 객체
 */
fun autoSetDocInfo(di: HWPDocInfo, bt: HWPBodyText) {
    di.let {
        it.documentProperties.sectionCount = bt.sectionList.size
        di.idMappings.run {
            binDataCount = it.binDataList.size
            hangulFaceNameCount = it.hangulFaceNameList.size
            englishFaceNameCount = it.englishFaceNameList.size
            hanjaFaceNameCount = it.hanjaFaceNameList.size
            japaneseFaceNameCount = it.japaneseFaceNameList.size
            etcFaceNameCount = it.etcFaceNameList.size
            symbolFaceNameCount = it.symbolFaceNameList.size
            userFaceNameCount = it.userFaceNameList.size
            borderFillCount = it.borderFillList.size
            charShapeCount = it.charShapeList.size
            tabDefCount = it.tabDefList.size
            numberingCount = it.numberingList.size
            bulletCount = it.bulletList.size
            paraShapeCount = it.paraShapeList.size
            styleCount = it.styleList.size
            memoShapeCount = it.memoShapeList.size
            trackChangeCount = it.trackChange2List.size
            trackChangeAuthorCount = it.trackChangeAuthorList.size
        }
    }
}