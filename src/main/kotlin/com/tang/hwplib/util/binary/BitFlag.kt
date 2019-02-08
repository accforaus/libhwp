package com.tang.hwplib.util.binary

import kotlin.experimental.and
import kotlin.experimental.or
import kotlin.experimental.xor

/**
 * HWP 객체의 이진 연산을 수행하기 위한 Utility 함수
 *
 * @author accforaus
 */

/**
 * [mask] 에서 [position] 번째 비트가 1인지 여부 반환하는 함수
 *
 *  @param [mask] [Int]값을 가지는 데이터
 *  @param [position] 비트의 위치
 *  @return [mask] 에서 [position] 번째 비트가 1인지 여부 반환
 */
internal fun get(mask: Int, position: Int) : Boolean {
    val mask2: Int = 1.shl(position)
    return mask.and(mask2) == mask2
}

/**
 * [mask] 에서 [position] 번째 비트가 1인지 여부 반환하는 함수
 *
 *  @param [mask] [Short]값을 가지는 데이터
 *  @param [position] 비트의 위치
 *  @return [mask] 에서 [position] 번째 비트가 1인지 여부 반환
 */
internal fun get(mask: Short, position: Int) : Boolean {
    val mask2: Short = 1.shl(position).toShort()
    return mask.and(mask2) == mask2
}

/**
 * [mask] 에서 [position] 번째 비트가 1인지 여부 반환하는 함수
 *
 *  @param [mask] [Long]값을 가지는 데이터
 *  @param [position] 비트의 위치
 *  @return [mask] 에서 [position] 번째 비트가 1인지 여부 반환
 */
internal fun get(mask: Long, position: Int) : Boolean {
    val mask2: Long = 1.shl(position).toLong()
    return mask.and(mask2) == mask2
}


/**
 * [mask]의 [position]번째 비트를 [flag]값이 true 일 때 1, false 일 때 0으로 설정하는 함수
 *
 * @param [mask] [Long]값을 가지는 데이터
 * @param [position] 비트의 위치
 * @param [flag] [Boolean]값
 * @return [flag]값에 따른 [position]위치의 [mask] 설정 값
 */
internal fun set(mask: Long, position: Int, flag: Boolean) : Long {
    var tempMask: Long = mask
    if (flag)
        tempMask = tempMask.or(0x1.shl(position).toLong())
    else
        if (tempMask.and(0x1.shl(position).toLong()).toInt() != 0)
            tempMask = tempMask.xor(0x1.shl(position).toLong())
    return tempMask
}

/**
 * [mask]의 [position]번째 비트를 [flag]값이 true 일 때 1, false 일 때 0으로 설정하는 함수
 *
 * @param [mask] [Int]값을 가지는 데이터
 * @param [position] 비트의 위치
 * @param [flag] [Boolean]값
 * @return [flag]값에 따른 [position]위치의 [mask] 설정 값
 */
internal fun set(mask: Int, position: Int, flag: Boolean) : Int {
    var tempMask: Int = mask
    if (flag)
        tempMask = tempMask.or(0x1.shl(position))
    else
        if (tempMask.and(0x1.shl(position)) != 0)
            tempMask = tempMask.xor(0x1.shl(position))
    return tempMask
}

/**
 * [mask]의 [position]번째 비트를 [flag]값이 true 일 때 1, false 일 때 0으로 설정하는 함수
 *
 * @param [mask] [Short]값을 가지는 데이터
 * @param [position] 비트의 위치
 * @param [flag] [Boolean]값
 * @return [flag]값에 따른 [position]위치의 [mask] 설정 값
 */
internal fun set(mask: Short, position: Int, flag: Boolean) : Short {
    var tempMask: Short = mask
    if (flag)
        tempMask = (tempMask.or(0x1.shl(position).toShort()))
    else
        if (tempMask.and(0x1.shl(position).toShort()).toInt() != 0)
            tempMask = tempMask.xor(0x1.shl(position).toShort())
    return tempMask
}

