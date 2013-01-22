package de.cgz.data.types;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.cgz.data.DataObjectCreator;
import de.cgz.data.types.collection.collection.ArrayListDataCollectionImpl;
import de.cgz.data.types.collection.collection.ListDataCollection;
import de.cgz.data.types.collection.container.ArrayDataContainer;
import de.cgz.data.types.collection.container.ListDataContainer;
import de.cgz.data.types.collection.range.DiscreteRange;
import de.cgz.data.types.collection.range.EndPoint;
import de.cgz.data.types.collection.range.EndPoint.EndPointType;
import de.cgz.data.types.collection.range.RangeValueGenerators;
import de.cgz.data.types.collection.range.SimpleDiscreteRange;
import de.cgz.data.types.collection.range.SimpleEndPoint;

@SuppressWarnings("unchecked")
public final class TypeFactory {

	private static TypeFactory instance;

	public static TypeFactory getInstance() {
		return instance == null ? (instance = new TypeFactory()) : instance;
	}

	private Map<Class<?>, Class<?>> implementationMap = new HashMap<Class<?>, Class<?>>();
	private Map<Class<? extends DataObject>, DataObjectCreator<? extends DataObject>> dataObjectCreatorMap = new HashMap<Class<? extends DataObject>, DataObjectCreator<? extends DataObject>>();

	private TypeFactory() {
		registerImpls();
		registerDataObjectCreators();
	}

	protected void registerDataObjectCreators() {
		registerDOC(ArrayDataContainer.class, new DataObjectCreator<DataObject>() {
			public DataObject createDataObject() {
				return createDataContainer();
			} 
		});
	}

	private<T extends DataObject> void registerDOC(Class<T> type, DataObjectCreator<? extends DataObject> creator) {
		dataObjectCreatorMap.put(type, creator);
	}
	
	public <T extends DataObject> DataObjectCreator<T> getDataObjectCreator(Class<T> type) {
		if (dataObjectCreatorMap.containsKey(type)) {
			return (DataObjectCreator<T>) dataObjectCreatorMap.get(type);
		}

		for (Class<?> t : dataObjectCreatorMap.keySet()) {
			if (type.isAssignableFrom(t)) {
				return (DataObjectCreator<T>) dataObjectCreatorMap.get(t);
			}
		}

		return null;
	}

	protected void registerImpls() {

	}

	private void registerImpl(Class<?> type, Class<?> impl) {
		implementationMap.put(type, impl);
	}

	
	public ListDataContainer<DataObject> createDataContainer() {
		return  createDataContainer(DataObject.class);
	}
	
	public <T extends DataObject> ListDataContainer<T> createDataContainer(Class<T> type) {
		return new ArrayDataContainer<T>(type);
	}

	public <T extends DataObject> ListDataContainer<T> createDataContainer(Class<T> type, Iterable<T> initialData) {
		return new ArrayDataContainer<T>(type, initialData);
	}

	public <T extends DataObject> ListDataContainer<T> createDataContainer(Class<T> type, T[] initialData) {
		return new ArrayDataContainer<T>(type, initialData);
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


	public <T extends Comparable<T>> EndPoint<T> createEndPoint(T value, EndPointType type) {
		return new SimpleEndPoint<T>(value, 0, type, RangeValueGenerators.getInstance().generator(value.getClass()));
	}

	public Object create(Class<?> type, String stringPresentation) {

		if (Calendar.class.isAssignableFrom(type)) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			try {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(stringPresentation));
				return calendar;
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		if (!DataObject.class.isAssignableFrom(type)) {
			throw new UnsupportedTypeException();
		}

		if (type.isInterface()) {
			type = implementationMap.get(type);
		}
		if (type == null) {
			throw new UnsupportedTypeException();
		}
		try {
			Method method = type.getMethod("createFromString", String.class);
			return (DataObject) method.invoke(null, stringPresentation);
		} catch (Exception e) {
			throw new RuntimeException("Can't create Object.", e);
		}
	}

	public Class<? extends Object> getInstanciableClass(Class<?> interfaceType) {
		Class<?> type = null;
		if (interfaceType.isInterface()) {
			type = implementationMap.get(interfaceType);
		}

		return type;
	}

	public <T extends DataObject> T createDataObject(Class<T> type) {

		Class<? extends Object> instanciableClass = getInstanciableClass(type);
		try {
			T newInstance = (T) instanciableClass.newInstance();
			return newInstance;
		} catch (Exception e) {
			throw new RuntimeException(String.format("Can't create new instance from type '%s'", instanciableClass.getName()));
		}
	}

}
