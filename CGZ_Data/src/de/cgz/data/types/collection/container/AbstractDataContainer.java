package de.cgz.data.types.collection.container;

import java.util.ArrayList;
import java.util.Iterator;

import de.cgz.data.types.AbstractDataObject;
import de.cgz.data.types.DataObject;
import de.cgz.data.types.Descriptor;
import de.cgz.data.types.ReturnState;
import de.cgz.data.types.Statement;


public abstract class AbstractDataContainer<T extends DataObject> extends AbstractDataObject implements DataContainer<T>, Iterable<T> {
	
	private Iterable<T> innerData;
	private Iterable<T> reverseData;
	
	private int size;
	
	
	
	public AbstractDataContainer() {
		this(new ArrayList<T>());
	}
	

	public AbstractDataContainer(Iterable<T> innerData) {
		setInnerData(innerData);
		reverseData = null;
		
	}
	
	
	
	private void setInnerData(Iterable<T> innerData) {		
		try {
			@SuppressWarnings("unchecked")
			Iterable<T> instance = innerData.getClass().newInstance();
		} catch( Exception e) {
			throw new IllegalArgumentException(String.format("Could not instanciate %s", getClass().getName()));
		}
	}


	public Iterator<T> iterator() {
		innerData.iterator();
		return null;
	}
	
	
	
	
	public Iterator<T> reverseIterator() {
		Iterator<T> 
		forEachReverse(new Statement<T>() {
			public T execute(T element, int index, Integer size) {
				return element;
			}			
		});		
		return null;
	}	
		
	private void forEachReverse(Statement<T> statement) {		
		
	}


	protected Iterable<T> innerData() {
		return innerData;
	}
	
	

	
	public T get(final int index) {		
		checkBounds(index);
		
		T result = forEach(new Statement<T>() {
			public T execute(T element, int idx, Integer size) {
				if(index == idx) {
					return element;
				}
				return null;
			}			
		});
		return result;
	}
	
	
	
	public T forEach(Statement<T> statement) {
		T element = null;
		int index = 0;
		ReturnState state = ReturnState.VOID;
		for(Iterator<T> it=innerData.iterator() ; it.hasNext() ;) {			
			element = it.next();
			
			T statementRetVal = statement.execute(element, index, size);	
			if(!isEmpty(statementRetVal)) {
				return statementRetVal;
			}
			index++;
		}
		return null;
	}
	
	
	public int getSize() {
		return size;
	}




	
	
	
	private void checkBounds(int index) {
		if(!inBounds(index)) {
			throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds. (size: %d%s, args", index, getSize()));
		}
	}
	
	private boolean inBounds(int index) {
		return index >= 0 && index < size;
	}


	public Descriptor<T> getDescriptor() {
		return descriptor;
	}


	public void setDescriptor(Descriptor<T> descriptor) {
		this.descriptor = descriptor;
	}
	
	protected abstract boolean empty();
	
}
