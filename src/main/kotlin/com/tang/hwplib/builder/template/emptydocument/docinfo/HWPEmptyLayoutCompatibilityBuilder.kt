package com.tang.hwplib.builder.template.emptydocument.docinfo

import com.tang.hwplib.builder.docinfo.HWPLayoutCompatibilityBuilder
import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPLayoutCompatibility

class HWPEmptyLayoutCompatibilityBuilder : HWPBuilder<HWPLayoutCompatibility> {
    private fun buildLayout() : HWPLayoutCompatibilityBuilder = HWPLayoutCompatibilityBuilder()

    override fun build(): HWPLayoutCompatibility = buildLayout().build()
}