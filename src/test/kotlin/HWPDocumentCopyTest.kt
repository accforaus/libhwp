import com.tang.hwplib.objects.HWPDocument
import org.junit.Test

class HWPDocumentCopyTest {
    @Test
    fun `바르게 복사되었는지 테스트`() {
        val hwp: HWPDocument = HWPDocument("/Users/josh/ChinaProject/hwp/tex/2018 수학 2학년 1학기 중간 적중모의고사 1회(1.수와 식_2.연립방정식) [Q].hwp")
        val copyedHwp = hwp.copy()
        copyedHwp.write("/Users/josh/ChinaProject/hwp/tex/2018 수학 2학년 1학기 중간 적중모의고사 1회(1.수와 식_2.연립방정식) [Q] -2-.hwp")
    }
}