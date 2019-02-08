package com.tang.hwplib.reader.util

import com.tang.hwplib.objects.HWPRecordHeader
import com.tang.hwplib.objects.docinfo.HWPDocInfo
import com.tang.hwplib.objects.fileheader.HWPFileVersion
import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.exceptions.StreamReaderException
import org.apache.poi.poifs.filesystem.DocumentEntry
import org.apache.poi.poifs.filesystem.DocumentInputStream
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.Charset
import java.util.zip.DataFormatException
import java.util.zip.Inflater
import kotlin.experimental.and

/**
 * Microsoft compound File Stream [MS-CFB] 를 읽기 위한 스트림 객체
 * @see <link href='https://msdn.microsoft.com/ko-kr/library/dd942138.aspx'>
 *
 * @author accforaus
 *
 * @property [size] [Long], Stream 크기
 * @property [read] [Long], 가지는 현재까지 읽은 바이트 수
 * @property [header] [HWPRecordHeader], 가지는 HWP 파일 레코드 헤더객체
 * @property [readAfterHeader] [Long], 헤더를 읽은 후 부터 현재까지 읽은 바이트 수
 * @property [docInfo] [HWPDocInfo], HWP 파일의 문서정보를 가지는 객체
 * @property [fileVersion] [HWPFileVersion], HWP 파일의 버전을 나타내는 객체
 */
internal abstract class StreamReader protected constructor() {
    var size: Long = 0
    var read: Long = 0
    var header: HWPRecordHeader = HWPRecordHeader()
    var readAfterHeader: Long = 0
    var docInfo: HWPDocInfo? = null
    var fileVersion: HWPFileVersion? = null

    /**
     * byte 배열의 크기 만큼 byte 배열을 읽는 함수
     * @param [buffer] [ByteArray], 읽을 byte 배열
     * @return [Unit]
     */
    abstract fun readBytes(buffer: ByteArray) : Unit

    /**
     * signed 1 byte 정수값을 읽어서 [Byte] 값을 반환하는 함수
     * @throws [NullPointerException]
     * @return [Byte]값
     */
    protected abstract fun readSInt1() : Byte?

    /**
     * signed 2 byte 정수값을 읽어서 [Short]값을 반환하는 함수
     * @throws [NullPointerException]
     * @return [Short]값
     */
    protected abstract fun readSInt2() : Short?

    /**
     * signed 4 byte 정수값을 읽어서 [Int]값을 반환하는 함수
     * @throws [NullPointerException]
     * @return [Int]값
     */
    protected abstract fun readSInt4() : Int?

    /**
     * unsigned 1 byte 정수값을 읽어서 [Short]값을 반환하는 함수
     * @throws [NullPointerException]
     * @return [Short]값
     */
    protected abstract fun readUInt1() : Short?

    /**
     * unsigned 2 byte 정수값을 읽어서 [Int]값을 반환하는 함수
     * @throws [NullPointerException]
     * @return [Int]값
     */
    protected abstract fun readUInt2() : Int?

    /**
     * unsigned 4 byte 정수값을 읽어서 [Long]값을 반환하는 함수
     * @throws [NullPointerException]
     * @return [Long]값
     */
    protected abstract fun readUInt4() : Long?

    /**
     * double 값을 읽고 [Double]값을 반환하는 함수
     * @throws [NullPointerException]
     * @return [Double]값
     */
    abstract fun readDouble() : Double?

    /**
     * float 값을 읽고 [Float]값을 반환하는 함수
     * @return [Float]값
     */
    abstract fun readFloat() : Float

    /**
     * n 바이트 만큼 건너뛰는 함수
     * @param [n] [Long], 건너뛸 byte 수
     * @return [Unit]값
     */
    abstract fun skip(n: Long) : Unit

    /**
     * 스트림을 읽기 위한 객체를 닫는 함수
     * @throws [NullPointerException]
     * @return [Unit]
     */
    abstract fun close() : Unit

