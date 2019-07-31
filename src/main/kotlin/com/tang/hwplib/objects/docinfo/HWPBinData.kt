package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.annotation.ID
import com.tang.hwplib.annotation.IDTypes
import com.tang.hwplib.annotation.LinkID
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataProperty
import com.tang.hwplib.objects.docinfo.bindata.HWPBinDataType
import com.tang.hwplib.objects.etc.BIN_DATA
import com.tang.hwplib.util.compare.nullEquals
import com.tang.hwplib.util.exceptions.HWPBuildException

/**
 * 바이너리 데이터 (가변)
 * Tag ID: HWPTAG_BIN_DATA [BIN_DATA]
 * 그림, OLE 등의 바이너리 데이터 아이템에 대한 정보
 *
 * @author accforaus
 *
 * @property [property] [HWPBinDataProperty], 속성 (UINT16 - unsigned 2 bytes)
 * @property [absolutePathForLink] [String], Type이 "LINK"일 때, 연결 파일의 절대 경로 (WCHAR array - unsigned 4 bytes)
 * @property [relativePathForLink] [String], Type이 "LINK"일 때, 연결 파일의 상대 경로 (WCHAR array - unsigned 4 bytes)
 * @property [binDataID] [Int], Type이 "EMBEDDING"이거나 "STORAGE"일 때, BINDATASTORAGE에 저장된 바이너리 데이터 아이디 (UINT16 - unsigned 2 bytes)
 * @property [extensionForEmbedding] [String], Tyype이 EMBEDDING일 때 extension("." 제외) 그림(jpg, bmp, gif) OLE(ole) (WCHAR array - unsigned 4 bytes)
 */
@LinkID class HWPBinData : HWPDocInfoElement() {
    var property: HWPBinDataProperty = HWPBinDataProperty()
    var absolutePathForLink: String? = null
    var relativePathForLink: String? = null
    @ID(IDTypes.BinData)
    var binDataID: Int = 0
    var extensionForEmbedding: String? = null

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPBinData] 복사된 객체 반환
     */
    override fun copy(): HWPBinData = HWPBinData().also {
        it.property.value = this.property.value
        it.absolutePathForLink = this.absolutePathForLink
        it.relativePathForLink = this.relativePathForLink
        it.binDataID = this.binDataID
        it.extensionForEmbedding = this.extensionForEmbedding
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPBinData] 생성된 객체 반환
         */
        fun build(property: HWPBinDataProperty = HWPBinDataProperty.build(),
                  absolutePathForLink: String? = null,
                  relativePathForLink: String? = null,
                  binDataID: Int = 0,
                  extensionForEmbedding: String? = null) : HWPBinData = HWPBinData().apply {
            property.run {
                when(getType()) {
                    HWPBinDataType.Link -> {
                        if (absolutePathForLink == null || relativePathForLink == null)
                            throw HWPBuildException("[HWPBinData] Type: Link, absolute path ($absolutePathForLink) or relate path($relativePathForLink) must be not null")
                    }
                    else -> {
                        if (extensionForEmbedding == null)
                            throw HWPBuildException("[HWPBinData] Type: ${getType()}, extension for embedding must be not null")
                    }
                }
            }
            this.property = property
            this.absolutePathForLink = absolutePathForLink
            this.relativePathForLink = relativePathForLink
            this.binDataID = binDataID
            this.extensionForEmbedding = extensionForEmbedding
        }
    }

    override fun equals(other: Any?): Boolean = (other as HWPBinData).let { binData ->
        return property == binData.property
                && nullEquals(absolutePathForLink, binData.absolutePathForLink)
                && nullEquals(relativePathForLink, binData.relativePathForLink)
                && nullEquals(extensionForEmbedding, binData.extensionForEmbedding)
    }
}