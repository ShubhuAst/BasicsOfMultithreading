//
/*Try shutdown() and shutdownNow() and observe the difference.*/
//

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ques4 {
    public static void main(String[] args) throws InterruptedException {
        int tSize = 5;

        // for shutdown()
        ExecutorService execute1 = Executors.newFixedThreadPool(tSize);
        for (int i = 0; i < tSize; i++) {
            execute1.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        execute1.shutdown();
        // this didn't allow new threads, and terminate threads that are waiting but didn't interrupt threads in progress.
        System.out.println("Waiting... ");
        Thread.sleep(5000);
        System.out.println("================================");
        // for shutdownNow()
        ExecutorService execute2 = Executors.newFixedThreadPool(tSize);
        for (int i = 0; i < tSize; i++) {
            execute2.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        execute2.shutdownNow();
        // didn't allow new threads, terminates waiting threads, interrupt in progress threads
    }
}
