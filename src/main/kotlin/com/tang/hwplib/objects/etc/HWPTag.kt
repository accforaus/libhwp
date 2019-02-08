package com.tang.hwplib.objects.etc

/**
 * 문서 정보 ('Doc Info')에서 사용되는 데이터 레코드
 *
 * @author accforaus
 */

const val BEGIN: Short = 0x10
/**
 * 문서 속성
 */
const val DOCUMENT_PROPERTIES: Short = BEGIN
/**
 * 아이디 매핑 헤더
 */
const val ID_MAPPINGS: Short = (BEGIN.toInt() + 1).toShort()
/**
 * HWPBinData
 */
const val BIN_DATA: Short = (BEGIN.toInt() + 2).toShort()
/**
 * Typeface Name
 */
const val FACE_NAME: Short = (BEGIN.toInt() + 3).toShort()
/**
 * 테두리/배경
 */
const val BORDER_FILL: Short = (BEGIN.toInt() + 4).toShort()
/**
 * 글자 모양
 */
const val CHAR_SHAPE: Short = (BEGIN.toInt() + 5).toShort()
/**
 * 탭 정의
 */
const val TAB_DEF: Short = (BEGIN.toInt() + 6).toShort()
/**
 * 번호 정의
 */
const val NUMBERING: Short = (BEGIN.toInt() + 7).toShort()
/**
 * 불릿 정의
 */
const val BULLET: Short = (BEGIN.toInt() + 8).toShort()
/**
 * 문단 모양
 */
const val PARA_SHAPE: Short = (BEGIN.toInt() + 9).toShort()
/**
 * 스타일
 */
const val STYLE: Short = (BEGIN.toInt() + 10).toShort()
/**
 * 문서의 임의의 데이터
 */
const val DOC_DATA: Short = (BEGIN.toInt() + 11).toShort()
/**
 * 배포용 문서 데이터
 */
const val DISTRIBUTE_DOC_DATA: Short = (BEGIN.toInt() + 12).toShort()
/**
 * 호환 문서
 */
const val COMPATIBLE_DOCUMENT: Short = (BEGIN.toInt() + 14).toShort()
/**
 * 레이아웃 호환성
 */
const val LAYOUT_COMPATIBILITY: Short = (BEGIN.toInt() + 15).toShort()
/**
 * 변경 추적 정보
 */
const val TRACKCHANGE: Short = (BEGIN.toInt() + 16).toShort()
/**
 * 메모 모양
 */
const val MEMO_SHAPE: Short = (BEGIN.toInt() + 76).toShort()
/**
 * 금칙처리 문자
 */
const val FORBIDDEN_CHAR: Short = (BEGIN.toInt() + 78).toShort()
/**
 * 변경 추적 내용 및 모양
 */
const val TRACK_CHANGE: Short = (BEGIN.toInt() + 80).toShort()
/**
 * 변경 추적 작성자
 */
const val TRACK_CHANGE_AUTHOR: Short = (BEGIN.toInt() + 81).toShort()


/**
 *  본문에서 사용되는 데이터 레코드
 */

/**
 * 문단 헤더
 */
const val PARA_HEADER: Short = (BEGIN.toInt() + 50).toShort()
/**
 * 문단의 텍스트
 */
const val PARA_TEXT: Short = (BEGIN.toInt() + 51).toShort()
/**
 * 문단의 글자 모양
 */
const val PARA_CHAR_SHAPE: Short = (BEGIN.toInt() + 52).toShort()
/**
 * 문단의 레이아웃
 */
const val PARA_LINE_SEG: Short = (BEGIN.toInt() + 53).toShort()
/**
 * 문단의 영역 태그
 */
const val PARA_RANGE_TAG: Short = (BEGIN.toInt() + 54).toShort()
/**
 * 컨트롤 헤더
 */
const val CTRL_HEADER: Short = (BEGIN.toInt() + 55).toShort()
/**
 * 문단 리스트 헤더
 */
const val LIST_HEADER: Short = (BEGIN.toInt() + 56).toShort()
/**
 * 용지 설정
 */
const val PAGE_DEF: Short = (BEGIN.toInt() + 57).toShort()
/**
 * 각주/미주 모양
 */
const val FOOTNOTE_SHAPE: Short = (BEGIN.toInt() + 58).toShort()
/**
 * 쪽 테두리/배경
 */
const val PAGE_BORDER_FILL: Short = (BEGIN.toInt() + 59).toShort()
/**
 * 개체
 */
const val SHAPE_COMPONENT: Short = (BEGIN.toInt() + 60).toShort()
/**
 * 표 개체
 */
const val TABLE: Short = (BEGIN.toInt() + 61).toShort()
/**
 * 직선 개체
 */
const val SHAPE_COMPONENT_LINE: Short = (BEGIN.toInt() + 62).toShort()
/**
 * 사각형 개체
 */
const val SHAPE_COMPONENT_RECTANGLE: Short = (BEGIN.toInt() + 63).toShort()
/**
 * 타원 개체
 */
const val SHAPE_COMPONENT_ELLIPSE: Short = (BEGIN.toInt() + 64).toShort()
/**
 * 호 개체
 */
const val SHAPE_COMPONENT_ARC: Short = (BEGIN.toInt() + 65).toShort()
/**
 * 다각형 개체
 */
const val SHAPE_COMPONENT_POLYGON: Short = (BEGIN.toInt() + 66).toShort()
/**
 * 곡선 개체
 */
const val SHAPE_COMPONENT_CURVE: Short = (BEGIN.toInt() + 67).toShort()
/**
 * OLE 개체
 */
const val SHAPE_COMPONENT_OLE: Short = (BEGIN.toInt() + 68).toShort()
/**
 * 그림 개체
 */
const val SHAPE_COMPONENT_PICTURE: Short = (BEGIN.toInt() + 69).toShort()
/**
 * 컨테이너 개체
 */
const val SHAPE_COMPONENT_CONTAINER: Short = (BEGIN.toInt() + 70).toShort()
/**
 * 컨트롤 임의의 데이터
 */
const val CTRL_DATA: Short = (BEGIN.toInt() + 71).toShort()
/**
 * 수식 개체
 */
const val EQEDIT: Short = (BEGIN.toInt() + 72).toShort()
/**
 * 글맵시
 */
const val SHAPE_COMPONENT_TEXTART: Short = (BEGIN.toInt() + 74).toShort()
/**
 * 양식 개체
 */
const val FORM_OBJECT: Short = (BEGIN.toInt() + 75).toShort()
/**
 * 메모 리스트 헤도
 */
const val MEMO_LIST: Short = (BEGIN.toInt() + 77).toShort()
/**
 * 차트 데이터
 */
const val CHART_DATA: Short = (BEGIN.toInt() + 79).toShort()
/**
 * 비디오 데이터
 */
const val VIDEO_DATA: Short = (BEGIN.toInt() + 82).toShort()
/**
 * Unknown
 */
const val SHAPE_COMPONENT_UNKNOWN: Short = (BEGIN.toInt() + 99).toShort()