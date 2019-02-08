package com.tang.hwplib.util.string

/**
 * HEX 문자열
 *
 * @author accforaus
 */
private val hexArray: CharArray = "0123456789ABCDEF".toCharArray()

/**
 * 문자열(UTF16LE)의 크기를 반환하는 함수
 *
 * @author accforaus
 *
 * @param [str] [String], 크기가 반환될 문자열
 * @return [Int] 문자열 크기 반환
 */
internal fun getUTF16LEStringSize(str: String?) : Int = if (str == null) 2 else 2 + str.length * 2

/**
 * byte 배열을 HEX 문자열로 변환하고 반환하는 함수
 *
 * @author accforaus
 *
 * @param [bytes] [ByteArray], 변환될 byte 배열
 * @return [String] 변환된 HEX 문자열 반환
 */
internal fun bytesToHex(bytes: ByteArray) : String = ByteArray(bytes.size * 2).let {
    for (index in 0..bytes.size) {
        val v : Int = bytes[index].toInt().and(0xFF)
        it[index * 2] = hexArray[v.ushr(4)].toByte()
        it[index * 2 + 1] = hexArray[v.and(0x0F)].toByte()
    }
    return String(it)
}