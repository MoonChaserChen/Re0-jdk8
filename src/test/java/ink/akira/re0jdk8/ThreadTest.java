package ink.akira.re0jdk8;

public class ThreadTest {
    public static void main(String[] args) {
        new MyThread().start();
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread.run");
        }
    }
}
