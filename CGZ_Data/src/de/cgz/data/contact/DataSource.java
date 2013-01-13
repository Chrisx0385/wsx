package de.cgz.data.contact;

import de.cgz.data.types.DataObject;
import de.cgz.data.types.collection.container.DataContainer;


public interface DataSource<T extends DataObject> {

	DataContainer<T> getData();
	
	DataContainer<T> getData(DataFilter<T> filter);
}
