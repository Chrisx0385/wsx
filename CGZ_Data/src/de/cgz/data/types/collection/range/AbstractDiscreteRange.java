package de.cgz.data.types.collection.range;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import de.cgz.data.types.InjectStatement;
import de.cgz.data.types.Statement;
import de.cgz.data.types.collection.collection.AbstractOrderedDataCollection;
import de.cgz.data.types.collection.collection.DataCollection;
import de.cgz.data.types.collection.collection.OrderedDataCollection;
import de.cgz.data.types.collection.range.EndPoint.EndPointType;

public abstract class AbstractDiscreteRange<T extends Comparable<T>> extends AbstractRange<T> implements
		DiscreteRange<T>, OrderedDataCollection<T> {
	
	private final EndPoint<T> left;
	private final EndPoint<T> right;
	
	private final OrderedDataCollection<T> data;	

	protected AbstractDiscreteRange(EndPoint<T> left, EndPoint<T> right) {
		super();
		this.left = left;
		this.right = right;
		this.data = createDataCollection();
	}
	
	public AbstractDiscreteRange(T left, EndPointType leftType, T right, EndPointType rightType) {
		this(factory().createEndPoint(left, leftType), factory().createEndPoint(right, rightType));		
	}

	public EndPoint<T> getLeft() {
		return left;
	}

	public EndPoint<T> getRight() {
		return right;
	}

	public T getMinVal() {		
		return getLeft().getValue();
	}

	public T getMaxVal() {
		return getRight().getValue();
	}
		
	protected OrderedDataCollection<T> createDataCollection() {
		return new AbstractOrderedDataCollection<T>() {
			protected Iterable<T> getData() {
				return new Iterable<T>() {
					public Iterator<T> iterator() {					
						
						return new Iterator<T>() {
							private RangeValue<T> next = left;
							
							public boolean hasNext() {								
								return !next.getValue().equals(right.nextValue().getValue());
							}

							public T next() {
								if(!hasNext()) {
									throw new NoSuchElementException();
								}
								T current = next.getValue();
								next = next.nextValue();
								return current;
							}

							public void remove() {
								throw new UnsupportedOperationException();
							}
							
						};
					}					
				};
			}};
	}
	
	
	

	public int size() {
		return data.size();
	}

	public T get(int index) {
		return data.get(index);
	}

	public OrderedDataCollection<T> get(Range<Integer> dataRange) {
		return data.get(dataRange);
	}

	public DataCollection<T> reverse() {
		return data.reverse();
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	public boolean contains(T element) {
		return data.contains(element);
	}

	public Iterator<T> iterator() {
		return data.iterator();
	}

	public List<T> toList() {
		return data.toList();
	}

	public Set<T> toSet() {
		return data.toSet();
	}

	public T[] toArray(T[] at) {
		return data.toArray(at);
	}
	
	public Object[] toArray() {
		return data.toArray();
	}

	public Object forEach(Statement<T> statement) {
		return data.forEach(statement);
	}

	public <R> R forEach(Class<? extends R> returnType, Statement<T> statement) {
		return data.forEach(returnType, statement);
	}

	public <M> M inject(M mem, InjectStatement<T, M> statement) {
		return data.inject(mem, statement);
	}

	public <R> DataCollection<R> collect(Class<? extends R> returnType, Statement<T> statement) {
		return data.collect(returnType, statement);
	}

	public T find(Statement<T> statement) {
		return data.find(statement);
	}

	public DataCollection<T> findAll(Statement<T> statement) {
		return data.findAll(statement);
	}

	public DataCollection<T> intersection(DataCollection<T> other) {
		return data.intersection(other);
	}

	public DataCollection<T> difference(DataCollection<T> subtrahend) {
		return data.difference(subtrahend);
	}

	public DataCollection<T> union(DataCollection<T> addend) {
		return data.union(addend);
	}

	public T first() {
		return data.first();
	}

	public T last() {
		return data.last();
	}

	public DataCollection<T> sort() {
		return data.sort();
	}

	public DataCollection<T> sort(Comparator<T> comp) {
		return data.sort(comp);
	}
	
	


}
