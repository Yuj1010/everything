package www.bit.Link;


public class LinkImpl<T> implements Link<T>{
	private Node<T> first;
	private Node<T> last;
	private int size=0;
	
	private class Node<T>{
		private Node<T> prev;
		private T data;
		private Node<T> next;
		public Node(Node<T> prev, T data, Node<T> next) {
			super();
			this.prev = prev;
			this.data = data;
			this.next = next;
		}
		
	}
	
	@Override
	public void add(T obj) {
		Node<T> temp=this.last;
		Node<T> newNode=new Node<T>(temp, obj, null);
		this.last=newNode;
		if(this.first==null) {
			this.first=newNode;
		}else {
			temp.next=newNode;
		}
		this.size++;
	}

	@Override
	public boolean remove(int index) {
		if(!isLinkElement(index)) {
			return false;
		}else {
			Node<T> node=node(index);
			if(node==this.first) {
				if(node==this.last) {
					node=null;
					this.first=this.last=null;
					this.size--;
				}else {
					this.first=node.next;
					this.first.prev.next=null;
					this.first.prev=null;
					this.size--;
				}
				
			}
			else if(node==this.last){
				this.last=node.prev;
				this.last.next.prev=null;
				this.last.next=null;
				this.size--;
			}
			else {
				node.next.prev=node.prev;
				node.prev.next=node.next;
				node.next=node.prev=null;
				this.size--;
			}
		}
		return true;
	}

	@Override
	public boolean set(int index, T obj) {
		if(!isLinkElement(index)) {
			return false;
		}
		Node<T> node=node(index);
		node.data=obj;
		return true;	
	}

	@Override
	public T get(int index) {
		if(!isLinkElement(index)) {
			return null;
		}
		return node(index).data;
	}

	@Override
	public void printLink() {
		Object [] result=this.toArray();
		for(Object object:result) {
			System.out.println(object);
		}
		
	}

	@Override
	public Object[] toArray() {
		Object [] result=new Object [size];
		int i=0;
		for(Node<T> temp=this.first;temp!=null;temp=temp.next) {
			result[i++]=temp.data;
		}
		return result;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public void clear() {
		for(Node<T> node=this.first;node!=null;) {
			Node<T> temp=node.next;
			node.prev=node.next=null;
			node=temp;
		}
		this.first=this.last=null;
	}

	@Override
	public int contains(T t) {
		if(t==null) {
			int index=0;
			for(Node<T> node=this.first;node!=null;node=node.next) {
				if(node.data==null) {
					return index;
				}
				index++;
			}
			return -1;
		}else {
			int index=0;
			for(Node<T> node=this.first;node!=null;node=node.next) {
				if(t.equals(node.data)) {
					return index;
				}
				index++;
			}
		}
		return -1;
	}
	private Node<T> node (int index) {
		if(index<(size>>1)) {
			Node<T> result=this.first;
			for(int i=0;i<index;i++) {
				result=result.next;
			}
			return result;
		}else {
			Node<T> result=this.last;
			for(int i=size-1;i>index;i--) {
				result=result.prev;
			}
			return result;
		}
		
	}
	private boolean isLinkElement(int index) {
		return index>=0&&index<size;
	}
}
