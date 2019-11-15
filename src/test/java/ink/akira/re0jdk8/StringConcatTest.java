package ink.akira.re0jdk8;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class StringConcatTest {
    @Test
    public void test(){
        System.out.println("abc" + "def"); //abcdef
    }

    @Test
    public void testForNull(){
        System.out.println("abc" + null); //abcnull
        System.out.println(null + "cde"); //nullcde
    }

    @Test
    public void testConcat(){
        String s1 = "abc";
        String s2 = "def";
        String sNull = null;
        System.out.println(s1.concat(s2)); // abcdef
        System.out.println(s1.concat(sNull)); // NullPointerException
        System.out.println(sNull.concat(s2)); // NullPointerException
    }

    @Test
    public void testAppend(){
        String sNull = null;
        StringBuilder sAbc = new StringBuilder("abc");
        sAbc.append(sNull);
        sAbc.append("def");
        System.out.println(sAbc.toString()); // abcnulldef
    }
}
