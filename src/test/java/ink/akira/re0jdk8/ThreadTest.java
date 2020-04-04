package ink.akira.re0jdk8;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        System.out.println(new Date());
        executorService.scheduleWithFixedDelay(new PrintTime("延迟5"), 0, 5, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(new PrintTime("延迟10"), 0, 10, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(new PrintTime("延迟15"), 0, 15, TimeUnit.SECONDS);
    }

    public static class PrintTime implements Runnable {
        private String taskName;

        public PrintTime(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println(this.taskName + ": " + new Date());
        }
    }
}
