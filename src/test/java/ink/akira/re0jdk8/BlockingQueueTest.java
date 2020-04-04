package ink.akira.re0jdk8;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class BlockingQueueTest {
    @Test
    public void test(){
        LinkedBlockingQueue<List<String>> linkedBlockingQueue = new LinkedBlockingQueue<>(2);
        linkedBlockingQueue.add(Arrays.asList("a", "b", "c"));
        linkedBlockingQueue.add(Arrays.asList("d", "e", "f"));
        linkedBlockingQueue.offer(Arrays.asList("g", "h", "i"));
        List<String> strings = linkedBlockingQueue.poll();
        String join = Joiner.on(",").join(strings);
        System.out.println(join);
    }
}
