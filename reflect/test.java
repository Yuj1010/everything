package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface ISubject{
	public void eat(String msg,int num);
}

class RealSubject implements ISubject{

	@Override
	public void eat(String msg,int num) {
		// TODO Auto-generated method stub
		System.out.println("��"+num+"��"+msg);
	}
	
}

class ProxySubject implements InvocationHandler{
	private Object realSubject;
	public Object bind(Object readSubject) {
		this.realSubject=readSubject;
		return Proxy.newProxyInstance(readSubject.getClass().getClassLoader(), readSubject.getClass().getInterfaces(), this);
	}
	public void before() {
		System.out.println("����ǰ");
	}
	public void after() {
		System.out.println("�����");
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		this.before();
		Object ret =method.invoke(this.realSubject, args);
		this.after();
		return ret;
	}
	
}

//class ProxySubject implements ISubject{
//	private ISubject subject;
//	public ProxySubject (ISubject subject ) {
//		this.subject=subject;
//	}
//	public void preEat() {
//		System.out.println("���");
//	}
//	public void aftEat() {
//		System.out.println("ϴ��");
//	}
//	@Override
//	public void eat() {
//		// TODO Auto-generated method stub
//		this.preEat();
//		this.subject.eat();
//		this.aftEat();
//	}
//	
//}

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
public class test {

	public static void main(String[] args) throws Exception {
		ISubject subject=(ISubject)new ProxySubject().bind(new RealSubject());
		subject.eat("����", 10);
	}

}
