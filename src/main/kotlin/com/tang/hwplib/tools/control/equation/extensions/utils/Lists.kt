package com.tang.hwplib.tools.control.equation.extensions.utils

/**
 * replace element in mutable list
 * @receiver MutableList<T>
 * @param element T
 * @param other T
 */
fun <T> MutableList<T>.replace(element: T, other: T) {
    val index = this.indexOf(element)
    if (index != -1) {
        this.remove(element)
        this.add(index, other)
    }
}

/**
 * push function for Stack like
 * @receiver MutableList<T>
 * @param element T
 */
fun <T> MutableList<T>.push(element: T) {
    this.add(element)
}

/**
 * pop function for Stack like
 * @receiver MutableList<T>
 * @return T
 */
fun <T> MutableList<T>.pop() : T {
    val element = this[lastIndex]
    this.removeAt(lastIndex)
    return element
}