package ink.akira.re0jdk8;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JoinerTest {
    @Test
    public void testJoiner(){
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add(null); // 默认不允许null值
        list.add("def");
        System.out.println(Joiner.on("").skipNulls().join(list)); // 跳过null值
        System.out.println(Joiner.on("").useForNull("|").join(list)); // null转化为|
        System.out.println(Joiner.on("").join(list)); // 不允许null值 NullPointerException
    }
}
