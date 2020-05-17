package ink.akira.re0jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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