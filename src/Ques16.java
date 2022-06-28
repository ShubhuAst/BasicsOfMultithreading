//
/*Create a deadlock and Resolve it using tryLock().*/
//

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* ----------------------------------------------Dead Lock----------------------------------------------------*/
// comment below code and run this

//public class Ques16 {
//
//    public static void main(String[] args) {
//
//        Ques16 obj1 = new Ques16();
//        Ques16 obj2 = new Ques16();
//
//
//        new Thread(() -> {
//            try {
//                synchronized (obj1) {
//                    System.out.println("Thread 1: Resource Lock 1");
//                    Thread.sleep(2000);
//
//                    synchronized (obj2) {
//                        System.out.println("Thread 1: Resource Lock 2");
//                    }
//                }
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//
//        new Thread(() -> {
//            try {
//                synchronized (obj2) {
//                    System.out.println("Thread 2: Resource Lock 2");
//                    Thread.sleep(2000);
//
//                    synchronized (obj1) {
//                        System.out.println("Thread 2: Resource Lock 1");
//                    }
//                }
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }


/* ----------------------------------------Resolve Deadlock Using tryLock------------------------------------------------*/

// comment above code and run this
public class Ques16 {
    public static void main(String[] args) {
        Resource res1 = new Resource();
        Resource res2 = new Resource();
        Ques16 lock = new Ques16();
        new Thread(() -> {
            try {
                lock.lock(res1, res2);
                System.out.println("Thread 1: Resource Lock 1");
                Thread.sleep(2000);
                System.out.println("Thread 1: Resource Lock 2");
                lock.unLock(res1, res2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                lock.lock(res1, res2);
                System.out.println("Thread 2: Resource Lock 2");
                Thread.sleep(2000);
                System.out.println("Thread 2: Resource Lock 1");
                lock.unLock(res1, res2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void lock(Resource res1, Resource res2) {
        while (true) {
            Boolean resOneLocked = res1.getLock().tryLock();
            Boolean resTwoLocked = res2.getLock().tryLock();
            if (resOneLocked && resTwoLocked) {
                return;
            }
            if (resOneLocked) {
                res1.getLock().unlock();
            }
            if (resTwoLocked) {
                res2.getLock().unlock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void unLock(Resource res1, Resource res2) {
        res1.getLock().unlock();
        res2.getLock().unlock();
    }

}

class Resource {
    private Lock lock = new ReentrantLock();

    public Lock getLock() {
        return lock;
    }
}