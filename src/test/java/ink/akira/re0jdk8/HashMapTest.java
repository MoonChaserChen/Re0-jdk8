package ink.akira.re0jdk8;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    @Test
    public void testDuplicateKey(){
        Map<String, String> map = new HashMap<>();
        map.put("abc", "value");
        map.put("ABC", "value");
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    @Test
    public void testDuplicateKey1(){
        Map<Key, String> map = new HashMap<>();
        Key allen = new Key(0, "Allen");
        Key alice = new Key(1, "Alice");
        map.put(allen, "allen value");
        map.put(alice, "alice value");
        map.forEach((k,v) -> System.out.println(k + " -> " + v));
    }

    @Test
    public void testContainsKey(){
        Map<String, String> map = new HashMap<>();
        map.put(null, "value");
        boolean b = map.containsKey(null);
        System.out.println(b);
    }

    @Test
    public void testTableSize(){
        for (int i = 0; i < 10; i++) {
            int j = 2 * i;
            System.out.println(j + ": " + tableSizeFor(j));
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
}
