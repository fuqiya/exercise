package demos;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThead implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("******come");
        return 2048;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyThead());
       new Thread(futureTask,"A").start();

        System.out.println(futureTask.get());
    }
}