    /**
     * 현재까지 읽은 byte 수, 헤더를 읽은 후 부터 현재까지 읽은 byte 수의 값을 n만큼 추가하는 함수
     *
     * @param [n] [Long], 추가할 byte의 수
     * @return [Unit]
     */
    protected fun forwardPosition(n: Long) {
        this.read += n
        this.readAfterHeader += n
    }

    /**
     * HWP 레코드 헤더를 읽고 반환하는 함수
     *
     * @throws [NullPointerException]
     * @return [header] [HWPRecordHeader], 한글 레코드 헤더
     */
    fun readRecordHeader(): HWPRecordHeader = header.apply {
        val value: Long = readUInt4()!!
        tagId = get(value, 0, 9).toShort()
        level = get(value, 10, 19).toShort()
        size = get(value, 20, 31)
        readAfterHeader = 0
    }

    /**
     * HWP 파일의 문자열을 읽고 반환하는 함수
     * HWP 파일의 문자열은 2 byte의 문자열 길이가 저장된 부분 뒤에 UTF-16LE 형태의 byte로 된 문자열 부분이 따른다
     * WCHAR 2 한글의 기본 코드로 유니코드 기반 문자
     * 힌글, 영문, 한자를 비롯한 모든 문자가 2 byte의 일정한 길이를 가진다.
     *
     * @throws [NullPointerException]
     * @return [String] 문자열 값
     */
    fun readUTF16LEString(): String? = Unit.run{
        val len: Int = readWord()
        when (len) {
            in 1..Int.MAX_VALUE -> {
                val arr: ByteArray = ByteArray(len * 2)
                readBytes(arr)
                String(arr, 0, arr.size, Charset.forName("UTF_16LE"))
            }
            else -> null
        }
    }

    /**
     * 한 글자를 읽어서 반환하는 함수
     * HWP 파일의 문자열은 2 byte의 문자열 길이가 저장된 부분 뒤에 UTF-16LE 형태의 byte로 된 문자열 부분이 따른다
     * WCHAR 2 한글의 기본 코드로 유니코드 기반 문자
     * 힌글, 영문, 한자를 비롯한 모든 문자가 2 byte의 일정한 길이를 가진다.
     *
     * @return [String] 한 글자 값
     */
    fun readWChar(): String = Unit.run {
        val arr: ByteArray = ByteArray(2)
        readBytes(arr)
        return String(arr, 0, 2, Charset.forName("UTF_16LE"))
    }

    /**
     * BYTE 값을 반환하는 함수
     * 부호 없는 한 바이트 (0-255)
     * unsigned 1 byte
     *
     * @throws [StreamReaderException] BYTE 값이 NULL 값일 때
     * @return [Short] BYTE 값을 반환
     */
    fun readByte(): Short = readUInt1() ?: throw StreamReaderException("BYTE cannot be null")

    /**
     * WORD 값을 반환하는 함수
     * 16비트 컴파일러에서 'unsigned int'에 해당
     * unsigned 2 bytes
     *
     * @throws [StreamReaderException] WORD 값이 NULL 값일 때
     * @return [Int] WORD 값을 반환
     */
    fun readWord(): Int = readUInt2() ?: throw StreamReaderException("WORD cannot be null")

    /**
     * DWORD 값을 반환하는 함수
     * 16비트 컴파일러에서 'unsigned long'에 해당
     * unsigned 4 bytes
     *
     * @throws [StreamReaderException] DWORD 값이 NULL 값일 때
     * @return [Long] DWORD 값을 반환
     */
    fun readDWord(): Long = readUInt4() ?: throw StreamReaderException("DWORD cannot be null")

    /**
     * HWPUNIT 값을 반환하는 함수
     * 1/7200 인치로 표현된 한글 내부 단위
     * unsigned 4 bytes
     *
     * @throws [StreamReaderException] HWPUNIT 값이 NULL 일 때
     * @return [Long] HWPUNIT 값을 반환
     */
    fun readHwpUnit(): Long = readUInt4() ?: throw StreamReaderException("HWPUINT cannot be null")

