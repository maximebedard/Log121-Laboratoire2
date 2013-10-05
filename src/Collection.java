

public interface Collection<T> extends Iterable<T> {

	public void addFirst(T element);
	public void addFirst(Collection<T> col);
	
	public void addLast(T element);
	public void addLast(Collection<T> col);
	
	public void add(int index, T element);
	
	public void removeFirst();	
	public void removeLast();
	
	public void remove(int index);
	
	public int count();
	
	public T find(int index);
	
}
