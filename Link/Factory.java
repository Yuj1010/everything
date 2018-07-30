package www.bit.Link;

public class Factory {
	private Factory() {}
	public static <T> Link<T> getLinkInstance() {
		return new LinkImpl<T>();
	}
}
