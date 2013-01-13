package de.cgz.data.types.collection.collection;


public interface MutableDataCollection<T> extends DataCollection<T> {
	
	public void add(T data);
	public void addAll(Iterable<T> data);
	public void addAll(T[] data);
	
	public T remove(T data);
	
	public void clear();

}
