package Map;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import java.util.Iterator;

class Order{
	private String title;
	private double price;
	private int amount;
	public Order(String title,double price,int amount) {
		this.title=title;
		this.price=price;
		this.amount=amount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}



public class test {
	public static void main(String[] args) {
//		Map<Integer, String> map=new HashMap<>();
//		map.put(1, "hello");
//		map.put(2, "world");
//		map.put(3, "hello");
//		map.put(4, "java");
//		Set<Map.Entry<Integer, String>> set=map.entrySet();
//		Iterator<Map.Entry<Integer, String>> iterator=set.iterator();
//		while(iterator.hasNext()) {
//			Map.Entry<Integer, String>temp=iterator.next();
//			System.out.println(temp.getKey()+"="+temp.getValue());
//		}
		List<Order> list=new ArrayList<>(); 
		list.add(new Order("IphoneX", 8500.99, 12));
		list.add(new Order("MacBookpro", 18500, 10));
		list.add(new Order("IpadAir", 6500, 8));
		double allPrice=list.stream()
				.map((obj)->obj.getPrice()*obj.getAmount())
				.reduce((sum,x)->sum+x).get();
		System.out.println("总花费为："+allPrice);
	}	
}
