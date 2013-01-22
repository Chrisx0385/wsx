package de.cgz.data.utils;

import java.util.HashSet;
import java.util.Set;

import de.cgz.data.types.DataObject;
import de.cgz.data.types.TypeFactory;
import de.cgz.data.types.collection.collection.DataCollection;


public final class DataUtils {
	private static DataUtils instance;

	public static DataUtils getInstance() {
		return instance == null ? (instance = new DataUtils()) : instance;
	}

	private DataUtils() {}
	
	/**
	 * Prüft ob der gegebene Wert leer ist.
	 * Fuer Strings, Iterable, Arrays, DataObject einsetzbar.
	 * Als leer gelten folgende Werte:
	 * <table border="1">
	 * <thead>
	 * <tr><th><b>Wert</b></th> <th><b>Bedingung</b></th></tr>
	 * </thead>
	 * <tbody>
	 * <tr><td><b><code>null</code></b></td><td></td></tr>
	 * <tr><td><b><code>Object[] a</code></b></td><td>a.length == 0</td></tr>
	 * <tr><td><b><code>Iterable i</code></b></td> <td><code> i.iterator().hasNext() = false </code></td></tr>
	 * <tr><td><b><code>DataObject d</code></b></td> <td><code> d.hasData = false </code></td></tr>
	 * <tr><td><b><code>String s</code></b></td> <td><code> s.trim().length() == 0 </code></td></tr>
	 * <tr><td><b><code>Object o</code></b></td><td><code> o.toString().trim().length() == 0 </code></td></tr>
	 * </tbody>
	 * </table>
	 * 
	 * @param value der zu prüfendende Wert.
	 * @return true wenn der Wert leer ist.
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public boolean isEmpty(Object value) {
		if(value == null) return true;		
		if(value.getClass().isArray()) return ((Object[])value).length == 0;
		if(value instanceof Iterable) return !((Iterable)value).iterator().hasNext();
		if(value instanceof DataObject) return !((DataObject) value).isEmpty();
		
		String val = value.toString();
		if(val == null) return true;	//Vadin implementiert toString mit null als moeglichem retVal.	
		
		return value.toString().trim().length() == 0;
	}
	
	public void nullCheck(Object... values) {
		for(Object value : values)
			if(value == null) throw new IllegalArgumentException("given value must not be null.");		
	}
	
	public boolean isSomethingNull(Object... values) {
		for(Object value : values)
			if(value == null) return true;
		
		return false;
	}
	
	public boolean isSomethingEmpty(Object... values) {
		for(Object value : values)
			if(isEmpty(value)) return true;
		
		return false;		
	}
	
	public StringUtils getStringUtils() {
		return StringUtils.getInstance();
	}
	
	public ReflectionUtils getReflectionUtils() {
		return ReflectionUtils.getInstance();
	}
	
	public TypeFactory getTypeFactory() {
		return TypeFactory.getInstance();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public<T> DataCollection<T> toDataCollection(T... data) {
		if(isEmpty(data)) {
			return (DataCollection<T>) getTypeFactory().createListDataCollection();
		}
		return getTypeFactory().createListDataCollection(data);		
	}
	
	public<T> Set<T> toSet(T... data) {
		if(isEmpty(data)) {
			return new HashSet<T>();
		}
		return getTypeFactory().createListDataCollection(data).toSet();		
	}
	
}
