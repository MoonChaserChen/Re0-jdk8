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

    @Test
    public void testChar(){
        System.out.println((char)-128);
        System.out.println((char)97);
        char c = '陈';
        int ci = c;
        System.out.println((int)c);
        System.out.println(ci);
        System.out.println(Character.getNumericValue(c));
    }

    @Test
    public void testDataType(){
        int number = 5; //这里的5默认为int类型
        long number1 = 5; //这里的5默认为int类型，且自动转化为long类型（容量小的数据类型可以自动转换为容量大的数据类型）
        long number2 = 21474836470L; //21474836470超出了int类型范围，这里需要使用21474836470L表示为long类型
        System.out.println((int)number2); //强制将long类型的number2转化为int类型（会产生溢出，得不到想要的结果）
        double d = 5.6; //这里的5.6默认double类型，即5.6D。
        float f = 5.6F; //这里的5.6默认double类型，无法自动转换为更小容量的类型float，这里需要使用5.6F表示为float类型
        float f1 = (float) 5.6;//强制将double类型的5.6转化为float类型
    }

    @Test
    public void testAutoChange(){
        byte a = 100; // int -> byte
        int i = a; // byte -> int
    }

    @Test
    public void testAccuracy() {
        double a = (3.3 - 2.4) / 0.1;
        System.out.println(a); // 8.999999999999998

        float f1 = 0.65f;
        float f2 = 0.6f;
        float f3 = f1 - f2; // 0.049999952
        System.out.println(f3);

        System.out.println(5.9 + 0.2); // 6.1000000000000005
        System.out.println(5.9 + 0.2 == 6.1); //false
    }
}
