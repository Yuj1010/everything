package temp;

import java.awt.event.MouseWheelEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;






//class Person implements Comparable<Person>{
//	private String name;
//	private Integer age;
//	@Override
//	public String toString() {
//		return "Person [name=" + name + ", age=" + age + "]";
//	}
//	public int compareTo(Person per) {
//		if(per.age>this.age) {
//			return -1;
//		}else if(per.age<this.age) {
//			return 1;
//		}else {
//			return this.name.compareTo(per.name);
//		}
//	}
//	public Person(String name, Integer age) {
//		super();
//		this.name = name;
//		this.age = age;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public Integer getAge() {
//		return age;
//	}
//	public void setAge(Integer age) {
//		this.age = age;
//	}
//	
//}


class Person implements Comparable<Person>{
	private String name;
	private Integer age;
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public int compareTo(Person per) {
		if(per.age>this.age) {
			return -1;
		}else if(per.age<this.age) {
			return 1;
		}else {
			return this.name.compareTo(per.name);
		}
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(name,age);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj) return true;
		if(obj==null||getClass()!=obj.getClass()) return false;
		Person per=(Person) obj;
		return Objects.equals(name, per.name)&&Objects.equals(age, per.age);
	}
	
}


public class test {
	public static void main(String[] args) {
		Map<Integer, Person> map=new HashMap<>();
		map.put(1, new Person("A", 100));
		map.put(2, new Person("B", 90));
		map.put(3, new Person("C", 70));
		Set<Map.Entry<Integer, Person>> set = map.entrySet();
		Iterator<Map.Entry<Integer, Person>> iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry<Integer, Person> per =iterator.next();
			System.out.println(per.getKey()+"->"+per.getValue());
		}
	}
}
