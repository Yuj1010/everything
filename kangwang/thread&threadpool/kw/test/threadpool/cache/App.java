package kw.test.threadpool.cache;

import kw.test.threadpool.cache.pool.ThreadPoolTest;

/**
 * auther   kangwang
 */
public class App {
    public static void main(String[]args){
/*        ThreadTest threadTest = new ThreadTest();
        threadTest.start();*/

/*        RunnableTest runnableTest = new RunnableTest();
        Thread thread = new Thread(runnableTest);
        thread.start();*/

        ThreadPoolTest.test();
        ThreadPoolTest.test();
        ThreadPoolTest.test();
        ThreadPoolTest.test();
        ThreadPoolTest.test();

    }
}
