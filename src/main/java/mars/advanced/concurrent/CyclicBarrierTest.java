package mars.advanced.concurrent;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void main(String [] args) {
        final CyclicBarrier cyclicBarrier0 = new CyclicBarrier(3);
        final CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
        final CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

        new Thread(()->{
            try {
                for (int i=0; i<5; i++) {
                    print();
                    cyclicBarrier1.await();

                    cyclicBarrier0.await();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i=0; i<5; i++) {
                    cyclicBarrier1.await();
                    print();
                    cyclicBarrier2.await();

                    cyclicBarrier0.await();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(()-> {
            try {
                for (int i=0; i<5; i++) {
                    cyclicBarrier2.await();
                    print();

                    cyclicBarrier0.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "C").start();


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void print() {
        Thread t = Thread.currentThread();
        String name = t.getName();
        System.out.println(name);
    }

}
