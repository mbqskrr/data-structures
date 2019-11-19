package queue;
public interface IDennQueue<T>{


public void offerDenn(T element);
public boolean isEmptyDenn();
public T peekDenn();
public T pollDenn();

}