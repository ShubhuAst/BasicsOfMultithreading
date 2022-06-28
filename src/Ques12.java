//
/*Use Atomic Classes instead of Synchronize method and blocks.*/
//

import java.util.concurrent.atomic.AtomicInteger;

public class Ques12 {
    private static AtomicInteger num = new AtomicInteger(20);

    private void increment() {
        num.incrementAndGet();
    }

    private void decrement() {
        num.decrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        Ques12 obj = new Ques12();

        // Thread 1
        Thread T1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                obj.increment();
                System.out.println(num.get());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Increment");

        // Thread 2
        Thread T2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                obj.decrement();
                System.out.println(num.get());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Increment");

        T1.start();
        T1.join();
        T2.start();
        T2.join();
        System.out.println("Waiting... ");
        Thread.sleep(2000);
        System.out.println("Final Value: " + obj.num);
    }
}
