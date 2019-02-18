package com.tang.hwplib.builder.etc

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.etc.UnknownRecord

class UnknownRecordBuilder : HWPBuilder<UnknownRecord> {
    private val unknownRecord : UnknownRecord = UnknownRecord.build()

    fun setHeader(headerBuilder: HWPRecordHeaderBuilder) : UnknownRecordBuilder = this.apply {
        unknownRecord.header = headerBuilder.build()
    }

    fun setBody(body: ByteArray) : UnknownRecordBuilder = this.apply {
        unknownRecord.body = body
    }

    override fun build(): UnknownRecord = unknownRecord
}