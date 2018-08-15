package cglib;

import java.lang.reflect.Method;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/*
 * cglibʵ�ֶ�̬����
 */


class Message{
	public void send(){
		System.out.println("hello");
	}
}

class ClassProxy implements MethodInterceptor {
	private Object target;
	public ClassProxy(Object target) {
		this.target=target;
	}
	
	public void PreHandle() {
		System.out.println("����ǰ");
	}
	
	public void AfterHandle() {
		System.out.println("�����");
	}

	@Override
	public Object intercept(Object arg0, Method method, Object[] arg2, MethodProxy arg3) throws Throwable {
		// TODO Auto-generated method stub
		this.PreHandle();
		Object ret=method.invoke(this.target,arg2);
		this.AfterHandle();
		return ret;
	}
	
}

public class Test{
	public static void main(String[] args) {
		Message msg = new Message() ;
		// ��������ϵ�Ĵ�������
		Enhancer enhancer = new Enhancer() ;
		enhancer.setSuperclass(msg.getClass()) ;
		// �������,���ϾͶ�̬���ú�����֮��Ĵ����ϵ
		enhancer.setCallback(new ClassProxy(msg)) ;
		Message temp = (Message) enhancer.create() ;
		temp.send() ;

	}
}
