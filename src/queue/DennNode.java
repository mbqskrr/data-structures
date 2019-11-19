package queue;
public class DennNode <T>{

  private T element;
  private DennNode<T> nodeNext;

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
}