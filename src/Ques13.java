//
/*Coordinate 2 threads using wait() and notify().*/
//
public class Ques13 {

    public static void main(String[] args) {

        Ques13 obj = new Ques13();

        new Thread(() -> {
            try {
                synchronized (obj) {
                    System.out.println("Running: " + Thread.currentThread().getName());
                    Thread.sleep(3000);

                    // wait until 'obj' is not free
                    obj.wait();
                    System.out.println("execution back: " + Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
                synchronized (obj) {
                    System.out.println("Running: " + Thread.currentThread().getName());

                    // notify/wakes any one(single) who needs 'obj' after this thread execution completes
                    obj.notify();
                    System.out.println("Finish Work: " + Thread.currentThread().getName());

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
