package de.cgz.data.types;


public class MutableSimpleDataObjectImpl<T> extends SimpleDataObjectImpl<T> implements MutableSimpleDataObject<T> {

	public MutableSimpleDataObjectImpl(Class<T> type) {
		super(type);
	}

	public MutableSimpleDataObjectImpl(T value) {
		super(value);
	}
	
	public void setValue(T value) {
		super.setValue(value);
	};
	
	

}
