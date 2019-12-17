package demos;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("葫芦兄弟合体，葫芦小金刚在此，妖精！");
        });

         for (int i = 1;i <= 7; i++) {
             final int tmpI = i;
              new Thread(() -> {
                  try {
                      System.out.println(Thread.currentThread().getName()+"\t 葫芦"+tmpI+"娃来了");
                      cyclicBarrier.await();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  } catch (BrokenBarrierException e) {
                      e.printStackTrace();
                  }
              }, String.valueOf(i)).start();
          }
    }
}
