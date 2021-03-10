# JVM参数设置
| 参数 | 说明 | 默认值 |
| ---- | ---- | ---- |
| -XX:MaxPermSize | 永久代最大值 | 大部分情况下默认64Mb |
| -XX:MaxMetaspaceSize | 元空间最大值 | 默认无上限，即系统最大内存 |
| -XX:MetaspaceSize | 元空间初始大小 | 默认动态调整 |
| -XX:+/-UseTLAB | 是否使用TLAB | Server模式默认开启 |
| -XX:UseSerialGC |  虚拟机运行在Client模式下的默认值，打开此开关后，使用Serial+Serial Old的收集器组合进行内存回收 |
| -XX:UseParNewGC |  打开此开关后，使用ParNew+Serial Old的收集器组合进行内存回收 |
| -XX:UseConcMarkSweepGC |   打开此开关后，使用ParNew+CMS+Serial Old的收集器组合进行内存回收。Serial Old收集器将作为CMS收集器出现Concurrent Mode Failure失败后的后备收集器使用 |
| -XX:UseParallelGC |    虚拟机运行在Server模式下的默认值，打开此开关后，使用Parallel Scavenge + Serial Old（PS MarkSweep）的收集器组合进行内存回收 |
| -XX:UseParallelOldGC |     打开此开关后，使用Parallel Scavenge + Parallel Old的收集器组合进行内存回收 |
| -XX:SurvivorRatio |    新生代中Eden区域与Survivor区域的容量比值 | 默认值为8，代表Eden：Survivor=8：1 |
| -XX:PretenureSizeThreshold |   直接晋升到老年代的对象大小，设置这个参数后，大于这个参数的对象将直接在老年代分配 |
| -XX:MaxTenuringThreshold |     晋升到老年代的对象年龄，每个对象在坚持过一次Minor GC之后，年龄就增加1，当超过这个参数时就进入老年代 |
| -XX:UseAdaptiveSizePolicy |    动态调整Java堆中各个区域的大小以及进入老年代的年龄 |
| -XX:HandlePromotionFailure |   是否允许分配担保失败，即老年代的剩余空间不足以应付新生代的整个Eden和Survivor区的所有对象都存活的极端情况 |
| -XX:ParallelGCThreads |    设置并行GC时进行内存回收的线程数 |
| -XX:GCTimeRatio |  GC时间占总时间的比率，仅在使用Parallel Scavenge收集器时生效 | 默认值为99，即允许1%的GC时间 |
| -XX:MaxGCPauseMillis |     设置GC的最大停顿时间，仅在使用Parallel Scavenge收集器时生效 |
| -XX:CMSInitingOccupancyFraction |  设置CMS收集器在老年代空间被使用多少后触发垃圾收集，仅在使用CMS收集器时生效 | 默认值为68% |
| -XX:UseCMSCompactAtFullCollection |  设置CMS收集器在完成垃圾收集后是否要进行一次内存碎片整理，仅在使用CMS收集器时生效 |
| -XX:CMSFullGCsBeforeCompaction |  设置CMS收集器在进行若干次垃圾收集后再启动一次内存碎片整理。仅在使用CMS收集器时生效 |