package list;
public class DennNode <T>{

	/** 
	 * Nodo usado en una lista enlazada
	 */
  private T element;
  private DennNode<T> nodeNext;
  private DennNode<T> nodePrevious;
  
  
  public DennNode(T element){
    this.element = element;
//    this.nodeNext = nodeNext;
  }

  public void setNodeNext(DennNode<T> nodeNext){
    this.nodeNext = nodeNext;
  }

  public DennNode<T> getNodeNext(){
    return nodeNext;
  }

  public void setElement(T element){
    this.element = element;
  }

  public T getElement(){
    return element;
  }

public DennNode<T> getNodePrevious() {
	return nodePrevious;
}

public void setNodePrevious(DennNode<T> nodePrevious) {
	this.nodePrevious = nodePrevious;
}
  
  
}