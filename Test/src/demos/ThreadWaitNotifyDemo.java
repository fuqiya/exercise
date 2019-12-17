package demos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirCounditoner {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment() {
        lock.lock();
        try
        {
            while (number != 0) {
                condition.await();//this.wait();
            }
            ++number;
            System.out.println(Thread.currentThread().getName()+":"+number);
            condition.signalAll();//this.notifyAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void decrement() {
        lock.lock();
        try
        {
            while (number == 0) {
                condition.await();//this.wait();
            }
            --number;
            System.out.println(Thread.currentThread().getName()+":"+number);
            condition.signalAll();//this.notifyAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}


public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirCounditoner airCounditoner = new AirCounditoner();

        new Thread(() -> {for (int i = 1;i<=10; i++) airCounditoner.increment();},"A").start();
        new Thread(() -> {for (int i = 1;i<=10; i++) airCounditoner.decrement();},"B").start();
        new Thread(() -> {for (int i = 1;i<=10; i++) airCounditoner.increment();},"C").start();
        new Thread(() -> {for (int i = 1;i<=10; i++) airCounditoner.decrement();},"D").start();
    }
}
