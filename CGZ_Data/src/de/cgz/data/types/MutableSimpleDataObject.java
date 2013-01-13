package de.cgz.data.types;


public interface MutableSimpleDataObject<T> extends SimpleDataObject<T> {
	void setValue(T value);
}
