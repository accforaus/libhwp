package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.HWPLayoutCompatibility

class HWPLayoutCompatibilityBuilder : HWPBuilder<HWPLayoutCompatibility> {
    private val layout : HWPLayoutCompatibility = HWPLayoutCompatibility.build()

    fun setLetterLevelFormat(letterLevelFormat: Long) : HWPLayoutCompatibilityBuilder = this.apply {
        layout.letterLevelFormat = letterLevelFormat
    }

    fun setParagraphLevelFormat(paragraphLevelFormat: Long) : HWPLayoutCompatibilityBuilder = this.apply {
        layout.paragraphLevelFormat = paragraphLevelFormat
    }

    fun setSectionLevelFormat(sectionLevelFormat: Long) : HWPLayoutCompatibilityBuilder = this.apply {
        layout.sectionLevelFormat = sectionLevelFormat
    }

    fun setObjectLevelFormat(objectLevelFormat: Long) : HWPLayoutCompatibilityBuilder = this.apply {
        layout.objectLevelFormat = objectLevelFormat
    }

    fun setFieldLevelFormat(fieldLevelFormat: Long) : HWPLayoutCompatibilityBuilder = this.apply {
        layout.fieldLevelFormat = fieldLevelFormat
    }

    override fun build(): HWPLayoutCompatibility = layout
}