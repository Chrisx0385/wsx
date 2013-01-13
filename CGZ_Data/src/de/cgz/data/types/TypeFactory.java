package de.cgz.data.types;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

import de.cgz.data.types.collection.collection.ArrayListDataCollectionImpl;
import de.cgz.data.types.collection.collection.ListDataCollection;
import de.cgz.data.types.collection.range.DiscreteRange;
import de.cgz.data.types.collection.range.EndPoint;
import de.cgz.data.types.collection.range.EndPoint.EndPointType;
import de.cgz.data.types.collection.range.RangeValueGenerators;
import de.cgz.data.types.collection.range.SimpleDiscreteRange;
import de.cgz.data.types.collection.range.SimpleEndPoint;


public final class TypeFactory {
	
	private static TypeFactory instance;

	public static TypeFactory getInstance() {
		return instance == null ? (instance = new TypeFactory()) : instance;
	}
	
	private Map<Class<?>, Class<?>> implementationMap = new HashMap<Class<?>, Class<?>>();

	private TypeFactory() {
		registerImpls();
	}
	
	protected void registerImpls() {
		
	}
	private void registerImpl(Class<?> type, Class<?> impl) {
		implementationMap.put(type, impl);
	}

	public ListDataCollection<? extends Object> createListDataCollection() {
		return new ArrayListDataCollectionImpl<Object>();
	}
	
	public <T> ListDataCollection<T> createListDataCollection(Class<? extends T> type) {
		return new ArrayListDataCollectionImpl<T>();
	}
	
	public <T> ListDataCollection<T> createListDataCollection(Iterable<T> initialData) {
		return new ArrayListDataCollectionImpl<T>(initialData);
	}
	public <T> ListDataCollection<T> createListDataCollection(T[] initialData) {
		return new ArrayListDataCollectionImpl<T>(initialData);
	}
	
	public <T extends Comparable<T>> DiscreteRange<T> createDiscreteRange(T min, T max) {
		return new SimpleDiscreteRange<T>(min, EndPointType.INCLUSIVE, max, EndPointType.INCLUSIVE);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> EndPoint<T> createEndPoint(T value, EndPointType type) {
		return new SimpleEndPoint<T>(value, 0, type, RangeValueGenerators.getInstance().generator(value.getClass()));
	}

	public Object create(Class<?> type, String stringPresentation) {
		
		if(Calendar.class.isAssignableFrom(type)) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			try {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(stringPresentation));
				return calendar;
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		
		if(!DataObject.class.isAssignableFrom(type)) {
			throw new UnsupportedTypeException();
		}
		
		if(type.isInterface()) {
			type = implementationMap.get(type);
		}
		if(type == null) {
			throw new UnsupportedTypeException();
		}
		try {			
			Method method = type.getMethod("createFromString", String.class);
			return (DataObject) method.invoke(null, stringPresentation);
		} catch (Exception e) {
			throw new RuntimeException("Can't create Object.", e);
		}
	}
	

}
