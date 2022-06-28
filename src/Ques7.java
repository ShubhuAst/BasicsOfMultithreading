//
/*Submit List of tasks to ExecutorService and wait for the completion of all the tasks.*/
//

import java.util.concurrent.*;

public class Ques7 {
    public static void main(String[] args) {
        int tSize = 5;
        ExecutorService execute = Executors.newFixedThreadPool(tSize);
        for (int i = 0; i < tSize; i++) {
            execute.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        execute.shutdown();

        try {
            execute.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("All task completed in thread pool");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
