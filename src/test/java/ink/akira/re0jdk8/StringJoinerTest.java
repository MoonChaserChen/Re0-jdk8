package ink.akira.re0jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class StringJoinerTest {
    @Test
    public void testStringJoiner(){
        StringJoiner sj = new StringJoiner("");
        sj.add("abc");
        sj.add(null);
        sj.add("def");
        System.out.println(sj.toString()); // abcnulldef
    }

    @Test
    public void testStringJoiner1(){
        StringJoiner sj = new StringJoiner("|");
        sj.add("abc");
        sj.add("def");
        System.out.println(sj.toString()); // abc|def
    }

    @Test
    public void testStringJoiner2(){
        StringJoiner sj = new StringJoiner(",", "(", ")");
        sj.add("abc");
        sj.add("def");
        System.out.println(sj.toString()); // abcdef
    }

    @Test
    public void testStringJoiner3(){
        StringJoiner sj = new StringJoiner("").setEmptyValue("initValue");
        List<String> list = new ArrayList<>();
        for (String s : list) {
            sj.add(s);
        }
        System.out.println(sj.toString()); // initValue
    }

    @Test
    public void testStringJoiner4(){
        StringJoiner sj = new StringJoiner("");
        List<String> list = new ArrayList<>();
        for (String s : list) {
            sj.add(s);
        }
        System.out.println(sj.toString() == null); // false
    }

    @Test
    public void testStringJoiner5(){
        StringJoiner sj = new StringJoiner("").setEmptyValue("initValue");
        List<String> list = new ArrayList<>();
        list.add("abc");
        for (String s : list) {
            sj.add(s);
        }
        System.out.println(sj.toString()); // abc
    }

}
