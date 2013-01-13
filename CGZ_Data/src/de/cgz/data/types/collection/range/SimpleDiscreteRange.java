package de.cgz.data.types.collection.range;

import de.cgz.data.types.collection.range.EndPoint.EndPointType;


public class SimpleDiscreteRange<T extends Comparable<T>> extends AbstractDiscreteRange<T> {

	public SimpleDiscreteRange(T left, EndPointType leftType, T right, EndPointType rightType) {
		super(left, leftType, right, rightType);
	}



}
