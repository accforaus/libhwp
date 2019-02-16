package com.tang.hwplib.builder.interfaces

interface HWPBuilder<T> {
    fun build() : T
}

interface HWPControlBuilder<T> : HWPBuilder<T>