package ex_reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//public class test{
//	public static void main(String[] args) throws Exception  {
//		Class<?> cls=Class.forName("ex_reflect.Person");
//		//�κ�ʱ��������е���ͨ��������Ҫʵ��������
//		Object object=cls.newInstance();
//		//ȡ��setName���������ʵ�����������÷����������������
//		Method setMethod=cls.getMethod("setName", String.class);
//		//���ͨ��Method��������ָ�����������÷�����Ҫʵ��������
//		//ͬʱ�������
//		setMethod.invoke(object, "Yu");
//		Method getMethod=cls.getMethod("getName");
//		Object result=getMethod.invoke(object);
//		System.out.println(result);
//	}
//}


//class Person{
//	public Person() {}
//	public Person(String name) {}
//	public Person(String name,int age) {}
//}
//
//public class test{
//	public static void main(String[] args) {
//		Class<?> cls=Person.class;
//		//ȡ��ȫ������
//		Constructor<?>[] constructors=cls.getConstructors();
//		for (Constructor<?> constructor : constructors) {
//			System.out.println(constructor);
//		}
//	}
//}


//interface IFruit {}
//interface IMessage{}
//class CLS implements IMessage,IFruit{}



//public class test {
//
//	public static void main(String[] args) {
//		//ȡ��Class����
//		Class<?> cls=CLS.class; 
//		//ȡ��Package����
//		System.out.println(cls.getPackage().getName());
//		//ȡ�ø�������
//		System.out.println(cls.getSuperclass().getName());
//		//ȡ��ʵ�ָ���ӿڶ���
//		Class<?>[] iclass=cls.getInterfaces();
//		for (Class<?> class1 : iclass) {
//			System.out.println(class1.getName());
//		}
//	}
//}

class Person{
	private String name;
}

public class test{
	public static void main(String[] args) throws Exception {
		Class<?> cls=Class.forName("ex_reflect.Person");
		Object object=cls.newInstance();
		Field nameField=cls.getDeclaredField("name");
		System.out.println(nameField.getType().getName());
		System.out.println(nameField.getType().getSimpleName());
	}
}






