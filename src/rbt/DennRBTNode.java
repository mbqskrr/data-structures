package rbt;

import java.util.List;

public class DennRBTNode<A extends Comparable<A>> {

	public static final int BLACK = 1;
	public static final int RED = 0;
	
	private DennRBTNode<A> RChild;
	private DennRBTNode<A> LChild;
	private A elem;
	private int color;
	private DennRBTNode<A> parent;
	protected boolean FLAG;

	
	public DennRBTNode(A elem) {
		 this.elem = elem;
		 color = RED;
		 setRChild(new DennRBTNode<A>());
		 setLChild(new DennRBTNode<A>());
		 parent = null;
		 FLAG = false;
		 
	 }
	 
	 private DennRBTNode() {
		 this.elem = null;
		 color = BLACK;
		 parent = null;
	 }
	 
	 public  DennRBTNode(DennRBTNode<A> node) {
		 this.elem = null;
		 RChild = null;
		 LChild = null;
		 parent = node;
		 color = BLACK;
		 FLAG = true;
		 
	 }
	 
	 
	 public DennRBTNode<A> getParent(){
		 return parent;
	 }
	 
	 public DennRBTNode<A> getUncle(){	
		 
		 if(parent == null || parent.parent == null) {
			 return null;
		 }else {
			 if(parent.parent.isRChild(parent))
				 return parent.parent.LChild.LChild;
			 else
				 return parent.parent.RChild;
		 }
		 
	 }
	 
	 public int getColor() {
		 return color;
	 }
	 
	 public DennRBTNode<A> getRChild(){
		 return RChild;
	 }
	 
	 public DennRBTNode<A> getLChild(){
		 return LChild;
	 }
	 
	 public boolean RChildLeaf() {
		 return RChild.elem == null;
	 }

	 
	 public boolean LChildLeaf() {
		 return LChild.elem == null;
	 }
	 
	 public DennRBTNode<A> getHigher(){
		 return RChildLeaf() ? this : RChild.getHigher();
	 }
	 
	 public DennRBTNode<A> getSmaller(){
		 return LChildLeaf() ? this : LChild.getSmaller();
	 }
	 
	 public void getPreorden(List<A> preorden) {
		 
		 preorden.add(elem);
		 if(!LChildLeaf())
			 LChild.getPreorden(preorden);
		 if(!RChildLeaf())
			 RChild.getPreorden(preorden);
	 }
	 
	 public boolean isLeaf() {
		 return elem == null;
	 }
	 
	 public int getWeight() {
		 return isLeaf() ? 0 : 1 + RChild.getWeight() + LChild.getWeight();
	 }
	 
	 public void getLeafs(List<DennRBTNode<A>> leafs) {
		 
		 if(isLeaf())
			 leafs.add(this);
		 else {
			 if(!RChildLeaf())
				 RChild.getLeafs(leafs);
			 if(!LChildLeaf())
				 LChild.getLeafs(leafs);
		 }
	 }
	 
	 public int getHeight() {
		 
		 if(isLeaf())
			 return 0;
		 int h1 = LChild.getHeight();
		 int h2 = RChild.getHeight();
		 return (h1 >= h2) ? h1 +1 : h2 +1;
	 }

	 
	 

	 
	 public void setRChild(DennRBTNode<A> child) {
		 if(child != null) 
			 child.setParent(this);
		RChild = child;
		 
		 
	 }
	 
	 public void setLChild(DennRBTNode<A> child) {
		 
		 if(child != null) 
			 child.setParent(this);
		 LChild = child;
	 }

	public void setParent(DennRBTNode<A> parent) {
	
		this.parent = parent;	
	}
	
	public boolean isRChild(DennRBTNode<A> node) {
		
		return RChild == node;
	}
	
	 public boolean isLChild(DennRBTNode<A> node) {
		 return LChild == node;
	 }
	 
	 public DennRBTNode<A> getNode(A elem) throws ElementNotExistException{
		 
		 int comp = elem.compareTo(this.elem);
		 if(comp == 0)
			 return this;
		 else if(comp < 0) {
			 if(!LChildLeaf())
				 return LChild.getNode(elem);
			 else
				 throw new ElementNotExistException("El elemento buscado no existe");
		 }else {
			 if(!RChildLeaf())
				 return RChild.getNode(elem);
			 else
				 throw new ElementNotExistException("El element buscado no existe");
		 }
	 }
	 
	 public A getInfoNode() {
		 return elem;
	 }
	 
	 
	 public boolean exist(A a) {
		 
		try {
			getNode(a);
			return true;
		} catch (ElementNotExistException e) {
			// TODO Auto-generated catch block
			return false;
		}
	
	 }
	 
	 public boolean RChildBlack() {
		 return RChild.color == BLACK;
	 }
	 
	 public boolean LChildBlack() {
		 return LChild.color == BLACK;
	 }
	 
	 public boolean BlackChilds() {
		 return RChildBlack() && LChildBlack();
	 }
	 
	 public DennRBTNode<A> getBrother(){
		 if(parent == null)
			 return null;
		 else
			 return parent.isRChild(this) ? parent.LChild : parent.RChild;
	 }
	 
	 
	 public void setColor(int color) {
		 this.color = color;
	 }
	 
//	 public boolean isDBlack() {
//		 return color == DBLACK;
//	 }
	 
	 public void setElement(DennRBTNode<A> node) {
		 
		 if(node.elem != null) {
			 
			 A aux = elem;
			 elem = node.elem;
			 node.elem = aux;
		 }else {
			 elem = null;
			 color = BLACK;
			 RChild = null;
			 LChild = null;
		 }
		 
	 }
	 
	 public DennRBTNode<A> sucesor(DennRBTNode<A> x){
		 if(!x.RChild.FLAG)
			 return x.RChild.getSmaller();
		 	DennRBTNode<A> y = x.parent;
		 while(y != null && x == y.RChild) {
			 x = y;
			 y = y.parent;
			
		 }
		 return y;
	 }
}
