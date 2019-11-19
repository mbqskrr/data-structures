package heap;

public interface IDennHeap<P, T> {
	
	public int parent(int pos);
	public int leftChild(int pos);
	public int rigthChild(int pos);
	public void minHeapify(int index);
	public void maxHeapify(int index);
	public void maxHeap();
	public void minHeap();
	public void insertMax(P priory, T element);
	public void insertMin(P priory, T element);
	public void swap(int fPos, int sPos);
	public T max();
	public T removeMin();
	public T removeMax();
	public int size();
	public boolean isLeaf(int pos);
}
