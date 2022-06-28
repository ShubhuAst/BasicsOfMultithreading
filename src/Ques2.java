//
/*Use sleep and join methods with thread.*/
//
public class Ques2 {

    private static int num = 10;

    private synchronized void increment() {
        num++;
    }

    private synchronized void decrement() {
        num--;
    }

    public static void main(String[] args) throws InterruptedException {
        Ques2 obj = new Ques2();

        // Thread 1
        Thread T1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                obj.increment();
                System.out.println(num);
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
                System.out.println(num);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Decrement");

        T1.start();
        T1.join();
        T2.start();
        T2.join();
        System.out.println("Waiting... ");
        Thread.sleep(2000);
        System.out.println("Final Value: " + obj.num);
    }
}
