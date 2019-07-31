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

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPImageBullet] 복사된 객체 반환
     */
    fun copy() : HWPImageBullet = HWPImageBullet().also {
        it.contrast = this.contrast
        it.brightness = this.brightness
        it.effects = this.effects
        it.id = this.id
    }

    companion object {
        /**
         * 객체를 생성하고 반환하는 함수
         *
         * @return [HWPImageBullet] 생성된 객체 반환
         */
        fun build(contrast: Short = 0, brightness: Short = 0,
                  effects: Short = 0, id: Short = 0)
                : HWPImageBullet = HWPImageBullet().apply {
            this.contrast = contrast
            this.brightness = brightness
            this.effects = effects
            this.id = id
        }
    }

    override fun equals(other: Any?): Boolean = (other as HWPImageBullet).let { bullet ->
        return contrast == bullet.contrast
                && brightness == bullet.brightness
                && effects == bullet.effects
    }
}