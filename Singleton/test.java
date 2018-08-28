package Singleton;

//饿汉式
//class Singleton{
//	private final static Singleton singleton =new Singleton();
//	private Singleton() {}
//	public static Singleton getSingleton() {
//		return singleton;
//	}
//}

//懒汉式  (线程不安全，不推荐使用)
//class Singleton{
//	private static Singleton singleton;
//	private Singleton() {};
//	public static Singleton getSingleton() {
//		if(singleton==null) {
//			singleton=new Singleton();
//		}
//		return singleton;
//	}
//}

//懒汉式，双重检查
//class Singleton{
//	private static Singleton singleton;
//	private Singleton() {}
//	public static Singleton getSingleton(){
//		if(singleton==null) {
//			synchronized (Singleton.class) {
//				if(singleton==null) {
//					singleton=new Singleton();
//					return singleton;
//				}
//			}
//		}
//		return singleton;
//	}
//}

//静态内部类
//class Singleton{
//	private  Singleton () {}
//	private static class SingletonInstance{
//		private final static  Singleton singleton = new Singleton();
//	}
//	public static Singleton getSingleton() {
//		return SingletonInstance.singleton;
//	}
//}

//枚举
class Singleton{
	
}
enum Type{
	INSTANCE;
	private Singleton instance ;
	private Type() {
		// TODO Auto-generated constructor stub
		instance=new Singleton();
	}
	public Singleton getSingleton() {
		return instance;
	}
	
}




public class test {
	public static void main(String[] args) {
		System.out.println(Type.INSTANCE.getSingleton());
	}
}
