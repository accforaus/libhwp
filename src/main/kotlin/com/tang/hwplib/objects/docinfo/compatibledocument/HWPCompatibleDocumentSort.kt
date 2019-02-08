package com.tang.hwplib.objects.docinfo.compatibledocument

/**
 * 대상 프로그램
 * UINT32 - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [value] [Byte], 대상 프로그램 종류 값을 가진 데이터
 */
enum class HWPCompatibleDocumentSort(v: Byte) {
    /**
     * 한글 문서 (현재 버전)
     */
    HWPCurrent(0.toByte()),
    /**
     * 한글 2007 호환 문서
     */
    HWP2007(1.toByte()),
    /**
     * MS 워드 호환 문서
     */
    MSWord(2.toByte());

    var value: Byte = v

    companion object {
        /**
         * 파일에 저장되는 정수값에 해당되는 enum 값을 반환하는 함수
         * @param [v] [Byte], 파일에 저장되는 정수값
         * @return [HWPCompatibleDocumentSort] enum 값
         */
        fun valueOf(v: Byte) : HWPCompatibleDocumentSort {
            for (cds in values())
                if (cds.value == v)
                    return cds
            return HWPCurrent
        }
    }
}