//
/*Create and Run a Thread using Runnable Interface and Thread class.*/
//
class ThreadOne extends Thread {
    public void run() {
        System.out.println("I am thread created by using Thread class.");
    }
}

class ThreadTwo implements Runnable {
    public void run() {
        System.out.println("I am thread created by using Runnable interface.");
    }
}

public class Ques1 {

    public static void main(String[] args) {

        // using thread class
        ThreadOne T1 = new ThreadOne();
        T1.start();

        //using runnable interface
        Thread T2 = new Thread(new ThreadTwo());
        T2.start();
    }
}
