package com.tang.hwplib.objects.bodytext.control.bookmark

import com.tang.hwplib.objects.docinfo.HWPBinData
import com.tang.hwplib.objects.etc.DOC_DATA

/**
 * 파라미터 아이템 종류
 *
 * @author accforaus
 *
 * @property [value] [Int], 파라미터 아이템 값
 */
enum class HWPParameterType(v: Int) {
    /**
     * NULL
     */
    NULL(0),
    /**
     * 문자열
     */
    String(1),
    /**
     * INT8
     */
    Integer1(2),
    /**
     * INT16
     */
    Integer2(3),
    /**
     * INT32
     */
    Integer4(4),
    /**
     * INT
     */
    Integer(5),
    /**
     * UINT8
     */
    UnsignedInteger1(6),
    /**
     * UINT16
     */
    UnsignedInteger2(7),
    /**
     * UINT32
     */
    UnsignedInteger4(8),
    /**
     * UINT
     */
    UnsignedInteger(9),
    /**
     * 파라미터 셋
     */
    ParameterSet(0x8000),
    /**
     * 파라미터 셋 배열
     */
    Array(0x8001),
    /**
     * 바이너리 데이터[HWPBinData] ID
     */
    BINDataID(0x8002);

    var value: Int = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Int], 파일에 저장되는 정수값
         * @return [HWPParameterType] enum 값
         */
        fun valueOf(v: Int) : HWPParameterType {
            for (pt in values())
                if (pt.value == v)
                    return pt
            return NULL
        }
    }
}

/**
 * 파라미터 셋을 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [id] [Int], 파라미터 셋 ID (WORD - unsigned 2 byte)
 * @property [parameterItemList] [ArrayList] 파라미터 아이템
 */
class HWPParameterSet {
    var id: Int = 0
    var parameterItemList: ArrayList<HWPParameterItem> = ArrayList()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPParameterSet] 복사된 객체 반환
     */
    fun copy() : HWPParameterSet = HWPParameterSet().also {
        it.id = this.id
        for (parameterItem in this.parameterItemList)
            it.parameterItemList.add(parameterItem.copy())
    }
    /**
     * 파라미터 아이템을 추가하고 반환하는 함수
     *
     * @return [HWPParameterItem]  생성된 객체 밚놘
     */
    fun addNewParameterItem() : HWPParameterItem = HWPParameterItem().apply { parameterItemList.add(this) }

    /**
     * id 값이 같은 파라미터 아이템을 반환하는 함수
     *
     * @param [id] [Int] ID 값이 같은 파라미터 아이템 (존재하지 않으면 NULL)
     */
    fun getParameterItem(id: Int) : HWPParameterItem? = Any().run {
        for (pi in parameterItemList)
            if (pi.id == id.toLong())
                return pi
        return null
    }

    companion object {
        /**
         * 필드이름으로 파라미터 셋을 생성하는
         *
         * @param [fieldName] [String] 필드 이름
         * @return [HWPParameterSet] 생성된 객체 반환 (존재하지 않으면 NULL)
         */
        fun createForFieldName(fieldName: String?) : HWPParameterSet? {
            if (fieldName == null)
                return null
            val ps: HWPParameterSet = HWPParameterSet()
            ps.id = 0x21b
            val pi: HWPParameterItem = ps.addNewParameterItem()
            pi.id = 0x4000.toLong()
            pi.type = HWPParameterType.String
            pi.value_BSTR = fieldName
            return ps
        }

        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPParameterSet] 생성된 객체 반환
         */
        fun build(id: Int = 0,
                  parameterItemGenerator: () -> ArrayList<HWPParameterItem> = {ArrayList()})
                : HWPParameterSet = HWPParameterSet().apply {
            this.id = id
            this.parameterItemList = parameterItemGenerator()
        }
    }
}

/**
 * 파라미터 아이템을 나타내는 객체
 * 가변 길이
 *
 * @author acforaus
 *
 * @property [id] [Long], 파라미터 아이템 ID (WORD - unsigned 2 bytes)
 * @property [type] [HWPParameterType], 파라미터 아이템 종류 (WORD - unsigned 2 bytes)
 * @property [value_BSTR] [String], 문자열, (WCHAR array)
 * @property [value_I1] [Byte], INT8 (UINT32 - unsigned 4 bytes)
 * @property [value_I2] [Short], INT16 (UINT32 - unsigned 4 bytes)
 * @property [value_I4] [Int], INT32 (UINT32 - unsigned 4 bytes)
 * @property [value_I] [Int], INT (UINT32 - unsigned 4 bytes)
 * @property [value_UI1] [Short], UINT8 (UINT32 - unsigned 4 bytes)
 * @property [value_UI2] [Int], UINT16 (UINT32 - unsigned 4 bytes)
 * @property [value_UI4] [Long], UINT32 (UINT32 - unsigned 4 bytes)
 * @property [value_UI] [Long], UINT (UINT32 - unsigned 4 bytes)
 * @property [value_ParameterSet] [HWPParameterSet], 파라미터 셋
 * @property [value_ParameterArray] [Array], 파라미터 셋 배열
 * @property [value_binData] [Int], 바이너리 데이터[HWPBinData] ID
 */
