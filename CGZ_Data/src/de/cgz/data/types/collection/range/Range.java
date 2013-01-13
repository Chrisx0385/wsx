package de.cgz.data.types.collection.range;



public interface Range<T extends Comparable<T>> {
	
	EndPoint<T> getLeft();
	EndPoint<T> getRight();
	
	boolean contains(T data);
}
