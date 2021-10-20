package mars.leetcode.others;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadDeadLock {


    public static ReentrantLock reentrantLockA = new ReentrantLock();
    public static ReentrantLock reentrantLockB = new ReentrantLock();

    @Test
    public void test() {
        Thread thread1 = new Thread(() -> {
            reentrantLockA.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLockB.lock();

            reentrantLockB.unlock();
            reentrantLockA.unlock();
            System.out.println("1 ok.");
        });
        Thread thread2 = new Thread(() -> {
            reentrantLockB.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLockA.lock();

            reentrantLockA.unlock();
            reentrantLockB.unlock();

            System.out.println("2 ok.");
        });
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
