package rbt;

public class DennRBTree<A extends Comparable<A>> implements IDennRBT<A> {

	
	public static final int BLACK = 1;
	public static final int RED = 0;
	
	
	private DennRBTNode<A> root;
	
	private DennRBTNode<A> nodex = null;
	private DennRBTNode<A> nodey = null;
	private DennRBTNode<A> nodez = null;
	private DennRBTNode<A> nodew = null;
	
	public DennRBTree() {
		root = null;
	}
	
	@Override
	public DennRBTNode<A> getRoot() {
		return root;
	}

	@Override
	public void insert_balance(DennRBTNode<A> z) {
		
		DennRBTNode<A> y = null;
		DennRBTNode<A> x = root;
		while(x != null) {
			y = x;
			if(z.getInfoNode().compareTo(x.getInfoNode()) < 0)
				x = x.getLChild();
			else
				x = x.getRChild();
		}
		
		z.setParent(y);
		if(y == null)
			root = z;
		else
			if(z.getInfoNode().compareTo(y.getInfoNode()) < 0)
				y.setLChild(z);
			else
				y.setRChild(z);
		z.setLChild(null);
		z.setRChild(null);
		z.setColor(RED);
		
	}
	
private DennRBTNode<A> deleteRB(DennRBTNode<A> z){
		
		nodez = z;
		DennRBTNode<A> y = null;
		DennRBTNode<A> x = null;
		
		if(z.getLChild().FLAG || z.getRChild().FLAG) {
			
			y = z;
		}else {
			
			y = z.sucesor(z);
		}
		nodey = y;
		
		if(!y.getLChild().FLAG) {
			
			x = y.getLChild();
		}else {
			x = y.getRChild();
		}
		
		nodex = x;
		x.setParent(y.getParent());
		
		if(y.getParent() == null) {
			root = x;
		}else {
			
			if(y == y.getParent().getLChild()) {
				y.getParent().setLChild(x);
			}else {
				y.getParent().setRChild(x);
			}
		}
		
		nodex= x;
		
		if(y != z) {
			
			z.setElement(y);
		}
		if(y.getColor() == BLACK) {
			
			fixedUp(x);
		}
		
		nodex = null;
		nodey = null;
		nodez = null;
		nodew = null;
		return y;
	}

	@Override
	public void leftRotate(DennRBTNode<A> x) {
		// TODO Auto-generated method stub
		DennRBTNode<A> y = x.getRChild();
		x.setRChild(y.getLChild());
		if(y.getLChild() != null) 
			y.getLChild().setParent(x);
			y.setParent(x.getParent());
		if(x.getParent() == null)
			root = y;
		else
			if(x == x.getParent().getLChild())
				x.getParent().setLChild(y);
			else
				x.getParent().setRChild(y);
		
		y.setLChild(x);
		x.setParent(y);
	}

	@Override
	public void rightRotate(DennRBTNode<A> x) {
		// TODO Auto-generated method stub
		DennRBTNode<A> y = x.getLChild();
		x.setLChild(y.getRChild());
		if(y.getRChild() != null)
			y.getRChild().setParent(x);
			y.setParent(x.getParent());
		if(x.getParent() == null)
			root = y;
		else
			if(x == x.getParent().getRChild())
				x.getParent().setRChild(y);
			else
				x.getParent().setLChild(y);
		y.setRChild(x);
		x.setParent(y);
	}

	@Override
	public void insertRB(A elem) {
		// TODO Auto-generated method stub
		
		deleteSentinels(root);
		
		DennRBTNode<A> x = new DennRBTNode<A>(elem);
		insert_balance(x);
		while(x != root && x.getParent().getColor() == RED) {
			
			boolean nodeYRed = false;
			if(x.getParent() == x.getParent().getParent().getLChild()) {
				
				DennRBTNode<A> y = x.getParent().getParent().getRChild();
				if(y != null) {
					if(y.getColor() == RED) {
						//caso1
						x.getParent().setColor(BLACK);
						y.setColor(BLACK);
						x.getParent().getParent().setColor(RED);
						x = x.getParent().getParent();
						nodeYRed = true;
					}
				}
				
				if( !nodeYRed) {
					
					if( x == x.getParent().getRChild()) {
						
						//caso2
						x = x.getParent();
						leftRotate(x);
					}
					
					//caso 3
					x.getParent().setColor(BLACK);
					x.getParent().getParent().setColor(RED);
					rightRotate(x.getParent().getParent());
				}
			}
			
			else {
				if(x.getParent() == x.getParent().getParent().getRChild()) {
					
					DennRBTNode<A> y = x.getParent().getParent().getLChild();
					if(y != null) {
						
						if(y.getColor() == RED) {
							//caso1
							x.getParent().setColor(BLACK);
							y.setColor(BLACK);
							x .getParent().getParent().setColor(RED);
							x = x.getParent().getParent();
							nodeYRed = true;
						}
					}
					
					if(!nodeYRed) {
						
						//caso2
						if( x == x.getParent().getLChild()) {
							x = x.getParent();
							rightRotate(x);
						}
						
						//caso 3
						x.getParent().setColor(BLACK);
						x.getParent().getParent().setColor(RED);
						leftRotate(x.getParent().getParent());
					}
				}
			}
			
		}
		
		root.setColor(BLACK);
		createSentinel(root);
		
	}

	

