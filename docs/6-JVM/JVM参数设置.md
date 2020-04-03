# JVM参数设置
| 参数 | 说明 | 默认值 |
| ---- | ---- | ---- |
| -XX:MaxPermSize | 永久代最大值 | 大部分情况下默认64Mb |
| -XX:MaxMetaspaceSize | 元空间最大值 | 默认无上限，即系统最大内存 |
| -XX:MetaspaceSize | 元空间初始大小 | 默认动态调整 |
| -XX:+/-UseTLAB | 是否使用TLAB | Server模式默认开启 |