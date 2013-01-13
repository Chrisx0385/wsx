package de.cgz.data.types;


public class SimpleDataDescriptor<T extends DataObject> extends AbstractDescriptor<T> {
	
	
	
	public SimpleDataDescriptor(Class<T> type) {
		super(type);
	}

	public boolean hasData() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

}
