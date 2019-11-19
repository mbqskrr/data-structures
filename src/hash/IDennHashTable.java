package hash;

public interface IDennHashTable<P, T> {

	public void insert(P key, T element);
	public void remove(P key, T element);
	public boolean isEmpty();
	public int size();
	public DennItem<P, T> search(P key);
	public int getSlot(P key);
	public boolean contains(P key);
}
