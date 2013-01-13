package de.cgz.data.types.collection.collection;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import de.cgz.data.types.DataObject;
import de.cgz.data.types.InjectStatement;
import de.cgz.data.types.Statement;


public interface DataCollection<T> extends DataObject, Iterable<T> {
	
	public int size();
	public boolean isEmpty();
	public boolean contains(final T element);
	
	public List<T> toList();
	public Set<T> toSet();
	public Object[] toArray();
	public T[] toArray(T[] at);
	
	public Object forEach(final Statement<T> statement);
	public <R> R forEach(final Class<? extends R> returnType, final Statement<T> statement);
	public <M> M inject(final M mem, final InjectStatement<T, M> statement);
	public <R> DataCollection<R> collect(final Class<? extends R> returnType, final Statement<T> statement);
	
	public T find(final Statement<T> statement);
	public DataCollection<T> findAll(final Statement<T> statement);
	
	public  DataCollection<T> intersection(final DataCollection<T> other);
	public  DataCollection<T> difference(final DataCollection<T> subtrahend);
	public  DataCollection<T> union(final DataCollection<T> addend);	
	
	public T first();
	public T last();
	
	public DataCollection<T> sort();
	public DataCollection<T> sort(final Comparator<T> comp);
	
	
		
}
