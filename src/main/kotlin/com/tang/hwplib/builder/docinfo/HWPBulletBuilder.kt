package com.tang.hwplib.builder.docinfo

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.builder.docinfo.bullet.HWPImageBulletBuilder
import com.tang.hwplib.builder.docinfo.numbering.HWPParagraphHeadInfoBuilder
import com.tang.hwplib.copyto.docinfo.IDMappingTypes
import com.tang.hwplib.objects.docinfo.HWPBullet
import com.tang.hwplib.objects.docinfo.HWPDocInfo

class HWPBulletBuilder(private val docInfo : HWPDocInfo) : HWPDocInfoBuilder() {
    private val bullet: HWPBullet = HWPBullet.build()

    fun setParagraphHeadInfo(paragraphHeadInfoBuilder: HWPParagraphHeadInfoBuilder) : HWPBulletBuilder = this.apply {
        bullet.paragraphHeadInfo = paragraphHeadInfoBuilder.build()
    }

    fun setBulletChar(bulletChar: String) : HWPBulletBuilder = this.apply {
        bullet.bulletChar = bulletChar
    }

    fun setImageBulletCheck(imageBulletCheck: Int) : HWPBulletBuilder = this.apply {
        bullet.imageBulletCheck = imageBulletCheck
    }

    fun setImageBullet(imageBulletBuilder: HWPImageBulletBuilder) : HWPBulletBuilder = this.apply {
        bullet.imageBullet = imageBulletBuilder.build()
    }

    fun setCheckBulletChar(checkBulletChar: String) : HWPBulletBuilder = this.apply {
        bullet.checkBulletChar = checkBulletChar
    }

    fun proceed() : Int = build().run {
        docInfo.bulletList.size
    }

    override fun build(): HWPBullet = bullet.apply {
        docInfo.bulletList.add(this)
        docInfo.updateIDMappings(IDMappingTypes.BULLET)
    }
}

class HWPBulletListBuilder : HWPBuilder<ArrayList<HWPBullet>> {
    private val bulletList: ArrayList<HWPBullet> = ArrayList()

    fun addBullet(bulletBuilder: HWPBulletBuilder) : HWPBulletListBuilder = this.apply {
        bulletList.add(bulletBuilder.build())
    }

    override fun build(): ArrayList<HWPBullet> = bulletList
}