package com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.ole

import com.tang.hwplib.util.binary.get
import com.tang.hwplib.util.binary.set

/**
 * DVASPECT
 *
 * @author accforaus
 *
 * @property [value] [Byte], DVASPECT value
 */
enum class DVASPECT(v: Byte) {
    /**
     * DVASPECT_CONTENT
     */
    CONTENT(1.toByte()),
    /**
     * DVASPECT_THUMBNAIL
     */
    THUMBNAIL(2.toByte()),
    /**
     * DVASPECT_ICON
     */
    ICON(4.toByte()),
    /**
     * DVASPECT_DOCPRINT
     */
    DOCPRINT(8.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [DVASPECT] enum 값
         */
        fun valueOf(v: Byte) : DVASPECT {
            for (dsp in values())
                if (dsp.value == v)
                    return dsp
            return CONTENT
        }
    }
}

/**
 * 개체 종류
 *
 * @author accforaus
 *
 * @property [value] [Byte], 개체 종류 값
 */
enum class HWPObjectSort(v: Byte) {
    /**
     * Unknown
     */
    Unknown(0.toByte()),
    /**
     * Embedded
     */
    Embedded(1.toByte()),
    /**
     * Link
     */
    Link(2.toByte()),
    /**
     * Static
     */
    Static(3.toByte()),
    /**
     * Equation
     */
    Equation(4.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPObjectSort] enum 값
         */
        fun valueOf(v: Byte) : HWPObjectSort {
            for (os in values())
                if (os.value == v)
                    return os
            return Unknown
        }
    }
}

/**
 * OLE 개체 속성의 속성을 나타내는 객체
 * UINT16 - unsgiend 2 bytes
 *
 * @author accforaus
 *
 * @property [value] [Long], OLE 개체 속성의 속성값
 */
class HWPShapeComponentOLEProperty {
    var value: Long = 0

    /**
     * DVASPECT 종류를 반환하는 함수
     * bit 0-7
     *
     * @return [DVASPECT] DVASPECT 종류 반환
     */
    fun getDVASPECT() : DVASPECT = DVASPECT.valueOf(get(value,0, 7).toByte())

    /**
     * DVASPECT 종류를 설정하는 함수
     * bit 0-7
     *
     * @param [dvaspect] [DVASPECT] DVASPECT 종류값
     */
    fun setDVASPECT(dvaspect: DVASPECT) {
        value = set(value, 0, 7, dvaspect.value.toInt())
    }

    /**
     * moniker assigned를 반환하는 함수
     * bit 8
     *
     * @return [Boolean] moniker assigned 반환
     */
    fun isMoniker() : Boolean = get(value, 8)

    /**
     * moniker assigned를 설정하는 함수
     * bit 8
     *
     * @param [moniker] [Boolean] moniker assigned값
     */
    fun setMoniker(moniker: Boolean) {
        value = set(value, 8, moniker)
    }

    /**
     * 베이스라인을 반환하는 함수
     * 0은 디퐅르(85%)를 뜻하고 1-101이 0-100%를 나타낸다.
     * 현재는 수식만이 베이스라인을 별도로 가진다.
     * bit 9-15
     *
     * @return [Byte] 베이스라인 반환
     */
    fun getBaseLine() : Byte = get(value, 9, 15).toByte()

    /**
     * 베이스라인을 설정하는 함수
     * 0은 디퐅르(85%)를 뜻하고 1-101이 0-100%를 나타낸다.
     * 현재는 수식만이 베이스라인을 별도로 가진다.
     * bit 9-15
     *
     * @param [baseLine] [Byte] 베이스라인값
     */
    fun setBaseLine(baseLine: Byte) {
        value = set(value, 9, 15, baseLine.toInt())
    }

    /**
     * 개체 종류를 반환하는 함수
     * bit 16-21
     *
     * @return [HWPObjectSort] 개체 종류 반환
     */
    fun getObjectSort() : HWPObjectSort = HWPObjectSort.valueOf(get(value, 16, 21).toByte())

    /**
     * 개체 종류를 설정하는 함수
     * bit 16-21
     *
     * @param [objectSort] [HWPObjectSort] 개체 종류값
     */
    fun setObjectSort(objectSort: HWPObjectSort) {
        value = set(value, 16, 21, objectSort.value.toInt())
    }
}