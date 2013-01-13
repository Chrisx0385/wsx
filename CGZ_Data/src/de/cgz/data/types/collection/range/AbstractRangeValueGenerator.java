package de.cgz.data.types.collection.range;

import de.cgz.data.types.AbstractDataObject;



public abstract class AbstractRangeValueGenerator<T extends Comparable<T>> extends AbstractDataObject implements RangeValueGenerator<T> {

	public AbstractRangeValueGenerator() {
		super();
	}

	public boolean isEmpty() {
		return false;
	}


}
