package ink.akira.re0jdk8;

import org.junit.Test;

public class IntegerTest {
    @Test
    public void testParse(){
        Integer integer = new Integer(123);
        Integer integer1 = new Integer(123);
        System.out.println(integer == integer1);//false

        Integer integer2 = Integer.valueOf(123);
        Integer integer3 = Integer.valueOf(123);
        System.out.println(integer2 == integer3);//true

        Integer aa = 127;//等价于：Integer aa = Integer.valueOf(127);
        Integer bb = 127;//等价于：Integer bb = Integer.valueOf(127);
        System.out.println(aa == bb);//true
    }

    @Test
    public void testNPE(){
        Integer i = Math.random() > 0.5 ? null : 100; // May get null
        int result = i + 100;
        System.out.println(result);
    }

    @Test
    public void testValueOf(){
        Integer i1 = 127;
        Integer i2 = 127;
        Integer i3 = 200;
        Integer i4 = 200;
        System.out.println(i1 == i2); //true
        System.out.println(i3 == i4); //false

        Boolean b1 = false;
        Boolean b2 = false;
        Boolean b3 = true;
        Boolean b4 = true;
        System.out.println(b1 == b2); //true
        System.out.println(b3 == b4); //true

        Double d1 = 100.0;
        Double d2 = 100.0;
        Double d3 = 200.0;
        Double d4 = 200.0;
        System.out.println(d1 == d2); //false
        System.out.println(d3 == d4); //false
    }
}
