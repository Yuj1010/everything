package kw.test.thread;

import kw.test.thread.dec.DecThread;
import kw.test.thread.inc.IncThread;

/**
 * auther   kangwang
 */
public class App {
    public static void main(String []args){
        Thread t1 = new Thread(new IncThread(),"inc");
        Thread t2 = new Thread(new DecThread(),"dec");
        t1.start();
        t2.start();
    }
}
