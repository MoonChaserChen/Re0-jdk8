package ink.akira.re0jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StringTest {
    @Test
    public void test(){
        String a = "abc";
        String b = "def";
        String c = "ghi";
        String d = a + b + c;
        System.out.println(d);
    }

    @Test
    public void test1(){
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("def");
        list.add("ghi");
        String result = "";
        for (String s : list) {
            result += s;
        }
        System.out.println(result);
    }
}
