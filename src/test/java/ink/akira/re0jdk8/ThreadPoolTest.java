package ink.akira.re0jdk8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

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
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 20; i++) {
            ses.submit(new Task());
        }
        ses.schedule(new Task(), 1000L, TimeUnit.MILLISECONDS);
        ses.shutdown();
    }
}