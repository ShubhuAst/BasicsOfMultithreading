//
/*Increase concurrency with Thread pools using newCachedThreadPool() and newFixedThreadPool().*/
//

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ques9 {
    public static void main(String[] args) throws InterruptedException {

        int eSize = 5;
        // it will create new thread if all existing are busy otherwise use the idel one.
        ExecutorService executor1 = Executors.newCachedThreadPool();

        for (int i = 0; i < eSize; i++) {
            executor1.submit(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        executor1.shutdown();

        try {
            executor1.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread.sleep(3000);
        System.out.println("waiting...\n============================================");

        // it will create fixed no. of threads.
        ExecutorService executor2 = Executors.newFixedThreadPool(eSize);
        for (int i = 0; i < eSize; i++) {
            executor2.submit(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }

        executor2.shutdown();

        try {
            executor2.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
