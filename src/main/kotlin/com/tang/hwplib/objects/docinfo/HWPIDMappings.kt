package com.tang.hwplib.objects.docinfo
import com.tang.hwplib.objects.etc.ID_MAPPINGS
/**
 * 아이디 매핑 개수 (72 bytes)
 * doc versiond에 따라 가변적
 * Tag ID: HWPTAG_ID_MAPPINGS [ID_MAPPINGS]
 *
 * @author accforaus
 *
 * @property [binDataCount] [Int], 바이너리 데이터(INT32) (signed 4 bytes)
 * @property [hangulFaceNameCount] [Int], 한글 글꼴(INT32) (signed 4 bytes)
 * @property [englishFaceNameCount] [Int], 영어 글끌(INT32) (signed 4 bytes)
 * @property [hanjaFaceNameCount] [Int], 한자 글꼴(INT32) (signed 4 bytes)
 * @property [japaneseFaceNameCount] [Int], 일어 글꼴(INT32) (signed 4 bytes)
 * @property [etcFaceNameCount] [Int], 기타 글꼴(INT32) (signed 4 bytes)
 * @property [symbolFaceNameCount] [Int], 기호 글꼴(INT32) (signed 4 bytes)
 * @property [userFaceNameCount] [Int], 사용자 글꼴(INT32) (signed 4 bytes)
 * @property [borderFillCount] [Int], 테두리/배경(INT32) (signed 4 bytes)
 * @property [charShapeCount] [Int], 글자 모양(INT32) (signed 4 bytes)
 * @property [tabDefCount] [Int], 탭 정의(INT32) (signed 4 bytes)
 * @property [numberingCount] [Int], 문단 번호(INT32) (signed 4 bytes)
 * @property [bulletCount] [Int], 글머리표(INT32) (signed 4 bytes)
 * @property [paraShapeCount] [Int], 문단 모양(INT32) (signed 4 bytes)
 * @property [styleCount] [Int], 스타일(INT32) (signed 4 bytes)
 * @property [memoShapeCount] [Int], 메모 모양(*>=5.0.2.1*)(INT32) (signed 4 bytes)
 * @property [trackChangeCount] [Int], 변경 추적(*>=5.0.3.2*)(INT32) (signed 4 bytes)
 * @property [trackChangeAuthorCount] [Int], 변경 추적 사용자(*>=5.0.3.2*)(INT32) (signed 4 bytes)
 */
class HWPIDMappings {
    var binDataCount: Int = 0
    var hangulFaceNameCount: Int = 0
    var englishFaceNameCount: Int = 0
    var hanjaFaceNameCount: Int = 0
    var japaneseFaceNameCount: Int = 0
    var etcFaceNameCount: Int = 0
    var symbolFaceNameCount: Int = 0
    var userFaceNameCount: Int = 0
    var borderFillCount: Int = 0
    var charShapeCount: Int = 0
    var tabDefCount: Int = 0
    var numberingCount: Int = 0
    var bulletCount: Int = 0
    var paraShapeCount: Int = 0
    var styleCount: Int = 0
    var memoShapeCount: Int = 0
    var trackChangeCount: Int = 0
    var trackChangeAuthorCount: Int = 0
}