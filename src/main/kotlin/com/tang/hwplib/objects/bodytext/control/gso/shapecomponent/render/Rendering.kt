package com.tang.hwplib.objects.bodytext.control.gso.shapecomponent.render

/**
 * Matrix를 나타내는 객체
 * 각 matrix는 원소가 double로 표현되는 3x3 matrix로 구현된다.
 * 마지막 줄(row)는 항상 0, 0, 1이기 때문에 실제 serialization에서는 마지막 줄은 빠진다
 * 48 bytes
 *
 * @author accforaus
 *
 * @property [values] [DoubleArray], 3 x 2 matrix의 원소 (double 48 bytes)
 */
class HWPMatrix {
    var values: DoubleArray = DoubleArray(6)

    /**
     * 인덱스 위치의 값을 가져오는 함수
     *
     * @param [index] [Int], 인덱스 값
     * @return [Double] [index]에 위치한 값 반환
     */
    fun getValue(index: Int): Double = values[index]

    /**
     * 인덱스 위치에 값을 설정하는 함수
     *
     * @param [index] [Int], 인덱스 값
     * @param [value] [Double], 저장할 값
     */
    fun setValue(index: Int, value: Double) {
        values[index] = value
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPMatrix] 복사된 객체 반환
     */
    fun copy() : HWPMatrix = HWPMatrix().also {
        it.values = this.values.copyOf()
    }
}

/**
 * Scale/Rotation Matrix의 쌍을 나타내는 객체
 * 48 * 2 bytes
 * @author accforaus
 *
 * @property [scaleMatrix] [HWPMatrix], scale matrix (double 48 bytes)
 * @property [rotateMatrix] [HWPMatrix], rotate matrix (double 48 bytes)
 */
class HWPScaleRotateMatrixPair {
    var scaleMatrix: HWPMatrix = HWPMatrix()
    var rotateMatrix: HWPMatrix = HWPMatrix()

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPScaleRotateMatrixPair] 복사된 객체 반환
     */
    fun copy() : HWPScaleRotateMatrixPair = HWPScaleRotateMatrixPair().also {
        it.scaleMatrix = this.scaleMatrix.copy()
        it.rotateMatrix = this.rotateMatrix.copy()
    }
}

/**
 * Rendering 정보를 나타내는 객체
 * 가변 길이
 *
 * @author accforaus
 *
 * @property [translationMatrix] [HWPMatrix], translation matrix (BYTE stream - unsigned 48 bytes)
 * @property [scaleRotateMatrixPairList] [ArrayList], scale/rotation matrix sequence (BYTE stream - unsigned cnt * 48 * 2 bytes)
 */
class HWPRenderingInfo {
    var translationMatrix: HWPMatrix = HWPMatrix()
    var scaleRotateMatrixPairList: ArrayList<HWPScaleRotateMatrixPair> = ArrayList()

    /**
     * scale/rotation matrix 쌍을 생성하고 반환하는 함수
     *
     * @return [HWPScaleRotateMatrixPair] 생성된 객체 반환
     */
    fun addNewScaleRotateMatrixPair(): HWPScaleRotateMatrixPair = HWPScaleRotateMatrixPair().apply { scaleRotateMatrixPairList.add(this) }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPRenderingInfo] 복사된 객체 반환
     */
    fun copy() : HWPRenderingInfo = HWPRenderingInfo().also {
        it.translationMatrix = this.translationMatrix.copy()
        for (scaleRotateMatrix in this.scaleRotateMatrixPairList) it.scaleRotateMatrixPairList.add(scaleRotateMatrix.copy())
    }
}