class HWPParameterItem {
    var id: Long = 0
    var type: HWPParameterType? = null
    var value_BSTR: String? = null
    var value_I1: Byte = 0
    var value_I2: Short = 0
    var value_I4: Int = 0
    var value_I: Int = 0
    var value_UI1: Short = 0
    var value_UI2: Int = 0
    var value_UI4: Long = 0
    var value_UI: Long = 0
    var value_ParameterSet: HWPParameterSet? = null
    var value_ParameterArray: Array<HWPParameterItem>? = null
    var value_binData: Int = -1

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPParameterItem] 복사된 객체 반환
     */
    fun copy() : HWPParameterItem = HWPParameterItem().also {
        it.id = this.id
        this.type?.run { it.type = HWPParameterType.valueOf(this.value) }
        it.value_BSTR = this.value_BSTR
        it.value_I1 = this.value_I1
        it.value_I2 = this.value_I2
        it.value_I4 = this.value_I4
        it.value_I = this.value_I4
        it.value_UI1 = this.value_UI1
        it.value_UI2 = this.value_UI2
        it.value_UI4 = this.value_UI4
        it.value_UI = this.value_UI
        this.value_ParameterSet?.run { it.value_ParameterSet = this.copy() }
        this.value_ParameterArray?.run {
            it.value_ParameterArray = Array(this.size, {HWPParameterItem()})
            for ((index,parameterItem) in this.withIndex())
                it.value_ParameterArray!![index] = parameterItem.copy()
        }
        it.value_binData = this.value_binData
    }
    /**
     * 파라미터 셋을 생성하는 함수
     */
    fun createValue_ParameterSet() {
        value_ParameterSet = HWPParameterSet()
    }

    /**
     * 파라미터 배열 길이를 반환하는 함수
     *
     * @return [Int] 파라미터 배열 길이
     */
    fun getValue_ParameterArrayCount() : Int = value_ParameterArray?.size ?: 0

    /**
     * 인덱스에 위치한 파라미터 아이템을 반환하는 함수
     *
     * @param [index] [Int], 인덱스
     * @return [HWPParameterItem] 인덱스에 위치한 객체 반환 (존재하지 않으면 NULL)
     */
    fun getValue_ParameterArray(index: Int): HWPParameterItem? = value_ParameterArray?.get(index)

    /**
     * 주어진 크기 만큼 새로운 파라미터 배열을 생성하는 함수
     *
     * @param [count] [Int], 생성할 크기
     */
    fun createValue_ParameterArray(count: Int) {
        value_ParameterArray = Array(count, init = {HWPParameterItem()})
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPParameterItem] 생성된 객체 반환
         */
        fun build(id: Long = 0, type: HWPParameterType? = null,
                  value_BSTR: String? = null, value_I1: Byte = 0,
                  value_I2: Short = 0, value_I4: Int = 0, value_I: Int = 0,
                  value_UI1: Short = 0, value_UI2: Int = 0, value_UI4: Long = 0,
                  value_UI: Long = 0, value_ParameterSet: HWPParameterSet? = null,
                  value_parameterArray_generator: () -> Array<HWPParameterItem>? = { null },
                  value_binData: Int = -1)
                : HWPParameterItem = HWPParameterItem().apply {
            this.id = id
            this.type = type
            this.value_BSTR = value_BSTR
            this.value_I1 = value_I1
            this.value_I2 = value_I2
            this.value_I4 = value_I4
            this.value_I = value_I
            this.value_UI1 = value_UI1
            this.value_UI2 = value_UI2
            this.value_UI4 = value_UI4
            this.value_UI = value_UI
            this.value_ParameterSet = value_ParameterSet
            this.value_ParameterArray = value_parameterArray_generator()
            this.value_binData = value_binData
        }
    }
}

/**
 * 문서 임의의 데이터를 나타내는 객체
 * Tag ID: HWPTAG_DOC_DATA [DOC_DATA]
 * 라벨 문서인지 여부나 인쇄 대화상자의 정보를 저장한다
 * 가변길이
 *
 * @author accforaus
 *
 * @property [parameterSet] [HWPParameterSet], 파라미터 셋
 */
class HWPCtrlData {
    var parameterSet: HWPParameterSet = HWPParameterSet()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPCtrlData] 복사된 객체 반환
     */
    fun copy() : HWPCtrlData = HWPCtrlData().also {
        it.parameterSet = this.parameterSet.copy()
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수¡
         *
         * @return [HWPParameterSet] 생성된 객체 반환
         */
        fun build(parameterSet: HWPParameterSet) : HWPCtrlData = HWPCtrlData().apply {
            this.parameterSet = parameterSet
        }
    }
}