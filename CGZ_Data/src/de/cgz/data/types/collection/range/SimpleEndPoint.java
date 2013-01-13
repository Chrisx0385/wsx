package de.cgz.data.types.collection.range;



public class SimpleEndPoint<T extends Comparable<T>> extends SimpleRangeValueImpl<T> implements EndPoint<T> {

	private final int infinite;
	private final EndPointType endPointType;

	public SimpleEndPoint(T value, RangeValueGenerator<T> generator) {		
		this(value, 0, EndPointType.INCLUSIVE, generator);		
	}
	
	public SimpleEndPoint(T value, int infinite, EndPointType endPointType, RangeValueGenerator<T> generator) {
		super(value, generator);
		this.infinite = infinite;
		this.endPointType = endPointType;
	}

	public EndPointType getEndPointType() {
		return endPointType;
	}

	public boolean isPosInfinite() {
		return infinite > 0;
	}

	public boolean isNegInfinite() {
		return infinite < 0;
	}


}
