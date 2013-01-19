package de.cgz.data.types;

import de.cgz.data.types.collection.collection.DataCollection;
import de.cgz.data.ui.DisplayMode;


public interface Descriptor<T extends DataObject> {
	
	Class<T> getType();
	
	DataCollection<String> getProperties(DisplayMode mode);
	
	DataCollection<String> getCollectionProperties(DisplayMode mode);
	
	Class<?> getPropertyType(String property);
	

	
}
