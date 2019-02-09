package com.tang.hwplib.objects.bodytext.paragraph

import com.tang.hwplib.objects.bodytext.control.HWPControl
import com.tang.hwplib.objects.bodytext.control.HWPControlType
import com.tang.hwplib.objects.bodytext.control.copyControl
import com.tang.hwplib.objects.bodytext.control.createHWPControl
import com.tang.hwplib.objects.bodytext.control.ctrlheader.HWPCtrlHeaderGso
import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControl
import com.tang.hwplib.objects.bodytext.control.gso.HWPGsoControlType
import com.tang.hwplib.objects.bodytext.control.gso.createHWPGSOControl
import com.tang.hwplib.objects.bodytext.paragraph.charshape.HWPParaCharShape
import com.tang.hwplib.objects.bodytext.paragraph.header.HWPParaHeader
import com.tang.hwplib.objects.bodytext.paragraph.linesegment.HWPParaLineSeg
import com.tang.hwplib.objects.bodytext.paragraph.memo.HWPMemo
import com.tang.hwplib.objects.bodytext.paragraph.rangetag.HWPParaRangeTag
import com.tang.hwplib.objects.bodytext.paragraph.text.HWPParaText

/**
 * 문단 리스트 인터페이스
 *
 * @author accforaus
 * @see [Iterable]
 */
interface HWPParagraphListInterface: Iterable<HWPParagraph> {
    /**
     * 새로운 문단을 추가하고 반환하는 함수
     *
     * @return [HWPParagraph] 생성된 문단 객체 반환
     */
    fun addNewParagraph(): HWPParagraph

    /**
     * 문단 수를 반환하는 함수
     *
     * @return [Int] 문단 수 반환
     */
    fun getParagraphCount(): Int

    /**
     * [index] 번째의 문단을 반환하는 함수
     *
     * @param [index] [Int], 반환할 인덱스
     * @return [HWPParagraph] [index] 번째 문단 반환
     */
    fun getParagraph(index: Int): HWPParagraph
}

/**
 * 문단 리스트를 나타내는 객체
 *
 * @author accforaus
 * @see [HWPParagraphListInterface]
 *
 * @property [paragraphList] [ArrayList], 문단 리스트
 */
class HWPParagraphList: HWPParagraphListInterface {
    var paragraphList: ArrayList<HWPParagraph> = ArrayList()

    /**
     * 새로운 문단을 추가하고 반환하는 함수
     *
     * @return [HWPParagraph] 생성된 문단 객체 반환
     */
    override fun addNewParagraph(): HWPParagraph = HWPParagraph().apply { paragraphList.add(this) }

    /**
     * 문단 수를 반환하는 함수
     *
     * @return [Int] 문단 수 반환
     */
    override fun getParagraphCount(): Int = paragraphList.size

    /**
     * [index] 번째의 문단을 반환하는 함수
     *
     * @param [index] [Int], 반환할 인덱스
     * @return [HWPParagraph] [index] 번째 문단 반환
     */
    override fun getParagraph(index: Int): HWPParagraph = paragraphList[index]

    /**
     * 반복자를 반환하는 함수
     *
     * @return [Iterator] 문단 반복자 반환
     */
    override fun iterator(): Iterator<HWPParagraph> = paragraphList.iterator()

    /**
     * 문단 리스트에서 문자열을 추출하는 함수
     *
     * @return [String] 문단 리스트에서 추출된 문자열 반환
     */
    fun getNormarString(): String = StringBuilder().run {
        for (p in paragraphList)
            this.append(p.getNormalString()).append("\n")
        return this.toString()
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPParagraphList] 복사된 객체 반환
     */
    fun copy() : HWPParagraphList = HWPParagraphList().also {
        for (paragraph in this.paragraphList)
            it.paragraphList.add(paragraph.copy())
    }
}

/**
 * 문단을 나타내는 객체
 *
 * @author accforaus
 *
 * @property [header] [HWPParaHeader], 문단 헤더 객체
 * @property [text] [HWPParaText], 문단 텍스트 객체
 * @property [paraCharShape] [HWPParaCharShape], 문단 글자 모양 객체
 * @property [lineSeg] [HWPParaLineSeg], 문단 레이아웃 객체
 * @property [rangeTag] [HWPParaRangeTag], 문단 영역 태그 객체
 * @property [controlList] [ArrayList], 문단 내 컨트롤 리스트
 * @property [memoList] [ArrayList], 문단 내 메모 리스트
 */
class HWPParagraph {
    var header: HWPParaHeader = HWPParaHeader()
    var text: HWPParaText? = null
    var paraCharShape: HWPParaCharShape? = null
    var lineSeg: HWPParaLineSeg? = null
    var rangeTag: HWPParaRangeTag? = null
    var controlList: ArrayList<HWPControl>? = null
    var memoList: ArrayList<HWPMemo>? = null

    /**
     * 문단 텍스트[HWPParaText]를 생성하는 함수
     */
    fun createText() {
        text = HWPParaText()
    }

    /**
     * 문단 텍스트[HWPParaText]를 제거하는 함수
     */
    fun deleteText() {
        text = null
    }

