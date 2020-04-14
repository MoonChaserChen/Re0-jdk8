# Queue

## Queue
Queue通常不允许null元素，但未明确禁止，比如 `LinkedList`。（因为当允许null元素时，若poll()方法返回null，则不能确定队列是否为空） 

|  | Throws exception | Special value |
| ---- | ---- | ---- |
| Insert |  boolean add(E e) | boolean offer(E e)  |
| Remove | E remove()  | E poll()  |
| Examine | E element() | E peek()  |

>1. Insert时，若队列空间不足，抛出异常 `IllegalStateException` 或返回 false
>2. Remove时，若队列不含元素，则抛出异常 `NoSuchElementException` 或返回 null
>3. Examine时，若队列不含元素，则抛出异常 `NoSuchElementException` 或返回 null

## Deque
public interface Deque<E> extends Queue<E> {}
```
FL = First & Last
for method in Queue:
    if method is 'element':
        getFL
    else:
        methodFL
```

>1. 在操作不能正常执行时： addFL, getFL, removeFL抛异常， offerFL, peekFL, pollFL返回特殊值
>2. add => addLast, offer => offerLast
>3. element => getFist, peek => peekFist
>4. remove => removeFist, poll => poolFirst

## BlockingQueue
public interface BlockingQueue<E> extends Queue<E> {}

0. 主要用于生产者-消费者模式
1. 在获取元素时，若队列为空则等待（wait及timed wait）；在添加元素时，若队列无多余空间，则等待（wait及timed wait）
2. 其实现都应线程安全
3. BlockingQueue 元素不能为 null，在添加null元素时会报 `NullPointerException`，因此在poll()时若返回null，则队列肯定为空。

|    | Throws exception	 | Special value | Blocks |	Times out |
| ---- | ---- | ---- | ---- | ---- |
| Insert | add(e) | offer(e) | put(e) | offer(e, time, unit) |
| Remove | remove() | poll() | take() | poll(time, unit) | 
| Examine | element() | peek() | not applicable | not applicable |

> Times out后返回特殊值

### 其它方法
| 方法 | 说明 |
| ---- | ---- |
| int remainingCapacity(); | 返回队列剩余容量，若队列不限容量，通常返回 Integer.MAX_VALUE |
| public boolean contains(Object o); | 是否包含某个元素 |
| boolean remove(Object o) | 移除掉某个元素（若有多个，只移除一个），通常通过equals()判断是否为同一个元素。通常不是很高效，只在少数情况下使用，比如存放在队列中的消息被取消了 | 
| int drainTo(Collection<? super E> c); | 将队列的元素移到集合c中，通常操作比遍历poll()效率高 |
| int drainTo(Collection<? super E> c, int maxElements); | 将队列最多maxElements个元素移到集合c中，通常操作比遍历poll()效率高 |
