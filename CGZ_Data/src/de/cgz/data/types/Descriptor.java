package de.cgz.data.types;


public interface Descriptor<T extends DataObject> {
	
	Class<T> getType();
	
}
