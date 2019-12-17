package demos;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(5);

         for (int i = 1;i <= 5; i++) {
              new Thread(() -> {
                  System.out.println(Thread.currentThread().getName()+"\t 离开教室 ");
                  countDownLatch.countDown();
              }, String.valueOf(i)).start();
          }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 成功关门");
    }
}
