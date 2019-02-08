package com.tang.hwplib.objects

/**
 * 레코드 헤더
 * 논리적으로 연관된 데이터들을 헤더 정보와 함께 저장하는 방식
 * 레코드 구조를 가지는 스트림은 연속된 여러 개의 레코드로 구성되어 있다.
 * 헤코드 헤더에는 데이터 확장에 대비한 정보를 가지고 있다.
 *
 * 32bits의 크기를 가지고 있다.
 *
 * @author accforaus
 *
 * @property [tagId] [Short], 10bits의 크기를 가지며 레코드가 나타내는 데이터의 종류를 나타내는 태그. 0x000 - 0x3FF까지의 범위를 가진다.
 * @property [level] [Short], 10bits의 크기를 가지며 논리적으로 연관된 연속된 레코드를 표현한다.
 * @property [size] [Long], 12bits의 크기를 가지며 데이터 영역의 길이를 바이트 단위로 나타낸다.
 */

class HWPRecordHeader {
    var tagId: Short = 0
    var level: Short = 0
    var size: Long = 0

    /**
     * 새로운 레코드 헤더 객체를 생성하고 값을 복사하는 객체
     *
     * @return [HWPRecordHeader] 새로 생성된 레코드 헤더 객체
     */
    fun copy() : HWPRecordHeader = HWPRecordHeader().also {
        it.size = this.size
        it.level = this.level
        it.tagId = this.tagId
    }
}