    /**
     * SHWPUNIT 값을 반환하는 함수
     * 1/7200 인치로 표현된 한글 내부 단위
     * signed 4 bytes
     *
     * @throws [StreamReaderException] SHWPUNIT 값이 NULL 일 때
     * @return [Int] SHWPUNIT 값을 반환
     */
    fun readSHwpUnit(): Int = readSInt4() ?: throw StreamReaderException("SHWPUNIT cannot be null")

    /**
     * HWPUNIT16 값을 반환하는 함수
     * INT16과 같다
     * signed 2 bytes
     *
     * @throws [StreamReaderException] HWPUNIT16 값이 NULL 일 때
     * @return [Short] HWPUNIT16 값을 반환
     */
    fun readHwpUnit16(): Short = readSInt2() ?: throw StreamReaderException("HWPUNIT16 cannot be null")

    /**
     * UINT8 값을 반환하는 함수
     * 'unsigned __int8'에 해당
     * unsigned 1 byte
     *
     * @throws [StreamReaderException] UINT8 값이 NULL 일 때
     * @return [Short] UINT8 값을 반환
     */
    fun readUInt8(): Short = readUInt1() ?: throw StreamReaderException("UINT8 cannot be null")

    /**
     * UINT16 값을 반환하는 함수
     * 'unsigned __int16'에 해당
     * unsigned 2 bytes
     *
     * @throws [StreamReaderException] UINT16 값이 NULL 일 때
     * @return [Int] UINT16 값을 반환
     */
    fun readUInt16(): Int = readUInt2() ?: throw StreamReaderException("UINT16 cannot be null")

    /**
     * UINT32(=UINT)값을 반환하는 함수
     * 'unsigned __int32'에 해당
     * unsigned 4 bytes
     *
     * @throws [StreamReaderException] UINT32(=UINT) 값이 NULL 일 때
     * @return [Long] UINT(=UINT32) 값을 반환
     */
    fun readUInt32(): Long = readUInt4() ?: throw StreamReaderException("UINT32(=UINT) cannot be null")

    /**
     * INT8 값을 반환하는 함수
     * 'signed __int8'에 해당
     * signed 1 byte
     *
     * @throws [StreamReaderException] INT8 값이 NULL 일 때
     * @return [Byte] INT8 값을 반환
     */
    fun readInt8(): Byte = readSInt1() ?: throw StreamReaderException("INT8 cannot be null")

    /**
     * INT16 값을 반환하는 함수
     * 'signed __int16'에 해당
     * signed 2 bytes
     *
     * @throws [StreamReaderException] INT16 값이 NULL 일 때
     * @return [Short] INT16 값을 반환
     */
    fun readInt16(): Short = readSInt2() ?: throw StreamReaderException("INT16 cannot be null")

    /**
     * INT32 값을 반환하는 함수
     * 'signed __int32'에 해당
     * signed 4 bytes
     *
     * @throws [StreamReaderException] INT32 값이 NULL 일 때
     * @return [Int] INT32 값을 반환
     */
    fun readInt32(): Int = readSInt4() ?: throw StreamReaderException("INT32 cannot be null")

    /**
     * COLORREF 값을 반환하는 함수
     * RGB값 (0x00bbggrr)을 십진수로 표시
     * (rr: red 1 byte, gg: green 1 byte, bb: blue 1byte)
     * unsigned 4 bytes
     *
     * @throws [StreamReaderException] COLORREF 값이 NULL 일 때
     * @return [Long] COLORREF 값을 반환
     */
    fun readColorRef(): Long = readUInt4() ?: throw StreamReaderException("COLORREF cannot be null")

    /**
     * 현재 위치가 스트림의 끝인지 여부를 반환하는 함수
     *
     * @return [Boolean] 현재 위치가 스트림의 끝인지 여부
     */
    fun isEndOfStream(): Boolean = read >= size

