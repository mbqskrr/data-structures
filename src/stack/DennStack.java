package stack;


public class DennStack<T> implements IDennStack<T> {

	private DennNode<T> firstNode;
	private DennNode<T> lastNode;
	private int size;
	
	public DennStack() {
		firstNode = null;
		lastNode = null;
		size = 0;
	}
	
	@Override
	public void dennPush(T t) {
		// TODO Auto-generated method stub
		DennNode<T> newNode = new DennNode<T>(t);
		if (firstNode == null) {
			firstNode = newNode;
			lastNode = newNode;
			newNode.setNodeNext(newNode);
			newNode.setNodePrevious(newNode);
			size++;
		} else {
			lastNode.setNodeNext(newNode);
			newNode.setNodePrevious(lastNode);
			newNode.setNodeNext(null);
			lastNode = newNode;
			size++;
		}
	}

	@Override
	public T dennPeek() {
		// TODO Auto-generated method stub
		T object = null;
		if(firstNode.getNodeNext() == null) {
			object = firstNode.getElement();
		}else {
			object = lastNode.getElement();
		}
		return object;
	}

	@Override
	public T dennPop() {
		// TODO Auto-generated method stub
		T object = null;
		if(firstNode.getNodeNext() == null) {
			object = firstNode.getElement();
			firstNode = null;
			size--;
		}
		else {
			object = lastNode.getElement();
			lastNode.getNodePrevious().setNodeNext(null);
			lastNode = lastNode.getNodePrevious();
			size--;
		}
		
		return object;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return firstNode == null;
	}

	@Override
	public int dennSize() {
		// TODO Auto-generated method stub
		return size;
	}

	public DennNode<T> getFirstNode() {
		return firstNode;
	}

	public void setFirstNode(DennNode<T> firstNode) {
		this.firstNode = firstNode;
	}

	public DennNode<T> getLastNode() {
		return lastNode;
	}

	public void setLastNode(DennNode<T> lastNode) {
		this.lastNode = lastNode;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	

}
