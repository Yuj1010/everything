package kw.test.thread.dec;

/**
 * auther   kangwang
 */
public class Dec {
    static int i=0;
    public static synchronized void inc(){
        i++;
        System.out.print(Thread.currentThread().getName()+i+"   ");
    }
}
