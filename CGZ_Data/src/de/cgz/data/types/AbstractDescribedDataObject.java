package de.cgz.data.types;


public abstract class AbstractDescribedDataObject<T extends DataObject> extends AbstractDataObject implements DescribedObject<T> {
	
	private Descriptor<T> dataDescriptor;
	
	
	public AbstractDescribedDataObject() {
		this(null);
	}
	
	@SuppressWarnings("unchecked")
	public AbstractDescribedDataObject(Descriptor<T> dataDescriptor) {
		this.dataDescriptor = dataDescriptor == null ? new SimpleDataDescriptor<T>((Class<T>) getClass()) : dataDescriptor;
	}
	
	
	public Descriptor<T> getDescriptor() {
		return dataDescriptor;
	}
	
	
}
