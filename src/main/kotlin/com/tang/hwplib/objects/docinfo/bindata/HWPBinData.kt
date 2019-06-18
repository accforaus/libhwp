package com.tang.hwplib.objects.docinfo.bindata

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * 바이너리 데이터 압축 (bit 4-5)
 *
 * @author accforaus
 *
 * @property [value] [Byte], 저장된 데이터 값
 */
enum class HWPBinDataCompress(v: Byte) {
    /**
     * 스토리지의 디폴트 모드 (0x0000)
     */
    ByStorageDefault(0.toByte()),
    /**
     * 무조건 압축 (0x0010)
     */
    Compress(1.toByte()),
    /**
     * 무조건 압축하지 않은 (0x0020)
     */
    NoCompress(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPBinDataCompress] enum 값
         */
        fun valueOf(v: Byte) : HWPBinDataCompress = values().find { it.value == v } ?: ByStorageDefault
    }
}

/**
 * 바이너리 데이터 타입 (bit 0-3)
 *
 * @author accforaus
 *
 * @property [value] [Byte], 저장된 데이터 값
 */
enum class HWPBinDataType(v: Byte) {
    /**
     * LINK, 그림 외부 파일 참조 (0x0000)
     */
    Link(0.toByte()),
    /**
     * EMBEDDING, 그림 파일 포함 (0x0001)
     */
    Embedding(1.toByte()),
    /**
     * STORAGE, OLE 포함 (0x0002)
     */
    Storage(2.toByte());

    val value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPBinDataType] enum 값
         */
        fun valueOf(v: Byte) : HWPBinDataType = values().find { it.value == v } ?: Link
    }
}

/**
 * 바이너리 데이터 상태 (bit 8-9)
 *
 * @author accforaus
 *
 * @property [value] [Byte], 저장된 데이터 값
 */
enum class HWPBinDataState(v: Byte) {
    /**
     * 아직 access된 적 없는 상태 (0x0000)
     */
    NotAccess(0.toByte()),
    /**
     * access에 성공하여 파일을 찾은 상태 (0x0100)
     */
    SuccessAccess(1.toByte()),
    /**
     * access가 실패한 에러 상태 (0x0200)
     */
    FailAccess(2.toByte()),
    /**
     * 링크 access가 실패했으나 무시된 상태 (0x0300)
     */
    FailAccessButIgnore(3.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPBinDataState] enum 값
         */
        fun valueOf(v: Byte) : HWPBinDataState = values().find { it.value == v } ?: NotAccess
    }
}

/**
 * 바이너리 데이터 속성을 나타내는 객체 (UINT16) (unsigned 2 bytes)
 *
 * @author accforaus
 *
 * @property [value] [Int], 속성값을 가진 데이터
 */
class HWPBinDataProperty {
    var value: Int = 0
        set(newValue) {
            field = newValue
            _type = getType()
            _compress = getCompress()
            _state = getState()
        }
    private var _type: HWPBinDataType = HWPBinDataType.Storage
    private var _compress: HWPBinDataCompress = HWPBinDataCompress.Compress
    private var _state: HWPBinDataState = HWPBinDataState.NotAccess
    /**
     * 바이너리 데이터 타입을 반환하는 함수
     * bit 0-3
     *
     * @return [HWPBinDataProperty], 바이너리 데이터 타입을 반환
     */
    fun getType() : HWPBinDataType = HWPBinDataType.valueOf(get(value, 0,3).toByte())

    /**
     * 바이너리 데이터 타입을 설정하는 함수
     * bit 0-3
     *
     * @param [type] [HWPBinDataType], 바이너리 데이터 타입이 저장된 데이터
     */
    fun setType(type: HWPBinDataType) {
        value = set(value, 0, 3, type.value.toInt())
    }

    /**
     * 바이너리 데이터 압축을 반환하는 함수
     * bit 4-5
     *
     * @return [HWPBinDataCompress], 바이너리 데이터 압축을 반환
     */
    fun getCompress(): HWPBinDataCompress = HWPBinDataCompress.valueOf(get(value, 4, 5).toByte())

    /**
     * 바이너리 데이터 압축을 설정하는 함수
     * bit 4-5
     *
     * @param [compress] [HWPBinDataCompress], 바이너리 데이터 압축값을 가진 데이터
     */
    fun setCompress(compress: HWPBinDataCompress) {
        value = set(value, 4, 5, compress.value.toInt())
    }

    /**
     * 바이너리 데이터 상태를 반환하는 함수
     * bit 8-9
     *
     * @return [HWPBinDataState], 바이너리 데이터 상태를 반환
     */
    fun getState() : HWPBinDataState = HWPBinDataState.valueOf(get(value, 8, 9).toByte())

    /**
     * 바이너리 데이터 상태를 설정하는 함수
     * bit 8-9
     *
     * @param [state] [HWPBinDataState], 바이너리 데이터 상태를 가진 데이터
     */
    fun setState(state: HWPBinDataState) {
        value = set(value,8, 9, state.value.toInt())
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPBinDataProperty] 생성된 객체 반환
         */
        fun build(type: HWPBinDataType = HWPBinDataType.Link,
                  compress: HWPBinDataCompress = HWPBinDataCompress.ByStorageDefault,
                  state: HWPBinDataState = HWPBinDataState.NotAccess):
                HWPBinDataProperty = HWPBinDataProperty().apply {
            setType(type)
            setCompress(compress)
            setState(state)
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPBinDataProperty] 생성된 객체 반환
         */
        fun build(value: Int = 0): HWPBinDataProperty = HWPBinDataProperty().apply {
            this.value = value
        }
    }
}