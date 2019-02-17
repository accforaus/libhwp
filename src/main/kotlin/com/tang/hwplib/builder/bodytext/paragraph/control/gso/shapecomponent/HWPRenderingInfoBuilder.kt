package com.tang.hwplib.builder.bodytext.paragraph.control.gso.shapecomponent

import com.tang.hwplib.builder.interfaces.HWPBuilder
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.render.HWPMatrix
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.render.HWPRenderingInfo
import com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.render.HWPScaleRotateMatrixPair

class HWPRenderingInfoBuilder : HWPBuilder<HWPRenderingInfo> {
    private val renderingInfo : HWPRenderingInfo = HWPRenderingInfo.build()

    fun setTranslationMatrix(matrixBuilder: HWPMatrixBuilder) : HWPRenderingInfoBuilder = this.apply {
        renderingInfo.translationMatrix = matrixBuilder.build()
    }

    fun setScaleRotateMatrixPairList(scaleRotateMatrixPairListBuilder: HWPScaleRotateMatrixPairListBuilder) : HWPRenderingInfoBuilder = this.apply {
        renderingInfo.scaleRotateMatrixPairList = scaleRotateMatrixPairListBuilder.build()
    }

    override fun build(): HWPRenderingInfo = renderingInfo
}

class HWPScaleRotateMatrixPairListBuilder : HWPBuilder<ArrayList<HWPScaleRotateMatrixPair>> {
    private val scaleRotateMatrixList : ArrayList<HWPScaleRotateMatrixPair> = ArrayList()

    fun addScaleRotateMatrix(scaleRotateMatrixPairBuilder: HWPScaleRotateMatrixPairBuilder) : HWPScaleRotateMatrixPairListBuilder = this.apply {
        scaleRotateMatrixList.add(scaleRotateMatrixPairBuilder.build())
    }

    override fun build(): ArrayList<HWPScaleRotateMatrixPair> = scaleRotateMatrixList
}

class HWPScaleRotateMatrixPairBuilder : HWPBuilder<HWPScaleRotateMatrixPair> {
    private val scaleRotateMatrix: HWPScaleRotateMatrixPair = HWPScaleRotateMatrixPair.build()

    fun setScaleMatrix(matrixBuilder: HWPMatrixBuilder) : HWPScaleRotateMatrixPairBuilder = this.apply {
        scaleRotateMatrix.scaleMatrix = matrixBuilder.build()
    }

    fun setRotateMatrix(matrixBuilder: HWPMatrixBuilder) : HWPScaleRotateMatrixPairBuilder = this.apply {
        scaleRotateMatrix.rotateMatrix = matrixBuilder.build()
    }

    override fun build(): HWPScaleRotateMatrixPair = scaleRotateMatrix
}

class HWPMatrixBuilder : HWPBuilder<HWPMatrix> {
    private val matrix : HWPMatrix = HWPMatrix.build()

    fun setvalue(index: Int, value: Double) : HWPMatrixBuilder = this.apply {
        matrix.setValue(index, value)
    }

    override fun build(): HWPMatrix = matrix
}