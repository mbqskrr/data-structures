package list;


public class DennList<T> implements IDennList<T>{

	private DennNode<T> firstNode;
	private DennNode<T> lastNode;
	int size = 0;
	
	public DennList() {
		super();
		size = 0;
	}
	
	@Override
	public void add(T element) {
		// TODO Auto-generated method stub
		DennNode<T> linnyNew = new DennNode<T>(element);

		if (firstNode == null) {
			firstNode = linnyNew;
			lastNode = linnyNew;
			linnyNew.setNodeNext(linnyNew);
			linnyNew.setNodePrevious(linnyNew);
			size++;
		} else {
			
			lastNode.setNodeNext(linnyNew);
			linnyNew.setNodePrevious(lastNode);
			linnyNew.setNodeNext(null);
			lastNode = linnyNew;
			size++;
		}
	}

	@Override
	public T getElement(int i) {
		// TODO Auto-generated method stub
		T element = null;
		DennNode<T> node = firstNode;
		int number = 0;
		boolean h = false;

		if (i < getSize()) {
			while (node != null && h == false ) {
				if (number == i) {
					element = node.getElement();
					h = true;
				} else {
					number++;
					node = node.getNodeNext();
				}
			}
		}
		return element;
	}

	@Override
	public void deleteElement(T element) {
		// TODO Auto-generated method stub
		if(firstNode != null) {
			
			if (firstNode.getElement() == element) {
				firstNode = firstNode.getNodeNext();
				size--;
			} else {
				DennNode<T> delete = firstNode;
				DennNode<T> deleteNext = delete.getNodeNext();

				boolean stop = false;
				while (stop == false) {
					if (deleteNext.getElement() == element) {
						delete.setNodeNext(deleteNext.getNodeNext());
						size--;
						stop = true;
					} else {
						delete = delete.getNodeNext();
						deleteNext = delete.getNodeNext();
					}
				}
			}
		}
	}

	@Override
	public void delete(int i) {
		// TODO Auto-generated method stub
		DennNode<T> node = firstNode;
		DennNode<T> nodeNX = node.getNodeNext();
		int number = 1;
		boolean h = false;

		if (i < getSize()) {
			
			if(number == 0) {
				firstNode.getNodeNext().setNodePrevious(null);
				firstNode = firstNode.getNodeNext();
				size--;
			}
			else {
				while(nodeNX != null && h == false) {
					if(number == i) {
						
						
						if(nodeNX.getNodeNext() != null) {
							node.setNodeNext(nodeNX.getNodeNext());
							nodeNX.getNodeNext().setNodePrevious(node);
							h = true;
							size--;
						}else {
							node.setNodeNext(null);
							h = true;
							size--;
						}
					}
					else {
						node = node.getNodeNext();
						nodeNX = node.getNodeNext();
						number++;
					}
				}
			}
		}	
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return firstNode == null;
	}

	@Override
	public int getSize() {
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
	
	

}