/**
 * [mask]값에서 [start]부터 [end]까지의 비트 값을 반환하는 함수
 *
 * @param [mask] [Long]값을 가지는 데이터
 * @param [start] 비트의 시작 위치
 * @param [end] 비트의 끝 위치
 * @return [mask]값에서 [start]부터 [end]까지의 비트 값을 반환값
 */
internal fun get(mask: Long, start: Int, end: Int) : Long {
    var ret: Long = mask.shr(start)

    var temp: Long = 0
    for (index in 0 until end-start) {
        temp = temp.shl(1)
        temp += 1
    }

    ret = ret.and(temp)
    return ret
}

/**
 * [mask]값에서 [start]부터 [end]까지의 비트 값을 반환하는 함수
 *
 * @param [mask] [Int]값을 가지는 데이터
 * @param [start] 비트의 시작 위치
 * @param [end] 비트의 끝 위치
 * @return [mask]값에서 [start]부터 [end]까지의 비트 값을 반환값
 */
internal fun get(mask: Int, start: Int, end: Int) : Int {
    var ret: Int = mask.shr(start)

    var temp: Int = 0
    for (index in 0 until end - start) {
        temp = temp.shl(1)
        temp += 1
    }

    ret = ret.and(temp)
    return ret
}

/**
 * [mask]값에서 [Short]부터 [end]까지의 비트 값을 반환하는 함수
 *
 * @param [mask] [Long]값을 가지는 데이터
 * @param [start] 비트의 시작 위치
 * @param [end] 비트의 끝 위치
 * @return [mask]값에서 [start]부터 [end]까지의 비트 값을 반환값
 */
internal fun get(mask: Short, start: Int, end: Int) : Short {
    var ret: Short = mask.toInt().shr(start).toShort()

    var temp: Short = 0
    for (index in 0 until end - start) {
        temp = temp.toInt().shl(1).toShort()
        temp = (temp.toInt() + 1).toShort()
    }

    ret = ret.and(temp)
    return ret
}

/**
 * [mask]값에서 [start]부터 [end]까지의 비트를 [value]로 설정하는 함수
 *
 * @param [mask] [Long] 값을 가지는 데이터
 * @param [start] 비트의 시작값
 * @param [end] 비트의 끝값
 * @param [value] 비트 위치에 설정할 값
 * @return [mask]값에서 [start]부터 [end]까지의 비트를 [value]로 설정된 [mask]데이터
 */
internal fun set(mask: Long, start: Int, end: Int, value: Int) : Long {
    var temp: Long = mask
    for (position in start..end) {
        val flag: Boolean = get(value, position - start)
        temp = set(temp, position, flag)
    }
    return temp
}

/**
 * [mask]값에서 [start]부터 [end]까지의 비트를 [value]로 설정하는 함수
 *
 * @param [mask] [Int] 값을 가지는 데이터
 * @param [start] 비트의 시작값
 * @param [end] 비트의 끝값
 * @param [value] 비트 위치에 설정할 값
 * @return [mask]값에서 [start]부터 [end]까지의 비트를 [value]로 설정된 [mask]데이터
 */
internal fun set(mask: Int, start: Int, end: Int, value: Int) : Int {
    var temp: Int = mask
    for (position in start..end) {
        val flag: Boolean = get(value, position - start)
        temp = set(temp, position, flag)
    }
    return temp
}

/**
 * [mask]값에서 [start]부터 [end]까지의 비트를 [value]로 설정하는 함수
 *
 * @param [mask] [Short] 값을 가지는 데이터
 * @param [start] 비트의 시작값
 * @param [end] 비트의 끝값
 * @param [value] 비트 위치에 설정할 값
 * @return [mask]값에서 [start]부터 [end]까지의 비트를 [value]로 설정된 [mask]데이터
 */
internal fun set(mask: Short, start: Int, end: Int, value: Int) : Short {
    var temp: Short = mask
    for (position in start..end) {
        val flag: Boolean = get(value, position - start)
        temp = set(temp, position, flag)
    }
    return temp
}

