package de.cgz.data.types;

import de.cgz.data.ui.DisplayMode;


public abstract class AbstractDescriptor<T extends DataObject> extends AbstractDataObject implements Descriptor<T> {

	private final Class<T> type;

	public AbstractDescriptor(Class<T> type) {
		this.type = type;
		
	}

	public Class<T> getType() {
		return type;
	}

}
