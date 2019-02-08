package com.tang.hwplib.objects.etc

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 색상을 나타내는 객체
 * COLORREF - unsigned 4 bytes
 * 
 * @author accforaus
 * 
 * @property [value] [Byte], 색상값을 가진 데이터
 */
class Color4Byte {
    var value: Long = 0

    /**
     * R(red) 값을 반환하는 함수
     * bit 0-7
     *
     * @return [Short] R(red) 값을 반환
     */
    fun getR() : Short = get(value, 0, 7).toShort()

    /**
     * R(red) 값을 설정하는 함수
     * bit 0-7
     *
     * @param [r] [Short], R(red)값을 가진 데이터
     */
    fun setR(r: Short) {
        value = set(value, 0, 7, r.toInt())
    }

    /**
     * R(red) 값을 반환하는 함수
     * bit 8-15
     *
     * @return [Short] R(red) 값을 반환
     */
    fun getG() : Short = get(value, 8, 15).toShort()

    /**
     * G(green) 값을 설정하는 함수
     * bit 8-15
     *
     * @param [g] [Short], G(green)값을 가진 데이터
     */
    fun setG(g: Short) {
        value = set(value, 8, 15, g.toInt())
    }

    /**
     * B(Blue) 값을 반환하는 함수
     * bit 16-23
     *
     * @return [Short] B(Blue) 값을 반환
     */
    fun getB() : Short = get(value, 16, 23).toShort()

    /**
     * B(Blue) 값을 설정하는 함수
     * bit 16-23
     *
     * @param [b] [Short], B(Blue)값을 가진 데이터
     */
    fun setB(b: Short) {
        value = set(value, 16, 23, b.toInt())
    }

    /**
     * A(alpha) 값을 반환하는 함수
     * bit 21-34
     *
     * @return [Short] A(alpha) 값을 반환
     */
    fun getA() : Short = get(value, 24, 31).toShort()

    /**
     * A(alpha) 값을 설정하는 함수
     * bit 24-31
     *
     * @param [a] [Short], A(alpha)값을 가진 데이터
     */
    fun setA(a: Short) {
        value = set(value, 24, 31, a.toInt())
    }
}