package ex_reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


//class Person{
//	private String name;
//	private int age;
//	public Person() {}
//	public Person(String name, int age) {
//		super();
//		this.name = name;
//		this.age = age;
//	}
//	@Override
//	public String toString() {
//		return "Person [name=" + name + ", age=" + age + "]";
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public int getAge() {
//		return age;
//	}
//	public void setAge(int age) {
//		this.age = age;
//	}
//	
//}
//public class test{
//	public static void main(String[] args) throws Exception  {
//		Class<?> cls=Class.forName("ex_reflect.Person");
//		//任何时候调用类中的普通方法都需要实例化对象
//		Object object=cls.newInstance();
//		//取得setName这个方法的实例化对象，设置方法名称与参数类型
//		Method setMethod=cls.getMethod("setName", String.class);
//		//随后通过Method类对象调用指定方法，调用方法需要实例化对象
//		//同时传入参数
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
//		//取得全部构造
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
//		//取得Class对象
//		Class<?> cls=CLS.class; 
//		//取得Package名称
//		System.out.println(cls.getPackage().getName());
//		//取得父类名称
//		System.out.println(cls.getSuperclass().getName());
//		//取得实现父类接口对象
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






