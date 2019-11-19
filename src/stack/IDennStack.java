package stack;

public interface IDennStack<T>{

	public void dennPush(T t);
	public T dennPeek();
	public T dennPop();
	public boolean isEmpty();
	public int dennSize();
	
}
