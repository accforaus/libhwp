package com.tang.hwplib.objects.bodytext.control.gso

import com.tang.hwplib.objects.bodytext.control.makeCtrlID

/**
 * 그리기 개체 [HWPGsoControl] 타입
 *
 * @author accforaus
 *
 * @property [id] [Long], 그리기 개체 ID
 */
enum class HWPGsoControlType(v: Long) {
    /**
     * 선
     */
    Line(makeCtrlID('$', 'l','i','n')),
    /**
     * 사각형
     */
    Rectangle(makeCtrlID('$', 'r', 'e', 'c')),
    /**
     * 타원
     */
    Ellipse(makeCtrlID('$', 'e', 'l', 'l')),
    /**
     * 호
     */
    Arc(makeCtrlID('$', 'a', 'r', 'c')),
    /**
     * 다각형
     */
    Polygon(makeCtrlID('$', 'p', 'o', 'l')),
    /**
     * 곡선
     */
    Curve(makeCtrlID('$', 'c', 'u', 'r')),
    /**
     * 그림
     */
    Picture(makeCtrlID('$', 'p', 'i', 'c')),
    /**
     * OLE
     */
    OLE(makeCtrlID('$', 'o', 'l', 'e')),
    /**
     * 묶음 개체
     */
    Container(makeCtrlID('$', 'c', 'o', 'n')),
    /**
     * 객체 연결선
     */
    ObjectLinkLine(makeCtrlID('$', 'c', 'o', 'l')),
    /**
     * 비디오
     */
    Video(makeCtrlID('$', 'v', 'i', 'd'));

    var id: Long = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [id] [Long], 파일에 저장되는 정수값
         * @return [HWPGsoControlType] enum 값
         */
        fun idOf(id: Long) : HWPGsoControlType {
            for (gct in values())
                if (gct.id == id)
                    return gct
            return Line
        }
    }
}