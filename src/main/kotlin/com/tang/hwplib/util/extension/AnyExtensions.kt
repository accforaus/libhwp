package com.tang.hwplib.util.extension

fun <T> Any.nullEquals(target: T, source: T) : Boolean {
    if (target != null) {
        if (source == null) return false
        if (target != source) return false
    } else {
        if (source != null) return false
    }
    return true
}

infix fun <T> List<T>.contentEquals(source: List<T>) : Boolean {
    if (this.size != source.size) return false

    for (i in 0 until this.size) {
        if (this[i] != source[i]) return false
    }
    return true
}