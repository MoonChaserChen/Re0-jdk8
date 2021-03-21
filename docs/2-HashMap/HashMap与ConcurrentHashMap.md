# HashMap与ConcurrentHashMap
1. HashMap非线程安全，ConcurrentHashMap线程安全
2. HashMap的Key和Value都允许为空，ConcurrentHashMap都不允许为空
3. HashMap的Iterator可能产生ConcurrentModificationException异常
```java
public class HashMapTest {
    @Test
    public void testConMod1(){
        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        Iterator<String> iterator = map.keySet().iterator();
        map.put("b", "b");
        iterator.next(); // ConcurrentModificationException
    }
    
    @Test
    public void testConMod2(){
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("a", "a");
        Iterator<String> iterator = map.keySet().iterator();
        map.put("b", "b");
        iterator.next();
    }
}
```