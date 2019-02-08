package com.tang.hwplib.writer.util

import com.tang.hwplib.objects.fileheader.HWPFileVersion
import com.tang.hwplib.util.binary.set
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.Charset
import java.util.zip.Deflater

/**
 * Microsoft compound File Stream [MS-CFB]에 데이터를 저장하기 위한 스트림 객체
 * @see <link href='https://msdn.microsoft.com/ko-kr/library/dd942138.aspx'>
 *
 * @author accforaus
 *
 * @constructor 이름과 압축여부, 한글 파일 버전을 저장한다.
 *
 * @property [name] [String], 스트림(파일) 이름
 * @property [compress] [Boolean], 압축 여부
 * @property [version] [HWPFileVersion], 한글 파일 버전
 * @property [os] [ByteArrayOutputStream], 임시로 저장될 ByteArrayOutputStream
 * @property [currentRecordLevel], 현재 레코드 레벨
 */
internal data class StreamWriter(var name: String, var compress: Boolean, var version: HWPFileVersion) {
    var os: ByteArrayOutputStream = ByteArrayOutputStream()
    var currentRecordLevel: Int = 0

    /**
     * 스트림을 닫는 함수
     */
    fun close() {
        os.close()
    }

    /**
     * 스트림을 반환하는 함수
     *
     * @return [InputStream] 생성된 스트림 반환
     */
    fun getDataStream() : InputStream = Unit.run {
        val bytes = when (compress) {
            true -> compressBytes()
            else -> os.toByteArray()
        }
        return ByteArrayInputStream(bytes)
    }

    /**
     * byte 배열을 스트림에 저장하는 함수
     *
     * @param [value] [ByteArray], 저장될 byte 배열
     */
    fun writeBytes(value: ByteArray) { os.write(value) }

    /**
     * byte 배열을 지정된 수 만큼 스트림에 저장하는 함수
     *
     * @param [value] [ByteArray], 저장될 byte 배열
     * @param [count] [Int], 저장할 개수
    */
    fun writeBytes(value: ByteArray, count: Int) {
        if (value.size == count) os.write(value)
        else if (value.size < count) writeZero(count - value.size)
        else if (value.size > count) {
            for (index in 0 until count) {
                os.write(value[index].toInt())
            }
        }
    }

    /**
     * signed 1 byte를 저장하는 함수
     *
     * @param [value] [Byte], 저장될 signed 1 byte
     */
    private fun writeSInt1(value: Byte) {
        val buffer: ByteArray = ByteArray(1)
        buffer[0] = value
        writeBytes(buffer)
    }

    /**
     * signed 2 bytes를 스트림에 저장하는 함수
     *
     * @param [value] [Short], 저장될 signed 2 bytes
     */
    private fun writeSInt2(value: Short) {
        val buffer: ByteArray = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(value).array()
        writeBytes(buffer)
    }

    /**
     * signed 4 bytes를 스트림에 저장하는 함수
     *
     * @param [value] [Int], 저장될 signed 4 bytes
     */
    private fun writeSInt4(value: Int) {
        val buffer: ByteArray = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array()
        writeBytes(buffer)
    }

    /**
     * unsigned 1 byte를 스트림에 저장하는 함수
     *
     * @param [value] [Short], 저장될 unsigned 1 byte
     */
    private fun writeUInt1(value: Short) {
        val buffer: ByteArray = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(value).array()
        os.write(buffer, 0, 1)
    }

    /**
     * unsigned 2 bytes 정수를 스트림에 저장하는 함수
     *
     * @param [value] [Int], 저장될 unsigned 4 bytes
     */
    private fun writeUInt2(value: Int) {
        val buffer: ByteArray = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array()
        os.write(buffer, 0, 2)
    }

    /**
     * unsigned 4 bytes 정수를 스트림에 저장하는 함수
     *
     * @param [value] [Long], 저장될 unsigned 4 bytes
     */
    private fun writeUInt4(value: Long) {
        val buffer: ByteArray = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(value).array()
        os.write(buffer, 0, 4)
    }

    /**
     * 배정도 실수[Double]를 스트림에 저장하는 함수
     *
     * @param [value] [Double], 저장될 실수
     */
    fun writeDouble(value: Double) {
        val buffer: ByteArray = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(value).array()
        os.write(buffer, 0, 8)
    }

    /**
     * 단정도 실수[Float]을 스트림에 저장하는 함수
     *
     * @param [value] [Float], 저장될 실수
     */
    fun writeFloat(value: Float) {
        val buffer: ByteArray = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(value).array()
        os.write(buffer, 0, 4)
    }

    /**
     * BYTE를 스트림에 저장하는 함수
     *
     * @param [value] [Short], 저장될 BYTE
     */
    fun writeByte(value: Short) {
        writeUInt8(value)
    }

    /**
     * WORD를 스트림에 저장하는 함수
     *
     * @param [value] [Long], 저장될 WORD
     */
    fun writeWord(value: Int) {
        writeUInt16(value)
    }

