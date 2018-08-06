package kw.test.thread.dec;

/**
 * auther   kangwang
 */
public class DecThread implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            Dec.inc();
        }
    }
}
