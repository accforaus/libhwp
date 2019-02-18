package com.tang.hwplib.builder.template.emptydocument.docinfo

import com.tang.hwplib.builder.etc.HWPRecordHeaderBuilder
import com.tang.hwplib.builder.etc.UnknownRecordBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.etc.UnknownRecord

class HWPEmptyForbiddenCharBuilder : HWPBuilder<UnknownRecord> {
    private fun buildForbiddenChar() : UnknownRecordBuilder = UnknownRecordBuilder()
            .setHeader(HWPRecordHeaderBuilder()
                    .setTagID(94)
                    .setLevel(1)
                    .setSize(16))
            .setBody(ByteArray(16, {0.toByte()}))

    override fun build(): UnknownRecord = buildForbiddenChar().build()
}