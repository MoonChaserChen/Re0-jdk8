package ink.akira.re0jdk8;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class HashMapTest {
    @Test
    public void testDuplicateKey() {
        new ConcurrentHashMap<>();
        Map<String, String> map = new HashMap<>();
        map.put("abc", "value");
        map.put("ABC", "value");
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    @Test
    public void testKetSet() {
        Map<String, String> map = new HashMap<>();
        map.put("abc", "value");
        map.put("ABC", "value");
        map.keySet().remove("abc");
        Map<String, String> syncMap = Collections.synchronizedMap(map);
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    @Test
    public void map2sync() {
        Map<String, String> map = new HashMap<>();
        map.put("abc", "value");
        map.put("ABC", "value");
        Map<String, String> syncMap = Collections.synchronizedMap(map);
    }

    @Test
    public void testDuplicateKey1() {
        Map<Key, String> map = new HashMap<>();
        Key allen = new Key(0, "Allen");
        Key alice = new Key(1, "Alice");
        map.put(allen, "allen value");
        map.put(alice, "alice value");
        map.forEach((k, v) -> System.out.println(k + " -> " + v));
    }

    @Test
    public void testContainsKey() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "value");
        boolean b = map.containsKey(null);
        System.out.println(b);
    }

    @Test
    public void testTableSize() {
        for (int i = 0; i < 10; i++) {
            int j = 2 * i;
            System.out.println(j + ": " + tableSizeFor(j));
        }
    }

    @Test
    public void testFloatHash() {
        int[] tableSizeArr = new int[]{4, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096};
        for (int tableSize : tableSizeArr) {
            int i1 = new Float(1).hashCode() & (tableSize - 1);
            int i2 = new Float(2).hashCode() & (tableSize - 1);
            int i3 = new Float(3).hashCode() & (tableSize - 1);
            int i4 = new Float(4).hashCode() & (tableSize - 1);
            int i5 = new Float(5).hashCode() & (tableSize - 1);
            assertThat(i1).isEqualTo(i2).isEqualTo(i3).isEqualTo(i4).isEqualTo(i5);
        }
    }

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

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    @Test
    public void testConMod() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        for (String s : map.keySet()) {
            map.put("b", "b");
        }
        Iterator<String> iterator = map.keySet().iterator();
        iterator.next();
        map.put("c", "c");

        Iterator<Map.Entry<String, String>> iterator1 = map.entrySet().iterator();
        iterator1.next();
        map.put("d", "d");
        while (iterator1.hasNext()) {
            Map.Entry<String, String> next = iterator1.next();
            map.put("e", "e");
        }
    }

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
