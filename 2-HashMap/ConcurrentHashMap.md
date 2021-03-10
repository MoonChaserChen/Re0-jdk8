# ConcurrentHashMap
线程安全的HashMap
## JDK1.7
采用分段锁Segment的机制，Segment继承ReentrantLock用来充当锁的角色，每个 Segment 对象守护每个散列映射表的若干个桶。

![segment](https://blog.jdk8.akira.ink/images/segment.png)

### 如何将K-V映射到Segment上
根据Key的hash值，做个转换 `int j = (hash >>> segmentShift) & segmentMask`
并根据得到的 `j`找到对应的Segment（如果没有，则创建）
>1. 这里的Ky的hash值算法即不是Object.hashcode()方法，也跟HashMap中ey的hash值算法不同

### Segment的创建
Segment会以this.segments[0]为原型进行创建，创建好后通过CAS操作记录到this.segments中

### ConcurrentHashMap之put
通过Key找到Segment后，进而调用Segment的put方法。由于Segment继承至ReentrantLock，因此整个put方法全用 `tryLock()` 与 `unlock()` 包裹。
> 这个锁把Segment的扩容（这个扩容叫 `rehash()`，而在HashMap里叫 `resize()`）也包裹了。

## JDK1.8
![jdk1.8_chm_put.jpeg](/images/jdk1.8_chm_put.jpeg)

> 对桶中第一个元素使用CAS操作，对后续元素使用synchronized操作（同步对象为桶头元素）
> 扩容叫 `tryPresize()`

## 为什么JDK1.8不用Segment
1. 减少内存开销:如果使用ReentrantLock则需要节点继承AQS(AbstractQueuedSynchronizer)来获得同步支持，增加内存开销，而1.8中只有头节点需要进行同步。
2. 内部优化:synchronized则是JVM直接支持的，JVM能够在运行时作出相应的优化措施：锁粗化、锁消除、锁自旋等等。
3. JDK1.8锁的粒度更小。