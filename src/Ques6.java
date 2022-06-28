//
/*Return a Future from ExecutorService by using callable and use get(), isDone(),
 isCancelled() with the Future object to know the status of task submitted.*/
//

import java.util.concurrent.*;

public class Ques6 {

    public static void main(String[] args) {
        int tSize = 1;
        ExecutorService execute = Executors.newFixedThreadPool(tSize);
        Future<String> future = execute.submit(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "I am from future :)";
        });
        execute.shutdown();

        try {
            System.out.println("get(): " + future.get(5, TimeUnit.SECONDS));
            System.out.println("isDone(): " + future.isDone());
            System.out.println("isCancelled(): " + future.isCancelled());
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
