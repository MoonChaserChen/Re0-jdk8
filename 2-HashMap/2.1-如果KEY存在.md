# 如果KEY存在
对于HashMap的public V put(K key, V value)方法，如果key已存在。这样的情况下会怎样呢？key会不会覆盖之前的？
value会不会覆盖之前的？HashMap又是怎样判断key已存在的呢？

## 从`Map`说起
HashMap中的put(K key, V value)方法是来自于java.util.Map（下面简称Map）的，在Map接口中定义了以下规范：
> Associates the specified value with the specified key in this map
  (optional operation).  If the map previously contained a mapping for
  the key, the old value is replaced by the specified value.  (A map
  m is said to contain a mapping for a key k if and only
  if m.containsKey(k) would return true.
 
也就是说当key存在时，value会覆盖之前的值（但是这里并未规定key是否会覆盖之前的值）。是否存在则由Map#containsKey(Object key)方法决定，
那么，再来看Map#containsKey(Object key)方法的规范：
> Returns true if this map contains a mapping for the specified
  key.  More formally, returns true if and only if
  this map contains a mapping for a key k such that
  (key==null ? k==null : key.equals(k)).  (There can be
  at most one such mapping.)
  
简单来说，这里就是通常以这样的方法来判断的：
如果key为null，就看Map里有没有键为null的，如果key不为null，就看Map里是否有键k使得：key.equals(k)。在java.util.AbstractMap中可看到其实现：
```java
public boolean containsKey(Object key) {
    Iterator<Map.Entry<K,V>> i = entrySet().iterator();
    if (key==null) {
        while (i.hasNext()) {
            Entry<K,V> e = i.next();
            if (e.getKey()==null)
                return true;
        }
    } else {
        while (i.hasNext()) {
            Entry<K,V> e = i.next();
            if (key.equals(e.getKey()))
                return true;
        }
    }
    return false;
}
```

## 如何判断key存在/相等
java.util.Map规定了key是否存在由containsKey(Object key)方法来确定，因此key是否存在/相等的判定是交给其实现类来完成的，
各实现类的判定各有差异。

### java.util.AbstractMap
AbstractMap中定义了一个containsKey的默认实现，即是使用equals方法来进行比较。代码参见[从`Map`说起](#从Map说起)。

### java.util.HashMap
HashMap定义的containsKey(Object key)由以下代码确定：

`e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))`

即若HashMap中存在某个键k满足以下条件之一，则认为key存在：
1. key与k的hash值相等，且是同一个对象（内存地址相同）
2. key与k的hash值相等，且equals相等


## 如果KEY存在
如有下面的类：
```java
public static class Key {
    private int id;
    private String name;

    public Key(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```
equals方法均返回true，hashcode方法均返回1，因为任何两个Key对象均会被HashMap认为相等。现尝试往HashMap中进行put操作：
```java
public class HashMapTest {
    @Test
    public void testDuplicateKey(){
        Map<Key, String> map = new HashMap<>();
        Key allen = new Key(0, "Allen");
        Key alice = new Key(1, "Alice");
        map.put(allen, "allen value");
        map.put(alice, "alice value");
        map.forEach((k,v) -> System.out.println(k + ": " + v));
    }
}
```
其输出结果为：
```
Key{id=0, name='Allen'} -> alice value
```
可以看到，结果这里的key与value并未对应上。同时也可以得出结论：

**当向HashMap中put一个key已存在的键值对时，key不会覆盖以前的，但value会覆盖**


