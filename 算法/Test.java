package homework;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import org.omg.CORBA.PRIVATE_MEMBER;

//class MyThread implements Runnable{
//	private int flag=3;
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		for(int i=0;i<100;i++){
//			this.fun();
//		}
//	}
//	
//	public synchronized void fun() {
//		if(Thread.currentThread().getName().equals("*Thread-0")&&flag%3==0) {
//			System.out.print("*Thread-0");
//			flag++;
//		}
//		else if(Thread.currentThread().getName().equals("#Thread-1")&&flag%3==1) {
//			System.out.print("#Thread-1");
//			flag++;
//		}
//		else if(Thread.currentThread().getName().equals("@Thread-2")&&flag%3==2) {
//			System.out.print("@Thread-2");
//			flag++;
//		}
//	}
//	
//}
//public class Test{
//	public static void main(String[] args) {
//		MyThread myThread=new MyThread();
//		Thread thread1=new Thread(myThread,"*Thread-0");
//		Thread thread2=new Thread(myThread,"#Thread-1");
//		Thread thread3=new Thread(myThread,"@Thread-2");
//		thread1.start();
//		thread2.start();
//		thread3.start();
//		
//	}
//}


public class Test{
	public static void main(String[] args) {
		System.out.println(Num(5));
		
	}
//	static int s(int i){//绝佳的一个代码。。。
//		int u=i;
//		for(int x=0;x<5;x++){
//			if(i%5!=1)
//				return s(u+1);
//			i=(i-1)/5*4;
//		}
//		return u;
//	}

	private static int Num(int i) {
		while (true) {
			int num=1;
			for(int j=0;j<i;j++) {
				if(num%5!=1) {
					num++;
					continue;
				}
				else {
					num=(num-1)/5*4;
				}
			}
			return num;
		}
	}
}
