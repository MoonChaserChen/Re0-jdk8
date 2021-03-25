# GC
## GC收集器
默认的GC收集器，没查到准确信息。
1. [第一种说法](https://stackoverflow.com/questions/33206313/default-garbage-collector-for-java-8)：
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