//
/*Use a singleThreadExecutor to submit multiple threads.*/
//

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ques3 {

    public static void main(String[] args) {

        int tSize = 5;
        // create single thread only which will execute task one after another.
        ExecutorService execute = Executors.newSingleThreadExecutor();

        for (int i = 0; i < tSize; i++) {
            execute.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        execute.shutdown();

        try {
            execute.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
