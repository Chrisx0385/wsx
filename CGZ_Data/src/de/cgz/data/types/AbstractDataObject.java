package de.cgz.data.types;

import de.cgz.data.utils.DataUtils;



/**
 * Superclass off all data types
 */
public abstract class AbstractDataObject implements DataObject {

	protected static TypeFactory factory() {
		return TypeFactory.getInstance();
	}
	
	protected static DataUtils utils() {
		return DataUtils.getInstance();
	}
	
	public boolean isValid() {
		return true;
	}
	
	protected void validate() {
		doValidate();
	}
	
	protected void doValidate() {
		//hook
		if(isValid()) throw new IllegalArgumentException(String.format("validation failed for %s", this));
	}
	

	public boolean isEmpty() {
		return false;
	}

}
