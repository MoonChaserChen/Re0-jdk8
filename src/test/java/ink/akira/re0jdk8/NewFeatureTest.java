package ink.akira.re0jdk8;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NewFeatureTest {
    @Test
    public void testBinary(){
        int bin = 0b1011;
        System.out.println(bin); // 11
    }

    @Test
    public void testUnderScore(){
        int bigNumber = 100_000_000;
        int bin = 0b1000_1011;
        System.out.println(bin); // 139
        System.out.println(bigNumber + bin); // 100000139
    }

    public String readFirstLineFromFile (String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }
}
