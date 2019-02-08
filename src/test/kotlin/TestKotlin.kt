import com.tang.hwplib.objects.HWPDocument
import com.tang.hwplib.reader.fromFile
import com.tang.hwplib.writer.toHWPFile
import org.junit.Test
import java.io.File
import java.lang.Exception
import java.util.concurrent.Executors

class test {
    @Test fun debug() {
        val path: String = "/Users/josh/ChinaProject/hwp/tex/"
        val directory: File = File("/Users/josh/ChinaProject/hwp/test/")
        val output: String = "/Users/josh/ChinaProject/hwp/test/"
        for (file in directory.listFiles()) {
            if (file.name.contains(".HWP") || file.name.contains(".hwp")) {
                val thread: Thread = Thread(Runnable {
                    try {
                        val hwp: HWPDocument = fromFile(file.absoluteFile.toString())
                        toHWPFile(hwp,"$output 2-${file.name}")
                    } catch (e: Exception) {
                        println(file.name)
                        e.printStackTrace()
                    }
                })
                thread.run()
            }
        }
    }
    @Test fun debugs() {
        val hwp: HWPDocument = HWPDocument("/Users/josh/ChinaProject/hwp/tex/2018 수학 3학년 2학기 기말 적중모의고사 1회(7.삼각비_8.원의 성질) [Q].hwp")
        hwp.write("/Users/josh/ChinaProject/hwp/tex/2018 수학 3학년 2학기 기말 적중모의고사 1회(7.삼각비_8.원의 성질) [Q]22.hwp")
    }
}