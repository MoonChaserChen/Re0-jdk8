# StringJoiner
StringJoiner为JDK 8新增加的工具类，用于字符串拼接。位于java.util中。

### 直接拼接
```java
public class StringJoinerTest {
    @Test
    public void testStringJoiner(){
        StringJoiner sj = new StringJoiner("");
        sj.add("abc");
        sj.add(null);
        sj.add("def");
        System.out.println(sj.toString()); // abcnulldef
    }
}
```

### 设置间隔符
```java
public class StringJoinerTest {
    @Test
    public void testStringJoiner1(){
        StringJoiner sj = new StringJoiner("|");
        sj.add("abc");
        sj.add("def");
        System.out.println(sj.toString()); // abc|def
    }
}
```

### 设置间隔符 + 前后缀
```java
public class StringJoinerTest {
    @Test
    public void testStringJoiner2(){
        StringJoiner sj = new StringJoiner(",", "(", ")");
        sj.add("abc");
        sj.add("def");
        System.out.println(sj.toString()); // (abc,def)
    }
}
```
这里的处理有很点类似mybatis动态sql中的`<foreach>`

### 设置默认值
```java
public class StringJoinerTest {
    @Test
    public void testStringJoiner3(){
        StringJoiner sj = new StringJoiner("").setEmptyValue("initValue");
        // 空的list
        List<String> list = new ArrayList<>();
        for (String s : list) {
            sj.add(s);
        }
        System.out.println(sj.toString()); // initValue
    }
}
```
适合于从列表中获取元素进行拼接，但当获取到的列表没有元素时，给予默认值。