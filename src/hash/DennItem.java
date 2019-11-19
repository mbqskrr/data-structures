package hash;


public class DennItem<P, T> {

	private P key;
	private T value;
	private DennItem<P, T> next;
	private DennItem<P, T> previous;
	private int priority;
	
	public DennItem(P key, T value) {
		this.key = key;
		this.value = value;
		next = null;
		previous = null;
	}
	
	public P getKey() {
		return key;
	}
	public void setKey(P key) {
		this.key = key;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public DennItem<P, T> getNext() {
		return next;
	}
	public void setNext(DennItem<P, T> next) {
		this.next = next;
	}
	public DennItem<P, T> getPrevious() {
		return previous;
	}
	public void setPrevious(DennItem<P,T> previous) {
		this.previous = previous;
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
