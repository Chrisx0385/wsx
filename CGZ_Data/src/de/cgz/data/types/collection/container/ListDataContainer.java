package de.cgz.data.types.collection.container;

import de.cgz.data.types.DataObject;
import de.cgz.data.types.collection.collection.ListDataCollection;


public interface ListDataContainer<T extends DataObject> extends DataContainer<T>, ListDataCollection<T> {

}
