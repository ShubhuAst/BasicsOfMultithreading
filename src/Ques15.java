//
/*Use Reentract lock for coordinating 2 threads with signal(), signalAll() and wait().*/
//

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ques15 {
    public static void main(String[] args) {
        List<Integer> listInt = new LinkedList<>();
        Lock lock = new ReentrantLock();
        Condition conAdd = lock.newCondition();
        Condition conRemove = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Delete operation: " + Thread.currentThread().getName());
                Thread.sleep(3000);
                while (listInt.isEmpty()) {
                    System.out.println("List is empty : Delete operation");
                    // wait until any element gat added
                    conAdd.await();
                }
                System.out.println("Now List is not empty, execution back(delete operation): " +
                        Thread.currentThread().getName());
                conRemove.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(500);
                System.out.println("Adding Element: " + Thread.currentThread().getName());
                while (listInt.size() == Integer.MAX_VALUE) {
                    // Taking max size this is just an example
                    conRemove.await();
                }
                listInt.add(5);
                // notify/wakes all who needs 'list' after this thread execution completes
                conAdd.signalAll();
                System.out.println("Adding Element Finish: " + Thread.currentThread().getName());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
