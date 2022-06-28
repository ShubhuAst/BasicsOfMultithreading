import java.util.LinkedList;
import java.util.List;

//
/*Coordinate multiple threads using wait() and notifyAll()*/
//
public class Ques14 {

    public static void main(String[] args) {
        List<Integer> listInt = new LinkedList<>();

        Thread delete = new Thread(() -> {
            try {
                synchronized (listInt) {
                    System.out.println("Delete operation: " + Thread.currentThread().getName());
                    Thread.sleep(3000);
                    while (listInt.isEmpty()) {
                        System.out.println("List is empty : Delete operation");
                        // wait until 'list' is not free
                        listInt.wait();
                    }
                    System.out.println("Now List is not empty, execution back(delete operation): " +
                            Thread.currentThread().getName());
                    listInt.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread add = new Thread(() -> {
            try {
                Thread.sleep(500);
                synchronized (listInt) {
                    System.out.println("Adding Element: " + Thread.currentThread().getName());

                    // notify/wakes all who needs 'list' after this thread execution completes
                    listInt.notifyAll();
                    listInt.add(5);
                    System.out.println("Adding Element Finish: " + Thread.currentThread().getName());

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread get = new Thread(() -> {
            try {
                Thread.sleep(5000);
                synchronized (listInt) {
                    System.out.println("Get data: " + Thread.currentThread().getName());
                    while (listInt.isEmpty()) {
                        System.out.println("List is empty: Get data");
                        // wait until 'obj' is not free
                        listInt.wait();
                    }
                    System.out.println("Now List is not empty, execution back(delete operation): " +
                            Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        delete.start();
        add.start();
        get.start();
    }
}
