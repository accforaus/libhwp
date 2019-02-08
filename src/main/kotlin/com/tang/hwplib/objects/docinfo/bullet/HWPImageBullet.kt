package com.tang.hwplib.objects.docinfo.bullet


/**
 * 이미지 글머리를 나타내는 객체
 * BYTE stream - unsigned 4 bytes
 *
 * @author accforaus
 *
 * @property [contrast] [Short], 대비 (BYTE - unsigned 1 byte)
 * @property [brightness] [Short], 밝기 (BYTE - unsigned 1 byte)
 * @property [effects] [Short], 효과 (BYTE - unsigned 1 byte)
 * @property [id] [Short], ID (BYTE - unsigned 1 byte)
 */
class HWPImageBullet {
    var contrast: Short = 0
    var brightness: Short = 0
    var effects: Short = 0
    var id: Short = 0
}