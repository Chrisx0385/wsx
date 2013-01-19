package de.cgz.data.types;

import de.cgz.data.types.collection.collection.DataCollection;
import de.cgz.data.types.collection.collection.ListDataCollection;
import de.cgz.data.ui.DisplayMode;



public abstract class AbstractDescriptor<T extends DataObject> extends AbstractDataObject implements Descriptor<T> {

	private final Class<T> type;

	public AbstractDescriptor(Class<T> type) {
		this.type = type;		
	}

	public Class<T> getType() {
		return type;
	}

	public Class<?> getPropertyType(String property) {
		return utils().getReflectionUtils().getPropertyType(type, property);
	}
	
	@Override
	public DataCollection<String> getProperties(DisplayMode mode) {
		return factory().createListDataCollection(getPropertiesArray(mode));
	}

	public DataCollection<String> getCollectionProperties(DisplayMode mode) {		
		DataCollection<String> properties = getProperties(mode);
		return properties.inject(factory().createListDataCollection(String.class), new InjectStatement<String, ListDataCollection<String>>() {
			public ListDataCollection<String> execute(String property, int index, ListDataCollection<String> mem) {	
				Class<?> propertyType = getPropertyType(property);
				if(propertyType.isArray() || Iterable.class.isAssignableFrom(propertyType)) {
					mem.add(property);
				}
				return mem;
			}});
	}
	
	protected abstract String[] getPropertiesArray(DisplayMode mode);
	protected abstract String[] getCollectionPropertiesArray(DisplayMode mode);
	
}
