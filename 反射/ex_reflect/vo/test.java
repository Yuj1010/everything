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
// * ������Ҫ����ʵ���Զ���voƥ�䴦�����
// */
//class BeanOperation{
//	private BeanOperation() {}
//	/*
//	 * �����������е����Բ���
//	 * actionObject:��ǰ������������Ķ���
//	 * msg:���Եľ������ݣ���ʽΪ���������ƣ�����|�������ƣ�����.......
//	 */
//	public static void setBeanValue(Object actionObject,String msg) throws Exception {
//		//����ַ�����ȥ��������������,����|���
//		String [] result=msg.split("\\|");
//		//ִ�н�����ʣ�µĵĸ�ʽΪ���������ƣ�����
//		//������֣����ա��������
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
//	 * �������ַ�������ĸת��Ϊ��д
//	 * str�������ַ���
//	 */
//
//	public static String initCap(String str) {
//		return str.substring(0, 1).toUpperCase()+str.substring(1);
//	}
//	
//	public static Object getObject(Object obj,String attribute) throws Exception {
//		String methodName="get"+initCap(attribute);
//		//����ָ�����͵�Field����Ŀ����ȡ�ö������ͣ�
//		Field field=obj.getClass().getDeclaredField(attribute);
//		if(field==null) {
//			//�ڶ��γ��ԴӸ���ȡ�ø�����
//			field=obj.getClass().getField(attribute);
//		}
//		if(field==null) {
//			//��ʱ����û�и����ԣ��˳�
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














