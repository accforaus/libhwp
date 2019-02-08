package com.tang.hwplib.writer.bodytext.paragraph.control.bookmark

import com.tang.hwplib.objects.bodytext.control.bookmark.HWPCtrlData
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterItem
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterSet
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterType
import com.tang.hwplib.objects.etc.CTRL_DATA
import com.tang.hwplib.util.string.getUTF16LEStringSize
import com.tang.hwplib.writer.util.StreamWriter

/**
 * 파라미터 셋 [HWPParameterSet]을 쓰는 객체
 *
 * @author accforaus
 */
internal class ForParameterSet {
    companion object {
        /**
         * 파라미터 셋의 전체 크기를 반환하는 함수
         *
         * @return [Int] 파라미터 셋의 전체 길이 반환
         */
        fun getSize(ps: HWPParameterSet?) : Int {
            var size: Int = 0
            ps?.run {
                size += 6
                for (pi in parameterItemList) size += getSizeForParameterItem(pi)
            }
            return size
        }

        /**
         * 파라미터 아이템 으로 부터 크기를 반환하는 함수
         *
         * @param [pi] [HWPParameterItem], 파라미터 아이템 객체
         * @return [Int] 파라미터 아이템 으로 부터 크기 반환
         */
        private fun getSizeForParameterItem(pi: HWPParameterItem): Int {
            var size: Int = 0
            size += 4
            when (pi.type) {
                HWPParameterType.NULL -> {}
                HWPParameterType.String -> size += getUTF16LEStringSize(pi.value_BSTR)
                HWPParameterType.Integer1 -> size += 4
                HWPParameterType.Integer2 -> size += 4
                HWPParameterType.Integer4 -> size += 4
                HWPParameterType.Integer -> size += 4
                HWPParameterType.UnsignedInteger1 -> size += 4
                HWPParameterType.UnsignedInteger2 -> size += 4
                HWPParameterType.UnsignedInteger4 -> size += 4
                HWPParameterType.UnsignedInteger -> size += 4
                HWPParameterType.ParameterSet -> size += getSize(pi.value_ParameterSet)
                HWPParameterType.Array -> size += getSizeForParameterArray(pi)
                HWPParameterType.BINDataID -> size += 2
            }
            return size
        }

        /**
         * 파라미터 배열로 부터 크기를 반환하는 함수
         *
         * @param [pi] [HWPParameterItem], 파라미터 아이템 객체
         * @return [Int] 파라미터 배열로 부터 얻은 크기 반환
         */
        private fun getSizeForParameterArray(pi: HWPParameterItem): Int {
            var size: Int = 0
            size += 4
            val count: Short = pi.getValue_ParameterArrayCount().toShort()
            for (index in 0 until count) size += getSizeForParameterItem(pi.getValue_ParameterArray(index)!!) - 2
            return size
        }

        /**
         * 파라미터 셋을 쓰는 함수
         *
         * @param [ps] [HWPParameterSet], 파라미터 셋 객체
         * @param [sw] [StreamWriter], 스트림 쓰기 객체
         */
        fun write(ps: HWPParameterSet, sw: StreamWriter) {
            ps.run {
                sw.writeWord(id)
                val parameterCount: Short = parameterItemList.size.toShort()
                sw.writeInt16(parameterCount)
                sw.writeZero(2)
                for (pi in parameterItemList)
                    parameterItem(pi, sw)
            }
        }

        /**
         * 파라미터 아이템을 쓰는 함수
         *
         * @param [pi] [HWPParameterItem], 파라미터 아이템 객체
         * @param [sw] [StreamWriter], 스트림 쓰기 객체
         */
        private fun parameterItem(pi: HWPParameterItem, sw: StreamWriter) {
            sw.writeWord(pi.id.toInt())
            sw.writeWord(pi.type!!.value)
            parameterValue(pi, sw)
        }

        /**
         * 파라미터 아이템 종류를 쓰는 함수
         *
         * @param [pi] [HWPParameterItem], 파라미터 아이템 객체
         * @param [sw] [StreamWriter], 스트림 쓰기 객체
         */
        private fun parameterValue(pi: HWPParameterItem, sw: StreamWriter) {
            pi.run {
                when (type) {
                    HWPParameterType.NULL -> {}
                    HWPParameterType.String -> sw.writeUTF16LEString(value_BSTR)
                    HWPParameterType.Integer1 -> sw.writeInt32(value_I1.toInt())
                    HWPParameterType.Integer2 -> sw.writeInt32(value_I2.toInt())
                    HWPParameterType.Integer4 -> sw.writeInt32(value_I4)
                    HWPParameterType.Integer -> sw.writeInt32(value_I)
                    HWPParameterType.UnsignedInteger1 -> sw.writeUInt32(value_UI1.toLong())
                    HWPParameterType.UnsignedInteger2 -> sw.writeUInt32(value_UI2.toLong())
                    HWPParameterType.UnsignedInteger4 -> sw.writeUInt32(value_UI4)
                    HWPParameterType.UnsignedInteger -> sw.writeUInt32(value_UI)
                    HWPParameterType.ParameterSet -> write(value_ParameterSet!!, sw)
                    HWPParameterType.Array -> parameterArray(pi, sw)
                    HWPParameterType.BINDataID -> sw.writeUInt16(value_binData)
                }
            }
        }

        /**
         * 파라미터 배열을 쓰는 함수
         *
         * @param [pi] [HWPParameterItem], 파라미터 아이템 객체
         * @param [sw] [StreamWriter], 스트림 쓰기 객체
         */
        private fun parameterArray(pi: HWPParameterItem, sw: StreamWriter) {
            pi.run {
                val count: Short = getValue_ParameterArrayCount().toShort()
                sw.writeInt16(count)
                if (count > 0) {
                    sw.writeUInt16(getValue_ParameterArray(0)!!.id.toInt())
                    for (index in 0 until count) {
                        val elementPi: HWPParameterItem? = getValue_ParameterArray(index)
                        sw.writeUInt16(elementPi!!.type!!.value)
                        parameterValue(elementPi, sw)
                    }
                }
            }
        }
    }
}

/**
 * 컨트롤 임의의 데이터 [HWPCtrlData]를 쓰는 함수
 * Tag ID: [CTRL_DATA]
 *
 * @author accforaus
 *
 * @param [cd] [HWPCtrlData], 문서 임의의 데이터 객체
 * @param [sw] [StreamWriter], 스트림 쓰기 객체
 */
internal fun forCtrlData(cd: HWPCtrlData?, sw: StreamWriter) {
    cd?.run {
        fun getSize(): Int = ForParameterSet.getSize(cd.parameterSet)
        sw.writeRecordHeader(CTRL_DATA.toInt(), getSize())
        ForParameterSet.write(parameterSet, sw)
    }
}