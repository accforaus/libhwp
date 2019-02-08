import org.junit.Test
import java.nio.charset.Charset
import kotlin.experimental.and

class testClass {
    var list: ArrayList<Int>? = null
}

class BasicTest {
    @Test fun basic() {
        var value: Long = 0
    }

    @ExperimentalUnsignedTypes
    @Test fun`부호없는 상수 테스트`() {
        val string: String = "123456789"
        val bArray: ByteArray = string.toByteArray(Charset.forName("UTF-16LE"))
        for (index in 0 until bArray.size)
            print("${bArray[index]} ")
    }
}