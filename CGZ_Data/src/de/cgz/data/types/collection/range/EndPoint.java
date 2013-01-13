package de.cgz.data.types.collection.range;


public interface EndPoint<T extends Comparable<T>> extends RangeValue<T> {

	enum EndPointType {
		EXCLUSIVE, INCLUSIVE;
	}
	
	boolean isPosInfinite();
	boolean isNegInfinite();
	
	EndPointType getEndPointType();
}
