package graphMatrix;

import java.util.ArrayList;
import java.util.LinkedList;

import graphCollections.IDennGraphM;
import graphCollections.Heap;
import graphCollections.IDennHeap;
import graphCollections.Queue;
import graphCollections.StructureStackQueue;
import graphCollections.Tree;
import graphMatrix.DennNodeM;

public class DennGraphM<T> implements IDennGraphM<T> {

	
	private int limit;
	private int[][] matrix;
	private LinkedList<DennNodeM<T>> nodes;
	private int[] parent;
	
	
	public DennGraphM(int nod) {
		matrix = new int[nod][nod];
		nodes = new LinkedList<DennNodeM<T>>();
		limit = 0;
	}
	
	public boolean isEmpty() {
		boolean empty = false;
		if(limit == 0) {
			empty = true;
		}
		return empty;
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}


	/**
	 * @return the matrix
	 */
	public int[][] getMatrix() {
		return matrix;
	}

	/**
	 * @param matrix the matrix to set
	 */
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * @return the nodes
	 */
	public LinkedList<DennNodeM<T>> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(LinkedList<DennNodeM<T>> nodes) {
		this.nodes = nodes;
	}

	@Override
	public void addNodeM(T key) {
		
		DennNodeM<T> node = searchNodeM(key);
		if(node == null) {
			node = new DennNodeM<T>(key);
			nodes.add(node);
			node.setPos(nodes.indexOf(node));
			limit++;
			
		}
		
	}

	@Override
	public void addEdge(T key, T key2, int dis) {
		
		DennNodeM<T> v1 = searchNodeM(key);
		DennNodeM<T> v2 = searchNodeM(key2);
		
		if(v1 == null) {
			addNodeM(key);
			v1 = searchNodeM(key);
		}
		if(v2 == null) {
			addNodeM(key2);
			v2 = searchNodeM(key2);
		}
		
		int pos1 = nodes.indexOf(v1);
		int pos2 = nodes.indexOf(v2);
		
		matrix[pos1][pos2] = dis;
//		matrix[pos2][pos1] = dis; no dirigido
		v1.getAdjacents().add(v2);
//		v2.getAdjacents().add(v1); no dirigido
		
	}

	@Override
	public DennNodeM<T> searchNodeM(T key) {
		
		DennNodeM<T> node = null;
		boolean find = false;
		for(int i = 0; i < nodes.size() && !find; i++) {
			if(nodes.get(i).getElem().equals(key)) {
				node = nodes.get(i);
				find = true;
//				System.out.println("Lo encontré"+ node.getPos());
			}
		}
		return node;
	}

	@Override
	public void deleteNodeM(T key) {
		DennNodeM<T> v = searchNodeM(key);
		if(v != null) {
			int pos = nodes.indexOf(v);
			for(int i = 0; i < limit; i++) {
				matrix[pos][i]= 0;
			}
			for(int i = pos; i < limit; i++) {
				for(int j = pos; j < limit; j++) {
					matrix[i][j] = matrix[i+1][j+1];
				}
			}
			limit--;
		}
		nodes.remove(v);
		
	}

	@Override
	public void deleteEdge(T key, T key2) {
		DennNodeM<T> v1 = searchNodeM(key);
		DennNodeM<T> v2 = searchNodeM(key2);
		if (v1 != null && v2 != null) {
			int pos1 = nodes.indexOf(v1);
			int pos2 = nodes.indexOf(v2);
			matrix[pos1][pos2]= 0;
		}
		
	}

	@Override
	public boolean adjacentNodeM(T key, T key2) {
		boolean y = false;
		
		DennNodeM<T> v1 = searchNodeM(key);
		DennNodeM<T> v2 = searchNodeM(key2);
		if (v1 != null && v2 != null) {
			int pos1 = nodes.indexOf(v1);
			int pos2 = nodes.indexOf(v2);
			if(matrix[pos1][pos2] != 0) {
				y = true;
			}
		}
		
		return y;
	}

