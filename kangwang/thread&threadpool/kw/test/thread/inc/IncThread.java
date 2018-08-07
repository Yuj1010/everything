package kw.test.thread.inc;

/**
 * auther   kangwang
 */
public class IncThread implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<100;i++) {
            Inc.add();
        }
    }
}
