package de.cgz.data.types.collection.range;

import de.cgz.data.types.collection.collection.OrderedDataCollection;


public interface DiscreteRange<T extends Comparable<T>> extends Range<T>, OrderedDataCollection<T> {
	
	public T getMinVal();
	public T getMaxVal();	

}
