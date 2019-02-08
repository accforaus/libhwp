package com.tang.hwplib.reader.bodytext.paragraph.control.bookmark

import com.tang.hwplib.objects.bodytext.control.bookmark.HWPCtrlData
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterItem
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterSet
import com.tang.hwplib.objects.bodytext.control.bookmark.HWPParameterType
import com.tang.hwplib.reader.util.StreamReader
import com.tang.hwplib.objects.etc.*

/**
 * 파라미터 셋 [HWPParameterSet]을 읽는 객체
 *
 * @author accforaus
 */
internal class ForParameterSet {
    companion object {
        /**
         * 파라미터 셋 [HWPParameterSet]을 읽는 함수
         *
         * @param [ps] [HWPParameterSet], 빈 파라미터 셋
         * @param [sr] [StreamReader], 스트림 리더 객체
         */
        fun read(ps: HWPParameterSet, sr: StreamReader) {
            ps.id = sr.readWord()
            val parameterCount: Short = sr.readInt16()
            sr.skip(2)
            for (index in 0 until parameterCount) {
                ps.addNewParameterItem().run { parameterItem(this, sr) }
            }
        }

        /**
         * 파라미터 셋 [HWPParameterSet]을 추가하는 함수
         *
         * @param [pi] [HWPParameterItem], 파라미터 아이템 객체
         * @param [sr] [StreamReader], 스트림 리더 객체
         */
        private fun parameterSet(pi: HWPParameterItem, sr: StreamReader) {
            pi.createValue_ParameterSet()
            read(pi.value_ParameterSet!!, sr)
        }

        /**
         * 파라미터 배열을 읽는 객체
         *
         * @param [pi] [HWPParameterItem], 파라미터 배열 객체
         * @param [sr] [StreamReader], 스트림 리더 객체
         */
        private fun parameterArray(pi: HWPParameterItem, sr: StreamReader) {
            val count: Short = sr.readInt16()
            if (count > 0) {
                pi.createValue_ParameterArray(count.toInt())
                val id: Int = sr.readWord()
                for (index in 0 until count)
                    parameterItemForArray(pi.getValue_ParameterArray(index)!!, id, sr)
            }
        }

        /**
         * 파라미터 아이템 [HWPParameterItem] 종류별로 설정하는 하뭇
         *
         * @param [pi] [HWPParameterItem], 파라미터 아이템
         * @param [sr] [StreamReader], 스트림 리더 객체
         */
        private fun parameterValue(pi: HWPParameterItem, sr: StreamReader) {
            when(pi.type) {
                HWPParameterType.NULL -> {}
                HWPParameterType.String -> pi.value_BSTR = sr.readUTF16LEString()
                HWPParameterType.Integer1 -> pi.value_I1 = sr.readInt32().toByte()
                HWPParameterType.Integer2 -> pi.value_I2 = sr.readInt32().toShort()
                HWPParameterType.Integer4 -> pi.value_I4 = sr.readInt32()
                HWPParameterType.Integer -> pi.value_I = sr.readInt32()
                HWPParameterType.UnsignedInteger1 -> pi.value_UI1 = sr.readUInt32().toShort()
                HWPParameterType.UnsignedInteger2 -> pi.value_UI2 = sr.readUInt32().toInt()
                HWPParameterType.UnsignedInteger4 -> pi.value_UI4 = sr.readUInt32()
                HWPParameterType.UnsignedInteger -> pi.value_UI = sr.readUInt32()
                HWPParameterType.ParameterSet -> parameterSet(pi, sr)
                HWPParameterType.Array -> parameterArray(pi, sr)
                HWPParameterType.BINDataID -> pi.value_binData = sr.readUInt16()
            }
        }

        /**
         * 파라미터 아이템 [HWPParameterItem]을 읽는 함수
         *
         * @param [pi] [HWPParameterItem], 빈 파라미터 아이템 객체
         * @param [sr] [StreamReader], 스트림 리더 객체
         */
        private fun parameterItem(pi: HWPParameterItem, sr: StreamReader) {
            pi.id = sr.readWord().toLong()
            pi.type = HWPParameterType.valueOf(sr.readWord())
            parameterValue(pi, sr)
        }

        /**
         * 파라미터 아이템 배열을 읽는 함수
         *
         * @param [pi] [HWPParameterItem], 파라미터 아이템 객체
         * @param [sr] [StreamReader], 스트림 리더 객체
         */
        private fun parameterItemForArray(pi: HWPParameterItem, id: Int, sr: StreamReader) {
            pi.id = id.toLong()
            pi.type = HWPParameterType.valueOf(sr.readWord())
            parameterValue(pi, sr)
        }
    }
}

/**
 * 컨트롤 임의의 데이터 [HWPCtrlData]를 읽는 함수
 * Tag ID: [CTRL_DATA]
 *
 * @author accforaus
 *
 * @param [cd] [HWPCtrlData], 빈 컨트롤 임의의 데이터
 * @param [sr] [StreamReader], 스트림 리더 객체
 */
internal fun forCtrlData(cd: HWPCtrlData, sr: StreamReader) {
    ForParameterSet.read(cd.parameterSet, sr)
}