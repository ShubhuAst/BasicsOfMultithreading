//
/*Schedule task using schedule(), scheduleAtFixedRate() and scheduleWithFixedDelay()*/
//

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Ques8 {
    public static void main(String[] args) throws InterruptedException {

        int sSize = 5;
        /*-----------------------------------------------part1-----------------------------------------------------*/
        System.out.println("with schedule() :...");
        ScheduledExecutorService scheduler1 = Executors.newScheduledThreadPool(sSize);

        for (int i = 0; i < sSize; i++) {
            // schedule() enable task after given delay
            scheduler1.schedule(() -> {
                System.out.println(Thread.currentThread().getName());
            }, 2, TimeUnit.SECONDS);
        }
        scheduler1.shutdown();
        Thread.sleep(3000);
        System.out.println("===============================================");

        /*-----------------------------------------------part2-----------------------------------------------------*/
        System.out.println("with scheduleAtFixedRate() :...");
        ScheduledExecutorService scheduler2 = Executors.newScheduledThreadPool(1);

        // scheduleAtFixedRate() enable task after given delay and then execute it periodically
        // no matter previous task is complete or not
        scheduler2.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName());
        }, 3, 1, TimeUnit.SECONDS);

        /*-----------------------------------------------part3-----------------------------------------------------*/
        // comment part 2 and uncomment part 3

//        System.out.println("with scheduleWithFixedDelay() :...");
//        ScheduledExecutorService scheduler3 = Executors.newScheduledThreadPool(1);
//        // scheduleWithFixedDelay() enable task after given delay and then execute next task after the given delay
//        // when current task is completed
//        scheduler3.scheduleWithFixedDelay(() -> {
//            System.out.println(Thread.currentThread().getName());
//        }, 3, 2, TimeUnit.SECONDS);

    }
}
