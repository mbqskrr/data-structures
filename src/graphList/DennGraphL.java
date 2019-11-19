package graphList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

import graphCollections.IDennGraphL;
import graphCollections.IDennNodeL;
import graphCollections.StructureStackQueue;
import graphCollections.Stack;
import graphCollections.Queue;
import graphCollections.NodeAdyacentWeight;
import graphCollections.DisjointSets;

public class DennGraphL<T> implements IDennGraphL<T> {
	
private HashMap<T, DennNodeL<T>> nodes;

	int maxNodes;
	int totalNodes;
	
	public DennGraphL(int maxNodes) {
		nodes = new HashMap<>();
		this.maxNodes = maxNodes;
		totalNodes = 0;
	}

	public HashMap<T, DennNodeL<T>> getNodes(){
		return nodes;
	}
	
	@Override
	public void addNode(T node) throws Exception {
		DennNodeL<T> n = new DennNodeL<T>(node);
		if(nodes.get(node) != null) {
			throw new Exception("Nodo ya existente");
		}
		if(totalNodes == maxNodes) {
			throw new Exception("Número máximo de nodos alcanzados");
			
		}
		nodes.put(node, n);
		totalNodes++;
		
	}

	@Override
	public void addEdge(T node1, T node2, double distance) throws Exception {
		DennNodeL<T> n1 = nodes.get(node1);
		DennNodeL<T> n2 = nodes.get(node2);
		if(n1 == null || n2 == null) {
			throw new Exception("Uno de los nodos no existe");
		}
		n1.addAdjacents(n2);
		n1.addDistance(n2, distance);
		
//		n2.addAdjacents(n1);
//		n2.addDistance(n1, distance);
		
	}

	@Override
	public int BFS(T Norigin) throws Exception {
		for(T na : nodes.keySet()) {
			nodes.get(na).setVisit(false);
		}
		int find = 0;
		DennNodeL<T> act = nodes.get(Norigin);
		if(act == null) {
			throw new Exception("Nodo existe el nodo");
		}
		
		Queue<DennNodeL<T>> queque = new StructureStackQueue<DennNodeL<T>>();
		queque.enqueque(act);
		find++;
		act.setVisit(true);
		while(!queque.isEmptyQ()) {
			DennNodeL<T> n = queque.dequeque();
			ArrayList<IDennNodeL<T>> adjacents = n.getAdjacents();
			for(int i = 0; i < adjacents.size(); i++) {
				if(!adjacents.get(i).isVisit()) {
					adjacents.get(i).setVisit(true);
					find++;
					adjacents.get(i).setParent(n);
					queque.enqueque((DennNodeL<T>)adjacents.get(i));
				}
			}
			n.setVisit(true);
		}
		
		return find;
	}

	@Override
	public int BFS() {
		int bfs;
		Set<T> keys = nodes.keySet();
		try {
			bfs = BFS(keys.iterator().next());
		}catch(Exception e) {
			bfs = 0;
		}
		return bfs;
	}

	@Override
	public void DFS() throws Exception {
	for(T actual : nodes.keySet()) {
		nodes.get(actual).setVisit(true);
	}
	
	for(T a: nodes.keySet()) {
		DennNodeL<T> act = nodes.get(a);
		if(!act.isVisit()) {
			Stack<DennNodeL<T>> stack = new StructureStackQueue<DennNodeL<T>>();
			stack.push(act);
			while(!stack.isEmpty()) {
				DennNodeL<T> actual = stack.pop();
				if(!actual.isVisit()) {
					actual.setVisit(true);
					ArrayList<IDennNodeL<T>> adjacents = actual.getAdjacents();
					for(int i = 0; i < adjacents.size(); i++) {
						DennNodeL<T> add = (DennNodeL<T>) adjacents.get(i);
						if(!add.isVisit()) {
							stack.push(add);
							add.setParent(actual);
						}
					}
				}
			}
		}
	}
		
	}
	
	
	public DennNodeL<T> getNode(T elem){
		return nodes.get(elem);
	}
	

