package kw.test.sczxfz.scz;

import kw.test.sczxfz.admin.PCData;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer  implements Runnable{

    //Volatile修饰的成员变量在每次被线程访问时，都强迫从共享内存中重读该成员变量的值。
    //而且，当成员变量发生变化时，强迫线程将变化值回写到共享内存。
    //这样在任何时刻，两个不同的线程总是看到某个成员变量的同一个值。
    private volatile  boolean isRunning= true;

    //内存缓冲区
    private BlockingQueue<PCData> queue;

    //总数，原子操作
    private static AtomicInteger count = new AtomicInteger();

    /*睡着的时间*/
    private static final int SLEEPTIME=1000;


    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        PCData data=null;
        Random r  = new Random();
        System.out.println("start producer id = "+ Thread .currentThread().getId());
        try{
            while(isRunning){
                Thread.sleep(r.nextInt(SLEEPTIME));
                //构造任务数据
                data= new PCData(count.incrementAndGet());
                System.out.println("data is put into queue ");
                //提交数据到缓冲区
                if(!queue.offer(data,2,TimeUnit.SECONDS)){
                    System.out.println("faile to  put data:  "+ data);
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();

        }


    }

    public void stop(){

        isRunning=false;
    }


}