    /**
     * 현재 위치가 레코드의 끝인지 여부를 반환하는 함수
     *
     * @return [Boolean] 현재 위치가 레코드의 끝인지 여부
     */
    fun isEndOfRecord(): Boolean = readAfterHeader >= header.size

    /**
     * 레코드 헤더를 읽은 직후 인지 여부를 반환하는 함수
     *
     * @return [Boolean] 레코드 헤더를 읽은 직후 인지 여부
     */
    fun isImmediatelyAfterReadingHeader() : Boolean = readAfterHeader.toInt() == 0

    /**
     * 레코드를 끝으로 이동시키는 함수
     *
     * @return [Unit]
     */
    fun skipToEndRecord() {
        val n : Long = header.size - readAfterHeader
        if (n > 0) skip(n)
    }

    /**
     * 문단 모양 ID값을 반환하는 함수
     * [docInfo]가 Null값이 아니라면 문단 모양 ID값을 조정후 반환시킨다.
     *
     * @param [oldParaShapeId] [Int], 값을 조정할 문단 모양 ID 값
     * return [oldParaShapeId] [Int], 값이 조정된 문단 모양 ID
     */
    fun correctParaShapeId(oldParaShapeId: Int): Int = docInfo?.run {
        return oldParaShapeId - idMappings.paraShapeCount + paraShapeList.size
    } ?: oldParaShapeId
}

/**
 * 압축된 스트림을 읽기 위한 객체
 *
 * @author accforaus
 *
 * @property [bis] [ByteArrayInputStream] 압축 풀린 데이터를 읽기 위한 InputStream
 * @constructor 압축된 스트림을 읽어 압축을 풀어서 압축 풀린 데이터로 InputStream을 만든다.
 *              @param [de] [DocumentEntry], 스트림을 가르키는 Apache POI객체
 *              @param [fileVersion] [HWPFileVersion], HWP 문서의 파일 정보
 *              @throws [Exception]
 */
internal class StreamReaderForCompress(de: DocumentEntry, fileVersion: HWPFileVersion?) : StreamReader() {
    private val bis: ByteArrayInputStream = DocumentInputStream(de).let {
        this.fileVersion = fileVersion
        val compressed: ByteArray = getCompressedBytes(it, de.size)
        try {
            val decompressed: ByteArray = decompress(compressed)
            this.size = decompressed.size.toLong()
            ByteArrayInputStream(decompressed)
        } catch (e: DataFormatException) {
            this.size = compressed.size.toLong()
            ByteArrayInputStream(compressed)
        }
    }
    /*
    private fun setByteArrayInputStream(de: DocumentEntry) : Unit {
        val dis: DocumentInputStream = DocumentInputStream(de)
        val compressed: ByteArray = getCompressedBytes(dis, de.size)
        try {
            val decompressed: ByteArray = decompress(compressed)
            bis = ByteArrayInputStream(decompressed)
            size = decompressed.size.toLong()
        } catch (e: DataFormatException) {
            bis = ByteArrayInputStream(compressed)
            size = compressed.size.toLong()
        }
    }
    */

    /**
     * 스트림에서 압축된 데이터를 읽고 반환하는 함수
     *
     * @param [dis] [DocumentInputStream], 스트림을 읽기 위한
     */
    private fun getCompressedBytes(dis: DocumentInputStream, size: Int) : ByteArray {
        val buffer: ByteArray = ByteArray(size)
        dis.read(buffer)
        return buffer
    }

    /**
     * 압축된 데이터를 풀어서 원본 데이터를 얻는 함수
     *
     * @param [compressed] [ByteArray], 압축된 데이터 배열
     * @throws [DataFormatException]
     * @throws [NullPointerException]
     * @return [ByteArray] 원본 데이터 배열
     */
    private fun decompress(compressed: ByteArray) : ByteArray {
        val decompressor: Inflater = Inflater(true)
        decompressor.setInput(compressed, 0, compressed.size)
        val bos: ByteArrayOutputStream = ByteArrayOutputStream(compressed.size)

        val buf: ByteArray = ByteArray(8096)
        while (!decompressor.finished()) {
            val count: Int = decompressor.inflate(buf)
            bos.write(buf, 0, count)
        }
        bos.close()
        return bos.toByteArray()
    }

