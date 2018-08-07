package kw.test.threadpool.cache.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * auther   kangwang
 */
public class ThreadPoolTest {
    public static void test(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

            cachedThreadPool.execute(new Runnable() {
                int index= 0;
                @Override
                public void run() {
                    while (true){
                        try {
                            Thread.sleep(index * 1000);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        index++;
                    System.out.println(Thread.currentThread().getName()+"================"+index);
                }}
            });
        }
    }
