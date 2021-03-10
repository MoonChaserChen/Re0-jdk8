# ScheduledExecutorService
## 多个线程与单个任务
### 任务由哪个线程执行
**10个线程，1个任务循环执行，实际上这个任务由哪个线程执行是不确定的。**
```java
public class ThreadPoolTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolTest.class);
    
    private static class Task implements Runnable {
        @Override
        public void run() {
            LOGGER.info("Task.run()");
        }
    }
    
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleWithFixedDelay(new Task(), 0, 1000, TimeUnit.MILLISECONDS);
    }
}
```

```
18:06:41.829 [pool-1-thread-1] INFO ink.akira.re0jdk8.ThreadPoolTest - Task.run()
18:06:42.835 [pool-1-thread-1] INFO ink.akira.re0jdk8.ThreadPoolTest - Task.run()
18:06:43.837 [pool-1-thread-2] INFO ink.akira.re0jdk8.ThreadPoolTest - Task.run()
18:06:44.838 [pool-1-thread-1] INFO ink.akira.re0jdk8.ThreadPoolTest - Task.run()
18:06:45.840 [pool-1-thread-3] INFO ink.akira.re0jdk8.ThreadPoolTest - Task.run()
18:06:46.841 [pool-1-thread-2] INFO ink.akira.re0jdk8.ThreadPoolTest - Task.run()
18:06:47.842 [pool-1-thread-4] INFO ink.akira.re0jdk8.ThreadPoolTest - Task.run()
18:06:48.844 [pool-1-thread-1] INFO ink.akira.re0jdk8.ThreadPoolTest - Task.run()
18:06:49.845 [pool-1-thread-5] INFO ink.akira.re0jdk8.ThreadPoolTest - Task.run()
18:06:50.846 [pool-1-thread-3] INFO ink.akira.re0jdk8.ThreadPoolTest - Task.run()
```
> 后面还有打印，没有停

### 若任务失败
**若任务失败，则不会接着执行了（不会交给下个线程执行）。**
```java
public class ThreadPoolTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolTest.class);

    private static LinkedBlockingQueue<Integer> INTEGERS = new LinkedBlockingQueue<>();

    static {
        for (int i = 0; i < 100; i++) {
            INTEGERS.add(i);
        }
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            Integer integer = INTEGERS.poll();
            if (integer != null) {
                if (integer % 12 == 11) {
                    throw new RuntimeException();
                }
                LOGGER.info("integer: {}", integer);
            }
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleWithFixedDelay(new Task(), 0, 1000, TimeUnit.MILLISECONDS);
    }
}
```

```
14:51:28.272 [pool-1-thread-1] INFO ink.akira.re0jdk8.ThreadPoolTest - integer: 0
14:51:29.280 [pool-1-thread-1] INFO ink.akira.re0jdk8.ThreadPoolTest - integer: 1
14:51:30.282 [pool-1-thread-2] INFO ink.akira.re0jdk8.ThreadPoolTest - integer: 2
14:51:31.282 [pool-1-thread-1] INFO ink.akira.re0jdk8.ThreadPoolTest - integer: 3
14:51:32.284 [pool-1-thread-3] INFO ink.akira.re0jdk8.ThreadPoolTest - integer: 4
14:51:33.285 [pool-1-thread-2] INFO ink.akira.re0jdk8.ThreadPoolTest - integer: 5
14:51:34.287 [pool-1-thread-4] INFO ink.akira.re0jdk8.ThreadPoolTest - integer: 6
14:51:35.289 [pool-1-thread-1] INFO ink.akira.re0jdk8.ThreadPoolTest - integer: 7
14:51:36.290 [pool-1-thread-5] INFO ink.akira.re0jdk8.ThreadPoolTest - integer: 8
14:51:37.292 [pool-1-thread-3] INFO ink.akira.re0jdk8.ThreadPoolTest - integer: 9
14:51:38.293 [pool-1-thread-6] INFO ink.akira.re0jdk8.ThreadPoolTest - integer: 10
```
> 后面没打印了


