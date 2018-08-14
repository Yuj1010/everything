package vo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


//class Emp{
//	private String name;
//	private String job;
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getJob() {
//		return job;
//	}
//	public void setJob(String job) {
//		this.job = job;
//	}
//	@Override
//	public String toString() {
//		return "Emp [name=" + name + ", job=" + job + "]";
//	}
//	
//}
//class EmpAction{
//	private Emp emp=new Emp();
//	public void setValue(String value) throws Exception {
//		BeanOperation.setBeanValue(this,value) ;
//	}
//	public Emp getEmp() {
//		return emp;
//	}
//}
//
///*
// * 本类主要负责实现自动的vo匹配处理操作
// */
//class BeanOperation{
//	private BeanOperation() {}
//	/*
//	 * 负责设置类中的属性操作
//	 * actionObject:当前发出设置请求的对象
//	 * msg:属性的具体内容，格式为：属性名称：内容|属性名称：内容.......
//	 */
//	public static void setBeanValue(Object actionObject,String msg) throws Exception {
//		//拆分字符串，去除所有属性内容,按照|拆分
//		String [] result=msg.split("\\|");
//		//执行结束后剩下的的格式为：属性名称：内容
//		//继续拆分，按照“：”拆分
//		for(int i=0;i<result.length;i++) {
//			String [] temp=result[i].split(":");
//			String attribute=temp[0];
//			String value=temp[1];
//			String[] fields=attribute.split("\\.");
//			Object currenObject=getObject(actionObject,fields[0]);
//			setObjectValue(currenObject,fields[1],value);
//		}
//	}
//
//	/*
//	 * 将给定字符串首字母转换为大写
//	 * str：给定字符串
//	 */
//
//	public static String initCap(String str) {
//		return str.substring(0, 1).toUpperCase()+str.substring(1);
//	}
//	
//	public static Object getObject(Object obj,String attribute) throws Exception {
//		String methodName="get"+initCap(attribute);
//		//调用指定类型的Field对象，目的是取得对象类型，
//		Field field=obj.getClass().getDeclaredField(attribute);
//		if(field==null) {
//			//第二次尝试从父类取得该属性
//			field=obj.getClass().getField(attribute);
//		}
//		if(field==null) {
//			//此时表面没有该属性，退出
//			return null;
//		}
//		Method method=obj.getClass().getMethod(methodName);
//		return method.invoke(obj);
//	}
//	
//	public static void setObjectValue(Object obj,String attribute,String value) throws Exception {
//		Field field=obj.getClass().getDeclaredField(attribute);
//		if(field==null) {
//			field=obj.getClass().getField(attribute);
//		}
//		if(field==null) {
//			return ;
//		}
//		String methodName="set"+initCap(attribute);
//		Method setMethod=obj.getClass().getMethod(methodName, field.getType());
//		setMethod.invoke(obj, value);
//	}
//}
//
//
//
//public class test{
//	public static void main(String[] args) throws Exception {
//		String value = "emp.name:LBj|emp.job:King" ;
//		EmpAction empAction=new EmpAction();
//		empAction.setValue(value);
//		System.out.println(empAction.getEmp());
//	}
//}














