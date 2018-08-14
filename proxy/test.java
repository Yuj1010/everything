package proxy;

//import java.lang.reflect.Constructor;
/*
 * 基础代理
 */
//interface ISubject{
//	public void eat();
//}
//
//class RealSubject implements ISubject {
//
//	@Override
//	public void eat() {
//		// TODO Auto-generated method stub
//		System.out.println("吃烧烤");
//	}
//
//}
//
//class ProxySubject implements ISubject{
//	private ISubject subject;
//	
//	public ProxySubject(ISubject subject) {
//		this.subject=subject;
//	}
//	public void perpare() {
//		System.out.println("先买菜");
//	}
//	public void afterEat() {
//		System.out.println("吃完睡觉");
//	}
//	@Override
//	public void eat() {
//		// TODO Auto-generated method stub
//		this.perpare();
//		this.subject.eat();
//		this.afterEat();
//	}
//	
//}
//
//class Factory{
//	public static <T> T getInstance(String proxyClassName,String realClassName) throws Exception{
//		T t=null;
//		Class<?> class1=Class.forName(realClassName);
//		Object realObj=class1.newInstance();
//		Class<?> proxyClass =Class.forName(proxyClassName);
//		Constructor<?> cons =proxyClass.getDeclaredConstructor(class1.getInterfaces()[0]);
//		t=(T)cons.newInstance(realObj);
//		return t;
//	}
//}
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.concurrent.ForkJoinPool.ManagedBlocker;



/*
 * 动态代理
 */
//interface ISubject{
//	public void eat(String msg,int num) ;
//}
//
//class RealSubject implements ISubject{
//
//	@Override
//	public void eat(String msg, int num) {
//		// TODO Auto-generated method stub
//		System.out.println("我要吃"+num+"个"+msg);
//	}
//	
//}
//
//class ProxySubject implements InvocationHandler{
//	private Object target;
//	public Object bind(Object target) {
//		this.target=target;
//		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
//		
//	}
//	public void preHandle() {
//		System.out.println("代理前");
//	}
//	public void afterHandle() {
//		System.out.println("代理后");
//	}
//	@Override
//	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//		// TODO Auto-generated method stub
//		this.preHandle();
//		Object ret =method.invoke(this.target, args);
//		this.afterHandle();
//		return ret;
//	}
//	
//}
//
//
//




/*
 * cglib实现动态代理
 */







