package de.cgz.data.types;


public class EmptyDataObject extends AbstractDataObject {

	public EmptyDataObject() {
	}
	
	@Override
	public boolean isEmpty() {
		return true;
	}
}
