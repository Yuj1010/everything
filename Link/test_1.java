package www.bit.Link;

public class test_1 {

	public static void main(String[] args) {
		Link<String> link = Factory.getLinkInstance();
		link.add("��ͷ");
		link.add("����1");
		link.add("����2");
		link.add("��β");
		System.out.println(link.remove(0));
		link.printLink();
	}

}