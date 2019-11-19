package rbt;

public interface IDennRBT<A extends Comparable<A>> {

	
	public DennRBTNode<A> getRoot();
	public void insert_balance(DennRBTNode<A> z);
	public void leftRotate(DennRBTNode<A> x);
	public void rightRotate(DennRBTNode<A> x);
	public void insertRB(A elem);
	public void clean();
	public void deleteSentinels(DennRBTNode<A> x);
	public void createSentinel(DennRBTNode<A> x);
	public void deleteRB(A elem);
	public void fixedUp(DennRBTNode<A> x);
	public void inOrder(DennRBTNode<A> r);
	public void preOrder(DennRBTNode<A> r);
	public void postOrder(DennRBTNode<A> r);
}
