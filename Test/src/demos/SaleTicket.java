package demos;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicket {
    public static void main(String[] args){
        Ticket ticket =new Ticket();

        new Thread(() -> { for (int i = 1; i <=31;i++) ticket.sale(); },"A").start();
        new Thread(() -> { for (int i = 1; i <=31;i++) ticket.sale(); },"B").start();
        new Thread(() -> { for (int i = 1; i <=31;i++) ticket.sale(); },"C").start();
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=31;i++) {
                    ticket.sale();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=31;i++) {
                    ticket.sale();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=31;i++) {
                    ticket.sale();
                }
            }
        }, "C").start();*/
    }
}

class Ticket
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
