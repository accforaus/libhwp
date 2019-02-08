package com.tang.hwplib.objects.etc

import com.tang.hwplib.objects.HWPRecordHeader

/**
 *  알려지지 않은 레코드를 나타내는 객체
 *
 *  @author accforaus
 *
 *  @constructor [headerHWP]를 헤더로 설정한다
 *
 *  @property [headerHWP] [HWPRecordHeader], 레코드 헤더
 *  @property [body] [ByteArray], 데이터
 */
class UnknownRecord constructor() {
    var header: HWPRecordHeader? = null
    var body: ByteArray? = null

    constructor(header: HWPRecordHeader) : this() {
        this.header = header.copy()
    }
}