package mars.advanced.concurrent.locks;

import org.junit.Test;

public class TwinsLockTest {

    public static TwinsLock twinsLock = new TwinsLock();


    @Test
    public void test() {
        System.out.println("begin...");

        class Worker extends Thread {
            public void run() {
                try {
                    twinsLock.lock();
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread());
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    twinsLock.unlock();
                }
            }
        }


        for (int i=0; i<10; i++) {
            Worker worker = new Worker();
            worker.start();
        }

        int count = 0;
        while (count<1000) {
            try {
                count++;
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("end...");

    }



}
