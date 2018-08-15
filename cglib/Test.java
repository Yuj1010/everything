package cglib;

import java.lang.reflect.Method;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/*
 * cglib实现动态代理
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
		System.out.println("代理前");
	}
	
	public void AfterHandle() {
		System.out.println("代理后");
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
		// 负责代理关系的代理处理类
		Enhancer enhancer = new Enhancer() ;
		enhancer.setSuperclass(msg.getClass()) ;
		// 代理对象,以上就动态配置好了类之间的代理关系
		enhancer.setCallback(new ClassProxy(msg)) ;
		Message temp = (Message) enhancer.create() ;
		temp.send() ;

	}
}