	@Override
	public int getDistance(T key, T key2) {
		int dis = 0;
		
		DennNodeM<T> v1 = searchNodeM(key);
		DennNodeM<T> v2 = searchNodeM(key2);
		
		if (v1 != null && v2 != null) {
			int pos1 = nodes.indexOf(v1);
			int pos2 = nodes.indexOf(v2);
			dis = matrix[pos1][pos2];
		}
		return dis;
	}

	
	public int[] dijkstra(T key) {
		
		DennNodeM<T> v1 = searchNodeM(key);
		int origin = nodes.indexOf(v1);
		int[] distance = new int[nodes.size()];
		parent =  new int[nodes.size()];
		boolean[] visit = new boolean[nodes.size()];
		
		for(int i = 0; i < nodes.size(); i++) {
			distance[i] = Integer.MAX_VALUE;
			parent[i] = -1;
			visit[i] = false;
		}
		
		distance[origin] = 0;
		IDennHeap<Integer> queue = new Heap<Integer>(100000);
		queue.insertMin(origin, origin);
		
		
		
		while(!(queue.size()==0)) {
			
//			int u = origin;
			int u = queue.removeMin();
			visit[u] = true;
			for(int i = 0; i < nodes.size(); i++) {
				
				if(matrix[u][i] != 0) {
					if(distance[i]> distance[u]+ matrix[u][i]) {
						distance[i] = distance[u] + matrix[u][i];
						parent[i] = u;
						queue.insertMin(i, i);
					}
				}
			}
		}
		return distance;
	}
	
	public void infinite(int[][] floyd) {
		
		for(int i = 0; i < limit; i++) {
			for(int j = 0; j < limit; j++) {
				if(floyd[i][j] == 0 && i != j) {
					floyd[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
	}

	public int[][] floydWarshall(){
		
		int[][] floyd = new int[nodes.size()][nodes.size()];
		
		for(int i = 0; i < nodes.size(); i++) {
			for(int j = 0; j < nodes.size(); j++) {
				floyd[i][j] = matrix[i][j];
			}
		}
		
		infinite(floyd);
		for(int s = 0; s < limit; s++) {
			for(int i = 0; i < limit; i++) {
				for(int j = 0; j < limit; j++) {
					if(s != i && s != j) {
						long n = (long)floyd[i][s] + (long)floyd[s][j];
						if(floyd[i][j] > n) {
							floyd[i][j] = (int)n;
							
						}
					}
				}
			}
		}
		return floyd;
	}
	
	public Tree<T> BFS(T origin) throws Exception{
		
		Tree<T> bfs = new Tree<T>();
		DennNodeM<T> n = searchNodeM(origin);
		
		boolean[] visit = new boolean[nodes.size()] ;
		
		visit[n.getPos()] = true;
		Queue<Integer> queue = new StructureStackQueue<Integer>();
		bfs.add(nodes.get(n.getPos()).getElem(),null);
		queue.enqueque(n.getPos());
		
		while(!queue.isEmptyQ()) {
			int u = queue.dequeque();
			for(int i = 0; i< nodes.size(); i++) {
				if( !visit[i]) {
					queue.enqueque(i);
					bfs.add(nodes.get(i).getElem(),nodes.get(u).getElem());
					visit[i] = true;
					
				}
			}
			
		}
		
		return bfs;
	}
	
	public ArrayList<T> DFS(T origin) throws Exception {
		boolean[] visit = new boolean[nodes.size()];
		
		ArrayList<T> path = new ArrayList<T>();
		for(int i=0; i<nodes.size(); i++) {
			visit[i]=false;
		}
		
		DennNodeM<T> n = searchNodeM(origin);
		Queue<Integer> queque = new StructureStackQueue<>();
		visit[n.getPos()]=true;
		queque.enqueque(n.getPos());
		path.add(n.getElem());
		
		while(!queque.isEmptyQ()) {
			int u = queque.dequeque();
			for(int i=0; i<nodes.size(); i++) {
				if(!visit[i]) {
					queque.enqueque(i);
					visit[i]=true;
					path.addAll(DFS(nodes.get(i).getElem()));
					
				}
			}
		}
		return path;
	}
	
	
	
}
