package de.cgz.data.types.collection.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.cgz.data.types.AbstractDataObject;
import de.cgz.data.types.InjectStatement;
import de.cgz.data.types.MutableSimpleDataObject;
import de.cgz.data.types.MutableSimpleDataObjectImpl;
import de.cgz.data.types.Statement;

public abstract class AbstractDataCollection<T> extends AbstractDataObject implements DataCollection<T> {

	public Iterator<T> iterator() {
		return getData().iterator();
	}

	/**
	 * Counts the number of elements in this {@link DataCollection}. <br />
	 * Attention: unperformant operation. Classes using
	 * {@link AbstractDataCollection} should override if size is known and musst
	 * not be counted.
	 * 
	 * @return Number of elements in this {@link DataCollection}.
	 */
	public int size() {
		if (isEmpty()) return 0;
		int size = 0;
		Iterator<T> iterator = iterator();
		for (; iterator.hasNext(); size++, iterator.next()) {}
		;

		return size;
	}

	public List<T> toList() {
		return inject(new ArrayList<T>(), new InjectStatement<T, List<T>>() {

			public List<T> execute(T element, int index, List<T> mem) {
				mem.add(element);
				return mem;
			}
		});
	}

	public Set<T> toSet() {
		return inject(new HashSet<T>(), new InjectStatement<T, Set<T>>() {

			public Set<T> execute(T element, int index, Set<T> mem) {
				mem.add(element);
				return mem;
			}
		});
	}

	public Object[] toArray() {
		return toList().toArray();
	}
	
	public T[] toArray(T[] at) {
		return toList().toArray(at);
	};

	public T forEach(Statement<T> statement) {
		T element = null;
		int index = 0;
		for (Iterator<T> it = iterator(); it.hasNext();) {
			element = it.next();

			@SuppressWarnings("unchecked")
			T statementRetVal = (T) statement.execute(element, index);
			if (!utils().isEmpty(statementRetVal)) {
				return statementRetVal;
			}
			index++;
		}
		return null;
	}



	@SuppressWarnings("unchecked")
	public <R> R forEach(Class<? extends R> returnType, Statement<T> statement) {
		return (R) forEach(statement);
	}

	public <M> M inject(final M mem, final InjectStatement<T, M> statement) {
		final MutableSimpleDataObject<M> result = new MutableSimpleDataObjectImpl<M>(mem);
		forEach(mem.getClass(), new Statement<T>() {
			public T execute(T element, int index) {
				result.setValue(statement.execute(element, index, result.getValue()));
				return null;
			};});
			
		return result.getValue();
	}

	public <R> DataCollection<R> collect(Class<? extends R> returnType, final Statement<T> statement) {		
		return inject(factory().createListDataCollection(returnType), new InjectStatement<T, ListDataCollection<R>>() {
			@SuppressWarnings("unchecked")
			public ListDataCollection<R> execute(T element, int index, ListDataCollection<R> mem) {
				mem.add((R) statement.execute(element, index));
				return mem;
			}});		
	}

	public T find(final Statement<T> statement) {
		return forEach(new Statement<T>() {
			public T execute(T element, int index) {
				if((Boolean) statement.execute(element, index))
					return element;
				return null;
			}			
		});
	}

	@SuppressWarnings("unchecked")
	public DataCollection<T> findAll(final Statement<T> statement) {
		return inject((ListDataCollection<T>) factory().createListDataCollection(), new InjectStatement<T, ListDataCollection<T>>() {
			public ListDataCollection<T> execute(T element, int index, ListDataCollection<T> mem) {
				if((Boolean) statement.execute(element, index)) {
					mem.add(element);					
				}
				return mem;					
			}			
		});
	}
	
	
	public boolean contains(final T element) {
		T found = find(new Statement<T>() {
			public Object execute(T e, int index) {				
				return element.equals(e);
			}			
		});
		return found != null;
	}

	public boolean isEmpty() {
		return !iterator().hasNext();
	}

	@SuppressWarnings("unchecked")
	public DataCollection<T> intersection(final DataCollection<T> other) {
		return inject((ListDataCollection<T>) factory().createListDataCollection(), new InjectStatement<T, ListDataCollection<T>>() {
			public ListDataCollection<T> execute(T element, int index, ListDataCollection<T> mem) {
				if(other.contains(element)) {
					mem.add(element);
				}
				return mem;
			}			
		});
	}

	@SuppressWarnings("unchecked")
	public DataCollection<T> difference(final DataCollection<T> subtrahend) {
		return inject((ListDataCollection<T>) factory().createListDataCollection(), new InjectStatement<T, ListDataCollection<T>>() {
			public ListDataCollection<T> execute(T element, int index, ListDataCollection<T> mem) {
				if(!subtrahend.contains(element)) {
					mem.add(element);
				}
				return mem;
			}			
		});
	}

	public DataCollection<T> union(DataCollection<T> addend) {		
		return addend.inject((ListDataCollection<T>)factory().createListDataCollection(this), new InjectStatement<T, ListDataCollection<T>>() {
			public ListDataCollection<T> execute(T element, int index, ListDataCollection<T> mem) {
				if(!AbstractDataCollection.this.contains(element)) mem.add(element);
				
				return mem;
			}			
		});		
	}

	public T first() {
		if(isEmpty()) return null;
		
		return iterator().next();
	}

	public T last() {		
		if(isEmpty()) return null;
		
		return inject(first(), new InjectStatement<T, T>() {
			public T execute(T element, int index, T mem) {				
				return element;
			}			
		});
	}

	@SuppressWarnings("unchecked")
	public DataCollection<T> sort() {		
		@SuppressWarnings("rawtypes")
		List list = toList();
		Collections.sort(list);
		return factory().createListDataCollection((List<T>) list);
	}

	public DataCollection<T> sort(Comparator<T> comp) {
		List<T> data = toList();
		Collections.sort(data, comp);
		return factory().createListDataCollection(data);
	}

	protected abstract Iterable<T> getData();

}
