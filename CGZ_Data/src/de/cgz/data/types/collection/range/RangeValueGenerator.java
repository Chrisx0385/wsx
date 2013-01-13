package de.cgz.data.types.collection.range;

import de.cgz.data.types.DataObject;


public interface RangeValueGenerator<T extends Comparable<T>> extends DataObject {	
	/**
	 * @param current the value for which the successor or predecessor should be calculated.
	 * @param n a positive amount for the n'th successor of the given value <code>current</code>, a negative amount for the n'th predecessor.
	 * @return a successor or predecessor of the given value <code>current</code>.
	 */
	T nextValue(T current, int n);
}