    /**
     * 문단 글자 모양[HWPParaCharShape]를 생성하는 함수
     */
    fun createCharShape() {
        paraCharShape = HWPParaCharShape()
    }

    /**
     * 문단 글자 모양[HWPParaCharShape]를 제거하는 함수
     */
    fun deleteCharShape() {
        paraCharShape = null
    }

    /**
     * 문단 레이아웃[HWPParaLineSeg]를 생성하는 함수
     */
    fun createLineSeg() {
        lineSeg = HWPParaLineSeg()
    }

    /**
     * 문단 레이아웃[HWPParaLineSeg]를 제거하는 함수
     */
    fun deleteLineSeg() {
        lineSeg = null
    }

    /**
     * 문단 영역 태그[HWPParaRangeTag]를 생성하는 함수
     */
    fun createRangeTag() {
        rangeTag = HWPParaRangeTag()
    }

    /**
     * 문단 영역 태그[HWPParaRangeTag]를 제거하는 함수
     */
    fun deleteRangeTag() {
        rangeTag = null
    }

    /**
     * 컨트롤 객체를 생성하고 반환하는 함수
     *
     * @param [type] [HWPControlType], 컨트롤 유형
     * @return [HWPControl] 생성된 컨트롤 객체 반환
     */
    fun addNewControl(type: HWPControlType) : HWPControl? = addNewControl(type.ctrlId)

    /**
     * 컨트롤 객체를 생성하고 반환하는 함수
     *
     * @param [id] [Long], 컨트롤 ID
     * @return [HWPControl] 생성된 컨트롤 반환
     */
    fun addNewControl(id: Long) : HWPControl? = createHWPControl(id).apply {
        if (controlList == null)
            controlList = ArrayList()
        controlList!!.add(this!!)
    }

    /**
     * Gso 컨트롤 객체를 생성하고 반환하는 함수
     *
     * @param [gsoType] [HWPGsoControlType], Gso 컨트롤 타입
     * @return [HWPGsoControl] 생성된 Gso 컨트롤 객체 반환
     */
    fun addNewGsoControl(gsoType: HWPGsoControlType) : HWPGsoControl? = addNewGsoControl(gsoType.id, HWPCtrlHeaderGso())

    /**
     * Gso 컨트롤 객체를 생성하고 반환하는 함수
     *
     * @param [gsoType] [HWPGsoControlType], Gso 컨트롤 타입
     * @param [header] [HWPCtrlHeaderGso], Gso 컨트롤 헤더
     * @return [HWPGsoControl] 생성된 Gso 컨트롤 객체 반환
     */
    fun addNewGsoControl(gsoType: HWPGsoControlType, header: HWPCtrlHeaderGso) : HWPGsoControl? = addNewGsoControl(gsoType.id, header)

    /**
     * Gso 컨트롤 객체를 생성하고 반환하는 함수
     *
     * @param [gsoId] [Long], Gso 컨트롤 ID
     * @param [header] [HWPCtrlHeaderGso], Gso 컨트롤 헤더
     * @return [HWPGsoControl] 생성된 Gso 컨트롤 객체 반환
     */
    fun addNewGsoControl(gsoId: Long, header: HWPCtrlHeaderGso) : HWPGsoControl? = createHWPGSOControl(gsoId, header).apply {
        if (controlList == null)
            controlList = ArrayList()
        controlList!!.add(this!!)
    }

    /**
     * 컨트롤 객체의 인덱스를 반환하는 함수
     *
     * @param [c] [HWPControl], 알고자 하는 컨트롤 객체
     * @return [Int] 컨트롤 객체의 인덱스 반환 (존재하지 않으면 -1)
     */
    fun getControlIndexOf(c: HWPControl) : Int = controlList?.indexOf(c) ?: -1

    /**
     * 문단 텍스트에서 일반 문자를 추출하는 함수
     *
     * @return [String] 문단 텍스트에서 생성된 문자열 반환 (존재하지 않으면 "")
     */
    fun getNormalString() : String = text?.getNormalString(0) ?: ""

    /**
     * 메모 객체를 생성하고 반환하는 함수
     *
     * @return [HWPMemo] 생성된 메모 객체 반환
     */
    fun addNewMemo() : HWPMemo = HWPMemo().also {
        if (memoList == null)
            memoList = ArrayList()
        memoList!!.add(it)
    }

    /**
     * 객체를 복사한 후 반환하는 함수
     *
     * @return [HWPParagraph] 복사된 객체 반환
     */
    fun copy() : HWPParagraph = HWPParagraph().also {
        it.header = this.header.copy()
        this.text?.run { it.text = this.copy() }
        this.paraCharShape?.run { it.paraCharShape = this.copy() }
        this.lineSeg?.run { it.lineSeg = this.copy() }
        this.rangeTag?.run { it.rangeTag = this.copy() }
        this.controlList?.run {
            it.controlList = ArrayList()
            for (control in this) {
                copyControl(control)?.run {
                    it.controlList?.add(this)
                }
            }
        }
        this.memoList?.run {
            it.memoList = ArrayList()
            for (memo in this)
                it.memoList?.add(memo.copy())
        }
    }
}