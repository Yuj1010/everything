class Person implements Comparable<Person>{
	private String name;
	private Integer age;
	
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
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	public int compareTo(Person o) {
		if(this.age>o.age) {
			return 1;
		}else if (this.age<o.age) {
			return -1;
		}else {
			return this.name.compareTo(o.name);
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}

public class test{
	public static void main(String[] args) {
//		List<String> list=new ArrayList<>();
//		list.add("A");
//		list.add("C");
//		list.add("D");
//		list.add("C");
//		System.out.println(list.size());
//		System.out.println(list.isEmpty());
//		System.out.println(list.contains("B"));
//		System.out.println(list.set(0, "Z"));
//		System.out.println();
//		for (String string : list) {
//			System.out.println(string);
//		}
//		Set<Person> list=new TreeSet<>();
//		Person A=new Person("A", 3);
//		list.add(A);
//		list.add(new Person("A", 3));
//		list.add(new Person("B", 5));
//		list.add(new Person("C", 6));
//		list.remove(A);
//		System.out.println(list.contains(new Person("A", 3)));
//		for (Person person : list) {
//			System.out.println(person);
//		}
//		Set<Person> set =new TreeSet<>();
//		set.add(new Person("习大大", 60));
//		set.add(new Person("金三胖", 30));
//		for (Person person : set) {
//			System.out.println(person);
//		}
	}
}
