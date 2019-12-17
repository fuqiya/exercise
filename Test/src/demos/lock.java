package demos;

import java.util.concurrent.TimeUnit;

class Phone {
    public static synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("******sendEmail");
    }

    public synchronized void sendSMS() throws InterruptedException {
        System.out.println("******sendSMS");
    }

    public void hello() {
        System.out.println("******hello");
    }
}

public class lock {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