	@Override
	public void deleteSentinels(DennRBTNode<A> x) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteRB(A elem) {
		// TODO Auto-generated method stub
		
		try {
			DennRBTNode<A> z = root.getNode(elem);

			DennRBTNode<A> p = deleteRB(z);
			p = null;
			
			if(root.FLAG) {
				root.setParent(null);
				root = null;
			}
			
			deleteSentinels(root);
			createSentinel(root);
		} catch (ElementNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void fixedUp(DennRBTNode<A> x) {
		// TODO Auto-generated method stub
		
		nodez = nodey = null;
		
		while(x != root && x.getColor() == BLACK) {
			
			nodex = x;
			if(x == x.getParent().getLChild()) {
				
				DennRBTNode<A> w = x.getParent().getRChild();
				nodew = w;
				//caso 1
				if(w.getColor() == RED) {
					w.setColor(BLACK);
					x.getParent().setColor(RED);
					leftRotate(x.getParent());
					w = x.getParent().getRChild();
				}
				
				//caso2
				if(w.BlackChilds()) {
					
					w.setColor(RED);
					x = x.getParent();
				}else {
					
					//caso3
					if(w.RChildBlack()) {
						
						w.getLChild().setColor(BLACK);
						w.setColor(RED);
						rightRotate(w);
						w = x.getParent().getRChild();
						nodew = w;
						
					}
					
					//caso4
					
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(BLACK);
					w.getRChild().setColor(BLACK);
					leftRotate(x.getParent());
					x = root;
					nodex = x;
					
				}
			}
			else {
				
				if(x == x.getParent().getRChild()) {
					
					DennRBTNode<A> w = x.getParent().getLChild();
					nodew = w;
					//caso1
					if(w.getColor() == RED) {
						
						w.setColor(BLACK);
						x.getParent().setColor(RED);
						rightRotate(x.getParent());
						w = x.getParent().getLChild();
					}
					//caso2
					
					if(w.BlackChilds()) {
						w.setColor(RED);
						x = x.getParent();
					}else {
						//caso3
						
						if(w.LChildBlack()) {
							w.getRChild().setColor(BLACK);
							w.setColor(RED);
							leftRotate(w);
							w = x.getParent().getLChild();
							nodew = w;
						}
						
						//caso4
						
						w.setColor(x.getParent().getColor());
						x.getParent().setColor(BLACK);
						w.getLChild().setColor(BLACK);
						rightRotate(x.getParent());
						x = root;
						nodex = x;
					}
					
				}
			}
		}
		
		x.setColor(BLACK);
		nodex = x;
	}

	@Override
	public void inOrder(DennRBTNode<A> r) {
		// TODO Auto-generated method stub
		
		if(r != null) {
			inOrder(r.getLChild());
			System.out.print(r.getInfoNode() + ", ");
			inOrder(r.getRChild());
			
		}
		
	}

	@Override
	public void preOrder(DennRBTNode<A> r) {
		// TODO Auto-generated method stub
		if(r != null) {
			System.out.print(r.getInfoNode() + ", ");
			preOrder(r.getLChild());
			preOrder(r.getRChild());
			
		}
	}

	@Override
	public void postOrder(DennRBTNode<A> r) {
		// TODO Auto-generated method stub
		
		if(r != null) {
			postOrder(r.getLChild());
			postOrder(r.getRChild());
			System.out.print(r.getInfoNode() + ", ");
			
		}
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		root = null;
	}

	@Override
	public void createSentinel(DennRBTNode<A> x) {
		// TODO Auto-generated method stub
		
		if(x.getLChild() == null) {
			DennRBTNode<A> sentinel = new DennRBTNode<A>(x);
			x.setLChild(sentinel);
		}else {
			
			createSentinel(x.getLChild());
		}
		if(x.getRChild() == null) {
			DennRBTNode<A> sentinel = new DennRBTNode<A>(x);
			x.setRChild(sentinel);
		}else {
			createSentinel(x.getRChild());
		}
		
	}

}
