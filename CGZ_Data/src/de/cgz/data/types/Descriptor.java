package de.cgz.data.types;

import de.cgz.data.ui.DisplayMode;


public interface Descriptor<T extends DataObject> {
	
	Class<T> getType();
	
	String[] getProperties(DisplayMode mode);
	
}
