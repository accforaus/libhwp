package com.tang.hwplib.objects.bodytext.paragraph.text

import java.nio.charset.Charset
import kotlin.experimental.and

/**
 * HWP 제어 문자의 종류
 *
 * @author accforaus
 */
enum class HWPCharType {
    /**
     * 일반 문자
     */
    Normal,
    /**
     * 문자 컨트롤
     */
    ControlChar,
    /**
     * 인라인 컨트롤
     */
    ControlInline,
    /**
     * 확장 컨트롤
     */
    ControlExtend
}

/**
 * HWP 글자를 나타내는 객체
 *
 * @author accforaus
 *
 * @property [code] [Short], 문자 코드의 일부분을 특수 용도로 사용함을 알기 위한 데이터
 */
abstract class HWPChar {
    var code: Short = 0

    /**
     * 제어 문자의 종류를 반환하는 함수
     *
     * @return [HWPCharType] 제어 문자의 종류를 반환
     */
    abstract fun getType() : HWPCharType

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPChar] 복사된 객체 반환
     */
    abstract fun copy() : HWPChar

    companion object {
        /**
         * 제어 문자의 종류를 반환하는 함수
         *
         * @param [code] [Int], 제어 문자의 코드데이터
         * @return [HWPCharType] 제어 문자의 종류
         */
        fun type(code: Int) : HWPCharType = when(code) {
            in 1..3 , in 11..12, in 14..18, in 21..23 -> HWPCharType.ControlExtend
            in 4..9, in 19..20 -> HWPCharType.ControlInline
            0, 10, 13, in 24..31 -> HWPCharType.ControlChar
            else -> HWPCharType.Normal
        }
    }
}

/**
 * 문자 컨트롤을 나타내는 문자 객체
 * 하나의 문자로 취급되는 문자 컨트롤
 * 부가 정보 없이 문자 하나로 표현되는 제어 문자
 * 1 size
 *
 * @author accforaus
 */

class HWPCharControlChar : HWPChar() {
    override fun getType(): HWPCharType = HWPCharType.ControlChar

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCharControlChar] 복사된 객체 반환
     */
    override fun copy(): HWPCharControlChar = HWPCharControlChar().also { it.code = this.code }

    /**
     * 문자 코드를 설정하는 함수
     *
     * @param [ch] [String], 설정할 문자 코드가 들어있는 데이터
     */
    fun setCode(ch: String) {
        val b: ByteArray = ch.toByteArray(Charset.forName("UTF-16LE"))
        /**
         * 문자 코드를 확인하고 형 변환을 하는 함수
         *
         * @param [byteArray] [ByteArray], 변환될 byte 배열
         * @return [Short] 변환된 데이터
         */
        fun checkCode(byteArray: ByteArray): Short = when(b.size) {
            in 2..Int.MAX_VALUE -> ((byteArray[1].and(0xFF.toByte())).toInt().shl(8)).or(byteArray[0].and(0xFF.toByte()).toInt()).toShort()
            1 -> byteArray[0].and(0xFF.toByte()).toShort()
            else -> 0
        }
        code = checkCode(b)
    }
}

/**
 * 확장 컨트롤을 나타내는 문자 객체
 * 별도의 오브젝트가 데이터를 표현하는 확장 컨트롤
 * 제어 문자는 포인터를 가지고 있고, 포인터가 가르키는 곳에 실제 오브젝트가 존재하는 제어 문자
 * 8 size
 */
class HWPCharControlExtend: HWPChar() {
    var addition: ByteArray = ByteArray(12)

    override fun getType(): HWPCharType = HWPCharType.ControlExtend

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCharControlExtend] 복사된 객체 반환
     */
    override fun copy(): HWPCharControlExtend = HWPCharControlExtend().also {
        it.code = this.code
        it.addition = this.addition.copyOf()
    }

    /**
     * InstanceID 오브젝트의 포인터를 반환하는 함수
     *
     * @return [String] [addition]을 이용하여 오브젝트 포인터를 반환
     */
    fun getInstanceId() : String = ByteArray(addition.size).run {
        var bufferIndex: Int = 0
        var insert: Boolean = false
        for (index in addition.size - 1 downTo 0) {
            if (addition[index].toInt() != 0)
                insert = true
            if (insert) {
                this[bufferIndex++] = addition[index]
            }
        }
        return String(this, 0, bufferIndex)
    }
}

/**
 * 인라인 컨트롤을 나타내는 문자 객체
 * 별도의 오브젝트 포인터를 가르키지 않는 단순한 인라인 컨트롤
 * 부가정보가 12 byte(6 WCHAR) 이내에서 표현될 수 있는 제어 문자
 * 부가 정보를 다 넣지 못한 경우 확장 컨트롤로 대체
 * 8 size
 *
 * @author accforaus
 *
 * @property [addition] [ByteArray], 부가 정보를 나타내는 12 byte 배열
 */
class HWPCharControlInline: HWPChar() {
    var addition: ByteArray = ByteArray(12)
    override fun getType(): HWPCharType = HWPCharType.ControlInline

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCharControlInline] 복사된 객체 반환
     */
    override fun copy(): HWPCharControlInline = HWPCharControlInline().also {
        it.code = this.code
        it.addition = this.addition.copyOf()
    }
}

/**
 * 일반 문자를 나타내는 문자 객체
 *
 * @author accforaus
 */
class HWPCharNormal: HWPChar() {
    override fun getType(): HWPCharType = HWPCharType.Normal

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCharNormal] 복사된 객체 반환
     */
    override fun copy(): HWPCharNormal = HWPCharNormal().also { it.code = this.code }
    /**
     * 문자 코드 [code]를 문자열로 변환하는 함수
     *
     * @return [String] [code]를 문자열로 변환후 반환
     */
    fun getCh() : String {
        /**
         * [Short]를 [String]으로 변환하는 함수
         *
         * @param [code] [Short], 변환될 데이터
         * @return [String] 변환된 [code]데이터
         */
        fun shortToString(code: Short) : String {
            val ch: ByteArray = ByteArray(2)
            ch[0] = code.and(0xff).toByte()
            ch[1] = (code.toInt().shr(8)).and(0xff).toByte()
            return String(ch, 0, 2, Charset.forName("UTF-16LE"))
        }
        return shortToString(code)
    }
}