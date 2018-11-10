package www.bit.Link;

public interface Link <T>{
	void add(T obj);
	boolean remove(int index);
	boolean set(int index,T obj);
	T get(int index);
	void printLink();
	Object [] toArray();
	int getSize();
	void clear();
	int contains(T obj);
}
