package com.tang.hwplib.objects.bindata

import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataCompress

/**
 * 저장된 바이너리 데이터를 나타내는 객체
 *
 * @author accforaus
 *
 * @property [name] [String], 저장된 데이터 이름
 * @property [data] [ByteArray], 저장된 데이터 바이너리
 * @property [compressMethod] [HWPBinDataCompress], 저장된 데이터의 압축 종류
 */
class HWPEmbeddedBinaryData {
    var name: String = ""
    var data: ByteArray? = null
    var compressMethod: HWPBinDataCompress? = null
}

/**
 * 바이너리 데이터를 나타내는 객체
 * HWPBinData 스토리제에는 그림이나 OLE 개체와 같이 문서에 첨부된 바이너리 데이터가 각각 스트림으로 저장
 *
 * @author accforaus
 *
 * @property [embeddedBinaryDataList] [ArrayList], 저장된 바이너리 데이터 리스트
 */
class HWPBinData {
    var embeddedBinaryDataList: ArrayList<HWPEmbeddedBinaryData> = ArrayList()

    /**
     * 바이너리 데이터를 추가하고 반환하는 함수
     *
     * @return [HWPEmbeddedBinaryData] 생성된 객체 반환
     */
    fun addNewEmbeddedBinaryData() : HWPEmbeddedBinaryData = HWPEmbeddedBinaryData().also { embeddedBinaryDataList.add(it) }

    /**
     * 바이너리 데이터를 추가하는 함수
     *
     * @param [name] [String], 데이터 이름
     * @param [data] [ByteArray], 데이터 바이너리
     * @param [compressMethod] [HWPBinDataCompress], 데이터 압축 종류
     */
    fun addNewEmbeddedBinaryData(name: String, data: ByteArray, compressMethod: HWPBinDataCompress) {
        HWPEmbeddedBinaryData().apply {
            this.data = data
            this.name = name
            this.compressMethod = compressMethod
            embeddedBinaryDataList.add(this)
        }
    }
}