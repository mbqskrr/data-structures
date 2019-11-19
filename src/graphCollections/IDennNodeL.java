package graphCollections;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDennNodeL<T> extends IDennNode<T> {

	ArrayList<IDennNodeL<T>> getAdjacents();
	HashMap<IDennNodeL<T>, Double> getDistances();
	void addAdjacents(IDennNodeL<T> n);
	void addDistance(IDennNodeL<T> adjacent, Double distance);

}
