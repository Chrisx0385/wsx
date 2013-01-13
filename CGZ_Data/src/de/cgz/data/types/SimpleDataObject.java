package de.cgz.data.types;


public interface SimpleDataObject<T> extends DataObject {

	T getValue();
	Class<T> getType();
}
