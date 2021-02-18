# synchronized与Lock比较
1. 类型及使用不同
    > Lock是一个接口，而synchronized是Java中的关键字。synchronized是隐式使用，可对代码块、方法使用；Lock为显式使用，只对代码块使用
2. 对异常的处理
    > synchronized在其代码块发生异常时，会自动释放锁；Lock则不然，因此一般在finally释放Lock锁
3. Lock提供更多的特性
    1. lockInterruptibly
    2. tryLock
    3. ReentrantLock提供fair lock
    0. 读写锁（ReadWriteLock其实是另一个顶级接口）
4. 实现原理
    1. synchronized使用JVM来实现
    2. Lock代码层面实现（CAS操作）
5. JDK更钟爱synchronized
    ```
    JDK1.5中，synchronized是性能低效的。因为这是一个重量级操作，它对性能最大的影响是阻塞的是实现，挂起线程和恢复线程的操作都需要
    转入内核态中完成，这些操作给系统的并发性带来了很大的压力。相比之下使用Java提供的Lock对象，性能更高一些。多线程环境下，
    synchronized的吞吐量下降的非常严重，而ReentrankLock则能基本保持在同一个比较稳定的水平上。
    
    到了JDK1.6，发生了变化，对synchronize加入了很多优化措施，有自适应自旋，锁消除，锁粗化，轻量级锁，偏向锁等等。
    导致在JDK1.6上synchronize的性能并不比Lock差。官方也表示，他们也更支持synchronize，在未来的版本中还有优化余地，
    所以还是提倡在synchronized能实现需求的情况下，优先考虑使用synchronized来进行同步。
    ```
