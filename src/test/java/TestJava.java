import org.junit.Test;

public class TestJava {
    @Test
    public void test() {
        long l = 0x80000000;
        long l2 = 0x7fffffff;
        System.out.println(l);
        System.out.println(l2);
        System.out.println(l & l2);
    }
}
