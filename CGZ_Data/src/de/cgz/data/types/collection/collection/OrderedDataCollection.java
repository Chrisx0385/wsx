package de.cgz.data.types.collection.collection;

import de.cgz.data.types.collection.range.Range;


public interface OrderedDataCollection<T> extends DataCollection<T> {

	public int size();
	public T get(final int index);	
	public OrderedDataCollection<T> get(final Range<Integer> dataRange);
	
	public DataCollection<T> reverse();
}
