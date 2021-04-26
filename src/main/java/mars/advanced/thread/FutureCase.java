package mars.advanced.thread;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FutureCase {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Test
    public void test() throws Exception {

        Future<?> future = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName());

            Thread.sleep(3000);
            return "ok";

        });

        System.out.println(future.get());
    }

    private static final List<String> INPUT_LIST = Arrays.asList("a", "b", "c");

    @Test
    public void testCompletableFuture() throws Exception {
        long start1 = System.currentTimeMillis();

        List<CompletableFuture<String>> futures = INPUT_LIST.stream().map(param ->
            CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Thread.currentThread().getName() + "_operate";
            })
        ).collect(Collectors.toList());

        CompletableFuture<Void> all = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        all.get();
        all.join();
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);

    }


}
