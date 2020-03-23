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
### Serial收集器
### ParNew收集器
### Parallel-Scavenge收集器
### Serial-Old收集器
### Parallel-Old收集器
### CMS收集器
### G1收集器

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
