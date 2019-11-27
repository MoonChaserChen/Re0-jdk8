package ink.akira.re0jdk8;

public class Outer {
    public void hello(boolean bo) {
        if (bo) {
            class Inner {}
        }
    }
}