package ink.akira.re0jdk8;

import java.io.FileNotFoundException;
import java.io.IOException;

public class EmptyClass {
    public static final String ABC;

    static {
        ABC = "A";
    }

    public void abc(){
        try {
            throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            throw new IOException();
        } finally {
            return;
        }
    }
}
