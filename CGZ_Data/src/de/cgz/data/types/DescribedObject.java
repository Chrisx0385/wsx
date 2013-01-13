package de.cgz.data.types;


public interface DescribedObject<T extends DataObject> {

	Descriptor<T> getDescriptor();
}
