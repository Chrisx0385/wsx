package de.cgz.data.types.collection.range;

import de.cgz.data.types.DataObject;


public interface RangeValue<T extends Comparable<T>> extends DataObject, Comparable<RangeValue<T>> {
	
	T getValue();
	
	RangeValue<T> nextValue();	
	RangeValue<T> previousValue();	

}
