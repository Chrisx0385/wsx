package de.cgz.data.types.collection.range;

import de.cgz.data.types.SimpleDataObjectImpl;

public class SimpleRangeValueImpl<T extends Comparable<T>> extends SimpleDataObjectImpl<T> implements SimpleRangeValue<T> {
	
	private final RangeValueGenerator<T> generator;

	public SimpleRangeValueImpl(T value, RangeValueGenerator<T> generator) {
		super(value);
		this.generator = generator;
		utils().nullCheck(getValue(), getGenerator());
	}

	public int compareTo(RangeValue<T> o) {
		if(this.equals(o)) return 0;
		if(this.isEmpty()) return -1;
		if(o.isEmpty()) return 1;
		return getValue().compareTo(o.getValue());
	}

	public RangeValue<T> nextValue() {
		return new SimpleRangeValueImpl<T>(generator.nextValue(getValue(), 1), generator);
	}

	public RangeValue<T> previousValue() {
		return new SimpleRangeValueImpl<T>(generator.nextValue(getValue(), -1), generator);
	}

	protected RangeValueGenerator<T> getGenerator() {
		return generator;
	}

}
