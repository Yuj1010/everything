package kw.test.sczxfz;

import kw.test.sczxfz.admin.PCData;
import kw.test.sczxfz.scz.Producer;
import kw.test.sczxfz.xfz.Consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * auther   kangwang
 */
public class App {
        /**
         * @param args
         */
        public static void main(String[] args)  throws InterruptedException{
            //建立缓冲区
            BlockingQueue<PCData> queue = new LinkedBlockingDeque<PCData>(10);
            //建立生产者
            Producer producer1 = new Producer(queue);
            Producer producer2 = new Producer(queue);
            Producer producer3 = new Producer(queue);

            //建立消费者
            Consumer consumer1 = new Consumer(queue);
            Consumer consumer2 = new Consumer(queue);
            Consumer consumer3 = new Consumer(queue);

            //建立线程池
            ExecutorService service = Executors.newCachedThreadPool();

            //运行生产者
            service.execute(producer1);
            service.execute(producer2);
            service.execute(producer3);
            //运行消费者
            service.execute(consumer1);
            service.execute(consumer2);
            service.execute(consumer3);

            Thread.sleep(10*1000);

            //停止生产者
            producer1.stop();
            producer2.stop();
            producer3.stop();

            Thread.sleep(3000);
            service.shutdown();
        }

}
