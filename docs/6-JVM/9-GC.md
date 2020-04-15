# GC
## GC收集器
默认的GC收集器，没查到准确信息。

[1. 第一种说法](https://stackoverflow.com/questions/33206313/default-garbage-collector-for-java-8)：
```
Java 7 - Parallel GC
Java 8 - Parallel GC
Java 9 - G1 GC
Java 10 - G1 GC
```

2. 第二种说法（参见[《深入理解Java虚拟机》 第2版 P90](#JVM参数)）
```
虚拟机默认在Client模式下运行，因此默认使用UseSerialGC参数，因此其GC为：Serial + Serial Old
```
>1. -client
    Selects the Java HotSpot Client VM. A 64-bit capable JDK currently ignores this option and instead uses the Java Hotspot Server VM.
>2. https://docs.oracle.com/javase/7/docs/technotes/guides/vm/server-class.html
>3. https://docs.oracle.com/javase/7/docs/technotes/tools/windows/java.html
>4. https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/collectors.html

3. 查看
```
[fifadmin@kyxl-match-01 ~]$ java -XX:+PrintCommandLineFlags -version
-XX:InitialHeapSize=260262208 -XX:MaxHeapSize=4164195328 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC 
java version "1.8.0_161"
Java(TM) SE Runtime Environment (build 1.8.0_161-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.161-b12, mixed mode)
```

### Serial收集器
发展历史最悠久的收集器。GC线程阻塞所有用户线程。新生代GC，采用复制算法。虚拟机运行在Client模式下的默认新生代收集器。
### ParNew收集器
Serial收集器的多线程版本。默认开启的收集线程数与CPU的数量相同。新生代GC，采用复制算法。
### Parallel-Scavenge收集器
新生代收集器。采用复制算法。多线程GC。目的是达到一个可控的吞吐量
> 吞吐量 = 运行用户代码时间 / (运行用户代码时间 + GC时间)
> -XX:MaxGCPauseMillis 最大GC停顿时间
> -XX:GCTimeRatio 吞吐量
> -XX+UseAdaptiveSizePolicy 自适应调节策略，自动动态调整-XX:SurvivorRatio -XX:PretenureSizeThreshold 
### Serial-Old收集器
Serial的老年代版本，采用标记-整理算法，单线程GC。CMS收集器的后备预案。
### Parallel-Old收集器
Parallel Scavenge的老年代版本，多线程GC，采用标记-整理算法。
### CMS收集器
老年代收集器。采用标记-清除算法。获取最短回收停顿。多线程GC，默认线程数(N<sub>cpu</sub> + 3) / 4
1. 初始标记
> 只标记GC ROOTS，快速
2. 并发标记
> 可与用户线程一起执行
3. 重新标记
> 修正“并发标记”，阻塞用户线程
4. 并发清除
>  可与用户线程一起执行
### G1收集器
前沿成果。可预期的停顿时间。堆分为多个Region，每个Region有回收价值，先收回价值大的Region。

## JVM参数
摘自： 《深入理解Java虚拟机》 第2版 P90

| 参数 | 描述 | 
| ---- | ---- |
| UseSerialGC |  虚拟机运行在Client模式下的默认值，打开此开关后，使用Serial+Serial Old的收集器组合进行内存回收 |
| UseParNewGC |  打开此开关后，使用ParNew+Serial Old的收集器组合进行内存回收 |
| UseConcMarkSweepGC |   打开此开关后，使用ParNew+CMS+Serial Old的收集器组合进行内存回收。Serial Old收集器将作为CMS收集器出现Concurrent Mode Failure失败后的后备收集器使用 |
| UseParallelGC |    虚拟机运行在Server模式下的默认值，打开此开关后，使用Parallel Scavenge + Serial Old（PS MarkSweep）的收集器组合进行内存回收 |
| UseParallelOldGC |     打开此开关后，使用Parallel Scavenge + Parallel Old的收集器组合进行内存回收 |
| SurvivorRatio |    新生代中Eden区域与Survivor区域的容量比值，默认值为8，代表Eden：Survivor=8：1 |
| PretenureSizeThreshold |   直接晋升到老年代的对象大小，设置这个参数后，大于这个参数的对象将直接在老年代分配 |
| MaxTenuringThreshold |     晋升到老年代的对象年龄，每个对象在坚持过一次Minor GC之后，年龄就增加1，当超过这个参数时就进入老年代 |
| UseAdaptiveSizePolicy |    动态调整Java堆中各个区域的大小以及进入老年代的年龄 |
| HandlePromotionFailure |   是否允许分配担保失败，即老年代的剩余空间不足以应付新生代的整个Eden和Survivor区的所有对象都存活的极端情况 |
| ParallelGCThreads |    设置并行GC时进行内存回收的线程数 |
| GCTimeRatio |  GC时间占总时间的比率，默认值为99，即允许1%的GC时间。仅在使用Parallel Scavenge收集器时生效 |
| MaxGCPauseMillis |     设置GC的最大停顿时间，仅在使用Parallel Scavenge收集器时生效 |
| CMSInitingOccupancyFraction |  设置CMS收集器在老年代空间被使用多少后触发垃圾收集。默认值为68%，仅在使用CMS收集器时生效 |
| UseCMSCompactAtFullCollection |  设置CMS收集器在完成垃圾收集后是否要进行一次内存碎片整理，仅在使用CMS收集器时生效 |
| CMSFullGCsBeforeCompaction |  设置CMS收集器在进行若干次垃圾收集后再启动一次内存碎片整理。仅在使用CMS收集器时生效 |
