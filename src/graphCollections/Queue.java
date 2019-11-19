package graphCollections;

public interface Queue<A> {
	
	public boolean isEmptyQ();
	
	public void enqueque(A a);
	
	public A front() throws Exception;
	
	public A dequeque() throws Exception;
	
	public int sizeQ();
}
