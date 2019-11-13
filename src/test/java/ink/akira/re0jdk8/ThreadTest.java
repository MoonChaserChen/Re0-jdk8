package ink.akira.re0jdk8;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {
    public static AtomicInteger ai = new AtomicInteger(0);
    public static final int LIMIT_COUNT = 30;

    public static class PrintTask implements Runnable{
        private String printString;
        private Semaphore current;
        private Semaphore next;

        public PrintTask(String printString, Semaphore current, Semaphore next) {
            this.printString = printString;
            this.current = current;
            this.next = next;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    current.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ai.get() + printString);
                if (current.availablePermits() == 0) {
                    next.release(2);
                }
                if (ai.getAndIncrement() >= LIMIT_COUNT) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Semaphore s0 = new Semaphore(2);
        Semaphore s1 = new Semaphore(0);
        Semaphore s2 = new Semaphore(0);
        PrintTask a = new PrintTask("A", s0, s1);
        PrintTask b = new PrintTask("B", s1, s2);
        PrintTask c = new PrintTask("C", s2, s0);
        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();
    }
}