	@Override
	public IDennGraphL<T> prim() throws Exception {
		
		int cant = nodes.size();
		int cantReal = BFS();
		if(cant != cantReal)
			throw new Exception("Grafo no conexo");
		PriorityQueue<NodeAdyacentWeight<DennNodeL<T>>> queue = new PriorityQueue<>();
		DennGraphL<T> r = new DennGraphL<T>(maxNodes);
		HashMap<T, DennNodeL<T>> newNodes = new HashMap<>();
		for(T na : nodes.keySet()) {
			DennNodeL<T> actual = nodes.get(na);
			T elemActual = actual.getElem();
			actual.setVisit(false);
			r.addNode(elemActual);
			DennNodeL<T> n = r.getNode(elemActual);
			newNodes.put(elemActual, n);
			ArrayList<IDennNodeL<T>> adjacents = actual.getAdjacents();
			for(int i = 0; i < adjacents.size(); i++) {
				queue.add(new NodeAdyacentWeight<DennNodeL<T>>(actual,actual.getDistanceAdjacent((DennNodeL<T>)adjacents.get(i)), (DennNodeL<T>)adjacents.get(i)));
				
			}	
		}
		
		NodeAdyacentWeight<DennNodeL<T>> lowEdge = queue.poll();
		DennNodeL<T> actual = lowEdge.getNode();
		queue = new PriorityQueue<>();
		queue.add(lowEdge);
		
		
		while(!queue.isEmpty()) {
			actual.setVisit(true);
			ArrayList<IDennNodeL<T>> adjacents =actual.getAdjacents();
			for(int i = 0 ; i < adjacents.size(); i++) {
				DennNodeL<T>  adjcentActual= (DennNodeL<T>) adjacents.get(i);
				if(!adjcentActual.isVisit()) {
					NodeAdyacentWeight<DennNodeL<T>> newEdge=  new NodeAdyacentWeight<DennNodeL<T>>(actual, actual.getDistanceAdjacent(adjcentActual), adjcentActual);
					queue.add(newEdge);
				}
				
			}
			
			
			boolean add = false;
			while(!add && queue.isEmpty()) {
				NodeAdyacentWeight<DennNodeL<T>> nextEdge = queue.poll();
				if(!nextEdge.getAdjacent().isVisit()) {
					r.addEdge(nextEdge.getNode().getElem(), nextEdge.getAdjacent().getElem(), nextEdge.getDistance());
					add = true;
					actual = nextEdge.getAdjacent();
				}
			}
		
		}

		return r;
	}

	@Override
	public DennGraphL<T> Kruskal() throws Exception {
		// TODO Auto-generated method stub
		
		DennGraphL<T> gOut = new  DennGraphL<T>(maxNodes);
		DisjointSets set = new DisjointSets(maxNodes);
		
		for(int i = 0; i < totalNodes; i++) {
			try {
				gOut.addNode(nodes.get(i).getElem());
				nodes.get(i).setVisit(false);
			}catch(Exception e){
				
				e.printStackTrace();
			}
		}
		
		ArrayList<NodeAdyacentWeight<DennNodeL<T>>> edges = new ArrayList<>();
		
		for(T key : nodes.keySet()) {
			DennNodeL<T> actual = new DennNodeL<>(key);
		}
		
		
		Collections.sort(edges);
		
		for(int k = 0; k < edges.size(); k++) {
			NodeAdyacentWeight<DennNodeL<T>> edge = edges.get(k);
			DennNodeL<T> i = edge.getNode();
			DennNodeL<T> J = edge.getAdjacent();
		}
		return gOut;
	}

