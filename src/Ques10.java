//
/*Use Synchronize method to enable synchronization between multiple
threads trying to access method at same time.*/
//
public class Ques10 {

    private static int num = 10;

    // now one thread at a time can access this method.
    private synchronized void incrCurrValue() {
        num++;
    }

    public static void main(String[] args) throws InterruptedException {
        Ques10 obj = new Ques10();

        // Thread 1
        Thread T1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                obj.incrCurrValue();
                System.out.println(Thread.currentThread().getName() + " : " + num);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Thread 2
        Thread T2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                obj.incrCurrValue();
                System.out.println(Thread.currentThread().getName() + " : " + num);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        T1.start();
        T1.join();
        T2.start();
        T2.join();
        System.out.println("Waiting... ");
        Thread.sleep(2000);
        System.out.println("Final Value: " + obj.num);
    }
}
