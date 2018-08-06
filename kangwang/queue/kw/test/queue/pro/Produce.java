package kw.test.queue.pro;

import kw.test.queue.test;

import java.util.concurrent.BlockingDeque;

/**
 * auther   kangwang
 */
public class Produce implements Runnable {
    BlockingDeque blockingDeque = null;

    public Produce(BlockingDeque blockingDeque){
        this.blockingDeque=blockingDeque;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            blockingDeque.add(new test());
            System.out.println("生产者====================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