    override fun readBytes(buffer: ByteArray) {
        forwardPosition(buffer.size.toLong())
        bis.read(buffer)
    }

    override fun readSInt1(): Byte? {
        val buffer: ByteArray = readBytes(1)
        return buffer[0]
    }

    override fun readFloat(): Float = ByteBuffer.wrap(readBytes(4)).order(ByteOrder.LITTLE_ENDIAN).float

    /**
     * n(number) byte를 읽고, byte배열을 반환하는 함수
     *
     * @param [n] [Int], 읽을 바이트 수
     * @exception [NullPointerException]
     * @return [ByteArray] 새로 읽은 byte 배열
     */
    private fun readBytes(n: Int) : ByteArray {
        val buffer: ByteArray = ByteArray(n)
        readBytes(buffer)
        return buffer
    }

    override fun readSInt2(): Short? = ByteBuffer.wrap(readBytes(2)).order(ByteOrder.LITTLE_ENDIAN).short

    override fun readSInt4(): Int? = ByteBuffer.wrap(readBytes(4)).order(ByteOrder.LITTLE_ENDIAN).int

    override fun readUInt1(): Short? = readSInt1()!!.toShort().and(0xff)

    override fun readUInt2(): Int? = readSInt2()!!.toInt().and(0xffff)

    override fun readUInt4(): Long? = readSInt4()!!.and(-1).toLong()

    override fun readDouble(): Double? = ByteBuffer.wrap(readBytes(8)).order(ByteOrder.LITTLE_ENDIAN).double

    override fun skip(n: Long) {
        readBytes(n.toInt())
    }

    override fun close() {
        bis.close()
    }
}

/**
* 일반적인 스트림을 읽기 위한 객체
*
* @author accforaus
*
* @property [dis] [DocumentInputStream] Stream을 읽기 위한 Apache POI InputStream 객체
* @constructor 생성자
*              @param [de] [DocumentEntry], 스트림을 가르키는 Apache POI객체
*              @param [fileVersion] [HWPFileVersion], HWP 문서의 파일 정보
*              @throws [Exception]
*/
internal class StreamReaderForNormal(de: DocumentEntry, fileVersion: HWPFileVersion?) : StreamReader() {
    private val dis: DocumentInputStream = DocumentInputStream(de).also {
        this.size = de.size.toLong()
        this.fileVersion = fileVersion
    }

    override fun readBytes(buffer: ByteArray) {
        forwardPosition(buffer.size.toLong())
        dis.read(buffer)
    }

    override fun readSInt1(): Byte? {
        forwardPosition(1)
        return dis.readByte()
    }

    override fun readSInt2(): Short? {
        forwardPosition(2)
        return dis.readShort()
    }

    override fun readSInt4(): Int? {
        forwardPosition(4)
        return dis.readInt()
    }

    override fun readUInt1(): Short? {
        forwardPosition(1)
        return dis.readByte().toShort().and(0xff)
    }

    override fun readUInt2(): Int? {
        forwardPosition(2)
        return dis.readShort().toInt().and(0xffff)
    }

    override fun readUInt4(): Long? {
        forwardPosition(4)
        return dis.readInt().and(0x00000000ffffffffL.toInt()).toLong()
    }

    override fun readDouble(): Double? {
        forwardPosition(8)
        return dis.readDouble()
    }

    override fun readFloat(): Float {
        val arr: ByteArray = ByteArray(4)
        readBytes(arr)
        return ByteBuffer.wrap(arr).order(ByteOrder.LITTLE_ENDIAN).float
    }

    override fun skip(n: Long) {
        forwardPosition(n)
        dis.skip(n)
    }

    override fun close() {
        dis.close()
    }
}