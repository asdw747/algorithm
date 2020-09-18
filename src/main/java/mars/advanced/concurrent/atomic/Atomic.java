package mars.advanced.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {

    public static void main(String [] args) {

        System.out.println("ok");

        AtomicInteger i = new AtomicInteger(1);
        i.set(99);
        i.compareAndSet(100, 101);

        System.out.println(i);

    }

}
