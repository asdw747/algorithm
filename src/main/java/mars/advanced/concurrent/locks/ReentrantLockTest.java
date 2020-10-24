package mars.advanced.concurrent.locks;

import org.junit.Test;

import java.nio.FloatBuffer;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    @Test
    public void test() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();

        for (int i=0; i<10000000; i++) {
            if (i == 100) {
                Thread.currentThread().interrupt();
            }

            if (Thread.interrupted()) {
                System.out.println("end");
                break;
            }
            System.out.println(i);
        }

        System.out.println("ok");
    }

}
