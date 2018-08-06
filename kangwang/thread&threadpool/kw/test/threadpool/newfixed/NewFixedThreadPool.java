package kw.test.threadpool.newfixed;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * auther   kangwang
 */
public class NewFixedThreadPool {
    public void fun(){
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        System.out.println("================RUNABLE========");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}
