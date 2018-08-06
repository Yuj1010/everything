package kw.test.thread.inc;

/**
 * auther   kangwang
 */
public class Inc {
    static int i=0;
    public static synchronized void add(){
        i++;
        System.out.print(Thread.currentThread().getName()+i+"   ");
    }
}
