package kw.test.queue.cus;

import java.util.concurrent.BlockingDeque;

/**
 * auther   kangwang
 */
public class Customer implements Runnable {

    BlockingDeque blockingDeque = null;

    public Customer(BlockingDeque blockingDeque){
        this.blockingDeque=blockingDeque;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            blockingDeque.take();
            System.out.println("消费===========");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