	@Override
	public DennWeightList<T> Dijkstra(T node1, T node2) throws Exception {
	
		HashMap<DennNodeL<T>, Double> l = new HashMap<>();
		HashMap<DennNodeL<T>, Double> s = new HashMap<>();
		
		for(T na : nodes.keySet()) {
			l.put(nodes.get(na), Double.MAX_VALUE);
		}
		
		DennNodeL<T>  n1 = nodes.get(node1);
		DennNodeL<T>  n2 = nodes.get(node2);
		if(n1 == null || n2 == null) {
			throw new Exception("Uno de los nodos no existe");
		}
		l.put(n1, 0.0);
		PriorityQueue<DennNodeL<T>> heap = new PriorityQueue<>();
		heap.add(n1);
		boolean find = false;
		while(s.get(n2) == null && !heap.isEmpty()) {
			DennNodeL<T> actual = heap.poll();
			HashMap<IDennNodeL<T>, Double> actualDistances = actual.getDistances();
			double lActual = l.get(actual);
			
			s.put(actual, 0.0);
			if(actual == n2){
				find = true;
			}
			
			ArrayList<IDennNodeL<T>> adjacents = actual.getAdjacents();
			for(int i = 0; i < adjacents.size(); i++) {
				DennNodeL<T> actualAdjacent = (DennNodeL<T>) adjacents.get(i);
				Double newActualDistances = actualDistances.get(actualAdjacent);
				if(newActualDistances + lActual < l.get(actualAdjacent)) {
					actualAdjacent.setParent(actual);
					actualAdjacent.setDistancePrevPath(newActualDistances + lActual);
					l.put(actualAdjacent, newActualDistances + lActual);
					heap.add(actualAdjacent);
				}
			}
			
			
			
		}
		
		if(!find)
			throw new Exception("Imposible llegar del nodo 1 al nodo 2");
		LinkedList<T> path = new LinkedList<T>();
		DennNodeL<T> actual = n2;
		while(actual != n1) {
			T elem = actual.getElem();
			path.addFirst(elem);
			actual = (DennNodeL<T>) actual.getParent();
		}
		path.addFirst(n1.getElem());
		
		return new DennWeightList<>(path, l.get(n2));
	}
	
	@Override
	public ArrayList<T> getAdjacents(T node) throws Exception {
		if(nodes.get(node)== null) {
			throw new Exception("Nodo no encontrado");
		}
		
		ArrayList<IDennNodeL<T>> adjacents = nodes.get(node).getAdjacents();
		ArrayList<T> r = new ArrayList<T>();
		for(int i = 0; i <adjacents.size(); i++) {
			r.add(adjacents.get(i).getElem());
		}
		return r;
	}

	@Override
	public T getParent(T node) throws Exception {
		
		return nodes.get(node).getParent().getElem();
	}

	@Override
	public void deleteNode(T node) throws Exception {
		DennNodeL<T>  delete = nodes.get(node);
		if(delete == null)
			throw new Exception("Nodo no existe");
		for(T k : nodes.keySet()) {
			DennNodeL<T> actual = nodes.get(k);
			actual.getAdjacents().remove(delete);
			actual.getDistances().remove(delete);
		}
		nodes.remove(node);
		
	}

	@Override
	public void deleteEdge(T node1, T node2) throws Exception {
		DennNodeL<T> n1 = nodes.get(node1);
		DennNodeL<T> n2 = nodes.get(node2);
		
		if(n1==null || n2==null||n1.getDistanceAdjacent(n2)==null )
			throw new Exception("Arista o nodo no existente");
		
		n1.getAdjacents().remove(n2);
		n1.getDistances().remove(n2);
		
//		n1.getAdjacents().remove(n2);  no dirigido
//		n1.getDistances().remove(n2);

	}

	@Override
	public double getDistance(T node1, T node2) throws Exception {
		DennNodeL<T> n1 = nodes.get(node1);
		DennNodeL<T> n2 = nodes.get(node2);
		if(n1 == null || n2 == null || n1.getDistanceAdjacent(n2)== null)
			throw new Exception("Arista o nodo no existente");
		return n1.getDistances().get(n2);
	}
	
}
