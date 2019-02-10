package com.tang.hwplib.objects.fileheader

import kotlin.experimental.and

/**
 * HWP 문서의 파일 버전을 나타내는 객체
 * 0xMMnnPPrr의 형태 (예 5.0.3.0)
 *
 * @author accforaus
 *
 * @property [mm] [Short], 문서 형식의 구조가 완전히 바뀌는 것을 나타냄. 숫자가 다르면 구 버전과 호환 불가
 * @property [nn] [Short], 큰 구조는 동일하나, 큰 변화가 있는 것을 나타냄. 숫자가 다르면 구 버전과 호환 불가
 * @property [pp] [Short], 구조는 동일, Record가 추가되었거나, 하위 버전에서 호환되지 않는 정보가 추가된 것을 나타냄. 숫자가 달라도 구버전과 호환 가능.
 * @property [rr] [Short], Record에 정보들이 추가된 것을 나타냄. 숫자가 달라도 구 버전과 호환 가능.
 */

class HWPFileVersion {
    var mm: Short = 0
    var nn: Short = 0
    var pp: Short = 0
    var rr: Short = 0

    /**
     * HWP 문서의 파일 버전을 설정하는 함수
     *
     * @param [version] [Long], 버전 DWORD(unsigned 4 bytes)
     * @return [Unit]
     */
    fun setVersion(version: Long) {
        mm = ((version and 0xff000000) shr 24).toShort()
        nn = ((version and 0xff0000) shr 16).toShort()
        pp = ((version and 0xff00) shr 8).toShort()
        rr = (version and 0xff).toShort()
    }

    /**
     * HWP 문서의 파일 버전을 반환하는 함수
     *
     * @return [Long], 0xMMnnPPrr의 형태
     */
    fun getVersion(): Long {
        var version: Long = 0
        version += (mm and 0xff).toInt() shl 24
        version += (nn and 0xff).toInt() shl 16
        version += (pp and 0xff).toInt() shl 8
        version += (rr and 0xff)
        return version
    }

    /**
     * 현재 버전이 비교 버전 [mm2], [nn2], [pp2], [rr2]보다 상위 버전인지 여부를 반환하는 함수
     *
     * @param [mm2] [Int], MM
     * @param [nn2] [Int], nn
     * @param [pp2] [Int], PP
     * @param [rr2] [Int], rr
     *
     * @return [Boolean], 현재 버전이 비교 버전보다 상위 버전인지 여부
     */
    fun isOver(mm2: Int, nn2: Int, pp2: Int, rr2: Int) : Boolean = (mm > mm2) ||
            (mm.toInt() == mm2 && nn > nn2) ||
            (mm.toInt() == mm2 && nn.toInt() == nn2 && pp > pp2) ||
            (mm.toInt() == mm2 && nn.toInt() == nn2 && pp.toInt() == pp2 && rr > rr2) ||
            (mm.toInt() == mm2 && nn.toInt() == nn2 && pp.toInt() == pp2 && rr.toInt() == rr2)


    /**
     * 0xMMnnPPrr값의 형태를 [String]으로 변환하는 함수
     *
     * @return [String], (5.0.3.0)과 같이 변환된 값
     */
    override fun toString(): String = StringBuilder().let {
        it.append(mm).append(".")
                .append(nn)
                .append(".")
                .append(pp)
                .append(".")
                .append(rr)
                .append(".")
                .toString()
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPFileVersion] 복사된 객체 반환
     */
    fun copy() : HWPFileVersion = HWPFileVersion().also {
        it.mm = this.mm
        it.nn = this.nn
        it.pp = this.pp
        it.rr = this.rr
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPFileVersion] 생성된 객체 반환
         */
        fun build(mm: Short = 5, nn: Short = 0, pp :Short = 5, rr :Short = 0): HWPFileVersion = HWPFileVersion().apply {
            this.mm = mm
            this.nn = nn
            this.pp = pp
            this.rr = rr
        }
    }
}