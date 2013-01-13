package de.cgz.data.types.collection.collection;

import java.util.Collections;
import java.util.List;

import de.cgz.data.types.InjectStatement;
import de.cgz.data.types.Statement;
import de.cgz.data.types.collection.range.Range;


public abstract class AbstractOrderedDataCollection<T> extends AbstractDataCollection<T> implements OrderedDataCollection<T> {

	public T get(final int index) {
		return forEach(new Statement<T>() {
			public T execute(T element, int i) {
				if(index == i) return element;
				return null;
			}			
		});
	}

	@SuppressWarnings("unchecked")
	public OrderedDataCollection<T> get(final Range<Integer> dataRange) {
		return inject((ListDataCollection<T>)factory().createListDataCollection(), new InjectStatement<T, ListDataCollection<T>>() {
			public ListDataCollection<T> execute(T element, int index, ListDataCollection<T> mem) {
				if(dataRange.contains(index)) {
					mem.add(element);
				}
				return mem;
			}});
	}

	public DataCollection<T> reverse() {
		List<T> data = toList();
		Collections.reverse(data);
		return factory().createListDataCollection(data);
	}

}
