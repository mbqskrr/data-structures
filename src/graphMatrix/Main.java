package graphMatrix;

import graphCollections.Tree;


public class Main {

public static void main(String[] args) throws Exception {
		
		DennGraphM<Integer> g = new DennGraphM<Integer>(5);
	
		g.addNodeM(1);
		g.addNodeM(30);
		g.addNodeM(22);
		g.addNodeM(6);
		g.addNodeM(4);
		
		g.addEdge(1, 30, 2);
		g.addEdge(30, 4, 7);
		g.addEdge(4, 30, 50);
		g.addEdge(1, 22, 6);
		g.addEdge(30, 6, 12);
		g.addEdge(4, 6, 5);
		g.addEdge(6, 4, 9);
		g.addEdge(22, 4, 1);
		
		
		
		
		int[] disktraj = g.dijkstra(22);
		DennNodeM<Integer> n = g.searchNodeM(6);
		
		
		System.out.println(disktraj[n.getPos()]);
		
		
		int[][] floyd = g.floydWarshall();
		
		DennNodeM<Integer> n1 = g.searchNodeM(4);
		DennNodeM<Integer> n2 = g.searchNodeM(6);
		
		System.out.println(floyd[n1.getPos()][n2.getPos()]);
		
		
		System.out.println("Hola");

		Tree<Integer> t = g.BFS(1);
	
		
		
		System.out.println(t.print());
		System.out.println("Hola 2");
		System.out.println(g.DFS(1));
		
		
	}
	
}
