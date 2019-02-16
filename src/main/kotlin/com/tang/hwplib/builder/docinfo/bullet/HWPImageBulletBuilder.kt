package com.tang.hwplib.builder.docinfo.bullet

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.docinfo.bullet.HWPImageBullet

class HWPImageBulletBuilder : HWPBuilder<HWPImageBullet> {
    private val imageBullet: HWPImageBullet = HWPImageBullet.build()

    fun setContrast(contrast: Short) : HWPImageBulletBuilder = this.apply {
        imageBullet.contrast = contrast
    }

    fun setBrightness(brightness: Short) : HWPImageBulletBuilder = this.apply {
        imageBullet.brightness = brightness
    }

    fun setEffects(effects: Short) : HWPImageBulletBuilder = this.apply {
        imageBullet.effects = effects
    }

    fun setID(id: Short) : HWPImageBulletBuilder = this.apply {
        imageBullet.id = id
    }

    override fun build(): HWPImageBullet = imageBullet
}