    /**
     * DWORD를 스트림에 저장하는 함수
     *
     * @param [value] [Long], 저장될 DWORD
     */
    fun writeDWord(value: Long) {
        writeUInt32(value)
    }

    /**
     * HWPUNIT을 스트림에 저장하는 함수
     *
     * @param [value] [Long], 저장될 HWPUNIT
     */
    fun writeHwpUnit(value: Long) {
        writeUInt32(value)
    }

    /**
     * SHWPUNIT을 스트림에 저장하는 함수
     *
     * @param [value] [Int], 저장될 SHWPUNIT
     */
    fun writeSHwpUnit(value: Int) {
        writeInt32(value)
    }

    /**
     * HWPUNIT16을 스트림에 저장하는 함수
     *
     * @param [value] [Short], 저장될 HWPUNIT16
     */
    fun writeHwpUnit16(value: Short) {
        writeInt16(value)
    }

    /**
     * INT8을 스트림에 저장하는 함수
     *
     * @param [value] [Byte], 저장될 INT8
     */
    fun writeInt8(value: Byte) {
        writeSInt1(value)
    }

    /**
     * INT16을 스트림에 저장하는 함수
     *
     * @param [value] [Short], 저장될 INT16
     */
    fun writeInt16(value: Short) {
        writeSInt2(value)
    }

    /**
     * INT32를 스트림에 저장하는 함수
     *
     * @param [value] [Int], 저장될 INT32
     */
    fun writeInt32(value: Int) {
        writeSInt4(value)
    }

    /**
     * UINT8을 스트림에 저장하는 함수
     *
     * @param [value] [Short], 저장될 UINT8
     */
    fun writeUInt8(value: Short) {
        writeUInt1(value)
    }

    /**
     * UINT16을 스트림에 저장하는 함수
     *
     * @param [value] [Int], 저장될 UINT16
     */
    fun writeUInt16(value: Int) {
        writeUInt2(value)
    }

    /**
     * UINT32를 스트림에 저장하는 함수
     *
     * @param [value] [Long], 저장될 UINT32
     */
    fun writeUInt32(value: Long) {
        writeUInt4(value)
    }

    /**
     * COLORREF를 스트림에 저장하는 함수
     *
     * @param [value] [Long], 저장될 COLORREF
     */
    fun writeColorRef(value: Long) {
        writeUInt32(value)
    }

    /**
     * 레코드 헤더를 스트림에 저장하는 함수
     *
     * @param [tagID] [Int], 레코드의 태그 아이디
     * @param [size] [Int], 레코드의 크기
     */
    fun writeRecordHeader(tagID: Int, size: Int) {
        var header: Long = 0
        header = set(header, 0, 9, tagID)
        header = set(header, 10, 19, currentRecordLevel)
        header = set(header, 20, 31, Math.min(size, 4095))
        writeUInt4(header)
    }

    /**
     * 문자열(UTF-16LE)를 스트림에 저장하는 함수
     *
     * @param [value] 저장될 문자열
     */
    fun writeUTF16LEString(value: String?) {
        if (value == null) writeUInt2(0)
        else {
            writeUInt2(value.length)
            if (value.isNotEmpty()) writeBytes(value.toByteArray(Charset.forName("UTF-16LE")))
        }
    }

    /**
     * 한글의 기본 코드로 유니코드 기반 문자를 스트림에 저장하는 함수
     *
     * @param [value] 저장될 문자
     */
    fun writeWChar(value: String?) {
        if (value != null && value.isNotEmpty()) {
            val buffer: ByteArray = value.toByteArray(Charset.forName("UTF-16LE"))
            if (buffer.size >= 2) os.write(buffer, 0, 2)
            else writeZero(2)
        } else
            writeZero(2)
    }

    /**
     * 0값의 byte를 지정된 개수만큼 스트림에 저장하는 함수
     *
     * @param [number] 저장할 개수
     */
    fun writeZero(number: Int) {
        if (number > 0) {
            val zero: ByteArray = ByteArray(number)
            os.write(zero)
        }
    }

    /**
     * 레코드 레벨을 증가시키는 함수
     */
    fun upRecordLevel() { currentRecordLevel++ }

    /**
     * 레코드 레벨을 감소시키는 함수
     */
    fun downRecordLevel() { currentRecordLevel-- }

    /**
     * 스트림에 저장된 데이터를 압축하고 반환하는 함수
     *
     * @return [ByteArray] 압축된 데이터 반환
     */
    private fun compressBytes() : ByteArray {
        val original: ByteArray = os.toByteArray()
        val bos: ByteArrayOutputStream = ByteArrayOutputStream()
        val compressor: Deflater = Deflater(Deflater.DEFAULT_COMPRESSION, true).apply {
            setInput(original)
            finish()
        }
        val buf: ByteArray = ByteArray(1024)
        while (!compressor.finished()) {
            val count: Int = compressor.deflate(buf)
            bos.write(buf, 0, count)
        }
        val zero: ByteArray = ByteArray(4)
        bos.write(zero)
        val length: ByteArray = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(original.size).array()
        bos.write(length)
        return bos.toByteArray()

    }
}