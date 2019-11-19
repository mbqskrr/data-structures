package graphList;

import java.util.ArrayList;
import java.util.HashMap;

import graphCollections.IDennNodeL;
import graphCollections.Node;

public class DennNodeL<T> extends Node<T> implements IDennNodeL<T>, Comparable<DennNodeL<T>> {

	private ArrayList<IDennNodeL<T>> adjacents;
	private HashMap<IDennNodeL<T>, Double> distances;
	private double distancePrevPath;
	
	public DennNodeL(T elem) {
		super(elem);
		adjacents = new ArrayList<IDennNodeL<T>>();
		distances = new HashMap<>();
	}

	@Override
	public int compareTo(DennNodeL<T> n) {
		if(distancePrevPath == n.distancePrevPath) {
			return 0;
		}else if(distancePrevPath < n.distancePrevPath) {
			return -1;
		}else {
			return 0;
		}
	}

	@Override
	public ArrayList<IDennNodeL<T>> getAdjacents() {
		return adjacents;
	}

	@Override
	public HashMap<IDennNodeL<T>, Double> getDistances() {
		return distances;
	}

	@Override
	public void addAdjacents(IDennNodeL<T> n) {
		adjacents.add(n);		
	}

	@Override
	public void addDistance(IDennNodeL<T> adjacent, Double distance) {
		distances.put(adjacent, distance);
	}

	public void setDistancePrevPath(double distancePrevPath) {
		this.distancePrevPath = distancePrevPath;
	}
	
	public double getDistancePrevPath() {
		return distancePrevPath;
	}
	
	public Double getDistanceAdjacent(IDennNodeL<T> n) {
		return distances.get(n);
	}


}
