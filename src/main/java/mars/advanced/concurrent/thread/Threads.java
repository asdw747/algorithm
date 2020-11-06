package mars.advanced.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Threads {

    public static void main(String [] args) {

//        cachedThreadPool();
//        fixedThreadPool();
//        scheduledThreadPool();


    }

    private static void cachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(Threads::gogogo);
    }

    private static void fixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(() -> go(1));
        executorService.submit(() -> go(1));
        executorService.submit(() -> go(1));
        executorService.submit(() -> go(1));
        executorService.submit(() -> go(1));

        for (int i=0; i<100; i++) {
            executorService.submit(Threads::gogogo);
        }
    }

    private static void scheduledThreadPool() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
//        executorService.schedule(() -> go(1), 10, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(() -> go(1), 10, 10, TimeUnit.SECONDS);
    }

    private static void singleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(Threads::gogogo);
    }

    private static void go(int count) {
        for (int i=0; i<count; i++) {
            try {
                System.out.println(Thread.currentThread() + "sleep " + i);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void gogogo() {
        for (int i=0; i<100; i++) {
            try {
                System.out.println(Thread.currentThread() + "sleep " + i);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
