package Singleton;

//����ʽ
//class Singleton{
//	private final static Singleton singleton =new Singleton();
//	private Singleton() {}
//	public static Singleton getSingleton() {
//		return singleton;
//	}
//}

//����ʽ  (�̲߳���ȫ�����Ƽ�ʹ��)
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

//����ʽ��˫�ؼ��
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

//��̬�ڲ���
//class Singleton{
//	private  Singleton () {}
//	private static class SingletonInstance{
//		private final static  Singleton singleton = new Singleton();
//	}
//	public static Singleton getSingleton() {
//		return SingletonInstance.singleton;
//	}
//}

//ö��
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
