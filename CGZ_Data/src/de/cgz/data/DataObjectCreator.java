package de.cgz.data;

import de.cgz.data.types.DataObject;


public interface DataObjectCreator<T extends DataObject> {

	T createDataObject();
}
