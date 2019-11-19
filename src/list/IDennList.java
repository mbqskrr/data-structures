package list;

public interface IDennList<T> {
	public void add(T element);
	public T getElement(int i);
	public void deleteElement(T element);
	public void delete(int i);
	public boolean isEmpty();
	int getSize();
}
