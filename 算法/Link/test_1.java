package www.bit.Link;

public class test_1 {

	public static void main(String[] args) {
		Link<String> link = Factory.getLinkInstance();
		link.add("车头");
		link.add("车厢1");
		link.add("车厢2");
		link.add("车尾");
		System.out.println(link.remove(0));
		link.printLink();
	}

}