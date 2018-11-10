package pc_model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Goods{
	private String goodsname;
	private int count;
	public synchronized void getGoods() {
		while(this.count<1) {
			try {
				this.wait();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.count--;
		System.out.println(Thread.currentThread().getName()+"消费者已经消费");
		System.out.println(toString());
		System.out.println("------------------------------------------");
		this.notifyAll();
	}
	public synchronized void setGoods(String goodsName) {
		while(this.count>0) {
			try {
				this.wait();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.goodsname=goodsName;
		this.count++;
		System.out.println(Thread.currentThread().getName()+"生产者生产商品");
		System.out.println(toString());
		System.out.println("------------------------------------------");
		this.notifyAll();
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Goods [goodsname=" + goodsname + ", count=" + count + "]";
	}
}

class Producer implements Runnable{
	private Goods goods;
	public Producer(Goods goods) {
		super();
		this.goods=goods;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			this.goods.setGoods("奔驰G500");
		}
	}
}

class Consumer implements Runnable{
	private Goods goods;
	public Consumer(Goods goods) {
		super();
		this.goods=goods;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			this.goods.getGoods();
		}
	}
}

public class test {

	public static void main(String[] args) {
		Goods goods=new Goods();
		List<Thread> list =new ArrayList<>();
//		for(int i=0;i<10;i++) {
//			Producer producer=new Producer(goods);
//			Thread pThread=new Thread(producer,"生产者"+i);
//			list.add(pThread);
//		}
//		for(int i=0;i<5;i++) {
//			Consumer consumer=new Consumer(goods);
//			Thread cThread=new Thread(consumer,"消费者"+i);
//			list.add(cThread);
//
//		}
		ExecutorService pService =Executors.newFixedThreadPool(10);
		ExecutorService cService =Executors.newFixedThreadPool(5);
		for(int i=0;i<5;i++) {
			Producer producer=new Producer(goods);
			pService.submit(producer);
		}
		for(int i=0;i<5;i++) {
			Consumer consumer=new Consumer(goods);
			cService.submit(consumer,"消费者"+i);
		}
		for (Thread thread : list) {
			thread.start();
		}
		pService.shutdown();
		cService.shutdown();
	}

}
