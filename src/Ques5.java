//
/*Use isShutDown() and isTerminated() with ExecutorService.*/
//

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ques5 {
    public static void main(String[] args) throws InterruptedException {
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
        System.out.println("isShutDown() :" + execute.isShutdown()); // true
        System.out.println("isTerminated() :" + execute.isTerminated()); //false
        // isTerminated() false because in my try some threads are still in progress due to sleep()

    }
}
