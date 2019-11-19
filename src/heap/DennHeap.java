package heap;

public class DennHeap<P, T> implements IDennHeap<P, T> {

	public static final int FRONT = 1;
	private DennItem<P, T>[] heap;	
	private int sizeM;
	private int maxSize;
	
	
	public DennHeap(int maxSize) {
		this.maxSize =  maxSize;
		this.sizeM = 0;
		heap = new DennItem[(maxSize+1)];
	}
	@Override
	public int parent(int pos) {
		// TODO Auto-generated method stub
		return (pos / 2);
	}

	@Override
	public int leftChild(int pos) {
		// TODO Auto-generated method stub
		return (pos * 2);
	}

	@Override
	public int rigthChild(int pos) {
		// TODO Auto-generated method stub
		return (pos * 2) + 1;
	}

	@Override
	public void minHeapify(int index) {
		// TODO Auto-generated method stub
		while(hasLeftChild(index)) {
			
			int largest = leftChild(index);
			
			if(hasRigthChild(index) && heap[leftChild(index)].getPriority() < heap[rigthChild(index)].getPriority() ) {
				largest = rigthChild(index);
			}
			if(heap[index].getPriority() < heap[largest].getPriority()) {
				swap(index, largest);
				minHeapify(largest);
			}
			else break;
		}
		
	}

	@Override
	public void maxHeapify(int index) {
		// TODO Auto-generated method stub
		while(hasLeftChild(index)) {
			
			int smallest = leftChild(index);
			
			if(hasRigthChild(index)&& heap[leftChild(index)].getPriority() > heap[rigthChild(index)].getPriority()) {
				smallest = rigthChild(index);
			}
			if(heap[index].getPriority() > heap[smallest].getPriority()) {
				swap(index, smallest);
				maxHeapify(smallest);
			}
			else break;
			
		}
	}

	@Override
	public void maxHeap() {
		// TODO Auto-generated method stub
		int index = sizeM;
		while(parent(index) != 0 && (heap[parent(index)].getPriority() < heap[index].getPriority())){
			swap(index, parent(index));
			index = parent(index);
		}
	}

	@Override
	public void minHeap() {
		// TODO Auto-generated method stub
		int index = sizeM;
		while(parent(index) != 0 && (heap[parent(index)].getPriority() > heap[index].getPriority())){
			swap(index, parent(index));
			index = parent(index);	
		}
	}

	@Override
	public void insertMax(P priory, T element) {
		// TODO Auto-generated method stub
		DennItem<P, T> add = new DennItem<P, T>(priory, element);
		setSizeM(sizeM+1);
		heap[sizeM] = add;
		maxHeap();
		
	}

	@Override
	public void insertMin(P priory, T element) {
		// TODO Auto-generated method stub
		DennItem<P, T> add = new DennItem<P, T>(priory, element);
		setSizeM(sizeM+1);
		heap[sizeM] = add;
		minHeap();
	}

	@Override
	public void swap(int fPos, int sPos) {
		// TODO Auto-generated method stub
		DennItem<P,T> temp = heap[fPos];
		heap[fPos] = heap[sPos];
		heap[sPos] = temp;
	}

	@Override
	public T max() {
		// TODO Auto-generated method stub
		return heap[FRONT].getValue();

	}

	@Override
	public T removeMin() {
		// TODO Auto-generated method stub
		DennItem<P, T> remove = heap[FRONT];
		heap[FRONT] = heap[sizeM];
		heap[sizeM] = null;
		setSizeM(sizeM-1);
		minHeapify(FRONT);
		return remove.getValue();
	}

	@Override
	public T removeMax() {
		// TODO Auto-generated method stub
		DennItem<P, T> remove = heap[FRONT];
		heap[FRONT] = heap[sizeM];
		heap[sizeM] = null;
		setSizeM(sizeM-1);
		maxHeapify(FRONT);
		return remove.getValue();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return sizeM;
	}

	@Override
	public boolean isLeaf(int pos) {
		// TODO Auto-generated method stub
		if(pos >= (sizeM/2)&& pos<= sizeM) {
			return true;
		}
		return false;
	}
	
	private boolean hasLeftChild(int i) {
		return leftChild(i) <= sizeM;
	}
	
	private boolean hasRigthChild(int i) {
		return rigthChild(i) <= sizeM;
	}
	
	public DennItem<P, T>[] getHeap() {
		return heap;
	}
	public void setHeap(DennItem<P, T>[] heap) {
		this.heap = heap;
	}
	public int getSizeM() {
		return sizeM;
	}
	public void setSizeM(int sizeM) {
		this.sizeM = sizeM;
	}
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
	

}
