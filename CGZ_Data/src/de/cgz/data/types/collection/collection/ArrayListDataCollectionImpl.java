package de.cgz.data.types.collection.collection;

import java.util.ArrayList;
import java.util.List;

import de.cgz.data.types.Statement;
import de.cgz.data.types.collection.range.Range;


public class ArrayListDataCollectionImpl<T> extends AbstractDataCollection<T> implements ListDataCollection<T>{

	private final List<T> data = new ArrayList<T>();
	
	public ArrayListDataCollectionImpl() {
		
	}
	
	public ArrayListDataCollectionImpl(Iterable<T> initialData) {
		for (T t : initialData) {
			data.add(t);
		}
	}

	public ArrayListDataCollectionImpl(T[] initialData) {
		for (T t : initialData) {			
			data.add(t);
		}
	}

	protected Iterable<T> getData() {
		return data;
	}
	
	@Override
	public int size() {
		return data.size();
	}

	public void add(T data) {
		this.data.add(data);		
	}

	public void addAll(Iterable<T> data) {
		for(T e : data) this.data.add(e);		
	}

	public void addAll(T[] data) {
		for(T e : data) this.data.add(e);		
	}

	public T remove(final T data) {
		T removedElement = find(new Statement<T>() {
			public Object execute(T element, int index) {
				if(data.equals(element)) {
					return element;
				}
				return null;
			}});		
		this.data.remove(data);
		return removedElement;
	}

	public void clear() {
		data.clear();		
	}

	public T get(int index) {
		inRange(index);
		return data.get(index);
	}

	public OrderedDataCollection<T> get(Range<Integer> dataRange) {
		rangeCheck(dataRange);
		return null;
	}

	public DataCollection<T> reverse() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void inRange(int index) {
		
	}
	
	protected void rangeCheck(Range<Integer> dataRange) {
		
	}

}
