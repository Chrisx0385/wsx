package de.cgz.data.types.collection.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cgz.data.types.DataObject;




public class AbstractHashedDataContainer<T extends DataObject> extends AbstractDataContainer<T> implements HashedDataContainer<T>  {
	
	private final List<T> dataList = new ArrayList<T>();
	private final Map<String, Map<Object, T>> propertyHashes = new HashMap<String, Map<Object,T>>();
	
	

	public boolean hasData() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean empty() {
		// TODO Auto-generated method stub
		return false;
	}





}
