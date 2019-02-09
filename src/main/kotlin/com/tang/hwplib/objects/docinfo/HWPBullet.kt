package com.tang.hwplib.objects.docinfo

import com.tang.hwplib.objects.docinfo.bullet.HWPImageBullet
import com.tang.hwplib.objects.docinfo.numbering.HWPParagraphHeadInfo
import com.tang.hwplib.objects.etc.BULLET

/**
 * 글머리표를 나타내는 객체
 * Tag ID: HWPTAG_BULLET [BULLET]
 * 20 bytes
 *
 * @author accforaus
 *
 * @property [paragraphHeadInfo] [HWPParagraphHeadInfo], 문단 머리의 정보 (BYTE stream - unsigned 8 bytes)
 * @property [bulletChar] [String], 글머리표 문자 (WCHAR - unsigned 2 bytes)
 * @property [imageBulletCheck] [Int], 이미지 글머리표 여부 (글머리표: 0, 이미지 글머리표: ID) (INT32 - signed 4 bytes)
 * @property [imageBullet] [HWPImageBullet], 이미지 글머리 (대비, 밝기, 효과, ID) (BYTE stream - unsigned 4 bytes)
 * @property [checkBulletChar] [String], 체크 글머리표 문자 (WCHAR - unsigned 2 bytes)
 */
class HWPBullet {
    var paragraphHeadInfo: HWPParagraphHeadInfo = HWPParagraphHeadInfo()
    var bulletChar: String = ""
    var imageBulletCheck: Int = 0
    var imageBullet: HWPImageBullet = HWPImageBullet()
    var checkBulletChar: String = ""

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPBullet] 복사된 객체 반환
     */
    fun copy() : HWPBullet = HWPBullet().also {
        it.paragraphHeadInfo = this.paragraphHeadInfo.copy()
        it.bulletChar = this.bulletChar
        it.imageBulletCheck = this.imageBulletCheck
        it.imageBullet = this.imageBullet.copy()
        it.checkBulletChar = this.checkBulletChar
    }
}