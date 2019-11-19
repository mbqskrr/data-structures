package queue;


public class DennQueue<T> implements IDennQueue{

  private DennNode<T> firstNode;

  public DennQueue(){
  }

  @Override
  public void offerDenn(Object element) {
	 
	  DennNode<T> dn = new DennNode(element);
	  
	  if(firstNode == null){
		  firstNode = dn;
	  }else{
	      DennNode<T> tmp = firstNode;
	      while(tmp.getNodeNext() != null){
	        tmp = tmp.getNodeNext();
	      }
	      tmp.setNodeNext(dn);
	     
	    }
  }
  
  @Override
  public boolean isEmptyDenn() {
	  if(firstNode == null){
	      return true;
	    }else{
	      return false;
	    }
	
  }

  @Override
  public T peekDenn() {
	  return firstNode.getElement();
  }

  @Override
  public T pollDenn() {
	
	 T tmp = peekDenn();
	 firstNode = firstNode.getNodeNext();
	 return tmp;
  }


  public static void main(String[] args) {
	  DennQueue<Integer> n = new DennQueue<>();
	  n.offerDenn(6);
	  n.offerDenn(3);
	  n.offerDenn(10);
	  System.out.println(n.peekDenn());
	  System.out.println(n.pollDenn());
	  System.out.println(n.isEmptyDenn());
	  System.out.println(n.pollDenn());
	  System.out.println(n.pollDenn());
	  System.out.println(n.isEmptyDenn());

  }
}