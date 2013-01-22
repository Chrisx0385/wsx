package de.cgz.data.types.collection.container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.cgz.data.DataObjectCreator;
import de.cgz.data.types.AbstractDataObject;
import de.cgz.data.types.DataObject;
import de.cgz.data.types.InjectStatement;
import de.cgz.data.types.Statement;
import de.cgz.data.types.collection.collection.DataCollection;
import de.cgz.data.types.collection.collection.ListDataCollection;
import de.cgz.data.types.collection.collection.OrderedDataCollection;
import de.cgz.data.types.collection.range.Range;


public class ArrayDataContainer<T extends DataObject> extends AbstractDataObject implements ListDataContainer<T>, Iterable<T> {
	
	private final ListDataCollection<T> dataCollection;
	private final Class<T> type;
	private T selectedDataObject;
	private DataObjectCreator<T> dataObjectCreator;

	

	
	protected void setDataObjectCreator(DataObjectCreator<T> dataObjectCreator) {
		this.dataObjectCreator = dataObjectCreator;
	}

	public ArrayDataContainer(Class<T> type, Iterable<T> innerData, DataObjectCreator<T> dataObjectCreator ) {
		this.type = type;
		this.dataObjectCreator = dataObjectCreator;
		dataCollection = factory().createListDataCollection(innerData);		
	}
	
	public ArrayDataContainer(Class<T> type, Iterable<T> innerData) {
		this(type, innerData, null);
	}
	public ArrayDataContainer(Class<T> type, T[] innerData) {
		this(type, Arrays.asList(innerData));
	}	
	public ArrayDataContainer(Class<T> type) {
		this(type, new ArrayList<T>(0));
	}

	public void add(T data) {
		dataCollection.add(data);
	}

	public void addAll(Iterable<T> data) {
		dataCollection.addAll(data);
	}

	public void addAll(T[] data) {
		dataCollection.addAll(data);
	}

	public T get(int index) {
		return dataCollection.get(index);
	}

	public T remove(T data) {
		return dataCollection.remove(data);
	}

	public OrderedDataCollection<T> get(Range<Integer> dataRange) {
		return dataCollection.get(dataRange);
	}

	public void clear() {
		dataCollection.clear();
	}

	public DataCollection<T> reverse() {
		return dataCollection.reverse();
	}

	@Override
	public boolean isValid() {
		return dataCollection.isValid();
	}

	public int size() {
		return dataCollection.size();
	}

	@Override
	public boolean isEmpty() {
		return dataCollection.isEmpty();
	}

	public boolean contains(T element) {
		return dataCollection.contains(element);
	}

	public List<T> toList() {
		return dataCollection.toList();
	}

	public Set<T> toSet() {
		return dataCollection.toSet();
	}

	public Object[] toArray() {
		return dataCollection.toArray();
	}

	public T[] toArray(T[] at) {
		return dataCollection.toArray(at);
	}

	public Object forEach(Statement<T> statement) {
		return dataCollection.forEach(statement);
	}

	public <R> R forEach(Class<? extends R> returnType, Statement<T> statement) {
		return dataCollection.forEach(returnType, statement);
	}

	public <M> M inject(M mem, InjectStatement<T, M> statement) {
		return dataCollection.inject(mem, statement);
	}

	public <R> DataCollection<R> collect(Class<? extends R> returnType, Statement<T> statement) {
		return dataCollection.collect(returnType, statement);
	}

	public T find(Statement<T> statement) {
		return dataCollection.find(statement);
	}

	public DataCollection<T> findAll(Statement<T> statement) {
		return dataCollection.findAll(statement);
	}

	public DataCollection<T> intersection(DataCollection<T> other) {
		return dataCollection.intersection(other);
	}

	public DataCollection<T> difference(DataCollection<T> subtrahend) {
		return dataCollection.difference(subtrahend);
	}

	public DataCollection<T> union(DataCollection<T> addend) {
		return dataCollection.union(addend);
	}

	public T first() {
		return dataCollection.first();
	}

	public Iterator<T> iterator() {
		return dataCollection.iterator();
	}

	public T last() {
		return dataCollection.last();
	}

	public DataCollection<T> sort() {
		return dataCollection.sort();
	}

	public DataCollection<T> sort(Comparator<T> comp) {
		return dataCollection.sort(comp);
	}
	
	

	public T createDataObject() {
		if(dataObjectCreator != null) {
			return dataObjectCreator.createDataObject();
		}		
		try {
			return factory().createDataObject(getType());
		} catch (Exception e) {
			//fall through
		}		
		try {
			return getType().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(String.format("Can't create new instance from type '%s'", getType().getName()));
		}
	}
	
	public T addNewDataObject() {
		T data = createDataObject();
		add(data);
		return data;
	}

	public Class<T> getType() {
		return type;
	}

	public T getSelectedDataObject() {
		return selectedDataObject;
	}	
	public void setSelectedDataObject(T selectedDataObject) {
		this.selectedDataObject = selectedDataObject;
	}
}
