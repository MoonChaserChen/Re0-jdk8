# List
## ArrayList与LinkedList复杂度
|  | ArrayList | LinkedList |
| ---- | ---- | ---- |
| E get(int index); | O(1) | O(n) |
| boolean add(E e); | O(1) | O(1) |
| void add(int index, E element); | O(n)?，后面的元素需要移动 | O(n)，先查找到第几个元素 |
| E remove(int index); | O(n)?，后面的元素需要移动 | O(n)，先查找到第几个元素 |
| boolean remove(Object o); | O(n) | O(n) |
| boolean contains(Object o); | O(n) | O(n) |
> 对于数组元素的移动复杂度可能还是O(1)
## ArrayList数组最大长度
```
private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
```

