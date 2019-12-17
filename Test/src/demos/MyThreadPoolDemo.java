package demos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket2
{
    private int number = 30;
    private Lock lock = new ReentrantLock();

    public  void sale() {
        lock.lock();
        try {
            if(number > 0) {
                System.out.println(Thread.currentThread().getName()+"卖出了第"+number--+"张票,还剩"+number+"张票");
            }

        }finally {
            lock.unlock();
        }
    }
}

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        Ticket2 ticket2 = new Ticket2();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try
        {
            for (int i = 1; i <= 30 ;i++) {
                threadPool.execute(() -> {ticket2.sale();});
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
}
