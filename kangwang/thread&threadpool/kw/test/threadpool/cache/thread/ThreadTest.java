package kw.test.threadpool.cache.thread;

/**
 * auther   kangwang
 */
public class ThreadTest extends Thread{
    @Override
    public void run() {
        while(true) {
            System.out.println("我是线程==============@！");
            try {
                ThreadTest.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
