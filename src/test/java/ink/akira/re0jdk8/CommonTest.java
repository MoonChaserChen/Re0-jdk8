package ink.akira.re0jdk8;

import org.junit.Test;
import sun.misc.Unsafe;

public class CommonTest {
    @Test
    public void testUnsafe(){
        int anInt1 = getInt(123L);
        System.out.println(anInt1);
        int anInt = Unsafe.getUnsafe().getInt(123L);
        System.out.println(anInt);
    }
    public native int getInt(long var1);
}
